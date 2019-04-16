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
public class FIFO {
// duhet me modifiku, me bo per me i shqyrtu dy varghe te aeroplanave
    private int[][][] tables;
    public FIFO(int[][][] tables){
        this.tables = tables;
        
    }
    public boolean fifo(Airplane[] a, Airplane[] b) {
        //Constraints s = new Constraints(a.length);
        Constraints aConst = new Constraints(tables);
        Constraints bConst = new Constraints(tables);
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
            if (a[A].getTime() < b[B].getTime()) {
                if(A>0&&B>0){
                    aConst.setTime(a[A], b[B - 1], a[A - 1]);
                }
                else if (A>0&&B==0){
                    aConst.setTime(a[A], null, a[A - 1]);
                }
                else{
                    aConst.setTime(a[A], b[B - 1], null);
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
                
                 
                if(A>0&&B>0){
                    bConst.setTime(b[B], a[A - 1], b[B - 1]);
                }
                else if (B>0&&A==0){
                    bConst.setTime(b[B], null, b[B-1]);
                }
                else{
                    bConst.setTime(b[B], a[A-1], null);
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
            
                if(A>0&&B>0){
                    aConst.setTime(a[A], b[B - 1], a[A - 1]);
                }
                else if (A>0&&B==0){
                    aConst.setTime(a[A], null, a[A - 1]);
                }
                else{
                    aConst.setTime(a[A], b[B - 1], null);
                }
//            if (aConst.setTime(a[A], b[B - 1], a[A - 1])) {
//                return false;
//            }
            A++;
        }
        while (B != b.length) {
            if(A>0&&B>0){
                    bConst.setTime(b[B], a[A - 1], b[B - 1]);
                }
                else if (A>0&&B==0){
                    bConst.setTime(b[B], a[A - 1], null);
                }
                else{
                    bConst.setTime(b[B], null, b[B - 1]);
                }
//            if (bConst.setTime(b[B], a[A - 1], b[B - 1])) {
//                return false;
//            }
            B++;
        }
        aConst.t=0;
        bConst.t=0;
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
        return ((a.getOTime() - a.getTime()) );
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
