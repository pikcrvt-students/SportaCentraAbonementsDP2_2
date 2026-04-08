import java.util.*;

public class Main {

    public static int Registresana() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SAKUMS");
        System.out.println();
        System.out.println("1. Registresana");
        System.out.println("2. Pislegasanas");
        System.out.println("3. Iziet");
        System.out.println();
<<<<<<< HEAD

        int regIzvele = scanner.nextInt();

        switch (regIzvele) {
            case 1:
                System.out.println("Ka jus gribat registreties?");
                System.out.println("1. Klients");
                System.out.println("2. Treneris");

                int lietIzvele = scanner.nextInt();

                switch (lietIzvele) {
                    case 1:
                        Lietotaji.klientuRegistresana();
                        break;
                    case 2:
                        Treneri.treneruRegistresana();
                        break;
                }
                break;
=======
        System.out.println("Izveleties darbibu:");

        int izvele1 = scanner.nextInt();
        Lietotaji lietotaji = new Lietotaji();
        Treneri treneri = new Treneri();
        treninuPlani treninuPlani = new treninuPlani();
        switch(izvele1) {
            
                        case 1:
                            System.out.println("Ka jus gribat registreties?");
                            System.out.println("1. Klients");
                            System.out.println("2. Treneris");
                            int subsubIzvele = scanner.nextInt();
                            switch(subsubIzvele) {
                                case 1:
                                    Lietotaji.klientuRegistresana();
                                    System.out.println("1. Atgriezties");
                                    System.out.println("2. Iziet");
            
                                    int subIzvele2 = scanner.nextInt();
                                    switch(subIzvele2) {
                                        case 1:
                                            main(args);
                                
                                        case 2:
                                            break;
                                    }
                                    break;
                                case 2:
                                    Treneri.treneruRegistresana();
                                    System.out.println("1. Atgriezties");
                                    System.out.println("2. Iziet");
            
                                    int subIzvele3 = scanner.nextInt();
                                    switch(subIzvele3) {
                                        case 1:
                                            main(args);
                                            break;
                                        case 2:
                                            break;
                                    }
                                    break;
                            }
                            
                            break;
>>>>>>> 71872e3 (Izveidotas 11 funkcijas)
            case 2:
                System.out.println("Ka jus gribat pieslegties?");
                System.out.println("1. Klients");
                System.out.println("2. Treneris");

                int pieslegIzvele = scanner.nextInt();

                switch (pieslegIzvele) {
                    case 1:
<<<<<<< HEAD
                        Lietotaji.loadKlientiFromFile();
                        Lietotaji.klientuPieslegsanas();
=======
                        main(args);
                        break;
                    case 2:
                        break;
                }
                break;
            case 3:
                //treneruIzvele();
                System.out.println("1. Atgriezties");
                System.out.println("2. Iziet");

                int subIzvele6 = scanner.nextInt();
                switch(subIzvele6) {
                    case 1:
                        main(args);
                        break;
                    case 2:
                        break;
                }
                break;
            case 4:
                //abonementuIzvele();
                System.out.println("1. Atgriezties");
                System.out.println("2. Iziet");

                int subIzvele7 = scanner.nextInt();
                switch(subIzvele7) {
                    case 1:
                        main(args);
                        break;
                    case 2:
                        break;
                }
                break;
            case 5:
                //profilaRedigesana();
                System.out.println("1. Atgriezties");
                System.out.println("2. Iziet");

                int subIzvele8 = scanner.nextInt();
                switch(subIzvele8) {
                    case 1:
                        main(args);
>>>>>>> 71872e3 (Izveidotas 11 funkcijas)
                        break;
                    case 2:
                        Treneri.loadTreneriFromFile();
                        Treneri.treneruPieslegsanas();
                        break;
                }
                break;

<<<<<<< HEAD
            case 3:
=======
            case 6:
                treninuPlani.treninuPlanaIevade();
                System.out.println("1. Atgriezties");
                System.out.println("2. Iziet");

                int subIzvele9 = scanner.nextInt();
                switch(subIzvele9) {
                    case 1:
                        main(args);
                        break;
                    case 2:
                        break;
                }
                break;
            case 7:
>>>>>>> 71872e3 (Izveidotas 11 funkcijas)
                System.out.println("Uz redzēšanos!");
                break;
        }

        return regIzvele; 
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int regIzvele = Registresana();

        if (regIzvele == 1) {

            System.out.println("IZVELNE:");
            System.out.println("1. Mans konts");
            System.out.println("2. Izveleties treneri");
            System.out.println("3. Izveleties abonementu");
            System.out.println("4. Iemaksat naudu konta");
            System.out.println("5. Iziet");
            System.out.println();
            System.out.println("Izveleties darbibu:");

            int mainklientIzvele = scanner.nextInt();

            switch (mainklientIzvele) {
                case 1:
                    Lietotaji.mansKonts();
                    
                    System.out.println("Atpakal?");
                        System.out.println("1. Ja");
                        System.out.println("2. Ne");
                        int atpakalIzvele = scanner.nextInt();
                        switch(atpakalIzvele) {
                            case 1:
                                main(args); 
                                break;
                            case 2:
                                System.out.println("Uz redzesanos!");
                                break;
                        }
                    break;
                case 2:
                    Treneri.izveletiesTreneri();
                    System.out.println("1. Jā");
                    System.out.println("2. Nē");
                    int atpakalIzvele2 = scanner.nextInt();
                    switch(atpakalIzvele2) {
                            case 1:
                                main(args); 
                                break;
                            case 2:
                                System.out.println("Uz redzesanos!");
                                break;
                        }
                    break;
                case 3:
                    Abonements.abonementuIzvele();
                    System.out.println("Gribat atgriezties?");
                    System.out.println();
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele3 = scanner.nextInt();
                    switch(atpakalIzvele3) {
                            case 1:
                                main(args); 
                                break;
                            case 2:
                                System.out.println("Uz redzesanos!");
                                break;
                        }
                    break;
                case 4:
                    Lietotaji.naudasIemaksa();
                    int atpakalIzvele5 = scanner.nextInt();
                    switch(atpakalIzvele5) {
                            case 1:
                                main(args); 
                                break;
                            case 2:
                                System.out.println("Uz redzesanos!");
                                break;
                        }
                    break;
                case 5:
                    System.out.println("Uz redzesanos!");
                    break;
                default:
                    System.out.println("Nederiga izvele.");
                    break;
            }

        } else if (regIzvele == 2) {

            System.out.println("IZVELNE:");
            System.out.println("1. Mans konts");
            System.out.println("2. Pievienot treninu planu");
            System.out.println("3. Apskatit treneru sarakstu");
            System.out.println("4. Rediget savu profilu");
            System.out.println("5. Iziet");
            System.out.println();
            System.out.println("Izveleties darbibu:");

            int maintrenIzvele = scanner.nextInt();

            switch (maintrenIzvele) {
                case 1:
                    Lietotaji.mansKonts();
                     System.out.println("Atpakal?");
                        System.out.println("1. Ja");
                        System.out.println("2. Ne");
                        int atpakalIzvele = scanner.nextInt();
                        switch(atpakalIzvele) {
                            case 1:
                                main(args); 
                                break;
                            case 2:
                                System.out.println("Uz redzesanos!");
                                break;
                        }
                    break;
                case 2:
                    treninuPlani.treninuPlanaIevade();
                    break;
                case 3:
                    // treninuPlani.apskatitTreneruSarakstu();
                    break;
                case 4:
                    Treneri.redigetTerneraprofilaDatus();
                    break;
                case 5:
                    System.out.println("Uz redzesanos!");
                    break;
            }
        }
    }
}