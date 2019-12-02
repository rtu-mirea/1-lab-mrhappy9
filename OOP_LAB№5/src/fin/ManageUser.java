package fin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUser extends User{
    private String ADMIN = "admin";
    private ArrayList<User> user = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();

    public void createUser(String name, String login, String password){
        user.add(new User(name, login, password));
    }

    /*Создание объекта класса Request через наследника(для заполнения выбора места проведения саммита*/
    public void createRequestForUser(String country, String day_from, String day_to, boolean decision){
        requests.add(new User(country, day_from, day_to, decision));
    }

    /*Запуск окон GUI*/
    public void launch_Gui(boolean registration, boolean apply_data, boolean admin) {
        if(!registration && !apply_data && !admin) {
            GuiUser gui = new GuiUser();
        }
        else if(registration && !apply_data && !admin) {
            GuiUser gui = new GuiUser(true);
        }
        else if(!registration && apply_data && !admin){
            GuiUser gui = new GuiUser(true, true);
        }
        else if(!registration && !apply_data && admin){
            GuiUser gui = new GuiUser(true, true, true);
        }
    }

    /*Зарегистрирован ли введенный пользователь?*/
    public boolean check_existUser(String name, String login, String password){
        for(User us: user){
            if(us.getName().equals(name) &&
               us.getLogin().equals(login) &&
               us.getPassword().equals(password)) {
                    return true;
            }
        }
        return false;
    }
    /*поиск страны, которую выбрало большинство*/
    public String define_convenientCountry(){
        String convenient_country = "";
        int max = 0;
        int repeat = 0;
        ArrayList<String> countries = new ArrayList<>();

        for(Request req: requests){
            countries.add(req.getCountry());
        }

        for(String cont: countries){
            repeat = Collections.frequency(countries, cont);
            if(repeat > max && repeat != 1){
                convenient_country = cont;
                max = repeat;
            }
        }

        if(!convenient_country.equals(""))
            return convenient_country;
        return "";
    }

    //поиск дня старта для саммита, который выбрало большинство
    public String define_convenient_dayFrom(){
        String convenient_dayFrom = "";
        int max = 0;
        int repeat = 0;
        ArrayList<String> daysFrom = new ArrayList<>();
        for(Request req: requests){
            daysFrom.add(req.getDay_from());
        }
        for(String day: daysFrom){
            repeat = Collections.frequency(daysFrom, day);
            if(repeat > max && repeat != 1){
                convenient_dayFrom = day;
                max = repeat;
            }
        }
        if(!convenient_dayFrom.equals("")){
            return convenient_dayFrom;
        }
        return "";
    }

    //поиск дня окончания для саммита, который выбрало большинство
    public String define_convenient_dayTo(){
        String convenient_dayTo = "";
        int max = 0;
        int repeat = 0;
        ArrayList<String> daysTo = new ArrayList<>();
        for(Request req: requests){
            daysTo.add(req.getDay_to());
        }
        for(String day: daysTo){
            repeat = Collections.frequency(daysTo, day);
            if(repeat > max && repeat != 1){
                convenient_dayTo = day;
                max = repeat;
            }
        }
        if(!convenient_dayTo.equals("")){
            return convenient_dayTo;
        }
        return "";
    }

    /*--------------------------------------------Графический интерфейс----------------------------------------------*/
    public class GuiUser extends JFrame {
        /*Элементы для входа*/
        private JLabel label_name = new JLabel("Name: ");
        private JTextField name = new JTextField(25);
        private JLabel label_login = new JLabel("Login: ");
        private JTextField login = new JTextField(25);
        private JLabel label_password = new JLabel("password");
        private JPasswordField password = new JPasswordField(23);
        private JButton signIn = new JButton("Sign In");
        private JButton goto_signUp = new JButton("Sign Up");
        private JButton admin = new JButton("Enter as Admin");

        /*Элементы для регистрации*/
        private JLabel label_country = new JLabel("Country: ");
        private JTextField country = new JTextField(25);
        private JLabel label_day_from = new JLabel("First Day: ");
        private JTextField day_from = new JTextField(25);
        private JLabel label_day_to = new JLabel("Last Day");
        private JTextField day_to = new JTextField(23);
        private JButton apply = new JButton("Apply");

        /*Элементы для админа*/
        private JTextArea result = new JTextArea();
        private JButton summary = new JButton("Summary");
        private JButton go_back = new JButton("Main Window");

        /*Стартовая страница*/
        public GuiUser() {
            super("Start page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            name.setHorizontalAlignment(JTextField.LEFT);
            login.setHorizontalAlignment(JTextField.LEFT);
            password.setHorizontalAlignment(JTextField.LEFT);

            name.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            login.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));

            JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
            contents.add(label_name);
            contents.add(name);
            contents.add(label_login);
            contents.add(login);
            contents.add(label_password);
            contents.add(password);
            contents.add(signIn);
            contents.add(goto_signUp);
            contents.add(admin);

            ActionListener open_new_win = new OpenRegistrationWindow();
            goto_signUp.addActionListener(open_new_win);

            ActionListener entrance = new OpenEntry();
            signIn.addActionListener(entrance);

            ActionListener openadmin = new OpenAdminWindow();
            admin.addActionListener(openadmin);

            setSize(370, 160);
            setContentPane(contents);
            setVisible(true);
        }

        /*Страница регистрации*/
        public GuiUser(boolean decision) {
            super("Sign Up");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            name.setHorizontalAlignment(JTextField.LEFT);
            login.setHorizontalAlignment(JTextField.LEFT);
            password.setHorizontalAlignment(JTextField.LEFT);

            name.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            login.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));

            JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
            contents.add(label_name);
            contents.add(name);
            contents.add(label_login);
            contents.add(login);
            contents.add(label_password);
            contents.add(password);
            contents.add(signIn);

            ActionListener input_data_registration = new Registration();
            signIn.addActionListener(input_data_registration);

            setSize(370, 160);
            setContentPane(contents);
            setVisible(true);
        }

        /*Страница ввода данных*/
        public GuiUser(boolean decision, boolean decision2) {
            super("Data Window");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            country.setHorizontalAlignment(JTextField.LEFT);
            day_from.setHorizontalAlignment(JTextField.LEFT);
            day_to.setHorizontalAlignment(JTextField.LEFT);

            country.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            day_from.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            day_to.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));

            JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
            contents.add(label_country);
            contents.add(country);
            contents.add(label_day_from);
            contents.add(day_from);
            contents.add(label_day_to);
            contents.add(day_to);
            contents.add(apply);

            ActionListener written_data = new Written_Data();
            apply.addActionListener(written_data);

            setSize(370, 160);
            setContentPane(contents);
            setVisible(true);
        }

        /*Подсчет данных администратором*/
        public GuiUser(boolean decision1, boolean decision2, boolean decision3) {
            super("Admin");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            summary.setHorizontalAlignment(JTextField.LEFT);
            go_back.setHorizontalAlignment(JTextField.LEFT);

            result.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            summary.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            go_back.setFont(new Font("TimesNewRoman", Font.PLAIN, 14));

            JPanel contentsAdmin = new JPanel(new FlowLayout(FlowLayout.LEFT));
            contentsAdmin.add(result);
            contentsAdmin.add(summary);
            contentsAdmin.add(go_back);

            ActionListener go_into_main_window = new intoMainWindow();
            go_back.addActionListener(go_into_main_window);

            ActionListener getResult = new Admin();
            summary.addActionListener(getResult);

            setSize(370, 160);
            setContentPane(contentsAdmin);
            setVisible(true);
        }

        /*Открытие окна регистрации*/
        public class OpenRegistrationWindow implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                launch_Gui(true, false, false);
            }
        }

        /*Регистрация и возвращение к основному окну*/
        public class Registration implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (!name.getText().equals("") &&
                        !login.getText().equals("") &&
                        !password.getText().equals("")) {
                    createUser(name.getText(), login.getText(), password.getText());
                    /*После регистрации -> открытие основного окна*/
                    setVisible(false);
                    launch_Gui(false, false, false);
                }
            }
        }

        /*Выбор места саммита*/
        public class OpenEntry implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (check_existUser(name.getText(), login.getText(), password.getText())) {
                    setVisible(false);
                    launch_Gui(false, true, false);
                }
                else {
                    System.out.println("The User doesn't exist");
                    name.setText("");
                    login.setText("");
                    password.setText("");
                }
            }
        }

        /*Ввод данных*/
        public class Written_Data implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(!country.getText().equals("") &&
                   !day_from.getText().equals("") &&
                   !day_to.getText().equals("")){
                        Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)$");
                        Matcher matcher_from = pattern.matcher(day_from.getText());
                        Matcher matcher_to = pattern.matcher(day_to.getText());
                        if(matcher_from.find() && matcher_to.find()) {
                            createRequestForUser(country.getText(), day_from.getText(), day_to.getText(), true);
                            /*После ввода данных открытие основного окна*/
                            setVisible(false);
                            launch_Gui(false, false, false);
                    }
                    else{
                        country.setText("");
                        day_from.setText("");
                        day_to.setText("");
                    }
                }
            }
        }

        /*Возвращение к начальному экрану*/
        public class intoMainWindow implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                launch_Gui(false,false,false);
            }
        }

        /*Открытие окна администратором*/
        public class OpenAdminWindow implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(name.getText().equals(ADMIN) &&
                   login.getText().equals(ADMIN) &&
                   password.getText().equals(ADMIN)) {
                    setVisible(false);
                    launch_Gui(false, false, true);
                }
                System.out.println("Access Denied");
            }
        }

        /*Подведение итогов администратором*/
        public class Admin implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(!define_convenientCountry().equals("") &&
                   !define_convenient_dayFrom().equals("") &&
                   !define_convenient_dayTo().equals("")){
                        String country = "Страна проведения: " + define_convenientCountry() + "\n";
                        String dayFrom = "День старта: " + define_convenient_dayFrom() + "\n";
                        String dayTo = "День конца: " + define_convenient_dayTo() + "\n";
                        result.setText(country + dayFrom + dayTo);
                }
                else{
                    result.setText("Нет подходящего места для проведения саммита");
                }
            }
        }
    }
}
