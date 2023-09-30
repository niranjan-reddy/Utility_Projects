package cyou.kayacorp.projects.utility;

import java.util.Scanner;

import static java.lang.System.exit;

public class NumberInputAdder {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        int total = 0;
        while(true) {
            System.out.print("Enter number:> ");
            int a = sc.nextInt();
            total += a;

            System.out.println("Total = " + total);
            if(a == 0)
                exit(0);
        }
    }
}