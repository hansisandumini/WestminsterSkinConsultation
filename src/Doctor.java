import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Serializable {
    private int licenseNo;
    private String specialization;

    public Doctor(String name, String surname, LocalDate dob, int mobileNo, int licenseNo, String specialization){
        super(name,surname,dob,mobileNo);
        this.licenseNo = licenseNo;
        this.specialization = specialization;
    }

    public int getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(int licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


}
