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
        System.out.println("Vaccine: "+name+", no of doses: "+doses+", Gap between doses: "+gap);
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

    Citizen(int a, String id){

        System.out.print("Enter name : ");
        this.name = sc.next();
        this.age = a;
        this.ID = id;
        this.next=0;
        this.doses=0;
        this.Vaccine_type="none";
        Vaccination_status="REGISTERED";
        System.out.println("Citizen name: "+name+", Age:"+age+", ID: "+ID+", Status: "+Vaccination_status);
    }

    void set_vac_status(Vaccine v, int d){

        this.Vaccine_type = v.name;
        if(v.gap==0)this.next++;
        else this.next=d+v.gap;
        this.doses++;
        if(0<this.doses && this.doses<v.doses)this.Vaccination_status="PARTIALLY VACCINATED";
        else if(this.doses==v.doses)this.Vaccination_status="FULLY VACCINATED";

    }

    void print_all(){

        System.out.println("Name: "+name);
        System.out.println("Vaccination status: "+Vaccination_status);
        System.out.println("Vaccine: "+Vaccine_type);
        System.out.println("No of doses: "+doses);
        if(Vaccination_status.equals("PARTIALLY VACCINATED")){
                System.out.println("Next dose due date: "+next);
        }
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
        System.out.println("Hospital name: "+name+", Pin: "+pincode+", Unique ID: "+ID);
    }
    
    void set_pin(){
        do{
            System.out.print("Enter PinCode : ");
            this.pincode = sc.next();
            if(pincode.length()!=6)System.out.println("Wrong Input");
        }while(pincode.length()!=6);

    }
    String Add_slot(ArrayList<String> v_name){
        System.out.println();
        for(int i =0; i<v_name.size();i++){
            System.out.println(i+".  "+v_name.get(i));
        }
        System.out.print("Select Vaccine: ");
        int i = sc.nextInt();

        Slot s = new Slot(v_name.get(i));
        slot.add(s);
        System.out.println("Slot added \n"+"Hostpita name: "+name);
        System.out.println("ID: "+ID+", Day: "+s.day+", Quantity: "+s.quatity+", Vccine: "+s.vaccine);
        return s.vaccine;
    }

    Slot slot_booking(int next, String type, int o){

        int i = 0;
        int flag=1;
        for(Slot s: slot){
            if(s.day>=next && s.quatity>0){

                if(o==0 && (type.equals("none") || type.equals(s.vaccine))){
                    System.out.println("Slot: "+i+", Day->"+s.day+", Vaccine: "+s.vaccine+", Quantity: "+s.quatity);
                    flag++;
                }
                else if(o==1 && type.equals(s.vaccine)){
                    System.out.println("Slot: "+i+", Day->"+s.day+", Vaccine: "+s.vaccine+", Quantity: "+s.quatity);
                    flag++;
                }
            }
            i++;
        }
        if(flag==1){
            System.out.println("\nNo Slots available for you");
            return null;
        }
        System.out.print("\nEnter the slot no: ");
        int a = sc.nextInt();
        Slot s = slot.get(a);
        s.quatity--;
        return s;
    }

    void print_all_slots(){
        if(slot.size()==0 || slot==null){
            System.out.println("No slots available at this moment ");
            return;
        }
        for(Slot s : slot){
            System.out.println("Day-> "+s.day+", Vaccine: "+s.vaccine+", Quantity: "+s.quatity);
        }
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
            System.out.print("Enter Day no : ");
            this.day = sc.nextInt();
            if(day<0)System.out.println("Wrong input");

        }while(day<0);
        
    }
    void doses(){

        do{
            System.out.print("Enter no of Doses : ");
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
    static boolean v1,v2,v3,v4,v5,v6,v7;

    public static void main(String[] args){
        int c;
        do{
            c = dashboard();
            switch (c) {
                case 1: add_vaccine(); v1=true;
                    break;
                case 2: register_hopital(); v2 =true;
                    break;
                case 3: register_citizen(); v3 = true;
                    break;
                case 4: if(check_dependency(4)){ add_slots(); v4 = true; }
                        else System.out.println("Either viccines have not been added yet or No Hospital has registered");
                    break;
                case 5: if(check_dependency(5)){ book_slot(); v5 = true; }
                        else System.out.println("Either slots have not been added by hospitals or No citizen has registered");
                    break;
                case 6: if(check_dependency(6)){ print_all(); v6 = true; }
                        else System.out.println("No slots have been added yet");
                    break;
                case 7: if(check_dependency(7)){ citize_status(); v7 = true; }
                        else System.out.println("NO citizen has registerd yet");
                    break;
                default: System.out.println("Thanks");
                    break;
            }
            System.out.println();
        }while(c!=8);

    }

    public static boolean check_dependency(int c) {
        if(c==4) return v1&&v2;
        else if(c==5) return v4 && v3;
        else if(c==6) return v4;
        return v3;
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
        String id;
        do{ 
            System.out.print("Enter Unique ID: ");
            id = sc.next();
            if(id.length()!=12)System.out.println("Invalid ID");
        }while(id.length()!=12);

        if(PID.containsKey(id)){
            System.out.println("Citizen already registered with this ID");
            return;
        }
        Citizen c = new Citizen(a,id);
        CID.put(c.ID, c);
    }

    public static void add_slots() {
        Hospital h;
        do{
            System.out.print("Enter hospital ID: ");
            int id = sc.nextInt();
            h = HID.get(id);
            if(h==null)System.out.println("Wrong ID");
        }while(h==null);

        System.out.print("Enter the no of slots :");
        int n = sc.nextInt();
        while(n-->0){
            String v = h.Add_slot(v_name);
            if(VID.get(v)==null)VID.put(v, new HashSet<>());
            VID.get(v).add(h.ID);
        }
    }

    public static void book_slot() {
        Citizen c;
        do{
            System.out.print("Enter Patient Unique ID: ");
            String id = sc.next();
            c = CID.get(id);
            if(c==null)System.out.println("Wrong ID");
        }while(c==null);
        if(c.Vaccination_status.equals("FULLY VACCINATED")){
            System.out.println("You are fully vaccinated");
            return;
        }
        int x;
        do{
            System.out.println("1. Search by area \n2. Search by vaccine \n3. Exit ");
            x= sc.nextInt();
            if(x>3 || x<1)System.out.println("Wrong input");
        }while(x>3 || x<1);
        Slot s;
        if(x==1)s = search_by_area(c.next, c.Vaccine_type);
        else if(x==2) s = search_by_vaccine(c.next, c.Vaccine_type);
        else return;
        if(s==null)return;
        c.set_vac_status(vaccine.get(s.vaccine),s.day);
        System.out.println(c.name+" vaccinated with "+s.vaccine);
    }

    public static Slot search_by_area(int next,String type){

        String p;
        do{
            System.out.print("Enter the pincode: ");
            p = sc.next();
            if(PID.get(p)==null)System.out.println("Wrong pincode");
        }while(PID.get(p)==null);

        System.out.println("List of hospitals in that area :");
        for(Integer id: PID.get(p)){
            Hospital h = HID.get(id);
            System.out.println("ID: "+h.ID+", Hospital name: "+h.name);
        }   
        
        System.out.print("Enter hospital ID: ");
        int id = sc.nextInt();
        Hospital h = HID.get(id);
        return h.slot_booking(next,type,0);
    }

    public static Slot search_by_vaccine(int next, String type) {
        String v;
        do{
            System.out.print("Enter the vaccine: ");
            v = sc.next();
            if(VID.get(v)==null)System.out.println("Wrong Vaccine name");
        }while(VID.get(v)==null);

        System.out.println("List of hospitals: ");
        for(Integer id: VID.get(v)){
            Hospital h = HID.get(id);
            System.out.println("ID: "+h.ID+" Hospital name: "+h.name);
        }
        System.out.print("\nEnter hospital ID: ");
        int id = sc.nextInt();
        Hospital h = HID.get(id);
        return h.slot_booking(next, type, 1);
    }
    public static void print_all() {
        Hospital h;
        do{
            System.out.print("Enter hospital ID: ");
            int id = sc.nextInt();
            h = HID.get(id);
            if(h==null)System.out.println("Wrong ID");
        }while(h==null);
        h.print_all_slots();
    }

    public static void citize_status() {
        Citizen c;
        do{
            System.out.print("Enter Patient Unique ID: ");
            String id = sc.next();
            c = CID.get(id);
            if(c==null)System.out.println("Wrong ID");
        }while(c==null);
        c.print_all();
    }

}