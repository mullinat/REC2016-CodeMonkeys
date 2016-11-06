import java.util.*;
/**
 * Contains the info of each section as well as a list its offered classes
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sections
{
    private int sectionid, courseid, sectionNum;
    private int num;
    private char [] profName = new char [10];
    private boolean open;
    private List <Classes> classes = new ArrayList <Classes> (); // a list of classes info

    public Sections (List <Classes> cls){
        classes = cls;
    }

    public Sections (){}

    /**
     * Return a list of classes in that section
     */
    public List getClasses (){
        return classes;
    }

    public void setCourseId (int newId){
        courseid = newId;

    }

    public void setSectionId (int newSec){
        sectionid= newSec;
    }

    public void setSectionNum (int newSecNum)
    {
        sectionNum = newSecNum;
    }

    public void setProf (String newProf)
    {
        profName = newProf.toCharArray();
    }

    public void setOpen (boolean newop)
    {
        open = newop;
    }

    public int getId (){
        return sectionid;
    }

    public int getCourseId(){
        return courseid;
    }
}
