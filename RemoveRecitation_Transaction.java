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
public class RemoveRecitation_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
   MeetingTimesPrototype recitation;
   
   public RemoveRecitation_Transaction(CourseSiteGeneratorData initData, MeetingTimesPrototype initRecitation) {
        data = initData;
        recitation = initRecitation;
    }
    
    @Override
    public void doTransaction() {
        data.removeRecitation(recitation);        
    }

    @Override
    public void undoTransaction() {
        data.addRecitation(recitation);
    }
}