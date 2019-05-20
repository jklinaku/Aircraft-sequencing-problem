/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

/**
 *
 * @author Jon Klinaku
 */
public class Airplane {

    private final String[] airplane;
    private int OTime;
    private final int deadline;



    public Airplane(String[] airplane) {
        this.airplane = airplane;
        deadline=(int) Double.parseDouble(airplane[1])+900;
    }

    public String[] getAirplane() {
        String[] temp = new String[airplane.length+1];
        System.arraycopy(airplane, 0, temp, 0, airplane.length);
        temp[airplane.length ] = "" + OTime;
        return temp;
    }

//    public int getWeight() {
//        return (int) Double.parseDouble(airplane[5]);
//    }

//    public int getTime() {
//        return (int) Double.parseDouble(airplane[4]);
//    }
 public int getTime() {
        return (int) Double.parseDouble(airplane[1]);
    }
//    public String getTravelType(){
//    return airplane[2];
//    }
//    public String getType() {
//        return airplane[2];
//    }
//metoda e meposhtme kerkon modifikim!
    public boolean setOTime(int OTime) {
        
        
       if(OTime>deadline){
           //System.out.println("over time");
           return false;}
        this.OTime = OTime;
        return true;
    }
    public int getAirplaneClass(){
        return (int)Double.parseDouble(airplane[2]);
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
