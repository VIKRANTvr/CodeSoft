package com.grade_calculator;

import java.util.Scanner;

public class Student_Grade_Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of subjects: ");
		int numSubjects = scanner.nextInt();

		int totalMarks = 0;
		int marks;

		for (int i = 1; i <= numSubjects; i++) {
			System.out.print("Enter marks for subject " + i + " (out of 100): ");
			marks = scanner.nextInt();

			while (marks < 0 || marks > 100) {
				System.out.print("Invalid marks! Enter again for subject " + i + ": ");
				marks = scanner.nextInt();
			}

			totalMarks += marks;
		}

		double percentage = (double) totalMarks / numSubjects;

		String grade;
		if (percentage >= 90) {
			grade = "A+";
		} else if (percentage >= 80) {
			grade = "A";
		} else if (percentage >= 70) {
			grade = "B";
		} else if (percentage >= 60) {
			grade = "C";
		} else if (percentage >= 50) {
			grade = "D";
		} else {
			grade = "F (Fail)";
		}

		System.out.println("\n---- Student Grade Report ----");
		System.out.println("Total Marks: " + totalMarks);
		System.out.println("Average Percentage: " + percentage + "%");
		System.out.println("Grade: " + grade);

		scanner.close();
	}
}