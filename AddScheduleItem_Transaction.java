/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.SchedulePrototype;

public class AddScheduleItem_Transaction implements jTPS_Transaction{
    
    CourseSiteGeneratorData data;
   SchedulePrototype scheduleItem;
   
   public AddScheduleItem_Transaction(CourseSiteGeneratorData initData, SchedulePrototype initScheduleItem) {
        data = initData;
        scheduleItem = initScheduleItem;
    }
    
    @Override
    public void doTransaction() {
        data.addScheduleItem(scheduleItem);        
    }

    @Override
    public void undoTransaction() {
        data.removeScheduleItem(scheduleItem);
    }
    
}
