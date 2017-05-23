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
public class Airplane {

    private final String[] airplane;
    private int OTime;



    public Airplane(String[] airplane) {
        this.airplane = airplane;
    }

    public String[] getAirplane() {
        String[] temp = new String[airplane.length];
        System.arraycopy(airplane, 0, temp, 0, airplane.length);
        temp[airplane.length - 1] = "" + OTime;
        return temp;
    }

    public int getWeight() {
        return (int) Double.parseDouble(airplane[5]);
    }

    public int getTime() {
        return (int) Double.parseDouble(airplane[4]);
    }

    public String getType() {
        return airplane[2];
    }

    public void setOTime(int OTime) {
        this.OTime = OTime;
    }

    private String airplaneName() {
        return airplane[0];
    }

    public boolean samePlane(Airplane a) {
        return airplaneName().equals(a.airplaneName());
    }

    public int getOTime() {
        return OTime;
    }

}
