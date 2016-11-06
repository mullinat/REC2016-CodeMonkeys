import java.util.*;
/**
 * Contains the info of each course as a well as a list of its sections
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Course
{
    private int courseid, sectionid;

    private char [] code= new char [6];
    private char [] description= new char [6];
    private List <Sections> sec = new ArrayList <Sections> (); // list of section info 

    private Sections enrolledSection= new Sections ();
    public Course (List <Sections> sections){
        sec = sections;

    }

    public Course (){}

    public Sections getSec () {
        return enrolledSection;
    }

    public int getSectionID(){
        return sectionid;
    }

    public void setCourseCode (String newCode){
        code = newCode.toCharArray();
    }

    public void setCourseId (int newId){
        courseid = newId;
    }

    /**
     * Get a list of all the sections in that course
     */
    public List <Sections> getSectionList(){
        return sec;
    }

    /**
     * Set which section the user is trying to enroll in
     */
    public void setSection (Sections sectionAdded){
        enrolledSection = sectionAdded;
    }

    public void removeSection (){
        enrolledSection = null;
    }

    public void setDescription (String newdesc){
        description = newdesc.toCharArray();
    }

    public int getID(){
        return courseid;
    }

    public char[] getCourseCode(){
        return code;
    }
    
    public char[] getCourseDescription(){
        return description;
    }
}
