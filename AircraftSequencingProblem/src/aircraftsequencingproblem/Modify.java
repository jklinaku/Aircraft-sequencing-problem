/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

/**
 *
 * @author Sead Mejzini
 */
public class Modify {

    private Airplane[] plane;
    private int[] index;

    public Modify(Airplane[] plane) {
        this.plane = plane;
        index = new int[0];
    }

    public int generateIndex() {
        int t = 0;
        for (int i = 0; i < plane.length; i++) {
            if (plane[i].getTime() == plane[i].getOTime()) {
                int[] temp = new int[index.length + 1];
                temp[t] = i;
                index = temp;
                t++;
            }
        }
        return t;
    }

    public int[] getIndex() {
        return index;
    }

    public Airplane[] modify(Airplane[] a) {
        Airplane[] ans = new Airplane[a.length];
        
        return ans;
    }

    public Airplane[] getPlane() {
        return plane;
    }

}
