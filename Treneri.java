import java.io.*;
import java.util.*;

public class Treneri {
    public static ArrayList<String> treneruList = new ArrayList<>();
    private static final String filePathforTreneri = "csv/trenerRegistration.csv";
    private static String currentTrainerEmail = null;

    private static void nodrosinaTreneraSaglabasanu() {
        if (treneruList.isEmpty()) {
            loadTreneriFromFile();
        }
    }

    public static void treneruRegistresana() {
        nodrosinaTreneraSaglabasanu();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ievadiet savu vardu:");
        String treneraVards = scanner.nextLine();
        System.out.print("Ievadiet savu uzvardu:");
        String treneraUzvards = scanner.nextLine();

        String treneraEpasts;
        while (true) {
            System.out.println("Ievadiet e-pastu:");
            String treneraPastaievade = scanner.nextLine();

            if (treneraPastaievade.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                treneraEpasts = treneraPastaievade;
                break;
            } else {
                System.out.println("Nepareizi ievadits e-pasts. Meginiet velreiz.");
            }
        }

        String treneraTelefons;
        while (true) {
            System.out.println("Ievadiet telefona numuru:");
            String ievadeTreneratelefons = scanner.nextLine();

            if (ievadeTreneratelefons.matches("\\d{8}")) {
                treneraTelefons = "371" + ievadeTreneratelefons;
                break;
            } else {
                System.out.println("Nepareizi ievadits telefona numurs. Meginiet velreiz.");
            }
        }

        String treneraPkods;
        while (true) {
            System.out.println("Ievadiet personas kodu:");
            String input = scanner.nextLine();

            if (input.matches("^\\d{6}-\\d{5}$")) {
                treneraPkods = input;
                break;
            } else {
                System.out.println("Nepareizi ievadits personas kods. Meginiet velreiz.");
            }
        }

        System.out.print("Ievadiet savu specializaciju:");
        String treneraSpecializacija = scanner.nextLine();

        for (String treneris : treneruList) {
            String[] trenerInfo = normalizeTrenerInfo(treneris.split(","));
            if (trenerInfo[2].equalsIgnoreCase(treneraEpasts.trim())) {
                System.out.println("Sis e-pasts jau ir registrets.");
                return;
            }
        }

        int newID = getIDtreneri() + 1;

        String trenerData = newID + ". " + treneraVards + "," + treneraUzvards + "," + treneraEpasts + "," + treneraTelefons + "," + treneraPkods + "," + treneraSpecializacija;
        treneruList.add(trenerData);
        currentTrainerEmail = treneraEpasts.trim();

        updateFileTrener();
    }

    public static int getIDtreneri() {
        nodrosinaTreneraSaglabasanu();
        if (treneruList.isEmpty()) {
            return 0;
        }

        String lastLine = treneruList.get(treneruList.size() - 1);
        String[] parts = lastLine.split(",", -1);
        String idPart = parts[0].trim().replace(".", "");

        try {
            return Integer.parseInt(idPart);
        } catch (NumberFormatException e) {
            return treneruList.size();
        }
    }

    public static ArrayList<String> getTreneruList() {
        return treneruList;
    }

    public static int atrastTreneriPecEpasta(String epasts) {
        for (int i = 0; i < treneruList.size(); i++) {
            String[] trenerInfo = normalizeTrenerInfo(treneruList.get(i).split(","));
            if (trenerInfo[2].equalsIgnoreCase(epasts.trim())) {
                return i;
            }
        }
        return -1;
    }

    private static String[] normalizeTrenerInfo(String[] trenerInfo) {
        if (trenerInfo == null) {
            return new String[] {"", "", "", "", "", ""};
        }

        String[] trimmed = new String[trenerInfo.length];
        for (int i = 0; i < trenerInfo.length; i++) {
            trimmed[i] = trenerInfo[i] == null ? "" : trenerInfo[i].trim();
        }

        if (trimmed.length <= 5) {
            return new String[] {
                trimmed.length > 0 ? trimmed[0] : "",
                trimmed.length > 1 ? trimmed[1] : "",
                trimmed.length > 2 ? trimmed[2] : "",
                trimmed.length > 3 ? trimmed[3] : "",
                trimmed.length > 4 ? trimmed[4] : "",
                ""
            };
        }

        String[] result = new String[6];
        System.arraycopy(trimmed, 0, result, 0, 6);
        return result;
    }

    private static String normalizeTrenerLine(String line) {
        String[] parts = line.split(",");
        return String.join(",", normalizeTrenerInfo(parts));
    }

    private static boolean setCurrentTrenerByEmail(String email) {
        if (email == null) {
            return false;
        }

        String normalizedEmail = email.trim();
        for (String treneris : treneruList) {
            String[] trenerInfo = normalizeTrenerInfo(treneris.split(","));
            if (trenerInfo[2].equalsIgnoreCase(normalizedEmail)) {
                currentTrainerEmail = normalizedEmail;
                return true;
            }
        }

        return false;
    }

    public static void mansKonts() {
        Scanner scanner = new Scanner(System.in);

        if (currentTrainerEmail == null) {
            System.out.println("Ievadiet savu e-pastu:");
            String ievaditaisEpasts = scanner.nextLine();

            if (!setCurrentTrenerByEmail(ievaditaisEpasts)) {
                System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
                return;
            }
        }

        for (int i = 0; i < treneruList.size(); i++) {
            String[] trenerInfo = normalizeTrenerInfo(treneruList.get(i).split(","));
            if (trenerInfo[2].equalsIgnoreCase(currentTrainerEmail)) {
                System.out.println("Mans konts");
                System.out.println("Mans vards: " + trenerInfo[0].replaceFirst("^\\d+\\.\\s*", ""));
                System.out.println("Mans uzvards: " + trenerInfo[1]);
                System.out.println("Mans e-pasts: " + trenerInfo[2]);
                System.out.println("Mans telefons: " + trenerInfo[3]);
                System.out.println("Mans personas kods: " + trenerInfo[4]);
                System.out.println("Mana specializacija: " + trenerInfo[5]);

                System.out.println();
                System.out.println("Ko jus velaties darit?");
                System.out.println("1. Rediget profila datus");
                System.out.println("2. Dzest savu kontu");
                System.out.println("3. Atgriezties izvelne");
                int kontaIzvele = scanner.nextInt();
                scanner.nextLine();

                switch (kontaIzvele) {
                    case 1:
                        redigetTerneraprofilaDatus();
                        return;
                    case 2:
                        treneruList.remove(i);
                        updateFileTrener();
                        currentTrainerEmail = null;
                        System.out.println("Jusu konts ir dzests. Uz redzesanos!");
                        Main.Registresana();
                        return;
                    case 3:
                        Main.treneraIzvelne(new String[0]);
                        return;
                    default:
                        System.out.println("Nepareiza izvele.");
                        Main.treneraIzvelne(new String[0]);
                        return;
                }
            }
        }

        System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
    }

    public static String[] dabutTreneraInfoPecEpasta(String epasts) {
        int index = atrastTreneriPecEpasta(epasts);
        if (index == -1) {
            return null;
        }
        return normalizeTrenerInfo(treneruList.get(index).split(","));
    }

    public static String getCurrentTrainerEmail() {
        return currentTrainerEmail;
    }

    public static String dabutTreneraPilnoVarduPecEpasta(String epasts) {
        String[] trenerInfo = dabutTreneraInfoPecEpasta(epasts);
        if (trenerInfo == null) {
            return "Nezinams treneris";
        }

        String vards = trenerInfo[0].replaceFirst("^\\d+\\.\\s*", "");
        String uzvards = trenerInfo[1];
        return vards + " " + uzvards;
    }

    public static String getCurrentTrainerVardsUzvards() {
        if (currentTrainerEmail == null) {
            return "Nezinams treneris";
        }
        return dabutTreneraPilnoVarduPecEpasta(currentTrainerEmail);
    }

    private static void updateFileTrener() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathforTreneri));

            writer.write("id, vards, uzvards, epasts, telefons, pkods, specializacija");
            writer.newLine();

            for (String treneris : treneruList) {
                writer.write(treneris);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }
    }

    public static void izveletiesTreneri() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Treneru saraksts:");

        int index = 0;
        for (String treneris : treneruList) {
            System.out.println((index + 1) + ". " + treneris);
            index++;
        }

        System.out.println("Ievadiet trenera numuru, kuru velaties izveleties:");
        int treneruIzvele = scanner.nextInt();
        if (treneruIzvele < 1 || treneruIzvele > treneruList.size()) {
            System.out.println("Nepareiza izvele. Meginiet velreiz.");
            return;
        }
    }

    public static void redigetTerneraprofilaDatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rediget profila datus");
        System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");
        String ievaditaisEpasts = scanner.nextLine();

        for (int i = 0; i < treneruList.size(); i++) {
            String[] trenerInfo = normalizeTrenerInfo(treneruList.get(i).split(","));

            if (trenerInfo[2].equalsIgnoreCase(ievaditaisEpasts.trim())) {
                String idPart = trenerInfo[0];
                String vecaisVards = idPart.replaceFirst("^\\d+\\.\\s*", "");

                System.out.println("Ievadiet jaunu vardu:");
                String jaunsVards = scanner.nextLine();
                System.out.println("Ievadiet jaunu uzvardu:");
                String jaunsUzvards = scanner.nextLine();
                System.out.println("Ievadiet jaunu e-pastu:");
                String jaunsEpasts = scanner.nextLine();
                System.out.println("Ievadiet jaunu telefona numuru:");
                String jaunsTelefons = scanner.nextLine();
                System.out.println("Ievadiet jaunu personas kodu:");
                String jaunsPkods = scanner.nextLine();
                System.out.println("Ievadiet jaunu specializaciju:");
                String jaunsSpecializacija = scanner.nextLine();

                String idPrefix = "";
                if (idPart.contains(". ")) {
                    idPrefix = idPart.split("\\. ")[0] + ". ";
                }

                trenerInfo[0] = idPrefix + jaunsVards;
                trenerInfo[1] = jaunsUzvards;
                trenerInfo[2] = jaunsEpasts;
                trenerInfo[3] = jaunsTelefons;
                trenerInfo[4] = jaunsPkods;
                trenerInfo[5] = jaunsSpecializacija;

                treneruList.set(i, String.join(",", trenerInfo));
                currentTrainerEmail = jaunsEpasts.trim();
                updateFileTrener();

                System.out.println("Profila dati veiksmigi atjauninati!");
                Main.treneraIzvelne(new String[0]);
                return;
            }
        }

        System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
    }

    public static void treneruPieslegsanas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pieslegsanas");
        System.out.println("Ievadiet savu e-pastu: ");
        String ievaditaisEpasts = scanner.nextLine();

        boolean found = setCurrentTrenerByEmail(ievaditaisEpasts);

        if (found) {
            String[] trenerInfo = dabutTreneraInfoPecEpasta(currentTrainerEmail);

            System.out.println();
            System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + trenerInfo[1] + "!");
            System.out.println();
        } else {
            System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
            treneruPieslegsanas();
        }
    }

    public static void loadTreneriFromFile() {
        File file = new File(filePathforTreneri);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            treneruList.clear();
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                treneruList.add(normalizeTrenerLine(line));
            }
        } catch (IOException e) {
            System.out.println("Kluda ieladejot datus: " + e.getMessage());
        }
    }
}
