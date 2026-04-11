import java.util.*;

public class Administratori {

    public static void administratoruRegistresana() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (true) {
            System.out.println("Ievadiet slepkodu:");
            int slepkods = scanner.nextInt();

            if (slepkods == 67) {
                System.out.println("Laipni lugti, administrators!");
                break;
            } else {
                System.out.println("Nepareizs slepkods! Meginiet velreiz.");
            }
        }
    }

    public static void administratoruPieslegsanas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (true) {
            System.out.println("Ievadiet slepkodu:");
            int slepkods = scanner.nextInt();

            if (slepkods == 67) {
                System.out.println("Laipni lugti, administrators!");
                break;
            } else {
                System.out.println("Nepareizs slepkods! Meginiet velreiz.");
            }
        }
    }

    public static void dzestTrenerakodu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("Ievadies trenera e-pastu, kuru velaties izdzest: ");
        String trenepasts = scanner.nextLine();
        

    }
    }


