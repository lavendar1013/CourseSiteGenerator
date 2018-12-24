package csg.workspace.controllers;

import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import csg.CourseSiteGeneratorApp;
import static csg.OfficeHoursPropertyType.ADD_ITEM_DATE_PICKER;
import static csg.OfficeHoursPropertyType.ADD_ITEM_LINK_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.ADD_ITEM_TITLE_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.ADD_ITEM_TOPIC_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.EMAIL_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.END_DATE_PICKER;
import static csg.OfficeHoursPropertyType.HOME_CHECKBOX;
import static csg.OfficeHoursPropertyType.HOME_PAGE_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.HWS_CHECKBOX;
import static csg.OfficeHoursPropertyType.INSTRUCTOR_HOURS_TEXT_AREA;
import static csg.OfficeHoursPropertyType.LABS_TABLE;
import static csg.OfficeHoursPropertyType.LECTURES_TABLE;
import static csg.OfficeHoursPropertyType.NAME_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.NUMBER_COMBO_BOX;
import static csg.OfficeHoursPropertyType.OH_EMAIL_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.OH_FOOLPROOF_SETTINGS;
import static csg.OfficeHoursPropertyType.OH_NAME_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.OH_NO_TA_SELECTED_CONTENT;
import static csg.OfficeHoursPropertyType.OH_NO_TA_SELECTED_TITLE;
import static csg.OfficeHoursPropertyType.OH_OFFICE_HOURS_TABLE_VIEW;
import static csg.OfficeHoursPropertyType.OH_TAS_TABLE_VIEW;
import static csg.OfficeHoursPropertyType.OH_TA_EDIT_DIALOG;
import static csg.OfficeHoursPropertyType.RECITATIONS_TABLE;
import static csg.OfficeHoursPropertyType.ROOM_TEXT_FIELD;
import static csg.OfficeHoursPropertyType.SCHEDULE_CHECKBOX;
import static csg.OfficeHoursPropertyType.SCHEDULE_ITEMS_TABLE;
import static csg.OfficeHoursPropertyType.SEMESTER_COMBO_BOX;
import static csg.OfficeHoursPropertyType.START_DATE_PICKER;
import static csg.OfficeHoursPropertyType.STYLE_SHEET_COMBO_BOX;
import static csg.OfficeHoursPropertyType.SUBJECT_COMBO_BOX;
import static csg.OfficeHoursPropertyType.SYLLABUS_CHECKBOX;
import static csg.OfficeHoursPropertyType.TYPE_COMBO_BOX;
import static csg.OfficeHoursPropertyType.YEAR_COMBO_BOX;
import csg.data.CourseSiteGeneratorData;
import csg.data.HolidaysPrototype;
import csg.data.InstructorPrototype;
import csg.data.LecturesPrototype;
import csg.data.MeetingTimesPrototype;
import csg.data.PagesPrototype;
import csg.data.SchedulePrototype;
import csg.data.TAType;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.transactions.AddLab_Transaction;
import csg.transactions.AddLecture_Transaction;
import csg.transactions.AddRecitation_Transaction;
import csg.transactions.AddScheduleItem_Transaction;
import csg.transactions.AddTA_Transaction;
import csg.transactions.EditEndDate_Transaction;
import csg.transactions.EditInstructor_Transaction;
import csg.transactions.EditLecture_Transaction;
import csg.transactions.EditPages_Transaction;
import csg.transactions.EditScheduleItem_Transaction;
import csg.transactions.EditStartDate_Transaction;
import csg.transactions.EditTA_Transaction;
import csg.transactions.RemoveLab_Transaction;
import csg.transactions.RemoveLecture_Transaction;
import csg.transactions.RemoveRecitation_Transaction;
import csg.transactions.ToggleOfficeHours_Transaction;
import csg.workspace.dialogs.TADialog;
import java.awt.Image;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.*;
import javafx.scene.layout.*;
        

/**
 *
 * @author McKillaGorilla
 */
public class OfficeHoursController {

    CourseSiteGeneratorApp app;

    public OfficeHoursController(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(OH_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD);
        String email = emailTF.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        TAType type = data.getSelectedType();
        if (data.isLegalNewTA(name, email)) {
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name.trim(), email.trim(), type);
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);

            // NOW CLEAR THE TEXT FIELDS
            nameTF.setText("");
            emailTF.setText("");
            nameTF.requestFocus();
        }
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    public void processVerifyTA() {

    }

    public void processToggleOfficeHours() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        ObservableList<TablePosition> selectedCells = officeHoursTableView.getSelectionModel().getSelectedCells();
        if (selectedCells.size() > 0) {
            TablePosition cell = selectedCells.get(0);
            int cellColumnNumber = cell.getColumn();
            CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
            if (data.isDayOfWeekColumn(cellColumnNumber)) {
                DayOfWeek dow = data.getColumnDayOfWeek(cellColumnNumber);
                TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(OH_TAS_TABLE_VIEW);
                TeachingAssistantPrototype ta = taTableView.getSelectionModel().getSelectedItem();
                if (ta != null) {
                    TimeSlot timeSlot = officeHoursTableView.getSelectionModel().getSelectedItem();
                    ToggleOfficeHours_Transaction transaction = new ToggleOfficeHours_Transaction(data, timeSlot, dow, ta);
                    app.processTransaction(transaction);
                }
                else {
                    Stage window = app.getGUIModule().getWindow();
                    AppDialogsFacade.showMessageDialog(window, OH_NO_TA_SELECTED_TITLE, OH_NO_TA_SELECTED_CONTENT);
                }
            }
            int row = cell.getRow();
            cell.getTableView().refresh();
        }
    }

    public void processTypeTA() {
        app.getFoolproofModule().updateControls(OH_FOOLPROOF_SETTINGS);
    }

    public void processEditTA() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        if (data.isTASelected()) {
            TeachingAssistantPrototype taToEdit = data.getSelectedTA();
            TADialog taDialog = (TADialog)app.getGUIModule().getDialog(OH_TA_EDIT_DIALOG);
            taDialog.showEditDialog(taToEdit);
            TeachingAssistantPrototype editTA = taDialog.getEditTA();
            if (editTA != null) {
                EditTA_Transaction transaction = new EditTA_Transaction(taToEdit, editTA.getName(), editTA.getEmail(), editTA.getType());
                app.processTransaction(transaction);
            }
        }
    }

    public void processSelectAllTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.All);
    }

    public void processSelectGradTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.Graduate);
    }

    public void processSelectUndergradTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.Undergraduate);
    }

    public void processSelectTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.refresh();
    }
    
    public void processEditInstructor() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TextArea instructorHoursTextArea= (TextArea) gui.getGUINode(INSTRUCTOR_HOURS_TEXT_AREA);
        TextField instructorNameTextField=(TextField) gui.getGUINode(NAME_TEXT_FIELD);
        TextField instructorRoomTextField=(TextField) gui.getGUINode(ROOM_TEXT_FIELD);
        TextField instructorEmailTextField=(TextField) gui.getGUINode(EMAIL_TEXT_FIELD);
        TextField instructorHomeTextField=(TextField) gui.getGUINode(HOME_PAGE_TEXT_FIELD);
        String instructorHours=instructorHoursTextArea.getText();
        String instructorName=instructorNameTextField.getText();
        String instructorRoom=instructorRoomTextField.getText();
        String instructorEmail=instructorEmailTextField.getText();
        String instructorHome=instructorHomeTextField.getText();
        InstructorPrototype oldInstructor=data.getInstructor();
        EditInstructor_Transaction transaction=new EditInstructor_Transaction(app, oldInstructor, instructorName, instructorRoom, instructorEmail, instructorHome, instructorHours);
        app.processTransaction(transaction);
        
        
    }
    
    public void processEditExportDir() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectComboBox=(ComboBox) gui.getGUINode(SUBJECT_COMBO_BOX);
        ComboBox numberComboBox=(ComboBox) gui.getGUINode(NUMBER_COMBO_BOX);
        ComboBox semesterComboBox=(ComboBox) gui.getGUINode(SEMESTER_COMBO_BOX);
        ComboBox yearComboBox=(ComboBox) gui.getGUINode(YEAR_COMBO_BOX);
        String subject=(String)subjectComboBox.getValue();
        String number=(String)numberComboBox.getValue();
        String semester=(String)semesterComboBox.getValue();
        String year=(String)yearComboBox.getValue();
        data.editExportDir("./export/"+subject+"_"+number+"_"+semester+"_"+year+"/public_html");
        
    }
    
    public void processEditPages() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox=((CheckBox) gui.getGUINode(HOME_CHECKBOX));
        CheckBox syllabusCheckBox=((CheckBox) gui.getGUINode(SYLLABUS_CHECKBOX));
        CheckBox scheduleCheckBox=((CheckBox) gui.getGUINode(SCHEDULE_CHECKBOX));
        CheckBox hwsCheckBox=((CheckBox) gui.getGUINode(HWS_CHECKBOX));
        ArrayList<PagesPrototype> pages= new ArrayList();
        if(homeCheckBox.isSelected())
        {
            PagesPrototype homePage=new PagesPrototype("Home","home.html");
            pages.add(homePage);
        }
        if(syllabusCheckBox.isSelected())
        {
            PagesPrototype syllabusPage=new PagesPrototype("Syllabus","syllabus.html");
            pages.add(syllabusPage);
        }
        if(scheduleCheckBox.isSelected())
        {
            PagesPrototype schedulePage=new PagesPrototype("Schedule","schedule.html");
            pages.add(schedulePage);
        }
        if(hwsCheckBox.isSelected())
        {
            PagesPrototype hwPage=new PagesPrototype("HWs","hws.html");
            pages.add(hwPage);
        }
        EditPages_Transaction transaction=new EditPages_Transaction(app, pages);
        app.processTransaction(transaction);
        
    }
    
    public void processEditSubjectComboBox () {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectComboBox=(ComboBox) gui.getGUINode(SUBJECT_COMBO_BOX);
        String s=(String)subjectComboBox.getValue();
        data.addToSubjectComboBox(s);
        
    }
    
    public void processEditNumberComboBox() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox numberComboBox=(ComboBox) gui.getGUINode(NUMBER_COMBO_BOX);
        String s=(String)numberComboBox.getValue();
        data.addToNumberComboBox(s);
    }
    
    public void processAddLecture()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui=app.getGUIModule();
        LecturesPrototype newLecture= new LecturesPrototype("?","?","?","?");
        AddLecture_Transaction transaction= new AddLecture_Transaction(data, newLecture);
        app.processTransaction(transaction);

    }
    
    public void processEditLecture()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui=app.getGUIModule();
        TableView<LecturesPrototype> lecturesTableView = (TableView)gui.getGUINode(LECTURES_TABLE);
        LecturesPrototype lp = lecturesTableView.getSelectionModel().getSelectedItem();
        String newSection=lecturesTableView.getColumns().get(0).getText();
        String newDays=lecturesTableView.getColumns().get(1).getText();
        String newTime=lecturesTableView.getColumns().get(2).getText();
        String newRoom=lecturesTableView.getColumns().get(3).getText();
        EditLecture_Transaction transaction= new EditLecture_Transaction(data, lp, newSection, newDays, newTime, newRoom);
        app.processTransaction(transaction);
        
        
    }
    
    public void processRemoveLecture()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui= app.getGUIModule();
        TableView<LecturesPrototype> lecturesTableView = (TableView)gui.getGUINode(LECTURES_TABLE);
        LecturesPrototype lp= lecturesTableView.getSelectionModel().getSelectedItem();
        RemoveLecture_Transaction transaction= new RemoveLecture_Transaction(data,lp);
        app.processTransaction(transaction);
        
    }
    
    public void processAddRecitation()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui=app.getGUIModule();
        MeetingTimesPrototype newRecitation= new MeetingTimesPrototype("?","?","?","?","?");
        AddRecitation_Transaction transaction= new AddRecitation_Transaction(data, newRecitation);
        app.processTransaction(transaction);
    }
    
    public void processRemoveRecitation()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui= app.getGUIModule();
        TableView<MeetingTimesPrototype> recitationTableView = (TableView)gui.getGUINode(RECITATIONS_TABLE);
        MeetingTimesPrototype recitation= recitationTableView.getSelectionModel().getSelectedItem();
        RemoveRecitation_Transaction transaction= new RemoveRecitation_Transaction(data,recitation);
        app.processTransaction(transaction);
        
    }
    
    public void processAddLab()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui=app.getGUIModule();
        MeetingTimesPrototype newLab= new MeetingTimesPrototype("?","?","?","?","?");
        AddLab_Transaction transaction= new AddLab_Transaction(data, newLab);
        app.processTransaction(transaction);
    }
    
    public void processRemoveLab()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui= app.getGUIModule();
        TableView<MeetingTimesPrototype> labsTableView = (TableView)gui.getGUINode(LABS_TABLE);
        MeetingTimesPrototype lab= labsTableView.getSelectionModel().getSelectedItem();
        RemoveLab_Transaction transaction= new RemoveLab_Transaction(data,lab);
        app.processTransaction(transaction);
        
    }
    
    public void processAddScheduleItem()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui= app.getGUIModule();
        TableView scheduleTable= (TableView) gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        ComboBox typeComboBox=(ComboBox) gui.getGUINode(TYPE_COMBO_BOX);
            String type =(String)typeComboBox.getValue();
            DatePicker dateDatePicker=(DatePicker) gui.getGUINode(ADD_ITEM_DATE_PICKER);
            String month=dateDatePicker.getValue().getMonth().toString();
            String day=Integer.toString(dateDatePicker.getValue().getDayOfMonth());
            TextField title=(TextField) gui.getGUINode(ADD_ITEM_TITLE_TEXT_FIELD);
            String itemTitle=title.getText();
            TextField topic=(TextField) gui.getGUINode(ADD_ITEM_TOPIC_TEXT_FIELD);
            String itemTopic=topic.getText();
            TextField link=(TextField) gui.getGUINode(ADD_ITEM_LINK_TEXT_FIELD);
            String itemLink=link.getText();
            SchedulePrototype newSchedule =new SchedulePrototype(type, month+"/"+day, day, itemTitle, itemTopic, itemLink);
            AddScheduleItem_Transaction transaction= new AddScheduleItem_Transaction(data,newSchedule);
            app.processTransaction(transaction);
        
    }
    
    public void processEditScheduleItem()
    {
        
        AppGUIModule gui= app.getGUIModule();
        TableView scheduleTable= (TableView) gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        SchedulePrototype oldSchedule=(SchedulePrototype)scheduleTable.getSelectionModel().getSelectedItem();
        
        ComboBox typeComboBox=(ComboBox) gui.getGUINode(TYPE_COMBO_BOX);
        String type =(String)typeComboBox.getValue();
            DatePicker dateDatePicker=(DatePicker) gui.getGUINode(ADD_ITEM_DATE_PICKER);
            String month=dateDatePicker.getValue().getMonth().toString();
            String day=Integer.toString(dateDatePicker.getValue().getDayOfMonth());
            TextField title=(TextField) gui.getGUINode(ADD_ITEM_TITLE_TEXT_FIELD);
            String itemTitle=title.getText();
            TextField topic=(TextField) gui.getGUINode(ADD_ITEM_TOPIC_TEXT_FIELD);
            String itemTopic=topic.getText();
            TextField link=(TextField) gui.getGUINode(ADD_ITEM_LINK_TEXT_FIELD);
            String itemLink=link.getText();
            EditScheduleItem_Transaction transaction= new EditScheduleItem_Transaction(app, oldSchedule,type, month, day, itemTitle, itemTopic, itemLink);
            app.processTransaction(transaction);
    }
    
    public void processEditStyleSheet()
    {
        CourseSiteGeneratorData data= (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui= app.getGUIModule();
        ComboBox styleComboBox= (ComboBox) gui.getGUINode(STYLE_SHEET_COMBO_BOX);
        String style=(String)styleComboBox.getValue();
        data.setCssFile(style);
        
    }
    
    public void processEditFaviconIcon()
    {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Select Image");
        
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
       fileChooser.showOpenDialog(app.getGUIModule().getWindow());
       
        
    }
}