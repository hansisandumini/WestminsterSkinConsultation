import java.io.IOException;

public interface SkinConsultationManager {
    void addDoctor();
    void deleteDoctor();
    void sortDoctors();
    void saveFileData() throws IOException;
    void readFileData() throws IOException;
    void GUI() throws IOException;
}
