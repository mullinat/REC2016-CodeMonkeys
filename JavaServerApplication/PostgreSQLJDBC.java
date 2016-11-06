import java.util.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Reads the database from the SQL. Each course will have a list of sections and each section will have a list of classes.
 * All classes will be saved in its respective section (added to the list of classes in that section)
 * and each section will be saved in its respective course (added to the list of sections in that course).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class PostgreSQLJDBC {

    static  int classnumber ;//counts how many classes we have 
    static  int sectionsnumber ;//count how many sections we have
    List<Course> courses = new ArrayList<Course>(); // the list of classes that will be sent to the executing method (in Schedule class)
    /**
     * Reads in the database from the SQL and saves them in a list (of courses)
     * @return List <courses> - a list of courses (that contain their respective section ids and classes)
     */
    public List <Course> getCourses() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/rec",
                "rec",null);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        try{
            stmt = c.createStatement();

            //inmtitialize counters

            classnumber = 0;
            sectionsnumber = 0;

            

            ResultSet Courses = stmt.executeQuery( "Courses" );
            // loop saves all the courses in a list
            while ( Courses.next() ) {
                Course course  = new Course();
                course.setCourseId(Courses.getInt("course_id"));
                course.setCourseCode(Courses.getString("courseCode"));
                course.setDescription(Courses.getString("Description"));
                courses.add(course);

            }
            Courses.close();

            //table sections get 
            ResultSet Sections = stmt.executeQuery( "Sections" );
            
            while ( Sections.next() ) {
                Sections s = new Sections();  // save all the info a section to this object
                s.setSectionId(Sections.getInt("sections_id"));
                s.setCourseId(Sections.getInt("course_id"));
                s.setSectionNum(Sections.getInt("sectionNum"));
                s.setProf(Sections.getString("prof"));
                s.setOpen(Sections.getBoolean("available"));
                sectionsnumber++;
                
                // loop matches the course id of the section with the course id of the saved courses 
                // so that each section info will be saved in its course's list of sections
                // i.e. mth will save all sections with mth id in its list of sections
                for (int i =0; i< courses.size(); i++){
                    if (courses.get(i).getID()== s.getCourseId()){
                        courses.get(i).getSectionList().add(s);

                    }

                }

            }
            Sections.close();

            //Classes table get
            ResultSet Classes  = stmt.executeQuery("Classes");
            while(Classes.next()){
                Classes cl = new Classes();  // save all the info a class to this object
                cl.setClassId(Classes.getInt("class_id"));
                cl.setSectionId (Classes.getInt("section_id"));
                cl.setClassType( Classes.getString("classType"));
                cl.setDay (Classes.getString("dayOfWeek"));
                cl.setStartTime((Classes.getString("startTime")).substring(0,1));
                cl.setEndTime(Classes.getString("endTime").substring(0,1));
                classnumber++;

                // loop matches the section id with the class id so that each class info will be saved in its section's list of classes
                // i.e. mth section will save all the classes with mth id in its list of classes
                for (int i =0; i< courses.size(); i++){
                    for (int j =0; j< courses.get(i).getSectionList().size();j++){
                        Sections sec =  courses.get(i).getSectionList().get(j);
                        if (sec.getId()== cl.getSectionId()){

                            courses.get(i).getSectionList().get(j).getClasses().add(cl);

                        }

                    }

                }
            }
            Classes.close();

            stmt.close();
            c.close();

        }catch(Exception z) {;}
        //ystem.out.println("Operation done successfully");
        
        return courses;
    }

    /**
     * Returns the number of classes in total in the database
     * @return  int - the number classes in the database
     */
    int getClassesNumber(){
        return classnumber;
    }

    
    /**
     * Returns the number of sectins in total in the database
     * @return  int - the number sections in the database
     */
    int getSectionsNumber(){
        return sectionsnumber;
    }

}
