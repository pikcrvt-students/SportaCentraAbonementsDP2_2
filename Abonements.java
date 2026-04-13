import java.util.*;

public class Abonements {

    private static final String NOSAUKUMA_REGEX = "^.{1,15}$";
    private static final String PLANA_REGEX = "^.{1,600}$";

    private static boolean irDerigsAbonements(String nosaukums, double maksa, String plusi, String minusi) {
        if (nosaukums == null || !nosaukums.trim().matches(NOSAUKUMA_REGEX)) {
            return false;
        }

        if (maksa < 0) {
            return false;
        }

        if (plusi == null || !plusi.trim().matches(PLANA_REGEX)) {
            return false;
        }

        if (minusi != null && !minusi.trim().isEmpty() && !minusi.trim().matches(PLANA_REGEX)) {
            return false;
        }

        return true;
    }

    public static void abonementuIzvele() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("ABONEMENTU IZVELE");
        System.out.println();
        System.out.println("Izvelies abonementu:");
        System.out.println("1. GET FIT");
        System.out.println("2. GET CORE");
        System.out.println("3. GET CORE ULTRA");

        int abonementaIzvele = Main.readInt(scanner);

        switch (abonementaIzvele) {
            case 1:
                paraditAbonementu(
                    scanner,
                    "GET FIT",
                    20.0,
                    "Pieejams sporta zales aprikojums",
                    "Nav pieejama sauna"
                );
                break;
            case 2:
                paraditAbonementu(
                    scanner,
                    "GET CORE",
                    32.0,
                    "Pieejams sporta zales aprikojums un sauna",
                    null
                );
                break;
            case 3:
                paraditAbonementu(
                    scanner,
                    "GET CORE ULTRA",
                    50.0,
                    "Pieejams sporta zales aprikojums, sauna un masaza",
                    null
                );
                break;
            default:
                System.out.println("Nepareiza izvele.");
                break;
        }
    }

    private static void paraditAbonementu(Scanner scanner, String nosaukums, double maksa, String plusi, String minusi) {
        if (!irDerigsAbonements(nosaukums, maksa, plusi, minusi)) {
            System.out.println("Abonementa dati nav derigi.");
            return;
        }

        System.out.println(nosaukums);
        System.out.println();
        System.out.println(formatPrice(maksa) + " eiro/menesi");
        System.out.println("+ " + plusi);
        if (minusi != null && !minusi.isEmpty()) {
            System.out.println("- " + minusi);
        }

        System.out.println();
        System.out.println("Velaties iegadaties abonementu?");
        System.out.println("1. Ja");
        System.out.println("2. Ne");

        int iegadatiesIzvele = Main.readInt(scanner);
        if (iegadatiesIzvele == 1) {
            apstradatMaksajumu(scanner, nosaukums, maksa);
        }
    }

    private static void apstradatMaksajumu(Scanner scanner, String abonements, double maksa) {
        System.out.println("Izvelaties apmaksas veidu:");
        System.out.println();
        System.out.println("1. MAKSAT NEDELA (" + formatPrice(maksa / 4) + " eiro / nedela)");
        System.out.println("2. MAKSAT MENESI (" + formatPrice(maksa) + " eiro / menesis)");
        System.out.println("3. MAKSAT GADU (" + formatPrice(maksa * 12) + " eiro / gads)");
        System.out.println("4. Atpakal");

        int apmaksasVeids = Main.readInt(scanner);
        double paymentAmount;
        String periods;

        switch (apmaksasVeids) {
            case 1:
                paymentAmount = maksa / 4;
                periods = "nedelai";
                break;
            case 2:
                paymentAmount = maksa;
                periods = "menesim";
                break;
            case 3:
                paymentAmount = maksa * 12;
                periods = "gadam";
                break;
            case 4:
                return;
            default:
                System.out.println("Nepareiza izvele.");
                return;
        }

        scanner.nextLine();
        double finalAmount = piemeroStudentaAtlaidi(scanner, paymentAmount);
        double currentBalance = Lietotaji.getCurrentUserBalance();

        if (currentBalance >= finalAmount) {
            double newBalance = currentBalance - finalAmount;
            Lietotaji.updateCurrentUserBalance(newBalance);
            Lietotaji.updateCurrentUserAbonements(abonements + " (" + periods + ")");

            System.out.println("Apmaksa veiksmiga!");
            System.out.println("Samaksata summa: " + formatPrice(finalAmount) + " eiro");
            System.out.println("Atlikusais atlikums: " + formatPrice(newBalance) + " eiro");
        } else {
            System.out.println("Nepietiekams atlikums. Jusu summa: " + formatPrice(currentBalance) + " eiro");
            System.out.println("Nepieciesama summa: " + formatPrice(finalAmount) + " eiro");
        }
    }

    private static double piemeroStudentaAtlaidi(Scanner scanner, double paymentAmount) {
        System.out.println("Vai jus esat students?");
        System.out.println("1. Ja");
        System.out.println("2. Ne");

        String studentaIzvele = scanner.nextLine().trim();
        if (studentaIzvele.equals("1")) {
            System.out.println("Ievadiet savu universitati:");
            String universitatesNosaukums = scanner.nextLine().trim();

            if (!universitatesNosaukums.isEmpty()) {
                double discountedAmount = paymentAmount / 2;
                System.out.println("Studenta atlaide ir piemerota.");
                System.out.println("Universitate: " + universitatesNosaukums);
                System.out.println("Pilna cena: " + formatPrice(paymentAmount) + " eiro");
                System.out.println("Cena ar 50% atlaidi: " + formatPrice(discountedAmount) + " eiro");
                return discountedAmount;
            }
        }

        return paymentAmount;
    }

    private static String formatPrice(double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }

    public static void apskatitManuabonementu() {
        String currentUserEmail = Lietotaji.getCurrentUserEmail();

        for (String klients : Lietotaji.klientuList()) {
            String[] klientInfo = klients.split(",");

            if (klientInfo[2].trim().equals(currentUserEmail)) {
                System.out.println("Jusu abonements: " + klientInfo[4].trim());
                return;
            }
        }

        System.out.println("Jums nav abonementa.");
    }
}
