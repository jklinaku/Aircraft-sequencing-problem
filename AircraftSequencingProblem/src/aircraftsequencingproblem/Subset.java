package aircraftsequencingproblem;

/**
 *
 * @author Sead Mejzini
 */
public class Subset {

    private int size;
    private Airplane[] plane = new Airplane[0];

    public Subset(Airplane[] plane) {
        size = 0;
        this.plane = plane;
    }

    public void insertAirplane(Airplane a) {
        Airplane[] temp = new Airplane[plane.length + 1];
        System.arraycopy(plane, 0, temp, 0, plane.length);
        temp[plane.length] = a;
        plane = temp;
        size++;
    }
    
    public Airplane[] getAirplane() {
        return plane;
    }

    public int getSize() {
        return size;
    }
}
