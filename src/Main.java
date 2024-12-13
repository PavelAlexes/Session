//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Main {
//    private static List<String> students = new ArrayList<>();
//    private static List<String> teachers = new ArrayList<>();
//    private static List<String> subjects = new ArrayList<>();
//    private static List<String> examResults = new ArrayList<>();
//    private static List<String> groups = new ArrayList<>();
//    private  static List<String> dean = new ArrayList<>();
//    private static final String FILE_studentToExam = "studentToExam.txt";
//    private static final String FILE_studentGrades = "studentToGrades.txt";
//    private static final String FILE_studentFullGrades = "studentFullGrades.txt";
//    private static List<File> arrFileStudentToExam = new ArrayList<>();
//    private static List<String> arrStringStudentToExam = new ArrayList<>();
//    private static List<File> arrFileStudentGrades = new ArrayList<>();
//    private static List<String> arrStringStudentGrades = new ArrayList<>();
//    private static List<FullStudentGrades> arrFullStudentGrades = new ArrayList<>();
//    private static List<File> arrFullFileStringStudentGrades = new ArrayList<>();
//    private static List<String> arrFullStringStudentGrades = new ArrayList<>();
//    private static boolean deenGetResult = false;
//
//    public static void main(String[] args) {
//
//        loadData();
//
//        arrFileStudentToExam.add(new File(FILE_studentToExam));
//        arrFileStudentGrades.add(new File(FILE_studentGrades));
//        arrFullFileStringStudentGrades.add(new File(FILE_studentFullGrades));
//
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Сессия");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(400, 300);
//            frame.setLocation(600,250);
//
//            frame.addWindowListener(new java.awt.event.WindowAdapter() {
//                @Override
//                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                    saveData();
//                }
//            });
//
//            JPanel panel = new JPanel();
//            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//            JLabel loginLabel = new JLabel("Логин:");
//            JTextField loginField = new JTextField();
//            JLabel passwordLabel = new JLabel("Пароль:");
//            JPasswordField passwordField = new JPasswordField();
//
//            JButton loginButton = new JButton("Войти");
//            JButton registerButton = new JButton("Регистрация");
//
//            panel.add(loginLabel);
//            panel.add(loginField);
//            panel.add(passwordLabel);
//            panel.add(passwordField);
//            panel.add(loginButton);
//            panel.add(registerButton);
//
//            frame.add(panel);
//            frame.setVisible(true);
//
//            loginButton.addActionListener(e -> {
//                String username = loginField.getText();
//                String password = new String(passwordField.getPassword());
//                User user = UserManager.authenticate(username, password);
//
//                if (user != null) {
//                    frame.dispose();
//                    openRoleInterface(user);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Неверный логин или пароль!");
//                }
//            });
//
//            registerButton.addActionListener(e -> {
//                String username = JOptionPane.showInputDialog("Введите имя пользователя:");
//                String password = JOptionPane.showInputDialog("Введите пароль:");
//                String[] roles = {"Администратор", "Студент", "Преподаватель", "Заместитель директора"};
//                String role = (String) JOptionPane.showInputDialog(
//                        frame,
//                        "Выберите роль:",
//                        "Регистрация",
//                        JOptionPane.