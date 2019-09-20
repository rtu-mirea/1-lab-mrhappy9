package com.company;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.Scanner;

public class Ex_Class {
    private short n;
    private short arr[];

    Ex_Class(short n) throws Exception{
        if (n <= 0){
            throw new Exception(new String("Error creating array without numbers"));
        }
        else{
            this.n = n;
            arr = new short[n];
        }
    }
    void randomInput(){     // Ввод рандомных чисел
        short min = 10;
        short max = 110;
        for(int i = 0; i<n; i++)
                arr[i] = (short) (min + Math.random()*max);
    }
    void handInput(){     // Ввод рандомных чисел из интервала [min, max]
        Scanner in = new Scanner(System.in);
        for(int i = 0; i<n; i++){
            System.out.printf("Введите %d элемент массива: ", i+1);
            arr[i] = (short) in.nextInt();
        }
    }
    void show_without_index(){                  // Вывод элементов массива слева направо
        System.out.println("Элементы массива:");
        for(short num: arr)
            System.out.printf("%d ", (int)num);
        System.out.println();
    }
    void Right_to_Left(){
        System.out.println("Вывод элементов массива в обратном порядке:");
        for(int i = n - 1; i>-1; i--){
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
    void determine_quantity(int example){
        int count = 0;
        for (int i=0; i<n; i++){
            if (example < (int)arr[i])
                count++;
        }
        System.out.println("Количество чисел в массиве больших " + example + ":  " + count);
    }
    void sort_paste(){
        for(int i = 0; i<n; i++){
            for(int j = i; j>0 && arr[j-1]>arr[j]; j--){
                short tmp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = tmp;
            }
        }
        System.out.println("Вывод отсортированного массива: ");
        show_without_index();
    }
}


