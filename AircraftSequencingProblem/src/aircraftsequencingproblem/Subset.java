package aircraftsequencingproblem;

/**
 *
 * @author Sead Mejzini
 */
public class Subset {

    private Airplane[] plane;

    public Subset(int size) {
        plane = new Airplane[size];
    }

    public void insertAirplanes(Airplane p, int i) {
        this.plane[i] = p;
    }

    public Airplane[] getAirplane() {
        return plane;
    }

    public void printSubset() {
        for (int i = 0; i < plane.length; i++) {
            String[] temp = plane[i].getAirplane();
            System.out.print(i + ": ");
            for (String temp1 : temp) {
                System.out.print(temp1 + "   ");
            }
            System.out.println();
        }
    }
}
