package utils.URI;

import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.UnidadeCurricular;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.MapTimeZoneCache;
import net.fortuna.ical4j.util.Configurator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ExtractModelsFromWebcal {

    private static final String EVENT_NAME = "VEVENT";
    private static final String LINE_SEPARATOR = "\n";
    private static final String UC_PREFIX = "Unidade de execução: ";
    private static final String TURNO_PREFIX = "Turno: ";



    /*public static List<Aula> getAulasFromWebcal(URI uri) throws IOException, ParserException, ParseException {
        List<Aula> aulas = new ArrayList<>();

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(uri.toURL().openStream());

        for (Component component : calendar.getComponents()) {
            if (component.getName().equals(EVENT_NAME)) {
                VEvent event = (VEvent) component;

                String summary = event.getSummary().getValue();
                String description = event.getDescription().getValue();
                String location = event.getLocation().getValue();



                // Parse UC and turno from description
                String[] lines = description.split(LINE_SEPARATOR);
                String uc = parseValue(lines, UC_PREFIX);
                String turno = parseValue(lines, TURNO_PREFIX);

                // Create new Aula object and add to list
                Aula aula = new Aula(new UnidadeCurricular(uc), turno, location);
                aulas.add(aula);
            }
        }

        return aulas;
    }

    private static String parseValue(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                return line.substring(prefix.length()).trim();
            }
        }
        return "";
    }

*/

    public static List<Aula> getAulasFromWebcal(URI uri) throws IOException, ParserException, ParseException {
        List<Aula> aulas = new ArrayList<>();

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(uri.toURL().openStream());

        for (Component component : calendar.getComponents()) {
            if (component.getName().equals(EVENT_NAME)) {
                VEvent event = (VEvent) component;

                String summary = event.getSummary().getValue();
                String description = event.getDescription().getValue();
                String location = event.getLocation().getValue();
                String[] lines = description.split(LINE_SEPARATOR);
                //String uc = "";
                for (String line : lines) {
                    int index = line.indexOf("Unidade de execução:");
                    if (index >= 0) {
                        int endIndex = line.indexOf("\nCódigo", index);
                        if (endIndex >= 0) {
                        //    uc = line.substring(index + "Unidade de execução:".length(), endIndex).trim();
                            break;
                        }
                    }
                }
                String uc = parseValue(lines, UC_PREFIX);
                String turno = parseValue(lines, TURNO_PREFIX);

                // Create new Aula object and add to list
                Aula aula = new Aula(new UnidadeCurricular(uc), turno, location);
                aulas.add(aula);
            }
        }
        return aulas;
    }

    public static List<DataAula> getDataAulaFromWebcal(URI uri) throws IOException, ParserException, ParseException {
        List<DataAula> aulas = new ArrayList<>();

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(uri.toURL().openStream());

        for (Component component : calendar.getComponents()) {
            if (component.getName().equals(EVENT_NAME)) {
                VEvent event = (VEvent) component;

                String summary = event.getSummary().getValue();
                String description = event.getDescription().getValue();
                String location = event.getLocation().getValue();
                String[] lines = description.split(LINE_SEPARATOR);

                String uc = parseValue(lines, UC_PREFIX);
                String turno = parseValue(lines, TURNO_PREFIX);

                // Get start and end times
                Date start = event.getStartDate().getDate();
                Date end = event.getEndDate().getDate();
                LocalTime horaInicio = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                LocalTime horaFim = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

                // Get day of week
                int dayOfWeek = start.toInstant().atZone(ZoneId.systemDefault()).getDayOfWeek().getValue();
                DiaSemana diaSemana = DiaSemana.values()[dayOfWeek - 1];

                // Create new DataAula object and add to list
                DataAula aula = new DataAula(diaSemana, horaInicio, horaFim, start);
                aulas.add(aula);
            }
        }
        return aulas;
    }

    public static Set<UnidadeCurricular> getUnidadesCurriculares(String nomeUC, List<Aula> aulas) {
        Set<UnidadeCurricular> ucs = new HashSet<>();
        for (Aula aula : aulas) {
            if (aula.getUc().getNomeUC().equals(nomeUC)) {
                ucs.add(aula.getUc());
            }
        }
        return ucs;
    }



    private static String parseValue(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                return line.substring(prefix.length()).trim();
            }
        }
        return "";
    }


    public static void main(String[] args) {
        try {
            URI uri = new URI("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=jmcfo@iscte.pt&password=BBvgt0CF3CA66sm253Rr8j3nms5GkvuvniL8IrRCmfTuZwxQBqFaJ8pDDRZS0oedL1Qx7YHandbEuN9wRAJdflCP1RUMUlyOQuzb4IeCmcKFdgAE7KGAOA4JPVNmzxAz");


            List<DataAula> dataAulas = ExtractModelsFromWebcal.getDataAulaFromWebcal(uri);
            for (DataAula dataAula : dataAulas) {
                System.out.println(dataAula.toString());
            }
            //List<Aula> aulas = ExtractModelsFromWebcal.getAulasFromWebcal(uri);
            /*for (Aula aula : aulas) {
                System.out.println(aula.toString());
            }*/
        } catch (URISyntaxException | IOException | ParserException | ParseException e) {
            e.printStackTrace();
        }
    }
}
