package org.develop;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides methods to read input from the console.
 * It includes methods to read integer, float, double, and string values with error handling for incorrect input types.
 */
    public class Input {

        static Scanner setInput = new Scanner(System.in);

        public static int scanningForInt(String message) {
            int inputInt = 0;
            boolean correct = false;
            do {
                System.out.println(message);
                try {
                    inputInt = setInput.nextInt();
                    correct = true;
                } catch (InputMismatchException ex) {
                    System.out.println("ERROR. Input type mismatch.\n");
                }
                setInput.nextLine();
            } while (!correct);
            return inputInt;
        }

        public static float scanningForFloat(String message) {
            float inputFloat = 0f;
            boolean correct = false;
            do {
                System.out.println(message);
                try {
                    inputFloat = setInput.nextFloat();
                    correct = true;
                } catch (InputMismatchException ex) {
                    System.out.println("ERROR. Input type mismatch.\n");
                }
                setInput.nextLine();
            } while (!correct);
            return inputFloat;
        }

        public static double scanningForDouble(String message) {
            double inputDouble = 0.0;
            boolean correct = false;
            do {
                System.out.println(message);
                try {
                    inputDouble = setInput.nextDouble();
                    correct = true;
                } catch (InputMismatchException ex) {
                    System.out.println("ERROR. Input type mismatch.\n");
                }
                setInput.nextLine();
            } while (!correct);
            return inputDouble;
        }

        public static String scanningForString(String message) {
            String input = "";
            boolean correct = false;
            do {
                System.out.println(message);
                try {
                    input = setInput.nextLine();
                    if (input.length() < 2) {
                        throw new Exception();
                    } else {
                        correct = true;
                    }
                } catch (Exception ex) {
                    System.out.println("ERROR. Input type requires length > 1.\n");
                }
            } while (!correct);
            return input;
        }
    }