package fin;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static private boolean check_Data(String string) { //валидация даты
        Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)$");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    static private String default_string = "";

    static private String object_file = "object_file.dat";

    static private String extra_spaces = "---------------------";

    public static void main(String[] args) {
        LinkedList<ClassTextFile> collection_class_text = new LinkedList<>();

        LinkedList<ClassObjectFile> collection_class_object = new LinkedList<>();
        LinkedList<ClassObjectFile> collection_class_object_from_file = new LinkedList<>();

        File file = new File("data.txt");

        Scanner mainscan = new Scanner(System.in);

        int run = 1;
        while (run == 1) {
            System.out.println("1. Создать объект класса NoteBook ручным вводом или с помощью текстового файла");
            System.out.println("2. Вывести данные созданных объектов через класс ClassTextFile");
            System.out.println("3. Запись и чтение из файла объектов класса NoteBook");
            String lastname;
            String name;
            String phone;
            String birthday;
            String address;
            int choice = mainscan.nextInt();
            switch (choice) {
                case 1:
                    try {
                        Scanner scanner = new Scanner(file, "Cp1251");
                        System.out.println("1. Создать ручным вводом");
                        System.out.println("2. Автоматически с файла");
                        int created = mainscan.nextInt();
                        if (created == 1) {
                            System.out.println("Введите фамилию");
                            lastname = mainscan.next();
                            System.out.println("Введите имя");
                            name = mainscan.next();
                            System.out.println("Введите номер телефона");
                            phone = mainscan.next();

                            while (true) {
                                System.out.println("Введите дату в формате ДД.ММ.ГГГГ");
                                birthday = mainscan.next();
                                if (check_Data(birthday))
                                    break;
                                else
                                    System.out.println("Введите корректную дату");
                            }

                            System.out.println("Введите адресс");
                            address = mainscan.next();
                            ClassTextFile object = new ClassTextFile("data.txt", file);
                            object.handwriteData(lastname, name, phone, birthday, address);
                            collection_class_text.add(object);
                        } else if (created == 2) {
                            ClassTextFile object = new ClassTextFile("data.txt", file, scanner);
                            collection_class_text.add(object);
                        } else {
                            System.out.println("Объект будет создан по умолчанию");
                            ClassTextFile object = new ClassTextFile();
                            collection_class_text.add(object);
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    if(collection_class_text.size() > 0) {
                        for (ClassTextFile per : collection_class_text) {
                            System.out.println(per.getLastname());
                            System.out.println(per.getName());
                            System.out.println(per.getPhone());
                            System.out.println(per.getBirthday());
                            System.out.println(per.getAddress());
                            System.out.println(extra_spaces);
                        }
                    }
                    else
                        System.out.println("Вы не ввели никаких данных");
                    break;
                case 3:
                    System.out.printf("1. Записать данные объектов в %s с помощью ObjectOutputStream\n", object_file);
                    System.out.printf("2. Вывод данных объектов из %s с помощью ObjectInputSteam\n", object_file);
                    ClassObjectFile classObjectFile;
                    int ch = mainscan.nextInt();
                    if(ch == 1){
                        int runner = 1;
                        while (runner == 1) {
                            System.out.println("1. Ввести данные вручную для дальнейше записи в файл");
                            System.out.println("2. Заполнить по умолчанию для дальнейшей записи в файл");
                            System.out.println("3. Остановить заполнение");
                            int answer = mainscan.nextInt();
                            switch (answer) {
                                case 1:
                                    System.out.println("Введите фамилию");
                                    lastname = mainscan.next();
                                    System.out.println("Введите имя");
                                    name = mainscan.next();
                                    System.out.println("Введите номер телефона");
                                    phone = mainscan.next();

                                    while (true) {
                                        System.out.println("Введите дату в формате ДД.ММ.ГГГГ");
                                        birthday = mainscan.next();
                                        if (check_Data(birthday))
                                            break;
                                        else
                                            System.out.println("Введите корректную дату");
                                    }

                                    System.out.println("Введите адресс");
                                    address = mainscan.next();
                                    classObjectFile = new ClassObjectFile(object_file, lastname, name, phone, birthday, address);
                                    collection_class_object.add(classObjectFile);
                                    classObjectFile.write_data(collection_class_object);
                                case 2:
                                    classObjectFile = new ClassObjectFile(object_file, default_string);
                                    collection_class_object.add(classObjectFile);
                                    classObjectFile.write_data(collection_class_object);
                                    break;
                                default:
                                    runner = 4;
                            }
                        }
                    }
                    else{
                        File files = new File(object_file);
                        if(files.exists()) {
                            classObjectFile = new ClassObjectFile(object_file);
                            collection_class_object_from_file = classObjectFile.read_data();
                            for (ClassObjectFile obj : collection_class_object_from_file) {
                                System.out.println(obj.getLastname());
                                System.out.println(obj.getName());
                                System.out.println(obj.getPhone());
                                System.out.println(obj.getBirthday());
                                System.out.println(obj.getAddress());
                                System.out.println(extra_spaces);
                            }
                        }
                        else
                            System.out.println("Файл еще не создан. Введите данные для его создания");
                    }
                    break;
                default:
                    System.out.println("Выход");
                    run = 0;
            }
        }
    }
}
