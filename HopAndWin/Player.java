package HopAndWin;

public class Player {

    private int moves;
    private int total;
    private Bucket<Toy> bucket;

    public Player(){
        moves = 0;
        total = 0;
        bucket = new Bucket<Toy>();
    }
    public void setMoves(int m) {
        this.moves = m;
    }
    public int getMoves() {
        return this.moves;
    }
    public int movesLeft(){
        return 5-this.moves;
    }
    public void addToy(Toy t){
        bucket.add(t);
    }
    public Bucket<Toy> getBucket(){
        return bucket;
    }
    public void set_Total(int i){
        this.total = i;
    }
    public int get_total() {
        return total;
    }
}

class Random_Generator{

    private String s;
    private int i;
    
    public int random_Integer(int min, int max){

        this.i = (int) ((Math.random() * (max - min)) + min);
        return i;
    }

    public String random_string(){
        s="";
        int n = random_Integer(3, 7);
        String range = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";

        for(int i = 0 ; i<n ; i++){

            int ind=0;
            while (true) {
                try {
                    ind = random_Integer(0, 50);
                    break;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
            s+=range.charAt(ind);
        }
        return s;
    }
    
}