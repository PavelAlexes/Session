import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainApp {
    private static List<String> students = new ArrayList<>();
    private static List<String> teachers = new ArrayList<>();
    private static List<String> subjects = new ArrayList<>();
    private static List<String> examResults = new ArrayList<>();
    private static List<String> groups = new ArrayList<>();
    private  static List<String> dean = new ArrayList<>();
    private static final String FILE_studentToExam = "studentToExam.txt";
    private static final String FILE_studentGrades = "studentToGrades.txt";
    private static final String FILE_studentFullGrades = "studentFullGrades.txt";
    private static List<File> arrFileStudentToExam = new ArrayList<>();
    private static List<String> arrStringStudentToExam = new ArrayList<>();
    private static List<File> arrFileStudentGrades = new ArrayList<>();
    private static List<String> arrStringStudentGrades = new ArrayList<>();
    private static List<FullStudentGrades> arrFullStudentGrades = new ArrayList<>();
    private static List<File> arrFullFileStringStudentGrades = new ArrayList<>();
    private static List<String> arrFullStringStudentGrades = new ArrayList<>();
    private static boolean deenGetResult = false;

    public static void main(String[] args) {

        loadData();

        arrFileStudentToExam.add(new File(FILE_studentToExam));
        arrFileStudentGrades.add(new File(FILE_studentGrades));
        arrFullFileStringStudentGrades.add(new File(FILE_studentFullGrades));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Сессия");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocation(600,250);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    saveData();
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel loginLabel = new JLabel("Логин:");
            JTextField loginField = new JTextField();
            JLabel passwordLabel = new JLabel("Пароль:");
            JPasswordField passwordField = new JPasswordField();

            JButton loginButton = new JButton("Войти");
            JButton registerButton = new JButton("Регистрация");

            panel.add(loginLabel);
            panel.add(loginField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(registerButton);

            frame.add(panel);
            frame.setVisible(true);
            Icon icon = new ImageIcon("Снимок экрана 2024-12-17 184559.png");

            Image image = ((ImageIcon) icon).getImage(); // transform it
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newimg);  // transform it back
            Icon finalIcon = icon;

            loginButton.addActionListener(e -> {
                String username = loginField.getText();
                String password = new String(passwordField.getPassword());
                User user = UserManager.authenticate(username, password);

                if (user != null) {
                    frame.dispose();
                    openRoleInterface(user);
                } else {
                    JOptionPane.showMessageDialog(frame, "Я люблю только Полинку ВАЩЕ!#41!!@ ","♥",JOptionPane.QUESTION_MESSAGE,finalIcon);
                }
            });








            registerButton.addActionListener(e -> {
                String username = JOptionPane.showInputDialog("Самый хайповый подросток:");
                String password = JOptionPane.showInputDialog("Введите пароль:");
                String[] roles = {"да", "АААаАаа памагити"};
                String role = (String) JOptionPane.showInputDialog(
                        frame,
                        "тебя уебать пидарас?!2",
                        "Message",
                        JOptionPane.QUESTION_MESSAGE,
                        finalIcon,
                        roles,
                        roles[0]
                );


                if (username != null && password != null && role != null) {

                    UserManager.addUser(new User(username, password, role));

                    switch (role){
                        case "Студент":
                            students.add(username);
                            saveData();
                            break;
                        case "Преподаватель":
                            break;
                    }
                    JOptionPane.showMessageDialog(frame, "Пользователь успешно зарегистрирован!");
                }
            });
        });
    }

    private static void openRoleInterface(User user) {

        JFrame frame = new JFrame("Интерфейс " + user.getRole());

        frame.setLocation(600,250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveData();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton logoutButton = new JButton("Выйти");
        logoutButton.setLayout(null);
        logoutButton.setSize(250,30);
        logoutButton.addActionListener(e -> {
            frame.dispose();
            main(null);
        });

        switch (user.getRole()) {

            case "Администратор":

                // ИЗОБРАЖЕНИЕ-----------------------------------------------------------

                try {
                    // Загрузка изображения
                    BufferedImage originalImage = ImageIO.read(new File("Q:/Программирование/java/CourseW/Снимок экрана 2024-12-12 032529.png"));

                    int newWidth = 200;
                    int newHeight = 150;

                    Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);



                    JButton p1 = new JButton(new ImageIcon(scaledImage));
                    p1.setVisible(true);
                    p1.setSize(150, 130);
                    p1.setLocation(350, 2);
                    panel.add(p1);
                    panel.setVisible(true);

                    p1.addActionListener(e -> {
                        try {

                            BufferedImage originalImage2 = ImageIO.read(new File("Q:/Программирование/java/CourseW/photo_2024-12-12_22-40-35.jpg"));
                            int newWidth2 = 400;
                            int newHeight2 = 300;

                            Image scaledImage2 = originalImage2.getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);

                            JFrame p2 = new JFrame("Масштабированное изображение");

                            JButton p3 = new JButton(new ImageIcon(scaledImage2));
                            p3.setVisible(true);
                            p3.setSize(250, 250);
                            p3.setLocation(280, 2);
                            panel.add(p3);
                            panel.setVisible(true);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }


                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //// ИЗОБРАЖЕНИЕ-----------------------------------------------------------

//                JButton addGroupButton = new JButton("Добавить группу");
//                addGroupButton.setLayout(null);
//                addGroupButton.setSize(250,30);

//                JButton assignStudentToGroupButton = new JButton("Распределить студента по группе");

                JButton addStudentButton = new JButton("Добавить студента");
                addStudentButton.setLayout(null);
                addStudentButton.setSize(250,30);

                JButton addTeacherButton = new JButton("Добавить преподавателя");
                addTeacherButton.setLayout(null);
                addTeacherButton.setSize(250,30);

                JButton addDeanButton = new JButton("Добавить Заместителя директора");
                addDeanButton.setLayout(null);
                addDeanButton.setSize(250,30);

                JButton addSubjectButton = new JButton("Добавить дисциплину");
                addSubjectButton.setLayout(null);
                addSubjectButton.setSize(250,30);

                JButton deleteUserButton = new JButton("Удалить пользователя");
                deleteUserButton.setLayout(null);
                deleteUserButton.setSize(250,30);

                JButton deleteSubjectButton = new JButton("Удалить дисциплину");
                deleteSubjectButton.setLayout(null);
                deleteSubjectButton.setSize(250,30);

                panel.setLayout(null);
//                panel.add(addGroupButton).setLocation(10,20);
//                panel.add(assignStudentToGroupButton);
                panel.add(addStudentButton).setLocation(10,40);
                panel.add(addTeacherButton).setLocation(10,80);
                panel.add(addDeanButton).setLocation(10,140);
                panel.add(addSubjectButton).setLocation(10,180);
                panel.add(deleteUserButton).setLocation(300,140);
                panel.add(deleteSubjectButton).setLocation(300,180);
                panel.add(logoutButton).setLocation(150,220);


//                addGroupButton.addActionListener(e -> addGroup());
//                assignStudentToGroupButton.addActionListener(e -> assignStudentToGroup());

                addStudentButton.addActionListener(e -> {
                    String studentName = JOptionPane.showInputDialog("Введите имя студента:");
                    String studentPassword = JOptionPane.showInputDialog("Введите пароль студента:");
                    if (studentName != null && studentPassword != null) {
                        students.add(studentName);
                        UserManager.addUser(new User(studentName, studentPassword, "Студент"));

                        JOptionPane.showMessageDialog(frame, "Студент добавлен!");
                        saveData();
                    }
                });

                addDeanButton.addActionListener(e -> {
                    String deanName = JOptionPane.showInputDialog("Введите имя Заместителя директора:");
                    String deanPassword = JOptionPane.showInputDialog("Введите пароль Заместителя директора:");
                    if (deanName != null && deanPassword != null) {
                        dean.add(deanName);
                        UserManager.addUser(new User(deanName, deanPassword, "Заместитель директора"));
                        JOptionPane.showMessageDialog(frame, "Заместитель директора добавлен!");
                        saveData();
                    }
                });

                addTeacherButton.addActionListener(e -> {
                    String teacher = JOptionPane.showInputDialog("Введите имя преподавателя:");
                    String teacherPassword = JOptionPane.showInputDialog("Введите пароль преподавателя:");
                    if (teacher != null && teacherPassword != null) {
                        teachers.add(teacher);
                        UserManager.addUser(new User(teacher, teacherPassword, "Преподаватель"));
                        JOptionPane.showMessageDialog(frame, "Преподаватель добавлен!");
                        saveData();
                    }
                });

                addSubjectButton.addActionListener(e -> {
                    String subject = JOptionPane.showInputDialog("Введите название дисциплины:");
                    if (subject != null) {
                        subjects.add(subject);
                        JOptionPane.showMessageDialog(frame, "Дисциплина добавлена!");
                        saveData();
                    }
                });

                // УДАЛЕНИЕ ДИСЦИПЛИН--------------------------------------------------------------
                deleteSubjectButton.addActionListener(e -> {

                    String[] sa = new String[subjects.size()];

                    for(int i=0; i< subjects.size(); i++){
                        sa[i] = subjects.get(i);
                    }

                    if(subjects.isEmpty()){
                        JOptionPane.showMessageDialog(frame, "Список дисциплин пуст!");

                    } else {
                        String subject = (String) JOptionPane.showInputDialog(
                                frame,
                                "Какую дисциплину удалить:",
                                "Удаление учебной дисциплины",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                sa,
                                sa[0]
                        );

                        if (subject != null) {
                            subjects.remove(subject);
                            JOptionPane.showMessageDialog(frame, "Дисциплина успешно удалена!");
                        }
                    }
                });
                // УДАЛЕНИЕ ДИСЦИПЛИН--------------------------------------------------------------

                deleteUserButton.addActionListener(e -> {
                    deleteUser(frame);
                    saveData();
                });
                break;

            case "Студент":

                JButton takeExamButton = new JButton("Сдать экзамен");
                takeExamButton.setLayout(null);
                takeExamButton.setSize(250,30);

                JButton resultButton = new JButton("Посмотреть свою оценку");
                resultButton.setLayout(null);
                resultButton.setSize(250,30);

                panel.setLayout(null);
                panel.add(takeExamButton).setLocation(170,70);
                panel.add(resultButton).setLocation(170,110);
                panel.add(logoutButton).setLocation(170,150);

                takeExamButton.addActionListener(e -> {
                    giveExam(user);
                    saveData();
                });

                resultButton.addActionListener(e -> {

                    for (FullStudentGrades fullStudentGrades : arrFullStudentGrades){

                        if(Objects.equals(fullStudentGrades.getNameStudent(), user.getUsername())){

                            String result = fullStudentGrades.toString().replace("[", "").replace("]", "")
                                    .replace(", ", "");

                            if(deenGetResult && result.isEmpty() || result.isBlank()) {
                                JOptionPane.showMessageDialog(frame, "У вас не сдан ни один экзамен!");
                            }
                            else if (!deenGetResult) {
                                JOptionPane.showMessageDialog(frame, "Результаты еще не выложены!");
                            }
                            else JOptionPane.showMessageDialog(frame, result);

                            break;
                        }
                    }
                });
                break;

            case "Преподаватель":

                JButton takeExamButton1 = new JButton("Принять экзамен");
                takeExamButton1.setLayout(null);
                takeExamButton1.setSize(250,30);

                panel.setLayout(null);
                panel.add(takeExamButton1).setLocation(170,70);
                panel.add(logoutButton).setLocation(170,110);


//                takeExam.setLayout(null);
//                takeExam.setSize(250, 30);
//                panel.setLayout(null);
//                panel.add(takeExam).setLocation(10, 20);

                takeExamButton1.addActionListener(e -> {
//                    takeExam(user);
//                    saveData();

                    String[] sa = new String[arrStringStudentToExam.size()];

                    for(int i=0; i< arrStringStudentToExam.size(); i++){
                        sa[i] = arrStringStudentToExam.get(i);
                    }

                    if(arrStringStudentToExam.isEmpty()){
                        JOptionPane.showMessageDialog(frame, "Список допущенных студентов пуст!");

                    } else {
                        String students = (String) JOptionPane.showInputDialog(
                                frame,
                                "У какого студента принять экзамен:",
                                "Допущенные студенты",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                sa,
                                sa[0]
                        );
                        //-------------

                        String[] subjectArray = subjects.toArray(new String[0]);

                        if (subjectArray.length == 0) {
                            JOptionPane.showMessageDialog(null, "Нет доступных экзаменов!");
                            return;
                        }

                        String selectedSubject = (String) JOptionPane.showInputDialog(
                                null,
                                "Выберите предмет для проверки:",
                                "Сдача экзамена",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                subjectArray,
                                subjectArray[0]
                        );

                        //-------------

                        String[] sas = new String[]{"1", "2", "3", "4", "5"};
                        String gradest = (String) JOptionPane.showInputDialog(
                                frame,
                                "Какую оценку вы ставите за экзамен: ",
                                "Оценка",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                sas,
                                sas[0]
                        );
                        JOptionPane.showMessageDialog(frame, "Оценка поставлена!");

                        //---------------

                        FullStudentGrades fullStudentGrades = new FullStudentGrades(gradest, students, selectedSubject);
                        arrFullStudentGrades.add(fullStudentGrades);

                        PrintWriter w1 = null;
                        try {
                            w1 = new PrintWriter(new FileWriter(arrFullFileStringStudentGrades.getFirst(), true));
                            w1.println("Имя: " + fullStudentGrades.getNameStudent() + " Предмет: " + fullStudentGrades.getSubject()
                            + " Оценка: " + fullStudentGrades.getGrades());
                            w1.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        //---------------

                        PrintWriter w2 = null;
                        try {
                            w2 = new PrintWriter(new FileWriter(arrFileStudentGrades.getFirst(), true));
                            w2.println(gradest);
                            w2.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        arrStringStudentGrades.add(gradest);
                    }
                });
                break;

            case "Заместитель директора":

                JButton inputResultsButton = new JButton("Ввод результатов экзамена");
                inputResultsButton.setLayout(null);
                inputResultsButton.setSize(250,30);

                JButton manageExamPermissionButton = new JButton("Управление допуском студентов");
                manageExamPermissionButton.setLayout(null);
                manageExamPermissionButton.setSize(250,30);

                panel.setLayout(null);
                panel.add(manageExamPermissionButton).setLocation(170,60);
                panel.add(inputResultsButton).setLocation(170,100);
                panel.add(logoutButton).setLocation(170,180);

                inputResultsButton.addActionListener(e -> {
                    deenGetResult = true;
                    JOptionPane.showMessageDialog(frame, "Результаты экзамена введены!");
                });

                manageExamPermissionButton.addActionListener(e -> {
                    try {
                        manageExamPermissions(frame);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                break;
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void manageExamPermissions(JFrame parentFrame) throws IOException {

//        BufferedWriter writer = new BufferedWriter(new FileWriter("studentToExam.txt", true));

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Список студентов пуст!");
            return;
        }

        String[] studentArray = students.toArray(new String[0]);

        String selectedStudent = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Выберите студента для управления допуском:",
                "Управление допуском студентов",
                JOptionPane.QUESTION_MESSAGE,
                null,
                studentArray,
                studentArray[0]
        );

        if (selectedStudent != null) {
            int option = JOptionPane.showConfirmDialog(
                    parentFrame,
                    "Допустить студента " + selectedStudent + " к экзамену?",
                    "Подтвердите действие",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                PrintWriter w = new PrintWriter(new FileWriter(arrFileStudentToExam.getFirst(), true));
                w.println(selectedStudent);
                w.close();
                arrStringStudentToExam.add(selectedStudent);

                examResults.add(selectedStudent + " допущен к экзамену");
                JOptionPane.showMessageDialog(parentFrame, "Студент " + selectedStudent + " допущен к экзамену!");
            }
            else {
                examResults.add(selectedStudent + " не допущен к экзамену");
                JOptionPane.showMessageDialog(parentFrame, "Студент " + selectedStudent + " идет исправлять долги!");
            }
            saveData();
        }
    }

    private static void addGroup() {

        String groupName = JOptionPane.showInputDialog("Введите название группы:");

        if (groupName != null && !groupName.isEmpty()) {
            groups.add(groupName);
            JOptionPane.showMessageDialog(null, "Группа добавлена!");
            saveData();
        }
        else {
            JOptionPane.showMessageDialog(null, "Название группы не может быть пустым!");
        }
    }

    private static void assignStudentToGroup() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Список студентов пуст!");
            return;
        }
        if (groups.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Список групп пуст!");
            return;
        }

        String student = (String) JOptionPane.showInputDialog(
                null,
                "Выберите студента:",
                "Распределение по группе",
                JOptionPane.QUESTION_MESSAGE,
                null,
                students.toArray(),
                students.get(0)
        );

        if (student != null) {
            String group = (String) JOptionPane.showInputDialog(
                    null,
                    "Выберите группу:",
                    "Распределение по группе",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    groups.toArray(),
                    groups.getFirst() //!!!!!!!!!!!!!!
            );

            if (group != null) {
                JOptionPane.showMessageDialog(null, "Студент " + student + " добавлен в группу " + group);
                saveData();
            }
        }
    }
    // СДАЧА ЭКЗАМЕНА СТУДЕНТ--------------------------------------------------------------
    private static void giveExam(User student) {

        if(arrStringStudentToExam.contains(student.getUsername())) {

            String[] subjectArray = subjects.toArray(new String[0]);

            if (subjectArray.length == 0) {
                JOptionPane.showMessageDialog(null, "Нет доступных экзаменов!");
                return;
            }

            String selectedSubject = (String) JOptionPane.showInputDialog(
                    null,
                    "Выберите предмет:",
                    "Сдача экзамена",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    subjectArray,
                    subjectArray[0]
            );
            JOptionPane.showMessageDialog(null, "Экзамен сдан, ждите результаты!");

        }
        else {
            JOptionPane.showMessageDialog(null, "Вы не допущены к экзамену!");
        }
//        if (selectedSubject != null) {
//            String grade = JOptionPane.showInputDialog("Введите оценку для экзамена " + selectedSubject + ":");
//            if (grade != null) {
//                examResults.add(student.getUsername() + " - " + selectedSubject + ": " + grade);
//                JOptionPane.showMessageDialog(null, "Экзамен успешно сдан!");
//                saveData();
//            }
//        }
    }

    // СДАЧА ЭКЗАМЕНА СТУДЕНТ--------------------------------------------------------------


    // ПРИЕМ ЭКЗАМЕНА УЧИТЕЛЬ--------------------------------------------------------------

    private static void takeExam(User student) {

        if(arrStringStudentToExam.contains(student.getUsername())) {

            String[] subjectArray = subjects.toArray(new String[0]);

            if (subjectArray.length == 0) {
                JOptionPane.showMessageDialog(null, "Нет доступных экзаменов!");
                return;
            }

            String selectedSubject = (String) JOptionPane.showInputDialog(
                    null,
                    "Выберите предмет:",
                    "Сдача экзамена",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    subjectArray,
                    subjectArray[0]
            );
            JOptionPane.showMessageDialog(null, "Экзамен сдан, ждите результаты!");

        }
        else {
            JOptionPane.showMessageDialog(null, "Вы не допущены к экзамену!");
        }

    }
        // ПРИЕМ ЭКЗАМЕНА УЧИТЕЛЬ--------------------------------------------------------------

        private static void deleteUser(JFrame frame) {

        List<User> users = UserManager.loadUsers();
        String[] userNames = users.stream().map(User::getUsername).toArray(String[]::new);

        String selectedUser = (String) JOptionPane.showInputDialog(
                frame,
                "Выберите пользователя для удаления:",
                "Удалить пользователя",
                JOptionPane.QUESTION_MESSAGE,
                null,
                userNames,
                userNames.length > 0 ? userNames[0] : null
        );

        if (selectedUser != null) {
            users.removeIf(user -> user.getUsername().equals(selectedUser));
            UserManager.saveUsers(users);
            students.remove(selectedUser);
            arrStringStudentToExam.remove(selectedUser);
            dean.remove(selectedUser);
            teachers.remove(selectedUser);
            JOptionPane.showMessageDialog(frame, "Пользователь удален!");
            saveData();
        }
    }



//    private static void deleteSubject(JFrame frame) {
//        List<Subject> subjects = SubjectManager.loadSubject();
//        String[] subjectNames = subjects.stream().map(Subject::getSubject).toArray(String[]::new);
//
//        String selectedSubject = (String) JOptionPane.showInputDialog(
//                frame,
//                "Выберите пользователя для удаления:",
//                "Удалить пользователя",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                subjectNames,
//                subjectNames.length > 0 ? subjectNames[0] : null
//        );
//
//        if (selectedSubject != null) {
//            subjects.removeIf(subject -> subject.getSubject().equals(selectedSubject));
//            SubjectManager.saveSubject(subjects);
//            JOptionPane.showMessageDialog(frame, "Пользователь удален!");
//            saveData();
//        }
//    }









































    private static void loadData() {
        students = loadList("students.txt");
        teachers = loadList("teachers.txt");
        subjects = loadList("subjects.txt");
        examResults = loadList("exam_results.txt");
        groups = loadList("groups.txt");
    }

    private static List<String> loadList(String fileName) {
        List<String> list = new ArrayList<>();
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static void saveData() {
        saveList("students.txt", students);
        saveList("teachers.txt", teachers);
        saveList("subjects.txt", subjects);
        saveList("exam_results.txt", examResults);
        saveList("groups.txt", groups);
    }

    private static void saveList(String fileName, List<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String item : list) {
                bw.write(item);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
