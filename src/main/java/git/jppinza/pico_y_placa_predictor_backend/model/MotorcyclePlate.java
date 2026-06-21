package git.jppinza.pico_y_placa_predictor_backend.model;

public class MotorcyclePlate extends Plate {

    public MotorcyclePlate(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public int getLastDigit() {
        // 1. A motorcycle plate must be 6 characters long (2letters + 3numbers + 1letter)
        if (plateNumber.length() != 6) {
            throw new IllegalArgumentException("Invalid Motorcycle Plate");
        }
        // 2. Las character must be a letter
        char lastChar = plateNumber.charAt(5);
        if (!Character.isLetter(lastChar)) {
            throw new IllegalArgumentException("Invalid Motorcycle Plate");
        }
        
        // 3. The character right before the last letter MUST be our validator number (index 4)
        char validatorDigit = plateNumber.charAt(4);
        if (!Character.isDigit(validatorDigit)) {
            throw new IllegalArgumentException("Invalid Motorcycle Plate");
        }
        
        return Character.getNumericValue(validatorDigit);
    }

    @Override
    public String toString() {
        return "MotorcyclePlate{plateNumber=" + plateNumber + "}";
    }
}
