import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WestminsterSkinConsultationManager obj = new WestminsterSkinConsultationManager();

        while(true){
            displayMenu();
            String option = getOption();
            switch (option) {
                case "A" -> obj.addDoctor();
                case "B" -> obj.deleteDoctor();
                case "C" -> obj.sortDoctors();
                case "D" -> obj.saveFileData();
                case "E" -> obj.readFileData();
                case "F" -> obj.GUI();
                case "G" -> {
                    System.out.println("Exit...");
                    return;
                }
                default -> System.out.println("Please enter Valid input for option...");
            }

        }
    }
    private static void displayMenu(){  //Display the menu.
        System.out.println("""
                A. Add Doctor
                B. Delete Doctor
                C. Display Doctors
                D. Save File
                E. Read File
                F. Open GUI
                G. Exit
                """);
    }
    private static String getOption(){
        System.out.println("Enter Option");
        Scanner scn = new Scanner(System.in);

        return scn.nextLine().toUpperCase();
    }
}

