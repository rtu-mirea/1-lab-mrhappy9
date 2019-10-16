package com.company;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StringProcessing str_process = new StringProcessing();
        StProccesingBuffer stBuffer = new StProccesingBuffer();
        reStrings restr = new reStrings();
        Scanner in = new Scanner(System.in);
        boolean run = true;
        while(run){
            Scanner scan = new Scanner(System.in);
            System.out.println("1. Первое задание с применением класса String");
            System.out.println("2. Второе задание с применением класса StringBuilder");
            System.out.println("3. Третье задание - регулярные выражения");
            int choice = scan.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("1. Разделить текст на абзацы");
                    System.out.println("2. В каждом абзаце первое и последнее слово записать с CAPS LOCK");
                    System.out.println("3. Определите количество слов в конкретном абзаце, имеющие сочетание 'ам' ");
                    System.out.println("4. Сформировать новую строку, содеражащую информацию о кол-ве предложений.");
                    int choice_first = scan.nextInt();
                    switch (choice_first) {
                        case 1:
                            str_process.transform_string();
                            break;
                        case 2:
                            str_process.change_register();
                            break;
                        case 3:
                            System.out.println("Введите номер абзаца: ");
                            int position = scan.nextInt();
                            str_process.search_substring(position);
                            break;
                        case 4:
                            System.out.println(str_process.create_info());
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Разделить текст на абзацы");
                    System.out.println("2. В каждом абзаце первое и последнее слово записать с CAPS LOCK");
                    System.out.println("3. Добавить в текст информацию, отображающую количество предложений в тексте.");
                    int choice_second = scan.nextInt();
                    switch (choice_second) {
                        case 1:
                            stBuffer.transform_string();
                            break;
                        case 2:
                            stBuffer.change_register();
                            break;
                        case 3:
                            stBuffer.append_info();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. Определить, что строка состоит из двух одинаковых цифр.");
                    System.out.println("2. Ввести текст и замените все числа, удовлетворяющие условию 1, на '*'");
                    int choice_third = scan.nextInt();
                    switch (choice_third) {
                        case 1:
                            System.out.println("Введите строку: ");
                            String str = in.nextLine();
                            String result = restr.is_string_consists_2num(str) ? "Введеная строка состоит из 2 одинаковых цифр."
                                    : "Введеная строка НЕ состоит из 2 одинаковых цифр.";
                            System.out.println(result);
                            break;
                        case 2:
                            System.out.println("Введите текст: ");
                            String text = in.nextLine();
                            reStrings reStr = new reStrings(text);
                            reStr.replace_numeric();
                            break;
                    }
                    break;
            }
        }
    }
}