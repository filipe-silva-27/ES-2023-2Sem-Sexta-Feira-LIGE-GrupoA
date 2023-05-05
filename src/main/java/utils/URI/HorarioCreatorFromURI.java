/*package utils.URI;
import models.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class HorarioCreatorFromURI {

    public static Horario createHorarioFromURI(String uri) throws IOException{

        String content = readURL(uri);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(new StringReader(content));

        Horario horario = new Horario("Horario from URI");
        Map<String, UnidadeCurricular> ucs = new HashMap<>();

        for (Object component : calendar.getComponents()) {
            Component comp = (Component) component;

            if (comp.getName().equals("VEVENT")) {
                VEvent event = (VEvent) comp;

                String uid = event.getUid().getValue();
                String summary = event.getSummary().getValue();
                String[] summaryParts = summary.split("-");
                String ucName = summaryParts[1].trim();
                String turma = summaryParts[2].trim();
                String turno = summaryParts[3].trim();

                UnidadeCurricular uc = ucs.get(ucName);

                if (uc == null) {
                    uc = new UnidadeCurricular("Informática", ucName);
                    ucs.put(ucName, uc);
                }

                String sala = event.getLocation().getValue();
                Integer lotacao = null;
                Integer numInscritos = null;

                if (event.getDescription() != null) {
                    String description = event.getDescription().getValue();
                    String[] descriptionParts = description.split(",");

                    if (descriptionParts.length > 1) {
                        lotacao = Integer.valueOf(descriptionParts[0].trim());
                        numInscritos = Integer.valueOf(descriptionParts[1].trim());
                    }
                }

                DateTime start = event.getStartDate().getDateTime();
                Date date = new Date(start.getTime());
                DiaSemana diaSemana = DiaSemana.fromName(event.getStartDate().getDate().toString().split("T")[0]);
                LocalTime horaInicio = LocalTime.of(start.getHour(), start.getMinute());

                DateTime end = event.getEndDate().getDateTime();
                LocalTime horaFim = LocalTime.of(end.getHour(), end.getMinute());

                Aula aula = new Aula(uc, turno, turma, numInscritos, sala, lotacao);
                DataAula dataAula = new DataAula(diaSemana, horaInicio, horaFim, date);
                aula.addAula(dataAula);
                uc.addAula(aula);
            }
        }

        return horario;
    }

    private static String readURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        }
    }
}*/