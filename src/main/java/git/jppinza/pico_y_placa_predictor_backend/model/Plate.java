package git.jppinza.pico_y_placa_predictor_backend.model;

public abstract class Plate {
    //I'm going to apply encapsulation
    protected final String plateNumber;

    //I'm going to create a constructor:
    public Plate(String plateNumber){
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Inputs cannot be null or empty");
        }
        this.plateNumber = plateNumber.trim().toUpperCase();
    }

    //We only can access and modify the attributes by using setters, getters or methods:
    public String getPlateNumber(){
        return this.plateNumber;
    }

    // Abstract method strictly implemented by child classes (avoiding overridable calls in constructor)
    public abstract int getLastDigit();

    // Factory method to apply polymorphism in an easiest way
    public static Plate createPlate(String plateNumber) {
        if (plateNumber == null) throw new IllegalArgumentException("Inputs cannot be null");

        if (plateNumber.contains("-")) {
            return new CarPlate(plateNumber);
        } else {
            return new MotorcyclePlate(plateNumber);
        }
    }

}
