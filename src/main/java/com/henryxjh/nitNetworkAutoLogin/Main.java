package com.henryxjh.nitNetworkAutoLogin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(RSAEncrypt.encryptPassword(sc.nextLine()));
        sc.close();
    }
}