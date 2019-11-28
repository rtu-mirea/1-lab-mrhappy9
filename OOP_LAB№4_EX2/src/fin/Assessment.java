package fin;

import java.io.Serializable;

public class Assessment implements Serializable {
    private String num_book;
    private String cipher;
    private String discipline;
    private String data;
    private int mark;
    private String teacher;

    public Assessment(String num_book, String cipher, String discipline, String data, int mark, String teacher) {
        this.num_book = num_book.substring(0, 1).toUpperCase() + num_book.substring(1);
        this.cipher = cipher.substring(0, 1).toUpperCase() + cipher.substring(1);
        this.discipline = discipline.substring(0, 1).toUpperCase() + discipline.substring(1);
        this.data = data.substring(0, 1).toUpperCase() + data.substring(1);
        this.mark = mark;
        this.teacher = teacher.substring(0, 1).toUpperCase() + teacher.substring(1);
    }
    public Assessment() {}

    public void exchange_mark(int mark){
        this.mark = mark;
    }

    public boolean right_mark(){
        return mark == 3;
    }

    public String getNum_book() {
        return num_book;
    }

    public String getCipher() {
        return cipher;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getData() {
        return data;
    }

    public int getMark() {
        return mark;
    }

    public String getTeacher() {
        return teacher;
    }
}
