package git.jppinza.pico_y_placa_predictor_backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Service;

import git.jppinza.pico_y_placa_predictor_backend.model.Plate;
import git.jppinza.pico_y_placa_predictor_backend.model.ValidDays;
import git.jppinza.pico_y_placa_predictor_backend.model.ValidSchedules;

@Service
public class PicoYPlacaService {

    public boolean canDrive(String plateNumber, String date, String time) {
        //Check if they're null in order to avoid errors
        if (date == null || time == null) {
            throw new IllegalArgumentException("Inputs can't be null");
        }

        Plate plate = Plate.createPlate(plateNumber);
        int lastDigit = plate.getLastDigit();

        LocalDate parsedDate;
        LocalTime parsedTime;
        try {
            parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error! Invalid date/time format. Use yyyy-MM-dd and HH:mm");
        }

        DayOfWeek dayOfWeek = parsedDate.getDayOfWeek();
        
        // I'm checking if pico y placa applies.
        if (isRestrictedDay(dayOfWeek, lastDigit)) {
            if (isRestrictedTime(parsedTime)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRestrictedDay(DayOfWeek dayOfWeek, int lastDigit) {
        try {
            ValidDays restriction = ValidDays.valueOf(dayOfWeek.name());
            return lastDigit == restriction.getPlate1() || lastDigit == restriction.getPlate2();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isRestrictedTime(LocalTime time) {
        for (ValidSchedules schedule : ValidSchedules.values()) {
            if (!time.isBefore(schedule.getStart()) && !time.isAfter(schedule.getEnd())) {
                return true;
            }
        }
        return false;
    }
}
