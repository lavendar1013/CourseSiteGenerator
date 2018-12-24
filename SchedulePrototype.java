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
public class SchedulePrototype {
    
    private final StringProperty type;
    private final StringProperty month;
    private final StringProperty day;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    
    public SchedulePrototype(String initType, String initMonth, String initDay, String initTitle, String initTopic, String initLink)
    {
        type= new SimpleStringProperty(initType);
        month = new SimpleStringProperty(initMonth);
        day = new SimpleStringProperty(initDay);
        link = new SimpleStringProperty(initLink);
        topic = new SimpleStringProperty(initTopic);
        title = new SimpleStringProperty(initTitle);
    }
    
    public String getMonth() {
        return month.get();
    }

    public void setMonth(String initMonth) {
        month.set(initMonth);
    } 
    
    public String getDay() {
        return day.get();
    }

    public void setDay(String initDay) {
        day.set(initDay);
    }
    
    public String getLink() {
        return link.get();
    }

    public void setLink(String initLink) {
        link.set(initLink);
    }
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String initTitle) {
        title.set(initTitle);
    }
    
    public String getTopic() {
        return topic.get();
    }

    public void setTopic(String initTopic) {
        topic.set(initTopic);
    }
    
    public String getType() {
        return type.get();
    }

    public void setType(String initType) {
        type.set(initType);
    }
    
}
