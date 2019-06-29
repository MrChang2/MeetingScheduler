public class Notification {
    private String employeeID;
    private String message;

    public Notification(String id, String m) {
        employeeID = id;
        message = m;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
