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
public class Population {

    private int[] ndarja = new int[0];
    private Airplane[] airplane;
    private Subset[] subset;

    public Population(Airplane[] airplane) {
        this.airplane = airplane;
        int sa = howMany();
        this.subset = new Subset[sa];
    }

    public void populationCreator() {
        for (int i = 0; i < subset.length-1; i++) {
            insert(subset[i], ndarja, i);
        }
    }

    private int howMany() {
        int answer = 0;
        for (int i = 0; i < airplane.length; i++) {
            if (airplane[i].getTime() == airplane[i].getOTime()) {
                int[] temp = new int[ndarja.length + 1];
                temp[ndarja.length] = i;
                ndarja = temp;
                answer++;
            }
        }
        return answer;
    }

    public void insert(Subset s, int[] ndajra, int index) {
        int start = ndarja[index];
        int finish = ndarja[index + 1];
        for (int i = start; i < finish; i++) {
            s.insertAirplane(airplane[i]);
        }
    }

    public Subset[] getSubset() {
        return subset;
    }
}
