import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

        private static ArrayList<String> pieteikumi = new ArrayList<>();
        private static final String filepathforPieteikumi = "csv/pieteikumi.csv";
        private static final String NOSAUKUMA_REGEX = "^.{1,50}$";
        private static final String SPORTA_VEIDA_REGEX = "^.{1,40}$";
        private static final String MUSKULU_GRUPAS_REGEX = "^.{1,20}$";
        private static final String DATUMA_REGEX = "^\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2}$";
        private static final String ILGUMA_REGEX = "^.{1,20}$";
        private static final String APRAKSTA_REGEX = "^.{1,300}$";
        private static final String BRIVO_VIETU_REGEX = "^\\d{1,3}$";

        private static void ensureTreniniLoaded() { // funkcija <ensureTreniniLoaded> neko nepieņem un atgriež <void> tipa vērtību <void>
                 if (treninuList.isEmpty()) {
                         loadTreniniFromFile();
                 }
                if (treninuList.isEmpty()) {
                        loadTreniniFromFile();
                }
        }

        private static String cleanValue(String value) { // funkcija <cleanValue> pieņem <String> tipa vērtību <value> un atgriež <String> tipa vērtību <result>
                if (value == null) {
                        return "";
                }
                return value.trim().replace(",", ";");
        }

        private static String stripPrefix(String value, String... prefixes) {
                if (value == null) {
                        return "";
                }

                String trimmed = value.trim();
                for (String prefix : prefixes) {
                        if (trimmed.toLowerCase().startsWith(prefix.toLowerCase())) {
                                return trimmed.substring(prefix.length()).trim();
                        }
                }
                return trimmed;
        }

        private static String[] getPlanParts(String line) { // funkcija <getPlanParts> pieņem <String> tipa vērtību <line> un atgriež <String[]> tipa vērtību <result>
                String[] parts = line.split(",", -1);
                String[] result = new String[10];

                for (int i = 0; i < result.length; i++) {
                        if (i < parts.length) {
                                result[i] = parts[i].trim();
                        } else {
                                result[i] = "";
                        }
                }

                result[0] = stripPrefix(result[0], "ID:");
                result[1] = stripPrefix(result[1], "Autors:", "Treneris:");
                result[2] = stripPrefix(result[2], "Nosaukums:");
                result[3] = stripPrefix(result[3], "Sporta veids:");
                result[4] = stripPrefix(result[4], "Grutibas pakape:", "Grutibas veids:");
                result[5] = stripPrefix(result[5], "Muskulu grupa:");
                result[6] = stripPrefix(result[6], "Trenina datums:");
                result[7] = stripPrefix(result[7], "Trenina ilgums:");
                result[8] = stripPrefix(result[8], "Trenina apraksts:");
                result[9] = stripPrefix(result[9], "Brivo vietu skaits:");

                return result;
        }

        private static boolean irDerigsTeksts(String teksts, String regex) { // funkcija <irDerigsTeksts> pieņem <String> tipa vērtību <teksts> un <String> tipa vērtību <regex> un atgriež <boolean> tipa vērtību <true> vai <false>
                return teksts != null && teksts.trim().matches(regex);
        }

        public static void treninuPlanaIevade() { // funkcija <treninuPlanaIevade> pieņem <String> tipa vērtību <treneraVards>, <treneraUzvards>, <treneraEpasts>, <treneraTelefons>, <treneraPkods>, <treneraSpecializacija> un atgriež <void> tipa vērtību <void>
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);

                String treneris = Treneri.getCurrentTrainerVardsUzvards();

                String treninaNosaukums;
                while (true) {
                        System.out.println("Ievadiet trenina nosaukumu:");
                        treninaNosaukums = scanner.nextLine();

                        if (irDerigsTeksts(treninaNosaukums, NOSAUKUMA_REGEX)) {
                                treninaNosaukums = treninaNosaukums.trim();
                                break;
                        }

                        System.out.println("Nepareizs trenina nosaukums.");
                }

                String sportaVeids;
                while (true) {
                        System.out.println("Izvelieties sporta veidu:");
                        System.out.println("1. Kalistenika");
                        System.out.println("2. Klinsu kapsana");
                        System.out.println("3. Smaga atletika");
                        System.out.println("4. Fitness");
                        System.out.println("5. Crossfit");
                        String sportaVeidaIzvele = scanner.nextLine().trim();

                        if (sportaVeidaIzvele.equals("1")) {
                                sportaVeids = "Kalistenika";
                                break;
                        }
                        if (sportaVeidaIzvele.equals("2")) {
                                sportaVeids = "Klinsu kapsana";
                                break;
                        }
                        if (sportaVeidaIzvele.equals("3")) {
                                sportaVeids = "Smaga atletika";
                                break;
                        }
                        if (sportaVeidaIzvele.equals("4")) {
                                sportaVeids = "Fitness";
                                break;
                        }
                        if (sportaVeidaIzvele.equals("5")) {
                                sportaVeids = "Crossfit";
                                break;
                        }

                        System.out.println("Nepareiza izvele! Meginiet velreiz.");
                }

                String grutibasPakape;
                while (true) {
                        System.out.println("Ievadiet grutibas pakapi(viegli, videji, gruti):");
                        grutibasPakape = scanner.nextLine().trim().toLowerCase();

                        if (grutibasPakape.equals("viegli") || grutibasPakape.equals("videji") || grutibasPakape.equals("gruti")) {
                                break;
                        }

                        System.out.println("Nepareiza ievade! Meginiet velreiz.");
                }
                

                String muskuluGrupa;
                while (true) {
                        System.out.println("Ievadiet muskulu grupu:");
                        muskuluGrupa = scanner.nextLine();

                        if (irDerigsTeksts(muskuluGrupa, MUSKULU_GRUPAS_REGEX)) {
                                muskuluGrupa = muskuluGrupa.trim();
                                break;
                        }

                        System.out.println("Nepareizi ievadita muskulu grupa. Garums lidz 20 simboliem.");
                }

                String treninaDatums;
                while (true) {
                        System.out.println("Ievadiet trenina datumu:");
                        treninaDatums = scanner.nextLine();

                        if (irDerigsTeksts(treninaDatums, DATUMA_REGEX)) {
                                treninaDatums = treninaDatums.trim();
                                break;
                        }

                        System.out.println("Nepareizs datums. Lietojiet formatu dd.MM.yyyy HH:mm");
                }

                String treninaIlgums;
                while (true) {
                        System.out.println("Ievadiet trenina ilgumu:");
                        treninaIlgums = scanner.nextLine();

                        if (irDerigsTeksts(treninaIlgums, ILGUMA_REGEX)) {
                                treninaIlgums = treninaIlgums.trim();
                                break;
                        }

                        System.out.println("Nepareizi ievadits trenina ilgums.");
                }

                String treninaApraksts;
                while (true) {
                        System.out.println("Ievadiet trenina aprakstu:");
                        treninaApraksts = scanner.nextLine();

                        if (irDerigsTeksts(treninaApraksts, APRAKSTA_REGEX)) {
                                treninaApraksts = treninaApraksts.trim();
                                break;
                        }

                        System.out.println("Nepareizs trenina apraksts. Garums lidz 300 simboliem.");
                }

                String brivoVietuSkaits;
                while (true) {
                        System.out.println("Ievadiet brivo vietu skaitu:");
                        brivoVietuSkaits = scanner.nextLine();

                        if (irDerigsTeksts(brivoVietuSkaits, BRIVO_VIETU_REGEX) && Integer.parseInt(brivoVietuSkaits.trim()) > 0) {
                                brivoVietuSkaits = brivoVietuSkaits.trim();
                                break;
                        }

                        System.out.println("Nepareizs brivo vietu skaits.");
                }

                String[] fields = {treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits};
                if (!validateAizpilditieLauki(fields)) {
                        return;
                }

                int newID = getIDtreninam() + 1;

                String treninaData = newID + "," +
                                cleanValue(treneris) + "," +
                                cleanValue(treninaNosaukums)  + "," +
                                cleanValue(sportaVeids) + "," +
                                cleanValue(grutibasPakape) + "," +
                                cleanValue(muskuluGrupa) + "," +
                                cleanValue(treninaDatums) + "," +
                                cleanValue(treninaIlgums) + "," +
                               cleanValue(treninaApraksts) + "," +
                                cleanValue(brivoVietuSkaits);

                treninuList.add(treninaData);
                updateFileTrenini();
                System.out.println("Treninu plans veiksmigi pievienots!");
        }

        public static int getIDtreninam() { // funkcija <getIDtreninam> neko nepieņem un atgriež <int> tipa vērtību <id>
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

        public static void updateFileTrenini() { // funkcija <updateFileTrenini> neko nepieņem un atgriež <void> tipa vērtību <void>
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

        public static void loadTreniniFromFile() { // funkcija <loadTreniniFromFile> neko nepieņem un atgriež <void> tipa vērtību <void>
                treninuList.clear();

                File file = new File(filepathforTrenini);
                if (!file.exists()) {
                        return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        reader.readLine();

                        String currentRecord = "";
                        while ((line = reader.readLine()) != null) {
                                if (line.trim().isEmpty()) {
                                        continue;
                                }

                                if (currentRecord.isEmpty()) {
                                        currentRecord = line.trim();
                                } else {
                                        currentRecord += " " + line.trim();
                                }

                                // If this line ends without a trailing comma, it is the final line of a record.
                                if (!line.trim().endsWith(",")) {
                                        String[] parts = currentRecord.split(",", -1);
                                        if (parts.length >= 10) {
                                                treninuList.add(currentRecord.trim());
                                        }
                                        currentRecord = "";
                                }
                        }

                        if (!currentRecord.isEmpty()) {
                                String[] parts = currentRecord.split(",", -1);
                                if (parts.length >= 10) {
                                        treninuList.add(currentRecord.trim());
                                }
                        }
                } catch (IOException e) {
                        System.out.println("Kluda ieladejot treninu planus: " + e.getMessage());
                }
        }

        public static void loadPieteikumiFromFile() { // funkcija <loadPieteikumiFromFile> neko nepieņem un atgriež <void> tipa vērtību <void>
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

        public static void searchByTypeandDifficulty() { // funkcija <searchByTypeandDifficulty> neko nepieņem un atgriež <void> tipa vērtību <void>
                ensureTreniniLoaded();
                Scanner scanner = new Scanner(System.in);

                System.out.println("Filtret pec:");
                System.out.println("1. Sporta veida");
                System.out.println("2. Grutibas pakapes");
                String filtraIzvele = scanner.nextLine().trim();

                boolean found = false;
                System.out.println("-----ATRASTIE TRENINI-----");

                if (filtraIzvele.equals("1")) {
                        String sport = "";

                        while (true) {
                                System.out.println("Izvelieties sporta veidu:");
                                System.out.println("1. Kalistenika");
                                System.out.println("2. Klinsu kapsana");
                                System.out.println("3. Smaga atletika");
                                System.out.println("4. Fitness");
                                System.out.println("5. Crossfit");
                                String sportaIzvele = scanner.nextLine().trim();

                                if (sportaIzvele.equals("1")) {
                                        sport = "kalistenika";
                                        break;
                                }
                                if (sportaIzvele.equals("2")) {
                                        sport = "klinsu kapsana";
                                        break;
                                }
                                if (sportaIzvele.equals("3")) {
                                        sport = "smaga atletika";
                                        break;
                                }
                                if (sportaIzvele.equals("4")) {
                                        sport = "fitness";
                                        break;
                                }
                                if (sportaIzvele.equals("5")) {
                                        sport = "crossfit";
                                        break;
                                }

                                System.out.println("Nepareiza izvele! Meginiet velreiz.");
                        }

                        for (String line : treninuList) {
                                String[] parts = getPlanParts(line);

                                if (parts[3].toLowerCase().contains(sport)) {
                                        printPlan(parts);
                                        found = true;
                                }
                        }
                } else if (filtraIzvele.equals("2")) {
                        String difficulty = "";

                        while (true) {
                                System.out.println("Izvelieties grutibas pakapi:");
                                System.out.println("1. Viegli");
                                System.out.println("2. Videji");
                                System.out.println("3. Gruti");
                                String grutibasIzvele = scanner.nextLine().trim();

                                if (grutibasIzvele.equals("1")) {
                                        difficulty = "viegli";
                                        break;
                                }
                                if (grutibasIzvele.equals("2")) {
                                        difficulty = "videji";
                                        break;
                                }
                                if (grutibasIzvele.equals("3")) {
                                        difficulty = "gruti";
                                        break;
                                }

                                System.out.println("Nepareiza izvele! Meginiet velreiz.");
                        }

                        for (String line : treninuList) {
                                String[] parts = getPlanParts(line);

                                if (parts[4].toLowerCase().contains(difficulty)) {
                                        printPlan(parts);
                                        found = true;
                                }
                        }
                } else {
                        System.out.println("Nepareiza izvele.");
                        return;
                }

                if (!found) {
                        System.out.println("Neviens treninu plans netika atrasts.");
                }
        }

        private static void printPlan(String[] parts) { // funkcija <printPlan> pieņem <String[]> tipa vērtību <parts> un atgriež <void> tipa vērtību <void>
                System.out.println("ID: " + parts[0]);
                System.out.println("Autors: " + parts[1]);
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

        public static void paradaTreninuPlanus() { // funkcija <paradaTreninuPlanus> neko nepieņem un atgriež <void> tipa vērtību <void>
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

        public static boolean isUserPieteiciesTreninam(String userID, int treninaID) { // funkcija <isUserPieteiciesTreninam> pieņem <String> tipa vērtību <userID> un <int> tipa vērtību <treninaID> un atgriež <boolean> tipa vērtību <true> vai <false>
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

        public static void paradaPieteikusosTreninus() { // funkcija <paradaPieteikusosTreninus> pieņem <String> tipa vērtību <userEmail> un atgriež <void> tipa vērtību <void>
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

        public static boolean parbaudaVaiTreninsPilns(String treninaData) { // funkcija <parbaudaVaiTreninsPilns> pieņem <String> tipa vērtību <treninaData> un atgriež <boolean> tipa vērtību <true> vai <false>
                String[] parts = getPlanParts(treninaData);
                int brivasVietas = Integer.parseInt(parts[9]);
                return brivasVietas <= 0;
        }

        public static void updateBrivasVietas(int treninaID) { // funkcija <updateBrivasVietas> pieņem <int> tipa vērtību <treninaID> un atgriež <void> tipa vērtību <void>
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

        public static void izveletiesTreninuPlanuLietotajam() { // funkcija <izveletiesTreninuPlanuLietotajam> neko nepieņem un atgriež <void> tipa vērtību <void>
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

        public static void pieteiktiesTreninam(String userID, int treninaID, boolean isTreneris) { // funkcija <pieteiktiesTreninam> pieņem <String> tipa vērtību <userID>, <int> tipa vērtību <treninaID> un <boolean> tipa vērtību <isTreneris> un atgriež <void> tipa vērtību <void>
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

        public static void updatePieteikumiFailu() { // funkcija <updatePieteikumiFailu> neko nepieņem un atgriež <void> tipa vērtību <void>
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

        public static void dzestTrenanPlanu(int treninaID, boolean isTreneris) { // funkcija <dzestTrenanPlanu> pieņem <int> tipa vērtību <treninaID> un <boolean> tipa vērtību <isTreneris> un atgriež <void> tipa vērtību <void>
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

        public static boolean validateAizpilditieLauki(String[] fields) { // funkcija <validateAizpilditieLauki> pieņem <String[]> tipa vērtību <fields> un atgriež <boolean> tipa vērtību <true> vai <false>
                for (String field : fields) {
                        if (field == null || field.trim().isEmpty()) {
                                System.out.println("Visiem laukiem jabut aizpilditiem!");
                                return false;
                        }
                }

                return true;
        }

        public static boolean validateRakstzimjuGarumu(String input, int minLenght) { // funkcija <validateRakstzimjuGarumu> pieņem <String> tipa vērtību <input> un <int> tipa vērtību <minLenght> un atgriež <boolean> tipa vērtību <true> vai <false>
                if (input == null) {
                        return false;
                }
                return input.trim().length() >= minLenght;
        }
}
