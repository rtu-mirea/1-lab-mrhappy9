package fin;
import java.io.*;
public class Files{
    private File file;
    private String path;

    boolean create_file(String main_path){
        file = new File(main_path);
        try{
            boolean answer = file.createNewFile() && file.exists();
            path = answer ? file.getAbsolutePath() : "";
            return answer;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean create_folder(String path_folder){
        file = new File(path_folder);
        boolean answer = !file.exists() && file.mkdirs();
        path = answer ? file.getAbsolutePath() : "";
        return answer;
    }

    String getPath() {
        return path;
    }

    boolean isDir(){
        return file.isDirectory();
    }

    boolean del(){
        if (file.exists())
            return file.delete();
        return false;
    }

    boolean exists(){
        return file.exists();
    }

    String getName(){return file.getName();}
    String getParent(){return file.getParent();}
    long len(){return file.length();}
}