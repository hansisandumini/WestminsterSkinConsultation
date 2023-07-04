import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    private String name;
    private String surname;
    private int mobileNo;
    private LocalDate dateOfBirth;

    public Person(String name,String surname,LocalDate dob,int mobileNo){

        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dob;
        this.mobileNo = mobileNo;


    }

    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setMobileNo(int mobileNo){
        this.mobileNo = mobileNo;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public int getMobileNo(){
        return mobileNo;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

}