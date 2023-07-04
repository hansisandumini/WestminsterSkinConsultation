import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation {
    private String name;
    private String surname;
    private String dob;
    private String mobileNo;
    private String id;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private int hours;
    private int cost;
    Consultation(String name, String surname, String dob, String mobileNo, String id, Doctor doctor, LocalDate date, LocalTime time, int hours, int cost) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.id = id;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.hours = hours;
        this.cost =cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getCost() {
        return cost;
    }

    public int getDocLi(){
        return doctor.getLicenseNo();
    }

    public String getDob() {
        return dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getHours() {
        return hours;
    }
}
