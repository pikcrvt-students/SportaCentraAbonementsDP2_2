import java.util.*;

public class Main {

    private static boolean isTrainer = false;
    private static boolean isAdmin = false;
    private static boolean isKlietns = false;

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
                System.out.println("3. Administrators");

                int lietIzvele = scanner.nextInt();

                switch (lietIzvele) {
                    case 1:
                        Lietotaji.klientuRegistresana();
                        break;
                    case 2:
                        isTrainer = true;
                        Treneri.treneruRegistresana();
                        break;
                    case 3:
                        isAdmin = true;
                        Administratori.administratoruRegistresana();
                        break;

                    default:
                        System.out.println("Nederiga izvele! Meginiet velreiz.");
                }
                break;
            case 2:

                System.out.println("Ka jus gribat pieslegties?");
                System.out.println("1. Klients");
                System.out.println("2. Treneris");
                System.out.println("3. Administrators");

                int pieslegIzvele = scanner.nextInt();

                switch (pieslegIzvele) {
                    case 1:
                        isKlietns = true;
                        Lietotaji.loadKlientiFromFile();
                        Lietotaji.klientuPieslegsanas();
                        break;
                    case 2:
                        isTrainer = true;
                        Treneri.loadTreneriFromFile();
                        Treneri.treneruPieslegsanas();
                        break;
                    case 3:
                        isAdmin = true;
                        Administratori.administratoruPieslegsanas();
                        break;
            
                    default:
                        System.out.println("Nederiga izvele! Meginiet velreiz.");
                }
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
                        default:
                            System.out.println("Nederiga izvele! Meginiet velreiz.");
                            Main.klientaIzvelne(new String[0]);
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
                        default:
                            System.out.println("Nederiga izvele! Meginiet velreiz.");
                            Main.klientaIzvelne(new String[0]);
                    }
                break;

            case 4:
                System.out.println("APSKATIT ABONEMENTU");

                System.out.println();

                Abonements.apskatitManuabonementu();
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
                        default:
                            System.out.println("Nederiga izvele! Meginiet velreiz.");
                            Main.klientaIzvelne(new String[0]);
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
                System.out.println("Nederiga izvele! Meginiet velreiz.");
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
                System.out.println("Atpakal?");
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele3 = scanner.nextInt();
                    switch(atpakalIzvele3) {
                        case 1:
                            treneraIzvelne(args);
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
                System.out.println("Nederiga izvele! Meginiet veilreiz.");

        }
    }

    public static void administratoraIzvelne(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("IZVELNE:");

        System.out.println();

        System.out.println("1. Dzest trenera kontu");
        System.out.println("2. Dzest klienta kontu");
        System.out.println("3. Apskatit treneru sarakstu");
        System.out.println("4. Apskatit klientu sarakstu");
        System.out.println("5. Iziet");

        System.out.println();

        System.out.println("Izveleties darbibu:");

        int maintrenIzvele = scanner.nextInt();

        switch (maintrenIzvele) {

            case 1:
                System.out.println("DZEST TRENERA KONTU");

                System.out.println();

                //Administratori.dzestTreneraKontu();
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
                System.out.println("DZEST KLIENTA KONTU");

                System.out.println();

                //Administratori.dzestKlientaKontu();
                break;

            case 3:
                System.out.println("APSKATIT TRENERU SARAKSTU");

                System.out.println();

                //Administratori.paradaTrenerusarakstu();
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
                System.out.println("APSKATIT KLIENTU SARAKSTU");
                System.out.println();
                //Administratori.paradaKlientusarakstu();
                System.out.println("Atpakal?");
                    System.out.println("1. Ja");
                    System.out.println("2. Ne");
                    int atpakalIzvele3 = scanner.nextInt();
                    switch(atpakalIzvele3) {
                        case 1:
                            treneraIzvelne(args);
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
                System.out.println("Nederiga izvele! Meginiet veilreiz.");

        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pieslegIzvele = Registresana();

        if (!isTrainer) {
            klientaIzvelne(args);
        } else if (!isAdmin && !isKlietns) {
            treneraIzvelne(args);
        } else {
            treneraIzvelne(args);}
    }}
