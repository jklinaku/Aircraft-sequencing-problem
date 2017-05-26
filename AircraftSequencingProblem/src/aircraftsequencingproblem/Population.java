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

    private final Airplane[] airplane;
    private Subset[] subset = new Subset[0];

    public Population(Airplane[] airplane) {
        this.airplane = airplane;
    }

    public void populationCreator(int[] n) {
        int index = 0;
        subset = new Subset[n.length - 1];
        subsetCreator(subset, n);
        for (int i = 0; i < subset.length; i++) {
            for (int j = 0; j < n[i + 1] - n[i]; j++) {
                subset[i].insertAirplanes(airplane[index], j);
                index++;
            }
        }
    }

    public void subsetCreator(Subset[] s, int[] n) {
        for (int i = 0; i < s.length; i++) {
            s[i] = new Subset(n[i + 1] - n[i]);
        }
    }

    public int howMany() {
        int answer = 0;
        for (int i = 0; i < airplane.length; i++) {
            if (airplane[i].getTime() == airplane[i].getOTime()) {
                answer++;
            }
        }
        return answer;
    }

    public int[] fragments() {
        int[] answer = new int[howMany() + 1];
        int index = 0;
        for (int i = 0; i < airplane.length; i++) {
            if (airplane[i].getTime() == airplane[i].getOTime()) {
                answer[index] = i;
                index++;
            }
        }
        answer[howMany()] = airplane.length;
        return answer;
    }

    public Subset[] getSubset() {
        return subset;
    }
}
