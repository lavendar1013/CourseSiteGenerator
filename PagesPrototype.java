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
public class PagesPrototype {
    private final StringProperty name;
    private final StringProperty link;
    
    public PagesPrototype(String initName, String initLink)
    {
        name= new SimpleStringProperty(initName);
        link = new SimpleStringProperty(initLink);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    
    public String getLink() {
        return link.get();
    }

    public void setLink(String initLink) {
        link.set(initLink);
    }
}
