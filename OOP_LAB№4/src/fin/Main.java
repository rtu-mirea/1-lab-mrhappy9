package fin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String first_way = "MyFile1.txt";
    static String second_way = "D:\\MyFile2.txt";
    static String third_way = "D:\\newfolder\\MyFile.txt";
    static String folder_way = "Первая\\Вторая\\Третья";
    static String new_folder_way = "D:\\OOP_LAB№4\\NEW_FOLDER";
    static String created = "Файл создан ";
    static String createdff = "Папка создана ";
    static String mistake = "Ошибка при создании файла ";
    static String mistakeff = "Ошибка при создании папки ";
    static String seacrh_file = "MyFile1.txt";
    static String deleted = "Удаление ";

    static String file_way = "D:\\OOP_LAB№4";
    static String extra_file_way = "D:\\OOP_LAB№4\\try.txt";

    public static void main(String[] args) throws IOException {
        Files first_file = new Files();
        Files second_file = new Files();
        Files third_file = new Files();
        Files folder = new Files();
        Files new_folder = new Files();

        File file = new File(file_way);
        File extra_file = new File(extra_file_way);
        boolean run = true;
        while (run) {
            String answer;
            Scanner in = new Scanner(System.in);
            System.out.println("1. Упражнение 1");
            System.out.println("2. Упражнение 2");
            System.out.println("3. Упражнение 3");

            int exercises = in.nextInt();
            switch (exercises) {
                case 1:
                    System.out.println("1. Создать файл в папке приложения с именем MyFile1.txt");
                    System.out.println("2. Создать файл с именем MyFile2.txt в корне определенного диска");
                    System.out.println("3. Создать файл с именем MyFile3.txt в папке Имя диска\\\\Имя папки\\\\Имя файла");
                    System.out.println("4. Создать папку третьего уровня, например, Первая\\\\Вторая\\\\Третья");

                    int choice_second = in.nextInt();
                    switch (choice_second) {
                        case 1:
                            answer = first_file.create_file(first_way) ? created + first_file.getPath() : mistake;
                            System.out.println(answer);
                            break;
                        case 2:
                            answer = second_file.create_file(second_way) ? created + second_file.getPath() : mistake;
                            System.out.println(answer);
                            break;
                        case 3:
                            answer = third_file.create_file(third_way) ? created + third_file.getPath() : mistake;
                            System.out.println(answer);
                            break;
                        case 4:
                            answer = folder.create_folder(folder_way) ? createdff + folder.getPath() : mistakeff;
                            System.out.println(answer);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Проверить, что вызывающий объект содержит имя файла, а не папки и отобразить имя файла, вызывающего объекта и его родительскую папку");
                    System.out.println("2. Проверить, что вызывающий объект содержит имя папки, а не файла и отобразить имя файла, вызывающего объекта");
                    System.out.println("3. Проверить, что в папке приложения существует файл с именем MyFile1.txt");
                    System.out.println("4. Отобразить полный путь к файлу или папке объекта");
                    System.out.println("5. Отобразить размер файла или папки объекта, указать единицу измерения. Прокомментировать вид файла – папка или файл");

                    int choice_third = in.nextInt();
                    switch (choice_third) {
                        case 1:
                            String ans = extra_file.isFile() ? extra_file.getName() : mistakeff;
                            if (ans.equals(mistakeff))
                                extra_file.createNewFile();
                            System.out.println(extra_file.getName());
                            break;
                        case 2:
                            String answ = file.isDirectory() ? file.getName() : mistakeff;
                            System.out.println(answ);
                            break;
                        case 3:
                            boolean flag = false;
                            String[] recording = file.list();
                            for (String item : recording) {
                                if (item.equals(seacrh_file)) {
                                    flag = true;
                                    System.out.println(created + seacrh_file);
                                    break;
                                }
                            }
                            if (!flag)
                                System.out.println(mistake);
                            break;
                        case 4:
                            System.out.println(first_file.getPath());
                            break;
                        case 5:
                            long size = file.length();
                            String req = file.isDirectory() ? "Папка " : "Файл ";
                            System.out.println(req + size + " б");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. Добавить в папку приложения еще одну папку");
                    System.out.println("2. Сформировать массив файлов, находящихся в папке приложения, используя метод list(). Отобразить содержимое массива");
                    System.out.println("3. Сформировать массив файлов, находящихся в папке приложения,\n" +
                            "используя метод listFiles( ). Отобразить содержимое массива.\n" +
                            "Определить количество папок, содержащихся в файле приложения.");
                    System.out.println("4. Удалить папки и файлы созданные во всех трех упражнениях");

                    int third_choice = in.nextInt();
                    switch (third_choice) {
                        case 1:
                            new_folder.create_folder(new_folder_way);
                            System.out.println(createdff + new_folder.getPath());
                            break;
                        case 2:
                            String[] files_arrays = file.list();
                            StringBuilder content = new StringBuilder();
                            for (String item : files_arrays)
                                content.append(item).append(", ");
                            content.delete(content.length() - 2, content.length() - 1);
                            System.out.println(content);
                            break;
                        case 3:
                            File[] files_array = file.listFiles();
                            int quantity_files = files_array.length - 1;
                            StringBuilder second_content = new StringBuilder();
                            for (File item : files_array)
                                second_content.append(item).append(", ");
                            second_content.delete(second_content.length() - 2, second_content.length() - 1);
                            System.out.println("Кол-во папок " + quantity_files);
                            System.out.println(second_content);
                            break;
                        case 4:
                            try {
                                if (first_file.exists() && first_file.del())
                                    System.out.println(deleted + first_way);
                                if (second_file.exists() && second_file.del())
                                    System.out.println(deleted + second_way);
                                if (third_file.exists() && third_file.del())
                                    System.out.println(deleted + third_way);
                                if (folder.exists() && folder.del())
                                    System.out.println(deleted + folder_way);
                                if (new_folder.exists() && new_folder.del())
                                    System.out.println(deleted + new_folder_way);
                                if (extra_file.exists() && extra_file.delete())
                                    System.out.println(deleted + extra_file_way);
                            } catch (Exception e) {
                                System.out.println("Создайте все файлы перед удалением, чтобы избежать ошибки " + e.toString());
                            }
                            break;
                    }
                    break;
                    }
        }
    }
}