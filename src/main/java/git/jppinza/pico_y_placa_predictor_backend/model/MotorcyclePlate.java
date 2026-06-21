package git.jppinza.pico_y_placa_predictor_backend.model;

public class MotorcyclePlate extends Plate {

    public MotorcyclePlate(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public int getLastDigit() {
        // Motorcycle logic: 2Letters + 3Numbers + 1LastLetter (e.g., PB341C)
        // The validator digit is the last number before the final letter.
        for (int i = plateNumber.length() - 1; i >= 0; i--) {
            if (Character.isDigit(plateNumber.charAt(i))) {
                return Character.getNumericValue(plateNumber.charAt(i));
            }
        }
        throw new IllegalArgumentException("Invalid Motorcycle Plate!");
    }

    @Override
    public String toString() {
        return "MotorcyclePlate{plateNumber=" + plateNumber + "}";
    }
}
