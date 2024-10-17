package com.example;
import lombok.Data;

import java.util.HashMap;
import java.util.Random;

public class Main {
    private static final int GAMES_TO_PLAY = 1000;

    public static void main(String[] args) {
        HashMap<Integer, Boolean> resultsStay = new HashMap<>();
        HashMap<Integer, Boolean> resultsSwitch = new HashMap<>();

        int winsStay = 0;
        int winsSwitch = 0;

        Random random = new Random();

        for (int i = 1; i <= GAMES_TO_PLAY; i++) {
            boolean[] doors = {false, false, false};
            doors[random.nextInt(3)] = true; // random car placement

            int playerChoice = random.nextInt(3); // player's initial choice
            int hostReveals = revealGoat(doors, playerChoice, random); // host reveals a goat

            // Player chooses to stay
            boolean winStay = doors[playerChoice];
            resultsStay.put(i, winStay);
            if (winStay) {
                winsStay++;
            }

            int switchChoice = 3 - playerChoice - hostReveals; // switch to the remaining door
            boolean winSwitch = doors[switchChoice];
            resultsSwitch.put(i, winSwitch);
            if (winSwitch) {
                winsSwitch++;
            }
        }

        System.out.println("Результат 1000 игр в Монти Холле:");
        System.out.println("Стратегия \"Не меняй\" побеждает:" + winsStay );
        System.out.println("Стратегия \"Меняй\" выигрывает:" + winsSwitch);
    }

    private static int revealGoat(boolean[] doors, int playerChoice, Random random) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && !doors[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("No goat to reveal!");
    }
}
