import java.util.*;

public class Administratori {

    public static void administratoruRegistresana() { //funkcija <administratoruRegistresana> pieņem <int> tipa vērtību <slepkods> un atgriež <void> tipa vērtību <void>
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (true) {
            System.out.println("Ievadiet slepkodu:");
            String slepkods = scanner.nextLine();

            if (slepkods.equals("67")) {
                System.out.println("Laipni lugti, administrators!");
                break;
            } else if (slepkods.isEmpty()) {
                System.out.println("Lauks ir tukss! Meginiet velreiz.");
                
            } else {
                System.out.println("Nepareizs slepkods! Meginiet velreiz.");
            }
        }
    }

    public static void administratoruPieslegsanas() { //funkcija <administratoruPieslegsana> pieņem <int> tipa vērtību <slepkods> un atgriež <void> tipa vērtību <void>
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (true) {
            System.out.println("Ievadiet slepkodu:");
            int slepkods = Main.readInt(scanner);
            scanner.nextLine();

            if (slepkods == 67) {
                System.out.println("Laipni lugti, administrators!");
                break;
            } else {
                System.out.println("Nepareizs slepkods! Meginiet velreiz.");
            }
        }
    }

    public static void dzestTrenerakontu() { //funkcija <dzestTrenerakontu> pieņem <String> tipa vērtību <ievaditaisEpasts> un atgriež <void> tipa vērtību <void>
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("Ievadies trenera e-pastu, kuru velaties izdzest: ");
        String ievaditaisEpasts = scanner.nextLine();
        String[] trenerInfo = Treneri.dabutTreneraInfoPecEpasta(ievaditaisEpasts);

        if (trenerInfo != null) {
            System.out.println();
            System.out.println("Treneris atrasts: " + trenerInfo[0].trim() + ", " + trenerInfo[1].trim() + ", " + trenerInfo[2].trim());
            System.out.println();
        } else {
            System.out.println("Treneris ar so e-pastu netika atrasts.");
        }
        
    }

    public static void dzestLietotajakontu() { //funkcija <dzestLietotajakontu> pieņem <String> tipa vērtību <ievaditaisEpasts> un atgriež <void> tipa vērtību <void>
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("Ievadies lietotaja e-pastu, kuru velaties izdzest: ");
        String ievaditaisEpasts = scanner.nextLine();
        String[] lietotajaInfo = Lietotaji.dabutLietotajaInfoPecEpasta(ievaditaisEpasts);

        if (lietotajaInfo != null) {
            System.out.println();
            System.out.println("Lietotajs atrasts: " + lietotajaInfo[0].trim() + ", " + lietotajaInfo[1].trim() + ", " + lietotajaInfo[2].trim());
            System.out.println();
        } else {
            System.out.println("Lietotajs ar so e-pastu netika atrasts.");
        }
        
    }

   public static void  paradaTrenerusarakstu() {
        System.out.println("Treneru saraksts:");
        int index = 0;
        for (String treneris : Treneri.treneruList) {
            System.out.println((index + 1) + ". " + treneris);
            index++;
   }}

    public static void paradaLietotajusarakstu() {
        System.out.println("Lietotaju saraksts:");
        int index = 0;
        for (String lietotajs : Lietotaji.klientuList) {
            System.out.println((index + 1) + ". " + lietotajs);
            index++;}

}}

