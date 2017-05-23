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
public class FIFO {

    public void fifo(Airplane[] a) {
        Constraints s = new Constraints(a.length);
        a[0].setOTime(a[0].getTime());
        s.setT(a[0], 0);
        for (int i = 1; i < a.length; i++) {
            s.setTime(a[i], i);
        }
    }

    public int penalty(Airplane[] a) {
        int ans = 0;
        for (Airplane a1 : a) {
            ans += Math.abs(a1.getTime() - a1.getOTime()) * a1.getWeight();
        }
        return ans;
    }
}
