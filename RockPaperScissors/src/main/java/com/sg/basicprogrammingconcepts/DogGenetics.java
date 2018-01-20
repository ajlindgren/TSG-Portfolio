/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.basicprogrammingconcepts;

import java.util.*;

/**
 *
 * @author Alex
 */
public class DogGenetics {

    public static void main(String[] args) {
        Random dogHeritage = new Random();
        Scanner sc = new Scanner(System.in);

        //every possible breed of dog
        String[] breeds = {"St. Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"};

        System.out.println("What is your dog's name?");
        String dogName = sc.nextLine();

        System.out.println("");
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        System.out.println("");
        System.out.println(dogName + " is:");

        //divide dog's gene pool into 5 sections
        int[] geneDividers = new int[6];

        //generate 4 random numbers in geneDividers[]
        for (int i = 0; i < 4; i++) {
            geneDividers[i] = dogHeritage.nextInt(100);
        }

        //add 0 and 100 to geneDividers[] and sort its elements by increasing value
        geneDividers[4] = 0;
        geneDividers[5] = 100;
        Arrays.sort(geneDividers);

        //perform subtraction on geneDividers to extract 5 randomly generated values which sum to 100
        //associate each value with a breed and print the list
        for (int i = 0; i < 5; i++) {
            System.out.println((geneDividers[i + 1] - geneDividers[i]) + "% " + breeds[i]);
        }

        //lie to the user
        System.out.println("");
        System.out.println("Wow, that's QUITE the dog!");

    }
}
