import java.util.*;
import java.io.*;

public class Lietotaji {

    public static ArrayList<String> klientuList = new ArrayList<>();
    private static final String filePathforKlienti = "csv/klientRegistration.csv";
    private static String currentUserEmail = null;

    public static void klientuRegistresana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu vardu:");
        String klientaVards = scanner.nextLine();  
        System.out.println("Ievadiet savu uzvardu:");
        String klientaUzvards = scanner.nextLine();

        System.out.println("Ievadiet savu e-pastu:");
        String klientaEpasts;
        while (true) {
            System.out.println("Ievadiet e-pastu:");
            String klientaPastaievade = scanner.nextLine();

            if (klientaPastaievade.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            klientaEpasts = klientaPastaievade;
            break;
         } else {
            System.out.println("Nepareizi ievadits e-pasts. Meginiet velreiz.");
        }}

        String klientaTelefons;
        while (true) {
            System.out.println("Ievadiet telefona numuru:");
            String ievadeKlientatelefons = scanner.nextLine();

            if (ievadeKlientatelefons.matches("\\d{8}")) {
             klientaTelefons = "371" + ievadeKlientatelefons;
            break;
         } else {
            System.out.println("Nepareizi ievadits telefona numurs. Meginiet velreiz.");
        }
    }

        // Check if the email already exists in the client list
        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo.length > 2 && klientInfo[2].trim().equals(klientaEpasts)) {
                System.out.println("Sis e-pasts jau ir reģistrets. Ludzu, izmantojiet citu e-pastu.");
                return;
            }
        }

        // If the email is unique, proceed with registration
        int newID = getIDklients() + 1;
        String klientData = newID + ". " + klientaVards + "," + klientaUzvards + "," + klientaEpasts + "," + klientaTelefons + ",abonements,0.0";
        klientuList.add(klientData);
        
        updateFileforklient();
    }

    public static int getIDklients() {
        if (klientuList.isEmpty()) {
            return 0;
        }

        String lastLine = klientuList.get(klientuList.size() - 1);
        String[] idPart = lastLine.split("\\. ");
        return Integer.parseInt(idPart[0]);
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
        String ievaditaisEpasts = scanner.nextLine();

        boolean found = false;

        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                found = true;
                System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[0] + "!");
                break;
            }
        }

        if (!found) {
            System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
        }
    }

    public static void mansKonts() {
        Scanner scanner = new Scanner(System.in);
        if (currentUserEmail == null) {
            System.out.println("Ievadiet savu e-pastu:");
            String ievaditaisEpasts = scanner.nextLine();

            if (!setCurrentUserByEmail(ievaditaisEpasts)) {
                System.out.println("E-pasts nav atrasts. LLudzu, meginiet velreiz.");
                return;
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
                int kontaIzvele = scanner.nextInt();
                switch(kontaIzvele) {
                    case 1:
                        redigetProfilaDatus();
                        Main.klientaIzvelne(new String[0]); 
                        break;
                    case 2: 
                        Abonements.apskatitManuabonementu();
                        Main.klientaIzvelne(new String[0]);
                        break;
                    case 3: 
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
        System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");
        String ievaditaisEpasts = scanner.nextLine();

        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = normalizeKlientInfo(klientuList.get(i).split(","));
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                System.out.println("Ievadiet jaunu vardu:");
                String jaunsVards = scanner.nextLine();
                System.out.println("Ievadiet jaunu uzvardu:");
                String jaunsUzvards = scanner.nextLine();
                System.out.println("Ievadiet jaunu telefona numuru:");
                String jaunsTelefons = scanner.nextLine();

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
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathforKlienti))) {
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
        double depositAmount = scanner.nextDouble();
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
        String ievaditaisEpasts = scanner.nextLine();
        boolean found = setCurrentUserByEmail(ievaditaisEpasts);

        if (found) {
            int userIndex = findCurrentUserIndex();
            String[] klientInfo = normalizeKlientInfo(klientuList.get(userIndex).split(","));
            System.out.println();
            System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[1] + "!");
            System.out.println();
        } else {
            System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
            klientuPieslegsanas();
        }
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












