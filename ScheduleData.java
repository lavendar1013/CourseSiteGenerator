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
public class ScheduleData {
    
    String startingMondayMonth;
    String startingMondayDay;
    String endingFridayMonth;
    String endingFridayDay;
    
    ObservableList<HolidaysPrototype> holidays;
    ObservableList<SchedulePrototype> lectures;
    ObservableList<SchedulePrototype> references;
    ObservableList<SchedulePrototype> recitations;
    ObservableList<HomeworkPrototype> hws;
    
    public void addHoliday(HolidaysPrototype holiday)
    {
        holidays.add(holiday);
    }
    public void addLecture(SchedulePrototype lecture)
    {
       lectures.add(lecture);
    }
    
    public void addReference(SchedulePrototype reference)
    {
        references.add(reference);
    }
    
    public void addRecitation(SchedulePrototype recitation)
    {
        recitations.add(recitation);
    }
    
    public void addHW(HomeworkPrototype hw)
    {
        hws.add(hw);
    }
    
    public void setStartingMondayMonth(String initStartingMondayMonth)
    {
        startingMondayMonth= initStartingMondayMonth;
    }
    
    public String getStartingMondayMonth()
    {
        return startingMondayMonth;
    }
    
    public void setStartingMondayDay(String initStartingMondayDay)
    {
        startingMondayDay= initStartingMondayDay;
    }
    
    public String getStartingMondayDay()
    {
        return startingMondayDay;
    }
    
    public void setEndingFridayMonth(String initEndingFridayMonth)
    {
        endingFridayMonth= initEndingFridayMonth;
    }
    
    public String getEndingFridayMonth()
    {
        return endingFridayMonth;
    }
    
    public void setEndingFridayDay(String initEndingFridayDay)
    {
        endingFridayDay= initEndingFridayDay;
    }
    
    public String getEndingFridayDay()
    {
        return endingFridayDay;
    }
    
}
