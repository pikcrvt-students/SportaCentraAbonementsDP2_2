import java.util.*;
import java.io.*;

public class Lietotaji {

    public static ArrayList<String> klientuList = new ArrayList<>();
    private static final String filePathforKlienti = "csv/klientRegistration.csv";
    private static String currentUserEmail = null;
    private static final String VARDA_REGEX = "^[A-Za-zĀČĒĢĪĶĻŅŠŪŽāčēģīķļņšūž\\s-]{1,60}$";
    private static final String UZVARDA_REGEX = "^[A-Za-zĀČĒĢĪĶĻŅŠŪŽāčēģīķļņšūž\\s-]{1,100}$";
    private static final String EPASTA_REGEX = "^[A-Za-z0-9+_.-]{1,64}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String TELEFONA_REGEX = "^\\d{8}$";

    private static void nodrosinaKlientaSaglabasanu() {
        if (klientuList.isEmpty()) {
            loadKlientiFromFile();
        }
    }

    public static boolean vaiKlientaEpastsEksiste(String epasts) {
        nodrosinaKlientaSaglabasanu();

        for (String klients : klientuList) {
            String[] klientInfo = normalizeKlientInfo(klients.split(","));
            if (klientInfo[2].equalsIgnoreCase(epasts.trim())) {
                return true;
            }
        }

        return false;
    }

    private static boolean irDerigsVards(String vards) {
        return vards != null && vards.trim().matches(VARDA_REGEX);
    }

    private static boolean irDerigsUzvards(String uzvards) {
        return uzvards != null && uzvards.trim().matches(UZVARDA_REGEX);
    }

    private static boolean irDerigsEpasts(String epasts) {
        return epasts != null && epasts.trim().length() <= 150 && epasts.trim().matches(EPASTA_REGEX);
    }

    private static boolean irDerigsTelefons(String telefons) {
        return telefons != null && telefons.trim().matches(TELEFONA_REGEX);
    }

    public static void klientuRegistresana() {
        nodrosinaKlientaSaglabasanu();
        Scanner scanner = new Scanner(System.in);

        String klientaVards;
        while (true) {
            System.out.println("Ievadiet savu vardu:");
            klientaVards = scanner.nextLine();

            if (irDerigsVards(klientaVards)) {
                klientaVards = klientaVards.trim();
                break;
            }

            System.out.println("Nepareizi ievadits vards. Garums lidz 60 simboliem.");
        }

        String klientaUzvards;
        while (true) {
            System.out.println("Ievadiet savu uzvardu:");
            klientaUzvards = scanner.nextLine();

            if (irDerigsUzvards(klientaUzvards)) {
                klientaUzvards = klientaUzvards.trim();
                break;
            }

            System.out.println("Nepareizi ievadits uzvards. Garums lidz 100 simboliem.");
        }

        String klientaEpasts;
        while (true) {
            System.out.println("Ievadiet e-pastu:");
            String klientaPastaievade = scanner.nextLine();

            if (irDerigsEpasts(klientaPastaievade)) {
            klientaEpasts = klientaPastaievade.trim();
            break;
         } else {
            System.out.println("Nepareizi ievadits e-pasts. Meginiet velreiz.");
        }}

        String klientaTelefons;
        while (true) {
            System.out.println("Ievadiet telefona numuru:");
            String ievadeKlientatelefons = scanner.nextLine();

            if (irDerigsTelefons(ievadeKlientatelefons)) {
             klientaTelefons = ievadeKlientatelefons.trim();
            break;
         } else {
            System.out.println("Nepareizi ievadits telefona numurs. Meginiet velreiz.");
        }
    }

        if (vaiKlientaEpastsEksiste(klientaEpasts) || Treneri.vaiTreneraEpastsEksiste(klientaEpasts)) {
            System.out.println("Sis e-pasts jau ir registrets. Ludzu, izmantojiet citu e-pastu.");
            return;
        }

        int newID = getIDklients() + 1;
        String klientData = newID + ". " + klientaVards + "," + klientaUzvards + "," + klientaEpasts + "," + klientaTelefons + ",abonements,0.0";
        klientuList.add(klientData);
        
        updateFileforklient();
    }

    public static int getIDklients() {
        nodrosinaKlientaSaglabasanu();
        if (klientuList.isEmpty()) {
            return 0;
        }

        String lastLine = klientuList.get(klientuList.size() - 1);
        String[] parts = lastLine.split(",", -1);
        String idPart = parts[0].trim().replace(".", "");

        try {
            return Integer.parseInt(idPart);
        } catch (NumberFormatException e) {
            return klientuList.size();
        }
    }

    private static void updateFileKlietn() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathforKlienti))) {
            writer.write("id, vards, uzvards, epasts, telefons, abonements, balanse");
            writer.newLine();

            for (String klientData : klientuList) {
                writer.write(klientData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Kluda saglabajot datus: " + e.getMessage());
        }
    }

    private static void updateFileforklient() {
        updateFileKlietn();
    }

    private static String[] normalizeKlientInfo(String[] klientInfo) {
        if (klientInfo == null) {
            return new String[] {"", "", "", "", "abonements", "0.0"};
        }

        String[] trimmed = new String[klientInfo.length];
        for (int i = 0; i < klientInfo.length; i++) {
            trimmed[i] = klientInfo[i] == null ? "" : klientInfo[i].trim();
        }

        if (trimmed.length <= 4) {
            return new String[] {
                trimmed.length > 0 ? trimmed[0] : "",
                trimmed.length > 1 ? trimmed[1] : "",
                trimmed.length > 2 ? trimmed[2] : "",
                trimmed.length > 3 ? trimmed[3] : "",
                "abonements",
                "0.0"
            };
        }

        if (trimmed.length == 5) {
            return new String[] {
                trimmed[0],
                trimmed[1],
                trimmed[2],
                trimmed[3],
                trimmed[4].isEmpty() ? "abonements" : trimmed[4],
                "0.0"
            };
        }

        String[] result = new String[6];
        System.arraycopy(trimmed, 0, result, 0, 6);
        if (result[4].isEmpty()) {
            result[4] = "abonementa nav";
        }
        if (result[5].isEmpty()) {
            result[5] = "0.0";
        }
        return result;
    }

    private static String normalizeKlientLine(String line) {
        String[] parts = line.split(",");
        return String.join(",", normalizeKlientInfo(parts));
    }

    private static double parseBalance(String[] klientInfo) {
        if (klientInfo == null || klientInfo.length <= 5) {
            return 0.0;
        }
        try {
            return Double.parseDouble(klientInfo[5]);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private static int findCurrentUserIndex() {
        if (currentUserEmail == null) {
            return -1;
        }

        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = normalizeKlientInfo(klientuList.get(i).split(","));
            if (klientInfo[2].equals(currentUserEmail)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean setCurrentUserByEmail(String email) {
        if (email == null) {
            return false;
        }

        String normalizedEmail = email.trim();
        for (String klients : klientuList) {
            String[] klientInfo = normalizeKlientInfo(klients.split(","));
            if (klientInfo[2].equals(normalizedEmail)) {
                currentUserEmail = normalizedEmail;
                return true;
            }
        }

        return false;
    }

    public static void pieslegtiesKlientam() {
        System.out.println("Pieslegties");
        System.out.println("Ievadiet savu e-pastu:");
        Scanner scanner = new Scanner(System.in);
        String ievaditaisEpasts;

        while (true) {
            ievaditaisEpasts = scanner.nextLine().trim();
            boolean found = false;

            for (String klients : klientuList) {
                String[] klientInfo = klients.split(",");
                if (klientInfo[2].equalsIgnoreCase(ievaditaisEpasts)) {
                    found = true;
                    System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[0] + "!");
                    break;
                }
            }

            if (found) {
                break;
            }

            System.out.println("E-pasts nav atrasts. Ievadiet velreiz:");
        }
    }

    public static void mansKonts() {
        Scanner scanner = new Scanner(System.in);
        if (currentUserEmail == null) {
            while (true) {
                System.out.println("Ievadiet savu e-pastu:");
                String ievaditaisEpasts = scanner.nextLine().trim();

                if (setCurrentUserByEmail(ievaditaisEpasts)) {
                    break;
                }

                System.out.println("E-pasts nav atrasts. Ievadiet velreiz.");
            }
        }

        for (String klients : klientuList) {
            String[] klientInfo = normalizeKlientInfo(klients.split(","));
            if (klientInfo[2].equals(currentUserEmail)) {
                System.out.println("Mans konts");
                System.out.println("Mans vards: " + klientInfo[0]);
                System.out.println("Mans uzvards: " + klientInfo[1]);
                System.out.println("Mans e-pasts: " + klientInfo[2]);
                System.out.println("Mans telefons: " + klientInfo[3]);
                System.out.println("Jusu abonements: " + klientInfo[4]);
                
                System.out.println();
                System.out.println("Ko jus velaties darit?");
                System.out.println("1. Rediget profila datus");
                System.out.println("2. Apskatit jusu abonementu");
                System.out.println("3. Dzest savu kontu");
                System.out.println("4. Atgriezties izvelne");
                int kontaIzvele = Main.readInt(scanner);
                scanner.nextLine();
                switch(kontaIzvele) {
                    case 1:
                        while (true) {
                            System.out.println("Ievadiet savu e-pastu:");
                            String ievaditaisEpasts = scanner.nextLine().trim();

                            if (currentUserEmail != null && !ievaditaisEpasts.equalsIgnoreCase(currentUserEmail)) {
                                System.out.println("Sis nav jusu e-pasts. Ievadiet savu e-pastu velreiz.");
                                continue;
                            }

                            break;
                        }
                        redigetProfilaDatus();
                        Main.klientaIzvelne(new String[0]); 
                        break;
                    case 2: 
                        while (true) {
                            System.out.println("Ievadiet savu e-pastu:");
                            String ievaditaisEpasts = scanner.nextLine().trim();

                            if (currentUserEmail != null && !ievaditaisEpasts.equalsIgnoreCase(currentUserEmail)) {
                                System.out.println("Sis nav jusu e-pasts. Ievadiet savu e-pastu velreiz.");
                                continue;
                            }

                            break;
                        }
                        Abonements.apskatitManuabonementu();
                        Main.klientaIzvelne(new String[0]);
                        break;
                    case 3: 
                        while (true) {
                            System.out.println("Ievadiet savu e-pastu:");
                            String ievaditaisEpasts = scanner.nextLine().trim();

                            if (currentUserEmail != null && !ievaditaisEpasts.equalsIgnoreCase(currentUserEmail)) {
                                System.out.println("Sis nav jusu e-pasts. Ievadiet savu e-pastu velreiz.");
                                continue;
                            }

                            break;
                        }
                        klientuList.remove(klients);
                        updateFileKlietn();
                        System.out.println("Jusu konts ir dzests. Uz redzesanos!");
                        Main.Registresana();
                    case 4:
                        Main.klientaIzvelne(new String[0]);
                        break;
                    default:
                        System.out.println("Nepareiza izvele.");
                        Main.klientaIzvelne(new String[0]);
                }
                return;
            }
        }
        System.out.println("E-pasts nav atrasts. LLudzu, meginiet velreiz.");
    }

    public static void redigetProfilaDatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rediget profila datus");
        String ievaditaisEpasts;
        while (true) {
            System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");
            ievaditaisEpasts = scanner.nextLine().trim();

            if (currentUserEmail != null && !ievaditaisEpasts.equalsIgnoreCase(currentUserEmail)) {
                System.out.println("Sis nav jusu e-pasts. Ievadiet savu e-pastu velreiz.");
                continue;
            }

            break;
        }

        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = normalizeKlientInfo(klientuList.get(i).split(","));
            if (klientInfo[2].equalsIgnoreCase(ievaditaisEpasts)) {
                String jaunsVards;
                while (true) {
                    System.out.println("Ievadiet jaunu vardu:");
                    jaunsVards = scanner.nextLine();

                    if (irDerigsVards(jaunsVards)) {
                        jaunsVards = jaunsVards.trim();
                        break;
                    }

                    System.out.println("Nepareizi ievadits vards.");
                }

                String jaunsUzvards;
                while (true) {
                    System.out.println("Ievadiet jaunu uzvardu:");
                    jaunsUzvards = scanner.nextLine();

                    if (irDerigsUzvards(jaunsUzvards)) {
                        jaunsUzvards = jaunsUzvards.trim();
                        break;
                    }

                    System.out.println("Nepareizi ievadits uzvards.");
                }

                String jaunsTelefons;
                while (true) {
                    System.out.println("Ievadiet jaunu telefona numuru:");
                    jaunsTelefons = scanner.nextLine();

                    if (irDerigsTelefons(jaunsTelefons)) {
                        jaunsTelefons = jaunsTelefons.trim();
                        break;
                    }

                    System.out.println("Nepareizi ievadits telefona numurs.");
                }

                klientInfo[0] = jaunsVards;
                klientInfo[1] = jaunsUzvards;
                klientInfo[3] = jaunsTelefons;

                klientuList.set(i, String.join(",", klientInfo));
                updateFileKlietn();
                System.out.println("Profila dati veiksmigi atjauninati!");
                Main.klientaIzvelne(new String[0]);
                return;
            }
        }

        System.out.println("E-pasts nav atrasts. LLudzu, meginiet velreiz.");
    }

    public static void loadKlientiFromFile() {
        File file = new File(filePathforKlienti);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            klientuList.clear();
            String line;
            // Skip header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                klientuList.add(normalizeKlientLine(line));
            }
        } catch (IOException e) {
            System.out.println("Kluda ieladejot datus: " + e.getMessage());
        }
    }

    public static void naudasIemaksa() {
        Scanner scanner = new Scanner(System.in);
        if (currentUserEmail == null) {
            System.out.println("Ievadiet savu e-pastu:");
            String ievaditaisEpasts = scanner.nextLine();

            if (!setCurrentUserByEmail(ievaditaisEpasts)) {
                System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
                return;
            }
        }

        int userIndex = findCurrentUserIndex();
        if (userIndex == -1) {
            System.out.println("Klienta konts nav atrasts.");
            return;
        }

        String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
        System.out.println("Ievadiet iemaksa summu:");
        double depositAmount = Main.readDouble(scanner);
        scanner.nextLine();

        double currentBalance = parseBalance(klientInfo);
        double newBalance = currentBalance + depositAmount;

        klientInfo[5] = String.valueOf(newBalance);
        klientuList.set(userIndex, String.join(",", klientInfo));
        updateFileforklient();

        System.out.println("Naudas iemaksa veiksmiga! Jusu summa: " + newBalance);
    }

    public static void klientuPieslegsanas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pieslegsanas");
        System.out.println("Ievadiet savu e-pastu: ");
        boolean found;

        do {
            String ievaditaisEpasts = scanner.nextLine().trim();
            found = setCurrentUserByEmail(ievaditaisEpasts);

            if (!found) {
                System.out.println("E-pasts nav atrasts. Ievadiet velreiz:");
            }
        } while (!found);

        int userIndex = findCurrentUserIndex();
        String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
        System.out.println();
        System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[1] + "!");
        System.out.println();
        }
    
        public static double getCurrentUserBalance(){
            int userIndex = findCurrentUserIndex();
            if(userIndex == -1) {
                System.out.println("Nav pieslegts neviens klients.");
                return 0.0;
            }
            String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
            return parseBalance(klientInfo);
        }



     public static void updateCurrentUserBalance(double newBalance) {
        int userIndex = findCurrentUserIndex();
        if (userIndex == -1) return;

        String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
        klientInfo[5] = String.valueOf(newBalance);
        klientuList.set(userIndex, String.join(",", klientInfo));
        updateFileforklient();
    }

    public static void updateCurrentUserAbonements(String abonements) {
        int userIndex = findCurrentUserIndex();
        if (userIndex == -1) return;

        String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
        klientInfo[4] = abonements;
        klientuList.set(userIndex, String.join(",", klientInfo));
        updateFileforklient();
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail; 
    }

    public static ArrayList<String> klientuList() {
        return klientuList; 
    }

     public static int atrastKlientuPecEpasta(String epasts) {
        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = klientuList.get(i).split(",");
            if (klientInfo.length > 2 && klientInfo[2].trim().equalsIgnoreCase(epasts.trim())) {
                return i;
            }
        }
        return -1;
    }

    public static String[] dabutLietotajaInfoPecEpasta(String epasts) {
        int index = atrastKlientuPecEpasta(epasts);
        if (index == -1) {
            return null;
        }
        return klientuList.get(index).split(",");
    }

}












