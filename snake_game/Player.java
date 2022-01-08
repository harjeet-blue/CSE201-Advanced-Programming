package snake_game;

public class Player {

    private String name;
    private int floor;
    private int score;

    public Player() {

        System.out.print("Enter the Player name : ");
        this.name = Snake_and_ladders.sc.nextLine();
        this.floor = 0;
        this.score = 0;

    }


    public void set_floor(int n) {
        this.floor = n;
    }

    public void set_score(int n) {
        this.score = n;
    }

    public int get_floor() {
        return floor;
    }

    public int get_score() {
        return score;
    }

    public String get_name() {
        return name;
    }

}
