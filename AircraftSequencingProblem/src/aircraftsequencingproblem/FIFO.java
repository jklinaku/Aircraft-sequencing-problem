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

    public  void fifo(Airplane[] a) {
        Constraints s = new Constraints(a.length);
        a[0].setOTime(a[0].getTime());
        s.sett(s.timeAdder(a[0].getType(), a[0].getOTime()));
        for (int i = 1; i < a.length; i++) {
            s.setTime(a[i]);
        }
    }

    public  int penalty(Airplane a) {
        return (a.getOTime() - a.getTime()) * a.getWeight();
    }

    public  int penalty(Airplane[] a) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += (a[i].getOTime() - a[i].getTime()) * a[i].getWeight();
        }
        return ans;
    }

   
}
