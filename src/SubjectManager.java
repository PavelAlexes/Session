import javax.security.auth.Subject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class SubjectManager{

    private static final String FILE_NAME = "subjects.txt";

    public static List<Subject> loadSubject() {
        List<Subject> subjects = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return subjects;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                subjects.add(new Subject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public static void addSubject(Subject subject) {
        List<Subject> subjects = loadSubject();
        subjects.add(subject);
        saveSubject(subjects);
    }


    public static void saveSubject(List<Subject> subjects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Subject subject : subjects) {
                bw.write(subject.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}