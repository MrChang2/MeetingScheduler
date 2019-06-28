import java.util.ArrayList;

public class Meeting_Database {
    private ArrayList<Meeting> MeetingDatabase;

    public Meeting_Database() {
        MeetingDatabase = new ArrayList<Meeting>();
    }

    public ArrayList<Meeting> getMeetingDatabase() {
        return MeetingDatabase;
    }

    public void viewInfo(Meeting m) {
        for (int x=0;x<MeetingDatabase.size();x++) {
            if (m==MeetingDatabase.get(x)) {
                System.out.println("Meeting Name: " + MeetingDatabase.get(x).getMeetingName());
                System.out.println("Owner ID: " + MeetingDatabase.get(x).getOwnerID());
                System.out.println("Room ID: " + MeetingDatabase.get(x).getRoom().getRoomID());
            }
        }
    }
}
