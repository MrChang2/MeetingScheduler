public class SampleDriver {
    public static void main(String[] args) {
        Employee_Database employees = new Employee_Database();
        Room_Database rooms = new Room_Database();
        Meeting_Database meetings = new Meeting_Database();

        Employee e1 = new Employee("3190", "Steve", "Blum", "stevetheking", "iamtheking", "Assistant Manager", "9:00 am", "5:00 pm");
        Administrator e2 = new Administrator("4512", "Marty", "Stewart", "martystu", "getsmart", "Manager", "8:00 am", "6:00 pm");
        e2.setEmployeeDatabase(employees);
        e2.setRoomDatabase(rooms);

        employees.getEmployeeDatabase().add(e1);
        employees.getEmployeeDatabase().add(e2);

        Login newLogin = new Login(employees);
        Employee User = newLogin.LoginCheck();
        if (User!=null) {
            System.out.println("Welcome " + User.getFirst_name() + "!");
        }

        Room r1 = new Room("101");
        Room r2 = new Room("234");
        Room r3 = new Room("815");

        rooms.getRooms().add(r1);
        rooms.getRooms().add(r2);
        rooms.getRooms().add(r3);

        User.createMeeting(meetings, rooms);
        System.out.println("You have created a new meeting.");
        System.out.println("Owner ID: " + meetings.getMeetingDatabase().get(0).getOwnerID());
        System.out.println("Room ID: " + meetings.getMeetingDatabase().get(0).getRoom().getRoomID());

        User.inviteEmployee(employees, meetings);
        User.inviteEmployee(employees, meetings);
    }
}
