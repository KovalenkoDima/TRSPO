package lab3;
import java.util.Scanner;

public class Potoki {
    public static class Counter extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 11; i++) {
              if (!Thread.interrupted()){
                  System.out.println(i);
                  try {
                      Thread.sleep(5000);
                  } catch (InterruptedException e) {
                      return;
                  }
               }
            }
        }
    }

    public static void main(String[]args){
        System.out.println("Для остановки нажмите <s>");
        Scanner scanner = new Scanner(System.in);
        Counter c = new Counter();
        c.start();
            if (scanner.next().equals("s")){
                c.interrupt();
            }
        }
    }
