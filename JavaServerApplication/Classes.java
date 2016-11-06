
/**
 * Contains the info of each class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Classes
{
    private int classid, sectionid;
    private int classType;
    private int dayOfWeek;
    private int startTime, endTime;

    public Classes (){}

    // user interface
    public int getId (){
        return classid;
    }
    
    // user interface
    public int getType (){
        return classType;
    }
    
    // day in schdule
    public int getDay(){
        return dayOfWeek;
    }
    
    // time in schdule
    public int startTime(){
        return startTime;
    }
    
    public int endTime(){
        return endTime;
    }
    
    
    public void setDay (String newDay)
    {
        dayOfWeek = Integer.parseInt(newDay);
    }
    
    
    public void setClassId (int newClassId)
    {
        classid = newClassId;
    }
    
    public void setStartTime (String newEndTime)
    {
         endTime = Integer.parseInt(newEndTime);
    }
    
    public void setEndTime (String newEndTime)
    {
         startTime = Integer.parseInt(newEndTime);
    }
    
    public void setClassType (String newClassType)
    {
        classType = Integer.parseInt(newClassType);
    }

    public void setSectionId (int newSectionId)
    {
        sectionid = newSectionId;
    }
    
    public int getSectionId (){
        return sectionid;
    }

}

