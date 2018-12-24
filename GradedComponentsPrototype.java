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
public class GradedComponentsPrototype {
    private final StringProperty name;
    private final StringProperty description; 
    private final StringProperty weight;
    
    public GradedComponentsPrototype(String initName, String initDescription, String initWeight)
    {
        name= new SimpleStringProperty(initName);
        description= new SimpleStringProperty(initDescription);
        weight= new SimpleStringProperty(initWeight);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String initDescription) {
        description.set(initDescription);
    }
    
    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String initWeight) {
        weight.set(initWeight);
    }
        
    
}
