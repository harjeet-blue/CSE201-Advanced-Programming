import java.util.Scanner;

class Vaccine{

    String name;
    int doses;
    int gap;
    Scanner sc = new Scanner(System.in);

    Vaccine(){

        System.out.print("Enter Vaccine Name : ");
        this.name = sc.next();
        System.out.print("Enter no of doses : ");
        this.doses = sc.nextInt();
        System.out.print("Enter gap between doses : ");
        this.gap = sc.nextInt();

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
    int ID;
    int pincode;
    Scanner sc = new Scanner(System.in);

    Hospital(int id){

        System.out.println("Enter Hospital name : ");
        this.name = sc.next();
        System.out.println("Enter PinCode : ");
        this.pincode = sc.nextInt();
        this.ID = id;
    }

}

class Slot{

    int day;
    int doses;
    String vaccine;
    int HID;

}

public class COWIN{

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        dashboard();
        Vaccine v = new Vaccine();
        System.out.println(v.name + v.doses + v.gap);
    }

    public static void dashboard() {

        int p;
        System.out.println("1. ADD VACCINE");
        System.out.println("2. REGISTER HOSPITAL");
        System.out.println("3. REGISTER CITIZEN");
        System.out.println("4. ADD SLOTS FOR VACCINATION");
        System.out.println("5. BOOK SLOTS FOR VACCINATION");
        System.out.println("6. LIST ALL SLOTS FOR VACCINATION");
        System.out.println("7. CHECK VACCINATION STATUS");
        System.out.println();

        do{
            System.out.print("choose the appropriate option : ");
            p=sc.nextInt();

        }while(p<1 || p>7);
        
    }

}