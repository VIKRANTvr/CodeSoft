package com.number_game;

import java.util.Random;
import java.util.Scanner;

public class Number_Game {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		boolean playAgain = true;

		int totalScore = 0;

		System.out.println("Welcome to the Number Guessing Game!");

		while (playAgain) {
			int numberToGuess = random.nextInt(100) + 1;
			int attempts = 0;
			int maxAttempts = 5;
			boolean guessedCorrectly = false;

			System.out.println("\nGuess a number between 1 and 100. You have " + maxAttempts + " attempts.");

			while (attempts < maxAttempts) {
				System.out.print("Enter your guess: ");
				int userGuess = scanner.nextInt();
				attempts++;

				if (userGuess < numberToGuess) {
					System.out.println("Too low! Try again.");
				} else if (userGuess > numberToGuess) {
					System.out.println("Too high! Try again.");
				} else {
					System.out.println("Congratulations! You guessed the correct number: " + numberToGuess);
					totalScore += (maxAttempts - attempts + 1);
					guessedCorrectly = true;
					break;
				}
			}

			if (!guessedCorrectly) {
				System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was: "
						+ numberToGuess);
			}

			System.out.println("Your current score: " + totalScore);

			System.out.print("Do you want to play again? (yes/no): ");
			playAgain = scanner.next().equalsIgnoreCase("yes");
		}

		System.out.println("Thanks for playing! Your final score is : " + totalScore);
		scanner.close();
	}
}
