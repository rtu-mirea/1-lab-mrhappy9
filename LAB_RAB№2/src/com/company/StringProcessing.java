package com.company;
import java.io.*;
public class StringProcessing {
    private String text = "";
    private int quantity_paragraph = 0;
    StringProcessing() {
        try (FileReader reader = new FileReader("info.txt.")) {
            int txt;
            while ((txt = reader.read()) != -1) {
                text += (char) txt;
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    StringProcessing(String text) {
        this.text = text;
    }

    void transform_string() {
        String new_text = "";
        boolean flag = true;
        for (int index = 0; index < text.length(); index++) {
            new_text += text.charAt(index);
            // If there are found in special chars, flag will be changed.
            if ((text.charAt(index) == '!' || text.charAt(index) == '.' || text.charAt(index) == '?')) {
                flag = false;
            }
            // Add '\n' for creating a new paragraph
            if (!flag) {
                new_text += '\n';
                quantity_paragraph++;
                flag = true;
                //If ' ' is located after the special chars, get around ' '
                if (index + 1 < text.length() && text.charAt(index + 1) == ' ')
                    index++;
            }
        }
        text = new_text;
        System.out.println(text);
    }

    void change_register() {
        int index_first_word, finish_exception = 0;
        for (index_first_word = 0; is_right_char(text.charAt(index_first_word)); index_first_word++){
            finish_exception = index_first_word;
        }
        text = text.substring(0, finish_exception+1).toUpperCase() + text.substring(finish_exception+1);
        for (int index = index_first_word; index < text.length(); index++) {
            int first_start = 0, second_start, first_finish, second_finish = 0;
            // last word's register is changed(grow up with CAPS LOCK)
            if (text.charAt(index) == '\n') {
                first_finish = index - 1; //right board
                for (int i = first_finish; text.charAt(i) != ' '; i--) {
                    first_start = i;
                }
                text = text.substring(0, first_start) + text.substring(first_start, first_finish).toUpperCase() +
                        text.substring(first_finish);
                // first word's register is changed
                if (index + 1 < text.length()) {
                    second_start = index + 1;
                    for (int i = second_start; is_right_char(text.charAt(i)); i++){
                        second_finish = i;
                    }
                    text = text.substring(0, second_start) + text.substring(second_start, second_finish+1).toUpperCase() +
                            text.substring(second_finish+1);
                }
            }
        }
        System.out.println(text);
    }

    void search_substring(int position){
        int count = 0;                              // quantity substring which will be the same with 'ам'
        int under_count = 0;
        if (position <= quantity_paragraph){
            int start_sub_string = 0;               // for searching into the substring
            int start_search;
            int stop = 0;                           // for finding the end of the essential substring
            for(start_search = 0; stop != position; start_search++){
                if(text.charAt(start_search) == '\n'){
                    stop++;
                    if(stop < position)
                        start_sub_string = start_search+1;
                }
            }
            // searching into the chosen substring
            for(int index = start_sub_string; text.charAt(index) != '.' && text.charAt(index) != '!' &&
                    text.charAt(index) != '?'; index++){
                if(text.charAt(index) == 'а' && text.charAt(index+1) == 'м')
                    under_count = 1;
                else{
                    if(under_count != 0 && text.charAt(index) == ' '){ // substring 'am' is founded and the whole word, which contains one, have just read.
                        count++;
                        under_count = 0; //refresh counter
                    }
                }
            }
            System.out.printf("%s %d %s %d", "Количество слов с подстрокой 'ам' в абзаце", position, "равно", count);
            System.out.println();
        }
        else
            System.out.println("Введенного абцаза не существует.");
    }

    public String create_info(){
        int counter = 0;
        for(int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '!' || text.charAt(i) == '.' || text.charAt(i) == '?')
                counter++;
        }
        return "Кол-во предложений в тексте = " + counter;
    }
    private boolean is_right_char(char character){
        return ((int)character >= 1040 && (int)character <= 1103) || (int)character == 1105 || (int)character == 1025
                || character == '-' || character == '"';
    }
}
