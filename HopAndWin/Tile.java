package HopAndWin;

public class Tile{

    private Toy toy;
    private int ID;

    public Tile( int i, String name){
        this.toy = new Toy(name);
        this.ID = i;
    }

    public Toy softCopy(){
        return toy.clone();
    }
    
    public void setID(int i){
        this.ID = i;
    }
    public int getID(){
        return ID;
    }
    public void setToy(Toy t){
        this.toy = t;
    }
    public Toy getToy(){
        return toy;
    }
}

class Toy implements Cloneable{

    private String name;

    public Toy(String name){
        this.name = name;
    }

    public Toy clone(){
        try {
            Toy copy = (Toy) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}