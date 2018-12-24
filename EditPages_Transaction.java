/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.PagesPrototype;
import java.util.ArrayList;
import jtps.jTPS_Transaction;
/**
 *
 * @author parisfisher
 */
public class EditPages_Transaction implements jTPS_Transaction{
        CourseSiteGeneratorApp app;
        ArrayList<PagesPrototype> newPages;
        ArrayList<PagesPrototype> oldPages;
        
    public EditPages_Transaction(CourseSiteGeneratorApp app,ArrayList<PagesPrototype> pages) {
        this.app= app;
        newPages=pages;
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        oldPages=data.getPages();
    }
    
    @Override
    public void doTransaction() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.setPages(newPages);
    }

    @Override
    public void undoTransaction() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.setPages(oldPages);
    }
}
