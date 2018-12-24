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
public class HolidaysPrototype {
    
    private final StringProperty type;
    private final StringProperty month;
    private final StringProperty day;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty link;
    
    public HolidaysPrototype(String initMonth, String initDay, String initTitle, String initLink)
    {
        type=new SimpleStringProperty("holiday");
        month = new SimpleStringProperty(initMonth);
        day = new SimpleStringProperty(initDay);
        link = new SimpleStringProperty(initLink);
        title = new SimpleStringProperty(initTitle);
        date = new SimpleStringProperty(month+"/"+day+"/18");
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
    
    public String getType() {
        return type.get();
    }

    public void setType(String initType) {
        type.set(initType);
    }
            
    
}
