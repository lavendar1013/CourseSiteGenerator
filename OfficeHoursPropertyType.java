package csg;

/**
 * This class provides the properties that are needed to be loaded for
 * setting up To Do List Maker workspace controls including language-dependent
 * text.
 * 
 * @author Richard McKenna
 * @version 1.0
 */
public enum OfficeHoursPropertyType {

    /* THESE ARE THE NODES IN OUR APP */
    // FOR SIMPLE OK/CANCEL DIALOG BOXES
    OH_OK_PROMPT,
    OH_CANCEL_PROMPT,
    
    COURSE_BUILDER_TAB_PANE,
    
    SITE_TAB,
    SITE_VBOX,
    BANNER_LABEL,
    BANNER_GRID_PANE,
    SUBJECT_LABEL,
    SUBJECT_COMBO_BOX,
    NUMBER_LABEL,
    NUMBER_COMBO_BOX,
    SEMESTER_LABEL,
    SEMESTER_COMBO_BOX,
    YEAR_LABEL,
    YEAR_COMBO_BOX,
    TITLE_LABEL,
    TITLE_COMBO_BOX,
    EXPORT_LABEL,
    PAGES_GRID_PANE,
    PAGES_LABEL,
    HOME_CHECKBOX,
    SYLLABUS_CHECKBOX,
    HWS_CHECKBOX,
    SCHEDULE_CHECKBOX,
    STYLE_GRID_PANE,
    STYLE_LABEL,
    FAVICON_LABEL,
    NAVBAR_LABEL,
    LEFT_FOOTER_LABEL,
    RIGHT_FOOTER_LABEL,
    STYLE_SHEET_LABEL,
    STYLE_SHEET_COMBO_BOX,
    STYLE_NOTE_LABEL,
    INSTRUCTOR_GRID_PANE,
    INSTRUCTOR_LABEL,
    INSTRUCTOR_HOURS_TITLED_PANE,
    INSTRUCTOR_HOURS_TEXT_AREA,
    NAME_LABEL,
    NAME_TEXT_FIELD,
    ROOM_LABEL,
    ROOM_TEXT_FIELD,
    EMAIL_LABEL,
    EMAIL_TEXT_FIELD,
    HOME_PAGE_LABEL,
    HOME_PAGE_TEXT_FIELD,
    OFFICE_HOURS_TEXT_BUTTON,
    
    SYLLABUS_TAB,
    SYLLABUS_VBOX,
    DESCRIPTION_GRID_PANE,
    DESCRIPTION_TEXT_BUTTON,
    DESCRIPTION_LABEL,
    TOPICS_GRID_PANE,
    TOPICS_TEXT_BUTTON,
    TOPICS_LABEL,
    PREREQUISITES_GRID_PANE,
    PREREQUISITES_TEXT_BUTTON,
    PREREQUISITES_LABEL,
    TEXTBOOKS_GRID_PANE,
    TEXTBOOKS_TEXT_BUTTON,
    TEXTBOOKS_LABEL,
    OUTCOMES_GRID_PANE,
    OUTCOMES_TEXT_BUTTON,
    OUTCOMES_LABEL,
    GRADING_NOTE_GRID_PANE,
    GRADING_NOTE_TEXT_BUTTON,
    GRADING_NOTE_LABEL,
    GRADED_COMPONENTS_GRID_PANE,
    GRADED_COMPONENTS_TEXT_BUTTON,
    GRADED_COMPONENTS_LABEL,
    SPECIAL_ASSISTANCE_GRID_PANE,
    SPECIAL_ASSISTANCE_TEXT_BUTTON,
    SPECIAL_ASSISTANCE_LABEL,
    ACADEMIC_DISHONESTY_GRID_PANE,
    ACADEMIC_DISHONESTY_TEXT_BUTTON,
    ACADEMIC_DISHONESTY_LABEL,
    DESCRIPTION_TITLED_PANE,
    DESCRIPTION_TEXT_AREA,
    TOPICS_TITLED_PANE,
    TOPICS_TEXT_AREA,
    PREREQUISITES_TITLED_PANE,
    PREREQUISITES_TEXT_AREA,
    OUTCOMES_TITLED_PANE,
    OUTCOMES_TEXT_AREA,
    TEXTBOOKS_TITLED_PANE,
    TEXTBOOKS_TEXT_AREA,
    GRADED_COMPONENTS_TITLED_PANE,
    GRADED_COMPONENTS_TEXT_AREA,
    GRADING_NOTE_TITLED_PANE,
    GRADING_NOTE_TEXT_AREA,
    ACADEMIC_DISHONESTY_TITLED_PANE,
    ACADEMIC_DISHONESTY_TEXT_AREA,
    SPECIAL_ASSISTANCE_TITLED_PANE,
    SPECIAL_ASSISTANCE_TEXT_AREA,
    
    
    MEETING_TIMES_TAB,
    MEETING_TIMES_VBOX,
    LECTURES_GRID_PANE,
    LECTURES_ADD_BUTTON,
    LECTURES_REMOVE_BUTTON,
    LECTURES_LABEL,
    LECTURES_TABLE,
    SECTION_TABLE_COLUMN,
    DAYS_TABLE_COLUMN,
    TIME_TABLE_COLUMN,
    ROOM_TABLE_COLUMN,
    RECITATIONS_SECTION_TABLE_COLUMN,
    RECITATIONS_DAYS_TABLE_COLUMN,
    RECITATIONS_TIME_TABLE_COLUMN,
    RECITATIONS_ROOM_TABLE_COLUMN,
    RECITATIONS_TABLE,
    RECITATIONS_GRID_PANE,
    RECITATIONS_ADD_BUTTON,
    RECITATIONS_REMOVE_BUTTON,
    RECITATIONS_LABEL,
    RECITATIONS_TA1_TABLE_COLUMN,
    RECITATIONS_TA2_TABLE_COLUMN,
    EXPORT_DIR,
    
    LABS_SECTION_TABLE_COLUMN,
    LABS_DAYS_TABLE_COLUMN,
    LABS_TIME_TABLE_COLUMN,
    LABS_ROOM_TABLE_COLUMN,
    LABS_TABLE,
    LABS_GRID_PANE,
    LABS_ADD_BUTTON,
    LABS_REMOVE_BUTTON,
    LABS_LABEL,
    LABS_TA1_TABLE_COLUMN,
    LABS_TA2_TABLE_COLUMN,
    
    SCHEDULE_TAB,
    SCHEDULE_VBOX,
    CALENDAR_GRID_PANE,
    CALENDAR_LABEL,
    CALENDAR_START_LABEL,
    CALENDAR_END_LABEL,
    START_DATE_PICKER,
    END_DATE_PICKER,
    SCHEDULE_ITEMS_GRID_PANE,
    SCHEDULE_ITEMS_LABEL,
    SCHEDULE_ITEMS_TABLE,
    SCHEDULE_TYPE_TABLE_COLUMN,
    SCHEDULE_DATE_TABLE_COLUMN,
    SCHEDULE_TITLE_TABLE_COLUMN,
    SCHEDULE_TOPIC_TABLE_COLUMN,
    ADD_SCHEDULE_ITEMS_GRID_PANE,
    ADD_SCHEDULE_ITEMS_LABEL,
    TYPE_LABEL,
    DATE_LABEL,
    TYPE_COMBO_BOX,
    ADD_ITEM_DATE_PICKER,
    ADD_ITEM_TITLE_LABEL,
    ADD_ITEM_TITLE_TEXT_FIELD,
    ADD_ITEM_TOPIC_LABEL,
    ADD_ITEM_TOPIC_TEXT_FIELD,
    ADD_ITEM_LINK_LABEL,
    ADD_ITEM_LINK_TEXT_FIELD,
    ADD_ITEM_TEXT_BUTTON,
    CLEAR_ITEM_TEXT_BUTTON,
    RECITATIONS_DAY_TIME_TABLE_COLUMN,
    
    
    
    OFFICE_HOURS_TAB,
    OH_OFFICE_HOURS_START_DATE_PICKER,
    OH_OFFICE_HOURS_END_DATE_PICKER,
  

    // THESE ARE FOR TEXT PARTICULAR TO THE APP'S WORKSPACE CONTROLS
    OH_LEFT_PANE,
    OH_TAS_HEADER_PANE,
    OH_TAS_HEADER_LABEL,
    OH_GRAD_UNDERGRAD_TAS_PANE,
    OH_ALL_RADIO_BUTTON,
    OH_GRAD_RADIO_BUTTON,
    OH_UNDERGRAD_RADIO_BUTTON,
    OH_TAS_HEADER_TEXT_FIELD,
    OH_TAS_TABLE_VIEW,
    OH_NAME_TABLE_COLUMN,
    OH_EMAIL_TABLE_COLUMN,
    OH_SLOTS_TABLE_COLUMN,
    OH_TYPE_TABLE_COLUMN,

    OH_ADD_TA_PANE,
    OH_NAME_TEXT_FIELD,
    OH_EMAIL_TEXT_FIELD,
    OH_ADD_TA_BUTTON,

    OH_RIGHT_PANE,
    OH_OFFICE_HOURS_HEADER_PANE,
    OH_OFFICE_HOURS_HEADER_LABEL,
    OH_OFFICE_HOURS_TABLE_VIEW,
    OH_START_TIME_TABLE_COLUMN,
    OH_END_TIME_TABLE_COLUMN,
    OH_MONDAY_TABLE_COLUMN,
    OH_TUESDAY_TABLE_COLUMN,
    OH_WEDNESDAY_TABLE_COLUMN,
    OH_THURSDAY_TABLE_COLUMN,
    OH_FRIDAY_TABLE_COLUMN,
    OH_DAYS_OF_WEEK,
    OH_FOOLPROOF_SETTINGS,
    
    // FOR THE EDIT DIALOG
    OH_TA_EDIT_DIALOG,
    OH_TA_DIALOG_GRID_PANE,
    OH_TA_DIALOG_HEADER_LABEL, 
    OH_TA_DIALOG_NAME_LABEL,
    OH_TA_DIALOG_NAME_TEXT_FIELD,
    OH_TA_DIALOG_EMAIL_LABEL,
    OH_TA_DIALOG_EMAIL_TEXT_FIELD,
    OH_TA_DIALOG_TYPE_LABEL,
    OH_TA_DIALOG_TYPE_BOX,
    OH_TA_DIALOG_GRAD_RADIO_BUTTON,
    OH_TA_DIALOG_UNDERGRAD_RADIO_BUTTON,
    OH_TA_DIALOG_OK_BOX,
    OH_TA_DIALOG_OK_BUTTON, 
    OH_TA_DIALOG_CANCEL_BUTTON, 
    
    // THESE ARE FOR ERROR MESSAGES PARTICULAR TO THE APP
    OH_NO_TA_SELECTED_TITLE, OH_NO_TA_SELECTED_CONTENT
}