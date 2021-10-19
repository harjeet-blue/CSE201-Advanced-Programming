
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.HashMap;

interface lecture_material{
    
    void lecture_details();
    void uploaded_by(int i);
    void time_of_upload();

}
interface grade_assessments{

    int MAX=1;
    void assessment_details();
    void ID(int i);
    boolean is_closed();
    void uploaded_by(int i);
    int get_id();
    public void close_this();
    String get_type();
    String get_assig_name();
    public int get_max_score();

}
class grade_ans{

    private int id;
    private int max_score;
    private boolean graded;
    private boolean closed;
    private boolean done;
    private int score;
    private String ansfile;
    private int instructor;

    public grade_ans(int i,int m){
        id = i;
        max_score=m;
        graded=false;
        closed=false;
        done = false;
    }
    boolean is_closed(){
        return closed;
    }
    void checked(){
        graded=true;
    }
    public void close_this(){
        this.closed=true;
    }
    boolean is_graded(){
        return graded;
    }
    boolean is_done(){
        return done;
    }
    void mark_done(){
        done = true;
    }
    void graded_by(int i){
        instructor = i;
    }
    void give_marks(int m){
        score =m;
    }
    void submit_ans(String s){
        ansfile = s;
    }
    int get_marks(){ return score; }
    String get_ans(){ return ansfile; }
    int get_graded_ins(){ return instructor; }
    int get_id(){ return id; }
    int get_max_score(){ return max_score; }
}
class slides implements lecture_material{

    private int no_of_slides;
    private String topic;
    private ArrayList<String> content;
    private Date time;
    private int instructor;

    Scanner sc = new Scanner(System.in);

    public slides(int i){
        content = new ArrayList<>();
        lecture_details();
        time_of_upload();
        uploaded_by(i);
    }

    public void lecture_details(){

        System.out.print("Enter the topic of slides : ");
        this.topic = sc.next();

        System.out.print("Enter no of slides : ");
        this.no_of_slides = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the content of the slides");

        for (int i = 1; i <= no_of_slides; i++) {
            System.out.print("Enter the content of slide "+ i+" : ");
            String s = sc.nextLine();
            content.add(s);
        }
    }

    public void time_of_upload(){
        this.time = new Date();
    }

    public void uploaded_by(int i) {
        instructor = i;
    }

    public int get_no_of_slides(){ return no_of_slides; }
    public String get_topic(){ return topic; }
    public Date get_time(){ return time; }
    public int get_instructor(){ return instructor; }
    public ArrayList<String> get_content(){ return content; }
}

class Lec_vidoes implements lecture_material{

    private String topic;
    private String name;
    private int instructor;
    private Date time;

    Scanner sc = new Scanner(System.in);
    
    public Lec_vidoes(int i){
        lecture_details();
        time_of_upload();
        uploaded_by(i);
    }

    public void lecture_details(){
        System.out.print("Enter the topic of the video : ");
        this.topic = sc.next();

        do{
            System.out.print("Enter the filename : ");
            this.name = sc.next();

        }while(!check_name(name));
    }

    public boolean check_name(String s){
        if(s.length()<5)return false;
        int l = s.length();
        if(s.substring(l-4).equals(".mp4"))
            return true;
        System.out.println("Wrong filename format");
        return false;
    }

    public void time_of_upload(){
        this.time = new Date();
    }

    public void uploaded_by(int i) {
        instructor = i;
    }

    public String get_file(){ return name; }
    public String get_topic(){ return topic; }
    public Date get_time(){ return time; }
    public int get_instructor(){ return instructor; }

}

class assignment implements grade_assessments{

    private int ID;
    private String name;
    private String type;
    private int max_score;
    private boolean closed ;
    private int upload_instr;

    public assignment(int id, int ins){
        type = "Assignment";
        assessment_details();
        closed=false;
        ID(id);
        uploaded_by(ins);
    }
    public void assessment_details(){
        Backpack.sc.nextLine();
        System.out.print("Enter the problem statement : ");
        this.name = Backpack.sc.nextLine();
        System.out.print("Enter the max marks : ");
        this.max_score = Backpack.sc.nextInt();

    }
    public void ID(int i){
        this.ID = i;
    }
    public boolean is_closed(){
        return this.closed;
    }
    public void close_this(){
        this.closed=true;
    }
    public void uploaded_by(int i ){
        this.upload_instr=i;
    }
    public String get_type(){ return type; }
    public int get_id(){ return this.ID; }
    public String get_assig_name(){ return this.name; }
    public int get_max_score(){ return this.max_score;}
    public int get_uploaded_inst(){ return this.upload_instr; }
}
class quiz implements grade_assessments{

    private int ID;
    private String name;
    private String type;
    private boolean closed;
    private int upload_instr;

    public quiz(int id, int ins){
        type = "Quiz";
        assessment_details();
        ID(id);
        closed = false;
        uploaded_by(ins);
    }

    public void assessment_details() {
        Backpack.sc.nextLine();
        System.out.print("Enter quiz question : ");
        this.name = Backpack.sc.nextLine();
    }
    public void ID(int i){
        this.ID = i;
    }
    public void close_this(){
        this.closed=true;
    }
    public boolean is_closed(){
        return this.closed;
    }

    public void uploaded_by(int i ){
        this.upload_instr=i;
    }
   
    public String get_type(){ return type; }
    public int get_id(){ return this.ID; }
    public String get_assig_name(){ return this.name; }
    public int get_max_score(){ return grade_assessments.MAX;}
    public int get_uploaded_inst(){ return this.upload_instr; }
   

}

interface member{
    
    void view_lecture_materials();
    void view_assessments();
    void view_comments();
    comments add_comments(int i, String s);
    void logout();
    String get_type();
    int get_id();

}
class Instructor implements member{

    private int ID;
    int counter = -1;
    private String type;

    public Instructor(int i){
        this.ID = i;
        type = "Instructor";
    }
    public String get_type(){ return type ; }
    public int get_id(){ return ID; }

    public slides add_Slides(){

        slides s= new slides(this.ID);
        return s;
    }

    public Lec_vidoes add_leVidoes(){
        Lec_vidoes lv = new Lec_vidoes(this.ID);
        return lv;
    }
    public assignment add_assignment(){
        assignment a = new assignment(++counter, this.ID);
        return a;
    }
    public quiz add_quiz(){
        quiz q = new quiz(++counter, this.ID);
        return q;
    }
    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public void view_lecture_materials(){

        for (slides s : classroom.slide) {
            System.out.println("Title : "+ s.get_topic());
            System.out.println("No of slides : "+ s.get_no_of_slides());

            for (int i = 0; i < s.get_no_of_slides(); i++) {
                System.out.println("Slide : "+i+" "+s.get_content().get(i));
            }
            System.out.println(form.format(s.get_time()));
            System.out.println("Uploaded by : "+ s.get_instructor()+"\n");
        }

        for (Lec_vidoes l : classroom.vidoes) {
            System.out.println("Title of video : "+ l.get_topic() +"\n"+"Videofile : "+ l.get_file());
            System.out.println(form.format(l.get_time()));
            System.out.println("Uploaded by : "+ l.get_instructor()+"\n");
        }
    }
    public void view_assessments(){
      
        for (assignment ga : classroom.assess_list) {
            if(ga.is_closed())continue;
            System.out.println("ID : "+ga.get_id() + "\nAssignment : "+ ga.get_assig_name()+"\nMax_marks : "+ga.get_max_score()+"\n");
        }
        System.out.println("................");
        for (quiz q : classroom.quiz_list) {
            if(q.is_closed())continue;
            System.out.println("ID : "+q.get_id() + "\nQuiz : "+ q.get_assig_name()+"\nMax_marks : "+q.get_max_score()+"\n");
        }
    }

    public void Grade_Assessments(int aid, int sid){

        student s = Backpack.SID.get(sid);
        for (grade_ans a : s.assess_list) {
            if(a.get_id()==aid){
                System.out.println("Submission : " + a.get_ans()+"\nMax Marks : "+a.get_max_score());
                System.out.print("Marks Scored : ");
                a.give_marks(Backpack.sc.nextInt());
                a.graded_by(this.ID);
                a.checked();
                break;
            }
        }
    }

    public void Close_Assessment(int aid ){

        for ( student st : Backpack.stu_list) {

            for( grade_ans ga : st.assess_list ){
                if(ga.get_id()==aid){
                    ga.close_this();
                    break;
                }
            }

        }
    }

    public comments add_comments(int id , String s){
        comments c = new comments(s, id);
        return c;
    }

    public void view_comments() {
        for (comments c : classroom.comm_list) {
            c.print_com();
        }
    }
    public void logout(){
        System.out.println( "Instructor : " + this.ID + " Logged out successfully ");
    }
    
}

class student implements member{

    private int ID;
    private String type;
    ArrayList<grade_ans> assess_list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public student(int i){
        this.ID=i;
        this.type = "Student";
    }

    public String get_type(){ return type; }
    public int get_id(){ return ID; }

    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public void view_lecture_materials(){

        for (slides s : classroom.slide) {
            System.out.println("Title : "+ s.get_topic());
            System.out.println("No of slides : "+ s.get_no_of_slides());

            for (int i = 0; i < s.get_no_of_slides(); i++) {
                System.out.println("Slide : "+i+" "+s.get_content().get(i));
            }
            System.out.println(form.format(s.get_time()) + "\n");
            System.out.println("Uploaded by : "+ s.get_instructor());
        }

        for (Lec_vidoes l : classroom.vidoes) {
            System.out.println("Title of video : "+l.get_topic() +"\n"+"Videofile : "+ l.get_file());
            System.out.println(form.format(l.get_time()) + "\n");
            System.out.println("Uploaded by : "+l.get_instructor());
        }
    }

    public void view_assessments(){
        
        for (grade_ans ga : assess_list) {
            if(ga.is_closed() || ga.is_done())continue;
            grade_assessments a = classroom.AID.get(ga.get_id());
            System.out.println("ID : "+a.get_id() +"\n" + a.get_type()+" : "+a.get_assig_name() +"\nMax_marks : "+a.get_max_score()+"\n");
        }
       
    }

    public void submit_assessments(){

        System.out.println("Pending Assessment : ");
        int f = 0;
        for (grade_ans ga : assess_list) {
            if(ga.is_closed() || ga.is_done() || ga.is_graded())continue;
            grade_assessments a = classroom.AID.get(ga.get_id());
            System.out.println("ID : "+a.get_id() +"\n" + a.get_type()+" : "+a.get_assig_name() +"\nMax_marks : "+a.get_max_score()+"\n");
            f++;
        }

        if(f==0){
            System.out.println(" NO pending assessments :");
            return;
        }
        System.out.print("Enter id : ");
        int id = sc.nextInt();
        grade_assessments a = classroom.AID.get(id);
        String ans;

        if(a.get_type().equals("Assignment")){

            do{
                System.out.print("Enter the filename of assignement : ");
                ans = sc.next();
            }while(!check_name(ans));

        }else{
            
            System.out.print("Enter the one word ans : ");
            ans = sc.next();
        }

        for (grade_ans ga : assess_list) {
            if(ga.get_id()==id){
                ga.submit_ans(ans);
                ga.mark_done();
                break;
            }
        }
    }

    public boolean check_name(String s){
        if(s.length()<5)return false;
        int l = s.length();
        if(s.substring(l-4).equals(".zip"))
            return true;
        System.out.println("Wrong filename format");
        return false;
    }

    public void view_grades(){
        System.out.println("Graded Assessments ");

        for (grade_ans ga : assess_list) {
            if(ga.is_graded() && !ga.is_closed()){
                System.out.println("ID : "+ga.get_id());
                System.out.println("Submission : "+ga.get_ans() +"\nMax marks : " +ga.get_max_score()+"\nMax scored : "+ga.get_marks() );
                System.out.println("Graded by : "+ga.get_graded_ins());
            }
        }
        System.out.println("---------------------");
        System.out.println("Ungraded Assessments ");

         for (grade_ans ga : assess_list) {
            if(ga.is_done() && !ga.is_graded() && !ga.is_closed())
                System.out.println("ID : "+ga.get_id()+"\nSubmission : "+ga.get_ans() +"        Max marks : " +ga.get_max_score()+"\n");
        }
    }
    public void view_comments() {
        for (comments c : classroom.comm_list) {
            c.print_com();
        }
    }

    public comments add_comments(int id , String s){
        comments c = new comments(s, id);
        return c;
    }

    public void logout(){
        System.out.println( "Student : " + this.ID + " Logged out successfully ");
    }

}

class classroom{

    static HashMap<Integer, grade_assessments> AID = new HashMap<>();
    static ArrayList<slides> slide = new ArrayList<>();
    static ArrayList<Lec_vidoes> vidoes = new ArrayList<>();
    static ArrayList<assignment> assess_list = new ArrayList<>();
    static ArrayList<quiz> quiz_list = new ArrayList<>();
    static ArrayList<comments> comm_list = new ArrayList<>();

    public static void Add_lect_material(Instructor m){

        System.out.println("1. Add Lecture Slides \n2. Add Lecture Videos");
        int c = Backpack.sc.nextInt();
        if(c==1){

            slides s = m.add_Slides();
            slide.add(s);

        }else{
            Lec_vidoes lv = m.add_leVidoes();
            vidoes.add(lv);
        }

    }

    public static void Add_assessments(Instructor m){
        System.out.println("1. Add assignment\n2. Add quiz");
        int c = Backpack.sc.nextInt();
        if(c==1){
            assignment a = m.add_assignment();
            AID.put(a.get_id(), a);
            assess_list.add(a);
            for (student st : Backpack.stu_list) {
                st.assess_list.add(new grade_ans(a.get_id(), a.get_max_score()));
            }
            
        }else{
            quiz q = m.add_quiz();
            AID.put(q.get_id(), q);
            quiz_list.add(q);
            for (student st : Backpack.stu_list) {
                st.assess_list.add( new grade_ans(q.get_id(), 1));
            }
        }

    }

    public static void view_material(member m){
        m.view_lecture_materials();
    }

    public static void view_assign_quiz(member m){
        m.view_assessments();
    }
    public static void Add_Com(member m){
        comments c = m.add_comments(m.get_id(),m.get_type());
        comm_list.add(c);
    }
    public static void View_Com(member m) {
        m.view_comments();
    }
    public static void Log_out(member m){
        m.logout();
    }

    public static void grade(Instructor m){

        m.view_assessments();
        System.out.print("Enter assessments ID : ");
        int aid = Backpack.sc.nextInt();
        int f = 0 ;
        for ( student st : Backpack.stu_list) {
            for( grade_ans ga : st.assess_list ){
                if(ga.get_id()==aid && !ga.is_graded() ){
                    System.out.println(st.get_id());
                    f++;
                    break;
                }
            }
        }
        if(f==0){
            System.out.println("Either no one has submitted yet or everyone's work has been graded");
            return;
        }
        System.out.print(" choose student id : ");
        int sid = Backpack.sc.nextInt();
        m.Grade_Assessments(aid, sid);
    }

    public static void close(Instructor m ){

        System.out.println("List of open assessments :- ");
        int f = 0;
        for (assignment ga : classroom.assess_list) {
            if(ga.is_closed())continue;
            System.out.println("ID : "+ga.get_id() + "\nAssignment : "+ ga.get_assig_name()+"\nMax_marks : "+ga.get_max_score()+"\n");
            f++;
        }
        System.out.println("................");
        for (quiz q : classroom.quiz_list) {
            if(q.is_closed())continue;
            System.out.println("ID : "+q.get_id() + "\nQuiz : "+ q.get_assig_name()+"\nMax_marks : "+q.get_max_score()+"\n");
            f++;
        }
        if(f==0){
            System.out.println(" NO open  assessments ");
            return;
        }
        System.out.print("Enter the id of assessment to close : ");
        int id = Backpack.sc.nextInt();
        grade_assessments a =  AID.get(id);
        a.close_this();
        m.Close_Assessment(id);

    }

  

    public static void submit(student s) {
        s.submit_assessments();
    }
    public static void see_grades(student s){
        s.view_grades();
    }

}


public class Backpack{

    static Scanner sc = new Scanner(System.in);
    static ArrayList<student> stu_list = new ArrayList<>();
    static ArrayList<Instructor> ins_list = new ArrayList<>();
    static HashMap<Integer,student> SID = new HashMap<>();
    static HashMap<Integer,Instructor> IID = new HashMap<>();

    public static void main(String[] args) {

        Instructor I1 = new Instructor(0);
        Instructor I2 = new Instructor(1);
        ins_list.add(I1);
        ins_list.add(I2);
        IID.put(0, I1);
        IID.put(1, I2);
        student s1 = new student(0);
        student s2 = new student(1);
        student s3 = new student(2);
        stu_list.add(s1);
        stu_list.add(s2);
        stu_list.add(s3);
        SID.put(0, s1);
        SID.put(1, s2);
        SID.put(2, s3);
        int c;

        do{
            System.out.println("\nWelcome to Backpack\n1. Enter as instructor\n2. Enter as student\n3. Exit");
            c = sc.nextInt();

            if(c==1){
                System.out.println("Instructors ids : - \n0\n1");
                System.out.print("Enter instructor id : ");
            
                int id = sc.nextInt();
                instructor_menu(id);
            }
            else if(c==2){

                System.out.println("Instructors ids : - \n0\n1\n2");
                System.out.print("Enter student id : ");
            
                int id = sc.nextInt();
                student_menu(id);
            }
            else System.exit(0);

        }while(c!=3);

    }

    public static void instructor_menu(int id) {
        int c;
        do{
            System.out.println("\nWelcome Instructor : " + id);
            System.out.println("INSTRUCTOR MENU");
            System.out.println("1. Add lecture materials\n2. Add grade assessments\n3. View lecture materials");
            System.out.println("4. View assessments\n5. Grade assessments\n6. Close assessments\n7. View comments");
            System.out.println("8. Add comments\n9. Logout\nENTER APPROPRIATE OPTION");
            c = sc.nextInt();
            inst_helper(c, id);

        }while(c!=9);
        
    }
    public static void inst_helper(int c , int id) {

        Instructor ins = IID.get(id);

        switch (c) {
            case 1: classroom.Add_lect_material(ins);
                break;
            case 2: classroom.Add_assessments(ins);
                break;
            case 3: classroom.view_material(ins);
                break;
            case 4: classroom.view_assign_quiz(ins);
                break;
            case 5: classroom.grade(ins);
                break;
            case 6: classroom.close(ins);
                break;
            case 7: classroom.View_Com(ins);
                break;
            case 8: classroom.Add_Com(ins);
                break;
            case 9: classroom.Log_out(ins);
                break;
            default:
                break;
        }
    }

    public static void student_menu(int id) {
        int c ;
        do{
            System.out.println("\nWelcome Student : "+id);
            System.out.println("STUDENT MENU");
            System.out.println("1. View lecture materials\n2. View assessments\n3. Submit assessments\n4. View Grades");
            System.out.println("5. View comments\n6. Add comments\n7. Logout\nENTER CORRECT OPTION");
            c = sc.nextInt();
            student_helper(c,id);

        }while(c!=7);
    }

    public static void student_helper(int c, int id ){

        student s = SID.get(id);

        switch (c) {
            case 1: classroom.view_material(s);
                break;
            case 2: classroom.view_assign_quiz(s);
                break;
            case 3: classroom.submit(s);
                break;
            case 4: classroom.see_grades(s);
                break;
            case 5: classroom.View_Com(s);
                break;
            case 6: classroom.Add_Com(s);
                break;
            case 7: classroom.Log_out(s);
                break;
            default:
                break;
        }
    }
    
}

class comments {

    private String comment;
    private Date time;
    private String who;
    private int id;
    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

    public comments(String s, int id){
        set_date();
        Backpack.sc.nextLine();
        System.out.print("Enter comment : ");
        this.comment = Backpack.sc.nextLine();
        this.who = s;
        this.id = id;
    }
    public void print_com(){
        System.out.println(comment);
        System.out.println(form.format(this.time));
        System.out.println(who + " :  id : " + id);
    }
    public void set_date(){
        time = new Date();
    }
    public int get_id(){
        return id;
    }
    public Date get_date(){
        return time;
    }
    public String get_tyep(){
        return who;
    }
    public String get_comment(){
        return comment;
    }
}
