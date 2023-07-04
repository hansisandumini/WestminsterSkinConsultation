import java.time.LocalDate;

public class Patient extends Person{

    private String uniqueId;

    public Patient(String name, String surname, LocalDate dob, int mobileNo, String uniqueId) {
        super(name, surname, dob, mobileNo);
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

}
