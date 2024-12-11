import javax.security.auth.Subject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectManager {
    private static final String FILE_NAME = "subjects.txt";

    public static List<Subject> loadUsers() {
        List<Subject> subjects = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return Subjects;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                subjects.add(new Subject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public static void saveSubjects(List<Subject> subjects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Subject subject : saveSubjects) {
                bw.write(subject.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSubject(Subject subject) {
        List<Subject> subjects = loadUsers();
        subjects.add(subject);
        saveSubjects(subjects);
    }



}

