package fin;

import java.io.Serializable;

public class NoteBook implements Serializable {
    private String lastname;
    private String name;
    private String phone;
    private String birthday;
    private String address;

    protected NoteBook(String lastname, String name, String phone, String birthday, String address){
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
    }

    public NoteBook() {
        this.lastname = "Хорошев";
        this.name = "Дмитрий";
        this.phone = "88005559565";
        this.birthday = "27.07.1997";
        this.address = "Москва";
    }

    public NoteBook(String smth){}

    public String getLastname() {
        return lastname;
    }

    protected void setLastname(String lastname) {
        this.lastname = lastname;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getPhone() {
        return phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    protected String getBirthday() {
        return birthday;
    }

    protected void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    protected String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }
}
