package snake_game;

class Dice {

    private int face;
    private final int start = 1;


    public Dice() {
        flip();
    }

    public void flip() {
        face = (int) (Math.random() * 2) + 1;
    }

    public int get_face() {
        return face;
    }

    public boolean can_start() {
        return face == start;
    }

    @Override
    public String toString() {
        return "Dice gave " + face;
    }

}

public class Game {

    private Player p;

    public Game() {
        p = new Player();
    }

    public void print_information(int n, int x) {
        p.set_floor(x);
        p.set_score(n);
        System.out.println("Total points : " + p.get_score());
    }

    public int Empty_Floor_updates(floor f) {

        System.out.println("Player position Floor - " + f.get_floor());
        int n = f.update_score(p.get_score());
        int x = f.update_floor();

        print_information(n, x);
        System.out.println(p.get_name() + " has reached " + f);
        return p.get_floor();
    }

    public int Ladder_floor_updates(ladder l) {

        System.out.println("Player position Floor - " + l.get_floor());
        int n = l.update_score(p.get_score());
        int x = l.update_floor();

        print_information(n, x);
        System.out.println(p.get_name() + " has reached " + l);
        return p.get_floor();
    }

    public int Snake_floor_updates(snake_floor s) {

        System.out.println("Player position Floor - " + s.get_floor());
        int n = s.update_score(p.get_score());
        int x = s.update_floor();

        print_information(n, x);
        System.out.println(p.get_name() + " has reached " + s);
        return p.get_floor();
    }

    public int game_over() {

        p.set_score(1 + p.get_score());
        print_information(p.get_score(), 13);
        System.out.println(p.get_name() + " has reached an Empty floor\nGame Over");
        System.out.println(p.get_name() + " Accumulated " + p.get_score());
        return 13;

    }
}   
