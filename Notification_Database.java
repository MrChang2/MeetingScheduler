import java.util.ArrayList;

public class Notification_Database {
    private ArrayList<Notification> NotificationDatabase;

    public Notification_Database() {
        NotificationDatabase = new ArrayList<Notification>();
    }

    public ArrayList<Notification> getNotificationDatabase() {
        return NotificationDatabase;
    }
}
