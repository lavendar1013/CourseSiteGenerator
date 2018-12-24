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
public class MeetingTimesData {
    
    ObservableList<MeetingTimesPrototype> recitations;
    ObservableList<MeetingTimesPrototype> labs;
    ObservableList<LecturesPrototype> lectures;
    
     public void addRecitation(MeetingTimesPrototype recitation)
    {
        recitations.add(recitation);
    }
    public void addLab(MeetingTimesPrototype lab)
    {
       labs.add(lab);
    }
    
    public void addLecture(LecturesPrototype lecture)
    {
        lectures.add(lecture);
    }
    
}
