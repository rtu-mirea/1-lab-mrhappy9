package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        short n = 0, min, max;
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        Ex_Class cl;
        System.out.println("Введите длину массива: ");
        cl = new Ex_Class((short)scan.nextInt());
        while (flag) {
            try {
                System.out.println("1. Заполнить массив рандомными числами");
                System.out.println("2. Заполнить массив вручную.");
                System.out.println("3. Определить количество чисел массива больше заданного.");
                System.out.println("4. Сортировать массив методом вставки.");
                System.out.println("5. Вывести элементы массива слева направо.");
                System.out.println("6. Вывести элементы массива справа налево.");
                int place = scan.nextInt();
                switch (place){
                    case 1:
                        cl.randomInput();
                        System.out.println("Массив заполнен успешно.");
                        break;
                    case 2:
                        cl.handInput();
                        break;
                    case 3:
                        System.out.println("Введите число, с которым будут сравниваться элементы массива: ");
                        int x = scan.nextInt();
                        cl.determine_quantity(x);
                        break;
                    case 4:
                        cl.sort_paste();
                        System.out.println("Сортировка прошла успешно.");
                        break;
                    case 5:
                        cl.show_without_index();
                        break;
                    case 6:
                        cl.Right_to_Left();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
