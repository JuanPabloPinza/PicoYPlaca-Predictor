package git.jppinza.pico_y_placa_predictor_backend.model;

public enum ValidDays {
    MONDAY(1,2),
    TUESDAY(3,4),
    WEDNESDAY(5,6),
    THURSDAY(7,8),
    FRIDAY(9,0);

    private final int plate1;
    private final int plate2;

    ValidDays(int plate1, int plate2){
        this.plate1 = plate1;
        this.plate2 = plate2;
    }

    public int getPlate1() {
        return plate1;
    }

    public int getPlate2() {
        return plate2;
    }
}