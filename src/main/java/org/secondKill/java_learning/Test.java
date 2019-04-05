package org.secondKill.java_learning;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        int result;
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextLine())
        {
            String str1 = sc.nextLine();
            String[] strArr = str1.split(" ");
            int length = strArr.length;
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) {
                arr[i] = Integer.parseInt(strArr[i]);
            }
            
            String str2 = sc.nextLine();
            int a = Integer.parseInt(str2);
            String str3 = sc.nextLine();
            int b = Integer.parseInt(str3);

            int read = 0;
            int j = 0;
            int count1 = 0;
            int count2 = 0;
            int aNum=0;
            int bNum=0;
            for (int i = 0; i < a; i++) {
                while ((j <= 9 || arr[j] != 0) && count1 < a) {
                    if (arr[i] >0) {
                        aNum = aNum*10 + i;
                        arr[j]--;
                        count1++;
                    }

                    if (arr[j] == 0) {
                        j++;
                    }

                }
            }

            int k =j;
            for (int i = 0; i < b; i++) {
                while ((k <= 9 || arr[k] != 0) && count2 < b) {
                    bNum = bNum*10 + i;
                    arr[k]--;
                    if (k == 0) {
                        k--;
                    }
                }
            }
            System.out.println(a*b);
        }

        sc.close();
    }


}
