/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.collections.ObservableList;

/**
 *
 * @author parisfisher
 */
public class SyllabusData {
    
    String description;
    ObservableList<String> topics;
    String prerequisites;
    ObservableList<String> outcomes;
    ObservableList<TextbookPrototype> textbooks;
    ObservableList<GradedComponentsPrototype> gradedComponents;
    String gradingNote;
    String academicDishonesty;
    String specialAssistance;
    
    public void addOutcome(String initOutcome)
     {
         outcomes.add(initOutcome);
     }
    
    public void addTopic(String initTopic)
     {
         topics.add(initTopic);
     }
    
    public void addTextbook(TextbookPrototype textbook)
     {
         textbooks.add(textbook);
     }
    
    public void addGradedComponent(GradedComponentsPrototype gradedComponent)
     {
         gradedComponents.add(gradedComponent);
     }

    
    public void setDescription(String description)
    {
        this.description=description;
    }
    
    public void setPrerequisites(String prerequisites)
    {
        this.prerequisites=prerequisites;
    }
    
    public void setGradingNote(String gradingNote)
    {
        this.gradingNote=gradingNote;
    }
    
    public void setAcademicDishonesty(String academicDishonesty)
    {
        this.academicDishonesty=academicDishonesty;
    }
    
    public void setSpecialAssistance(String specialAssistance)
    {
        this.specialAssistance=specialAssistance;
    }
 }
