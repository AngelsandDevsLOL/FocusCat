import javax.imageio.ImageIO; // good luck haha
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class GUI extends Canvas {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
    class User {
        private String username;
        private String password;
        private LocalDate dayUsed;
        private int days;
        private String customizationNum;
        private int currentFocus;
        private int goal;
        private int catsKilled;

        public User(String u, String p, String t, int d, String cu, int cur, int g, int k) {
            username = u;
            password = p;
            if (!t.equals("0")) {
                dayUsed = LocalDate.parse(t, formatter);
            } else {
                dayUsed = null;
            }
            days = d;
            customizationNum = cu;
            currentFocus = cur;
            goal = g;
            catsKilled = k;
        }
    }
    private String username;
    private String password;
    private LocalDate dayUsed; // last time the program was used
    private int days; // consecutive days (could be negative !)
    private String customizationNum; // base color + " " + row + " " + column
    private int currentFocus; // how many minutes have you focused today?
    private int goal; // How many minutes do I want to focus today?
    private int catsKilled;
    private Font ftext = new Font("Serif", Font.PLAIN, 17);
    private Font ftitle = new Font("Serif", Font.PLAIN, 26);
    private LocalDate date = LocalDate.now(TimeZone.getDefault().toZoneId());
    private Color c2 = (new java.awt.Color(168, 230, 207));
    private Color c1 = (new java.awt.Color(220, 237, 193));
    private Color c3 = new java.awt.Color(255,211,182);
    private Color c4 = new java.awt.Color(255, 170, 165);
    private Color c5 = new java.awt.Color(255, 139, 148);
    HashMap<String, String> hMap = new HashMap<>();
    ArrayList<User> aList = new ArrayList<>();
    File f = new File("src/main/accounts");

    public void infoStuff() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(f));
        String[] input = br.readLine().split(" ");

        while (input.length != 1) {
            aList.add(new User(input[1], input[2], (input[3]), Integer.parseInt(input[4]), (input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]), Integer.parseInt(input[8])));
            hMap.put(input[1], input[2]);
            input = br.readLine().split(" ");
        }
    }

    public GUI() throws IOException {
        infoStuff();
        fillArray();
        getLogin();
    }
    private JLabel[][][] label = new JLabel[13][3][3];
    private int desiredWidth = 275, desiredHeight = 275;
    public void fillArray() throws IOException{
//# DARK GREY
        label[0][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_plain.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][0][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_black_stripes.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][1][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_black_stripes_weird_belly.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_normal_belly.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][2][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_grey/grey_black_stripes_normal_belly.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_grey/s_dg.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_grey/s_dg_weird_belly.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[0][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_grey/s_dg_normal_belly.png")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));


//# LIGHT GREY
        label[1][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/plain_grey.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][0][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/grey_stripes.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/grey_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][1][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/grey_stripes_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/grey_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][2][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_grey/grey_stripes_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_grey/s_lg.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_grey/s_lg_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[1][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_grey/s_lg_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Rust Blue
        label[2][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/rust_blue/plain_rb.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[2][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/rust_blue/rb_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
        label[2][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/rust_blue/rb_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[2][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_rust_blue/s_rb.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[2][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_rust_blue/s_rb_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[2][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_rust_blue/s_rb_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Normal Blue
        label[3][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/blue/plain_blue.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[3][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/blue/blue_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[3][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/blue/blue_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[3][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_blue/s_blue.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[3][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_blue/s_blue_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[3][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_blue/s_blue_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));




//# Purple
        label[4][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/purple/plain_purple.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[4][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/purple/purple_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[4][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/purple/purple_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[4][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_purple/s_purple.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[4][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_purple/s_purple_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[4][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_purple/s_purple_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Pink
        label[5][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/pink/plain_pink.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[5][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/pink/pink_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[5][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/pink/pink_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[5][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_pink/s_pink.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[5][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_pink/s_pink_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[5][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_pink/s_pink_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Light Orange
        label[6][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_orange/plain_light_orange.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[6][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_orange/lo_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[6][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/light_orange/lo_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[6][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_orange/s_lo.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[6][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_orange/s_lo_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[6][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_light_orange/s_lo_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Dark Orange
        label[7][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_orange/plain_orange.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[7][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_orange/do_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[7][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/dark_orange/do_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[7][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_orange/s_orange.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[7][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_orange/s_orange_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[7][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_dark_orange/s_orange_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Brown
        label[8][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/plain_brown.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][0][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/brown_stripes.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/brown_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][1][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/brown_stripes_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/brown_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][1][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/brown/brown_stripes_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_brown/s_brown.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
        label[8][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_brown/s_brown_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[8][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_brown/s_brown_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Yellow
        label[9][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/plain_yellow.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][0][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/yellow_stripes.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/yellow_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/yellow_stripes_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/yellow_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][2][1] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/yellow/yellow_stripes_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_yellow/s_yellow.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_yellow/s_yellow_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[9][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_yellow/s_yellow_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
//# Green
        label[10][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/green/plain_green.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[10][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/green/green_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[10][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/green/green_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[10][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_green/s_green.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[10][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_green/s_green_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[10][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_green/s_green_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# Off White
        label[11][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/off_white/plain_off_white.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[11][1][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/off_white/ow_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[11][2][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/off_white/ow_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[11][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_off_white/s_ow.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[11][1][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_off_white/s_ow_weird_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[11][2][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_off_white/s_ow_normal_belly.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

//# White
        label[12][0][0] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/white/plain_white.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

        label[12][0][2] = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/s_white/s_white.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

    }
    boolean bool = false;
    public void getLogin() throws IOException {
        JFrame frame = new JFrame("Focus Cat");
        JPanel panel = new JPanel();

        JLabel l = new JLabel("Login!");
        l.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
        panel.setLayout(null);
        frame.setSize(425, 225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(10, 20, 175, 25);
        panel.add(l);


        /**
         * https://beginnersbook.com/2015/07/java-swing-tutorial/
         */
        frame.add(panel, BorderLayout.CENTER);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 50, 175, 25);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(200, 50, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 175, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 80, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");

        loginButton.setBounds(10, 110, 175, 25);
        panel.add(loginButton);

        frame.setVisible(true);
        /** End of copied code */

        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(200, 110, 165, 25);
        panel.add(signupButton);
        signupButton.setBackground(c1);
        loginButton.setBackground(c1);
        JLabel text = new JLabel("");
        text.setBounds(10, 140, 250, 25);
        panel.setBackground(c2);
        panel.add(text);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = userText.getText();
                String passwordInput = passwordText.getText();
              //  myMethod.execute();
                if (!hMap.containsKey(usernameInput)) {
                    text.setText("Error: No user with such username");
                } else {
                    for (int i = 0; i < aList.size(); i++) {
                        User u = aList.get(i);
                        if (u.username.equals(usernameInput)) {
                            bool = true;
                            if (u.password.equals(passwordInput)) {
                                username = u.username;
                                password = u.password;
                                dayUsed = u.dayUsed;
                                days = u.days;
                                customizationNum = u.customizationNum;
                                currentFocus = u.currentFocus;
                                goal = u.goal;
                                catsKilled = u.catsKilled;
                                frame.dispose();
                                try {
                                    welcome();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            text.setText("Error: Password is incorrect");
                        }
                    }
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    signUp();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void signUp() throws IOException {
        JFrame frame = new JFrame("Focus Cat");
        JPanel panel = new JPanel();
        JLabel l = new JLabel("Sign up!");
        l.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
        panel.setLayout(null);
        frame.setSize(425, 245);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(10, 20, 175, 25);
        panel.add(l);
        frame.add(panel, BorderLayout.CENTER);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 50, 175, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(200, 50, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 175, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 80, 165, 25);
        panel.add(passwordText);

        JLabel confirmPassword = new JLabel("Confirm Password:");
        confirmPassword.setBounds(10, 110, 175, 25);
        panel.add(confirmPassword);

        JPasswordField confirmText = new JPasswordField(20);
        confirmText.setBounds(200, 110, 165, 25);

        panel.add(confirmText);

        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(10, 140, 175, 25);
        panel.add(signupButton);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 140, 165, 25);
        panel.add(loginButton);
        signupButton.setBackground(c1);
        loginButton.setBackground(c1);

        JLabel label = new JLabel("");
        label.setBounds(10, 170, 500, 25);
        panel.add(label);
        panel.setBackground(c2);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 6-13
                // has to have at least 1 number and 1 letter
                String usernameInput = userText.getText();
                String passwordInput = passwordText.getText();
                String confirmInput = confirmText.getText();
                if (hMap.containsKey(usernameInput)) {
                    label.setText("Username already taken");
                } else {
                    if (!(passwordInput.length() >= 7 && passwordInput.length() <= 15)) {
                        label.setText("Password must be between 7-15 characters inclusive");
                    } else {
                        if (confirmInput.equals(passwordInput)) {
                            name:
                            for (int i = 0; i < 26; i++) {
                                if (passwordInput.contains(String.valueOf((char) (i + 'a')))) {
                                    for (int j = 0; j < 9; j++) {
                                        if (passwordInput.contains(String.valueOf((char) (j + '0')))) {
                                            try {
                                                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/accounts", true));
                                                bw.write(" " + usernameInput + " " + passwordInput + " 0 0 12,0,0 0 25 0");
                                                bw.newLine();
                                                bw.write("0");
                                                bw.flush();
                                                aList.add(new User(usernameInput, passwordInput, "0", 0, "12,0,0", 0, 25, 0));
                                                hMap.put(usernameInput, passwordInput);

                                                frame.dispose();
                                                getLogin();
                                                break name;
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        } else {

                                        }
                                    }
                                }

                            }
                            label.setText("Password must have at least 1 character and letter");
                        } else {
                            label.setText("Passwords do not match");
                        }
                    }
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    getLogin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.setVisible(true);
    }
    private  JLabel catsKill = new JLabel("Cats Killed: " + catsKilled);
    private JPanel panel = new JPanel();
    private JLabel img = new JLabel();
    private JLabel streak = new JLabel("0");

    public void welcome() throws IOException {
        JFrame frame = new JFrame("Focus Cat");
        JLabel welcome = new JLabel("WELCOME PAGE!");
        welcome.setFont(new Font("Broadway", Font.BOLD, 30));
        welcome.setBounds(50, 375, 500, 50);
        panel.add(welcome);
        welcome.setFont(new Font("Lucida", Font.BOLD, 25));
        panel.setLayout(null);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(null);
        bigPanel.setBounds(415, 75, 325, 425);
        bigPanel.setBackground(c1);
        panel.add(bigPanel);

        JLabel l = new JLabel("00:30:00");                     /** timer */
        l.setFont(new Font("Serif", Font.PLAIN, 70));
        l.setBounds(35, 45, 300, 70);
        bigPanel.add(l);

        catsKill.setFont(ftext);
        catsKill.setBounds(50, 1, 300, 50);
        panel.add(catsKill);

        streak.setFont(ftext);
        streak.setBounds(160, 25, 300, 50);

        JLabel currentStreak = new JLabel("Current Streak: "); // productive days
        currentStreak.setBounds(50, 25, 300, 50);
        currentStreak.setFont(ftext);
        panel.add(currentStreak);

        panel.add(streak);

        String[] num = customizationNum.split(",");
        JLabel img = null;
        if (num[0].equals("-1")) {
            if (num[2].equals("0")) { // if it is sad
                img = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/box.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
            } else if (num[2].equals("1")) { // death by stabbing
                img = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/_stabbed.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
            } else if (num[2].equals("2")) { // death by beheading
                img = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/_beheaded.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));
            }
        } else {
            img = label[Integer.parseInt(num[0])][Integer.parseInt(num[1])][Integer.parseInt(num[2])];
            img.setBounds(50,80, 275, 275);
        }
        img.setBounds(50,80, 275, 275);
        panel.add(img);

        panel.setBackground(c2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font("Serif", Font.PLAIN, 17));
        instructions.setBounds(600, 15, 120, 30);
        panel.add(instructions);
        instructions.setBackground(c1);

        JFrame instructionsFrame = new JFrame();
        instructionsFrame.setSize(800, 640);
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(null);
        instructionsPanel.setBackground(c2);
        instructionsFrame.add(instructionsPanel, BorderLayout.CENTER);

        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.setVisible(false);


        JTextArea instructionsText = new JTextArea("                Every day, there is a timer with the previously entered goal time to focus. If you start to focus \n        before the end of the day (11:59 pm in your local time zone) and complete the allotted amount of \n        time, your cat will become happier. The goal of the app is for you to keep your daily streak of meeting \n        your focus time, which is equivalent to keeping your cat healthy and alive. \n" +
                "\n" +
                "                In order to log the time you focus, they should start the given timer, which will add to your time \n        completed for the day. You may split your total time into chunks: for example, completing 5 minutes \n        in the morning and 5 in the evening. Time will be added to the day you start, for example starting at \n        11:50 on a Sunday and continuing to work until 12:40 am on Monday adds to Sunday's focus time. \n        Throughout the focus time, the cat will display motivational messages to keep you focused.\n" +
                "\n" +
                "                Once you have reached or passed your goal, you will be allowed to customize your cat by \n        changing the base colour, adding or removing stripes, or changing belly patterns. Each base coat will \n        only have one possible stripe pattern. You cannot customize your cat more than once a day. If you are \n        consistent and continue to keep your streak, they will have the option to add accessories to your cat, \n        making it happier.\n" +
                "\n" +
                "                However, if one or multiple days pass without you completing your focus time, then the following \n        punishments are given:\n" +
                "\n" +
                "                -  After 1 day, the cat is sad\n" +
                "                -  After 2 days, the cat starts to get sick and loses their stripes, if they have one\n" +
                "                -  After 3 days, the cat is severely sick and loses their belly pattern, if they have one\n" +
                "                -  After 4 days, the cat loses their fur colour dies from a randomly generated method of death\n" +
                "\n" +
                "                Depending on the severity of the cat's sickness, it will take shorter/longer to heal them. For \n        example, healing from a mild sickness will be easier than healing from a more severe sickness. \n        Furthermore, no customizations can be made if the cat is sick, even if the user reaches their goal. If \n        the cat dies, then you must restart your progress with all your accessories removed.\n");
        instructionsText.setBounds(75, 75,650, 600);
        JScrollPane mySchroll = new JScrollPane(instructionsText);
        mySchroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mySchroll.setSize(new Dimension(800, 600));
        instructionsPanel.add(mySchroll,BorderLayout.CENTER);
        instructionsText.setFont(new Font("Serif", Font.PLAIN, 17));
        instructionsText.setBackground(c2);

        JFrame detailsFrame = new JFrame();
        detailsFrame.setSize(800, 600);
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(c2);
        detailsFrame.add(detailsPanel, BorderLayout.CENTER);

        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setVisible(false);


        JTextArea detailsText = new JTextArea("        Welcome to Focus Cat, an app to keep you productive while raising a beautiful cat! \n" +
                "\n" +
                "                You raise your cat by setting a timer(s) to focus for a certain amount of time every day. By \n        default, you must focus for 1 hour each day, but you can change this amount in the welcoming page. \n        If you focus for the required amount of time, your cat will be happy and you get to make one \n        customization to your cat. If you don’t, your cat will miss you and become sad or eventually become \n        sick. For each day your cat is sick, it loses one of your customizations. If you keep neglecting your \n        cat, its health will continuously decline until it either dies or commits suicide (because it hates you \n        so much). You may start again with a new cat, but the number of cats you kill will be permanently \n        displayed on the welcoming page so that you can feel guilty about how many innocent cats die \n        because of you.\n" +
                "\n" +
                "        Will you successfully raise a beautiful cat or will you become a merciless cat killer?\n" +
                "\n" +
                "        Enjoy this app and have fun!\n" +
                "\n" +
                "\n" +
                "        * FocusCatInc is not responsible for any trauma you may experience while using this app\n");
        detailsText.setBounds(75, 75,650, 600);
        JScrollPane scroll = new JScrollPane(detailsText);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setSize(new Dimension(800, 600));
        detailsPanel.add(scroll,BorderLayout.CENTER);
        detailsText.setFont(new Font("Serif", Font.PLAIN, 17));
        detailsText.setBackground(c2);

        JButton details = new JButton("Details");
        details.setFont(ftext);
        details.setBounds(450, 15, 90, 30);
        panel.add(details);
        details.setBackground(c1);

        JButton focus = new JButton("Focus!");
        focus.setBounds(110, 125, 100, 30);
        focus.setFont(ftext);
        bigPanel.add(focus);
        focus.setBackground(c3);

        JLabel timer = new JLabel("Timer: ");
        timer.setBounds(140, 170, 100, 25);
        timer.setFont(ftext);
        bigPanel.add(timer);

        SpinnerModel model = new SpinnerNumberModel(0, 0, 11, 1);

        JLabel hours = new JLabel("Hr: ");
        hours.setBounds(35, 215, 60, 25);
        bigPanel.add(hours);
        hours.setFont(ftext);

        JSpinner spinner = new JSpinner(model); // hours
        spinner.setBounds(65, 215, 35, 25);
        bigPanel.add(spinner);
        spinner.setFont(ftext);

        SpinnerModel model2 = new SpinnerNumberModel(30, 0, 59, 5);

        JLabel minutes = new JLabel("Min: ");
        minutes.setBounds(105, 215, 50, 25);
        bigPanel.add(minutes);
        minutes.setFont(ftext);

        JSpinner spinner2 = new JSpinner(model2); // minutes
        spinner2.setBounds(145, 215, 40, 25);
        spinner2.setFont(ftext);
        bigPanel.add(spinner2);

        SpinnerModel model3 = new SpinnerNumberModel(0, 0, 59, 5);

        JLabel seconds = new JLabel("Sec: ");
        seconds.setBounds(195, 215, 50, 25);
        bigPanel.add(seconds);
        seconds.setFont(ftext);

        JSpinner spinner3 = new JSpinner(model3); // seconds
        spinner3.setFont(ftext);
        spinner3.setBounds(230, 215, 40, 25);
        bigPanel.add(spinner3);


        JButton customizeButton = new JButton("Customize!");
        customizeButton.setFont(ftext);
        customizeButton.setBackground(c1);
        customizeButton.setBounds(50, 460, 175, 30);

        JLabel customizeProblem = new JLabel("Cat cannot be customized, not at full health");
        JLabel customizeProblem2 = new JLabel("or goal has not been reached");
        customizeProblem.setBounds(50, 450, 500, 30);
        customizeProblem2.setBounds(50, 480, 500, 30);
        customizeProblem2.setFont(ftext);
        customizeProblem.setFont(ftext);

        if (days >= 0 && currentFocus >= goal) {
            panel.add(customizeButton);
        } else {
            panel.add(customizeProblem);
            panel.add(customizeProblem2);
        }

        JLabel goalReached = new JLabel("");
        if (currentFocus >= goal) {
            goalReached.setText("Goal has been reached!");
        } else {
            goalReached.setText("Goal has not been reached! :(");
        }
        goalReached.setFont(ftext);
        goalReached.setBounds(35, 250, 300,20);

        bigPanel.add(goalReached);

        JLabel printGoal = new JLabel("Total Focus:            seconds");
        printGoal.setBounds(75, 280, 500, 20);
        printGoal.setFont(ftext);
        bigPanel.add(printGoal);

        JLabel current = new JLabel(String.valueOf(currentFocus));
        current.setBounds(170, 280, 200, 20);
        current.setFont(ftext);
        bigPanel.add(current);

        JLabel goall = new JLabel("Current Goal:            seconds");
        goall.setBounds(66, 305, 500, 20);
        goall.setFont(ftext);
        bigPanel.add(goall);

        JLabel goalll = new JLabel(String.valueOf(goal));
        goalll.setBounds(170, 305, 100, 20);
        goalll.setFont(ftext);
        bigPanel.add(goalll);

        JLabel hours1 = new JLabel("Hr: ");
        hours1.setBounds(35, 340, 55, 25);
        bigPanel.add(hours1);
        hours1.setFont(ftext);

        SpinnerModel model1 = new SpinnerNumberModel(goal / 3600, 0, 11, 1);

        JSpinner spinner1 = new JSpinner(model1); // hours
        spinner1.setBounds(65, 340, 35, 25);
        bigPanel.add(spinner1);
        spinner1.setFont(new Font("Serif", Font.PLAIN, 17));

        JLabel minutes1 = new JLabel("Min: ");
        minutes1.setBounds(105, 340, 50, 25);
        bigPanel.add(minutes1);
        minutes1.setFont(ftext);

        SpinnerModel model21 = new SpinnerNumberModel((goal % 3600) / 60, 0, 59, 5);

        JSpinner spinner21 = new JSpinner(model21); // minutes
        spinner21.setBounds(145, 340, 40, 25);
        spinner21.setFont(ftext);
        bigPanel.add(spinner21);

        JLabel seconds1 = new JLabel("Sec: ");
        seconds1.setBounds(195, 340, 50, 25);
        bigPanel.add(seconds1);
        seconds1.setFont(ftext);

        JButton setGoal = new JButton("Set Goal");
        setGoal.setBounds(115, 380, 100, 25);
        setGoal.setBackground(c3);
        bigPanel.add(setGoal);
        setGoal.setFont(ftext);

        SpinnerModel model31 = new SpinnerNumberModel(goal % 60, 0, 59, 5);

        JSpinner spinner31 = new JSpinner(model31); // seconds
        spinner31.setFont(ftext);
        spinner31.setBounds(230, 340, 40, 25);
        bigPanel.add(spinner31);

        setGoal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int number = goal;
                goal = (Integer)spinner1.getValue() * 3600 + (Integer)spinner21.getValue()  * 60 + (Integer)spinner31.getValue();
                goalll.setText(String.valueOf(goal));
                if (number <= currentFocus && goal > currentFocus) {
                    goalReached.setText("Goal has not been reached! :(");
                    Container c = customizeButton.getParent();
                    c.remove(customizeButton);
                    c.validate();
                    c.repaint();
                    panel.add(customizeProblem);
                    panel.add(customizeProblem2);
                } else if (number > currentFocus && goal <= currentFocus) {
                    goalReached.setText("Goal has been reached!");
                    panel.add(customizeButton);
                    Container c = customizeProblem.getParent();
                    c.remove(customizeProblem);
                    c.validate();
                    c.repaint();
                    c = customizeProblem2.getParent();
                    c.remove(customizeProblem2);
                    c.validate();
                    c.repaint();
                    try {
                        rewriteFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        customizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Container c = bigPanel.getParent();
                c.remove(bigPanel);
                c.validate();
                c.repaint();

                c = customizeButton.getParent();
                c.remove(customizeButton);
                c.validate();
                c.repaint();

                try {
                    customization();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deathMessage.setBounds(30, 425, 500, 35);
        panel.add(deathMessage);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        focus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusing(l, (Integer) spinner.getValue(), (Integer) spinner2.getValue(), (Integer) spinner3.getValue());
                myMethod.execute();
                try {
                    rewriteFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionsFrame.setVisible(true);
            }
        });
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsFrame.setVisible(true);
            }
        });
    }
    private int x = -1;
    private int y = -1;
    private int z = -1;
    public void customization() throws IOException {
        JFrame frame = new JFrame("Focus Cat");
        frame.setSize(800, 600);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(null);
        bigPanel.setBounds(400, 75, 340, 425);
        bigPanel.setBackground(c1);
        panel.add(bigPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton preview = new JButton("Preview Cat");
        preview.setBounds(90,20, 150, 30);
        preview.setFont(ftext);
        preview.setBackground(c3);
        bigPanel.add(preview);

        JButton timer = new JButton("Focus");
        timer.setBackground(c1);
        timer.setFont(ftext);
        timer.setBounds(50, 460, 175, 30);
        panel.add(timer);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container c = timer.getParent();
                c.remove(timer);
                c.validate();
                c.repaint();

                c = bigPanel.getParent();
                c.remove(bigPanel);
                c.validate();
                c.repaint();

                frame.dispose();
                try {
                    welcome();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JLabel baseLabel = new JLabel("Change Base:");
        baseLabel.setFont(ftext);
        baseLabel.setBounds(15, 65, 175, 25);
        bigPanel.add(baseLabel);

        JRadioButton b1dg = new JRadioButton("Dark Gray"); b1dg.setFont(ftext);
        JRadioButton b1lg = new JRadioButton("Light Gray"); b1lg.setFont(ftext);
        JRadioButton b1rb = new JRadioButton("Russian Blue"); b1rb.setFont(ftext);
        JRadioButton b1be = new JRadioButton("Blue"); b1be.setFont(ftext);
        JRadioButton b1pe = new JRadioButton("Purple"); b1pe.setFont(ftext);
        JRadioButton b1pk = new JRadioButton("Pink"); b1pk.setFont(ftext);
        JRadioButton b1lo = new JRadioButton("Light Orange"); b1lo.setFont(ftext);
        JRadioButton b1do = new JRadioButton("Dark Orange"); b1do.setFont(ftext);
        JRadioButton b1bn = new JRadioButton("Brown"); b1bn.setFont(ftext);
        JRadioButton b1y = new JRadioButton("Yellow"); b1y.setFont(ftext);
        JRadioButton b1g = new JRadioButton("Green"); b1g.setFont(ftext);
        JRadioButton b1ow = new JRadioButton("Off White"); b1ow.setFont(ftext);
        JRadioButton b1w = new JRadioButton("White"); b1w.setFont(ftext);
        ButtonGroup base = new ButtonGroup();
        Color buttonColor = c3;
        base.add(b1dg); base.add(b1rb); base.add(b1lo); base.add(b1do); base.add(b1bn);
        base.add(b1lg); base.add(b1be); base.add(b1pe); base.add(b1pk); base.add(b1y);
        base.add(b1g); base.add(b1ow); base.add(b1w);
        b1dg.setBackground(buttonColor); b1rb.setBackground(buttonColor); b1lo.setBackground(buttonColor);
        b1do.setBackground(buttonColor); b1bn.setBackground(buttonColor); b1lg.setBackground(buttonColor);
        b1pk.setBackground(buttonColor); b1pe.setBackground(buttonColor); b1be.setBackground(buttonColor);
        b1y.setBackground(buttonColor); b1g.setBackground(buttonColor); b1w.setBackground(buttonColor);
        b1ow.setBackground(buttonColor);
        b1dg.setBounds(15, 90, 150, 25); b1rb.setBounds(15, 140, 150, 25);
        b1lg.setBounds(15, 115, 150, 25); b1be.setBounds(15, 165, 150, 25);
        b1pe.setBounds(15, 190, 150, 25); b1pk.setBounds(15, 215, 150, 25);
        b1lo.setBounds(15, 240, 150, 25); b1do.setBounds(15, 265, 150, 25);
        b1bn.setBounds(15, 290, 150, 25); b1y.setBounds(15, 315, 150, 25);
        b1g.setBounds(15, 340, 150, 25); b1ow.setBounds(15, 365, 150, 25);
        b1w.setBounds(15, 390, 150, 25);
        bigPanel.add(b1dg); bigPanel.add(b1lg); bigPanel.add(b1rb); bigPanel.add(b1be);
        bigPanel.add(b1pe); bigPanel.add(b1pk); bigPanel.add(b1lo); bigPanel.add(b1do);
        bigPanel.add(b1bn); bigPanel.add(b1y); bigPanel.add(b1g); bigPanel.add(b1ow);
        bigPanel.add(b1w);
        JButton confirm = new JButton("Confirm");
        confirm.setBackground(c1);
        confirm.setBounds(50,450, 100, 30);
        bigPanel.add(confirm);

        JLabel stripe = new JLabel("Stripes: ");
        stripe.setBounds(175, 65, 175, 25);
        stripe.setFont(ftext);
        bigPanel.add(stripe);

        JRadioButton stripes = new JRadioButton("Stripes"); stripes.setFont(ftext);
        JRadioButton no = new JRadioButton("No Stripes"); no.setFont(ftext);
        ButtonGroup str = new ButtonGroup(); str.add(stripes); str.add(no);
        stripes.setBackground(c4); no.setBackground(c4);
        stripes.setBounds(175, 90, 150, 25);
        no.setBounds(175, 115, 150, 25);
        bigPanel.add(stripes);
        bigPanel.add(no);

        JRadioButton bp1 = new JRadioButton("No Belly Pattern"); bp1.setFont(ftext);
        JRadioButton bp2 = new JRadioButton("Belly Pattern 1"); bp2.setFont(ftext);
        JRadioButton bp3 = new JRadioButton("Belly Pattern 2"); bp3.setFont(ftext);
        ButtonGroup bellyPattern = new ButtonGroup();
        bellyPattern.add(bp1); bigPanel.add(bp1); bp1.setBounds(175, 175, 150, 25);
        bellyPattern.add(bp2); bigPanel.add(bp2); bp2.setBounds(175, 200, 150, 25);
        bellyPattern.add(bp3); bigPanel.add(bp3); bp3.setBounds(175, 225, 150, 25);
        bp1.setBackground(c5);         bp2.setBackground(c5);         bp3.setBackground(c5);

        JLabel bellyLabel = new JLabel("Belly Pattern: ");
        bellyLabel.setBounds(175, 150, 150, 25);
        bellyLabel.setFont(ftext);
        bigPanel.add(bellyLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JButton create = new JButton("Customize");
        create.setBounds(175, 270, 150, 30);
        create.setFont(ftext);
        create.setBackground(Color.WHITE);
        bigPanel.add(create);

        JLabel feedback = new JLabel("");
        feedback.setBounds(175, 315, 150, 30);
        feedback.setFont(ftext);
        bigPanel.add(feedback);

        preview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1dg.isSelected()) {
                    x = 0;
                } else if (b1lg.isSelected()) {
                    x = 1;
                } else if (b1rb.isSelected()) {
                    x = 2;
                } else if (b1be.isSelected()) {
                    x = 3;
                } else if (b1pe.isSelected()) {
                    x = 4;
                } else if (b1pk.isSelected()) {
                    x = 5;
                } else if (b1lo.isSelected()) {
                    x = 6;
                } else if (b1do.isSelected()) {
                    x = 7;
                } else if (b1bn.isSelected()) {
                    x = 8;
                } else if (b1y.isSelected()) {
                    x = 9;
                } else if (b1g.isSelected()) {
                    x = 10;
                } else if (b1ow.isSelected()) {
                    x = 11;
                } else if (b1w.isSelected()) {
                    x = 12;
                }
                if (stripes.isSelected()) {
                    y = 0;
                } else if (no.isSelected()) {
                    y = 1;
                }

                if (bp1.isSelected()) {
                    z = 0;
                } else if (bp2.isSelected()) {
                    z = 1;
                } else if (bp3.isSelected()) {
                    z = 2;
                }
                if (x != -1 && y != -1 && z != -1) {
                    if (label[x][y][z] == null) {
                        feedback.setText("Not a possible combo");
                    } else {
                        ImageIcon icon = new ImageIcon("src/main/_CAT/blue/plain_blue.png");
                        icon.getImage().flush();
                        img.setIcon(icon);
                        feedback.setText("");
                        customizationNum = x + "," + y + "," + z;
                    }
                } else {
                    feedback.setText("Fill in the form");
                }
            }
        });

    }
    LocalDate firstDate = LocalDate.now(TimeZone.getDefault().toZoneId());
    LocalDate secondDate;
    private JLabel jLabel1 = new JLabel();
    private JLabel deathMessage = new JLabel();
    // declare variables LocalDate firstDate and LocalDate secondDate in the class and store them in the file
    // declare variables LocalDate firstDate and LocalDate secondDate in the class and store them in the file
    public void updateImage(String[] methodNum) {

        Container parent = img.getParent();
        parent.remove(img);
        parent.validate();
        parent.repaint();
        img = label[Integer.parseInt(methodNum[0])][Integer.parseInt(methodNum[1])][Integer.parseInt(methodNum[2])];
        img.setBounds(50,80, 275, 275);
        panel.add(img);

    }

    public void updateImageWithPic(JLabel image) {
        panel.remove(img);
    }

    // declare variables LocalDate firstDate and LocalDate secondDate in the class and store them in the file
    boolean dayCompleted = false;
    SwingWorker myMethod = new SwingWorker<Void, Long>() {

        @Override

        protected Void doInBackground() {

            while (true) {

                secondDate = LocalDate.now(TimeZone.getDefault().toZoneId());
                if (!firstDate.isEqual(secondDate) && focusTimer == 0 || firstDate.isEqual(secondDate) && currentFocus >= goal && !dayCompleted) {

                    publish(firstDate.until(secondDate, ChronoUnit.DAYS));

                }

            }

        }

        @Override

        protected void process(java.util.List<Long> list) {
            String[] num = customizationNum.split(",");

            Long daysPassed = list.get(list.size() - 1);
            if ((daysPassed == 1 || daysPassed == 0 && !dayCompleted) && currentFocus >= goal) { // if they met their goal

                if (days == -1) { // turns sad cat happy

                    updateImage(num);
                    jLabel1.setText("Your Cat is happy now!");


                } else if (days <= -4) { // turns dead cat into a new cat

                    num = new String[]{"12", "0", "0"};
                    updateImage(num);
                    deathMessage.setText("Here is your new cat!");

                }

                days++;
                if (daysPassed == 0) { // makes sure that you can't get two days in one

                    dayCompleted = true;
                }

                int chance = (int) (Math.random() * 100);
                if (chance == 31) { // there is a 1/100 chance that your cat randomly dies
                    days = -4;
                    num = new String[]{"-1", "0", "2"};
                    try {
                        updateImageWithPic(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/_beheaded.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    deathMessage.setText("Oh no! Your cat randomly got run over by a car!");
                }

            } else { // if they didnt meet their goal

                if (daysPassed == 1) { // if a day passed, decrease their days
                    if (days >= 0) {

                        days = -1;

                    } else {

                        days -= 1;

                    }

                } else if (daysPassed == 2 || daysPassed == 3) {

                    days = - daysPassed.intValue();

                } else if (daysPassed >= 4) {

                    days = -4;

                } else {

                }

                if (days == -1) {

                    // turn the cat sad

                } else if (days == -2) { // turns cat sick but cat might still have belly pattern
                    num[2] = "0";
                    updateImage(num);
                    deathMessage.setText("Oh no your cat got sick!");
                } else if (days == -3) { // turns cat sick but only has base coat
                    num[1] = "0"; num[2] = "0";
                    updateImage(num);
                    deathMessage.setText("Oh no your cat got sick!");
                } else if (days <= -4) { // cat dies

                    int whichDeath = (int) (Math.random() * 2);
                    if (whichDeath == 0) {
                        try {
                            updateImageWithPic(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/_beheaded.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        num = new String[]{"-1", "0", "2"};
                    } else {
                        try {
                            updateImageWithPic(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/_CAT/_SICK/_stabbed.png ")).getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        num = new String[]{"-1", "0", "1"};
                    }
                    catsKilled++;
                    catsKill.setText(Integer.toString(catsKilled));

                    String[] deathMessages = {"due to drowning", "after falling off a cliff", "due to alcohol poisoning", "after being bitten by a snake", "after being run over by a truck", "after being struck by lightning", "after being stabbed", "due to cancer", "due to tuberculosis", "due to pneumonia", "of radiation poisoning", "after being shot", "after being stung by a jellyfish", "due to an allergic reaction", "of old age"};
                    deathMessage.setText( "Oh no! Your cat died " + deathMessages[(int) (Math.random() * deathMessages.length)] + ".");

                }

            }

            if (days < 0) {

                streak.setText("0");

            } else {

                streak.setText(Integer.toString(days));

            }

            currentFocus = 0;

        }

    };


    public void rewriteFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/accounts", false));
        bw.write("0 ");
        for (User u : aList) {
            bw.write(u.username + " " + u.password + " ");
            if (u.username == null) {
                bw.write("0 ");
            } else {
                bw.write(u.dayUsed.toString() + " ");
            }
            bw.write(u.days + " " + u.customizationNum + " " + u.currentFocus + " " + u.goal + " " + u.catsKilled);
            bw.newLine();
            bw.write("0 ");
        }
        bw.flush();
    }
// put all of that in the class and put myMethod.execute(); in a button (probably login button) to start the code

    private int focusTimer;
    private Timer timer;

    public void focusing(JLabel l, int hours, int minuits, int seconds) {

        focusTimer = hours * 3600 + minuits * 60 + seconds;

        timer = new Timer(1000, new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent thing) {

                int hoursLeft = focusTimer / 3600;
                int minuitsLeft = (focusTimer - (3600 * hoursLeft)) / 60;
                int secondsLeft = focusTimer - 3600 * hoursLeft - 60 * minuitsLeft;

                l.setText((timeFormat(hoursLeft) + ":" + timeFormat(minuitsLeft) + ":" + timeFormat(secondsLeft)));
                focusTimer--;
                currentFocus++; // maybe change

                if (focusTimer == -1) {

                    timer.stop();

                }

            }

        });
        timer.start();
    }
    public static void main(String[] args) throws IOException {
        GUI g = new GUI();
    }

    public String timeFormat(int number) {

        if (Integer.toString(number).length() == 1) {

            return "0" + number;

        } else {

            return Integer.toString(number);

        }

    }
}
