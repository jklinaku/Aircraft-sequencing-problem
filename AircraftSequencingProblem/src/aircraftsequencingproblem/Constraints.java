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
    private int t;
    public Constraints(int n) {
        T = new int[n][2];
    }

 
     public void setTime(Airplane a) {
       // System.out.println(index);
       int l=t-a.getTime();
       if(l>=0)
       {
           a.setOTime(t);
//           System.out.println(a.getTime()+" "+a.getOTime()+" 1 ");
           
          this.sett( timeAdder(a.getType(),t));
       }
        if(l<0){
           a.setOTime(a.getTime());
           t=a.getOTime();
//           System.out.println(a.getTime()+" "+a.getOTime()+" 2 ");
           this.sett(timeAdder(a.getType(),t));
       }
       
      
    }
    
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

   
    public void setT(Airplane a, int index) {
        T[index][0] = a.getTime();
        T[index][1] = timeAdder(a.getType(), T[index][0]);
        a.setOTime(T[index][0]);
    }
}
