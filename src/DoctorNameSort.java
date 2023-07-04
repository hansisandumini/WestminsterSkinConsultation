import java.util.Comparator;

public class DoctorNameSort implements Comparator<Doctor> {


    public int compare(Doctor doc1, Doctor doc2){
        return doc1.getSurname().compareTo(doc2.getSurname());
    }
}
