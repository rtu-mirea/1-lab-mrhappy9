package fin;

import java.io.File;
import java.util.Scanner;

public class ClassTextFile extends NoteBook{
    private String path;

    public ClassTextFile(String path, File file){
        this.path = path;
        if(file.exists())
            System.out.println("Файл обновился");
        else
            System.out.println("Файл не создался");
    }

    //ввод данных из файла
    public ClassTextFile(String path, File file, Scanner scan){
        super(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
        this.path = path;
        if(file.exists())
            System.out.println("Файл обновился");
        else
            System.out.println("Файл не создался");
    }

    public ClassTextFile(){
        super();
        System.out.println("Объект класса создан по умолчанию");
    }

    //Ручной ввод
    public void handwriteData(String lastname, String name, String phone, String birthday, String address){
        setLastname(lastname);
        setName(name);
        setPhone(phone);
        setBirthday(birthday);
        setAddress(address);
    }
}
