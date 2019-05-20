/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;



import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Jon Klinaku
 */
public class Constraints {

    //private int[][] T;
    private int t=0;
    protected static int[][][] tables;
//    private int[][] table1;
//    private int[][] table2;
    public Constraints() {
        //T = new int[n][2];
       // table1=tables[0];
      //  table2=tables[1];
       // this.tables=tables;
    }

 // duhet me modifiku per me bo krahasimin mes dy aeroplanave
    
    
    
    public boolean setTime(Airplane a, Airplane b, Airplane[] aPrev){
       int tempTime=t;
        if(!Objects.isNull(b))
        {
            int temp;
            temp=b.getOTime()+tables[1][b.getAirplaneClass()-1][a.getAirplaneClass()-1];
            if(temp>tempTime)
                tempTime=temp;
        }
        if(!Objects.isNull(aPrev)){
            int temp=aPrev[0].getOTime()+tables[0][aPrev[0].getAirplaneClass()-1][a.getAirplaneClass()-1];
            for (int i = 1; i < aPrev.length; i++) {
                if(temp<aPrev[i].getOTime()+tables[0][aPrev[i].getAirplaneClass()-1][a.getAirplaneClass()-1])
                     temp=aPrev[i].getOTime()+tables[0][aPrev[i].getAirplaneClass()-1][a.getAirplaneClass()-1];
            }
           // int temp=aPrev.getOTime()+tables[0][a.getAirplaneClass()-1][aPrev.getAirplaneClass()-1];
            if(temp>tempTime)
                tempTime=temp;
                
        }
        if(a.getTime()>tempTime)
            tempTime=a.getTime();
        
        //duhen modifikime te metutjeshme ne metoden setOtime (te kthej vler boolean!)
        if(!a.setOTime(tempTime))
            return false;
        this.t=tempTime;
        
        return true;
    }
    
//    public void setTime(Airplane a) {
//       // System.out.println(index);
//       int l=t-a.getTime();
//       if(l>=0)
//       {
//           a.setOTime(t);
////           System.out.println(a.getTime()+" "+a.getOTime()+" 1 ");
//           
//          this.sett( timeAdder(a.getType(),t));
//       }
//        if(l<0){
//           a.setOTime(a.getTime());
//           t=a.getOTime();
////           System.out.println(a.getTime()+" "+a.getOTime()+" 2 ");
//           this.sett(timeAdder(a.getType(),t));
//       }
//       
//      
//    }
//    
    public void sett(int t){
        this.t=t;
    }
//    public void setTime(Airplane a, int index) {
//       // System.out.println(index);
//        if (T[index -1][1] > a.getTime()) {
//            a.setOTime(T[index - 1][1]);
//            T[index][0] = T[index - 1][1];
//            T[index][1] = timeAdder(a.getType(), T[index][0]);
//        } else {
//            this.setT(a, index);
//        }
//    }

    public int timeAdder(String type, int time) {
        time += (type.equals("M")) ? 2 : 3;
        return time;
    }

   
//    public void setT(Airplane a, int index) {
//        T[index][0] = a.getTime();
//        T[index][1] = timeAdder(a.getType(), T[index][0]);
//        a.setOTime(T[index][0]);
//    }
}
