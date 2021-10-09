import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Vaccine{

    String name;
    int doses;
    int gap;
    Scanner sc = new Scanner(System.in);

    Vaccine(){

        System.out.print("Enter Vaccine Name : ");
        this.name = sc.next();
        set_doses();
        if(this.doses!=1)
            set_gap();
    }

    void set_doses(){
        do {
            System.out.print("Enter no of doses : ");
            this.doses = sc.nextInt();
            if(doses<1)System.out.println("Wrong input");
        } while (doses<1);
    }

    void set_gap(){
        do {
            System.out.print("Enter gap between doses : ");
            this.gap = sc.nextInt();
            if(gap<0)System.out.println("Wrong input");
        } while (gap<0);
    }
}

class Citizen{

    String ID;
    String name;
    String Vaccination_status;
    String Vaccine_type;
    int age;
    int doses;
    int next;
    Scanner sc = new Scanner(System.in);

    Citizen(){

        System.out.println("Enter name : ");
        this.name = sc.next();
        System.out.println("Enter age : ");
        this.age = sc.nextInt();
        System.out.println("Enter ID");
        this.age = sc.nextInt();

    }
}

class Hospital{

    String name;
    int pincode;
    ArrayList<Slot> slot = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    Hospital(){

        System.out.println("Enter Hospital name : ");
        this.name = sc.next();
        System.out.println("Enter PinCode : ");
        this.pincode = sc.nextInt();
    }
    
}

class Slot{

    int day;
    int quatity;
    String vaccine;
    Scanner sc = new Scanner(System.in);
    
    Slot(){
        day();
        doses();
        name();
        
    }
    void day(){

        do{
            System.out.println("Enter Day no : ");
            this.day = sc.nextInt();
            if(day<0)System.out.println("Wrong input");

        }while(day<0);
        
    }
    void doses(){

        do{
            System.out.println("Enter no of Doses : ");
            this.quatity = sc.nextInt();
            if(quatity<0)System.out.println("Wrong input");

        }while(quatity<0);
    }
    void name(){
        System.out.println("Select Vaccine");
        
    }
}

public class COWIN{

    public static Scanner sc = new Scanner(System.in);

    static ArrayList<Vaccine> vaccine = new ArrayList<>();
    static ArrayList<String> v_name = new ArrayList<>();
    static HashMap<Integer,Hospital> HID = new HashMap<>();
    static HashMap<String,Citizen> CID = new HashMap<>();
    static HashMap<Integer,Integer> PID = new HashMap<>();
    static HashMap<String, Integer> VID = new HashMap<>();
    static int give_id=100000;

    public static void main(String[] args) {
        
       int c = dashboard();
       switch (c) {
            case 1: add_vaccine();
                break;
            case 2: register_hopital();
                break;
            default:
                break;
       }

    }

    public static int dashboard() {

        int p;
        System.out.println("1. ADD VACCINE");
        System.out.println("2. REGISTER HOSPITAL");
        System.out.println("3. REGISTER CITIZEN");
        System.out.println("4. ADD SLOTS FOR VACCINATION");
        System.out.println("5. BOOK SLOTS FOR VACCINATION");
        System.out.println("6. LIST ALL SLOTS FOR VACCINATION");
        System.out.println("7. CHECK VACCINATION STATUS");
        System.out.println("8. EXIT");
        System.out.println();

        do{
            System.out.print("choose the appropriate option : ");
            p=sc.nextInt();

        }while(p<1 || p>8);
        return p;
    }

    public static void add_vaccine() {
        Vaccine v = new Vaccine();
        vaccine.add(v);
        v_name.add(v.name);
    }

    public static void register_hopital() {
        Hospital h = new Hospital();
        HID.put(give_id++, h);
    }
}