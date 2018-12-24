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
public class MeetingTimesPrototype {
    
    private final StringProperty section;
    private final StringProperty day_time;
    private final StringProperty room;
    private final StringProperty ta_1;
    private final StringProperty ta_2;
    
    public MeetingTimesPrototype(String initSection, String initDaystimes, String initRoom, String initTA1, String initTA2)
    {
        section = new SimpleStringProperty(initSection);
        day_time = new SimpleStringProperty(initDaystimes);
        ta_1 = new SimpleStringProperty(initTA1);
        ta_2 = new SimpleStringProperty(initTA2);
        room = new SimpleStringProperty(initRoom);
    }
    
    public String getSection() {
        return section.get();
    }

    public void setSection(String initSection) {
        section.set(initSection);
    } 
    
    public String getDay_time() {
        return day_time.get();
    }

    public void setDay_time(String initDaystimes) {
        day_time.set(initDaystimes);
    }
    
    public String getTa_1() {
        return ta_1.get();
    }

    public void setTa_1(String initTA1) {
        ta_1.set(initTA1);
    }
    
    public String getTa_2() {
        return ta_2.get();
    }

    public void setTa_2(String initTA2) {
        ta_2.set(initTA2);
    }
    
    public String getRoom() {
        return room.get();
    }

    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
    
}
