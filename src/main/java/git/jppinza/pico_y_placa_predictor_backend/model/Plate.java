package git.jppinza.pico_y_placa_predictor_backend.model;

public class Plate {
    //I'm going to apply encapsulation
    private final String plateNumber;

    //I'm going to create a constructor:
    public Plate(String plateNumber){
        this.plateNumber = plateNumber;
    }

    //We only can access and modify the attributes by using setters, getters or methods:
    String getPlateNumber(){
        return this.plateNumber;
    }

    //Polymorphism:
        @Override
    public String toString(){
        return "Plate{plateNumber="+plateNumber+"}";
    }

}
