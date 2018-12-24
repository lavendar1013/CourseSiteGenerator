package csg.workspace;

import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import csg.CourseSiteGeneratorApp;
import csg.OfficeHoursPropertyType;
import static csg.OfficeHoursPropertyType.*;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.MeetingTimesPrototype;
import csg.data.SchedulePrototype;
import csg.workspace.controllers.OfficeHoursController;
import csg.workspace.dialogs.TADialog;
import csg.workspace.foolproof.OfficeHoursFoolproofDesign;
import static csg.workspace.style.OHStyle.*;
import javafx.scene.control.ScrollBar;
import java.time.LocalDate;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

/**
 *
 * @author McKillaGorilla
 */
public class CSGWorkspace extends AppWorkspaceComponent {

    public CSGWorkspace(CourseSiteGeneratorApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();

        // 
        initFoolproofDesign();

        // INIT DIALOGS
        initDialogs();
    }

    private void initDialogs() {
        TADialog taDialog = new TADialog((CourseSiteGeneratorApp) app);
        app.getGUIModule().addDialog(OH_TA_EDIT_DIALOG, taDialog);
    }

    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder ohBuilder = app.getGUIModule().getNodesBuilder();
       Pane pane= new Pane();
      
       TabPane Pane= ohBuilder.buildTabPane(COURSE_BUILDER_TAB_PANE, null, EMPTY_TEXT, ENABLED);
        
     
       
       pane.getChildren().add(Pane);
       
       Tab site=ohBuilder.buildTab(SITE_TAB, null, EMPTY_TEXT, ENABLED);
       
       //SITE PAGE
       
      
       VBox siteBox= ohBuilder.buildVBox(SITE_VBOX, null, CLASS_VBOX, ENABLED);
       siteBox.prefWidthProperty().bind(Pane.widthProperty());
       ScrollPane scrollPane= new ScrollPane(siteBox);
       scrollPane.setMaxHeight(500);
       GridPane bannerGridPane=ohBuilder.buildGridPane(BANNER_GRID_PANE, siteBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(BANNER_LABEL, bannerGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildLabel(SUBJECT_LABEL, bannerGridPane, 0, 1, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox subjectComboBox=ohBuilder.buildComboBox(SUBJECT_COMBO_BOX, bannerGridPane, 1, 1, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       subjectComboBox.setPrefWidth(100);
       subjectComboBox.setEditable(ENABLED);
       ohBuilder.buildLabel(NUMBER_LABEL, bannerGridPane, 2, 1, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox numberComboBox=ohBuilder.buildComboBox(NUMBER_COMBO_BOX, bannerGridPane, 3, 1, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       numberComboBox.setPrefWidth(100);
       numberComboBox.setEditable(ENABLED);
       ohBuilder.buildLabel(SEMESTER_LABEL, bannerGridPane, 0, 2, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox semesterComboBox=ohBuilder.buildComboBox(SEMESTER_COMBO_BOX, bannerGridPane, 1, 2, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       semesterComboBox.setPrefWidth(100);
       semesterComboBox.getItems().add("Fall");
       semesterComboBox.getItems().add("Spring");
       semesterComboBox.getItems().add("Winter");
       semesterComboBox.getItems().add("Summer");
       ohBuilder.buildLabel(YEAR_LABEL, bannerGridPane, 2, 2, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox yearComboBox=ohBuilder.buildComboBox(YEAR_COMBO_BOX, bannerGridPane, 3, 2, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       yearComboBox.setPrefWidth(100);
       yearComboBox.getItems().add(LocalDate.now().getYear());
       yearComboBox.getItems().add(LocalDate.now().getYear()+1);
       ohBuilder.buildLabel(TITLE_LABEL, bannerGridPane, 0, 3, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox titleComboBox=ohBuilder.buildComboBox(TITLE_COMBO_BOX, bannerGridPane, 1, 3, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       titleComboBox.setPrefWidth(200);
       titleComboBox.setEditable(ENABLED);
       ohBuilder.buildLabel(EXPORT_LABEL, bannerGridPane, 0, 4, 1, 1, EMPTY_TEXT, ENABLED);
       ohBuilder.buildLabel(EXPORT_DIR, bannerGridPane, 1, 4, 1 ,1 , EMPTY_TEXT, ENABLED);
       
       GridPane pagesGridPane=ohBuilder.buildGridPane(PAGES_GRID_PANE, siteBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(PAGES_LABEL, pagesGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildCheckBox(HOME_CHECKBOX, pagesGridPane, 1, 0, 1, 1, EMPTY_TEXT, ENABLED);
       ohBuilder.buildCheckBox(SYLLABUS_CHECKBOX, pagesGridPane, 2, 0, 1, 1, EMPTY_TEXT, ENABLED);
       ohBuilder.buildCheckBox(SCHEDULE_CHECKBOX, pagesGridPane, 3, 0, 1, 1, EMPTY_TEXT, ENABLED);
       ohBuilder.buildCheckBox(HWS_CHECKBOX, pagesGridPane, 4, 0, 1, 1, EMPTY_TEXT, ENABLED);
       
       GridPane styleGridPane=ohBuilder.buildGridPane(STYLE_GRID_PANE, siteBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(STYLE_LABEL, styleGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildTextButton(FAVICON_LABEL, styleGridPane, 0, 1, 1, 1,CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(NAVBAR_LABEL, styleGridPane, 0, 2, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(LEFT_FOOTER_LABEL, styleGridPane, 0, 3, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(RIGHT_FOOTER_LABEL, styleGridPane, 0, 4, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildLabel(STYLE_SHEET_LABEL, styleGridPane, 0, 5, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox styleSheetComboBox=ohBuilder.buildComboBox(STYLE_SHEET_COMBO_BOX, styleGridPane, 1, 5, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       styleSheetComboBox.setPrefWidth(200);
       styleSheetComboBox.getItems().add("sea_wolf.css");
       styleSheetComboBox.getItems().add("bing_bearcats.css");
       ohBuilder.buildLabel(STYLE_NOTE_LABEL, styleGridPane, 0, 6, 1, 1, EMPTY_TEXT, ENABLED);
       
       GridPane instructorGridPane=ohBuilder.buildGridPane(INSTRUCTOR_GRID_PANE, siteBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(INSTRUCTOR_LABEL, instructorGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildLabel(NAME_LABEL, instructorGridPane, 0, 1, 1, 1, EMPTY_TEXT, ENABLED);
       TextField nameTextField=ohBuilder.buildTextField(NAME_TEXT_FIELD, instructorGridPane, 1, 1, 1, 1, EMPTY_TEXT, ENABLED);
       nameTextField.setPrefWidth(100);
       ohBuilder.buildLabel(ROOM_LABEL, instructorGridPane, 2, 1, 1, 1, EMPTY_TEXT, ENABLED);
       TextField roomTextField=ohBuilder.buildTextField(ROOM_TEXT_FIELD, instructorGridPane, 3, 1, 1, 1, EMPTY_TEXT, ENABLED);
       roomTextField.setPrefWidth(100);
       ohBuilder.buildLabel(EMAIL_LABEL, instructorGridPane, 0, 2, 1, 1, EMPTY_TEXT, ENABLED);
       TextField emailTextField=ohBuilder.buildTextField(EMAIL_TEXT_FIELD, instructorGridPane, 1, 2, 1, 1, EMPTY_TEXT, ENABLED);
       emailTextField.setPrefWidth(100);
       ohBuilder.buildLabel(HOME_PAGE_LABEL, instructorGridPane, 2, 2, 1, 1, EMPTY_TEXT, ENABLED);
       TextField homePageTextField=ohBuilder.buildTextField(HOME_PAGE_TEXT_FIELD, instructorGridPane, 3, 2, 1, 1, EMPTY_TEXT, ENABLED);
       homePageTextField.setPrefWidth(100);
       TextArea hoursTextArea=ohBuilder.buildTextArea(INSTRUCTOR_HOURS_TEXT_AREA, instructorGridPane, EMPTY_TEXT, ENABLED);
       ohBuilder.buildTitledPane(INSTRUCTOR_HOURS_TITLED_PANE, instructorGridPane, 0, 3, 1, 1, EMPTY_TEXT, ENABLED, hoursTextArea);
       

        site.setContent(scrollPane);
        Pane.getTabs().add(site);
        
        
       //SYLLABUS PAGE
       Tab syllabus=ohBuilder.buildTab(SYLLABUS_TAB, null, EMPTY_TEXT, ENABLED);
       Pane.getTabs().add(syllabus);
       VBox syllabusBox= ohBuilder.buildVBox(SYLLABUS_VBOX, null, CLASS_VBOX, ENABLED);
       syllabusBox.prefWidthProperty().bind(Pane.widthProperty());
       ScrollPane syllabusScrollPane= new ScrollPane(syllabusBox);
       syllabusScrollPane.setMaxHeight(600);
       syllabus.setContent(syllabusScrollPane);
       TextArea descriptionTextArea=ohBuilder.buildTextArea(DESCRIPTION_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(DESCRIPTION_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, descriptionTextArea);
       
       TextArea topicsTextArea=ohBuilder.buildTextArea(TOPICS_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(TOPICS_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, topicsTextArea);
       
       TextArea prerequisitesTextArea=ohBuilder.buildTextArea(PREREQUISITES_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(PREREQUISITES_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, prerequisitesTextArea);
       
       TextArea outcomesTextArea=ohBuilder.buildTextArea(OUTCOMES_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(OUTCOMES_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, outcomesTextArea);
       
       TextArea textbooksTextArea=ohBuilder.buildTextArea(TEXTBOOKS_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(TEXTBOOKS_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, textbooksTextArea);
       
       TextArea gradedComponentsTextArea=ohBuilder.buildTextArea(GRADED_COMPONENTS_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(GRADED_COMPONENTS_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, gradedComponentsTextArea);
       
       TextArea gradingNoteTextArea=ohBuilder.buildTextArea(GRADING_NOTE_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(GRADING_NOTE_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, gradingNoteTextArea);
       
       TextArea academicDishonestyTextArea=ohBuilder.buildTextArea(ACADEMIC_DISHONESTY_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(ACADEMIC_DISHONESTY_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, academicDishonestyTextArea);
       
       TextArea specialAssistanceTextArea=ohBuilder.buildTextArea(SPECIAL_ASSISTANCE_TEXT_AREA, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTitledPane(SPECIAL_ASSISTANCE_TITLED_PANE, syllabusBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED, specialAssistanceTextArea);
       
       //MEETING TIMES PAGE
       Tab meetingTimes=ohBuilder.buildTab(MEETING_TIMES_TAB, null, EMPTY_TEXT, ENABLED);
       Pane.getTabs().add(meetingTimes);
       VBox meetingTimesVBox =ohBuilder.buildVBox(MEETING_TIMES_VBOX, null, CLASS_VBOX, ENABLED);
       meetingTimesVBox.prefWidthProperty().bind(Pane.widthProperty());
       ScrollPane meetingTimesScrollPane= new ScrollPane(meetingTimesVBox);
       meetingTimesScrollPane.setMaxHeight(600);
       meetingTimes.setContent(meetingTimesScrollPane);
       GridPane lecturesGridPane=ohBuilder.buildGridPane(LECTURES_GRID_PANE, meetingTimesVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTextButton(LECTURES_ADD_BUTTON, lecturesGridPane, 0, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(LECTURES_REMOVE_BUTTON, lecturesGridPane, 1, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildLabel(LECTURES_LABEL, lecturesGridPane, 2, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       TableView<MeetingTimesPrototype> lecturesTable= ohBuilder.buildTableView(LECTURES_TABLE, lecturesGridPane, 0,1,1,1, EMPTY_TEXT, ENABLED);
       lecturesTable.prefWidthProperty().bind(lecturesGridPane.widthProperty());
       lecturesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       TableColumn sectionColumn = ohBuilder.buildTableColumn(SECTION_TABLE_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        TableColumn daysColumn = ohBuilder.buildTableColumn(DAYS_TABLE_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        TableColumn timeColumn = ohBuilder.buildTableColumn(TIME_TABLE_COLUMN, lecturesTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn roomColumn = ohBuilder.buildTableColumn(ROOM_TABLE_COLUMN, lecturesTable, CLASS_OH_COLUMN);
        sectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("days"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        sectionColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 5.0));
        daysColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 5.0));
        timeColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(2.0 / 5.0));
        roomColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 5.0));
        
        
        sectionColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
            timeColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
            roomColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
            daysColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
       
        
        GridPane recitationsGridPane=ohBuilder.buildGridPane(RECITATIONS_GRID_PANE, meetingTimesVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTextButton(RECITATIONS_ADD_BUTTON, recitationsGridPane, 0, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(RECITATIONS_REMOVE_BUTTON, recitationsGridPane, 1, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildLabel(RECITATIONS_LABEL, recitationsGridPane, 2, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       TableView<MeetingTimesPrototype> recitationsTable= ohBuilder.buildTableView(RECITATIONS_TABLE, recitationsGridPane, 0,1,1,1, EMPTY_TEXT, ENABLED);
       recitationsTable.prefWidthProperty().bind(recitationsGridPane.widthProperty());
       TableColumn recitationsSectionColumn = ohBuilder.buildTableColumn(RECITATIONS_SECTION_TABLE_COLUMN, recitationsTable, CLASS_OH_COLUMN);
       TableColumn recitationsDaysColumn = ohBuilder.buildTableColumn(RECITATIONS_DAY_TIME_TABLE_COLUMN, recitationsTable, CLASS_OH_CENTERED_COLUMN);
       TableColumn recitationsRoomColumn = ohBuilder.buildTableColumn(RECITATIONS_ROOM_TABLE_COLUMN, recitationsTable, CLASS_OH_COLUMN);
       TableColumn recitationsTA1Column = ohBuilder.buildTableColumn(RECITATIONS_TA1_TABLE_COLUMN, recitationsTable, CLASS_OH_COLUMN);
       TableColumn recitationsTA2Column = ohBuilder.buildTableColumn(RECITATIONS_TA2_TABLE_COLUMN, recitationsTable, CLASS_OH_COLUMN);
         
        recitationsSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        recitationsDaysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("day_time"));
        recitationsRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        recitationsTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta_1"));
        recitationsTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta_2"));
        recitationsSectionColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 6.0));
        recitationsDaysColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(2.0 / 6.0));
        recitationsTA1Column.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 6.0));
        recitationsTA2Column.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 6.0));
        recitationsRoomColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 6.0));
        
         GridPane labsGridPane=ohBuilder.buildGridPane(LABS_GRID_PANE, meetingTimesVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildTextButton(LABS_ADD_BUTTON, labsGridPane, 0, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(LABS_REMOVE_BUTTON, labsGridPane, 1, 0, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildLabel(LABS_LABEL, labsGridPane, 2, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       TableView<MeetingTimesPrototype> labsTable= ohBuilder.buildTableView(LABS_TABLE, labsGridPane, 0,1,1,1, EMPTY_TEXT, ENABLED);
       labsTable.prefWidthProperty().bind(labsGridPane.widthProperty());
       TableColumn labsSectionColumn = ohBuilder.buildTableColumn(LABS_SECTION_TABLE_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsDaysColumn = ohBuilder.buildTableColumn(LABS_DAYS_TABLE_COLUMN, labsTable, CLASS_OH_COLUMN);
         TableColumn labsRoomColumn = ohBuilder.buildTableColumn(LABS_ROOM_TABLE_COLUMN, labsTable, CLASS_OH_COLUMN);
        TableColumn labsTA1Column = ohBuilder.buildTableColumn(LABS_TA1_TABLE_COLUMN, labsTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn labsTA2Column = ohBuilder.buildTableColumn(LABS_TA2_TABLE_COLUMN, labsTable, CLASS_OH_CENTERED_COLUMN);
       
        labsSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        labsDaysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("day_time"));
        labsRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        labsTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta_1"));
        labsTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta_2"));
        labsSectionColumn.prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0 / 6.0));
        labsDaysColumn.prefWidthProperty().bind(labsTable.widthProperty().multiply(2.0 / 6.0));
        labsTA1Column.prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0 / 6.0));
        labsTA2Column.prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0 / 6.0));
        labsRoomColumn.prefWidthProperty().bind(labsTable.widthProperty().multiply(1.0 / 6.0));
       
        //OFFICE HOURS PAGE
        Tab officeHours=ohBuilder.buildTab(OFFICE_HOURS_TAB, null, EMPTY_TEXT, ENABLED);
        Pane.getTabs().add(officeHours);
        
        VBox leftPane = ohBuilder.buildVBox(OH_LEFT_PANE, null, CLASS_OH_PANE, ENABLED);
    
        HBox tasHeaderBox = ohBuilder.buildHBox(OH_TAS_HEADER_PANE, leftPane, CLASS_OH_BOX, ENABLED);
        ohBuilder.buildLabel(OfficeHoursPropertyType.OH_TAS_HEADER_LABEL, tasHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        HBox typeHeaderBox = ohBuilder.buildHBox(OH_GRAD_UNDERGRAD_TAS_PANE, tasHeaderBox, CLASS_OH_RADIO_BOX, ENABLED);
        ToggleGroup tg = new ToggleGroup();
        ohBuilder.buildRadioButton(OH_ALL_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, true);
        ohBuilder.buildRadioButton(OH_GRAD_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);
        ohBuilder.buildRadioButton(OH_UNDERGRAD_RADIO_BUTTON, typeHeaderBox, CLASS_OH_RADIO_BUTTON, ENABLED, tg, false);
        
        // MAKE THE TABLE AND SETUP THE DATA MODEL
        TableView<TeachingAssistantPrototype> taTable = ohBuilder.buildTableView(OH_TAS_TABLE_VIEW, leftPane, CLASS_OH_TABLE_VIEW, ENABLED);
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn nameColumn = ohBuilder.buildTableColumn(OH_NAME_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn emailColumn = ohBuilder.buildTableColumn(OH_EMAIL_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        TableColumn slotsColumn = ohBuilder.buildTableColumn(OH_SLOTS_TABLE_COLUMN, taTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn typeColumn = ohBuilder.buildTableColumn(OH_TYPE_TABLE_COLUMN, taTable, CLASS_OH_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("slots"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(2.0 / 5.0));
        slotsColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0 / 5.0));
        
        
        // ADD BOX FOR ADDING A TA
        HBox taBox = ohBuilder.buildHBox(OH_ADD_TA_PANE, leftPane, CLASS_OH_PANE, ENABLED);
        ohBuilder.buildTextField(OH_NAME_TEXT_FIELD, taBox, CLASS_OH_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(OH_EMAIL_TEXT_FIELD, taBox, CLASS_OH_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextButton(OH_ADD_TA_BUTTON, taBox, CLASS_OH_BUTTON, !ENABLED);

        // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
        VBox.setVgrow(taTable, Priority.ALWAYS);

        // INIT THE HEADER ON THE RIGHT
        VBox rightPane = ohBuilder.buildVBox(OH_RIGHT_PANE, null, CLASS_OH_PANE, ENABLED);
        HBox officeHoursHeaderBox = ohBuilder.buildHBox(OH_OFFICE_HOURS_HEADER_PANE, rightPane, CLASS_OH_PANE, ENABLED);
        ohBuilder.buildLabel(OH_OFFICE_HOURS_HEADER_LABEL, officeHoursHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        ohBuilder.buildDatePicker(OH_OFFICE_HOURS_START_DATE_PICKER, officeHoursHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        ohBuilder.buildDatePicker(OH_OFFICE_HOURS_END_DATE_PICKER, officeHoursHeaderBox, CLASS_OH_HEADER_LABEL, ENABLED);
        // SETUP THE OFFICE HOURS TABLE
        TableView<TimeSlot> officeHoursTable = ohBuilder.buildTableView(OH_OFFICE_HOURS_TABLE_VIEW, rightPane, CLASS_OH_OFFICE_HOURS_TABLE_VIEW, ENABLED);
        setupOfficeHoursColumn(OH_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "startTime");
        setupOfficeHoursColumn(OH_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_OH_TIME_COLUMN, "endTime");
        setupOfficeHoursColumn(OH_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "monday");
        setupOfficeHoursColumn(OH_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "tuesday");
        setupOfficeHoursColumn(OH_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "wednesday");
        setupOfficeHoursColumn(OH_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "thursday");
        setupOfficeHoursColumn(OH_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_OH_DAY_OF_WEEK_COLUMN, "friday");

        // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
        VBox.setVgrow(officeHoursTable, Priority.ALWAYS);

        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        SplitPane sPane = new SplitPane(leftPane, rightPane);
        sPane.setOrientation(Orientation.VERTICAL);
        sPane.setDividerPositions(.4);
        ScrollPane ohScrollPane= new ScrollPane(sPane);
        ohScrollPane.setMaxHeight(600);
        
        officeHours.setContent(ohScrollPane);
        
        //SCHEDULE TAB
       Tab schedule=ohBuilder.buildTab(SCHEDULE_TAB, null, EMPTY_TEXT, ENABLED);
       Pane.getTabs().add(schedule);
       VBox scheduleVBox =ohBuilder.buildVBox(SCHEDULE_VBOX, null, CLASS_VBOX, ENABLED);
       scheduleVBox.prefWidthProperty().bind(Pane.widthProperty());
       ScrollPane scheduleScrollPane= new ScrollPane(scheduleVBox);
       scheduleScrollPane.setMaxHeight(600);
       schedule.setContent(scheduleScrollPane);
       GridPane calendarGridPane=ohBuilder.buildGridPane(CALENDAR_GRID_PANE, scheduleVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(CALENDAR_LABEL, calendarGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildLabel(CALENDAR_START_LABEL, calendarGridPane, 1, 0, 1, 1, EMPTY_TEXT,ENABLED);
       ohBuilder.buildDatePicker(START_DATE_PICKER, calendarGridPane, 1, 1, 1, 1, EMPTY_TEXT, ENABLED);
       
       ohBuilder.buildLabel(CALENDAR_END_LABEL, calendarGridPane, 1, 2, 1, 1, EMPTY_TEXT,ENABLED);
       ohBuilder.buildDatePicker(END_DATE_PICKER, calendarGridPane, 1, 3, 1, 1, EMPTY_TEXT, ENABLED);
        
        GridPane scheduleItemsGridPane= ohBuilder.buildGridPane(SCHEDULE_ITEMS_GRID_PANE, scheduleVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
        ohBuilder.buildLabel(SCHEDULE_ITEMS_LABEL,scheduleItemsGridPane,0, 0, 1, 1, CLASS_OH_HEADING, ENABLED );
       TableView<MeetingTimesPrototype> scheduleItemsTable= ohBuilder.buildTableView(SCHEDULE_ITEMS_TABLE, scheduleItemsGridPane, 0,1,1,1, EMPTY_TEXT, ENABLED);
       scheduleItemsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       TableColumn scheduleTypeColumn = ohBuilder.buildTableColumn(SCHEDULE_TYPE_TABLE_COLUMN, scheduleItemsTable, CLASS_OH_COLUMN);
        TableColumn scheduleDateColumn = ohBuilder.buildTableColumn(SCHEDULE_DATE_TABLE_COLUMN, scheduleItemsTable, CLASS_OH_COLUMN);
        TableColumn scheduleTitleColumn = ohBuilder.buildTableColumn(SCHEDULE_TITLE_TABLE_COLUMN, scheduleItemsTable, CLASS_OH_CENTERED_COLUMN);
        TableColumn scheduleTopicColumn = ohBuilder.buildTableColumn(SCHEDULE_TOPIC_TABLE_COLUMN, scheduleItemsTable, CLASS_OH_COLUMN);
        scheduleTypeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        scheduleDateColumn.setCellValueFactory(new PropertyValueFactory<String, String>("month"));
        scheduleTitleColumn.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
        scheduleTopicColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));
        
        scheduleTypeColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 4.0));
        scheduleDateColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 4.0));
        scheduleTitleColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 4.0));
        scheduleTopicColumn.prefWidthProperty().bind(recitationsTable.widthProperty().multiply(1.0 / 4.0));
        
       GridPane addScheduleItemsGridPane= ohBuilder.buildGridPane(ADD_SCHEDULE_ITEMS_GRID_PANE, scheduleVBox, CLASS_OH_DIALOG_GRID_PANE, ENABLED);
       ohBuilder.buildLabel(ADD_SCHEDULE_ITEMS_LABEL, addScheduleItemsGridPane, 0, 0, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildLabel(TYPE_LABEL, addScheduleItemsGridPane, 0, 1, 1, 1, EMPTY_TEXT, ENABLED);
       ComboBox typeComboBox=ohBuilder.buildComboBox(TYPE_COMBO_BOX, addScheduleItemsGridPane, 1, 1, 1, 1, EMPTY_TEXT, ENABLED, OH_OK_PROMPT, EMPTY_TEXT);
       typeComboBox.getItems().add("Holidays");
       typeComboBox.getItems().add("Lectures");
       typeComboBox.getItems().add("References");
       typeComboBox.getItems().add("Recitations");
       typeComboBox.getItems().add("HWs");
       typeComboBox.setPrefWidth(200);
       ohBuilder.buildLabel(DATE_LABEL, addScheduleItemsGridPane, 0, 2, 1, 1, EMPTY_TEXT, ENABLED);
       ohBuilder.buildDatePicker(ADD_ITEM_DATE_PICKER, addScheduleItemsGridPane, 1, 2, 1, 1, EMPTY_TEXT, ENABLED );
       ohBuilder.buildLabel(ADD_ITEM_TITLE_LABEL, addScheduleItemsGridPane, 0, 3, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildTextField(ADD_ITEM_TITLE_TEXT_FIELD, addScheduleItemsGridPane, 1, 3, 1, 1,CLASS_OH_TEXT_FIELD, ENABLED);
       ohBuilder.buildLabel(ADD_ITEM_TOPIC_LABEL, addScheduleItemsGridPane, 0, 4, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildTextField(ADD_ITEM_TOPIC_TEXT_FIELD, addScheduleItemsGridPane, 1, 4, 1, 1,CLASS_OH_TEXT_FIELD, ENABLED);
       ohBuilder.buildLabel(ADD_ITEM_LINK_LABEL, addScheduleItemsGridPane, 0, 5, 1, 1, CLASS_OH_HEADING, ENABLED);
       ohBuilder.buildTextField(ADD_ITEM_LINK_TEXT_FIELD, addScheduleItemsGridPane, 1, 5, 1, 1,CLASS_OH_TEXT_FIELD, ENABLED);
       ohBuilder.buildTextButton(ADD_ITEM_TEXT_BUTTON, addScheduleItemsGridPane, 0, 6, 1, 1, CLASS_OH_BUTTON, ENABLED);
       ohBuilder.buildTextButton(CLEAR_ITEM_TEXT_BUTTON, addScheduleItemsGridPane, 1, 6, 1, 1, CLASS_OH_BUTTON, ENABLED);
       
       workspace = pane;
       Pane.tabMinWidthProperty().bind(workspace.widthProperty().multiply(1/5));
    }
    private void setupOfficeHoursColumn(Object columnId, TableView tableView, String styleClass, String columnDataProperty) {
        AppNodesBuilder builder = app.getGUIModule().getNodesBuilder();
        TableColumn<TeachingAssistantPrototype, String> column = builder.buildTableColumn(columnId, tableView, styleClass);
        column.setCellValueFactory(new PropertyValueFactory<TeachingAssistantPrototype, String>(columnDataProperty));
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(1.0 / 7.0));
        column.setCellFactory(col -> {
            return new TableCell<TeachingAssistantPrototype, String>() {
                @Override
                protected void updateItem(String text, boolean empty) {
                    super.updateItem(text, empty);
                    if (text == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // CHECK TO SEE IF text CONTAINS THE NAME OF
                        // THE CURRENTLY SELECTED TA
                        setText(text);
                        TableView<TeachingAssistantPrototype> tasTableView = (TableView) app.getGUIModule().getGUINode(OH_TAS_TABLE_VIEW);
                        TeachingAssistantPrototype selectedTA = tasTableView.getSelectionModel().getSelectedItem();
                        if (selectedTA == null) {
                            setStyle("");
                        } else if (text.contains(selectedTA.getName())) {
                            setStyle("-fx-background-color: yellow");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
    }

    public void initControllers() {
        OfficeHoursController controller = new OfficeHoursController((CourseSiteGeneratorApp) app);
        AppGUIModule gui = app.getGUIModule();

        // FOOLPROOF DESIGN STUFF
        TextField nameTextField = ((TextField) gui.getGUINode(OH_NAME_TEXT_FIELD));
        TextField emailTextField = ((TextField) gui.getGUINode(OH_EMAIL_TEXT_FIELD));

        nameTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });
        emailTextField.textProperty().addListener(e -> {
            controller.processTypeTA();
        });

        // FIRE THE ADD EVENT ACTION
        nameTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.processAddTA();
        });
        ((Button) gui.getGUINode(OH_ADD_TA_BUTTON)).setOnAction(e -> {
            controller.processAddTA();
        });
        
        TableView officeHoursTableView = (TableView) gui.getGUINode(OH_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.getSelectionModel().setCellSelectionEnabled(true);
        officeHoursTableView.setOnMouseClicked(e -> {
            controller.processToggleOfficeHours();
        });
        
        TableView lecturesTableView= (TableView) gui.getGUINode(LECTURES_TABLE);
        
        TableColumn sectionColumn=(TableColumn)lecturesTableView.getColumns().get(0);
        TableColumn daysColumn=(TableColumn)lecturesTableView.getColumns().get(1);
        TableColumn timeColumn=(TableColumn)lecturesTableView.getColumns().get(2);
        TableColumn roomColumn=(TableColumn)lecturesTableView.getColumns().get(3);
        
        
        lecturesTableView.setOnMouseClicked(e -> {
            lecturesTableView.setEditable(true);
            sectionColumn.setEditable(ENABLED);
            timeColumn.setEditable(ENABLED);
            roomColumn.setEditable(ENABLED);
            daysColumn.setEditable(ENABLED);
            
        });
        
        Button styleButton=(Button) gui.getGUINode(FAVICON_LABEL);
        styleButton.setOnAction(e->{
            controller.processEditFaviconIcon();
        });
        Button removeLectureButton=((Button) gui.getGUINode(LECTURES_REMOVE_BUTTON));
        removeLectureButton.setOnAction(e-> {
            if(lecturesTableView.getSelectionModel().getSelectedItems().size()>0)
            {
               controller.processRemoveLecture();
            }
            });
        
        TableView recitationsTableView= (TableView) gui.getGUINode(RECITATIONS_TABLE);
        
        Button removeRecitationButton=((Button) gui.getGUINode(RECITATIONS_REMOVE_BUTTON));
        removeRecitationButton.setOnAction(e-> {
            if(recitationsTableView.getSelectionModel().getSelectedItems().size()>0)
            {
               controller.processRemoveRecitation();
            }
            });
                
           TableView labsTableView= (TableView) gui.getGUINode(LABS_TABLE);
           Button removeLabButton=((Button) gui.getGUINode(LABS_REMOVE_BUTTON));
            removeLabButton.setOnAction(e-> {
            if(labsTableView.getSelectionModel().getSelectedItems().size()>0)
            {
               controller.processRemoveLab();
            }
            });

        // DON'T LET ANYONE SORT THE TABLES
        TableView tasTableView = (TableView) gui.getGUINode(OH_TAS_TABLE_VIEW);
        for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
            ((TableColumn) officeHoursTableView.getColumns().get(i)).setSortable(false);
        }
        for (int i = 0; i < tasTableView.getColumns().size(); i++) {
            ((TableColumn) tasTableView.getColumns().get(i)).setSortable(false);
        }

        tasTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
            if (e.getClickCount() == 2) {
                controller.processEditTA();
            }
            controller.processSelectTA();
        });
        TableView scheduleTable= (TableView) gui.getGUINode(SCHEDULE_ITEMS_TABLE);
        scheduleTable.setOnMouseClicked(e->{
            SchedulePrototype oldSchedule=(SchedulePrototype)scheduleTable.getSelectionModel().getSelectedItem();
            ComboBox typeComboBox=(ComboBox) gui.getGUINode(TYPE_COMBO_BOX);
            typeComboBox.getSelectionModel().select(oldSchedule.getType());
            
            TextField title=(TextField) gui.getGUINode(ADD_ITEM_TITLE_TEXT_FIELD);
            title.setText(oldSchedule.getTitle());
            TextField topic=(TextField) gui.getGUINode(ADD_ITEM_TOPIC_TEXT_FIELD);
            topic.setText(oldSchedule.getTopic());
            TextField link=(TextField) gui.getGUINode(ADD_ITEM_LINK_TEXT_FIELD);
            link.setText(oldSchedule.getLink());
        });
        
        Button addScheduleButton=(Button) gui.getGUINode(ADD_ITEM_TEXT_BUTTON);
        addScheduleButton.setOnAction(e-> {
            
            if(scheduleTable.getSelectionModel().getSelectedItems().isEmpty())
            {
                controller.processAddScheduleItem();
            }
            else
            {
                controller.processEditScheduleItem();
                scheduleTable.refresh();
            }
             
        });
        ComboBox styleComboBox=(ComboBox) gui.getGUINode(STYLE_SHEET_COMBO_BOX);
        styleComboBox.setOnAction(e->{
            controller.processEditStyleSheet();
        });
        Button scheduleClearButton=(Button) gui.getGUINode(CLEAR_ITEM_TEXT_BUTTON);
        scheduleClearButton.setOnAction(e -> {
            ComboBox typeComboBox=(ComboBox) gui.getGUINode(TYPE_COMBO_BOX);
            typeComboBox.getSelectionModel().clearSelection();
            DatePicker dateDatePicker=(DatePicker) gui.getGUINode(ADD_ITEM_DATE_PICKER);
            dateDatePicker.getEditor().clear();
            TextField title=(TextField) gui.getGUINode(ADD_ITEM_TITLE_TEXT_FIELD);
            title.clear();
            TextField topic=(TextField) gui.getGUINode(ADD_ITEM_TOPIC_TEXT_FIELD);
            topic.clear();
            TextField link=(TextField) gui.getGUINode(ADD_ITEM_LINK_TEXT_FIELD);
            link.clear();
            
            scheduleTable.getSelectionModel().clearSelection();
        });
        RadioButton allRadio = (RadioButton) gui.getGUINode(OH_ALL_RADIO_BUTTON);
        allRadio.setOnAction(e -> {
            controller.processSelectAllTAs();
        });
        RadioButton gradRadio = (RadioButton) gui.getGUINode(OH_GRAD_RADIO_BUTTON);
        gradRadio.setOnAction(e -> {
            controller.processSelectGradTAs();
        });
        RadioButton undergradRadio = (RadioButton) gui.getGUINode(OH_UNDERGRAD_RADIO_BUTTON);
        undergradRadio.setOnAction(e -> {
            controller.processSelectUndergradTAs();
        });
        
        TextArea instructorHoursTextArea= (TextArea) gui.getGUINode(INSTRUCTOR_HOURS_TEXT_AREA);
        instructorHoursTextArea.setOnMouseClicked(e -> {
            controller.processEditInstructor();
        });
        TextField instructorNameTextField=(TextField) gui.getGUINode(NAME_TEXT_FIELD);
        instructorNameTextField.setOnMouseClicked(e -> {
            controller.processEditInstructor();
        });
        TextField instructorRoomTextField=(TextField) gui.getGUINode(ROOM_TEXT_FIELD);
        instructorRoomTextField.setOnMouseClicked(e -> {
            controller.processEditInstructor();
        });
        TextField instructorEmailTextField=(TextField) gui.getGUINode(EMAIL_TEXT_FIELD);
        instructorEmailTextField.setOnMouseClicked(e -> {
            controller.processEditInstructor();
        });
        TextField instructorHomeTextField=(TextField) gui.getGUINode(HOME_PAGE_TEXT_FIELD);
        instructorHomeTextField.setOnMouseClicked(e -> {
            controller.processEditInstructor();
        });
        ComboBox subjectComboBox=(ComboBox) gui.getGUINode(SUBJECT_COMBO_BOX);
        subjectComboBox.setOnAction(e -> {
            controller.processEditExportDir();
            controller.processEditSubjectComboBox();
        });
        ComboBox numberComboBox=(ComboBox) gui.getGUINode(NUMBER_COMBO_BOX);
        numberComboBox.setOnAction(e -> {
            controller.processEditExportDir();
            controller.processEditNumberComboBox();
        });
        ComboBox semesterComboBox=(ComboBox) gui.getGUINode(SEMESTER_COMBO_BOX);
        semesterComboBox.setOnAction(e -> {
            controller.processEditExportDir();
        });
        ComboBox yearComboBox=(ComboBox) gui.getGUINode(YEAR_COMBO_BOX);
        yearComboBox.setOnAction(e -> {
            controller.processEditExportDir();
        });
        CheckBox homeCheckBox=((CheckBox) gui.getGUINode(HOME_CHECKBOX));
        homeCheckBox.setOnAction(e -> {
            controller.processEditPages();
        });
        CheckBox syllabusCheckBox=((CheckBox) gui.getGUINode(SYLLABUS_CHECKBOX));
        syllabusCheckBox.setOnAction(e -> {
            controller.processEditPages();
        });
        CheckBox scheduleCheckBox=((CheckBox) gui.getGUINode(SCHEDULE_CHECKBOX));
        scheduleCheckBox.setOnAction(e -> {
            controller.processEditPages();
        });
        CheckBox hwsCheckBox=((CheckBox) gui.getGUINode(HWS_CHECKBOX));
        hwsCheckBox.setOnAction(e -> {
            controller.processEditPages();
        });
        
        Button lecturesAddButton=((Button) gui.getGUINode(LECTURES_ADD_BUTTON));
        lecturesAddButton.setOnAction(e -> {
            controller.processAddLecture();
        });
        Button recitationsAddButton=((Button) gui.getGUINode(RECITATIONS_ADD_BUTTON));
        recitationsAddButton.setOnAction(e -> {
            controller.processAddRecitation();
        });
        Button labsAddButton=((Button) gui.getGUINode(LABS_ADD_BUTTON));
        labsAddButton.setOnAction(e -> {
            controller.processAddLab();
        });

    }

    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(OH_FOOLPROOF_SETTINGS,
                new OfficeHoursFoolproofDesign((CourseSiteGeneratorApp) app));
    }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }

    @Override
    public void showNewDialog() {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }
}
