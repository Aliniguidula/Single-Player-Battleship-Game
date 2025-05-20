import java.util.ArrayList;

public class Submarine extends Ship {

    /**
     * Sets the inherited length variable and initializes the
     * hit array, based on the size of this ship (1 tiles).
     */
    public Submarine(){
        this.length = 1;
        this.hit = new boolean[]{false};
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}