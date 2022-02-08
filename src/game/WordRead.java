/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class WordRead {

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());
        boolean exit = false;
        String Vocab;
        File[] files = {new File("animals.txt"),
            new File("Furenituredata.txt")
        };

        do {
            String mainMenu = ("Choose Gatagory:\n"
                    + "     (1) Animal\n"
                    + "     (2) Fureniture\n"
                    + "     (-1) Out\n");
            System.out.print(mainMenu);
            int menuChoice = keyboard.nextInt();

            switch (menuChoice) {
                case 1:
                    for (File file : files) {
                        if (file.isFile()) {
                            Vocab = Read("animals.txt");
                            System.out.println(Vocab);
                        }
                    }
                    break;

                case 2:
                    for (File file : files) {
                        if (file.isFile()) {
                            Vocab = Read("Furenituredata.txt");
                            System.out.println(Vocab);
                        }
                    }
                    break;

                case -1:
                    exit = true;
                    // klaviyeden -1 tikladiktan sonra cıkacak durumdayız.
                    System.out.println("Out...");
                    break;

                default:
                    System.out.println("Wrong Number!");
                    System.out.println();
            }
        } while (!exit);

    }

    public static String Read(String filename) throws FileNotFoundException, IOException {
        BufferedReader inputStream = null;
        List<String> words = new ArrayList<String>();
        String line;
        String randomWord = null;
        try {
            inputStream = new BufferedReader(new FileReader(filename));
            while ((line = inputStream.readLine()) != null) {
                String[] wordsLine = line.split(" ");
                for (String word : wordsLine) {
                    words.add(word);
                }
                line = inputStream.readLine();
                Random rand = new Random(System.currentTimeMillis());
                randomWord = words.get(rand.nextInt(words.size()));
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return randomWord;
        /*
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        List<String> words = new ArrayList<String>();
        while (line != null) {
            String[] wordsLine = line.split(" ");
            for (String word : wordsLine) {
                words.add(word);
            }
            line = reader.readLine();
        }
        Random rand = new Random(System.currentTimeMillis());
        String randomWord = words.get(rand.nextInt(words.size()));
        return randomWord;
         */
    }

}
