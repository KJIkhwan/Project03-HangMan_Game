/*
 @ Dosya adı : Game
    @ Java programlama dilinde adam asmaca oyununun yapılmasını
      içermektedir. 
        - Program kullanıcıyı yönlendirecek bir menüden meydana gelecektir.
        - Menüde bulunan seçenekler kullanıcı tarafından parantez içerisinde belirtilen karakter girilerek sağlanacaktır.
        - Oyun kullanıcının bir kelimenin harflerini tahmin ederek kelimeyi en fazla 3 yanlış tahmin ile bilmesini sağlamak üzere kurulmuştur.
        - Programda kullanıcı oyuna her yeni başladığında sözlükte bulunan bir kelime rastgele üretilecektir, 
          programda kelimeler string bir array içerisinde tutulacaktır.
    
    @ BLM103 Bilgisayar Programlama I Dersi 1.Ödev
    @ Kod oluşturduğum Tarih : 16/11/2018
    @ Adı-soyad : Ikhwan KAJA 
      Email : ikhwan.kaja@stu.fsm.edu.tr
 */
package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static boolean endGame = false;
    public static StringBuilder newString = new StringBuilder();
    public static int missedBy = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("animals.txt"));
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

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("animals.txt", true)));

        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        /* Menüde bulunan seçenekler kullanıcı tarafından parantez içerisinde 
           belirtilen karakter girilerek sağlanacaktır. */
        System.out.println("Menü: ");
        System.out.println();
        System.out.println("Yapılması istenen işlem için karektor giriniz:");
        System.out.println();

        do {
            String mainMenu = ("     Oyuna başlamak için (B)\n"
                    + "     Sözlüğe yeni kelime eklemek için (Y)\n"
                    + "     Çıkmak için (C)\n"
                    + "\n"
                    + "Seçim ? =\t");
            System.out.print(mainMenu);
            String menuChoice = keyboard.next();

            switch (menuChoice) {
                case "B":
                    System.out.println("--- OYUN....BASLA!! --- ");     // klaviyeden B tıkladıktan sonra oyun başlayacaktır.
                    //String wordSelected = randomWord                  // dizinin kelimelerinden sadece 1 tane kelime random olarak tutacaktır.
                    startGame(randomWord);                              // tuttugu kelimesi tahmin edecektir. 
                    System.out.println("-- Oyun Bitti --");             // gizli kelimeyi çözer çözmezse oyun bitti. 
                    System.out.println();
                    // klaviyeden A tıkladıktan sonra ana menüye dönecektir.
                    System.out.print("Ana menüye dönmek için (A)\t");
                    String d = keyboard.next();
                    if (d.equals("A") || d.equals("a")) {
                        System.out.println();
                        break;
                    }

                case "Y":
                    System.out.print("Yeni kelimeyi giriniz =\t");           // klaviyeden Y tikladiktan sonra dizide yeni kelimeyin ekleyeceği durumdayız.
                    String newWord = keyboard.next();
                    out.print("");                                           // Star with new line.
                    out.println(newWord);                                    // klaviyeden yeni kelime sözluğu ekleyecektir.
                    out.close();
                    System.out.println("Kelime sözlüğe başarılı bir şekilde eklenmiştir!.");
                    System.out.println();
                    // klaviyeden A tıkladıktan sonra ana menüye dönecektir.
                    System.out.print("Ana menüye dönmek için (A)\t");
                    String b = keyboard.next();
                    if (b.equals("A") || b.equals("a")) {
                        System.out.println();
                        break;
                    }
                case "C":
                    exit = true;
                    // klaviyeden C tikladiktan sonra oyun cıkacak durumdayız.
                    System.out.println("Cikis yapiyor..");
                    break;
                default:
                    // klaviyeden başka bir harf tikladiysa ekrandaki "Yalış Seçtiniz!" yazacaktır.
                    System.out.println("Yanlis Sectiniz!");
                    System.out.println();
            }

        } while (!exit);
    }

    // Oyun bir kelimenin harflerini tahmin edecek bir metodu.
    public static void startGame(String wordSelected) {

        int i;
        char charInputed;
        for (i = 0; i < wordSelected.length(); i++) {
            newString.append("*");
        }
        while (endGame == false) {
            if (!wordSelected.equals(newString.toString())) {
                Scanner input = new Scanner(System.in);
                System.out.println("(Guess!) Enter a letter in word " + newString + " > ");
                charInputed = input.next().charAt(0);
                calculate(wordSelected, charInputed);
          
            } else {
                System.out.println("The word is " + newString + ".  You used " + missedBy + " time to correct them all.");
                endGame = true;
            }
        }
    }

    // Gizli kelimeyi kaç defa tahmin ettik bir metodu. 
    public static void calculate(String wordSelected, char charInputed) {

        int i;
        String temp;
        boolean miss = false;
        for (i = 0; i < wordSelected.length(); i++) {
            if (wordSelected.charAt(i) == charInputed) {
                temp = Character.toString(wordSelected.charAt(i));
                newString.replace(i, (i + 1), temp);
            } else {
                miss = true;
            }
        }
        if (miss = true) {
            missedBy++;
            miss = false;

        }

    }
}
