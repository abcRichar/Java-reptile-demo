package org.example;

public class Main {
    public static void main(String[] args) {
        int a[ ] = { 2, 0, 4, 1, 8, 3, 5 };

        int temp;

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = a.length - 1; j > i; j--) {

                if (a[j] < a[j - 1]) {

                    temp = a[j];

                    a[j] = a[j - 1];

                    a[j - 1] = temp;

                }

            }

        }

        for (Integer i : a) {

            System.out.print(i);

        }
    }
}