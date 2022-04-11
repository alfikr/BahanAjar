package com.sinau.bareng;

import com.sinau.bareng.singleton.BelajarSingleton;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Application {
    public static void main(String[] args) {
        Thread b = new Thread(()-> {
            while (true){
                if (BelajarSingleton.getInstance().getVal()!=null){
                    System.out.println(BelajarSingleton.getInstance().getVal());
                }
//                System.out.println(BelajarSingleton.getInstance().getVal()!=null?
//                        BelajarSingleton.getInstance().getVal():"Tidak ada data");
            }
//            System.out.println("thread B");
        });
        Thread a = new Thread(()-> {
//            System.out.println("Thread A");
            System.out.println("Input some text :");
            try(Scanner sc = new Scanner(System.in)){
                BelajarSingleton.getInstance().setVal(sc.nextLine());
            }

//            try {
//                sleep(1000);
//                b.start();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        a.start();
        b.start();
    }
}
