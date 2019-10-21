package com.company;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class reStrings {
    private String text = "";

    reStrings(){
        try(FileReader reader = new FileReader("object.txt")) {
            int txt;
            while((txt = reader.read()) != -1){
                text += (char)txt;
            }
            System.out.println(text);
        }catch (IOException ex){
            System.out.println(ex.toString());
        }
    }

    reStrings(String text){
        this.text = text;
    }
    // define- Does string consist of 2 the same digits
    boolean is_string_consists_2num(String str){
        Pattern pattern = Pattern.compile("^[0-9]{2}$");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            return true;
        }
        return false;
    }

    void replace_numeric(){
        String new_text = text.replaceAll("[0-9]{2}", "*");
        text = new_text;
        System.out.println(text);
    }
}
