package com.drsg.demo.v1.utils;

public class Main {
    public static void main(String[] args) {
        long i = 0;
        long s = System.currentTimeMillis() + 30;
        while (System.currentTimeMillis() < s) {
//            IdWorker.generateId();
            ;
            System.out.println(IdUtil.nextId());
            i++;
        }
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
    }
}
