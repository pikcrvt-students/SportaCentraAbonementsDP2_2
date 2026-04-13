import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

        private static ArrayList<String> pieteikumi = new ArrayList<>();
        private static final String filepathforPieteikumi = "csv/pieteikumi.csv";

        private static void ensureTreniniLoaded() {
                if (treninuList.isEmpty()) {
                        loadTreniniFromFile();
                }
        }

        private static String cleanValue(String value) {
                if (value == null) {
                        return "";
                }
                return value.trim().replace(",", ";");
        }

        private static String[] getPlanParts(String line) {
                String[] parts = line.split(",", -1);
                String[] result = new String[10];

                for (int i = 0; i < result.length; i++) {
                        if (i < parts.length) {
                                result[i] = parts[i].trim();
                        } else {
                                result[i] = "";
                        }
                }

                return result;
        }

        public static void treninuPlanaIevade() {
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);

                String treneris = Treneri.getCurrentTrainerVardsUzvards();

                System.out.println("Ievadiet trenina nosaukumu:");
                String treninaNosaukums = scanner.nextLine();

                System.out.println("Ievadiet sporta veidu:");
                String sportaVeids = scanner.nextLine();

                String grutibasPakape;
                while (true) {
                        System.out.println("Ievadiet grutibas pakapi(viegli, videji, gruti):");
                        grutibasPakape = scanner.nextLine().trim().toLowerCase();

                        if (grutibasPakape.equals("viegli") || grutibasPakape.equals("videji") || grutibasPakape.equals("gruti")) {
                                break;
                        }

                        System.out.println("Nepareiza ievade! Meginiet velreiz.");
                }
                

                System.out.println("Ievadiet muskulu grupu:");
                String muskuluGrupa = scanner.nextLine();

                System.out.println("Ievadiet trenina datumu:");
                String treninaDatums = scanner.nextLine();

                System.out.println("Ievadiet trenina ilgumu:");
                String treninaIlgums = scanner.nextLine();

                System.out.println("Ievadiet trenina aprakstu:");
                String treninaApraksts = scanner.nextLine();

                System.out.println("Ievadiet brivo vietu skaitu:");
                String brivoVietuSkaits = scanner.nextLine();

                String[] fields = {treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits};
                if (!validateAizpilditieLauki(fields)) {
                        return;
                }

                int newID = getIDtreninam() + 1;

                String treninaData = newID + "," +
                                "Autors: " + cleanValue(treneris) + "," + "\n" +
                                "Nosaukums: " + cleanValue(treninaNosaukums)  + "," + "\n" +
                                "Sporta veids: " + cleanValue(sportaVeids) + "," + "\n" +
                                "Grutibas veids: " + cleanValue(grutibasPakape) + "," + "\n" +
                                "Muskulu grupa: " + cleanValue(muskuluGrupa) + "," + "\n" +
                                "Trenina datums: " + cleanValue(treninaDatums) + "," + "\n" +
                                "Trenina ilgums: " + cleanValue(treninaIlgums) + "," + "\n" +
                               "Trenina apraksts: " + cleanValue(treninaApraksts) + "," + "\n" +
                                "Brivo vietu skaits: " + cleanValue(brivoVietuSkaits);

                treninuList.add(treninaData);
                updateFileTrenini();
                System.out.println("Treninu plans veiksmigi pievienots!");
        }

        public static int getIDtreninam() {
                ensureTreniniLoaded();

                if (treninuList.isEmpty()) {
                        return 0;
                }

                String[] parts = getPlanParts(treninuList.get(treninuList.size() - 1));

                try {
                        return Integer.parseInt(parts[0]);
                } catch (NumberFormatException e) {
                        return treninuList.size();
                }
        }

        public static void updateFileTrenini() {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepathforTrenini))) {
                        writer.write("id, treneris, treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits");
                        writer.newLine();

                        for (String trenins : treninuList) {
                                writer.write(trenins);
                                writer.newLine();
                        }
                } catch (IOException e) {
                        System.out.println("Kluda saglabajot treninu planus: " + e.getMessage());
                }
        }

        public static void loadTreniniFromFile() {
                treninuList.clear();

                File file = new File(filepathforTrenini);
                if (!file.exists()) {
                        return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        reader.readLine();

                        while ((line = reader.readLine()) != null) {
                                if (line.trim().isEmpty()) {
                                        continue;
                                }

                                String[] parts = line.split(",", -1);
                                if (parts.length >= 10) {
                                        treninuList.add(line.trim());
                                }
                        }
                } catch (IOException e) {
                        System.out.println("Kluda ieladejot treninu planus: " + e.getMessage());
                }
        }

        public static void loadPieteikumiFromFile() {
                pieteikumi.clear();

                File file = new File(filepathforPieteikumi);
                if (!file.exists()) {
                        return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        reader.readLine();

                        while ((line = reader.readLine()) != null) {
                                if (!line.trim().isEmpty()) {
                                        pieteikumi.add(line.trim());
                                }
                        }
                } catch (IOException e) {
                        System.out.println("Kluda ieladejot pieteikumus: " + e.getMessage());
                }
        }

        public static void searchByTypeandDifficulty() {
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);

                System.out.println("Ievadiet sporta veidu:");
                String sport = scanner.nextLine().trim().toLowerCase();

                System.out.println("Ievadiet grutibas pakapi:");
                String difficulty = scanner.nextLine().trim().toLowerCase();

                boolean found = false;
                System.out.println("-----ATRASTIE TRENINI-----");

                for (String line : treninuList) {
                        String[] parts = getPlanParts(line);

                        if (parts[3].toLowerCase().equals(sport) && parts[4].toLowerCase().equals(difficulty)) {
                                printPlan(parts);
                                found = true;
                        }
                }

                if (!found) {
                        System.out.println("Trenins un/vai grutibas pakape netika atrasta.");
                }
        }

        private static void printPlan(String[] parts) {
                System.out.println("ID: " + parts[0]);
                System.out.println("Treneris: " + parts[1]);
                System.out.println("Nosaukums: " + parts[2]);
                System.out.println("Sporta veids: " + parts[3]);
                System.out.println("Grutibas pakape: " + parts[4]);
                System.out.println("Muskulu grupa: " + parts[5]);
                System.out.println("Datums: " + parts[6]);
                System.out.println("Ilgums: " + parts[7]);
                System.out.println("Apraksts: " + parts[8]);
                System.out.println("Brivas vietas: " + parts[9]);
                System.out.println("-----------------------------");
        }

        public static void paradaTreninuPlanus() {
                ensureTreniniLoaded();
                System.out.println("-----PIEEJAMIE TRENINU PLANI-----");

                if (treninuList.isEmpty()) {
                        System.out.println("Nav pievienots neviens treninu plans!");
                        return;
                }

                for (String line : treninuList) {
                        printPlan(getPlanParts(line));
                }
        }

        public static boolean isUserPieteiciesTreninam(String userID, int treninaID) {
                for (String pieteikums : pieteikumi) {
                        String[] parts = pieteikums.split(",", -1);

                        if (parts.length < 2) {
                                continue;
                        }

                        if (parts[0].trim().equalsIgnoreCase(userID.trim())) {
                                int id = Integer.parseInt(parts[1].trim());
                                if (id == treninaID) {
                                        return true;
                                }
                        }
                }

                return false;
        }

        public static void paradaPieteikusosTreninus() {
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);
                String userEmail = Lietotaji.getCurrentUserEmail();

                System.out.println("-----TU ESI PIETEICIES SAJOS TRENINOS!-----");

                if (userEmail == null) {
                        System.out.println("Vispirms piesledzies klienta kontam.");
                        return;
                }

                boolean found = false;

                for (String line : treninuList) {
                        String[] parts = getPlanParts(line);
                        int treninaID = Integer.parseInt(parts[0]);

                        if (isUserPieteiciesTreninam(userEmail, treninaID)) {
                                printPlan(parts);
                                found = true;
                        }
                }

                if (!found) {
                        System.out.println("Tu neesi pieteicies nevienam treninam!");
                }

                System.out.println("Atpakal?");
                System.out.println("1. Ja");
                System.out.println("2. Ne");

                String izvele = scanner.nextLine();
                if (izvele.equals("1")) {
                        Main.klientaIzvelne(new String[0]);
                }
        }

        public static boolean parbaudaVaiTreninsPilns(String treninaData) {
                String[] parts = getPlanParts(treninaData);
                int brivasVietas = Integer.parseInt(parts[9]);
                return brivasVietas <= 0;
        }

        public static void updateBrivasVietas(int treninaID) {
                for (int i = 0; i < treninuList.size(); i++) {
                        String[] parts = getPlanParts(treninuList.get(i));
                        int id = Integer.parseInt(parts[0]);

                        if (id == treninaID) {
                                int brivasVietas = Integer.parseInt(parts[9]);
                                brivasVietas--;
                                parts[9] = String.valueOf(brivasVietas);
                                treninuList.set(i, String.join(",", parts));
                                updateFileTrenini();
                                return;
                        }
                }
        }

        public static void izveletiesTreninuPlanuLietotajam() {
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);
                String userID = Lietotaji.getCurrentUserEmail();

                if (userID == null) {
                        System.out.println("Vispirms piesledzies klienta kontam.");
                        return;
                }

                if (treninuList.isEmpty()) {
                        System.out.println("Nav pieejamu treninu planu.");
                        return;
                }

                paradaTreninuPlanus();
                System.out.println("Ievadiet trenina ID, kuram velaties pieteikties:");

                if (!scanner.hasNextInt()) {
                        System.out.println("Nepareizi ievadits trenina ID.");
                        return;
                }

                int treninaID = scanner.nextInt();
                scanner.nextLine();
                pieteiktiesTreninam(userID, treninaID, false);
        }

        public static void pieteiktiesTreninam(String userID, int treninaID, boolean isTreneris) {
                if (isTreneris) {
                        System.out.println("Treneri nevar pieteikties treniniem!");
                        return;
                }

                String treninaData = null;

                for (String line : treninuList) {
                        String[] parts = getPlanParts(line);
                        int id = Integer.parseInt(parts[0]);

                        if (id == treninaID) {
                                treninaData = line;
                                break;
                        }
                }

                if (treninaData == null) {
                        System.out.println("Trenins ar ID " + treninaID + " netika atrasts.");
                        return;
                }

                if (isUserPieteiciesTreninam(userID, treninaID)) {
                        System.out.println("Tu jau esi pieteicies sim treninam.");
                        return;
                }

                if (parbaudaVaiTreninsPilns(treninaData)) {
                        System.out.println("Sis trenins ir pilns. Izvelieties citu treninu.");
                        return;
                }

                String pieteikums = userID + "," + treninaID;
                pieteikumi.add(pieteikums);
                updatePieteikumiFailu();
                updateBrivasVietas(treninaID);

                System.out.println("Tu esi veiksmigi pieteicies treninam ar ID " + treninaID + ".");
        }

        public static void updatePieteikumiFailu() {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepathforPieteikumi))) {
                        writer.write("userID,treninaID");
                        writer.newLine();

                        for (String pieteikums : pieteikumi) {
                                writer.write(pieteikums);
                                writer.newLine();
                        }
                } catch (IOException e) {
                        System.out.println("Radas kluda rakstot pieteikumu faila: " + e.getMessage());
                }
        }

        public static void dzestTrenanPlanu(int treninaID, boolean isTreneris) {
                if (!isTreneris) {
                        System.out.println("Tikai treneri var dzest treninu planus!.");
                        return;
                }

                for (int i = 0; i < treninuList.size(); i++) {
                        String[] parts = getPlanParts(treninuList.get(i));
                        int id = Integer.parseInt(parts[0]);

                        if (id == treninaID) {
                                treninuList.remove(i);
                                updateFileTrenini();
                                System.out.println("Treninu plans ar ID " + treninaID + " ir dzests.");
                                return;
                        }
                }

                System.out.println("Treninu plans ar ID " + treninaID + " netika atrasts.");
        }

        public static boolean validateAizpilditieLauki(String[] fields) {
                for (String field : fields) {
                        if (field == null || field.trim().isEmpty()) {
                                System.out.println("Visiem laukiem jabut aizpilditiem!");
                                return false;
                        }
                }

                return true;
        }

        public static boolean validateRakstzimjuGarumu(String input, int minLenght) {
                if (input == null) {
                        return false;
                }
                return input.trim().length() >= minLenght;
        }
}
