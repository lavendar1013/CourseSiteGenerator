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
public class InstructorPrototype {
    private final StringProperty name;
    private final StringProperty link;
    private final StringProperty email;
    private final StringProperty room;
    private final StringProperty hours;
    
    public InstructorPrototype(String initName, String initLink, String initEmail, String initRoom,  String initHours) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        link = new SimpleStringProperty(initLink);
        room = new SimpleStringProperty(initRoom);
        hours = new SimpleStringProperty(initHours);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }
    
    public String getLink() {
        return link.get();
    }

    public void setLink(String initLink) {
        link.set(initLink);
    }
    
    public String getRoom() {
        return room.get();
    }

    public void setRoom(String initRoom) {
        room.set(initRoom);
    }
    

    
    public String getHours() {
        return hours.get();
    }

    public void setHours(String initHours) {
        hours.set(initHours);
    }
}
