/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

/**
 *
 * @author pro
 */
public class AirplaneList {
Airplane[] airplanes ;
FIFO f= new FIFO();
    public AirplaneList(Airplane[] airplanes) {
        this.airplanes = airplanes.clone();
        //FIFO.fifo(airplanes);
    }

    public AirplaneList(int n, String[][] a) {
        airplanes = new Airplane[n];
        for (int i = 0; i < n; i++) {
            airplanes[i] = new Airplane(a[i]);
        }
       // FIFO.fifo(airplanes);
    }

    public void update(){
       f.fifo(airplanes);
    }
    public void setAirplane(int pos,Airplane a){
        
        airplanes[pos]=a;
        
    }
    public int fitness(){
    
     return f.penalty(airplanes);
    }
    public int size(){
        return airplanes.length;
    }
    public Airplane[] getAirplanes() {
        Airplane[] a= new Airplane[airplanes.length];
        System.arraycopy(airplanes, 0, a, 0, airplanes.length);
        return a;
    }
    public Airplane getAirplane(int i) {
        return airplanes[i];
    }
    
}
