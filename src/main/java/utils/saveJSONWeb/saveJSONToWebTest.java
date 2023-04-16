package utils.saveJSONWeb;

public class saveJSONToWebTest {

        public static void main(String[] args) {
            String uploadUrl = "https://example.com/upload";
            String filePath = "/path/to/myfile.json";

            SaveJSONToWeb uploader = new SaveJSONToWeb();
            uploader.uploadJSON(uploadUrl, filePath);
        }
}
