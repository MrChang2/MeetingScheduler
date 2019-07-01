import java.util.ArrayList;

public class WorkHours_Database {
    private ArrayList<WorkHours> WorkHoursDatabase;

    public WorkHours_Database() {
        WorkHoursDatabase = new ArrayList<WorkHours>();
    }

    public ArrayList<WorkHours> getWorkHoursDatabase() {
        return WorkHoursDatabase;
    }

    //used in Employee method changeWorkingHours()
    public void updateWorkHours(WorkHours wh) {
        for (int x=0;x<WorkHoursDatabase.size();x++) {
            if (WorkHoursDatabase.get(x).getEmployeeID().equals(wh.getEmployeeID())) {
                WorkHoursDatabase.set(x, wh);
            }
        }
    }
}
