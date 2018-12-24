/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;
import javafx.collections.ObservableList;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import csg.CourseSiteGeneratorApp;
import static csg.OfficeHoursPropertyType.ACADEMIC_DISHONESTY_TEXT_AREA;
import static csg.OfficeHoursPropertyType.DESCRIPTION_TEXT_AREA;
import static csg.OfficeHoursPropertyType.EMAIL_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.EXPORT_DIR;
import static csg.OfficeHoursPropertyType.GRADED_COMPONENTS_TEXT_AREA;
import static csg.OfficeHoursPropertyType.GRADING_NOTE_TEXT_AREA;
import static csg.OfficeHoursPropertyType.HOME_CHECKBOX;
import static csg.OfficeHoursPropertyType.HOME_PAGE_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.HWS_CHECKBOX;
import static csg.OfficeHoursPropertyType.INSTRUCTOR_HOURS_TEXT_AREA;
import static csg.OfficeHoursPropertyType.LABS_TABLE;
import static csg.OfficeHoursPropertyType.LECTURES_TABLE;
import static csg.OfficeHoursPropertyType.NAME_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.NUMBER_COMBO_BOX;
import static csg.OfficeHoursPropertyType.OH_ALL_RADIO_BUTTON;
import static csg.OfficeHoursPropertyType.OH_GRAD_RADIO_BUTTON;
import static csg.OfficeHoursPropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static csg.OfficeHoursPropertyType.OUTCOMES_TEXT_AREA;
import static csg.OfficeHoursPropertyType.PREREQUISITES_TEXT_AREA;
import static csg.OfficeHoursPropertyType.RECITATIONS_TABLE;
import static csg.OfficeHoursPropertyType.ROOM_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.SCHEDULE_CHECKBOX;
import static csg.OfficeHoursPropertyType.SCHEDULE_ITEMS_TABLE;
import static csg.OfficeHoursPropertyType.SEMESTER_COMBO_BOX;
import static csg.OfficeHoursPropertyType.SPECIAL_ASSISTANCE_TEXT_AREA;
import static csg.OfficeHoursPropertyType.SUBJECT_COMBO_BOX;
import static csg.OfficeHoursPropertyType.SYLLABUS_CHECKBOX;
import static csg.OfficeHoursPropertyType.TEXTBOOKS_TEXT_AREA;
import static csg.OfficeHoursPropertyType.TITLE_COMBO_BOX;
import static csg.OfficeHoursPropertyType.TOPICS_TEXT_AREA;
import static csg.OfficeHoursPropertyType.YEAR_COMBO_BOX;
import csg.data.TimeSlot.DayOfWeek;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 *
 * @author parisfisher
 */
public class CourseSiteGeneratorData implements AppDataComponent{
    
    //Meeting Times Data
    ObservableList<MeetingTimesPrototype> mtRecitations;
    ObservableList<MeetingTimesPrototype> mtLabs;
    ObservableList<LecturesPrototype> mtLectures;
    ComboBox subjectComboBox;
    ComboBox numberComboBox;
    String exportDir;
    String cssFile;
    
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();

        // SETUP THE DATA STRUCTURES
        allTAs = new HashMap();
        allTAs.put(TAType.Graduate, new ArrayList());
        allTAs.put(TAType.Undergraduate, new ArrayList());
        
        ComboBox subjectCombo=(ComboBox)gui.getGUINode(SUBJECT_COMBO_BOX);
        subjectComboBox=subjectCombo;
        subjectCombo.getItems().clear();
        
        ComboBox numberCombo=(ComboBox)gui.getGUINode(NUMBER_COMBO_BOX);
        numberComboBox=numberCombo;
        numberCombo.getItems().clear();
        // GET THE LIST OF TAs FOR THE LEFT TABLE
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        teachingAssistants = taTableView.getItems();
        
        TableView<MeetingTimesPrototype> mtRecitationsTableView= (TableView)gui.getGUINode(RECITATIONS_TABLE);
        mtRecitations=mtRecitationsTableView.getItems();
        
        TableView<MeetingTimesPrototype> mtLabsTableView= (TableView)gui.getGUINode(LABS_TABLE);
        mtLabs=mtLabsTableView.getItems();
        
        TableView<LecturesPrototype> mtLecturesTableView= (TableView)gui.getGUINode(LECTURES_TABLE);
        mtLectures=mtLecturesTableView.getItems();
        
        TableView<SchedulePrototype> scheduleItemsTableView= (TableView)gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        scheduleItems=scheduleItemsTableView.getItems();
        
        logos=new ArrayList();
        pages=new ArrayList();
        instructor=new InstructorPrototype(" ", " ", " ", " ", " ");
        exportDir="";
        cssFile="sea_wolf.css";


        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        
        resetOfficeHours();
    }
    
    public String getCssFile()
    {
        return cssFile;
    }
    
    public void setCssFile(String newCssFile)
    {
        cssFile=newCssFile;
    }
    public ComboBox getSubjectComboBox()
    {
        return subjectComboBox;
    }
    
    public ComboBox getNumberComboBox()
    {
        return numberComboBox;
    }
        
    
    public void addToSubjectComboBox(String s)
    {
        if(!subjectComboBox.getItems().contains(s))
        {
        subjectComboBox.getItems().add(s);
        }
    }
    
    public void addToNumberComboBox(String s)
    {
        if(!numberComboBox.getItems().contains(s))
        {
        numberComboBox.getItems().add(s);
        }
    }
    
    public void editExportDir(String newExportDir)
    {
        exportDir=newExportDir;
         AppGUIModule gui = app.getGUIModule();
        Label exportLabel=(Label) gui.getGUINode(EXPORT_DIR);
        exportLabel.setText(exportDir);
    }
    
    public String getExportDir()
    {
        return exportDir;
    }
     public void addRecitation(MeetingTimesPrototype recitation)
    {
        mtRecitations.add(recitation);
    }
    
     public void removeRecitation(MeetingTimesPrototype recitation)
    {
        mtRecitations.remove(recitation);
    }
     
    public void addLab(MeetingTimesPrototype lab)
    {
       mtLabs.add(lab);
    }
    
    public void removeLab(MeetingTimesPrototype lab)
    {
       mtLabs.remove(lab);
    }
    
    public void addLecture(LecturesPrototype lecture)
    {
        mtLectures.add(lecture);
    }
    
    public void removeLecture(LecturesPrototype lecture)
    {
        mtLectures.remove(lecture);
    }
    
    public ObservableList<MeetingTimesPrototype> getRecitations()
    {
        return mtRecitations;
    }
    
    public ObservableList<MeetingTimesPrototype> getLab()
    {
        return mtLabs;
    }
    
    public ObservableList<LecturesPrototype> getLectures()
    {
        return mtLectures;
    }
    
    CourseSiteGeneratorApp app;
    
    // THESE ARE ALL THE TEACHING ASSISTANTS
    HashMap<TAType, ArrayList<TeachingAssistantPrototype>> allTAs;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistantPrototype> teachingAssistants;
    ObservableList<TimeSlot> officeHours;    

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;
    
    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 20;

    /**
     * This constructor will setup the required data structures for
     * use, but will have to wait on the office hours grid, since
     * it receives the StringProperty objects from the Workspace.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    
    
    // ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    
    // PRIVATE HELPER METHODS
    
    private void sortTAs() {
        Collections.sort(teachingAssistants);
    }
    
    private void resetOfficeHours() {
        //THIS WILL STORE OUR OFFICE HOURS
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        
        
        for (int i = startHour; i <= endHour; i++) {
            TimeSlot timeSlot = new TimeSlot(   this.getTimeString(i, true),
                                                this.getTimeString(i, false));
            officeHours.add(timeSlot);
            
            TimeSlot halfTimeSlot = new TimeSlot(   this.getTimeString(i, false),
                                                    this.getTimeString(i+1, true));
            officeHours.add(halfTimeSlot);
        }
    }
    
    private String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    // METHODS TO OVERRIDE
        
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    @Override
    public void reset() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        teachingAssistants.clear();
        mtRecitations.clear();
        mtLabs.clear();
        mtLectures.clear();
        scheduleItems.clear();
        logos.clear();
        pages.clear();
        
        for (TimeSlot timeSlot : officeHours) {
            timeSlot.reset();
        }
    }
    
    // SERVICE METHODS
    
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if (initStartHour <= initEndHour) {
            // THESE ARE VALID HOURS SO KEEP THEM
            // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
            startHour = initStartHour;
            endHour = initEndHour;
        }
        resetOfficeHours();
    }
    
    public void addTA(TeachingAssistantPrototype ta) {
        if (!hasTA(ta)) {
            TAType taType = TAType.valueOf(ta.getType());
            ArrayList<TeachingAssistantPrototype> tas = allTAs.get(taType);
            tas.add(ta);
            this.updateTAs();
        }
    }

    public void addTA(TeachingAssistantPrototype ta, HashMap<TimeSlot, ArrayList<DayOfWeek>> officeHours) {
        addTA(ta);
        for (TimeSlot timeSlot : officeHours.keySet()) {
            ArrayList<DayOfWeek> days = officeHours.get(timeSlot);
            for (DayOfWeek dow : days) {
                timeSlot.addTA(dow, ta);
            }
        }
    }
    
    public void removeTA(TeachingAssistantPrototype ta) {
        // REMOVE THE TA FROM THE LIST OF TAs
        TAType taType = TAType.valueOf(ta.getType());
        allTAs.get(taType).remove(ta);
        
        // REMOVE THE TA FROM ALL OF THEIR OFFICE HOURS
        for (TimeSlot timeSlot : officeHours) {
            timeSlot.removeTA(ta);
        }
        
        // AND REFRESH THE TABLES
        this.updateTAs();
    }

    public void removeTA(TeachingAssistantPrototype ta, HashMap<TimeSlot, ArrayList<DayOfWeek>> officeHours) {
        removeTA(ta);
        for (TimeSlot timeSlot : officeHours.keySet()) {
            ArrayList<DayOfWeek> days = officeHours.get(timeSlot);
            for (DayOfWeek dow : days) {
                timeSlot.removeTA(dow, ta);
            }
        }    
    }
    
    public DayOfWeek getColumnDayOfWeek(int columnNumber) {
        return TimeSlot.DayOfWeek.values()[columnNumber-2];
    }

    public TeachingAssistantPrototype getTAWithName(String name) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getName().equals(name))
                return ta;
        }
        return null;
    }

    public TeachingAssistantPrototype getTAWithEmail(String email) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getEmail().equals(email))
                return ta;
        }
        return null;
    }

    public TimeSlot getTimeSlot(String startTime) {
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime().replace(":", "_");
            if (timeSlotStartTime.equals(startTime))
                return timeSlot;
        }
        return null;
    }

    public TAType getSelectedType() {
        RadioButton allRadio = (RadioButton)app.getGUIModule().getGUINode(OH_ALL_RADIO_BUTTON);
        if (allRadio.isSelected())
            return TAType.All;
        RadioButton gradRadio = (RadioButton)app.getGUIModule().getGUINode(OH_GRAD_RADIO_BUTTON);
        if (gradRadio.isSelected())
            return TAType.Graduate;
        else
            return TAType.Undergraduate;
    }

    public TeachingAssistantPrototype getSelectedTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> tasTable = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem();
    }
    
    public HashMap<TimeSlot, ArrayList<DayOfWeek>> getTATimeSlots(TeachingAssistantPrototype ta) {
        HashMap<TimeSlot, ArrayList<DayOfWeek>> timeSlots = new HashMap();
        for (TimeSlot timeSlot : officeHours) {
            if (timeSlot.hasTA(ta)) {
                ArrayList<DayOfWeek> daysForTA = timeSlot.getDaysForTA(ta);
                timeSlots.put(timeSlot, daysForTA);
            }
        }
        return timeSlots;
    }
    
    private boolean hasTA(TeachingAssistantPrototype testTA) {
        return allTAs.get(TAType.Graduate).contains(testTA)
                ||
                allTAs.get(TAType.Undergraduate).contains(testTA);
    }
    
    public boolean isTASelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView tasTable = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem() != null;
    }

    public boolean isLegalNewTA(String name, String email) {
        if ((name.trim().length() > 0)
                && (email.trim().length() > 0)) {
            // MAKE SURE NO TA ALREADY HAS THE SAME NAME
            TAType type = this.getSelectedType();
            TeachingAssistantPrototype testTA = new TeachingAssistantPrototype(name, email, type);
            if (this.teachingAssistants.contains(testTA))
                return false;
            if (this.isLegalNewEmail(email)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isLegalNewName(String testName) {
        if (testName.trim().length() > 0) {
            for (TeachingAssistantPrototype testTA : this.teachingAssistants) {
                if (testTA.getName().equals(testName))
                    return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean isLegalNewEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if (matcher.find()) {
            for (TeachingAssistantPrototype ta : this.teachingAssistants) {
                if (ta.getEmail().equals(email.trim()))
                    return false;
            }
            return true;
        }
        else return false;
    }
    
    public boolean isDayOfWeekColumn(int columnNumber) {
        return columnNumber >= 2;
    }
    
    public boolean isTATypeSelected() {
        AppGUIModule gui = app.getGUIModule();
        RadioButton allRadioButton = (RadioButton)gui.getGUINode(OH_ALL_RADIO_BUTTON);
        return !allRadioButton.isSelected();
    }
    
    public boolean isValidTAEdit(TeachingAssistantPrototype taToEdit, String name, String email) {
        if (!taToEdit.getName().equals(name)) {
            if (!this.isLegalNewName(name))
                return false;
        }
        if (!taToEdit.getEmail().equals(email)) {
            if (!this.isLegalNewEmail(email))
                return false;
        }
        return true;
    }

    public boolean isValidNameEdit(TeachingAssistantPrototype taToEdit, String name) {
        if (!taToEdit.getName().equals(name)) {
            if (!this.isLegalNewName(name))
                return false;
        }
        return true;
    }

    public boolean isValidEmailEdit(TeachingAssistantPrototype taToEdit, String email) {
        if (!taToEdit.getEmail().equals(email)) {
            if (!this.isLegalNewEmail(email))
                return false;
        }
        return true;
    }    

    public void updateTAs() {
        TAType type = getSelectedType();
        selectTAs(type);
    }
    
    public void selectTAs(TAType type) {
        teachingAssistants.clear();
        Iterator<TeachingAssistantPrototype> tasIt = this.teachingAssistantsIterator();
        while (tasIt.hasNext()) {
            TeachingAssistantPrototype ta = tasIt.next();
            if (type.equals(TAType.All)) {
                teachingAssistants.add(ta);
            }
            else if (ta.getType().equals(type.toString())) {
                teachingAssistants.add(ta);
            }
        }
        
        // SORT THEM BY NAME
        sortTAs();

        // CLEAR ALL THE OFFICE HOURS
        Iterator<TimeSlot> officeHoursIt = officeHours.iterator();
        while (officeHoursIt.hasNext()) {
            TimeSlot timeSlot = officeHoursIt.next();
            timeSlot.filter(type);
        }
        
        app.getFoolproofModule().updateAll();
    }
    
    public Iterator<TimeSlot> officeHoursIterator() {
        return officeHours.iterator();
    }

    public Iterator<TeachingAssistantPrototype> teachingAssistantsIterator() {
        return new AllTAsIterator();
    }
    
    private class AllTAsIterator implements Iterator {
        Iterator gradIt = allTAs.get(TAType.Graduate).iterator();
        Iterator undergradIt = allTAs.get(TAType.Undergraduate).iterator();

        public AllTAsIterator() {}
        
        @Override
        public boolean hasNext() {
            if (gradIt.hasNext() || undergradIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (gradIt.hasNext())
                return gradIt.next();
            else if (undergradIt.hasNext())
                return undergradIt.next();
            else
                return null;
        }
    }
    
    //Schedule Data
    String startingMondayMonth;
    String startingMondayDay;
    String endingFridayMonth;
    String endingFridayDay;
    
    
    ObservableList<SchedulePrototype> scheduleItems;

    public String getStartingMondayMonth()
    {
        return startingMondayMonth;
    }
    
    public String getStartingMondayDay()
    {
        return startingMondayDay;
    }
    
    public String getEndingFridayMonth()
    {
        return endingFridayMonth;
    }
    
    public String getEndingFridayDay()
    {
        return endingFridayDay;
    }
    
    public void addScheduleItem(SchedulePrototype schedulePrototype)
    {
        scheduleItems.add(schedulePrototype);
        
    }
    
    public void removeScheduleItem(SchedulePrototype schedulePrototype)
    {
        scheduleItems.remove(schedulePrototype);
    }
    public ObservableList<SchedulePrototype> getScheduleItems()
    {
        return scheduleItems;
    }
    
    public void setStartingMondayMonth(String initStartingMondayMonth)
    {
        startingMondayMonth= initStartingMondayMonth;
    }
    
    
    public void setStartingMondayDay(String initStartingMondayDay)
    {
        startingMondayDay= initStartingMondayDay;
    }
    
   
    
    public void setEndingFridayMonth(String initEndingFridayMonth)
    {
        endingFridayMonth= initEndingFridayMonth;
    }
    
    
    
    public void setEndingFridayDay(String initEndingFridayDay)
    {
        endingFridayDay= initEndingFridayDay;
    }
    
   
    
    //Site Data
    String subject;
    String number;
    String semester;
    String year;
    String title; 
    InstructorPrototype instructor;
    ArrayList<LogoPrototype> logos;
    ArrayList<PagesPrototype> pages;
    
    public String getSubject()
    {
        return subject;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getSemester()
    {
        return semester;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public InstructorPrototype getInstructor()
    {
        return instructor;
    }
    
    public void setTitle(String title)
    {
        this.title=title;
        AppGUIModule gui = app.getGUIModule();
        ComboBox comboBox=((ComboBox) gui.getGUINode(TITLE_COMBO_BOX));
        comboBox.getItems().add(title);
        comboBox.getSelectionModel().select(title);

    }
    public void setYear(String year)
    {
        this.year=year;
        AppGUIModule gui = app.getGUIModule();
        ComboBox comboBox=((ComboBox) gui.getGUINode(YEAR_COMBO_BOX));
        comboBox.getSelectionModel().select(year);
    }
    public void setSemester(String semester)
    {
        this.semester=semester;
        AppGUIModule gui = app.getGUIModule();
        ComboBox comboBox=((ComboBox) gui.getGUINode(SEMESTER_COMBO_BOX));
        comboBox.getSelectionModel().select(semester);
    }
    
    public void setNumber(String number)
    {
        this.number=number;
        AppGUIModule gui = app.getGUIModule();
        ComboBox comboBox=((ComboBox) gui.getGUINode(NUMBER_COMBO_BOX));
        comboBox.getItems().add(number);
        comboBox.getSelectionModel().select(number);
    }
    
    public void setSubject(String subject)
    {
        this.subject=subject;
        AppGUIModule gui = app.getGUIModule();
        ComboBox comboBox=((ComboBox) gui.getGUINode(SUBJECT_COMBO_BOX));
        comboBox.getItems().add(subject);
        comboBox.getSelectionModel().select(subject);
        
    }
    
    public ArrayList<LogoPrototype> getLogos()
    {
        return logos;
    }
    
    public void addLogo(LogoPrototype logo)
    {
        logos.add(logo);
    }
     
     public void setInstructor(InstructorPrototype instructor)
     {
         this.instructor=instructor;

     }
     public void loadInstructor()
     {
         AppGUIModule gui = app.getGUIModule();
         TextField nameTextField=((TextField) gui.getGUINode(NAME_TEXT_FIELD));
         nameTextField.setText(instructor.getName());
         TextField emailTextField=((TextField) gui.getGUINode(EMAIL_TEXT_FIELD));
         emailTextField.setText(instructor.getEmail());
         TextField roomTextField=((TextField) gui.getGUINode(ROOM_TEXT_FIELD));
         roomTextField.setText(instructor.getRoom());
         TextField homeTextField=((TextField) gui.getGUINode(HOME_PAGE_TEXT_FIELD));
         homeTextField.setText(instructor.getLink());
         TextArea hoursTextArea=((TextArea) gui.getGUINode(INSTRUCTOR_HOURS_TEXT_AREA));
         hoursTextArea.setText((instructor.getHours()));
     }
     
     public void addPages(PagesPrototype initpage)
     {
         pages.add(initpage);
         AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox=((CheckBox) gui.getGUINode(HOME_CHECKBOX));
        CheckBox syllabusCheckBox=((CheckBox) gui.getGUINode(SYLLABUS_CHECKBOX));
        CheckBox scheduleCheckBox=((CheckBox) gui.getGUINode(SCHEDULE_CHECKBOX));
        CheckBox hwsCheckBox=((CheckBox) gui.getGUINode(HWS_CHECKBOX));
         Iterator pagesIterator=pages.iterator();
         while(pagesIterator.hasNext())
         {
             PagesPrototype page=(PagesPrototype)pagesIterator.next();
             if(page.getName().equals("Home"))
             {
                 homeCheckBox.setSelected(true);
             }
             else if(page.getName().equals("Syllabus"))
             {
                 syllabusCheckBox.setSelected(true);
             }
             else if(page.getName().equals("Schedule"))
             {
                 scheduleCheckBox.setSelected(true);
             }
             else if(page.getName().equals("HWs"))
             {
                 hwsCheckBox.setSelected(true);
             }
         }
     }
     
     public void setPages(ArrayList<PagesPrototype> pages)
     {
        this.pages=pages;
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox=((CheckBox) gui.getGUINode(HOME_CHECKBOX));
        CheckBox syllabusCheckBox=((CheckBox) gui.getGUINode(SYLLABUS_CHECKBOX));
        CheckBox scheduleCheckBox=((CheckBox) gui.getGUINode(SCHEDULE_CHECKBOX));
        CheckBox hwsCheckBox=((CheckBox) gui.getGUINode(HWS_CHECKBOX));
         Iterator pagesIterator=this.pages.iterator();
         while(pagesIterator.hasNext())
         {
             PagesPrototype page=(PagesPrototype)pagesIterator.next();
             if(page.getName().equals("Home"))
             {
                 homeCheckBox.setSelected(true);
             }
             else if(page.getName().equals("Syllabus"))
             {
                 syllabusCheckBox.setSelected(true);
             }
             else if(page.getName().equals("Schedule"))
             {
                 scheduleCheckBox.setSelected(true);
             }
             else if(page.getName().equals("HWs"))
             {
                 hwsCheckBox.setSelected(true);
             }
         }
     }
         
     public ArrayList<PagesPrototype> getPages()
     {
         return pages;
     }
    
    
     //Syllabus Data
    String description;
    String topics;
    String prerequisites;
    String outcomes;
    String textbooks;
    String gradedComponents;
    String gradingNote;
    String academicDishonesty;
    String specialAssistance;
    
    public String getDescription()
    {
        return description;
    }
    
    public String getTopics()
    {
        return topics;
    }
    
    public String getPrerequisites()
    {
        return prerequisites;
    }
    
    public String getOutcomes()
    {
        return outcomes;
    }
    
    public String getTextbooks()
    {
        return textbooks;
    }
    
    public String getGradedComponents()
    {
        return gradedComponents;
    }
    
    public String getGradingNote()
    {
        return gradingNote;
    }
    
    public String getAcademicDishonesty()
    {
        return academicDishonesty;
    }
    
    public String getSpecialAssistance()
    {
        return specialAssistance;
    }
    
    public void setTopics(String topics)
    {
        this.topics=topics;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(TOPICS_TEXT_AREA));
        textArea.setText(this.topics);
        
    }
    
    public void setOutcomes(String outcomes)
    {
        this.outcomes=outcomes;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(OUTCOMES_TEXT_AREA));
        textArea.setText(this.outcomes);
    }
    
    public void setTextbooks(String textbooks)
    {
        this.textbooks=textbooks;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(TEXTBOOKS_TEXT_AREA));
        textArea.setText(this.textbooks);
    }
    
    public void setGradedComponents(String gradedComponents)
    {
        this.gradedComponents=gradedComponents;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(GRADED_COMPONENTS_TEXT_AREA));
        textArea.setText(this.gradedComponents);
    }
    
    public void setDescription(String description)
    {
        this.description=description;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(DESCRIPTION_TEXT_AREA));
        textArea.setText(this.description);
    }
    
    public void setPrerequisites(String prerequisites)
    {
        this.prerequisites=prerequisites;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(PREREQUISITES_TEXT_AREA));
        textArea.setText(this.prerequisites);
    }
    
    public void setGradingNote(String gradingNote)
    {
        this.gradingNote=gradingNote;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(GRADING_NOTE_TEXT_AREA));
        textArea.setText(this.gradingNote);
    }
    
    public void setAcademicDishonesty(String academicDishonesty)
    {
        this.academicDishonesty=academicDishonesty;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(ACADEMIC_DISHONESTY_TEXT_AREA));
        textArea.setText(this.academicDishonesty);
    }
    
    public void setSpecialAssistance(String specialAssistance)
    {
        this.specialAssistance=specialAssistance;
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea=((TextArea) gui.getGUINode(SPECIAL_ASSISTANCE_TEXT_AREA));
        textArea.setText(this.specialAssistance);
    }
}
