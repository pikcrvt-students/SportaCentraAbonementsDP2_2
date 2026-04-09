import java.util.*;

public class Main {

    private static boolean isTrainer = false;

    public static int Registresana() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SAKUMS");
        System.out.println();
        System.out.println("1. Registresana");
        System.out.println("2. Pieslegasanas");
        System.out.println("3. Iziet");
        System.out.println();

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
                        isTrainer = true;
                        Treneri.treneruRegistresana();
                        break;
                }
                break;
            case 2:

                System.out.println("Ka jus gribat pieslegties?");
                System.out.println("1. Klients");
                System.out.println("2. Treneris");

                int pieslegIzvele = scanner.nextInt();

                switch (pieslegIzvele) {
                    case 1:
                        Lietotaji.loadKlientiFromFile();
                        Lietotaji.klientuPieslegsanas();
                        break;
                    case 2:
                        isTrainer = true;
                        Treneri.loadTreneriFromFile();
                        Treneri.treneruPieslegsanas();
                        break;
                }
                break;

            case 3:
                System.out.println("Uz redzēšanos!");
                break;
        }

        return regIzvele; 
    }

    public static void klientaIzvelne(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("IZVELNE:");

        System.out.println();

        System.out.println("1. Mans konts");
        System.out.println("2. Izveleties treneri");
        System.out.println("3. Izveleties abonementu");
        System.out.println("4. Apskatit abonementu");
        System.out.println("5. Iemaksat naudu konta");
        System.out.println("6. Iziet");
        System.out.println();
        System.out.println("Izveleties darbibu:");

        int mainklientIzvele = scanner.nextInt();

        switch (mainklientIzvele) {

            case 1:
                System.out.println("MANS KONTS");

                System.out.println();

                Lietotaji.mansKonts();
                
                System.out.println("Atpakal?");
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele = scanner.nextInt();
                    switch(atpakalIzvele) {
                        case 1:
                            klientaIzvelne(args);
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 2:
                System.out.println("IZVELETIES TRENERI");

                System.out.println();

                Treneri.izveletiesTreneri();
                System.out.println("1. Jā");
                System.out.println("2. Nē");
                int atpakalIzvele2 = scanner.nextInt();
                switch(atpakalIzvele2) {
                        case 1:
                            klientaIzvelne(args);
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 3:
                System.out.println("IZVELETIES ABONEMENTU");

                System.out.println();

                Abonements.abonementuIzvele();
                System.out.println("Gribat atgriezties?");
                System.out.println();
                System.out.println("1. Ja");
                System.out.println("2. Ne");
                int atpakalIzvele3 = scanner.nextInt();
                switch(atpakalIzvele3) {
                        case 1:
                            klientaIzvelne(args); 
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 4:
                System.out.println("NAUDAS IEMAKSA");

                System.out.println();

                Lietotaji.naudasIemaksa();
                System.out.println();
                System.out.println("Gribat atgriezties?");
                System.out.println();
                System.out.println("1. Ja");
                System.out.println("2. Ne");
                int atpakalIzvele5 = scanner.nextInt();
                switch(atpakalIzvele5) {
                        case 1:
                            klientaIzvelne(args); 
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 5:
                System.out.println("IEMAKSAT NAUDU KONTA");

                System.out.println();

                Lietotaji.naudasIemaksa();

                System.out.println();
                
                System.out.println("Gribat atgriezties?");
                System.out.println();
                System.out.println("1. Ja");
                System.out.println("2. Ne");
                int atpakalIzvele4 = scanner.nextInt();
                switch(atpakalIzvele4) {
                        case 1:
                            klientaIzvelne(args); 
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 6:
                System.out.println("Uz redzesanos!");
                break;

            default:
                System.out.println("Nederiga izvele.");
                break;
        }
    }

    public static void treneraIzvelne(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("IZVELNE:");

        System.out.println();

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
                System.out.println("MANS KONTS");

                System.out.println();

                Lietotaji.mansKonts();
                 System.out.println("Atpakal?");
                 System.out.println();
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele = scanner.nextInt();
                    switch(atpakalIzvele) {
                        case 1:
                            treneraIzvelne(args);
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 2:
                System.out.println("PIEVIENOT TRENINU PLANU");

                System.out.println();

                treninuPlani.treninuPlanaIevade();
                break;

            case 3:
                System.out.println("APSKATIT TRENINU PLANUS");

                System.out.println();

                treninuPlani.paradaTreninuPlanus();
                 System.out.println("Atpakal?");
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele2 = scanner.nextInt();
                    switch(atpakalIzvele2) {
                        case 1:
                            treneraIzvelne(args);
                            break;
                        case 2:
                            System.out.println("Uz redzesanos!");
                            break;
                    }
                break;

            case 4:
                System.out.println("REDIGET PROFILA DATUS");
                System.out.println();
                Treneri.redigetTerneraprofilaDatus();
                break;

            case 5:
                System.out.println("Uz redzesanos!");
                break;

        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pieslegIzvele = Registresana();

        if (!isTrainer) {
            klientaIzvelne(args);
        } else {
            treneraIzvelne(args);
        }
    }
}