package git.jppinza.pico_y_placa_predictor_backend.model;

public class Plate {
    //I'm going to apply encapsulation
    private final String plateNumber;
    private final int lastDigit;

    //I'm going to create a constructor:
    public Plate(String plateNumber){
        if (plateNumber == null) {
            throw new IllegalArgumentException("Inputs can't be null");
        }
        String sanitized = plateNumber.trim();
        if (sanitized.isEmpty() || !Character.isDigit(sanitized.charAt(sanitized.length() - 1))) {
            throw new IllegalArgumentException("Invalid plate number format");
        }
        this.plateNumber = sanitized;
        this.lastDigit = Character.getNumericValue(this.plateNumber.charAt(this.plateNumber.length() - 1));
    }

    //We only can access and modify the attributes by using setters, getters or methods:
    public String getPlateNumber(){
        return this.plateNumber;
    }

    public int getLastDigit() {
        return this.lastDigit;
    }

    @Override
    public String toString(){
        return "Plate{plateNumber="+plateNumber+"}";
    }

}
