/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
/**
 *
 * @author parisfisher
 */
public class EditStartDate_Transaction implements jTPS_Transaction{
    String month;
    String day;
    String oldMonth;
    String oldDay;
    CourseSiteGeneratorData data;
    public EditStartDate_Transaction(CourseSiteGeneratorData data, String month, String day)
        {
            oldDay=data.getStartingMondayDay();
            oldMonth=data.getStartingMondayMonth();
            this.data=data;
            this.month=month;
            this.day=day;
        }
    @Override
    public void doTransaction() {
        data.setStartingMondayDay(day);
        data.setStartingMondayMonth(month);
    }

    @Override
    public void undoTransaction() {
        data.setStartingMondayDay(oldDay);
        data.setStartingMondayMonth(oldMonth);
    }
}
