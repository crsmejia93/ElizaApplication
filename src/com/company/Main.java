package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String answer = " ";
        int random, count = 0;
        Scanner input = new Scanner(System.in);
        HashMap<String, String> words = new HashMap<>();
        String[] hedge = {"Please tell me more", "Many of my patients tell me the same thing"};
        String[] qualifier = {"Why do you say that", "You seem to think that", "So, you are concerned that"};
        String fileName = "words.txt";//This is the file that will be looked for.

        populateFromFile(words, fileName);//This populates the HashMap from file in fileName
        System.out.println("Good day. What is your problem?");
        while (!answer.equalsIgnoreCase("q")) {
            System.out.print("Enter your response here or Q to quit:");
            answer = input.nextLine();
            if (!answer.equals("q")) {
                if (answer.equalsIgnoreCase("pig")) {
                    System.out.print("Enter a word to play: ");
                    answer = input.nextLine();
                    String[] arrAns = answer.split(" ");
                    while(!answer.equalsIgnoreCase("no pig")) {
                        answer = pigLatin(arrAns);
                        System.out.println(answer);
                    }
                } else {
                    String[] arrAns = answer.split(" ");
                    random = (int) (1 + Math.random() * 20);
                    if (random < 10) {
                        answer = replaceWords(arrAns, qualifier, words);
                        System.out.println(answer);
                    } else if (random >= 10) {
                        printHedge(hedge);
                    }
                    count++;
                }
            }
            if (count == 5) {
                System.out.println("It is getting late, maybe we had better quit!");
            }
        }//end while

    }

    //------------------------METHODS----------------------------------------------------------------//
    private static String pigLatin(String[] arrAns) {
        String str="", tempStr="";
        HashMap<Character, String> replacement = new HashMap<>();
        //This populates the replacement HashMap
        populateFromPigFile(replacement, "pigLatin.txt");
        for(int i=0; i<arrAns.length;i++){//This iterates through the words
            //if the first letter on the word contains a consonant add "ay" to the end

            //This checks if the replacement HashMap contains the first letter of the word
            //and if it does than it appends ay to the end of word
            if(replacement.containsKey(arrAns[i].charAt(0))){
                tempStr = arrAns[i].concat(replacement.get(arrAns[i].charAt(0)));
            }
        }
        return str.concat(String.join("", tempStr));

    }

    private static void printHedge(String[] hedge) {
        int index = (int) (Math.random() * hedge.length);
        System.out.println(hedge[index]);
    }

    private static String replaceWords(String[] strArr, String[] qualifier, HashMap<String, String> hashMap) {
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            if (hashMap.containsKey(strArr[i])) {
                strArr[i] = hashMap.get(strArr[i]);
            }
        }
        //This for loop randomly selects a qualifier to prepend to the string that will be returned
        int index = (int) (Math.random() * qualifier.length - 1);
        for (int i = 0; i < qualifier.length; i++) {
            if (i == index) {
                str = qualifier[index];
            }
        }
        return str.concat(" " + String.join(" ", strArr));
    }

    private static void populateFromPigFile(HashMap<Character, String> myMap, String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanInput = new Scanner(file);
            while (scanInput.hasNextLine()) {
                myMap.put(scanInput.next().charAt(0), scanInput.nextLine());

            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
    }

    private static void populateFromFile(HashMap<String, String> myMap, String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanInput = new Scanner(file);
            while (scanInput.hasNextLine()) {
                myMap.put(scanInput.next(), scanInput.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
    }
}
