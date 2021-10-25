package snake_game;

public class floor {

    private int floor;  
    private int point;
    
    public floor(int n){
        this.floor = n;
        this.point = 1;
    }

    public int update_score(int n){
        return n+get_point();
    }   
    public int update_floor(){
        return floor;
    }
    public void set_floor(int n) {
        this.floor = n;
    }
    public void set_point(int n){
        this.point = n;
    }
    public int get_point() {
        return point;
    }
    public int get_floor(){
        return floor;
    }
  
}

class empty_floor extends floor{

    
    public empty_floor(int n) {
        super(n);
    }

    @Override
    public int update_floor(){
        return get_floor();
    }
    @Override
    public String toString(){
        return "Empty Floor";
    }

}

class snake_floor extends floor{

    private int cost;

    public snake_floor(int n) {
        super(n);
        
    }
    public int update_floor(){
        return get_floor();
    }
    public void set_cost(int n){
        cost = n;
    }
    public int get_cost() {
        return this.cost;
    }

    
}

class ladder extends floor {

    private int reward;

    public ladder(int n) {
        super(n);
    }
    public int update_floor(){
        return get_floor();
    }
    public void set_reward(int n){
        reward = n;
    }
    public int get_reward(){
        return reward;
    }

}

class normal_ladder extends ladder{

    public normal_ladder(int n) {
        super(n);
        set_reward(2);
    }

    @Override
    public int update_score(int n) {
        return n+get_reward();
    }
    @Override
    public int update_floor(){
        return 12;
    }
    @Override
     public String toString(){
        return "Ladder floor";
    }
     
}

class Elevator extends ladder{

    public Elevator(int n) {
        super(n);
        set_reward(4);
    }

    @Override
    public int update_score(int n){
        return n+get_reward();
    }
    @Override
    public int update_floor(){
        return 10;
    }
    
    @Override
     public String toString(){
        return "Elevator floor";
    }
}

class normal_snake extends snake_floor{

    public normal_snake(int n) {
        super(n);
        set_cost(2);
    }
    
    @Override
    public int update_score(int n){
        return n-get_cost();
    }
    public int update_floor(){
        return 1;
    }

    @Override
    public String toString(){
        return "Normal Snake floor";
    }
}

class King_cobra extends snake_floor{

    public King_cobra(int n) {
        super(n);
        set_cost(4);
    }

    @Override
    public int update_score(int n){
        return n-get_cost();
    }
    @Override
    public int update_floor(){
        return 3;
    }
    @Override
    public String toString(){
        return "King Cobra floor";
    }
}