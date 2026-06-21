package git.jppinza.pico_y_placa_predictor_backend.model;

public class CarPlate extends Plate {

    public CarPlate(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public int getLastDigit() {
        // Car logic: 3Letters + "-" + 3Numbers
        // The validator digit is the last character
        char lastChar = plateNumber.charAt(plateNumber.length() - 1);
        if (!Character.isDigit(lastChar)) {
            throw new IllegalArgumentException("Invalid Car Plate format");
        }
        return Character.getNumericValue(lastChar);
    }

    @Override
    public String toString() {
        return "CarPlate{plateNumber=" + plateNumber + "}";
    }
}
