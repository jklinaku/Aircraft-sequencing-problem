/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

/**
 *
 * @author Jon
 */
public class AirplaneLists {
   public Airplane[] airplanes1 ;
   public Airplane[] airplanes2 ;
    int[][][] tables;
FIFO f= new FIFO(tables);
    public AirplaneLists(Airplane[] airplanes1,Airplane[] airplanes2,int[][][] tables) {
        this.airplanes1 = airplanes1.clone();
        this.airplanes2 = airplanes2.clone();
        //FIFO.fifo(airplanes);
        this.tables = tables;
    }


    public void update(){
       f.fifo(airplanes1,airplanes2);
    }
    public void setAirplane(int pos,Airplane a,boolean airplane1){
        
        if(airplane1)
            airplanes1[pos]=a;
        else
            airplanes2[pos]=a;
        
    }
    public int fitness(){
    
     return f.penalty(airplanes1)+f.penalty(airplanes2);
    }
    public int size(){
        return airplanes1.length+airplanes2.length;
    }
    public Airplane[] getAirplanes() {
        Airplane[] a= new Airplane[airplanes1.length+airplanes2.length];
        System.arraycopy(airplanes1, 0, a, 0, airplanes1.length);
        System.arraycopy(airplanes2, 0, a, airplanes1.length, airplanes2.length);
        return a;
    }
    public Airplane getAirplane(int i,boolean airplane1) {
        if(airplane1)
            return airplanes1[i];
        else
            return airplanes2[i];
    }
}
