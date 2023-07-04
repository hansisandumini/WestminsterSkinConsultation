import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SkinConsultationGUI extends JFrame {

    static ArrayList<Consultation> arr = new ArrayList<>();

    // List of doctors in the consultation centre
    private ArrayList<Doctor> doctors;

    // Table to display the list of doctors
    private JTable doctorTable;

    // Button to add a new consultation
    private JButton addConsultationButton;
    private JButton showconsultationButton;

    public SkinConsultationGUI(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
        // Set up the layout of the GUI
        setLayout(new BorderLayout());
        // Set up the table to display the list of doctors
        String[] columnNames = {"Name", "Surname", "Medical License Number"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        doctorTable = new JTable(tableModel);
        for (Doctor doctor : doctors) {
            Object[] rowData = {doctor.getName(), doctor.getSurname(), doctor.getLicenseNo()};
            tableModel.addRow(rowData);
        }
        add(new JScrollPane(doctorTable), BorderLayout.CENTER);
        // Set up the button to add a new consultation
        addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the dialog to add a new consultation
                AddConsultationDialog dialog = new AddConsultationDialog(SkinConsultationGUI.this);
                dialog.setVisible(true);
            }
        });

        //sort table
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(doctorTable.getModel());
                doctorTable.setRowSorter(sorter);

                ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//set column one as sort key and ascending order
                sorter.setSortKeys(sortKeys);
                doctorTable.repaint();//refresh the table after sorting
                JOptionPane.showMessageDialog(null, "Successfully sorted doctors according to their names", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        showconsultationButton = new JButton("Show Consultation");
        showconsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the dialog to add a new consultation
                showConsultationTable contable = new showConsultationTable();
                contable.setVisible(true);
            }
        });

        add(addConsultationButton, BorderLayout.SOUTH);
        add(showconsultationButton, BorderLayout.NORTH);
        add(sortButton,BorderLayout.EAST);

        // Set up the properties of the GUI
        setTitle("Skin Consultation Centre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static class showConsultationTable extends JFrame {

        public showConsultationTable() {
            setLayout(new BorderLayout());
            DefaultTableModel consul = new DefaultTableModel();
            // Add columns to the model
            consul.addColumn("ID");
            consul.addColumn("First Name");
            consul.addColumn("SurName");
            consul.addColumn("Doctor");
            consul.addColumn("Cost");

            for (Consultation c : arr) {
                consul.addRow(new Object[]{c.getId(), c.getName(), c.getSurname(),c.getDoctor().getName(),c.getCost()});
            }
            JTable table = new JTable(consul);
            JPanel panel = new JPanel();
            add(new JScrollPane(table), BorderLayout.CENTER);
            setTitle("Skin Consultation Centre");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE );
            setVisible(true);
        }
    }

    public class AddConsultationDialog extends JDialog {

        // Text field to enter the patient name
        private JTextField nameField;

        // Text field to enter the patient surname
        private JTextField surnameField;

        // Text field to enter the patient date of birth
        private JTextField dobField;

        // Text field to enter the patient mobile number
        private JTextField mobileNoField;

        // Text field to enter the patient ID
        private JTextField idField;

        // Combo box to select the doctor for the consultation
        private JComboBox<String> doctorComboBox;

        // Text field to enter the date and time of the consultation
        private JTextField dateField;
        private JTextField timeFeild;
        private JTextField hoursField;
        // Text area to enter the notes for the consultation
        private JTextArea notesArea;

        // Button to save the consultation
        private JButton saveButton;

        private JButton availability;

        public AddConsultationDialog(JFrame frame) {
            super(frame, "Add Consultation", true);

            // Set up the layout of the dialog
            setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);

            // Add the label and text field for the patient name
            constraints.gridx = 0;
            constraints.gridy = 0;
            add(new JLabel("Name:"), constraints);
            nameField = new JTextField(20);
            constraints.gridx = 1;
            add(nameField, constraints);

            // Add the label and text field for the patient surname
            constraints.gridx = 0;
            constraints.gridy = 1;
            add(new JLabel("Surname:"), constraints);
            surnameField = new JTextField(20);
            constraints.gridx = 1;
            add(surnameField, constraints);

            // Add the label and text field for the patient date of birth
            constraints.gridx = 0;
            constraints.gridy = 2;
            add(new JLabel("Date of Birth (dd/mm/yyyy):"), constraints);
            dobField = new JTextField(20);
            constraints.gridx = 1;
            add(dobField, constraints);

            // Add the label and text field for the patient mobile number
            constraints.gridx = 0;
            constraints.gridy = 3;
            add(new JLabel("Mobile Number:"), constraints);
            mobileNoField = new JTextField(20);
            constraints.gridx = 1;
            add(mobileNoField, constraints);

            // Add the label and text field for the patient ID
            constraints.gridx = 0;
            constraints.gridy = 4;
            add(new JLabel("ID:"), constraints);
            idField = new JTextField(20);
            constraints.gridx = 1;
            add(idField, constraints);
            // Add the label and combo box to select the doctor
            constraints.gridx = 0;
            constraints.gridy = 5;
            add(new JLabel("Doctor:"), constraints);
            doctorComboBox = new JComboBox<String>();

            for (Doctor doctor : doctors) {
                doctorComboBox.addItem(doctor.getName() + " " + doctor.getSurname());
            }
            constraints.gridx = 1;
            add(doctorComboBox, constraints);

            // Add the label and text field for the date and time of the consultation
            constraints.gridx = 0;
            constraints.gridy = 6;
            add(new JLabel("Consultation Date(yyyy.mm.dd):"), constraints);
            dateField = new JTextField(20);
            constraints.gridx = 1;
            add(dateField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 7;
            add(new JLabel("Consultation time(hh.mm)"), constraints);
            timeFeild = new JTextField(20);
            constraints.gridx = 1;
            add(timeFeild, constraints);

            constraints.gridx = 0;
            constraints.gridy = 8;
            add(new JLabel("No of hours"), constraints);
            hoursField = new JTextField(20);
            constraints.gridx = 1;
            add(hoursField, constraints);

            //availability button
            availability = new JButton("Check Availability");
            availability.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAvailabilityMethod();
                }
            });
            constraints.gridx = 1;
            constraints.gridy = 9;
            add(availability, constraints);

            // Add the label and text area for the notes
            constraints.gridx = 0;
            constraints.gridy = 10;
            add(new JLabel("Notes:"), constraints);
            notesArea = new JTextArea(5, 20);
            notesArea.setLineWrap(true);
            constraints.gridx = 1;
            add(new JScrollPane(notesArea), constraints);

            // Add the button to save the consultation
            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveConsultation();
                }
            });
            constraints.gridx = 1;
            constraints.gridy = 11;
            add(saveButton, constraints);

            // Set up the properties of the dialog
            pack();
            setLocationRelativeTo(frame);
        }
        // Method to get the selected doctor from the combo box

        public Doctor getSelectedDoctor() {
            int index = doctorComboBox.getSelectedIndex();
            return doctors.get(index);
        }

        // Method to save the consultation to the system
        public void saveConsultation() {
            // Get the patient information from the text fields
            String name = nameField.getText();
            String surname = surnameField.getText();
            String dob = dobField.getText();
            String mobileNumber = mobileNoField.getText();
            String id = idField.getText();

            // Get the selected doctor from the combo box
            Doctor doctor = getSelectedDoctor();

            // Get the date and time of the consultation from the text field
            String [] dateArr = dateField.getText().split("\\.");
            String [] timeArr = timeFeild.getText().split("\\.");

            LocalDate date;
            LocalTime time;
            try {
                date = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
                time = LocalTime.of(Integer.parseInt(timeArr[0]),Integer.parseInt(timeArr[1]));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Invalid date or time","d",JOptionPane.WARNING_MESSAGE);
                return;
            }



            int hours = Integer.parseInt(hoursField.getText());

            // Get the notes for the consultation from the text area
            String notes = notesArea.getText();

            // Calculate the cost of the consultation
            int cost = 15; // First consultation is £15 per hour
            double consultationLength = 0;
            cost += 10 * (int) Math.ceil(1.0 * consultationLength / 60); // Subsequent consultations are £25 per hour



            // Create a new consultation object with the entered information
            Consultation consultation = new Consultation(name, surname, dob, mobileNumber, id, doctor, date,time,hours, cost);

            // Save the consultation to the system if the doctor is available if not add to any other available doctor (e.g. to a database, file, etc.)
            if(availabilityCheck(date,time,hours,doctor.getLicenseNo())){
                saveConsultation(consultation);
                JOptionPane.showMessageDialog(null,"Successfully added record","d",JOptionPane.WARNING_MESSAGE);
                setVisible(false);
                return;
            }else {
                for(Doctor d : doctors){
                    if(availabilityCheck(date,time,hours,d.getLicenseNo())){
                        Consultation tempC = new Consultation(name, surname, dob, mobileNumber, id,d, date,time,hours, cost);
                        saveConsultation(tempC);
                        JOptionPane.showMessageDialog(null,"Requested doctor is unavaialable record added to dr." + d.getSurname(),"d",JOptionPane.WARNING_MESSAGE);
                        setVisible(false);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null,"all doctors unavaialbe failed to add","d",JOptionPane.WARNING_MESSAGE);
            }


            // Close the dialog
            setVisible(false);
        }

        public void checkAvailabilityMethod(){
            // Get the selected doctor from the combo box
            Doctor doctor = getSelectedDoctor();

            // Get the date and time of the consultation from the text field
            String [] dateArr = dateField.getText().split("\\.");
            String [] timeArr = timeFeild.getText().split("\\.");

            LocalDate date;
            LocalTime time;
            try {
                date = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
                time = LocalTime.of(Integer.parseInt(timeArr[0]),Integer.parseInt(timeArr[1]));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Invalid date or time","d",JOptionPane.WARNING_MESSAGE);
                return;
            }



            int hours = Integer.parseInt(hoursField.getText());

            if(availabilityCheck(date,time,hours,doctor.getLicenseNo())){
                JOptionPane.showMessageDialog(null,"Doctor is available","d",JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null,"Doctor is unavailable","d",JOptionPane.WARNING_MESSAGE);
            }
        }

        public boolean availabilityCheck(LocalDate consultationDate, LocalTime consultationTime, int noOfHours, int doctorLicenceNumber){
            for (Consultation consultation : arr){
                if(consultation.getDocLi() == doctorLicenceNumber){
                    boolean dateCheck = consultationDate.isEqual(consultation.getDate());

                    LocalTime T1 = consultation.getTime(); //old consultation start time
                    LocalTime T2 = consultation.getTime().plusHours(consultation.getHours()); //old consultation end time
                    LocalTime t1 = consultationTime; //new consultation start time
                    LocalTime t2 = consultationTime.plusHours(noOfHours); //new consultation end time

                    boolean timeCheck1 = ( t1.isAfter(T1) ) && ( t1.isBefore(T2) );
                    boolean timeCheck2 = ( t2.isAfter(T1) ) && ( t2.isBefore(T2) );
                    boolean timeCheck3 = ( t1.isBefore(T1) && (t2.isAfter(T2)) );
                    boolean timeCheck4 = (t1.equals(T1) || t1.equals(T2) || t2.equals(T1) || t2.equals(T2));

                    if( (dateCheck) && ( (timeCheck1) || (timeCheck2) || (timeCheck3) || (timeCheck4) ) ){
                        return false;
                    }
                }
            }
            return true;
        }
        private void saveConsultation(Consultation consultation) {
            arr.add(consultation);
        }

    }

}
