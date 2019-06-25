public class SampleDriver {
    public static void main(String[] args) {
        Employee_Database employees = new Employee_Database();
        Room_Database rooms = new Room_Database();
        Administrator admin = new Administrator(employees, rooms);

        Employee e1 = new Employee("001", "Bob", "Hanson", "BobTheBuilder09", "wecanfixit", "Contractor");
        Employee e2 = new Employee("032", "John", "Smith", "BabaYaga", "iamjohnwick", "Manager");
        Employee e3 = new Employee("529", "Selina", "Kent", "Supercat", "totallylegit", "Secretary");

        Room r1 = new Room("11034");
        Room r2 = new Room("32847");
        Room r3 = new Room("64235");

        admin.addEmployee(e1);
        admin.addEmployee(e2);
        admin.addEmployee(e3);

        admin.addRoom(r1);
        admin.addRoom(r2);
        admin.addRoom(r3);

        admin.viewAllEmployeeInfo();
        admin.viewAllRoomInfo();

        System.out.println("Removing Bob and first room:");
        admin.deleteEmployee(e1);
        admin.deleteRoom(r1);

        admin.viewAllEmployeeInfo();
        admin.viewAllRoomInfo();
    }
}
