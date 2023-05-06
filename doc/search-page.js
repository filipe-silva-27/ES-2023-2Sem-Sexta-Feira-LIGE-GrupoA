/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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
$(function() {
    let copy = $("#page-search-copy");
    let expand = $("#page-search-expand");
    let searchLink = $("span#page-search-link");
    let redirect = $("input#search-redirect");
    function setSearchUrlTemplate() {
        let href = document.location.href.split(/[#?]/)[0];
        href += "?q=" + "%s";
        if (redirect.is(":checked")) {
            href += "&r=1";
        }
        searchLink.html(href);
        copy[0].onmouseenter();
    }
    function copyLink(e) {
        let textarea = document.createElement("textarea");
        textarea.style.height = 0;
        document.body.appendChild(textarea);
        textarea.value = this.previousSibling.innerText;
        textarea.select();
        document.execCommand("copy");
        document.body.removeChild(textarea);
        let span = this.lastElementChild;
        let copied = span.getAttribute("data-copied");
        if (span.innerHTML !== copied) {
            let initialLabel = span.innerHTML;
            span.innerHTML = copied;
            let parent = this.parentElement.parentElement;
            parent.onmouseleave = parent.ontouchend = copy[0].onmouseenter = function() {
                span.innerHTML = initialLabel;
            };
        }
    }
    copy.click(copyLink);
    copy[0].onmouseenter = function() {};
    redirect.click(setSearchUrlTemplate);
    setSearchUrlTemplate();
    copy.prop("disabled", false);
    redirect.prop("disabled", false);
    expand.click(function (e) {
        let searchInfo = $("div.page-search-info");
        if(this.parentElement.hasAttribute("open")) {
            searchInfo.attr("style", "border-width: 0;");
        } else {
            searchInfo.attr("style", "border-width: 1px;").height(searchInfo.prop("scrollHeight"));
        }
    });
});
$(window).on("load", function() {
    let input = $("#page-search-input");
    let reset = $("#page-search-reset");
    let notify = $("#page-search-notify");
    let resultSection = $("div#result-section");
    let resultContainer = $("div#result-container");
    let searchTerm = "";
    let activeTab = "";
    let fixedTab = false;
    let visibleTabs = [];
    let feelingLucky = false;
    function renderResults(result) {
        if (!result.length) {
            notify.html(messages.noResult);
        } else if (result.length === 1) {
            notify.html(messages.oneResult);
        } else {
            notify.html(messages.manyResults.replace("{0}", result.length));
        }
        resultContainer.empty();
        let r = {
            "types": [],
            "members": [],
            "packages": [],
            "modules": [],
            "searchTags": []
        };
        for (let i in result) {
            let item = result[i];
            let arr = r[item.category];
            arr.push(item);
        }
        if (!activeTab || r[activeTab].length === 0 || !fixedTab) {
            Object.keys(r).reduce(function(prev, curr) {
                if (r[curr].length > 0 && r[curr][0].score > prev) {
                    activeTab = curr;
                    return r[curr][0].score;
                }
                return prev;
            }, 0);
        }
        if (feelingLucky && activeTab) {
            notify.html(messages.redirecting)
            let firstItem = r[activeTab][0];
            window.location = getURL(firstItem.indexItem, firstItem.category);
            return;
        }
        if (result.length > 20) {
            if (searchTerm[searchTerm.length - 1] === ".") {
                if (activeTab === "types" && r["members"].length > r["types"].length) {
                    activeTab = "members";
                } else if (activeTab === "packages" && r["types"].length > r["packages"].length) {
                    activeTab = "types";
                }
            }
        }
        let categoryCount = Object.keys(r).reduce(function(prev, curr) {
            return prev + (r[curr].length > 0 ? 1 : 0);
        }, 0);
        visibleTabs = [];
        let tabContainer = $("<div class='table-tabs'></div>").appendTo(resultContainer);
        for (let key in r) {
            let id = "#result-tab-" + key.replace("searchTags", "search_tags");
            if (r[key].length) {
                let count = r[key].length >= 1000 ? "999+" : r[key].length;
                if (result.length > 20 && categoryCount > 1) {
                    let button = $("<button id='result-tab-" + key
                        + "' class='page-search-header'><span>" + categories[key] + "</span>"
                        + "<span style='font-weight: normal'> (" + count + ")</span></button>").appendTo(tabContainer);
                    button.click(key, function(e) {
                        fixedTab = true;
                        renderResult(e.data, $(this));
                    });
                    visibleTabs.push(key);
                } else {
                    $("<span class='page-search-header active-table-tab'>" + categories[key]
                        + "<span style='font-weight: normal'> (" + count + ")</span></span>").appendTo(tabContainer);
                    renderTable(key, r[key]).appendTo(resultContainer);
                    tabContainer = $("<div class='table-tabs'></div>").appendTo(resultContainer);

                }
            }
        }
        if (activeTab && result.length > 20 && categoryCount > 1) {
            $("button#result-tab-" + activeTab).addClass("active-table-tab");
            renderTable(activeTab, r[activeTab]).appendTo(resultContainer);
        }
        resultSection.show();
        function renderResult(category, button) {
            activeTab = category;
            setSearchUrl();
            resultContainer.find("div.summary-table").remove();
            renderTable(activeTab, r[activeTab]).appendTo(resultContainer);
            button.siblings().removeClass("active-table-tab");
            button.addClass("active-table-tab");
        }
    }
    function selectTab(category) {
        $("button#result-tab-" + category).click();
    }
    function renderTable(category, items) {
        let table = $("<div class='summary-table'>")
            .addClass(category === "modules"
                ? "one-column-search-results"
                : "two-column-search-results");
        let col1, col2;
        if (category === "modules") {
            col1 = "Module";
        } else if (category === "packages") {
            col1 = "Module";
            col2 = "Package";
        } else if (category === "types") {
            col1 = "Package";
            col2 = "Class"
        } else if (category === "members") {
            col1 = "Class";
            col2 = "Member";
        } else if (category === "searchTags") {
            col1 = "Location";
            col2 = "Name";
        }
        $("<div class='table-header col-plain'>" + col1 + "</div>").appendTo(table);
        if (category !== "modules") {
            $("<div class='table-header col-plain'>" + col2 + "</div>").appendTo(table);
        }
        $.each(items, function(index, item) {
            let rowColor = index % 2 ? "odd-row-color" : "even-row-color";
            renderItem(item, table, rowColor);
        });
        return table;
    }
    function renderItem(item, table, rowColor) {
        let label = getHighlightedText(item.input, item.boundaries, item.prefix.length, item.input.length);
        let link = $("<a/>")
            .attr("href",  getURL(item.indexItem, item.category))
            .attr("tabindex", "0")
            .addClass("search-result-link")
            .html(label);
        let container = getHighlightedText(item.input, item.boundaries, 0, item.prefix.length - 1);
        if (item.category === "searchTags") {
            container = item.indexItem.h || "";
        }
        if (item.category !== "modules") {
            $("<div/>").html(container).addClass("col-plain").addClass(rowColor).appendTo(table);
        }
        $("<div/>").html(link).addClass("col-last").addClass(rowColor).appendTo(table);
    }

    let timeout;
    function schedulePageSearch() {
        if (timeout) {
            clearTimeout(timeout);
        }
        timeout = setTimeout(function () {
            doPageSearch()
        }, 100);
    }
    function doPageSearch() {
        setSearchUrl();
        let term = searchTerm = input.val().trim();
        if (term === "") {
            notify.html(messages.enterTerm);
            activeTab = "";
            fixedTab = false;
            resultContainer.empty();
            resultSection.hide();
        } else {
            notify.html(messages.searching);
            doSearch({ term: term, maxResults: 1200 }, renderResults);
        }
    }
    function setSearchUrl() {
        let query = input.val().trim();
        let url = document.location.pathname;
        if (query) {
            url += "?q=" + encodeURI(query);
            if (activeTab && fixedTab) {
                url += "&c=" + activeTab;
            }
        }
        history.replaceState({query: query}, "", url);
    }
    input.on("input", function(e) {
        feelingLucky = false;
        schedulePageSearch();
    });
    $(document).keydown(function(e) {
        if ((e.ctrlKey || e.metaKey) && (e.key === "ArrowLeft" || e.key === "ArrowRight")) {
            if (activeTab && visibleTabs.length > 1) {
                let idx = visibleTabs.indexOf(activeTab);
                idx += e.key === "ArrowLeft" ? visibleTabs.length - 1 : 1;
                selectTab(visibleTabs[idx % visibleTabs.length]);
                return false;
            }
        }
    });
    reset.click(function() {
        notify.html(messages.enterTerm);
        resultSection.hide();
        activeTab = "";
        fixedTab = false;
        resultContainer.empty();
        input.val('').focus();
        setSearchUrl();
    });
    input.prop("disabled", false);
    reset.prop("disabled", false);

    let urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("q")) {
        input.val(urlParams.get("q"))
    }
    if (urlParams.has("c")) {
        activeTab = urlParams.get("c");
        fixedTab = true;
    }
    if (urlParams.get("r")) {
        feelingLucky = true;
    }
    if (input.val()) {
        doPageSearch();
    } else {
        notify.html(messages.enterTerm);
    }
    input.select().focus();
});
