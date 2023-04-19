package utils.saveJSONLocal;

public class SaveJSONToLocalTest {
    public static void main(String[] args) {
        String fileUrl = "https://example.com/data.json";
        try {
            SaveJSONToLocal.saveJSONToLocal(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

