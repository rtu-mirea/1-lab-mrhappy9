package fin;

import java.io.*;
import java.util.LinkedList;

public class Manager {
    public Manager(){}

    public boolean writeData(String path, LinkedList<Assessment> person){
        try(DataOutputStream write = new DataOutputStream(new FileOutputStream(path))){
            for (Assessment assessment : person) {
                write.writeUTF(assessment.getNum_book());
                write.writeUTF(assessment.getCipher());
                write.writeUTF(assessment.getDiscipline());
                write.writeUTF(assessment.getData());
                write.writeInt(assessment.getMark());
                write.writeUTF(assessment.getTeacher());
            }
            return true;
        }
        catch (IOException exc){
            System.out.println(exc.getMessage());
            return false;
        }
    }

    public boolean readData(String path, LinkedList<Assessment> whole_person, int len){
        try(DataInputStream read = new DataInputStream(new FileInputStream(path))){
            for(int i = 0; i < len; i++){
                Assessment pers = new Assessment(read.readUTF(), read.readUTF(), read.readUTF(),
                        read.readUTF(), read.readInt(), read.readUTF());
                whole_person.add(pers);
            }
            return true;
        }
        catch (IOException exc){
            System.out.println(exc.getMessage());
        }
        return false;
    }

    public boolean create_file_Access(String path, LinkedList<Assessment> bad_students){
        try(RandomAccessFile raf = new RandomAccessFile("raf_file.dat", "rw")){
            if(bad_students.size() > 0) {
                for (Assessment bad_student : bad_students) {
                    raf.writeUTF(bad_student.getNum_book());
                    raf.writeUTF(bad_student.getCipher());
                    raf.writeUTF(bad_student.getDiscipline());
                    raf.writeUTF(bad_student.getData());
                    raf.writeInt(bad_student.getMark());
                    raf.writeUTF(bad_student.getTeacher());
                }
                return true;
            }
        }
        catch (IOException exc){
            System.out.println(exc.getMessage());
        }
        return false;
    }
}
