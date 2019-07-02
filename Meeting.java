import database.MeetingDB;

import java.rmi.server.ExportException;
import java.util.ArrayList;
public class Meeting {
    private MeetingDB meetingDB;

    public Meeting(String meeting_id) throws Exception{
        meetingDB = MeetingDB.getInstance();
        getMeetingInfo(meeting_id);
    }

    public String[] getMeetingInfo(String meeting_id) throws Exception{
        return meetingDB.getThis(meeting_id);
    }

    public String getOwnerID(String meeting_id) throws Exception{
        return getMeetingInfo(meeting_id)[4];
    }

    public String getRoom(String meeting_id) throws Exception{
        return getMeetingInfo(meeting_id)[1];
    }

    public ArrayList<String[]> getAttendees(String meeting_id) throws Exception{
        return meetingDB.getAttendeesFor(meeting_id); // x[0] = id, x[1] = first_name, x[2] = last_name
    }
}

