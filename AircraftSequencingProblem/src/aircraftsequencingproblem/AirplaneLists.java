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
   // int[][][] tables;
FCFS f= new FCFS();
    public AirplaneLists(Airplane[] airplanes1,Airplane[] airplanes2) {
        this.airplanes1 = airplanes1.clone();
        this.airplanes2 = airplanes2.clone();
        //FIFO.fifo(airplanes);
        //this.tables = tables;
    }
    public AirplaneLists(int n, String[][] a) {
       Airplane[] airplanes = new Airplane[n];
        for (int i = 0; i < n; i++) {
            airplanes[i] = new Airplane(a[i]);
        }
        Airplane[] airplanes2= new Airplane[airplanes.length/2];
        Airplane[] airplanes1= new Airplane[airplanes.length-airplanes2.length];
        for(int i=0;i<airplanes.length;i++){
                if(i%2==0){
                   airplanes1[i/2]=airplanes[i];
                }
                else{
                   airplanes2[i/2]=airplanes[i]; 
                }
            }
        this.airplanes1=airplanes1.clone();
        this.airplanes2=airplanes2.clone();
       // FIFO.fifo(airplanes);
    }
    public AirplaneLists(Airplane[][] airplanes){
        this.airplanes1=airplanes[0].clone();
        this.airplanes2=airplanes[1].clone();
    }

    public boolean update(){
       return f.fcfs(airplanes1,airplanes2);
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
    public Airplane[][] getAirplanesMatrix(){
        Airplane[][] temp = new Airplane[2][airplanes1.length];
        for(int i=0;i<airplanes1.length;i++){
            temp[0][i]=airplanes1[i];
            temp[1][i]=airplanes2[i];
        }
        return temp;
    }
    public Airplane getAirplane(int i,boolean airplane1) {
        if(airplane1)
            return airplanes1[i];
        else
            return airplanes2[i];
    }
}
