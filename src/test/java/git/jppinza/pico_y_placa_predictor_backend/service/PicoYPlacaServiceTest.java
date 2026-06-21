package git.jppinza.pico_y_placa_predictor_backend.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PicoYPlacaServiceTest {

    private final PicoYPlacaService service = new PicoYPlacaService();

    //In order to test I'm using the plate PYI-0010 -> Friday is not allowed to drive
    @Test
    void testDriveOnWeekends() {
        assertTrue(service.canDrive("PYI-0010", "2026-06-20", "08:00"));
        assertTrue(service.canDrive("PYI-0010", "2026-06-20", "18:00"));
    }
    //June 19th -> Friday IS NOT ALLOWED
    @Test
    void testDriveWhenRestricted() {
        assertFalse(service.canDrive("PYI-0010", "2026-06-19", "07:30"));
        assertFalse(service.canDrive("PYI-0010", "2026-06-19", "16:30"));
    }

    // Monday the restrictions are for 1 and 2 so a Plate ending in 3 should be fine
    @Test
    void testCanDrive() {
        assertTrue(service.canDrive("PBX-1233", "2026-06-15", "08:00"));
    }

    @Test
    void testMotorcyclePlates() {
        //Plate 3 is allowed on Mondays
        assertTrue(service.canDrive("PY123C", "2026-06-15", "08:00"));

        // Plate 1 should be restricted Mondays.
        assertFalse(service.canDrive("XD001Z", "2026-06-15", "08:00"));
    }

    @Test
    void testAllowedDriveDuringSafeHoursOnRestrictedDay() {
        assertTrue(service.canDrive("PYI-0010", "2026-06-19", "06:59"));
    }


    @Test
    void testInvalidPlate() {
        assertThrows(IllegalArgumentException.class, () ->
            service.canDrive("ABC-DEFG", "2026-06-16", "08:00"));
    }


    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> 
            service.canDrive("PYO-0010", "16-10-2026", "08:00"));
    }
}
