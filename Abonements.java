import java.util.*;

public class Abonements {
    public static void abonementuIzvele() {

    Scanner scanner = new Scanner(System.in);

    System.out.println();

    System.out.println("ABONEMENTU IZVELE");
    System.out.println();

    System.out.println("Izvelies abonementu:");
    System.out.println("1. GET FIT");
    System.out.println("2. GET CORE");
    System.out.println("3. GET CORE ULTRA");

    int abonementaIzvele = scanner.nextInt();
    double summa = Lietotaji.getCurrentUserBalance();  

    switch (abonementaIzvele) {

        case 1:

            System.out.println("GET FIT");

            System.out.println();

            System.out.println("20 eiro/menesi");
            System.out.println("+ Zema maksa");
            System.out.println("+ Pieejams sporta zales aprikojums");
            System.out.println("- Nav pieejama sauna");

            System.out.println();

            System.out.println("Velaties iegadaties abonementu?");
            System.out.println("1. Jā");
            System.out.println("2. Nē");

            int iegadatiesIzvele = scanner.nextInt();

            if (iegadatiesIzvele == 1) {

                System.out.println("Izvelaties apmaksas veidu: ");

                System.out.println();

                System.out.println("1. MAKSAT NEDELA (5 eiro/ nedela)");
                System.out.println("2. MAKSAT MENESI (20 eiro/ menesis)");
                System.out.println("3. MAKSAT GADU (240 eiro/ gads)");
                System.out.println("4. Atpakal");

                int apmaksasVeids = scanner.nextInt();

                double paymentAmount = 0.0;

                switch (apmaksasVeids) {
                    case 1:
                        paymentAmount = 5.0;  
                        break;
                    case 2:
                        paymentAmount = 20.0;  
                        break;
                    case 3:
                        paymentAmount = 240.0;  
                        break;
                    case 4:
                        return;  
                    default:
                        System.out.println("Nepareiza izvele.");
                        return;
                }
                if (summa >= paymentAmount) {

                    summa -= paymentAmount;  

                    Lietotaji.updateCurrentUserBalance(summa);  

                    System.out.println("Apmaksa veiksmiga! Atlikusais atlikums: " + summa);

                } else {
                    System.out.println("Nepietiekams atlikums. Jusu summa: " + summa);
                }
            }
            break;
        
        case 2:
            System.out.println("GET CORE");

            System.out.println();

            System.out.println("32 eiro/menesi");
            System.out.println("+ Zema maksa");
            System.out.println("+ Pieejams sporta zales aprikojums");
            System.out.println("+ Pieejama sauna");

            System.out.println();

            System.out.println("Velaties iegadaties abonementu?");
            System.out.println("1. Jā");
            System.out.println("2. Nē");

            int iegadatiesIzvele2 = scanner.nextInt();

            if (iegadatiesIzvele2 == 1) {

                System.out.println("Izvelaties apmaksas veidu: ");

                System.out.println();

                System.out.println("1. MAKSAT NEDELA (5 eiro/ nedela)");
                System.out.println("2. MAKSAT MENESI (32 eiro/ menesis)");
                System.out.println("3. MAKSAT GADU (384 eiro/ gads)");
                System.out.println("4. Atpakal");

                int apmaksasVeids = scanner.nextInt();

                double paymentAmount = 0.0;

                switch (apmaksasVeids) {
                    case 1:
                        paymentAmount = 8.0;  
                        break;

                    case 2:
                        paymentAmount = 32.0;  
                        break;

                    case 3:
                        paymentAmount = 384.0;  
                        break;

                    case 4:
                        return; 

                    default:
                        System.out.println("Nepareiza izvele.");
                        return;
                }
                if (summa >= paymentAmount) {

                    summa -= paymentAmount;  

                    Lietotaji.updateCurrentUserBalance(summa);  

                    System.out.println("Apmaksa veiksmiga! Atlikusais atlikums: " + summa);

                } else {
                    System.out.println("Nepietiekams atlikums. Jusu summa: " + summa);
                }
            }
            break;

        case 3:

            System.out.println("GET CORE ULTRA");

            System.out.println();

            System.out.println("50 eiro/menesi");
            System.out.println("+ Zema maksa");
            System.out.println("+ Pieejams sporta zales aprikojums");
            System.out.println("+ Pieejama sauna");
            System.out.println("+ Pieejama masaža");

            System.out.println();

            System.out.println("Velaties iegadaties abonementu?");
            System.out.println("1. Ja");
            System.out.println("2. Ne");

            int iegadatiesIzvele3 = scanner.nextInt();

            if (iegadatiesIzvele3 == 1) {
                System.out.println("Izvelaties apmaksas veidu: ");

                System.out.println();

                System.out.println("1. MAKSAT NEDELA (5 eiro/ nedela)");
                System.out.println("2. MAKSAT MENESI (50 eiro/ menesis)");
                System.out.println("3. MAKSAT GADU (600 eiro/ gads)");
                System.out.println("4. Atpakal");

                int apmaksasVeids = scanner.nextInt();

                double paymentAmount = 0.0;

                switch (apmaksasVeids) {

                    case 1:
                        paymentAmount = 10.0;  
                        break;

                    case 2:
                        paymentAmount = 50.0;  
                        break;

                    case 3:
                        paymentAmount = 600.0;  
                        break;

                    case 4:
                        return;  

                    default:
                        System.out.println("Nepareiza izvele.");
                        return;
                }

                if (summa >= paymentAmount) {

                    summa -= paymentAmount;  

                    Lietotaji.updateCurrentUserBalance(summa);  

                    System.out.println("Apmaksa veiksmiga! Atlikusais atlikums: " + summa);

                } else {
                    System.out.println("Nepietiekams atlikums. Jusu summa: " + summa);
                }
            }
            break;

        default:
            System.out.println("Nepareiza izvele.");
            break;
    }

}

public static void apskatitManuabonementu() {

    String currentUserEmail = Lietotaji.getCurrentUserEmail();

    for(String klients : Lietotaji.klientuList()) {
        String[] klientInfo = klients.split(",");

        if (klientInfo[2].equals(currentUserEmail)) {

            System.out.println("Jusu abonements: " + klientInfo[4]);
            return;
        }
        else {
            System.out.println("Jums nav abonementa.");
            return;
        }
    }

    
}
}