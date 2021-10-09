import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;

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
        print();
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

    void print(){
        System.out.println("Vaccine: "+name+" no of doses: "+doses+" Gap between doses: "+gap);
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

    Citizen(int a){

        System.out.print("Enter name : ");
        this.name = sc.next();
        this.age = a;
        set_id();
        this.next=0;
        this.doses=0;
        this.Vaccine_type="none";
        Vaccination_status="RESITERED";
        System.out.println("Citizen name: "+name+" Age:"+age+" ID: "+ID);
    }
    void set_id(){
        do {
            System.out.print("Enter ID : ");
            this.ID = sc.next();
            if(ID.length()!=12)System.err.println("Wrong input");
        } while (ID.length()!=12);
    }

    void set_vac_status(Vaccine v){

        this.Vaccine_type = v.name;
        this.next+=v.gap;
        this.doses++;
        if(0<this.doses && this.doses<v.doses)this.Vaccination_status="PARTIALLY VACCINATED";
        else if(this.doses==v.doses)this.Vaccination_status="FULLY VACCINATED";

    }
}

class Hospital{

    String name;
    String pincode;
    int ID;
    ArrayList<Slot> slot = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    Hospital(int id){

        System.out.print("Enter Hospital name : ");
        this.name = sc.next();
        set_pin();
        this.ID =id;
        System.out.println("Hospital name: "+name+" Pin: "+pincode+" Unique ID: "+ID);
    }
    
    void set_pin(){
        do{
            System.out.print("Enter PinCode : ");
            this.pincode = sc.next();
            if(pincode.length()!=6)System.out.println("Wrong Input");
        }while(pincode.length()!=6);

    }
    String Add_slot(ArrayList<String> v_name){

        for(int i =0; i<v_name.size();i++){
            System.out.println(i+" "+v_name.get(i));
        }
        System.out.println("Select Vaccine: ");
        int i = sc.nextInt();

        Slot s = new Slot(v_name.get(i));
        slot.add(s);
        System.out.println("Slot added ");
        System.out.println("Hostpita name: "+ID+" Day: "+s.day+" Quantity: "+s.quatity+" Vccine: "+s.vaccine);
        return s.vaccine;
    }

    String slot_booking(int next, String type){
        int i = 1;
        for(Slot s: slot){
            if(s.day>next && s.quatity>0 && (type.equals("none") || type.equals(s.vaccine))){
                System.out.println(i+" Day->"+s.day+" Vaccine: "+s.vaccine+" Quantity: "+s.quatity);
                i++;
            }
        }
        if(i==1){
            System.out.println("No Slots available for u");
            return "no";
        }
        System.out.print("Enter the slot no: ");
        int a = sc.nextInt();
        Slot s = slot.get(--a);
        s.quatity--;
        return s.vaccine;
    }
}

class Slot{

    int day;
    int quatity;
    String vaccine;
    Scanner sc = new Scanner(System.in);
    
    Slot(String s){
        day();
        doses();
        this.vaccine = s;
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
    
}

public class COWIN{

    public static Scanner sc = new Scanner(System.in);
    static ArrayList<String> v_name = new ArrayList<>();
    static HashMap<Integer,Hospital> HID = new HashMap<>();
    static HashMap<String,Citizen> CID = new HashMap<>();
    static HashMap<String,ArrayList<Integer>> PID = new HashMap<>();
    static HashMap<String, HashSet<Integer>> VID = new HashMap<>();
    static HashMap<String,Vaccine> vaccine = new HashMap<>();

    static int give_id=100000;

    public static void main(String[] args) {

        int c;
        do{
            c = dashboard();
            switch (c) {
                case 1: add_vaccine();
                    break;
                case 2: register_hopital();
                    break;
                case 3: register_citizen();
                    break;
                case 4: add_slots();
                    break;
                case 5: book_slot();
                    break;
                default:
                    break;
            }
        }while(c!=8);

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
        vaccine.put(v.name,v);
        v_name.add(v.name);
    }

    public static void register_hopital() {
        give_id++;
        Hospital h = new Hospital(give_id);
        HID.put(give_id, h);
        if(PID.get(h.pincode)==null)PID.put(h.pincode, new ArrayList<>());
        PID.get(h.pincode).add(h.ID);
    }

    public static void register_citizen() {
        System.out.print("Enter age : ");
        int a = sc.nextInt();
        if(a<18){
            System.out.println("Only 18 and above are allowed");
            return;
        }
        Citizen c = new Citizen(a);
        CID.put(c.ID, c);
    }

    public static void add_slots() {
        System.out.print("Enter hospital ID: ");
        int id = sc.nextInt();
        Hospital h = HID.get(id);
        System.out.print("Enter the no of slots :");
        int n = sc.nextInt();
        while(n-->0){
            String v = h.Add_slot(v_name);
            if(VID.get(v)==null)VID.put(v, new HashSet<>());
            VID.get(v).add(h.ID);
        }
    }

    public static void book_slot() {
        
        System.out.println("Enter Patient Unique ID: ");
        String id = sc.next();
        Citizen c = CID.get(id);
        if(c.Vaccination_status.equals("FULLY VACCINATED")){
            System.out.println("You are fully vaccinated");
            return;
        }
        int x;
        do{
            System.out.println("1. Search by area \n 2. Search by pincode \n 3. Exit ");
            x= sc.nextInt();
            if(x>3 || x<1)System.out.println("Wrong input");
        }while(x>3 || x<1);
        String v;
        if(x==1)v = search_by_area(c.next, c.Vaccine_type);
        else if(x==2) v = search_by_pincode(c.next, c.Vaccine_type);
        else return;
        c.set_vac_status(vaccine.get(v));

    }

    public static String search_by_area(int next,String type){
        System.out.print("Enter the pincode: ");
        String p = sc.next();
        System.out.println("List of hospitals in that area :");
        for(Integer id: PID.get(p)){
            Hospital h = HID.get(id);
            System.out.println("ID: "+h.ID+" Hospital name: "+h.name);
        }   
        
        System.out.print("Enter hospital ID: ");
        int id = sc.nextInt();
        Hospital h = HID.get(id);
        return h.slot_booking(next,type);
    }

    public static String search_by_pincode(int next, String type) {
        System.out.print("Enter the vaccine: ");
        String v = sc.next();
        System.out.println("List of hospitals: ");
        for(Integer id: VID.get(v)){
            Hospital h = HID.get(id);
            System.out.println("ID: "+h.ID+" Hospital name: "+h.name);
        }
        System.out.print("Enter hospital ID: ");
        int id = sc.nextInt();
        Hospital h = HID.get(id);
        return h.slot_booking(next, type);
    }
}