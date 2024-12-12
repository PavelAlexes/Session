public class FullStudentGrades {

    private String grades;
    private String nameStudent;
    private String subject;


    public FullStudentGrades(String grades, String nameStudent, String subject) {
        this.grades = grades;
        this.nameStudent = nameStudent;
        this.subject = subject;
    }


    public String getGrades() {
        return grades;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Ваша оценка по предмету " + subject + ": " + grades+"\n";
    }
}
