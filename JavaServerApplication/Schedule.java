import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Creates a student's schedule
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Schedule
{
    private PostgreSQLJDBC data = new PostgreSQLJDBC();
    private List <Course> courses = new ArrayList <Course>();
    private List <Course> added= new ArrayList <Course>(); 
    private Scanner scan;
    List <Integer> textFileCourseID = new ArrayList <Integer>();
    List <Integer> textFileSectionID = new ArrayList <Integer>();

    /**
     * Get all the courses from the database
     */
    public Schedule (){
        
    }

    /**
     * Get the database from the SQL
     */
    public void getCourses (){
        courses = data.getCourses();
    }

    /**
     * Checks if there is conflict in classes' times
     * @param section - section trying to add to the schedule
     * @return boolean - true: there is space, false: there is conflict
     */
    private boolean checkAvailable(Sections section){
        for (int i =0; i<added.size(); i++){ // loop through all added courses

            for (int j =0; j< added.get(i).getSec().getClasses().size(); j++){ // loop through each course's section

                for (int k =0; k< section.getClasses().size(); k++){ // loop through all classes of each section
                    Classes classes1 = (Classes) section.getClasses().get(k);  // each class of the choosen section
                    Classes classes2 = (Classes) added.get(i).getSec().getClasses().get(j);  // each class of the schedule
                    if (classes1.getDay()== classes2.getDay()){ // make sure they are on the same day
                        // algorithm for comparing
                        // if start time of chosen class is before the class on the schedule, the end time has to be before the start time of class on the schedule as well
                        // if start time of chosen class is after the class on the schedule, the start time has to be after the start time of class on the schedule as well
                        if  (!( (classes1.startTime() <= classes2.startTime() && classes1.endTime() <= classes2.startTime()) && (classes1.startTime() >= classes2.startTime() && classes1.startTime() >= classes2.endTime()))  ) {
                            // conflict class return false

                            return false;

                        }
                    }
                }

            }

        }
        return true;
    }

    /**
     * Add the course to the schedule
     * @param course- course to be added
     */
    private void addCourse (Course course){

        added.add (course);

    }

    /**
     * Add the section if it passes the check availability test
     * @param course- course to be added
     * @param section- section chosen
     */
    private void addSection (Course course, Sections section){
        if (checkAvailable(section)){  // if no conflict, then add the course 
            added.add (course); // add course
            course. setSection (section); // set the section that was added
            outputResult(true); // output to a textfile that it was successfully added
            System.out.println("Successfully added"); // output on the screen
        } else{
            course.setSection (null); // setting back the section to null
            outputResult(false);
            System.out.println("Not Successfully added");
        }

    }

    /**
     * Remove course from schedule
     * @param course- course to be removed
     */
    private void removeCourse (Course course){       
        added.remove (course);

    }

    /**
     * Read the textfile sent by the GUI (contains course and section id trying to be added to the schedule)
     * @param name of the file
     */
    private void readTextFile (String name){
        Course tryAdd = new Course ();
        int counter=0; int id, idsection;
        try {
            scan = new Scanner (new File (name));
            // Make use of two interesting new methods found on the Scanner API
            while (scan.hasNext())
            {
                // Use the ArrayList's add() method and the Scanner's nextInt() method
                textFileCourseID.add(scan.nextInt()); 
                textFileSectionID.add(scan.nextInt());
                id = textFileCourseID.get(counter);
                idsection = textFileSectionID.get(counter);
                // Matching the course and section id with the data base
                for (int i =0; i<courses.size(); i++){   // matching the course selected with the database
                    if (id == courses.get(i).getID()){
                        tryAdd = courses.get(i);
                        break;
                    }

                }

                for (int i =0; i<courses.size(); i++){    // matching the section with the database

                    for (int j = 0; j < courses.get(i).getSectionList().size(); j++){ // looping through the sections of each course
                        Sections sec= (Sections) courses.get(i).getSectionList().get(j);
                        if (idsection ==  sec.getId()){
                            tryAdd.setSection(sec); // section you are trying to enroll
                            break;
                        }
                    }

                }

                counter++;
                addSection (tryAdd, tryAdd.getSec()); // Try to add the course and section
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("Error reading data");
        }

        // ADD A FINALLY BLOCK HERE TO CLOSE THE SCANNER
        finally{
            if (scan!= null){
                scan.close();
            }
        }

    }

    /**
     * Send out course information (course id, code, and description) to the GUI as a textfile
     */
    public void dataBaseFile (){
        // WRITE CONTENTS OF updatedText TO A TEXT FILE
        WriteFile f1 = new WriteFile ("output.txt", true);

        try{
            for(int i =0;i<courses.size();i++){
                f1.writeToFile(Integer.toString (courses.get(i).getID()) );
                String courseCode = String.valueOf (courses.get(i).getCourseCode());
                f1.writeToFile(courseCode);
                String courseDescription = String.valueOf(courses.get(i).getCourseDescription());
                f1.writeToFile(courseDescription);
            }
        }
        catch (IOException e){
        }

    }

    /**
     * Tells the GUI whether the course was successfully added or not in a textfile
     * @param result- true added, false not added
     */
    public void outputResult (boolean result){
        // WRITE CONTENTS OF updatedText TO A TEXT FILE
        WriteFile f1 = new WriteFile ("output.txt", false);

        try{
            if (result){
                f1.writeToFile("Successfull");
            } else {
                f1.writeToFile ("Not Successfull");
            }

        }
        catch (IOException e){
        }

    }

    /**
     * Executes the program.
     * Reads in the database. Reads the textfile that contains the courses and section trying to be added.
     * Checks for conflict in classes. Sends out a textfile to the GUI regarding wether the course was added or not
     * Adds courses to the schedule.
     * @param String [] args- the textfile name that contains the course and section ids trying to be added
     */
    public void main(String [] args){
        getCourses();
        Course tryAdd = new Course ();
        readTextFile(args[0]); // read all the list of course ids, section ids and try to add them
        
        

    }
}
