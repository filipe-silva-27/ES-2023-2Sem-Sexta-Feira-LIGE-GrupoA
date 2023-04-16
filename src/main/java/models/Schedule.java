package models;

import java.io.File;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    private String name;
    private File file;
    private int dayPeriod;
    Map<DayOfWeek, List<AulaTurno>> horario;

    public Schedule(String name, int dayPeriod) {
        this.name = name;
        this.file = null;
        this.dayPeriod = dayPeriod;
        this.horario = new EnumMap<>( DayOfWeek.class );
    }

    public void generateSchedule(List<UnidadeCurricular> ucs) {

/*
        // Group AulaUCTurno by day of week
        for (UnidadeCurricular uc : ucs) {
            for (AulaUCTurno aula: models.UnidadeCurricular.getAulaUCTurno()) {
                DayOfWeek day = models.AulaUCTurnogetDayOfWeek();
                List<AulaUCTurno AulaUCTurno = models.AulaUCTurnoByDay.getOrDefault(day, new ArrayList<>());
                AulaUCTurno.add(models.AulaUCTurno;
                AulaUCTurnoByDay.put(day, models.AulaUCTurno);
            }
        }

        // Print schedule for each day of week
        for (DayOfWeek day : DayOfWeek.values()) {
            List<AulaUCTurno AulaUCTurno = AulaUCTurnoByDay.getOrDefault(day, Collections.emptyList());
            Collections.sort(AulaUCTurno, Comparator.comparing(AulaUCTurno:getStartTime));

            System.out.println(day + ":");
            for (AulaUCTurnoAulaUCTurno: AulaUCTurno) {
                System.out.println(models.AulaUCTurnogetStartTime() + " - " + models.AulaUCTurnogetEndTime() + " " +
                        models.AulaUCTurnogetRoom().getName() + " " + models.AulaUCTurnogetUnidadeCurricular().getName());
            }
        }*/
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(final File selectedFile) {
        this.file = selectedFile;
    }
}
