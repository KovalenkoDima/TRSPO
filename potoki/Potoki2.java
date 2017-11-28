package lab3;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Potoki2 {
    public static void main (String[]args){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Введите свое имя: ");
            final String line = in.nextLine();
            Thread thread = new Thread(() -> {
                while(true)
                    try {
                    Thread.sleep(3000);
                    System.out.println("Мое имя: "+line);
                    } catch (InterruptedException ex){
                        Logger.getLogger(Potoki2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            thread.start();
        }
    }
}
