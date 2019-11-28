package fin;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static String first_file = "T1.txt";
    static String second_file = "T2.txt";

    static String third_file = "A.txt";
    static String fourth_file = "B.txt";

    static String last_file = "last_txtFile.txt";

    static char first = 'm';
    static char second = 'e';
    static char third = 's';
    static char fourth = 'k';

    private static List<Character> chars = new ArrayList<>();

    public static void main(String[] args) {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }

        Scanner scan = new Scanner(System.in);
	    int stop = 1;
	    while(stop == 1) {
            System.out.println("1. Чтение из одного текстового файла и запись в другой");
            System.out.println("2. Применение буферизированных потоков для чтения и записи текстовых файлов");
            System.out.println("3. Настройка кодировки символов для входного и выходного потоков");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader
                            (new FileInputStream(first_file), "Cp1251"))) {
                        int symbol;
                        while ((symbol = reader.read()) != -1)
                            chars.add((char) symbol);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("Файл считался: " + chars.toString());
                    System.out.println("\nЗаписывание считанных данных во второй файл началось...");
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(second_file), "Cp1251"))) {
                        for (char ch : chars) {
                            writer.write(ch);
                        }
                        System.out.println("Считанные данные успешно переписаны во второй файл");

                        try {
                            desktop.open(new File(second_file));
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(third_file), "Cp1251"), 512);
                        for (int i = 0; i < 512; i++) {
                            if (i < 128)
                                bufferedWriter.write(first);
                            else if (i < 128 * 2)
                                bufferedWriter.write(second);
                            else if (i < 128 * 3)
                                bufferedWriter.write(third);
                            else
                                bufferedWriter.write(fourth);
                        }
                        bufferedWriter.flush();
                        bufferedWriter.close();

                        BufferedReader inb = new BufferedReader(new InputStreamReader(new FileInputStream(third_file), "Cp1251"), 128);
                        BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fourth_file), "Cp1251"), 128);
                        int ok = 0;
                        while (ok != 4) {
                            char[] buf = new char[128];
                            for (int i = 0; i < buf.length; i++) {
                                buf[i] = (char) inb.read();
                            }
                            for (char c : buf) {
                                outb.write(c);
                                outb.flush();
                            }
                            ok++;
                        }
                        outb.close();
                    } catch (IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                case 3:
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(last_file), "UTF-8"));
                        System.out.println(Charset.defaultCharset().name());
                        String out_put_data = "";
                        int symbol;
                        while ((symbol = reader.read()) != -1) {
                            out_put_data += (char) symbol;
                        }
                        System.out.println(out_put_data);
                    } catch (IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;
                default:
                    System.out.println("Выход из приложения");
                    stop = 0;
            }
        }
    }
}
