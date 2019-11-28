package fin;

import java.io.*;
import java.util.LinkedList;

public class ClassObjectFile extends NoteBook{
    private String path;

    //создание пустого конструктора в супер-классе
    public ClassObjectFile(String path){
        super(path);
        this.path = path;
    }

    //заполнение по умолчанию, как в классе ClassTextFile
    public ClassObjectFile(String path, String classic){
        super();
        this.path = path;
    }

    public ClassObjectFile(String path, String lastname, String name, String phone, String birthday, String address){
        super(lastname, name, phone, birthday, address);
        this.path = path;
    }

    public void write_data(LinkedList<ClassObjectFile> object){
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path));
            writer.writeObject(object);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public LinkedList<ClassObjectFile> read_data(){
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
            return (LinkedList<ClassObjectFile>)reader.readObject();
        }
        catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
