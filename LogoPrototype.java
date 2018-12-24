/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author parisfisher
 */
public class LogoPrototype {
    
    private final StringProperty href;
    private final StringProperty src;
    
    public LogoPrototype(String initHref, String initSrc)
    {
        href= new SimpleStringProperty(initHref);
        src = new SimpleStringProperty(initSrc);
    }
    
    public LogoPrototype(String initHref)
    {
        href=new SimpleStringProperty(initHref);
        src=null;
    }
    
    public String getHref() {
        return href.get();
    }

    public void setHref(String initHref) {
        href.set(initHref);
    }
    
    public String getSrc() {
        return src.get();
    }

    public void setSrc(String initSrc) {
        src.set(initSrc);
    }
}
