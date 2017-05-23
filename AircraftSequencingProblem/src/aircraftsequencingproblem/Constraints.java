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
public class Constraints {

    private int[][] T;

    public Constraints(int n) {
        T = new int[n][2];
    }

    public void setTime(Airplane a, int index) {
        if (T[index - 1][1] > a.getTime()) {
            a.setOTime(T[index - 1][1]);
            T[index][0] = T[index - 1][1];
            T[index][1] = timeAdder(a.getType(), T[index][0]);
        } else {
            this.setT(a, index);
        }
    }

    private int timeAdder(String type, int time) {
        time += (type.equals("M")) ? 2 : 3;
        return time;
    }

    public void setT(Airplane a, int index) {
        T[index][0] = a.getTime();
        T[index][1] = timeAdder(a.getType(), T[index][0]);
        a.setOTime(T[index][0]);
    }
}
