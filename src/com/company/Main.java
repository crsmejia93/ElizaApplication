package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    String answer=" ";
	    String newAns=" ";
        Scanner input = new Scanner(System.in);
        HashMap<String, String> words = new HashMap<>();

        String fileName = "C:\\Users\\GBTC440018ur\\IdeaProjects\\ElizaApplication\\words.txt";
        populateFromFile(words,fileName);
        System.out.println(words);

        System.out.println("Good day. What is your problem?");
        while(!answer.equalsIgnoreCase("q")){
            System.out.print("Enter your response here or Q to quit: ");
            answer = input.nextLine();
            if(!answer.equals("q")){
                String[] arrAns = answer.split(" ");
                for(String word: arrAns){
                    if(words.containsKey(word)){
                        word = words.get(word);
                    }
                }
               System.out.println(answer);
            }
        }

    }

    private static void populateFromFile(HashMap<String, String> myMap, String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanInput = new Scanner(file);
            while(scanInput.hasNextLine()){
                myMap.put(scanInput.next(),scanInput.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
    }
}
