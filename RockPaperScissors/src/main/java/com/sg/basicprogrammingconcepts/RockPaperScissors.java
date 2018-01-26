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
public class RockPaperScissors {

    //compare to determine winner of each bout
    static int cpuThrowID;
    static int playerThrowID;
    //store and frequently update score counters
    static int playerScore;
    static int cpuScore;
    static int drawCount;
    //determine whether the entire game loops after final scoreboard, could be replaced with a boolean
    static int playAgain;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random cpuThrow = new Random();

        System.out.println("How many rounds of Rock Paper Scissors will you play? (1-10): ");
        int numRounds = sc.nextInt();
        //only 1-10 are valid, an out-of-bounds entry will exit the program
        if (numRounds >= 1 && numRounds <= 10) {
            //main gameplay loop
            do {
                if (numRounds >= 1 && numRounds <= 10) {
                    for (int j = 1; j <= numRounds; j++) {
                        cpuThrowID = cpuThrow.nextInt(3) + 1;
                        playerThrowID = getThrow("Your Throw (Rock, Paper, or Scissors): ");
                        //draw condition and counter
                        if (playerThrowID == cpuThrowID) {
                            System.out.print("Round " + j + " result: ");
                            System.out.println("DRAW");
                            drawCount++;
                            //win condition and counter
                        } else if ((playerThrowID == 1 && cpuThrowID == 2) || (playerThrowID == 2 && cpuThrowID == 3) || (playerThrowID == 3 && cpuThrowID == 1)) {
                            System.out.print("Round " + j + " result: ");
                            System.out.println("You score!");
                            playerScore++;
                            //loss condition and counter
                        } else {
                            System.out.print("Round " + j + " result: ");
                            System.out.println("CPU scores!");
                            cpuScore++;
                        }

                        //print scoreboard after each throw
                        System.out.println("");
                        System.out.println("Current score: ");
                        System.out.println("Player: " + playerScore);
                        System.out.println("CPU: " + cpuScore);
                        System.out.println("Draws: " + drawCount);
                        System.out.println("");

                    }

                    //print final scoreboard
                    System.out.println("");
                    System.out.println("That's it! Final Tally: ");
                    System.out.println("You scored " + playerScore + " time(s).");
                    System.out.println("The CPU scored " + cpuScore + " time(s).");
                    System.out.println("You drew " + drawCount + " time(s).");
                    System.out.println("");

                    if (playerScore > cpuScore) {
                        System.out.println("You win!");
                        System.out.println("");
                    } else if (cpuScore > playerScore) {
                        System.out.println("The CPU wins!");
                        System.out.println("");
                    } else {
                        System.out.println("Wow! A dead heat!");
                        System.out.println("");
                    }

                    //play again or exit
                    System.out.println("Play Again? (Yes/No)");
                    String insertCoin = sc.next();
                    if ("Yes".compareToIgnoreCase(insertCoin) == 0) {
                        playAgain = 1;
                        System.out.println("How many rounds of RPS to play: ");
                        numRounds = sc.nextInt();
                    } else {
                        playAgain = 0;
                        System.out.println("");
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("You must enter a number 1-10.");
                    System.exit(0);
                }
            } while (playAgain == 1);
        } else {
            System.out.println("You must enter a number 1-10.");
            System.exit(0);
        }
    }

    //getThrow scans in the player's throw selection and returns its integer analog
    public static int getThrow(String throwPrompt) {
        Scanner sc = new Scanner(System.in);
        boolean validEntry;
        do {
            System.out.print(throwPrompt);
            String playerThrow = sc.next();
            if ("Rock".compareToIgnoreCase(playerThrow) == 0) {
                playerThrowID = 1;
                validEntry = true;
            } else if ("Scissors".compareToIgnoreCase(playerThrow) == 0) {
                playerThrowID = 2;
                validEntry = true;
            } else if ("Paper".compareToIgnoreCase(playerThrow) == 0) {
                playerThrowID = 3;
                validEntry = true;
            } else {
                System.out.println("That's not an option in this game.");
                validEntry = false;
            }
            //if user entry !rock!paper!scissors, repeat prompt
        } while (!validEntry);
        return playerThrowID;
    }

}
