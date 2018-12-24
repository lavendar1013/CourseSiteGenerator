/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;

/**
 *
 * @author parisfisher
 */
public class SiteData implements AppDataComponent {
    
    CourseSiteGeneratorApp app;
    String subject;
    String number;
    String semester;
    String year;
    String title; 
    InstructorPrototype instructor;
    ObservableList<LogoPrototype> logos;
    ObservableList<PagesPrototype> pages;
    
    
    public SiteData(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();

        
        
        reset();
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setYear(String year)
    {
        this.year=year;
    }
    public void setSemester(String semester)
    {
        this.semester=semester;
    }
    
    public void setNumber(String number)
    {
        this.number=number;
    }
    
    public void setSubject(String subject)
    {
        this.subject=subject;
    }
    
    public void addLogo(LogoPrototype logo)
    {
        logos.add(logo);
    }
    
    @Override
     public void reset() {
        //THIS WILL STORE OUR OFFICE HOURS
        AppGUIModule gui = app.getGUIModule();
        logos.clear();
        pages.clear();
        instructor=null;
        subject=null;
        semester=null;
        year=null;
        title=null;
    }
     
     public void setInstructor(InstructorPrototype instructor)
     {
         this.instructor=instructor;
     }
     
     public void addPages(PagesPrototype page)
     {
         pages.add(page);
     }
}
