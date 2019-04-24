package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    String answer=" ";
        Scanner input = new Scanner(System.in);

        System.out.println("Good day. What is your problem?");
        while(!answer.equalsIgnoreCase("q")){
            System.out.print("Enter your response here or Q to quit: ");
            answer = input.nextLine();
            if(!answer.equals("q")){
               System.out.println(answer);
            }
        }

    }
}
