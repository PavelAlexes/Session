import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static List<String> students = new ArrayList<>();
    private static List<String> teachers = new ArrayList<>();
    private static List<String> subjects = new ArrayList<>();
    private static List<String> examResults = new ArrayList<>();
    private static List<String> groups = new ArrayList<>();
    private  static List<String> dean = new ArrayList<>();

    public static void main(String[] args) {
        loadData();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Сессия");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

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

            loginButton.addActionListener(e -> {
                String username = loginField.getText();
                String password = new String(passwordField.getPassword());
                User user = UserManager.authenticate(username, password);

                if (user != null) {
                    frame.dispose();
                    openRoleInterface(user);
                } else {
                    JOptionPane.showMessageDialog(frame, "Неверный логин или пароль!");
                }
            });

            registerButton.addActionListener(e -> {
                String username = JOptionPane.showInputDialog("Введите имя пользователя:");
                String password = JOptionPane.showInputDialog("Введите пароль:");
                String[] roles = {"Администратор", "Студент", "Преподаватель", "Заместитель директора"};
                String role = (String) JOptionPane.showInputDialog(
                        frame,
                        "Выберите роль:",
                        "Регистрация",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roles,
                        roles[0]
                );

                if (username != null && password != null && role != null) {
                    UserManager.addUser(new User(username, password, role));
                    JOptionPane.showMessageDialog(frame, "Пользователь успешно зарегистрирован!");
                }
            });
        });
    }

    private static void openRoleInterface(User user) {
        JFrame frame = new JFrame("Интерфейс " + user.getRole());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveData();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton logoutButton = new JButton("Выйти");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            main(null);
        });

        switch (user.getRole()) {
            case "Администратор":
                JButton addGroupButton = new JButton("Добавить группу");
                JButton assignStudentToGroupButton = new JButton("Распределить студента по группе");

                JButton addStudentButton = new JButton("Добавить студента");
                JButton addTeacherButton = new JButton("Добавить преподавателя");
                JButton addDeanButton = new JButton("Добавить Заместителя директора");
                JButton addSubjectButton = new JButton("Добавить дисциплину");
                JButton deleteUserButton = new JButton("Удалить пользователя");

                panel.add(addGroupButton);
                panel.add(assignStudentToGroupButton);
                panel.add(addStudentButton);
                panel.add(addTeacherButton);
                panel.add(addDeanButton);
                panel.add(addSubjectButton);
                panel.add(deleteUserButton);
                panel.add(logoutButton);

                addGroupButton.addActionListener(e -> addGroup());
                assignStudentToGroupButton.addActionListener(e -> assignStudentToGroup());

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

                deleteUserButton.addActionListener(e -> {
                    deleteUser(frame);
                    saveData();
                });
                break;

            case "Студент":
                JButton takeExamButton = new JButton("Сдать экзамен");
                panel.add(takeExamButton);
                panel.add(logoutButton);

                takeExamButton.addActionListener(e -> {
                    takeExam(user);
                    saveData();
                });
                break;

            case "Преподаватель":
                panel.add(new JLabel("Интерфейс для проведения экзамена."));
                panel.add(logoutButton);
                break;

            case "Заместитель директора":
                JButton allowExamButton = new JButton("Допуск всех студентов к экзамену");
                JButton inputResultsButton = new JButton("Ввод результатов экзамена");
                JButton manageExamPermissionButton = new JButton("Управление допуском студентов");

                panel.add(allowExamButton);
                panel.add(inputResultsButton);
                panel.add(manageExamPermissionButton);
                panel.add(logoutButton);

                allowExamButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Все студенты допущены к экзамену!"));
                inputResultsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Результаты экзамена введены!"));

                manageExamPermissionButton.addActionListener(e -> manageExamPermissions(frame));
                break;
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void manageExamPermissions(JFrame parentFrame) {
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
                examResults.add(selectedStudent + " допущен к экзамену");
                JOptionPane.showMessageDialog(parentFrame, "Студент " + selectedStudent + " допущен к экзамену!");
            } else {
                examResults.add(selectedStudent + " НЕ допущен к экзамену");
                JOptionPane.showMessageDialog(parentFrame, "Студент " + selectedStudent + " НЕ допущен к экзамену!");
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
        } else {
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
                    groups.get(0)
            );

            if (group != null) {
                JOptionPane.showMessageDialog(null, "Студент " + student + " добавлен в группу " + group);
                saveData();
            }
        }
    }

    private static void takeExam(User student) {
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

        if (selectedSubject != null) {
            String grade = JOptionPane.showInputDialog("Введите оценку для экзамена " + selectedSubject + ":");
            if (grade != null) {
                examResults.add(student.getUsername() + " - " + selectedSubject + ": " + grade);
                JOptionPane.showMessageDialog(null, "Экзамен успешно сдан!");
                saveData();
            }
        }
    }

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
            JOptionPane.showMessageDialog(frame, "Пользователь удален!");
            saveData();
        }
    }

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
