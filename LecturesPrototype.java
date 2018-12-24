/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author parisfisher
 */
public class LecturesPrototype {
    
    private final StringProperty section;
    private final StringProperty days;
    private final StringProperty time;
    private final StringProperty room;
   
    
    public LecturesPrototype(String initSection, String initDays, String initTime, String initRoom)
    {
        section = new SimpleStringProperty(initSection);
        days = new SimpleStringProperty(initDays);
        time = new SimpleStringProperty(initTime);
        room = new SimpleStringProperty(initRoom);
    }
    
    public String getSection() {
        return section.get();
    }

    public void setSection(String initSection) {
        section.set(initSection);
    } 
    
    public String getDays() {
        return days.get();
    }

    public void setDays(String initDays) {
        days.set(initDays);
    }
    
    public String getTime() {
        return time.get();
    }

    public void setTime(String initTime) {
        time.set(initTime);
    }
    
    public String getRoom() {
        return room.get();
    }

    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
}
