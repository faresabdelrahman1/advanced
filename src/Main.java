import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// ================= Main.java =================
public class Main {
    public static void main(String[] args) {
        UserDAO.showAllUsers();

        new RegisterFrame();
    }
}



class RegisterFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    JButton registerBtn;
    JButton goToLoginBtn;

    public RegisterFrame() {

        setTitle("Register");
        setSize(400, 300);
        setLayout(null);

        getContentPane().setBackground(new Color(45, 45, 45));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        userLabel.setForeground(Color.WHITE);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        passLabel.setForeground(Color.WHITE);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        add(passwordField);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 160, 120, 35);
        registerBtn.setBackground(new Color(0, 200, 100));
        add(registerBtn);

        goToLoginBtn = new JButton("Login");
        goToLoginBtn.setBounds(150, 200, 120, 25);
        add(goToLoginBtn);

        // 🔥 Register Action
        registerBtn.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields ❗");
                return;
            }

            boolean success = RegisterDAO.register(username, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Registered Successfully 🎉");

                // 👉 يوديك للـ Main مباشرة
                new HomeFrame(); // أو MainFrame حسب عندك
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed ❌");
            }
        });

        // 🔥 Go to Login
        goToLoginBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ================= LoginFrame.java =================

class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 300);
        setLayout(null);

        getContentPane().setBackground(new Color(45, 45, 45));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        userLabel.setForeground(Color.WHITE);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 150, 30);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        passLabel.setForeground(Color.WHITE);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 150, 30);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 160, 120, 35);
        loginBtn.setBackground(new Color(255, 165, 0));
        add(loginBtn);

        loginBtn.addActionListener(e -> {

            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (UserDAO.checkLogin(user, pass)) {
                new HomeFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ================= HomeFrame.java =================

class HomeFrame extends JFrame {

    public HomeFrame() {
        setTitle("Home Page");
        setSize(450, 350);
        setLayout(null);

        getContentPane().setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Welcome to Restaurant System");
        title.setBounds(80, 30, 350, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        JButton menuBtn = new JButton("Menu");
        menuBtn.setBounds(150, 100, 120, 35);
        add(menuBtn);

        JButton aboutBtn = new JButton("About");
        aboutBtn.setBounds(150, 150, 120, 35);
        add(aboutBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(150, 200, 120, 35);
        add(exitBtn);

        menuBtn.addActionListener(e -> {
            new MenuFrame();
            dispose();
        });

        aboutBtn.addActionListener(e -> new AboutFrame());

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ================= MenuFrame.java =================

class MenuFrame extends JFrame {

    static ArrayList<String> order = new ArrayList<>();

    public MenuFrame() {
        setTitle("Menu");
        setSize(520, 420);
        setLayout(null);

        getContentPane().setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Food Menu");
        title.setBounds(200, 10, 200, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        JButton pizza = new JButton("Pizza - 50");
        pizza.setBounds(50, 60, 150, 30);
        add(pizza);

        JButton burger = new JButton("Burger - 40");
        burger.setBounds(50, 100, 150, 30);
        add(burger);

        JButton pasta = new JButton("Pasta - 60");
        pasta.setBounds(50, 140, 150, 30);
        add(pasta);

        JButton fries = new JButton("Fries - 25");
        fries.setBounds(50, 180, 150, 30);
        add(fries);

        JButton cola = new JButton("Cola - 20");
        cola.setBounds(50, 220, 150, 30);
        add(cola);

        JButton goOrder = new JButton("Go To Order");
        goOrder.setBounds(300, 320, 150, 35);
        goOrder.setBackground(new Color(255, 165, 0));
        add(goOrder);

        pizza.addActionListener(e -> order.add("Pizza"));
        burger.addActionListener(e -> order.add("Burger"));
        pasta.addActionListener(e -> order.add("Pasta"));
        fries.addActionListener(e -> order.add("Fries"));
        cola.addActionListener(e -> order.add("Cola"));

        goOrder.addActionListener(e -> {
            new OrderFrame(order);
            dispose();
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ================= OrderFrame.java =================




class OrderFrame extends JFrame {

    public OrderFrame(ArrayList<String> order) {
        setTitle("Order");
        setSize(400, 300);
        setLayout(null);

        getContentPane().setBackground(new Color(50, 50, 50));

        JTextArea area = new JTextArea();
        area.setBounds(50, 50, 300, 120);
        add(area);

        StringBuilder sb = new StringBuilder();
        for (String item : order) {
            sb.append(item).append("\n");
        }
        area.setText(sb.toString());

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(140, 200, 120, 30);
        confirm.setBackground(new Color(255, 165, 0));
        add(confirm);

        confirm.addActionListener(e -> {
            new BillFrame(order);
            dispose();
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ================= BillFrame.java =================

class BillFrame extends JFrame {

    public BillFrame(ArrayList<String> order) {
        setTitle("Bill");
        setSize(400, 300);
        setLayout(null);

        getContentPane().setBackground(new Color(25, 25, 25));

        JTextArea area = new JTextArea();
        area.setBounds(50, 50, 300, 120);
        add(area);

        int total = 0;
        StringBuilder sb = new StringBuilder();

        for (String item : order) {
            switch(item) {
                case "Pizza": total += 50; break;
                case "Burger": total += 40; break;
                case "Pasta": total += 60; break;
                case "Fries": total += 25; break;
                case "Cola": total += 20; break;
            }
            sb.append(item).append("\n");
        }

        sb.append("\nTotal: ").append(total);
        area.setText(sb.toString());

        JButton finish = new JButton("Finish");
        finish.setBounds(140, 200, 120, 30);
        finish.setBackground(new Color(255, 165, 0));
        add(finish);

        finish.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order Completed!");
            System.exit(0);
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



class AboutFrame extends JFrame {

    public AboutFrame() {
        setTitle("About");
        setSize(400, 250);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 40, 40));

        JLabel text = new JLabel("Restaurant System Project");
        text.setBounds(80, 60, 300, 30);
        text.setForeground(Color.WHITE);
        add(text);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

