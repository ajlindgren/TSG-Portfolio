/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        return Double.parseDouble(temp);

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        boolean inRange = false;
        while (!inRange) {
            if (min <= Double.parseDouble(temp) && Double.parseDouble(temp) <= max) {
                inRange = true;
                return Double.parseDouble(temp);
            } else {
                System.out.println(prompt);
                temp = sc.next();
            }
        }
        return Double.parseDouble(temp);
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        return Float.parseFloat(temp);
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        boolean inRange = false;
        while (!inRange) {
            if (min <= Float.parseFloat(temp) && Float.parseFloat(temp) <= max) {
                inRange = true;
                return Float.parseFloat(temp);
            } else {
                System.out.println(prompt);
                temp = sc.next();
            }
        }
        return Float.parseFloat(temp);
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        return Integer.parseInt(temp);
    }

    //edited to return 0 if Integer.parseInt throws NumberFormatException -- possibly only applicable to DVD Library Application
    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        boolean inRange = false;
        try {
            while (!inRange) {
                if (min <= Integer.parseInt(temp) && Integer.parseInt(temp) <= max) {
                    inRange = true;
                    return Integer.parseInt(temp);
                } else {
                    System.out.println(prompt);
                    temp = sc.next();
                }
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return Integer.parseInt(temp);
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        return Long.parseLong(temp);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        String temp = sc.nextLine();
        boolean inRange = false;
        while (!inRange) {
            if (min <= Long.parseLong(temp) && Long.parseLong(temp) <= max) {
                inRange = true;
                return Long.parseLong(temp);
            } else {
                System.out.println(prompt);
                temp = sc.nextLine();
            }
        }
        return Long.parseLong(temp);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
}
