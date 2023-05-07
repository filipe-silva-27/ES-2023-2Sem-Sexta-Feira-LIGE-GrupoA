/*
 * Copyright (c) 2015, 2022, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
"use strict";
const messages = {
    enterTerm: "Enter a search term",
    noResult: "No results found",
    oneResult: "Found one result",
    manyResults: "Found {0} results",
    loading: "Loading search index...",
    searching: "Searching...",
    redirecting: "Redirecting to first result...",
    copyUrl: "Copy URL",
    urlCopied: "Copied!"
}
const categories = {
    modules: "Modules",
    packages: "Packages",
    types: "Classes and Interfaces",
    members: "Members",
    searchTags: "Search Tags"
};
const highlight = "<span class='result-highlight'>$&</span>";
const NO_MATCH = {};
const MAX_RESULTS = 500;
function checkUnnamed(name, separator) {
    return name === "<Unnamed>" || !name ? "" : name + separator;
}
function escapeHtml(str) {
    return str.replace(/</g, "&lt;").replace(/>/g, "&gt;");
}
function getHighlightedText(str, boundaries, from, to) {
    let start = from;
    let text = "";
    for (let i = 0; i < boundaries.length; i += 2) {
        let b0 = boundaries[i];
        let b1 = boundaries[i + 1];
        if (b0 >= to || b1 <= from) {
            continue;
        }
        text += escapeHtml(str.slice(start, Math.max(start, b0)));
        text += "<span class='result-highlight'>";
        text += escapeHtml(str.slice(Math.max(start, b0), Math.min(to, b1)));
        text += "</span>";
        start = Math.min(to, b1);
    }
    text += escapeHtml(str.slice(start, to));
    return text;
}
function getURLPrefix(item, category) {
    let urlPrefix = "";
    let slash = "/";
    if (category === "modules") {
        return item.l + slash;
    } else if (category === "packages" && item.m) {
        return item.m + slash;
    } else if (category === "types" || category === "members") {
        if (item.m) {
            urlPrefix = item.m + slash;
        } else {
            $.each(packageSearchIndex, function(index, it) {
                if (it.m && item.p === it.l) {
                    urlPrefix = it.m + slash;
                }
            });
        }
    }
    return urlPrefix;
}
function getURL(item, category) {
    if (item.url) {
        return item.url;
    }
    let url = getURLPrefix(item, category);
    if (category === "modules") {
        url += "module-summary.html";
    } else if (category === "packages") {
        if (item.u) {
            url = item.u;
        } else {
            url += item.l.replace(/\./g, '/') + "/package-summary.html";
        }
    } else if (category === "types") {
        if (item.u) {
            url = item.u;
        } else {
            url += checkUnnamed(item.p, "/").replace(/\./g, '/') + item.l + ".html";
        }
    } else if (category === "members") {
        url += checkUnnamed(item.p, "/").replace(/\./g, '/') + item.c + ".html" + "#";
        if (item.u) {
            url += item.u;
        } else {
            url += item.l;
        }
    } else if (category === "searchTags") {
        url += item.u;
    }
    item.url = url;
    return url;
}
function createMatcher(term, camelCase) {
    if (camelCase && !isUpperCase(term)) {
        return null;  // no need for camel-case matcher for lower case query
    }
    let pattern = "";
    let upperCase = [];
    term.trim().split(/\s+/).forEach(function(w, index, array) {
        let tokens = w.split(/(?=[A-Z,.()<>?[\/])/);
        for (let i = 0; i < tokens.length; i++) {
            let s = tokens[i];
            // ',' and '?' are the only delimiters commonly followed by space in java signatures
            pattern += "(" + $.ui.autocomplete.escapeRegex(s).replace(/[,?]/g, "$&\\s*?") + ")";
            upperCase.push(false);
            let isWordToken =  /\w$/.test(s);
            if (isWordToken) {
                if (i === tokens.length - 1 && index < array.length - 1) {
                    // space in query string matches all delimiters
                    pattern += "(.*?)";
                    upperCase.push(isUpperCase(s[0]));
                } else {
                    if (!camelCase && isUpperCase(s) && s.length === 1) {
                        pattern += "()";
                    } else {
                        pattern += "([a-z0-9$<>?[\\]]*?)";
                    }
                    upperCase.push(isUpperCase(s[0]));
                }
            } else {
                pattern += "()";
                upperCase.push(false);
            }
        }
    });
    let re = new RegExp(pattern, "gi");
    re.upperCase = upperCase;
    return re;
}
function analyzeMatch(matcher, input, startOfName, category) {
    let from = startOfName;
    matcher.lastIndex = from;
    let match = matcher.exec(input);
    while (!match && from > 1) {
        from = input.lastIndexOf(".", from - 2) + 1;
        matcher.lastIndex = from;
        match = matcher.exec(input);
    }
    if (!match) {
        return NO_MATCH;
    }
    let boundaries = [];
    let matchEnd = match.index + match[0].length;
    let leftParen = input.indexOf("(");
    // exclude peripheral matches
    if (category !== "modules" && category !== "searchTags") {
        if (leftParen > -1 && leftParen < match.index) {
            return NO_MATCH;
        } else if (startOfName - 1 >= matchEnd) {
            return NO_MATCH;
        }
    }
    let endOfName = leftParen > -1 ? leftParen : input.length;
    let score = 5;
    let start = match.index;
    let prevEnd = -1;
    for (let i = 1; i < match.length; i += 2) {
        let isUpper = isUpperCase(input[start]);
        let isMatcherUpper = matcher.upperCase[i];
        // capturing groups come in pairs, match and non-match
        boundaries.push(start, start + match[i].length);
        // make sure groups are anchored on a left word boundary
        let prevChar = input[start - 1] || "";
        let nextChar = input[start + 1] || "";
        if (start !== 0 && !/[\W_]/.test(prevChar) && !/[\W_]/.test(input[start])) {
            if (isUpper && (isLowerCase(prevChar) || isLowerCase(nextChar))) {
                score -= 0.1;
            } else if (isMatcherUpper && start === prevEnd) {
                score -= isUpper ? 0.1 : 1.0;
            } else {
                return NO_MATCH;
            }
        }
        prevEnd = start + match[i].length;
        start += match[i].length + match[i + 1].length;

        // lower score for parts of the name that are missing
        if (match[i + 1] && prevEnd < endOfName) {
            score -= rateNoise(match[i + 1]);
        }
    }
    // lower score if a type name contains unmatched camel-case parts
    if (input[matchEnd - 1] !== "." && endOfName > matchEnd)
        score -= rateNoise(input.slice(matchEnd, endOfName));
    score -= rateNoise(input.slice(0, Math.max(startOfName, match.index)));

    if (score <= 0) {
        return NO_MATCH;
    }
    return {
        input: input,
        score: score,
        category: category,
        boundaries: boundaries
    };
}
function isUpperCase(s) {
    return s !== s.toLowerCase();
}
function isLowerCase(s) {
    return s !== s.toUpperCase();
}
function rateNoise(str) {
    return (str.match(/([.(])/g) || []).length / 5
         + (str.match(/([A-Z]+)/g) || []).length / 10
         +  str.length / 20;
}
function doSearch(request, response) {
    let term = request.term.trim();
    let maxResults = request.maxResults || MAX_RESULTS;
    if (term.length === 0) {
        return this.close();
    }
    let matcher = {
        plainMatcher: createMatcher(term, false),
        camelCaseMatcher: createMatcher(term, true)
    }
    let indexLoaded = indexFilesLoaded();

    function getPrefix(item, category) {
        switch (category) {
            case "packages":
                return checkUnnamed(item.m, "/");
            case "types":
                return checkUnnamed(item.p, ".");
            case "members":
                return checkUnnamed(item.p, ".") + item.c + ".";
            default:
                return "";
        }
    }
    function useQualifiedName(category) {
        switch (category) {
            case "packages":
                return /[\s/]/.test(term);
            case "types":
            case "members":
                return /[\s.]/.test(term);
            default:
                return false;
        }
    }
    function searchIndex(indexArray, category) {
        let matches = [];
        if (!indexArray) {
            if (!indexLoaded) {
                matches.push({ l: messages.loading, category: category });
            }
            return matches;
        }
        $.each(indexArray, function (i, item) {
            let prefix = getPrefix(item, category);
            let simpleName = item.l;
            let qualifiedName = prefix + simpleName;
            let useQualified = useQualifiedName(category);
            let input = useQualified ? qualifiedName : simpleName;
            let startOfName = useQualified ? prefix.length : 0;
            let m = analyzeMatch(matcher.plainMatcher, input, startOfName, category);
            if (m === NO_MATCH && matcher.camelCaseMatcher) {
                m = analyzeMatch(matcher.camelCaseMatcher, input, startOfName, category);
            }
            if (m !== NO_MATCH) {
                m.indexItem = item;
                m.prefix = prefix;
                if (!useQualified) {
                    m.input = qualifiedName;
                    m.boundaries = m.boundaries.map(function(b) {
                        return b + prefix.length;
                    });
                }
                matches.push(m);
            }
            return matches.length < maxResults;
        });
        return matches.sort(function(e1, e2) {
            return e2.score - e1.score;
        });
    }

    let result = searchIndex(moduleSearchIndex, "modules")
         .concat(searchIndex(packageSearchIndex, "packages"))
         .concat(searchIndex(typeSearchIndex, "types"))
         .concat(searchIndex(memberSearchIndex, "members"))
         .concat(searchIndex(tagSearchIndex, "searchTags"));

    if (!indexLoaded) {
        let updateSearchResults = function() {
            doSearch(request, response);
        }
    } else {
        let updateSearchResults = function() {};
    }
    response(result);
}
// JQuery search menu implementation
$.widget("custom.catcomplete", $.ui.autocomplete, {
    _create: function() {
        this._super();
        this.widget().menu("option", "items", "> .result-item");
        // workaround for search result scrolling
        this.menu._scrollIntoView = function _scrollIntoView( item ) {
            let borderTop, paddingTop, offset, scroll, elementHeight, itemHeight;
            if ( this._hasScroll() ) {
                borderTop = parseFloat( $.css( this.activeMenu[ 0 ], "borderTopWidth" ) ) || 0;
                paddingTop = parseFloat( $.css( this.activeMenu[ 0 ], "paddingTop" ) ) || 0;
                offset = item.offset().top - this.activeMenu.offset().top - borderTop - paddingTop;
                scroll = this.activeMenu.scrollTop();
                elementHeight = this.activeMenu.height() - 26;
                itemHeight = item.outerHeight();

                if ( offset < 0 ) {
                    this.activeMenu.scrollTop( scroll + offset );
                } else if ( offset + itemHeight > elementHeight ) {
                    this.activeMenu.scrollTop( scroll + offset - elementHeight + itemHeight );
                }
            }
        };
    },
    _renderMenu: function(ul, items) {
        let currentCategory = "";
        let widget = this;
        widget.menu.bindings = $();
        $.each(items, function(index, item) {
            if (item.category && item.category !== currentCategory) {
                ul.append("<li class='ui-autocomplete-category'>" + categories[item.category] + "</li>");
                currentCategory = item.category;
            }
            let li = widget._renderItemData(ul, item);
            if (item.category) {
                li.attr("aria-label", categories[item.category] + " : " + item.l);
            } else {
                li.attr("aria-label", item.l);
            }
            li.attr("class", "result-item");
        });
        ul.append("<li class='ui-static-link'><a href='" + pathtoroot + "search.html?q="
            + encodeURI(widget.term) + "'>Go to search page</a></li>");
    },
    _renderItem: function(ul, item) {
        let li = $("<li/>").appendTo(ul);
        let div = $("<div/>").appendTo(li);
        let label = item.l
            ? item.l
            : getHighlightedText(item.input, item.boundaries, 0, item.input.length);
        let idx = item.indexItem;
        if (item.category === "searchTags" && idx.h) {
            if (idx.d) {
                div.html(label + "<span class='search-tag-holder-result'> (" + idx.h + ")</span><br><span class='search-tag-desc-result'>"
                    + idx.d + "</span><br>");
            } else {
                div.html(label + "<span class='search-tag-holder-result'> (" + idx.h + ")</span>");
            }
        } else {
            div.html(label);
        }
        return li;
    }
});
$(function() {
    let expanded = false;
    let windowWidth;
    function collapse() {
        if (expanded) {
            $("div#navbar-top").removeAttr("style");
            $("button#navbar-toggle-button")
                .removeClass("expanded")
                .attr("aria-expanded", "false");
            expanded = false;
        }
    }
    $("button#navbar-toggle-button").click(function (e) {
        if (expanded) {
            collapse();
        } else {
            let navbar = $("div#navbar-top");
            navbar.height(navbar.prop("scrollHeight"));
            $("button#navbar-toggle-button")
                .addClass("expanded")
                .attr("aria-expanded", "true");
            expanded = true;
            windowWidth = window.innerWidth;
        }
    });
    $("ul.sub-nav-list-small li a").click(collapse);
    $("input#search-input").focus(collapse);
    $("main").click(collapse);
    $("section[id] > :header, :header[id], :header:has(a[id])").hover(
        function () {
            $(this).append($("<button class='copy copy-header' onclick='copyUrl(this)'> " +
                "<img src='" + pathtoroot + "copy.svg' alt='" + messages.copyUrl + "'> " +
                "<span data-copied='" + messages.urlCopied + "'></span></button>"));
        },
        function () {
            $(this).find("button:last").remove();
        }
    );
    $(window).on("orientationchange", collapse).on("resize", function(e) {
        if (expanded && windowWidth !== window.innerWidth) collapse();
    });
    let search = $("#search-input");
    let reset = $("#reset-button");
    search.catcomplete({
        minLength: 1,
        delay: 200,
        source: doSearch,
        response: function(event, ui) {
            if (!ui.content.length) {
                ui.content.push({ l: messages.noResult });
            } else {
                $("#search-input").empty();
            }
        },
        autoFocus: true,
        focus: function(event, ui) {
            return false;
        },
        position: {
            collision: "flip"
        },
        select: function(event, ui) {
            if (ui.item.indexItem) {
                let url = getURL(ui.item.indexItem, ui.item.category);
                window.location.href = pathtoroot + url;
                $("#search-input").focus();
            }
        }
    });
    search.val('');
    search.prop("disabled", false);
    reset.prop("disabled", false);
    reset.click(function() {
        search.val('').focus();
    });
    search.focus();
});
