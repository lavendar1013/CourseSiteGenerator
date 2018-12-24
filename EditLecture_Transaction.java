/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.LecturesPrototype;

/**
 *
 * @author parisfisher
 */
public class EditLecture_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    LecturesPrototype lecture;
    String oldSection;
    String oldDays;
    String oldTime;
    String oldRoom;
    String newSection;
    String newDays;
    String newTime;
    String newRoom;
    
    public EditLecture_Transaction(CourseSiteGeneratorData initData, LecturesPrototype initLecture, String initSection, String initDays, String initTime, String initRoom){
        data=initData;
        lecture=initLecture;
        oldSection=lecture.getSection();
        oldDays=lecture.getDays();
        oldTime=lecture.getTime();
        oldRoom=lecture.getRoom();
        newSection=initSection;
        newDays=initDays;
        newTime=initTime;
        newRoom=initRoom;
    }
    
    @Override
    public void doTransaction() {
        int index=data.getLectures().indexOf(lecture); 
        data.getLectures().get(index).setDays(newDays);
        data.getLectures().get(index).setSection(newSection);
        data.getLectures().get(index).setTime(newTime);
        data.getLectures().get(index).setRoom(newRoom);
    }

    @Override
    public void undoTransaction() {
        int index=data.getLectures().indexOf(lecture); 
        data.getLectures().get(index).setDays(oldDays);
        data.getLectures().get(index).setSection(oldSection);
        data.getLectures().get(index).setTime(oldTime);
        data.getLectures().get(index).setRoom(oldRoom);
    }
        
    
}
