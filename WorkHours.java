//class dealing with tracking work hours. Contains array of 8 slots, each representing an hour in the work day. True=unavailable and False=available
public class WorkHours {
    private String employeeID;
    private boolean[] workHours = new boolean[8];

    public WorkHours(String id) {
        employeeID = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public boolean[] getWorkHours() {
        return workHours;
    }

    public void toggleAvailability(int x) {
        if (workHours[x]) {
            workHours[x] = false;
        }
        else {
            workHours[x] = true;
        }
    }

    public void printWorkHours() {
        int hour = 9;
        for (int x=0;x<workHours.length;x++) {
            //Print time
            if (x+hour<12) {
                System.out.print((x+hour) + " am: ");
            }
            else if (x+hour==12) {
                System.out.print("12 pm: ");
            }
            else {
                System.out.print((x+hour-12) + " pm: ");
            }
            //Print availability
            if (workHours[x]) {
                System.out.println("Unavailable ");
            }
            else {
                System.out.println("Available ");
            }
        }
    }
}
