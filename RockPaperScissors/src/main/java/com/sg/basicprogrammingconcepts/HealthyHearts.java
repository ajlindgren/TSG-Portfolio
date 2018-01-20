/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.basicprogrammingconcepts;
import java.util.Scanner;
/**
 *
 * @author Alex
 */
public class HealthyHearts {
    public static void main(String[] args) {
        int userAge;
        double maxRate;
        double targetRateUpper;
        double targetRateLower;
        
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter your age: ");
        userAge = myScanner.nextInt();
        
        maxRate = 220 - userAge;
        targetRateUpper = maxRate * 0.85;
        targetRateLower = maxRate * .5;
        
        System.out.println("Your maximum heart rate should be " + (int)maxRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + (int)targetRateLower + " - " + (int)targetRateUpper + " beats per minute.");
    }
}
