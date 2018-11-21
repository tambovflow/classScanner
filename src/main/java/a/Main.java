package a;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        ClassScan cs = new ClassScan();
        System.out.println("Enter ClassName:");
        try{
            Scanner sc = new Scanner(System.in);
            String st = sc.nextLine();
            Class cl = Class.forName(st);
            System.out.println(cs.scan(cl));


        }catch (ClassNotFoundException e){
            System.out.println("Class not found, please try to input valid java class name like this: java.lang.String");
            Main.main(args);
        }
    }
}
