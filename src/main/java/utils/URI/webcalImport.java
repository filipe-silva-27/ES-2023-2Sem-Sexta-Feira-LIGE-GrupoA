/*
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

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class webcalImport {

    private static final String EVENT_NAME = "VEVENT";
    private static final String LINE_SEPARATOR = "\n";
    private static final String UC_PREFIX = "Unidade de execução: ";
    private static final String TURNO_PREFIX = "Turno: ";

    public static void webcalToHorario(){
        String input = JOptionPane.showInputDialog(null, "Enter the Webcal URI: ");
        if (input != null && !input.trim().isEmpty()) {

                if (input.startsWith("webcal://")) {
                     input = "https://" + input.substring(9);
                }



                 getAulasFromWebcal(input);


                JOptionPane.showMessageDialog(null, "Calendar events imported from:\n" + input);

        }

    }


    public static void getAulasFromWebcal(String uriString) {
        List<Aula> aulas = new ArrayList<>();

        List<DataAula> datas = new ArrayList<>();
        int i = 0;
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = null;
        try {
            calendar = builder.build(new URI(uriString).toURL().openStream());
        } catch (IOException | ParserException | URISyntaxException e) {
            throw new RuntimeException(e);
        }



        for (Component component : calendar.getComponents()) {
            if (component.getName().equals(EVENT_NAME)) {
                VEvent event = (VEvent) component;

                //n da curso nem turma nem turno nem inscritos nem lotacao
                String description = event.getDescription().getValue();
                String[] lines = description.split(LINE_SEPARATOR);
                String unidadeCurricular = parseValue(lines, UC_PREFIX);
                String sala = event.getLocation().getValue();
                //String summary = event.getSummary().getValue();

                Date data = event.getStartDate().getDate();
                Date end = event.getEndDate().getDate();
                LocalTime horaInicio = data.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                LocalTime horaFim = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

                // Get day of week
                int dayOfWeek = data.toInstant().atZone(ZoneId.systemDefault()).getDayOfWeek().getValue();
                DiaSemana diaDaSemana = DiaSemana.values()[dayOfWeek - 1];

                criaHorario (unidadeCurricular, "-1", "-1", "-1",  diaDaSemana, horaInicio, horaFim, data, sala, -1, -1);


                */
/*//*
/ Create new DataAula object and add to list
                DataAula data = new DataAula(diaDaSemana, horaInicio, horaFim, data);
                datas.add(data);


                // Create new Aula object and add to list
                Aula aula = new Aula(new UnidadeCurricular(uc), turno, location);
                aulas.add(aula);
                aula.setDataAula(datas.get(i));
                i++;*//*

            }
        }

    }


    private static String parseValue(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                return line.substring(prefix.length()).trim();
            }
        }
        return "";
    }
}
*/
