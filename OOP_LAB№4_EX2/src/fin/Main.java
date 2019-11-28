package fin;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static private boolean check_Data(String string){ //валидация даты
        Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)$");
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()){
            return true;
        }
        return false;
    }

    static private boolean check_Mark(int mark){
        if(1 < mark && mark < 6) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите название для файла: ");
        String path = scan.next();

        Manager manager = new Manager();

        LinkedList<Assessment> person = new LinkedList<>();

        LinkedList<Assessment> person_with_bad_mark = new LinkedList<>();//информация о троечниках
        LinkedList<Assessment> whole_list = new LinkedList<>();//полная информация

        int ok = 2;
        while(ok==2) {
            System.out.println("1. Создать объект класса Assessment");
            System.out.println("2. Создать файл с информацией о созданных объктах");
            System.out.println("3. Вывести файл с информацией о созданных объектах");
            System.out.println("4. Заменить оценку у объекта по номеру книжки");
            System.out.println("5. Вернуть оценку студента");
            System.out.println("6. Получены ли оценки в одну дату и по одной дисциплине двумя студентами");
            System.out.println("7. Записать данные о троечниках в файл с помощью RandomAccessFile");

            String num_book;
            String cipher;
            String discipline;
            String data = "";
            int mark = 0;
            String teacher;

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите номер книжки ");
                    num_book = scan.next();
                    System.out.println("Введите номер шифра");
                    cipher = scan.next();
                    System.out.println("Введите название дисциплины");
                    discipline = scan.next();

                    while (true) {
                        System.out.println("Введите дату в формате ДД.ММ.ГГГГ");
                        data = scan.next();
                        if (check_Data(data))
                            break;
                        else
                            System.out.println("Введите корректную дату");
                    }

                    while (true) {
                        System.out.println("Введите оценку (5-ти бальная сис-ма)");
                        try {
                            mark = scan.nextInt();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("По умолчанию была проставлена 2");
                            mark = 2;
                            break;
                        }
                        if (check_Mark(mark))
                            break;
                        else
                            System.out.println("Введите корректную оценку");
                    }

                    System.out.println("Введите фамилию преподавателя");
                    teacher = scan.next();
                    person.add(new Assessment(num_book, cipher, discipline, data, mark, teacher));
                    break;
                case 2:
                    if(manager.writeData(path, person))
                        System.out.printf("Файл с именем %s создан\n", path);
                    else
                        System.out.println("Произошла ошибка");
                    break;
                case 3:
                    whole_list.clear();
                    person_with_bad_mark.clear();
                    if(manager.readData(path, whole_list, person.size())) {
                        for (Assessment as : whole_list) {
                            if (as.right_mark()) {
                                person_with_bad_mark.add(as);
                            }
                        }
                        System.out.println("Вывести считанные данные студентов троечников?\n1. Да\n2. Нет");
                        int response = scan.nextInt();
                        if (response == 1) {
                            for (Assessment req : person_with_bad_mark) {
                                System.out.println("---------------------");
                                System.out.println(req.getNum_book());
                                System.out.println(req.getCipher());
                                System.out.println(req.getDiscipline());
                                System.out.println(req.getData());
                                System.out.println(req.getMark());
                                System.out.println(req.getTeacher());
                                System.out.println("---------------------");
                            }
                        } else if (response == 2) {
                            for (Assessment ass : whole_list) {
                                System.out.println("---------------------");
                                System.out.println(ass.getNum_book());
                                System.out.println(ass.getCipher());
                                System.out.println(ass.getDiscipline());
                                System.out.println(ass.getData());
                                System.out.println(ass.getMark());
                                System.out.println(ass.getTeacher());
                                System.out.println("---------------------");
                            }
                        } else {
                            System.out.println("\n");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Введите номер книжки объекта для изменения оценки");
                    String numbook = scan.next();
                    boolean flag = false;
                    for (Assessment ass : person) {
                        if (ass.getNum_book().equals(numbook)) {
                            flag = true;
                            System.out.println("Введите новую оценку");
                            int new_mark = scan.nextInt();
                            ass.exchange_mark(new_mark);
                            System.out.printf("Оценка у объекта с номером книжки %s изменена\n", ass.getNum_book());
                            break;
                        }
                    }
                    if (!flag)
                        System.out.println("Объекта с введенным номером книжки не существует");
                    break;
                case 5:
                    boolean point = false;
                    System.out.println("Введите номер зачетной книжки: ");
                    String nbook = scan.next();
                    System.out.println("Введите название дисциплины");
                    String dis = scan.next();

                    for (Assessment pp : person) {
                        if (pp.getNum_book().equals(nbook) && pp.getDiscipline().equals(dis)) {
                            System.out.printf("Студент - номер книжки: %s, дисциплина: %s; имеет оценку: %d\n",
                                    pp.getNum_book(), pp.getDiscipline(), pp.getMark());
                            point = true;
                            break;
                        }
                    }
                    if (!point) {
                        System.out.printf("Студент - номер книжки: %s, дисциплина: %s -> НЕ существует\n",
                                nbook, dis);
                    }
                    break;
                case 6:
                    boolean stop = false;
                    String same_data;
                    String same_discipline;
                    for (int index = 0; index < person.size() - 1; index++) {
                        same_data = person.get(index).getData();
                        same_discipline = person.get(index).getDiscipline();
                        for (int j = index + 1; j < person.size(); j++) {
                            if (person.get(j).getData().equals(same_data) && person.get(j).getDiscipline().equals(same_discipline)) {
                                System.out.printf("Студент с шифром %s и Студент с шифром %s получили оценку по " +
                                                "%s %s числа\n", person.get(index).getCipher(), person.get(j).getCipher(),
                                        same_discipline, same_data);
                                stop = true;
                                break;
                            }
                        }
                        if (stop)
                            break;
                    }
                    if (!stop) {
                        System.out.println("Не существует двух студентов, согласно условию");
                    }
                    break;
                case 7:
                    if(manager.create_file_Access(path, person_with_bad_mark)){
                        System.out.println("Файл 'raf_file.dat' создан успешно");
                    }
                    else
                        System.out.println("Создание файла 'raf_file.dat' привело к ошибке");
                    break;
                default:
                    System.out.println("Выход");
                    ok = 3;
            }
        }
    }
}



