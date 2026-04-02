import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("IZVELNE:");
        System.out.println("1. Registresana");
        System.out.println("2. Pieslegties");
        System.out.println("3. Izveleties treneri");
        System.out.println("4. Izveleties abonementu");
        System.out.println("5. Rediget savu profilu");
        System.out.println("6. Iemaksat naudu konta");
        System.out.println("7. Iziet");
        System.out.println();
        System.out.println("Izveleties darbibu:"); 

        int izvele1 = scanner.nextInt();
        Lietotaji lietotaji = new Lietotaji();
        Treneri treneri = new Treneri();
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
            case 2:
                //pieslegties();
                System.out.println("1. Atgriezties");
                System.out.println("2. Iziet");

                int subIzvele5 = scanner.nextInt();
                switch(subIzvele5) {
                    case 1:
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
                        break;
                    case 2:
                        break;
                }
                break;

            case 6:
                //naudasIemaksa();
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
                System.out.println("Uz redzēšanos!");
                break;
            default:
                System.out.println("Nederiga izvele. Meginiet vēlreiz.");
                main(args);
                break;
        }
    }
}