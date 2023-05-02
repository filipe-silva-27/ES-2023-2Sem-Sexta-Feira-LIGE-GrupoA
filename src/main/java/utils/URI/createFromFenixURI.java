import models.Aula;
import models.Horario;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class createFromFenixURI {

    public static Horario createFromFenixURI(String uriString) throws Exception {

        // criar URI a partir da string
        URI uri = URI.create(uriString);

        // obter o calendário web do Fénix
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String calendario = response.body();

        // criar um scanner para ler o calendário
        Scanner scanner = new Scanner(calendario);

        // criar um objeto Horario
        Horario horario = new Horario();

        // obter o nome do estudante
        String nome = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("X-WR-CALNAME")) {
                nome = line.split(":")[1].trim();
                break;
            }
        }
        horario.setName(nome);

        // ler as entradas do calendário
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("BEGIN:VEVENT")) {
                // criar um objeto Aula
                Aula aula = new Aula();

                // ler a descrição da aula
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("DESCRIPTION:")) {
                        aula.setDescricao(line.substring(12));
                        break;
                    }
                }

                // ler a data e hora de início da aula
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("DTSTART")) {
                        LocalDateTime inicio = LocalDateTime.parse(line.substring(8), DateTimeFormatter.BASIC_ISO_DATE.withZoneUTC());
                        aula.setDataAula(inicio.toLocalDate());
                        aula.setHoraInicio(inicio.toLocalTime());
                        break;
                    }
                }

                // ler a data e hora de fim da aula
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("DTEND")) {
                        LocalDateTime fim = LocalDateTime.parse(line.substring(6), DateTimeFormatter.BASIC_ISO_DATE.withZoneUTC());
                        aula.setHoraFim(fim.toLocalTime());
                        break;
                    }
                }

                // ler a localização da aula
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("LOCATION:")) {
                        aula.setSala(line.substring(9));
                        break;
                    }
                }

                // adicionar a aula ao horário
                horario.getUnidadesCurriculares().addAula(aula);
            }
            else if (line.startsWith("END:VCALENDAR")) {
                break;
            }
        }

        scanner.close();

        return horario;
    }
}
