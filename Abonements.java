import java.util.Scanner;

public class Abonements {
    public static void abonementuIzvele() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Izvelies abonementu:");
        System.out.println("1. GET FIT");
        System.out.println("2. GET CORE");
        System.out.println("3. GET CORE ULTRA");

        int izvele = scanner.nextInt();
        switch (izvele) {
            case 1:
                System.out.println("GET FIT");
                System.out.println();
                System.out.println("20 eiro/menesi");
                System.out.println("+ Zema maksa");
                System.out.println("+ Pieejams sporta zales aprikojums");
                System.out.println("- Nav pieejama sauna");
                
                break;
            case 2:
                System.out.println("GET CORE");
                System.out.println();
                System.out.println("+ Izdevigaka vertiba");
                System.out.println("+ 32 eiro menesi");
                System.out.println("+ Grupu/Individualie trenini - kalistenika vai klinsu kapsana");
                System.out.println("+ Pieejams viss aprikojums");
                System.out.println("+ Pieejama sauna");
            case 3:
                System.out.println("GET CORE ULTRA");
                System.out.println();
                System.out.println("+ 50 eiro menesi");
                System.out.println("+ Grupu/Individualie trenini - GAN kalistenika, GAN klinsu kapsana");  
                System.out.println("+ Pieejams viss aprikojums");
                System.out.println("+ Pieejama sauna");

            default:
                break;
        }
    }
    
}