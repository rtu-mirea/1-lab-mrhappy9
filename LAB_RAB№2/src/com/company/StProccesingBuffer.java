package com.company;
import java.io.*;
public class StProccesingBuffer {
    private String text = "";
    private int quantity_paragraph = 0;
    StProccesingBuffer(){
        try(FileReader reader = new FileReader("info.txt")){
            int txt;
            while((int)(txt = reader.read()) != -1){
                text += (char)txt;
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
    StProccesingBuffer(String text){
        this.text = text;
    }
    void transform_string(){
        StringBuilder super_str = new StringBuilder(text);
        for(int index = 0; index < super_str.length(); index++){
            if((text.charAt(index) == '.' || text.charAt(index) == '!'
                || text.charAt(index) == '?') && index+1 < super_str.length()){
                    super_str.setCharAt(index+1 , '\n');
                    index++;
            }
        }
        super_str.insert(super_str.length(), '\n');
        text = super_str.toString();
        System.out.println(text);
    }
    void change_register(){
        StringBuilder super_string = new StringBuilder(text);

        // first word has raised up previously
        int finish_for_first_word = 0;
        for(int i = 0; is_right_char(super_string.charAt(i)); i++)
            finish_for_first_word = i;
        super_string.replace(0, finish_for_first_word+1, text.substring(0, finish_for_first_word+1).toUpperCase());


        int occurrences_point = 0;
        while(true){

            //raising last sentence's word
            int main_char = super_string.indexOf("\n", occurrences_point); // first occurrence
            int last_word = 0, index;
            for(index = main_char-2; is_right_char(super_string.charAt(index)); index--){

                //finding where the last word is starting
                last_word = index;
            }
            super_string.replace(last_word, main_char-1, text.substring(last_word, main_char-1).toUpperCase());

            //raising first sentence's word
            int first_word = 0;
            if (main_char + 1 < super_string.length()){
                for(int extra_index = main_char+1; is_right_char(super_string.charAt(extra_index)); extra_index++) {
                    first_word = extra_index;
                }
                super_string.replace(main_char + 1, first_word +1, text.substring(main_char + 1, first_word + 1).toUpperCase());
            }

            // raise the occurrence point
            occurrences_point = main_char + 1;

            if (main_char + 1 >= super_string.length())
                break;
        }
        text = super_string.toString();
        System.out.println(text);
    }
    void append_info(){
        StringBuilder super_string = new StringBuilder(text);
        int counter = 0;
        for(int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '!' || text.charAt(i) == '.' || text.charAt(i) == '?')
                counter++;
        }
        super_string.append("Кол-во предложений в тексте = ").append(counter);
        text = super_string.toString();
        System.out.println(text);
    }
    boolean is_right_char(char character){
        return ((int)character >= 1040 && (int)character <= 1103) || (int)character == 1105 || (int)character == 1025
                || character == '-' || character == '"';
    }
}
