/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author parisfisher
 */
public class TextbookPrototype {
    private final StringProperty title;
    private final StringProperty link; 
    private final StringProperty photo;
   ObservableList<String> authors;
    private final StringProperty publisher;
    private final StringProperty year;
    
    public TextbookPrototype(String initTitle, String initLink, String initPhoto,  String initPublisher, String initYear)
    {
        title= new SimpleStringProperty(initTitle);
        link= new SimpleStringProperty(initLink);
        photo= new SimpleStringProperty(initPhoto);
        publisher= new SimpleStringProperty(initPublisher);
        year= new SimpleStringProperty(initYear);
        
        
    }
        
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String initTitle) {
        title.set(initTitle);
    }
    
    public void addAuthor(String author)
    {
        authors.add(author);
    }
        
    
    public String getLink() {
        return link.get();
    }

    public void setLink(String initLink) {
        link.set(initLink);
    }
    public String getPhoto() {
        return photo.get();
    }

    public void setPhoto(String initPhoto) {
        photo.set(initPhoto);
    }
    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String initPublisher) {
        publisher.set(initPublisher);
    }
    public String getYear() {
        return year.get();
    }

    public void setYear(String initYear) {
        year.set(initYear);
    }
   
    
}
