/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.CourseSiteGeneratorApp;
import static csg.OfficeHoursPropertyType.SCHEDULE_ITEMS_TABLE;
import csg.data.SchedulePrototype;
import jtps.jTPS_Transaction;
import csg.data.TeachingAssistantPrototype;
import djf.modules.AppGUIModule;
import javafx.scene.control.TableView;
/**
 *
 * @author parisfisher
 */
public class EditScheduleItem_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorApp app;
    SchedulePrototype scheduleItemToEdit;
    String oldType, newType;
    String oldMonth, newMonth;
    String oldDay, newDay;
    String oldTitle, newTitle;
    String oldTopic, newTopic;
    String oldLink, newLink;
    
    public EditScheduleItem_Transaction(CourseSiteGeneratorApp initApp, SchedulePrototype initScheduleItemToEdit, 
            String type, String month, String day, String title, String topic, String link) {
        app=initApp;
        scheduleItemToEdit = initScheduleItemToEdit;
        oldType = scheduleItemToEdit.getType();
        oldMonth = scheduleItemToEdit.getMonth();
        oldDay = scheduleItemToEdit.getDay();
        oldTitle = scheduleItemToEdit.getTitle();
        oldTopic = scheduleItemToEdit.getTopic();
        oldLink = scheduleItemToEdit.getLink();
        newType=type;
        newMonth=month;
        newDay=day;
        newLink=link;
        newTopic=topic;
        newTitle=title;
    }


    @Override
    public void doTransaction() {
        AppGUIModule gui= app.getGUIModule();
        scheduleItemToEdit.setType(newType);
        scheduleItemToEdit.setMonth(newMonth);
        scheduleItemToEdit.setDay(newDay);
        scheduleItemToEdit.setLink(newLink);
        scheduleItemToEdit.setTopic(newTopic);
        scheduleItemToEdit.setTitle(newTitle);
        TableView scheduleTable= (TableView) gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        scheduleTable.refresh();
        
    }

    @Override
    public void undoTransaction() {
        AppGUIModule gui= app.getGUIModule();
        scheduleItemToEdit.setType(oldType);
        scheduleItemToEdit.setMonth(oldMonth);
        scheduleItemToEdit.setDay(oldDay);
        scheduleItemToEdit.setLink(oldLink);
        scheduleItemToEdit.setTopic(oldTopic);
        scheduleItemToEdit.setTitle(oldTitle);
        TableView scheduleTable= (TableView) gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        scheduleTable.refresh();
    }
}

