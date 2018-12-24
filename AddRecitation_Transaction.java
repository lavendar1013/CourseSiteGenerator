/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

/**
 *
 * @author parisfisher
 */
import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;

public class AddRecitation_Transaction implements jTPS_Transaction{
    
    CourseSiteGeneratorData data;
   MeetingTimesPrototype recitation;
   
   public AddRecitation_Transaction(CourseSiteGeneratorData initData, MeetingTimesPrototype initRecitation) {
        data = initData;
        recitation = initRecitation;
    }
    
    @Override
    public void doTransaction() {
        data.addRecitation(recitation);        
    }

    @Override
    public void undoTransaction() {
        data.removeRecitation(recitation);
    }
    
}
