package fin;

public class User extends Request{
    private String name;
    private String login;
    private String password;

    protected User(){}

    protected User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    /*Введенная информация для выбора места и даты саммита, взятая у каждого User*/
    protected User(String country, String day_from, String day_to, boolean decision){
        super(country, day_from, day_to);
    }

    protected boolean enter(String login, String password){
        return this.login.equals(login) && this.password.equals(password);
    }

    protected String getName(){ return name; }
    protected String getLogin(){return login;}
    protected String getPassword(){return password;}
}
