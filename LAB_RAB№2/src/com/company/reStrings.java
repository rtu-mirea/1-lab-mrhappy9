package com.company;
import java.io.*;
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
        return str.length() == 2 && str.charAt(0) == str.charAt(1);
    }

    void replace_numeric(){
        String replaced = "*";
        String check = "";

        StringBuilder str_builder = new StringBuilder(text);
        // point has added for correctly processing string
        str_builder.append('.');
        for(int index = 0; index < str_builder.length(); index++){
            if(Character.isDigit(str_builder.charAt(index))){
                check += str_builder.charAt(index);
            }
            else{
                if(is_string_consists_2num(check))
                    str_builder.replace(index-2, index, replaced);
                check = "";
            }
        }
        //point has deleted
        str_builder.deleteCharAt(str_builder.length()-1);
        text = str_builder.toString();
        System.out.println(text);
    }
}
