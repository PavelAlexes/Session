import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String FILE_NAME = "users.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
        List<User> users = loadUsers();
        users.add(user);
        saveUsers(users);
    }

    public static User authenticate(String username, String password) {
        for (User user : loadUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

