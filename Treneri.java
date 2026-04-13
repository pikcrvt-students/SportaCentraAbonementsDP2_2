import java.io.*;
import java.util.*;

public class Treneri {
    public static ArrayList<String> treneruList = new ArrayList<>();
    private static final String filePathforTreneri = "csv/trenerRegistration.csv";
    private static String currentTrainerEmail = null;
    private static final String VARDA_REGEX = "^[A-Za-zĀČĒĢĪĶĻŅŠŪŽāčēģīķļņšūž\\s-]{1,60}$";
    private static final String UZVARDA_REGEX = "^[A-Za-zĀČĒĢĪĶĻŅŠŪŽāčēģīķļņšūž\\s-]{1,100}$";
    private static final String PKODA_REGEX = "^\\d{6}-\\d{5}$";
    private static final String EPASTA_REGEX = "^[A-Za-z0-9+_.-]{1,64}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String TELEFONA_REGEX = "^\\d{8}$";
    private static final String SPECIALIZACIJAS_REGEX = "^[A-Za-zĀČĒĢĪĶĻŅŠŪŽāčēģīķļņšūž\\s-]{1,100}$";

    private static void nodrosinaTreneraSaglabasanu() {
        if (treneruList.isEmpty()) {
            loadTreneriFromFile();
        }
    }

    public static boolean vaiTreneraEpastsEksiste(String epasts) {
        nodrosinaTreneraSaglabasanu();

        for (String treneris : treneruList) {
            String[] trenerInfo = normalizeTrenerInfo(treneris.split(","));
            if (trenerInfo[2].equalsIgnoreCase(epasts.trim())) {
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

    private static boolean irDerigsPkods(String pkods) {
        return pkods != null && pkods.trim().length() <= 12 && pkods.trim().matches(PKODA_REGEX);
    }

    private static boolean irDerigaSpecializacija(String specializacija) {
        return specializacija != null && specializacija.trim().matches(SPECIALIZACIJAS_REGEX);
    }

    public static void treneruRegistresana() {
        nodrosinaTreneraSaglabasanu();

        Scanner scanner = new Scanner(System.in);

        String treneraVards;
        while (true) {
            System.out.print("Ievadiet savu vardu:");
            treneraVards = scanner.nextLine();

            if (irDerigsVards(treneraVards)) {
                treneraVards = treneraVards.trim();
                break;
            }

            System.out.println("Nepareizi ievadits vards. Garums lidz 60 simboliem.");
        }

        String treneraUzvards;
        while (true) {
            System.out.print("Ievadiet savu uzvardu:");
            treneraUzvards = scanner.nextLine();

            if (irDerigsUzvards(treneraUzvards)) {
                treneraUzvards = treneraUzvards.trim();
                break;
            }

            System.out.println("Nepareizi ievadits uzvards. Garums lidz 100 simboliem.");
        }

        String treneraEpasts;
        while (true) {
            System.out.println("Ievadiet e-pastu:");
            String treneraPastaievade = scanner.nextLine();

            if (irDerigsEpasts(treneraPastaievade)) {
                treneraEpasts = treneraPastaievade.trim();
                break;
            } else {
                System.out.println("Nepareizi ievadits e-pasts. Meginiet velreiz.");
            }
        }

        String treneraTelefons;
        while (true) {
            System.out.println("Ievadiet telefona numuru:");
            String ievadeTreneratelefons = scanner.nextLine();

            if (irDerigsTelefons(ievadeTreneratelefons)) {
                treneraTelefons = ievadeTreneratelefons.trim();
                break;
            } else {
                System.out.println("Nepareizi ievadits telefona numurs. Meginiet velreiz.");
            }
        }

        String treneraPkods;
        while (true) {
            System.out.println("Ievadiet personas kodu:");
            String input = scanner.nextLine();

            if (irDerigsPkods(input)) {
                treneraPkods = input.trim();
                break;
            } else {
                System.out.println("Nepareizi ievadits personas kods. Meginiet velreiz.");
            }
        }

        String treneraSpecializacija;
        while (true) {
            System.out.print("Ievadiet savu specializaciju:");
            treneraSpecializacija = scanner.nextLine();

            if (irDerigaSpecializacija(treneraSpecializacija)) {
                treneraSpecializacija = treneraSpecializacija.trim();
                break;
            }

            System.out.println("Nepareizi ievadita specializacija.");
        }

        if (vaiTreneraEpastsEksiste(treneraEpasts) || Lietotaji.vaiKlientaEpastsEksiste(treneraEpasts)) {
            System.out.println("Sis e-pasts jau ir registrets.");
            return;
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
            while (true) {
                System.out.println("Ievadiet savu e-pastu:");
                String ievaditaisEpasts = scanner.nextLine().trim();

                if (setCurrentTrenerByEmail(ievaditaisEpasts)) {
                    break;
                }

                System.out.println("E-pasts nav atrasts. Ievadiet velreiz.");
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
                int kontaIzvele = Main.readInt(scanner);
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
        int treneruIzvele = Main.readInt(scanner);
        if (treneruIzvele < 1 || treneruIzvele > treneruList.size()) {
            System.out.println("Nepareiza izvele. Meginiet velreiz.");
            return;
        }
    }

    public static void redigetTerneraprofilaDatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rediget profila datus");
        String ievaditaisEpasts;
        while (true) {
            System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");
            ievaditaisEpasts = scanner.nextLine().trim();

            if (currentTrainerEmail != null && !ievaditaisEpasts.equalsIgnoreCase(currentTrainerEmail)) {
                System.out.println("Sis nav jusu e-pasts. Ievadiet savu e-pastu velreiz.");
                continue;
            }

            break;
        }

        for (int i = 0; i < treneruList.size(); i++) {
            String[] trenerInfo = normalizeTrenerInfo(treneruList.get(i).split(","));

            if (trenerInfo[2].equalsIgnoreCase(ievaditaisEpasts.trim())) {
                String idPart = trenerInfo[0];

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

                String jaunsEpasts;
                while (true) {
                    System.out.println("Ievadiet jaunu e-pastu:");
                    jaunsEpasts = scanner.nextLine();

                    if (!irDerigsEpasts(jaunsEpasts)) {
                        System.out.println("Nepareizi ievadits e-pasts.");
                        continue;
                    }

                    if (!jaunsEpasts.trim().equalsIgnoreCase(ievaditaisEpasts.trim()) &&
                        (vaiTreneraEpastsEksiste(jaunsEpasts) || Lietotaji.vaiKlientaEpastsEksiste(jaunsEpasts))) {
                        System.out.println("Sis e-pasts jau ir registrets.");
                        continue;
                    }

                    jaunsEpasts = jaunsEpasts.trim();
                    break;
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

                String jaunsPkods;
                while (true) {
                    System.out.println("Ievadiet jaunu personas kodu:");
                    jaunsPkods = scanner.nextLine();

                    if (irDerigsPkods(jaunsPkods)) {
                        jaunsPkods = jaunsPkods.trim();
                        break;
                    }

                    System.out.println("Nepareizi ievadits personas kods.");
                }

                String jaunsSpecializacija;
                while (true) {
                    System.out.println("Ievadiet jaunu specializaciju:");
                    jaunsSpecializacija = scanner.nextLine();

                    if (irDerigaSpecializacija(jaunsSpecializacija)) {
                        jaunsSpecializacija = jaunsSpecializacija.trim();
                        break;
                    }

                    System.out.println("Nepareizi ievadita specializacija.");
                }

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
        boolean found;

        do {
            String ievaditaisEpasts = scanner.nextLine().trim();
            found = setCurrentTrenerByEmail(ievaditaisEpasts);

            if (!found) {
                System.out.println("E-pasts nav atrasts. Ievadiet velreiz.");
            }
        } while (!found);

        String[] trenerInfo = dabutTreneraInfoPecEpasta(currentTrainerEmail);

        System.out.println();
        System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + trenerInfo[1] + "!");
        System.out.println();
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
