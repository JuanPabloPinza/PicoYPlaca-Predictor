package git.jppinza.pico_y_placa_predictor_backend.model;
import java.time.LocalTime;

public enum ValidSchedules {

    MORNING_SCHEDULE(
            LocalTime.of(7, 0),
            LocalTime.of(9, 30)
    ),

    AFTERNOON_SCHEDULE(
            LocalTime.of(16, 0),
            LocalTime.of(19, 30)
    );

    private final LocalTime start;
    private final LocalTime end;

    ValidSchedules(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}