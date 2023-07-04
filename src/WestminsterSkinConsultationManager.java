import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    //Create doctor array list
    ArrayList<Doctor> docList = new ArrayList<Doctor>();


    //Adding doctors for the system.
    public void addDoctor(){
        if(checkSizeForDeleteDoctorToDocArray()){
            getInput();
        }else{
            System.out.println("Can not Delete....");}
    }

    private void getInput(){
        Scanner scn = new Scanner(System.in);
        System.out.println("_______Enter Doctor's Name_______");
        String name = scn.nextLine();
        System.out.println("_____Enter Doctor's Surname_____");
        String surname = scn.nextLine();
        System.out.println("_______Enter Specialization_______");
        String specialization = scn.next();
        int mobileNo = fixErrorOfGetMobileNo();
        int licenceNo = fixErrorOfGetLicenceNo();
        LocalDate dob = fixErrorOfGetDateOfBirth();

        docList.add(new Doctor(name,surname,dob,mobileNo,licenceNo,specialization));
    }

    //Deleting Doctors from the system.
    public void deleteDoctor(){
        int licenceNo = fixErrorOfGetLicenceNo();
        if(checkSizeForDeleteDoctorFromDocArray()){
            if(deleteDoctorFromDocArray(licenceNo)){
                System.out.println("He/She has been Deleted....");
            }else{
                System.out.println("Can not find this Doctor....");
            }
        }else {
            System.out.println("You can not delete....");
        }

    }

    //Check array size with the Doctor list.
    private boolean checkSizeForDeleteDoctorFromDocArray(){
        if(docList.size() != 1){
            return true;
        }else{
            return false;
        }
    }

    private boolean deleteDoctorFromDocArray(int licenceNo){

        for(Doctor doc: docList){
            if(doc.getLicenseNo() == licenceNo){

                System.out.println("Doctor Name : " + doc.getName());
                System.out.println("Doctor Surname : " + doc.getSurname());
                System.out.println("Doctor date of birth : " + doc.getDateOfBirth());
                System.out.println("Doctor Licence Number : " + doc.getLicenseNo());
                System.out.println("Doctor Mobile Number : " + doc.getMobileNo());
                System.out.println("Doctor Specialization : " + doc.getSpecialization());

                docList.remove(doc);
                return true;
            }
        }
        return false;
    }

    private boolean checkSizeForDeleteDoctorToDocArray(){
        if(docList.size() != 10){
            return true;
        }else{
            return false;
        }
    }

    public void sortDoctors(){
        Collections.sort(docList, new DoctorNameSort());
        int count = 0;

        for(Doctor doc: docList){
            count++;
            System.out.println("Doctor " + count + "\n");
            System.out.println("Doctor Name : " + doc.getName());
            System.out.println("Doctor Surname : " + doc.getSurname());
            System.out.println("Doctor date of birth : " + doc.getDateOfBirth());
            System.out.println("Doctor Licence Number : " + doc.getLicenseNo());
            System.out.println("Doctor Mobile Number : " + doc.getMobileNo());
            System.out.println("Doctor Specialization : " + doc.getSpecialization() +"\n");

        }

    }

    public void saveFileData() {
        try  {
            FileOutputStream fos = new FileOutputStream("hansi.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Doctor doctor : docList){
                oos.writeObject(doctor);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
        System.out.println("Successfully saved data into file\n");
    }

    public void readFileData()  {
        try  {
            FileInputStream fos = new FileInputStream("hansi.txt");
            ObjectInputStream oos = new ObjectInputStream(fos);
            docList.clear();//clear the file before reading
            boolean eof = false;
            while (!eof){
                try {
                    Doctor tempDoctor = (Doctor) oos.readObject();
                    docList.add(tempDoctor);
                }catch (EOFException e){
                    eof = true;
                }
            }
            System.out.println("Successfully loaded data from file\n");
            oos.close();
        } catch (ClassNotFoundException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }

    }


    private LocalDate fixErrorOfGetDateOfBirth(){
        while(true){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.println("_______Enter data of birth_______");
                String dob = scn.next();
                LocalDate date = LocalDate.parse(dob);
                return date;
            }catch (Exception e){
                System.out.println("_______Date of birth must be this structure ( ex: 2022-01-29 ) _______");
            }
        }
    }
    private int fixErrorOfGetMobileNo(){
        while(true){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.println("_______Enter mobile number_______");
                int mobile = scn.nextInt();
                return mobile;
            }catch (Exception e){
                System.out.println("_______Mobile number must be an integer_______");
            }
        }
    }

    private int fixErrorOfGetLicenceNo(){
        while(true){
            try{
                Scanner scn = new Scanner(System.in);
                System.out.println("_______Enter License number_______");
                int licenceNo = scn.nextInt();
                return licenceNo;
            }catch (Exception e){
                System.out.println("_______License number must be an integer_______");
            }
        }
    }

    public void GUI() {
        SkinConsultationGUI gui = new SkinConsultationGUI(docList);
    }
}