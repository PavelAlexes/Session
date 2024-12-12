import java.io.Serializable;

public class Subject implements Serializable {
    private String subjectName;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubject() {
        return subjectName;
    }


    @Override
    public String toString() {
        return subjectName;
    }
}
