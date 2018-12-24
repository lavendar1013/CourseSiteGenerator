/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;


/**
 *
 * @author parisfisher
 */
public class RemoveLab_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
   MeetingTimesPrototype lab;
   
   public RemoveLab_Transaction(CourseSiteGeneratorData initData, MeetingTimesPrototype initLab) {
        data = initData;
       lab = initLab;
    }
    
    @Override
    public void doTransaction() {
        data.removeLab(lab);        
    }

    @Override
    public void undoTransaction() {
        data.addLab(lab);
    }
}