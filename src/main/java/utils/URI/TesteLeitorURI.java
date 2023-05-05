package utils.URI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TesteLeitorURI {

    public static void loadScheduleFromWebcal(String webcalURI) throws IOException {
        // Criar um objeto URL a partir do Webcal URI
        URL url = new URL(webcalURI);

        // Abrir uma conexão HTTP para o URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurar a conexão para usar o método GET e aceitar a resposta como texto
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/calendar");

        // Verificar se a resposta foi bem sucedida (código 200 OK)
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }

        // Ler a resposta da conexão e exibi-la na saída padrão
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        // Fechar a conexão
        connection.disconnect();
    }

    /*public static String getWebcalURIFromUser() throws IOException {
        // Cria um objeto BufferedReader para ler a entrada do usuário
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Pede que o usuário digite a URI do Webcal
        System.out.println("Digite a URI do Webcal: ");

        // Lê a entrada do usuário e retorna a string
        String webcalURI =  reader.readLine();
       // webcalURI = webcalURI.substring(8);
        //
        // webcalURI = "https://" + webcalURI;
        return webcalURI;
    }*/

   /* public static String getWebcalURIFromUser() throws IOException {
        // Cria um objeto BufferedReader para ler a entrada do usuário
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Pede que o usuário digite a URI do Webcal
        System.out.println("Digite a URI do Webcal: ");

        // Lê a entrada do usuário e retorna a string
        String webcalURI =  reader.readLine();
        if (webcalURI.startsWith("webcal://")) {
            webcalURI = "https://" + webcalURI.substring(9);
        } else {
            webcalURI = "https://" + webcalURI;
        }
        return webcalURI;
    }*/

    public static String getWebcalURIFromUser(String input) {
        // Use the input provided as the URI
        String webcalURI = input;

        if (webcalURI.startsWith("webcal://")) {
            webcalURI = "https://" + webcalURI.substring(9);
        // Add "https://" prefix if necessary
       // if (!webcalURI.startsWith("https://")) {
         //   webcalURI = "https://" + webcalURI;
        }

        return webcalURI;
    }

    public static void main(String[] args) throws IOException {
        //String webcalURI = getWebcalURIFromUser();
        //String webcalURI = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=rmfde@iscte.pt&password=Kat41qMCyPigm7gCNAc5l1WKb5cHkLdInbvS1IYm3fDz84UaPhyJ3nsvdKcLPAfOMZkW0sW0STWAHYsNi3B6cOqDzoF2Sa0Q2aGPQ7LSw23yhGHehYEnsWWHhyYTADFZ";
        //loadScheduleFromWebcal(webcalURI);
    }
}
