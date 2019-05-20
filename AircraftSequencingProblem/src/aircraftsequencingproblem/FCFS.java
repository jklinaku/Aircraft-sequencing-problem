/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aircraftsequencingproblem;

import java.time.Clock;

/**
 *
 * @author Sead Mejzini
 */
public class FCFS {
// duhet me modifiku, me bo per me i shqyrtu dy varghe te aeroplanave
    //private int[][][] tables;
    public FCFS(){
        //this.tables = tables;
        
    }
    public boolean fcfs(Airplane[] a, Airplane[] b) {
        //Constraints s = new Constraints(a.length);
        Constraints aConst = new Constraints();
        Constraints bConst = new Constraints();
        int A = 0, B = 0;
        if (a[A].getTime() < b[B].getTime()) {
            a[A].setOTime(a[A].getTime());
            aConst.sett(a[A].getOTime());
            A++;
        } else {
            b[B].setOTime(b[B].getTime());
            bConst.sett(b[B].getOTime());
            B++;
        }
        //a[0].setOTime(a[0].getTime());
        //s.sett(s.timeAdder(a[0].getType(), a[0].getOTime()));
        while (A != a.length && B != b.length) {
            Airplane[] temp=null;
            if (a[A].getTime() < b[B].getTime()) {
                if(A>=3)
                    {
                       temp=new Airplane[3];
                        System.arraycopy(a, A-3, temp, 0, 3);
                    }
                    else if(A==2)
                    {
                       temp=new Airplane[2];
                        System.arraycopy(a, A-2, temp, 0, 2); 
                    }
                    else if(A==1){
                        temp=new Airplane[1];
                        System.arraycopy(a, A-1, temp, 0, 1);
                    }
                if(A>0&&B>0){
                    
                    if(!aConst.setTime(a[A], b[B - 1], temp))
                        return false;
                }
                else if (A>0&&B==0){
                    if(!aConst.setTime(a[A], null, temp))
                        return false;
                }
                else{
                    if(!aConst.setTime(a[A], b[B - 1], null))
                        return false;
                }
//                try {
//                    if (!aConst.setTime(a[A], b[B - 1], a[A - 1])) {
//                        return false;
//                    }
//
//                } catch (Exception e) {
//                    try {
//                        if (aConst.setTime(a[A], null, a[A - 1])) {
//                            return false;
//                        }
//                    } catch (Exception e1) {
//                        if (aConst.setTime(a[A], b[B - 1], null)) {
//                            return false;
//                        }
//                    }
//                }
                A++;
            } else {
                
                  if(B>=3)
                    {
                       temp=new Airplane[3];
                        System.arraycopy(b, B-3, temp, 0, 3);
                    }
                    else if(B==2)
                    {
                       temp=new Airplane[2];
                        System.arraycopy(b, B-2, temp, 0, 2); 
                    }
                    else if(B==1){
                        temp=new Airplane[1];
                        System.arraycopy(b, B-1, temp, 0, 1);
                    }
                if(A>0&&B>0){
                    if(!bConst.setTime(b[B], a[A - 1], temp))
                        return false;
                }
                else if (B>0&&A==0){
                    if(!bConst.setTime(b[B], null, temp))
                        return false;
                }
                else{
                    if(!bConst.setTime(b[B], a[A-1], null))
                        return false;
                }
//                try {
//                    if (bConst.setTime(b[B], a[A - 1], b[B - 1])) {
//                        return false;
//                    }
//                } catch (Exception e) {
//                    try {
//                        if (bConst.setTime(b[B], null, b[B - 1])) {
//                            return false;
//                        }
//                    } catch (Exception e1) {
//                        if (bConst.setTime(b[B], a[A - 1], null)) {
//                            return false;
//                        }
//                    }
//                }
                B++;
            }
        }
        while (A != a.length) {
            Airplane[] temp=null;
                if(A>=3)
                    {
                       temp=new Airplane[3];
                        System.arraycopy(a, A-3, temp, 0, 3);
                    }
                    else if(A==2)
                    {
                       temp=new Airplane[2];
                        System.arraycopy(a, A-2, temp, 0, 2); 
                    }
                    else if(A==1){
                        temp=new Airplane[1];
                        System.arraycopy(a, A-1, temp, 0, 1);
                    }
                if(A>0&&B>0){
                    
                    if(!aConst.setTime(a[A], b[B - 1], temp))
                        return false;
                }
                else if (A>0&&B==0){
                   if(! aConst.setTime(a[A], null, temp))
                       return false;
                }
                else{
                   if(! aConst.setTime(a[A], b[B - 1], null))
                       return false;
                }
//            if (aConst.setTime(a[A], b[B - 1], a[A - 1])) {
//                return false;
//            }
            A++;
        }
        while (B != b.length) {
             Airplane[] temp=null;
            if(B>=3)
                    {
                       temp=new Airplane[3];
                        System.arraycopy(b, B-3, temp, 0, 3);
                    }
                    else if(B==2)
                    {
                       temp=new Airplane[2];
                        System.arraycopy(b, B-2, temp, 0, 2); 
                    }
                    else if(B==1){
                        temp=new Airplane[1];
                        System.arraycopy(b, B-1, temp, 0, 1);
                    }
                if(A>0&&B>0){
                    if(!bConst.setTime(b[B], a[A - 1], temp))
                        return false;
                }
                else if (B>0&&A==0){
                    if(!bConst.setTime(b[B], null, temp))
                        return false;
                }
                else{
                   if(! bConst.setTime(b[B], a[A-1], null))
                       return false;
                }
//            if (bConst.setTime(b[B], a[A - 1], b[B - 1])) {
//                return false;
//            }
            B++;
        }
//        aConst.sett(0);
//        bConst.sett(0);
        return true;
    }

//    public void fifo(Airplane[] a) {
//        //Constraints s = new Constraints(a.length);
//        Constraints s = new Constraints(tables);
//
//        a[0].setOTime(a[0].getTime());
//        s.sett(s.timeAdder(a[0].getType(), a[0].getOTime()));
//        for (int i = 1; i < a.length; i++) {
//            s.setTime(a[i]);
//        }
//    }

    public int penalty(Airplane a) {
       int c;
       if(a.getAirplaneClass()<=3)
           c=2;
       else
           c=1;
      // System.out.println((a.getOTime() - a.getTime())*c);
        return ((a.getOTime() - a.getTime())*c );
    }

    public int penalty(Airplane[] a) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            // ans += (a[i].getOTime() - a[i].getTime()) * a[i].getWeight();
            ans += penalty(a[i]);
        }
        return ans;
    }

}
