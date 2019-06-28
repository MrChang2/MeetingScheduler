public class Employee {
    private String employeeID;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String position;
    private String availableStart;
    private String availableEnd;
    private boolean isAdmin = false;

    public Employee() {

    }
    public Employee(String e, String f, String l, String u, String pa, String po, String as, String ae) {
        employeeID = e;
        first_name = f;
        last_name = l;
        username = u;
        password = pa;
        position = po;
        availableStart = as;
        availableEnd = ae;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setAvailableStart(String availableStart) {
        this.availableStart = availableStart;
    }

    public String getAvailableStart() {
        return availableStart;
    }

    public void setAvailableEnd(String availableEnd) {
        this.availableEnd = availableEnd;
    }

    public String getAvailableEnd() {
        return availableEnd;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
