package utils.URI;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /**
     * A class that extracts a URI from a webpage given its URL.
     */
    public class WebpageURIExtractor {

        private String webpageUrl;
        private URL webpage;

        /**
         * Creates a new WebpageURIExtractor object with the given webpage URL.
         *
         * @param webpageUrl the URL of the webpage to extract the URI from
         * @throws MalformedURLException if the webpage URL is malformed
         */
        public WebpageURIExtractor(String webpageUrl) throws MalformedURLException {
            this.webpageUrl = webpageUrl;
            this.webpage = new URL(webpageUrl);
        }

        /**
         * Extracts the first URI found in the webpage.
         *
         * @return the URI found in the webpage, or null if none was found
         * @throws IOException if there is an error reading the webpage
         */
        public String extractURI() throws IOException {
            // Download the webpage content
            String webpageContent = new Scanner(webpage.openStream(), "UTF-8").useDelimiter("\\A").next();

            // Search for the first URI in the webpage
            Pattern pattern = Pattern.compile("href=['\"]?([^'\">\\s]+)['\"]?");
            Matcher matcher = pattern.matcher(webpageContent);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return null;
            }
        }

        /**
         * Returns the URL of the webpage.
         *
         * @return the URL of the webpage
         */
        public String getWebpageUrl() {
            return webpageUrl;
        }

        /**
         * Sets the URL of the webpage.
         *
         * @param webpageUrl the URL of the webpage
         * @throws MalformedURLException if the webpage URL is malformed
         */
        public void setWebpageUrl(String webpageUrl) throws MalformedURLException {
            this.webpageUrl = webpageUrl;
            this.webpage = new URL(webpageUrl);
        }
    }


