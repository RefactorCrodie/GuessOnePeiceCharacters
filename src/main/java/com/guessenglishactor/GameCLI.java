package com.guessenglishactor;

import java.util.Scanner;

public class GameCLI {
    private String Character;
    private Scanner PlayerResponse;
    private GameLogic Game;

    public GameCLI(GameLogic GameObject, Scanner scanner) {
        System.out.println("How many characters do you know from the show One Piece? (All Seasons)");
        System.out.println("Let's Start! (if you wish to quit at any time, enter 'exit')");
        System.out.println("Feel free to start entering names!: ");
        this.PlayerResponse = scanner;
        this.Game = GameObject;
    }

    public void start() {
        while (Game.isActive()) {
            Character = PlayerResponse.nextLine();
            if (Character.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for Playing!");
                Game.GameOver();
            } else if (Game.Selection(Character)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong or was already selected!");
            }
            System.out.println("Score is " + Game.getProperlyGuessedCharacters());
        }
    }
}