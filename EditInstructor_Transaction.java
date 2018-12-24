package csg.transactions;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import java.time.LocalDate;
import jtps.jTPS_Transaction;
import csg.data.InstructorPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class EditInstructor_Transaction implements jTPS_Transaction {
    
    CourseSiteGeneratorApp app;
    
    String oldName, newName;
    String oldEmail, newEmail;
    String oldHours, newHours;
    String oldRoom, newRoom;
    String oldHome, newHome;
    
    public EditInstructor_Transaction(CourseSiteGeneratorApp initApp, InstructorPrototype oldInstructor, String name, String room, String email, String home, String hours) {
        app = initApp;
        oldName = oldInstructor.getName();
        oldEmail = oldInstructor.getEmail();
        oldRoom = oldInstructor.getRoom();
        oldHours = oldInstructor.getHours();
        oldHome = oldInstructor.getLink();
        newName = name;
        newEmail = email;
        newHours=hours;
        newRoom=room;
        newHome=home;
        
    }


    @Override
    public void doTransaction() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        InstructorPrototype newInstructor=new InstructorPrototype(newName, newRoom, newEmail, newHome, newHours);
        data.setInstructor(newInstructor);
    }

    @Override
    public void undoTransaction() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        InstructorPrototype oldInstructor=new InstructorPrototype(oldName, oldRoom, oldEmail, oldHome, oldHours);
        data.setInstructor(oldInstructor);
    }
}