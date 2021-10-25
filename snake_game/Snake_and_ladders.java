package snake_game;

import java.util.Scanner;

public class Snake_and_ladders{

    static Scanner sc = new Scanner(System.in);
    static floor[] Floor = new floor[14];
    static String str;
    public static void main(String[] args) {

        set_floor();
        Game g = new Game();
        Dice d = new Dice();
        System.err.println();
        System.out.println("Game setup is ready");
        can_we_start(d);
        began(g,d);
    }

    public static void can_we_start(Dice d) {

        do{
            System.out.print("Hit enter to roll the Dcie :");
            str = sc.nextLine();
            d.flip();
            System.out.println(d);
            if(d.can_start())break; 
            System.out.println("Game cannot start until you get 1"+str);

        }while(!d.can_start());

    }

    public static void set_floor() {
        
        Floor[0] = new empty_floor(0);
        Floor[1] = new empty_floor(1);
        Floor[2] = new empty_floor(2);
        Floor[3] = new empty_floor(3);
        Floor[4] = new empty_floor(4);
        Floor[5] = new empty_floor(5);
        Floor[6] = new empty_floor(6);
        Floor[7] = new empty_floor(7);
        Floor[8] = new empty_floor(8);
        Floor[9] = new empty_floor(9);
        Floor[10] = new empty_floor(10);
        Floor[11] = new empty_floor(11);
        Floor[12] = new empty_floor(12);
        Floor[13] = new empty_floor(13);
    }

    public static void began( Game g, Dice d) {

        Elevator floor_2 = new Elevator(2);
        normal_ladder floor_8 = new normal_ladder(8);
        King_cobra floor_11 = new King_cobra(11);
        normal_snake floor_5 = new normal_snake(5);
        
        int dice = 0;
        int curr = 0;
        int flag = 0;

        do {
            
            switch (curr+dice) {

                case 0: curr = g.Empty_Floor_updates(Floor[0]);
                    break;
                case 1: curr = g.Empty_Floor_updates(Floor[1]);
                    break;
                case 2: curr = g.Ladder_floor_updates(floor_2);
                        dice = 0;
                        continue;
                case 3: curr = g.Empty_Floor_updates(Floor[3]);
                    break;
                case 4: curr = g.Empty_Floor_updates(Floor[4]);
                    break;
                case 5: curr = g.Snake_floor_updates(floor_5);
                        dice = 0;
                        continue;
                case 6: curr = g.Empty_Floor_updates(Floor[6]);
                    break;
                case 7: curr = g.Empty_Floor_updates(Floor[7]);
                    break;
                case 8: curr = g.Ladder_floor_updates(floor_8);
                        dice = 0;
                        continue;
                case 9: curr = g.Empty_Floor_updates(Floor[9]);
                    break;
                case 10: curr = g.Empty_Floor_updates(Floor[10]);
                    break;
                case 11: curr = g.Snake_floor_updates(floor_11);
                         dice = 0;
                         continue;
                case 12: curr = g.Empty_Floor_updates(Floor[12]);
                    break;
                case 13: g.game_over(); flag =1;
                    break;
                default:  System.out.println("Player cannot move");
                    break;
            }
            if(flag==1)break;
            System.out.println();
            System.out.print("Hit enter to roll the dice : ");
            str = sc.nextLine();
            d.flip();
            System.out.println(d);
            dice = d.get_face();

        } while (flag==0);

    }

}