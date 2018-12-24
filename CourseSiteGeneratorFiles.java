package csg.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.GradedComponentsPrototype;
import csg.data.HolidaysPrototype;
import csg.data.HomeworkPrototype;
import csg.data.InstructorPrototype;
import csg.data.LecturesPrototype;
import csg.data.LogoPrototype;
import csg.data.MeetingTimesData;
import csg.data.MeetingTimesPrototype;
import csg.data.OfficeHoursData;
import csg.data.PagesPrototype;
import csg.data.ScheduleData;
import csg.data.SchedulePrototype;
import csg.data.SiteData;
import csg.data.SyllabusData;
import csg.data.TAType;
import csg.data.TeachingAssistantPrototype;
import csg.data.TextbookPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import static djf.AppPropertyType.APP_EXPORT_PAGE;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import properties_manager.PropertiesManager;

/**
 * This class serves as the file component for the TA
 * manager app. It provides all saving and loading 
 * services for the application.
 * 
 * @author Richard McKenna
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
    // THIS IS THE APP ITSELF
    CourseSiteGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_TYPE = "type";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_MONDAY = "monday";
    static final String JSON_TUESDAY = "tuesday";
    static final String JSON_WEDNESDAY = "wednesday";
    static final String JSON_THURSDAY = "thursday";
    static final String JSON_FRIDAY = "friday";
    static final String JSON_DESCRIPTION = "description";
    static final String JSON_TOPICS = "topics";
    static final String JSON_PREREQUISITES = "prerequisites";
    static final String JSON_OUTCOMES = "outcomes";
    static final String JSON_TEXTBOOKS = "textbooks";
    static final String JSON_GRADED_COMPONENTS = "gradedComponents";
    static final String JSON_WEIGHT = "weight";
    static final String JSON_GRADING_NOTE = "gradingNote";
    static final String JSON_ACADEMIC_DISHONESTY = "academicDishonesty";
    static final String JSON_SPECIAL_ASSISTANCE = "specialAssistance";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_ROOM = "room";
    static final String JSON_LINK = "link";
    static final String JSON_PHOTO = "photo";
    static final String JSON_PAGES = "pages";
    static final String JSON_HREF = "href";
    static final String JSON_HOURS = "hours";
    static final String JSON_SRC = "src";
    static final String JSON_BOTTOM_RIGHT = "bottom_right";
    static final String JSON_BOTTOM_LEFT = "bottom_left";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_FAVICON = "favicon";
    static final String JSON_LOGOS = "logos";
    static final String JSON_TITLE = "title";
    static final String JSON_YEAR = "year";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_STARTING_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_STARTING_MONDAY_DAY = "startingMondayDay";
    static final String JSON_ENDING_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_ENDING_FRIDAY_DAY = "endingFridayDay";
    static final String JSON_MONTH = "month";
    static final String JSON_LECTURES= "lectures";
    static final String JSON_TOPIC = "topic";
   
    static final String JSON_REFERENCES = "references";
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_DAY = "day";
    static final String JSON_HWS = "hws";
    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_CRITERIA = "criteria";
    static final String JSON_SCHEDULE_ITEM = "schedule_item";
    static final String JSON_DATE = "date";
    static final String JSON_SECTION = "section";
    static final String JSON_DAYS= "days";
    static final String JSON_TIME = "time";
    static final String JSON_LABS = "labs";
    static final String JSON_DAY_TIME = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA_1 = "ta_1";
    static final String JSON_TA_2 = "ta_2";
    static final String JSON_AUTHORS = "authors";
    static final String JSON_PUBLISHER = "publisher";
    static final String JSON_SITE_TAB = "site_tab";
    static final String JSON_SYLLABUS_TAB = "syllabus_tab";
    static final String JSON_OFFICE_HOURS_TAB = "office_hours_tab";
    static final String JSON_SCHEDULE_TAB = "schedule_tab";
    static final String JSON_SECTIONS_TAB = "sections_tab";
    static final String JSON_COMBO_BOXES = "combo_boxes";
    
    

    public CourseSiteGeneratorFiles(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        dataManager.reset();

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject jsonfile = loadJSONFile(filePath);
        JsonObject json= jsonfile.getJsonObject(JSON_OFFICE_HOURS_TAB);
	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);
        
        // LOAD ALL THE GRAD TAs
        loadTAs(dataManager, json, JSON_GRAD_TAS);
        loadTAs(dataManager, json, JSON_UNDERGRAD_TAS);

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String startTime = jsonOfficeHours.getString(JSON_START_TIME);
            DayOfWeek dow = DayOfWeek.valueOf(jsonOfficeHours.getString(JSON_DAY_OF_WEEK));
            String name = jsonOfficeHours.getString(JSON_NAME);
            TeachingAssistantPrototype ta = dataManager.getTAWithName(name);
            TimeSlot timeSlot = dataManager.getTimeSlot(startTime);
            timeSlot.toggleTA(dow, ta);
        }
        
        JsonObject jsonSchedule=jsonfile.getJsonObject(JSON_SCHEDULE_TAB);
        loadSchedule(dataManager, jsonSchedule);
        JsonObject jsonMeetingTimes=jsonfile.getJsonObject(JSON_SECTIONS_TAB);
        loadMeetingTimes(dataManager, jsonMeetingTimes);
        JsonObject jsonSyllabus=jsonfile.getJsonObject(JSON_SYLLABUS_TAB);
        loadSyllabus(dataManager, jsonSyllabus);
        JsonObject jsonSite=jsonfile.getJsonObject(JSON_SITE_TAB);
        loadSite(dataManager, jsonSite);
        JsonObject jsonComboBoxes=jsonfile.getJsonObject(JSON_COMBO_BOXES);
        loadComboBoxes(dataManager, jsonComboBoxes);
        
        
        
    }
    
    private void loadComboBoxes(CourseSiteGeneratorData data, JsonObject json)
    {
        JsonArray jsonSubjectArray=json.getJsonArray(JSON_SUBJECT);
        for(int i = 0; i<jsonSubjectArray.size();i++)
        {
            String s=jsonSubjectArray.getString(i);
            data.addToSubjectComboBox(s);
        }
        JsonArray jsonNumberArray=json.getJsonArray(JSON_NUMBER);
        for(int i = 0; i<jsonNumberArray.size();i++)
        {
            String s=jsonNumberArray.getString(i);
            data.addToNumberComboBox(s);
        }
        
    }
    
    private void loadTAs(CourseSiteGeneratorData data, JsonObject json, String tas) {
        JsonArray jsonTAArray = json.getJsonArray(tas);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TAType type=null;
            if(tas.equals(JSON_GRAD_TAS))
            {
                
                type=type.Graduate;

            }
            else
            {
                
                type=type.Undergraduate;
            }
            
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, type);
            data.addTA(ta);
        }     
    }
    private void loadSchedule(CourseSiteGeneratorData data, JsonObject json)
    {
        String startingMondayMonth= json.getString(JSON_STARTING_MONDAY_MONTH);
        data.setStartingMondayMonth(startingMondayMonth);
        String startingMondayDay= json.getString(JSON_STARTING_MONDAY_DAY);
        data.setStartingMondayDay(startingMondayDay);
        String endingFridayMonth= json.getString(JSON_ENDING_FRIDAY_MONTH);
        data.setEndingFridayMonth(endingFridayMonth);
        String endingFridayDay= json.getString(JSON_ENDING_FRIDAY_DAY);
        data.setEndingFridayDay(endingFridayDay);
        
        JsonArray jsonHolidayArray= json.getJsonArray(JSON_HOLIDAYS);
        for(int i=0; i<jsonHolidayArray.size();i++)
        {
            JsonObject jsonHoliday= jsonHolidayArray.getJsonObject(i);
            String month = jsonHoliday.getString(JSON_MONTH);
            String day = jsonHoliday.getString(JSON_DAY);
            String title= jsonHoliday.getString(JSON_TITLE);
            String link= jsonHoliday.getString(JSON_LINK);
            SchedulePrototype holiday=new SchedulePrototype("holidays", month, day, title, "", link);
            data.addScheduleItem(holiday);
        }
        
        JsonArray jsonLecturesArray= json.getJsonArray(JSON_LECTURES);
        for(int i=0; i<jsonLecturesArray.size();i++)
        {
            JsonObject jsonLecture= jsonLecturesArray.getJsonObject(i);
            String month = jsonLecture.getString(JSON_MONTH);
            String day = jsonLecture.getString(JSON_DAY);
            String title= jsonLecture.getString(JSON_TITLE);
            String topic= jsonLecture.getString(JSON_TOPIC);
            String link= jsonLecture.getString(JSON_LINK);
            SchedulePrototype lecture= new SchedulePrototype("lectures", month, day, title, topic, link);
            data.addScheduleItem(lecture);
        }
        
        JsonArray jsonReferencesArray= json.getJsonArray(JSON_REFERENCES);
        for(int i=0; i<jsonReferencesArray.size();i++)
        {
            JsonObject jsonReference= jsonReferencesArray.getJsonObject(i);
            String month = jsonReference.getString(JSON_MONTH);
            String day = jsonReference.getString(JSON_DAY);
            String title= jsonReference.getString(JSON_TITLE);
            String topic= jsonReference.getString(JSON_TOPIC);
            String link= jsonReference.getString(JSON_LINK);
            SchedulePrototype reference= new SchedulePrototype("references",month, day, title, topic, link);
            data.addScheduleItem(reference);
        }
        
        JsonArray jsonRecitationsArray= json.getJsonArray(JSON_RECITATIONS);
        for(int i=0; i<jsonRecitationsArray.size();i++)
        {
            JsonObject jsonRecitation= jsonRecitationsArray.getJsonObject(i);
            String month = jsonRecitation.getString(JSON_MONTH);
            String day = jsonRecitation.getString(JSON_DAY);
            String title= jsonRecitation.getString(JSON_TITLE);
            String topic= jsonRecitation.getString(JSON_TOPIC);
            String link= jsonRecitation.getString(JSON_LINK);
            SchedulePrototype recitation= new SchedulePrototype("recitations", month, day, title, topic, link);
            data.addScheduleItem(recitation);
        }
        
        JsonArray jsonHomeworksArray= json.getJsonArray(JSON_HWS);
        for(int i=0; i<jsonHomeworksArray.size();i++)
        {
            JsonObject jsonHomework= jsonHomeworksArray.getJsonObject(i);
            String month = jsonHomework.getString(JSON_MONTH);
            String day = jsonHomework.getString(JSON_DAY);
            String title= jsonHomework.getString(JSON_TITLE);
            String topic= jsonHomework.getString(JSON_TOPIC);
            String link= jsonHomework.getString(JSON_LINK);
            SchedulePrototype hw= new SchedulePrototype("hws",month, day, title, topic, link);
            data.addScheduleItem(hw);
        }
        
    }
    
    private void loadMeetingTimes(CourseSiteGeneratorData data, JsonObject json)
    {
       JsonArray jsonLecturesArray= json.getJsonArray(JSON_LECTURES);
        for(int i=0; i<jsonLecturesArray.size();i++)
        {
            JsonObject jsonLecture= jsonLecturesArray.getJsonObject(i);
            String section = jsonLecture.getString(JSON_SECTION);
            String days = jsonLecture.getString(JSON_DAYS);
            String time= jsonLecture.getString(JSON_TIME);
            String room= jsonLecture.getString(JSON_ROOM);
            LecturesPrototype lecture= new LecturesPrototype(section, days, time, room);
            data.addLecture(lecture);
        } 
        
        JsonArray jsonRecitationsArray= json.getJsonArray(JSON_RECITATIONS);
        for(int i=0; i<jsonRecitationsArray.size();i++)
        {
            JsonObject jsonRecitations= jsonRecitationsArray.getJsonObject(i);
            String section = jsonRecitations.getString(JSON_SECTION);
            String daysTime = jsonRecitations.getString(JSON_DAY_TIME);
            String location= jsonRecitations.getString(JSON_LOCATION);
            String ta1= jsonRecitations.getString(JSON_TA_1);
            String ta2= jsonRecitations.getString(JSON_TA_2);
            MeetingTimesPrototype recitation= new MeetingTimesPrototype(section, daysTime, location, ta1, ta2);
            data.addRecitation(recitation);
        }
        
        JsonArray jsonLabsArray= json.getJsonArray(JSON_RECITATIONS);
        for(int i=0; i<jsonLabsArray.size();i++)
        {
            JsonObject jsonLabs= jsonLabsArray.getJsonObject(i);
            String section = jsonLabs.getString(JSON_SECTION);
            String daysTime = jsonLabs.getString(JSON_DAY_TIME);
            String location= jsonLabs.getString(JSON_LOCATION);
            String ta1= jsonLabs.getString(JSON_TA_1);
            String ta2= jsonLabs.getString(JSON_TA_2);
            MeetingTimesPrototype lab= new MeetingTimesPrototype(section, daysTime, location, ta1, ta2);
            data.addLab(lab);
        }
    }
    private void loadSyllabus(CourseSiteGeneratorData data, JsonObject json)
    {
        String description= json.getString(JSON_DESCRIPTION);
        data.setDescription(description);
        String prerequisites= json.getString(JSON_PREREQUISITES);
        data.setPrerequisites(prerequisites);
        String academicDishonesty= json.getString(JSON_ACADEMIC_DISHONESTY);
        data.setAcademicDishonesty(academicDishonesty);
        String specialAssistance= json.getString(JSON_SPECIAL_ASSISTANCE);
        data.setSpecialAssistance(specialAssistance);
        String gradingNote=json.getString(JSON_GRADING_NOTE);
       data.setGradingNote(gradingNote);
        
        JsonArray topicsArray=json.getJsonArray(JSON_TOPICS);
        String topics=topicsArray.toString();
        data.setTopics(topics);
        
        JsonArray outcomesArray=json.getJsonArray(JSON_OUTCOMES);
        String outcomes= outcomesArray.toString();
        data.setOutcomes(outcomes);
        
        JsonArray gradedComponentArray=json.getJsonArray(JSON_GRADED_COMPONENTS);
        String gradedComponents= gradedComponentArray.toString();
        data.setGradedComponents(gradedComponents);
        
        JsonArray textbooksArray= json.getJsonArray(JSON_TEXTBOOKS);
        String textbooks=textbooksArray.toString();
        data.setTextbooks(textbooks);
        
    }
    private void loadSite(CourseSiteGeneratorData data, JsonObject json)
    {
        String subject= json.getString(JSON_SUBJECT);
        data.setSubject(subject);
        String number=json.getString(JSON_NUMBER);
        data.setNumber(number);
        String semester= json.getString(JSON_SEMESTER);
        data.setSemester(semester);
        String year= json.getString(JSON_YEAR);
        data.setYear(year);
        String title=json.getString(JSON_TITLE);
        data.setTitle(title);
        
        
        JsonObject jsonLogos= json.getJsonObject(JSON_LOGOS);
        JsonObject favicon=jsonLogos.getJsonObject(JSON_FAVICON);
        String faviconHref=favicon.getString(JSON_HREF);
        LogoPrototype faviconLogo= new LogoPrototype(faviconHref);
        data.addLogo(faviconLogo);
        
        JsonObject navbar= jsonLogos.getJsonObject(JSON_NAVBAR);
        String navbarHref= navbar.getString(JSON_HREF);
        String navbarSrc= navbar.getString(JSON_SRC);
        LogoPrototype navbarLogo= new LogoPrototype(navbarHref, navbarSrc);
        data.addLogo(navbarLogo);
        
        JsonObject bottomLeft= jsonLogos.getJsonObject(JSON_BOTTOM_LEFT);
        String bottomLeftHref= bottomLeft.getString(JSON_HREF);
        String bottomLeftSrc= bottomLeft.getString(JSON_SRC);
        LogoPrototype bottomLeftLogo= new LogoPrototype(bottomLeftHref, bottomLeftSrc);
        data.addLogo(bottomLeftLogo);
        
        JsonObject bottomRight= jsonLogos.getJsonObject(JSON_BOTTOM_RIGHT);
        String bottomRightHref= bottomRight.getString(JSON_HREF);
        String bottomRightSrc= bottomRight.getString(JSON_SRC);
        LogoPrototype bottomRightLogo= new LogoPrototype(bottomRightHref, bottomRightSrc);
        data.addLogo(bottomRightLogo);
        
        JsonObject jsonInstructor=json.getJsonObject(JSON_INSTRUCTOR);
        String instructorName=jsonInstructor.getString(JSON_NAME);
        String instructorLink=jsonInstructor.getString(JSON_LINK);
        String instructorEmail=jsonInstructor.getString(JSON_EMAIL);
        String instructorRoom=jsonInstructor.getString(JSON_ROOM);
        
                JsonArray hoursArray=jsonInstructor.getJsonArray(JSON_HOURS);
        String hours= hoursArray.toString();

        InstructorPrototype instructor= new InstructorPrototype(instructorName, instructorLink,
        instructorEmail, instructorRoom, hours);
        data.setInstructor(instructor);
        data.loadInstructor();
        
        JsonArray jsonPagesArray= json.getJsonArray(JSON_PAGES);
        for (int i = 0; i < jsonPagesArray.size(); i++) {
            JsonObject page = jsonPagesArray.getJsonObject(i);
            String pageName= page.getString(JSON_NAME);
            String pageLink=page.getString(JSON_LINK);
            PagesPrototype Page= new PagesPrototype(pageName, pageLink);
            data.addPages(Page);
            
        }
        
        
        
        
    }
      
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        JsonArrayBuilder subjectArrayBuilder=Json.createArrayBuilder();
        Iterator<String> subjectIterator=dataManager.getSubjectComboBox().getItems().iterator();
        while(subjectIterator.hasNext())
        {
            subjectArrayBuilder.add(subjectIterator.next());
            
        }
        
        JsonArrayBuilder numberArrayBuilder=Json.createArrayBuilder();
        Iterator<String> numberIterator=dataManager.getNumberComboBox().getItems().iterator();
        while(numberIterator.hasNext())
        {
            numberArrayBuilder.add(numberIterator.next());
            
        }
        JsonArray subjectComboBox=subjectArrayBuilder.build();
        JsonArray numberComboBox=numberArrayBuilder.build();
        JsonObject comboBoxes=Json.createObjectBuilder()
                .add(JSON_SUBJECT, subjectComboBox)
                .add(JSON_NUMBER, numberComboBox)
                .build();
        JsonObject favicon=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(0).getHref())
                .build();

        JsonObject navbar=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(1).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(1).getSrc())
                .build();

        JsonObject bottom_left=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(2).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(2).getSrc())
                .build();

        JsonObject bottom_right=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(3).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(3).getSrc())
                .build();

        JsonObject logos=Json.createObjectBuilder()
                .add(JSON_FAVICON, favicon)
                .add(JSON_NAVBAR, navbar)
                .add(JSON_BOTTOM_LEFT, bottom_left)
                .add(JSON_BOTTOM_RIGHT, bottom_right)
                .build();
        String hours=dataManager.getInstructor().getHours();
        JsonReader hoursReader=Json.createReader(new StringReader(hours));
        JsonArray jsonhours=hoursReader.readArray();
        
        JsonObject instructor=Json.createObjectBuilder()
                .add(JSON_NAME, dataManager.getInstructor().getName())
                .add(JSON_LINK, dataManager.getInstructor().getLink())
                .add(JSON_EMAIL, dataManager.getInstructor().getEmail())
                .add(JSON_ROOM, dataManager.getInstructor().getRoom())
                .add(JSON_HOURS, jsonhours)
                .build();
        Iterator<PagesPrototype> pagesIterator=dataManager.getPages().iterator();
        JsonArrayBuilder pagesArrayBuilder=Json.createArrayBuilder();
        while(pagesIterator.hasNext()) {
            PagesPrototype page= pagesIterator.next();
            JsonObject pageJson=Json.createObjectBuilder()
                    .add(JSON_NAME, page.getName())
                    .add(JSON_LINK, page.getLink())
                    .build();
            pagesArrayBuilder.add(pageJson);
        }
        JsonArray pagesArray=pagesArrayBuilder.build();
                
        JsonObject siteTab=Json.createObjectBuilder()
                .add(JSON_SUBJECT, dataManager.getSubject())
                .add(JSON_NUMBER, dataManager.getNumber())
                .add(JSON_SEMESTER, dataManager.getSemester())
                .add(JSON_YEAR, dataManager.getYear())
                .add(JSON_TITLE, dataManager.getTitle())
                .add(JSON_LOGOS, logos)
                .add(JSON_INSTRUCTOR, instructor)
                .add(JSON_PAGES, pagesArray)
                .build();
        
        //Schedule Tab
        Iterator<SchedulePrototype> scheduleIterator=dataManager.getScheduleItems().iterator();
        JsonArrayBuilder holidaysArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder lecturesArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder referencesArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder recitationsArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder=Json.createArrayBuilder();
        
        while(scheduleIterator.hasNext()) {
            SchedulePrototype scheduleItem= scheduleIterator.next();
            if(scheduleItem.getType().equals("holidays"))
            {
                JsonObject holidayJson= Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getMonth())
                    .add(JSON_DAY, scheduleItem.getDay())
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_LINK, scheduleItem.getLink())
                    .build();
                holidaysArrayBuilder.add(holidayJson);
            }
            else if(scheduleItem.getType().equals("lectures"))
            {
                JsonObject lectureObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                lecturesArrayBuilder.add(lectureObject);
            }
            else if(scheduleItem.getType().equals("references"))
            {
                JsonObject referenceObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                referencesArrayBuilder.add(referenceObject);
            }
            else if(scheduleItem.getType().equals("recitations"))
            {
                JsonObject recitationsObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                recitationsArrayBuilder.add(recitationsObject);
            }
            else
            {
                JsonObject hwsObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .add(JSON_CRITERIA, "")
                        .add(JSON_TIME, "")
                        .build();
                hwsArrayBuilder.add(hwsObject);
            }
        }
        JsonArray holidaysArray=holidaysArrayBuilder.build();
        JsonArray lecturesArray=lecturesArrayBuilder.build();
        JsonArray referencesArray=referencesArrayBuilder.build();
        JsonArray recitationsArray=recitationsArrayBuilder.build();
        JsonArray hwsArray=hwsArrayBuilder.build();
        JsonObject scheduleTab=Json.createObjectBuilder()
                .add(JSON_STARTING_MONDAY_MONTH, dataManager.getStartingMondayMonth())
                .add(JSON_STARTING_MONDAY_DAY, dataManager.getStartingMondayDay())
                .add(JSON_ENDING_FRIDAY_MONTH, dataManager.getEndingFridayMonth())
                .add(JSON_ENDING_FRIDAY_DAY, dataManager.getEndingFridayDay())
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_REFERENCES, referencesArray)
                .add(JSON_RECITATIONS, recitationsArray)
                .add(JSON_HWS, hwsArray)
                .build();
        //Sections Tab
        Iterator<LecturesPrototype> lecturesIterator=dataManager.getLectures().iterator();
        JsonArrayBuilder mtLecturesArrayBuilder=Json.createArrayBuilder();
        while(lecturesIterator.hasNext()) {
            LecturesPrototype lecture=lecturesIterator.next();
            JsonObject lectureJson=Json.createObjectBuilder()
                    .add(JSON_SECTION,lecture.getSection())
                    .add(JSON_DAYS, lecture.getDays())
                    .add(JSON_TIME, lecture.getTime())
                    .add(JSON_ROOM, lecture.getRoom())
                    .build();
            mtLecturesArrayBuilder.add(lectureJson);
        }
        Iterator<MeetingTimesPrototype> labsIterator=dataManager.getLab().iterator();
        JsonArrayBuilder mtLabsArrayBuilder=Json.createArrayBuilder();
        while(labsIterator.hasNext())
        {
            MeetingTimesPrototype lab=labsIterator.next();
            JsonObject labJson=Json.createObjectBuilder()
                    .add(JSON_SECTION, lab.getSection())
                    .add(JSON_DAY_TIME, lab.getDay_time())
                    .add(JSON_LOCATION, lab.getRoom())
                    .add(JSON_TA_1, lab.getTa_1())
                    .add(JSON_TA_2, lab.getTa_2())
                    .build();
            mtLabsArrayBuilder.add(labJson);
        }
        Iterator<MeetingTimesPrototype> recitationsIterator=dataManager.getLab().iterator();
        JsonArrayBuilder mtRecitationsArrayBuilder=Json.createArrayBuilder();
        while(recitationsIterator.hasNext())
        {
            MeetingTimesPrototype recitation=recitationsIterator.next();
            JsonObject recitationsJson=Json.createObjectBuilder()
                    .add(JSON_SECTION, recitation.getSection())
                    .add(JSON_DAY_TIME, recitation.getDay_time())
                    .add(JSON_LOCATION, recitation.getRoom())
                    .add(JSON_TA_1, recitation.getTa_1())
                    .add(JSON_TA_2, recitation.getTa_2())
                    .build();
            mtRecitationsArrayBuilder.add(recitationsJson);
        }
        JsonArray mtRecitationsArray=mtRecitationsArrayBuilder.build();
        JsonArray mtLabsArray=mtLabsArrayBuilder.build();
        JsonArray mtLecturesArray=mtLecturesArrayBuilder.build();
        JsonObject sectionsTab=Json.createObjectBuilder()
                .add(JSON_LECTURES, mtLecturesArray)
                .add(JSON_LABS, mtLabsArray)
                .add(JSON_RECITATIONS,mtRecitationsArray )
                .build();
        //Syllabus Tab
        String topics =dataManager.getTopics();
        JsonReader topicsReader=Json.createReader(new StringReader(topics));
        JsonArray jsonTopics=topicsReader.readArray();
        
        String outcomes=dataManager.getOutcomes();
        JsonReader outcomesReader=Json.createReader(new StringReader(outcomes));
        JsonArray jsonOutcomes=outcomesReader.readArray();
        
        String textbooks=dataManager.getTextbooks();
        JsonReader textbooksReader=Json.createReader(new StringReader(textbooks));
        JsonArray jsonTextbooks=textbooksReader.readArray();
        
        String gradedComponents=dataManager.getGradedComponents();
        JsonReader gradedComponentsReader=Json.createReader(new StringReader(gradedComponents));
        JsonArray jsonGradedComponents=gradedComponentsReader.readArray();
        
        JsonObject syllabusTab=Json.createObjectBuilder()
                .add(JSON_DESCRIPTION, dataManager.getDescription())
                .add(JSON_PREREQUISITES, dataManager.getPrerequisites())
                .add(JSON_GRADING_NOTE, dataManager.getGradingNote())
                .add(JSON_ACADEMIC_DISHONESTY, dataManager.getAcademicDishonesty())
                .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSpecialAssistance())
                .add(JSON_TOPICS, jsonTopics)
                .add(JSON_TEXTBOOKS, jsonTextbooks)
                .add(JSON_OUTCOMES, jsonOutcomes)
                .add(JSON_GRADED_COMPONENTS, jsonGradedComponents)
                .build();
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TYPE, ta.getType().toString()).build();
            if (ta.getType().equals(TAType.Graduate.toString()))
                gradTAsArrayBuilder.add(taJson);
            else
                undergradTAsArrayBuilder.add(taJson);
	}
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
	JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

	// NOW BUILD THE OFFICE HOURS JSON OBJCTS TO SAVE
	JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                        .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                        .add(JSON_DAY_OF_WEEK, dow.toString())
                        .add(JSON_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
	}
	JsonArray officeHoursArray = officeHoursArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject officeHoursTab = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
		.build();
	
        JsonObject dataManagerJSO =Json.createObjectBuilder()
                .add(JSON_COMBO_BOXES, comboBoxes)
                .add(JSON_SITE_TAB, siteTab)
                .add(JSON_SCHEDULE_TAB, scheduleTab)
                .add(JSON_SECTIONS_TAB, sectionsTab)
                .add(JSON_SYLLABUS_TAB, syllabusTab)
                .add(JSON_OFFICE_HOURS_TAB, officeHoursTab)
                .build();
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        //OFFICE HOURS JSON
        String hours=dataManager.getInstructor().getHours();
        JsonReader r= Json.createReader(new StringReader(hours));
        JsonArray instructorHours=r.readArray();
        
        JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
	Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TYPE, ta.getType().toString()).build();
            if (ta.getType().equals(TAType.Graduate.toString()))
                gradTAsArrayBuilder.add(taJson);
            else
                undergradTAsArrayBuilder.add(taJson);
	}
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
	JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();
        JsonObject instructor=Json.createObjectBuilder()
                .add(JSON_NAME, dataManager.getInstructor().getName())
                .add(JSON_LINK, dataManager.getInstructor().getLink())
                .add(JSON_EMAIL, dataManager.getInstructor().getEmail())
                .add(JSON_ROOM, dataManager.getInstructor().getRoom())
                .add(JSON_HOURS, instructorHours)
                .build();
        JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                        .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                        .add(JSON_DAY_OF_WEEK, dow.toString())
                        .add(JSON_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
	}
	JsonArray officeHoursArray = officeHoursArrayBuilder.build();
        JsonObject officeHours=Json.createObjectBuilder()
                .add(JSON_START_HOUR, dataManager.getStartHour())
                .add(JSON_END_HOUR, dataManager.getEndHour())
                .add(JSON_INSTRUCTOR, instructor)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
                .build();
        
        //PAGE JSON
        JsonObject favicon=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(0).getHref())
                .build();

        JsonObject navbar=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(1).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(1).getSrc())
                .build();

        JsonObject bottom_left=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(2).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(2).getSrc())
                .build();

        JsonObject bottom_right=Json.createObjectBuilder()
                .add(JSON_HREF, dataManager.getLogos().get(3).getHref())
                .add(JSON_SRC, dataManager.getLogos().get(3).getSrc())
                .build();

        JsonObject logos=Json.createObjectBuilder()
                .add(JSON_FAVICON, favicon)
                .add(JSON_NAVBAR, navbar)
                .add(JSON_BOTTOM_LEFT, bottom_left)
                .add(JSON_BOTTOM_RIGHT, bottom_right)
                .build();
        
        Iterator<PagesPrototype> pagesIterator=dataManager.getPages().iterator();
        JsonArrayBuilder pagesArrayBuilder=Json.createArrayBuilder();
        while(pagesIterator.hasNext()) {
            PagesPrototype page= pagesIterator.next();
            JsonObject pageJson=Json.createObjectBuilder()
                    .add(JSON_NAME, page.getName())
                    .add(JSON_LINK, page.getLink())
                    .build();
            pagesArrayBuilder.add(pageJson);
        }
        JsonArray pagesArray=pagesArrayBuilder.build();
                
        JsonObject pages=Json.createObjectBuilder()
                .add(JSON_SUBJECT, dataManager.getSubject())
                .add(JSON_NUMBER, dataManager.getNumber())
                .add(JSON_SEMESTER, dataManager.getSemester())
                .add(JSON_YEAR, dataManager.getYear())
                .add(JSON_TITLE, dataManager.getTitle())
                .add(JSON_LOGOS, logos)
                .add(JSON_INSTRUCTOR, instructor)
                .add(JSON_PAGES, pagesArray)
                .build();
        //SCHEDULE JSON
        Iterator<SchedulePrototype> scheduleIterator=dataManager.getScheduleItems().iterator();
        JsonArrayBuilder holidaysArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder lecturesArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder referencesArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder recitationsArrayBuilder=Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder=Json.createArrayBuilder();
        
        while(scheduleIterator.hasNext()) {
            SchedulePrototype scheduleItem= scheduleIterator.next();
            if(scheduleItem.getType().equals("holidays"))
            {
                JsonObject holidayJson= Json.createObjectBuilder()
                    .add(JSON_MONTH, scheduleItem.getMonth())
                    .add(JSON_DAY, scheduleItem.getDay())
                    .add(JSON_TITLE, scheduleItem.getTitle())
                    .add(JSON_LINK, scheduleItem.getLink())
                    .build();
                holidaysArrayBuilder.add(holidayJson);
            }
            else if(scheduleItem.getType().equals("lectures"))
            {
                JsonObject lectureObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                lecturesArrayBuilder.add(lectureObject);
            }
            else if(scheduleItem.getType().equals("references"))
            {
                JsonObject referenceObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                referencesArrayBuilder.add(referenceObject);
            }
            else if(scheduleItem.getType().equals("recitations"))
            {
                JsonObject recitationsObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .build();
                recitationsArrayBuilder.add(recitationsObject);
            }
            else
            {
                JsonObject hwsObject=Json.createObjectBuilder()
                        .add(JSON_MONTH, scheduleItem.getMonth())
                        .add(JSON_DAY, scheduleItem.getDay())
                        .add(JSON_TITLE, scheduleItem.getTitle())
                        .add(JSON_TOPIC, scheduleItem.getTopic())
                        .add(JSON_LINK, scheduleItem.getLink())
                        .add(JSON_TIME, "")
                        .add(JSON_CRITERIA, "none")
                        .build();
                hwsArrayBuilder.add(hwsObject);
            }
        }
        JsonArray holidaysArray=holidaysArrayBuilder.build();
        JsonArray lecturesArray=lecturesArrayBuilder.build();
        JsonArray referencesArray=referencesArrayBuilder.build();
        JsonArray recitationsArray=recitationsArrayBuilder.build();
        JsonArray hwsArray=hwsArrayBuilder.build();
        JsonObject schedule=Json.createObjectBuilder()
                .add(JSON_STARTING_MONDAY_MONTH, dataManager.getStartingMondayMonth())
                .add(JSON_STARTING_MONDAY_DAY, dataManager.getStartingMondayDay())
                .add(JSON_ENDING_FRIDAY_MONTH, dataManager.getEndingFridayMonth())
                .add(JSON_ENDING_FRIDAY_DAY, dataManager.getEndingFridayDay())
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_REFERENCES, referencesArray)
                .add(JSON_RECITATIONS, recitationsArray)
                .add(JSON_HWS, hwsArray)
                .build();
        //SCHEDULE JSON
        Iterator<LecturesPrototype> lecturesIterator=dataManager.getLectures().iterator();
        JsonArrayBuilder mtLecturesArrayBuilder=Json.createArrayBuilder();
        while(lecturesIterator.hasNext()) {
            LecturesPrototype lecture=lecturesIterator.next();
            JsonObject lectureJson=Json.createObjectBuilder()
                    .add(JSON_SECTION,lecture.getSection())
                    .add(JSON_DAYS, lecture.getDays())
                    .add(JSON_TIME, lecture.getTime())
                    .add(JSON_ROOM, lecture.getRoom())
                    .build();
            mtLecturesArrayBuilder.add(lectureJson);
        }
        Iterator<MeetingTimesPrototype> labsIterator=dataManager.getLab().iterator();
        JsonArrayBuilder mtLabsArrayBuilder=Json.createArrayBuilder();
        while(labsIterator.hasNext())
        {
            MeetingTimesPrototype lab=labsIterator.next();
            JsonObject labJson=Json.createObjectBuilder()
                    .add(JSON_SECTION, lab.getSection())
                    .add(JSON_DAY_TIME, lab.getDay_time())
                    .add(JSON_LOCATION, lab.getRoom())
                    .add(JSON_TA_1, lab.getTa_1())
                    .add(JSON_TA_2, lab.getTa_2())
                    .build();
            mtLabsArrayBuilder.add(labJson);
        }
        Iterator<MeetingTimesPrototype> recitationsIterator=dataManager.getLab().iterator();
        JsonArrayBuilder mtRecitationsArrayBuilder=Json.createArrayBuilder();
        while(recitationsIterator.hasNext())
        {
            MeetingTimesPrototype recitation=recitationsIterator.next();
            JsonObject recitationsJson=Json.createObjectBuilder()
                    .add(JSON_SECTION, recitation.getSection())
                    .add(JSON_DAY_TIME, recitation.getDay_time())
                    .add(JSON_LOCATION, recitation.getRoom())
                    .add(JSON_TA_1, recitation.getTa_1())
                    .add(JSON_TA_2, recitation.getTa_2())
                    .build();
            mtRecitationsArrayBuilder.add(recitationsJson);
        }
        JsonArray mtRecitationsArray=mtRecitationsArrayBuilder.build();
        JsonArray mtLabsArray=mtLabsArrayBuilder.build();
        JsonArray mtLecturesArray=mtLecturesArrayBuilder.build();
        JsonObject sections=Json.createObjectBuilder()
                .add(JSON_LECTURES, mtLecturesArray)
                .add(JSON_LABS, mtLabsArray)
                .add(JSON_RECITATIONS,mtRecitationsArray )
                .build();
        //SYLLABUS JSON
        String topics =dataManager.getTopics();
        JsonReader topicsReader=Json.createReader(new StringReader(topics));
        JsonArray jsonTopics=topicsReader.readArray();
        
        String outcomes=dataManager.getOutcomes();
        JsonReader outcomesReader=Json.createReader(new StringReader(outcomes));
        JsonArray jsonOutcomes=outcomesReader.readArray();
        
        String textbooks=dataManager.getTextbooks();
        JsonReader textbooksReader=Json.createReader(new StringReader(textbooks));
        JsonArray jsonTextbooks=textbooksReader.readArray();
        
        String gradedComponents=dataManager.getGradedComponents();
        JsonReader gradedComponentsReader=Json.createReader(new StringReader(gradedComponents));
        JsonArray jsonGradedComponents=gradedComponentsReader.readArray();
        
        JsonObject syllabus=Json.createObjectBuilder()
                .add(JSON_DESCRIPTION, dataManager.getDescription())
                .add(JSON_TOPICS, jsonTopics)
                .add(JSON_PREREQUISITES, dataManager.getPrerequisites())
                .add(JSON_OUTCOMES, jsonOutcomes)
                .add(JSON_TEXTBOOKS, jsonTextbooks)
                .add(JSON_GRADED_COMPONENTS, jsonGradedComponents)
                .add(JSON_GRADING_NOTE, dataManager.getGradingNote())
                .add(JSON_ACADEMIC_DISHONESTY, dataManager.getAcademicDishonesty())
                .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSpecialAssistance())
                .build();
        
        File exportDir=new File(dataManager.getExportDir());
        File template=new File("./templateDir");
        if(!exportDir.exists())
        {
            exportDir.mkdir();
        }
        else
        {
            FileUtils.cleanDirectory(exportDir);
        }
       
        FileUtils.copyDirectory(template, exportDir);
        
        File css=new File("./work/cssfiles/"+dataManager.getCssFile());
        File exportCss=new File(exportDir.getAbsolutePath()+"/css");
        FileUtils.copyFileToDirectory(css, exportCss);
        
        File exportedCss=new File(exportCss.getAbsolutePath()+"/"+dataManager.getCssFile());
        File newExportedCss=new File(exportCss.getAbsolutePath()+"/"+"sea_wolf.css");
        exportedCss.renameTo(newExportedCss);
        
        ArrayList<String> exportedPages=new ArrayList<String>();
        Iterator pIterator=dataManager.getPages().iterator();
        while(pIterator.hasNext())
         {
             PagesPrototype page=(PagesPrototype)pIterator.next();
             if(page.getName().equals("Home"))
             {
                 File home= new File("./htmlFiles/index.html");
                 FileUtils.copyFileToDirectory(home, exportDir);
                 exportedPages.add("index.html");
             }
             else if(page.getName().equals("Syllabus"))
             {
                 File syllabusFile= new File("./htmlFiles/syllabus.html");
                 FileUtils.copyFileToDirectory(syllabusFile, exportDir);
                 exportedPages.add("syllabus.html");
             }
             else if(page.getName().equals("Schedule"))
             {
                 File scheduleFile= new File("./htmlFiles/schedule.html");
                 FileUtils.copyFileToDirectory(scheduleFile, exportDir);
                 exportedPages.add("schedule.html");
             }
             else if(page.getName().equals("HWs"))
             {
                 File hwsFile= new File("./htmlFiles/hws.html");
                 FileUtils.copyFileToDirectory(hwsFile, exportDir);
                 exportedPages.add("hws.html");
             }
         }
                
        String exportDirJs= exportDir.getAbsolutePath()+"/js";
        File syllabusFile= new File(exportDirJs, "SyllabusData.json");
        writeFile(syllabusFile, syllabus);
        File scheduleFile= new File(exportDirJs, "ScheduleData.json");
        writeFile(scheduleFile, schedule);
        File pagesFile=new File(exportDirJs, "PageData.json");
        writeFile(pagesFile, pages);
        File officeHoursFile= new File(exportDirJs, "OfficeHoursData.json");
        writeFile(officeHoursFile, officeHours);
        File sectionsFile=new File(exportDirJs, "SectionsData.json");
        writeFile(sectionsFile, sections);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        props.removeProperty(APP_EXPORT_PAGE);
        props.addProperty(APP_EXPORT_PAGE, dataManager.getExportDir()+ "/" + exportedPages.get(0));
        
        
        
        
    }
    
    private void writeFile(File filePath, JsonObject object) throws IOException
    {
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(object);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(object);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
        
    }
}