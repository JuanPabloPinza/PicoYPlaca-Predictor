package git.jppinza.pico_y_placa_predictor_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import git.jppinza.pico_y_placa_predictor_backend.service.PicoYPlacaService;

@RestController
@RequestMapping("/api/picoyplaca")
public class PicoYPlacaController {

    private final PicoYPlacaService picoYPlacaService;

    @Autowired
    public PicoYPlacaController(PicoYPlacaService picoYPlacaService) {
        this.picoYPlacaService = picoYPlacaService;
    }

    @GetMapping("/predict")
    public ResponseEntity<?> predict(@RequestParam String plate,@RequestParam String date,@RequestParam String time) {
        try {
            boolean canDrive = picoYPlacaService.canDrive(plate, date, time);
            String message = canDrive ? "This vehicle is allowed to drive." : "The vehicle is not allowed to drive.";
            return ResponseEntity.ok(new PredictionResponse(plate, date, time, canDrive, message));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public static class PredictionResponse {
        private String plate;
        private String date;
        private String time;
        private boolean canDrive;
        private String message;

        public PredictionResponse(String plate, String date, String time, boolean canDrive, String message) {
            this.plate = plate;
            this.date = date;
            this.time = time;
            this.canDrive = canDrive;
            this.message = message;
        }

        public String getPlate() { return plate; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public boolean isCanDrive() { return canDrive; }
        public String getMessage() { return message; }
    }
}

