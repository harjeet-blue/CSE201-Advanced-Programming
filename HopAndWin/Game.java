package HopAndWin;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Integer;

public class Game {

    static Scanner sc = new Scanner(System.in);
    static Player player = new Player();
    static Tile tile[] = new Tile[21];
    static Random_Generator ran = new Random_Generator();

    public static void main(String[] args) {

        setTile();
        String s;
        do {
            System.out.print("Hit Enter to initialise the game : ");
            s = sc.nextLine();

        } while (!s.equals(""));
        System.out.println("Game is Ready");
        jump();
    }

    public static void setTile() {

        String toy[] = {"", "Mickey", "Teddy", "Tom", "Jerry", "Bear", "Lion", "Doll", "Elephant", "Mouse", "wonderwoman",
                "Superman", "Batman", "Spiderman", "Ironman", "Captain America", "Thor", "Hulk", "Loki", "Flash", "Antman"};

        for (int i = 1; i <= 20; i++) {
            tile[i] = new Tile(i, toy[i]);
        }
    }

    public static void jump() {

        for (int i = 1; i <= 5; i++) {

            player.setMoves(i);
            System.out.print("Hit enter for your " + i + " Hop : ");
            sc.nextLine();
            int num = ran.random_Integer(1, 21);
            if (num > 20)
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
            else
                CollectPrize(num);
        }

        System.out.println("Game Over\nSoft toys won by you are:");
        for (int i = 0; i < player.get_total(); i++) {

            try {
                System.out.println(player.getBucket().get(i).getName());
            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println(e.getMessage());
            }

        }
    }

    public static void CollectPrize(int n) {

        System.out.println("You landed on " + n + " tile");
        String ans;

        if (n % 2 == 1) {

            System.out.println("Question answer round. Integer or strings?");
            ans = sc.nextLine();
            if (ans.equals("Integer")) {
                Integer_quiz();
            } else
                String_quiz();

        }
        Toy toy = null;

        try {
            toy = tile[n].softCopy();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("You won " + toy.getName() + " toy");
        player.set_Total(player.get_total() + 1);
        player.addToy(toy);
    }

    public static void Integer_quiz() {

        int a = 0, b = 0;
        boolean flag = true;
        Division div = new Division(a, b);

        while (flag) {
            try {

                a = ran.random_Integer(-100, 100);
                b = ran.random_Integer(-100, 100);
                div = new Division(a, b);
                div.check();
                if (b != 0) flag = false;

            } catch (DividebyZeroException e) {

                System.out.println(e.getMessage());
                System.out.println("Cannot divide by zero generating new Numbers");

            } catch (InvalidTypeException e) {
                System.out.println(e.getMessage());
            }
        }
        flag = true;
        System.out.println("Calculate the result of " + a + " divided by " + b);
        int ans = 0;
        String input = "";
        boolean stop = false;
        do {

            input = sc.nextLine();
            try {
                ans = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Number format exception Enter again");
                continue;
            }

            stop = div.verify(ans);
            if (!stop) System.out.println("Wrong ans enter again");
        } while (!stop);

        System.out.println("Correct ans");
    }

    public static void String_quiz() {

        String a = ran.random_string();
        String b = ran.random_string();

        Concatenate con = new Concatenate(a, b);
        System.out.println("Calculate the concatenate of strings " + a + " with " + b);
        String ans = "";
        boolean stop = true;
        do {
            try {
                ans = sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            stop = con.verify(ans);
            if (!stop) System.out.println("Wrong ans pls enter again");
        } while (!stop);

        System.out.println("Correct ans");

    }
}
