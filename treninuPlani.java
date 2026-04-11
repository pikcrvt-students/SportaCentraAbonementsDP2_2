import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

        private static ArrayList<String> pieteikumi = new ArrayList<>();
        private static final String filepathforPieteikumi = "csv/pieteikumi.csv";

        /*------------------TRENIŅA IZVEIDE-------------------------- */

        public static void treninuPlanaIevade() {  /*funkcija treninuPlanaIevade pieņem string tipa vērtības treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits un pieņem int tipa vērtības treninaID, un
atgriež void */
                Scanner scanner = new Scanner(System.in);

                System.out.println("Ievadiet trenina nosaukumu:");
                String treninaNosaukums = scanner.nextLine();
                System.out.println("Ievadiet sporta veidu (klinsu kapsana, kalistenika):");
                String sportaVeids = scanner.nextLine();
                System.out.println("Ievadiet grutibas pakapi (viegls, videjs, gruts):");
                String grutibasPakape = scanner.nextLine();
                System.out.println("Ievadiet muskuļu grupu:");
                String muskuluGrupa = scanner.nextLine();
                System.out.println("Ievadiet trenina datumu: ");
                String treninaDatums = scanner.nextLine();
                System.out.println("Ievadiet trenina ilgumu:");
                String treninaIlgums = scanner.nextLine();
                System.out.println("Ievadiet trenina aprakstu: ");
                String treninaApraksts = scanner.nextLine();
                System.out.println("Ievadiet brivo vietu skaitu:");
                String brivoVietuSkaits = scanner.nextLine();
                int newID = getIDtreninam() + 1;

                String treninaData = newID+ ". " + "Trenina nosaukums: "  + treninaNosaukums + "\n" + "Sporta veids: " + sportaVeids + "\n" + "Grutibas pakape: " + grutibasPakape + "\n" + "Muskulu grupa: " + muskuluGrupa + "\n" + "Trenina datums: " + treninaDatums + "\n" + "Trenina ilgums: " + treninaIlgums + "\n" + "Trenina apraksts: " + treninaApraksts + "\n" + "Brivas vietas: " + brivoVietuSkaits;
                treninuList.add(treninaData);

                updateFileTrenini();

                scanner.close();
        }

        /*------------------TRENIŅA ID PIEŠĶIRŠANA-------------------------- */

        public static int getIDtreninam() {  /* Funkcija getIDtreninam atgriež int tipa vērtību idPart */
                if (treninuList.isEmpty()) {
                        return 0;
                }

                String lastLine = treninuList.get(treninuList.size() - 1);
                String[] parts = lastLine.split(",");
                String idPart = parts[0].trim();
                if (idPart.endsWith(".")) {
                        idPart = idPart.substring(0, idPart.length() - 1).trim();
                }
                try {
                        return Integer.parseInt(idPart);
                } catch (NumberFormatException e) {
                        return 0;
                }
        }

        /*------------------TRENIŅA FAILA (CSV) SAGLABĀŠANA-------------------------- */
        
        public static void updateFileTrenini() { /* funkcija updateFileTrenini pieņem string tipa vērtību id, treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits un
atgriež void */
                
                try {
                        BufferedWriter writer = new BufferedWriter(
                                        new FileWriter(filepathforTrenini));

                        writer.write("id, treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits");
                        writer.newLine();

                        for (String trenina : treninuList) {
                                writer.write(trenina);
                                writer.newLine();
                        }

                        writer.close();
                
                } catch ( IOException e ) {
                        
                        System.out.println( "Kluda rakstot šo kodu: " + e.getMessage() );
                }


        }

        /*---------------------TRENIŅU FILTRĒŠANA---------------------- */

        public static void searchByTypeandDifficulty() { /* funkcija searchByTypeandDifficulty pieņem string tipa vērtību Sport un Difficulty, un
atgriež void */

                Scanner scanner = new Scanner(System.in);

                System.out.println("Ievadiet sporta veidu: ");
                String Sport = scanner.nextLine(). trim(). toLowerCase();

                System.out.println("Ievadiet grutibas pakapi: ");
                String Difficulty = scanner.nextLine(). trim(). toLowerCase();

                boolean found = false;

                        System.out.println("-----ATRASTIE TRENINI-----");

                for (String line: treninuList) {
                        
                        String[] parts = line.split(",");
                        if (parts.length < 9) continue;

                        String sportaVeids = parts[2].trim().toLowerCase();
                        String grutibasPakape = parts[3].trim().toLowerCase();

                        if (sportaVeids.equals(Sport) && grutibasPakape.equals(Difficulty)) {
                                        System.out.println(line);
                        found = true;
                        }
                              }
                if (!found) {
                System.out.println("Trenins un/vai grutibas pakape netika atrasta.");
                        }
                scanner.close();
                }

                /*---------------------TRENIŅU PLĀNU IZVADE---------------------- */

                public static void paradaTreninuPlanus() {  //*parāda visus treniņus *// 
                        System.out.println("-----PIEEJAMIE TRENINU PLANI-----");

                        if (treninuList.isEmpty()) {
                                System.out.println("Nav pievienots neviens treninu plans!");
                                return;
                        }

                        for (String line : treninuList) {
                                String[] parts = line.split(",");

                                System.out.println("ID: " + parts[0]);
                                System.out.println("Nosaukums: " + parts[1]);
                                System.out.println("Sporta veids: " + parts[2]);
                                System.out.println("Grutibas pakape: " + parts[3]);
                                System.out.println("Muskulu grupa: " + parts[4]);
                                System.out.println("Datums: " + parts[5]);
                                System.out.println("Ilgums: " + parts[6]);
                                System.out.println("Apraksts: " + parts[7]);
                                System.out.println("Brivas vietas: " + parts[8]);

                        
                                System.out.println("-----------------------------");
                        }
                }

                /*-------------------PĀRBAUDA PIETEIKUMU--------------------- */

                public static boolean isUserPieteiciesTreninam(int treninaID) {
                        for (String pieteikums : pieteikumi) {
                                String[] parts = pieteikums.split(",");
                                if (parts.length < 2) continue;

                                int id = Integer.parseInt(parts[1].trim());
                                if (id == treninaID) {
                                        return true;
                                }
                        }
                        return false;
                }

                /*---------------------PARĀDA TRENIŅUS, KUROS IR PIETEICIES---------------------- */

                public static void paradaPieteikusosTreninus () {
                        Scanner scanner = new Scanner(System.in);

                        System.out.println("-----TU ESI PIETEICIES SAJOS TRENINOS!-----");

                        boolean found = false;

                        for (String line : treninuList) {
                                
                               String[] parts = line.split(",");
                               int treninaID = Integer.parseInt(parts[0].trim());

                               if (isUserPieteiciesTreninam(treninaID)) {
                                        System.out.println(line);
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
                        switch (izvele) {
                                case "1":
                                        Main.klientaIzvelne(new String[0]);
                                        break;
                                case "2":
                                        break;
                                default:
                                        System.out.println("Nepareiza izvele.");
                        }
                }


                        




                        /*---------------PĀRBAUD VAI TRENIŅŠ IR PILNS---------------------- */

                        public static boolean parbaudaVaiTreninsPilns(String treninaData) {
                                String[] parts = treninaData.split(",");
                                int brivasVietas = Integer.parseInt(parts[8].trim());
                                return brivasVietas <= 0;
                        }

                        /*---------------ATJAUNO BRĪVĀS VIETAS---------------------- */

                        public static void updateBrivasVietas(int treninaID) {
                                
                                for (int i = 0; i < treninuList.size(); i++) {
                                        String[] parts = treninuList.get(i).split(",");
                                        int id = Integer.parseInt(parts[0].trim());

                                        if (id == treninaID) {

                                                int brivasVietas = Integer.parseInt(parts[8].trim());
                                                brivasVietas--;
                                                parts[8] = String.valueOf(brivasVietas);

                                                String updatedTreninaData = String.join(",", parts);
                                                treninuList.set(i, updatedTreninaData);
                                                updateFileTrenini();
                                                return;
                                        }
                        }

                }

                 /*---------------------PIETEIKŠANĀS TRENIŅAM---------------------- */

                public static void pieteiktiesTreninam (String userID, int treninaID, boolean isTreneris) {

                        if (isTreneris) {
                                System.out.println("Treneri nevar pieteikties treniniem!");
                                return;
                        }

                        String treninaData = null;

                        for (String line : treninuList) {
                                String[] parts = line.split(", ");
                                int id = Integer.parseInt(parts[0].trim());

                                if (id == treninaID) {
                                        treninaData = line;
                                        break;
                                }
                        }

                       if (treninaData == null) {

                        System.out.println("Treniņš ar ID " + treninaID + " netika atrasts.");
                        return;
                }

                        if (parbaudaVaiTreninsPilns(treninaData)) {
                                System.out.println("'Sis trenins ir pilns. Izvelieties citu treninu.");
                                return;
                        }

                        String pieteikums = userID + "," + treninaID;
                        pieteikumi.add(pieteikums);
                        updatePieteikumiFailu();

                        updateBrivasVietas(treninaID);
                        System.out.println("Tu esi veiksmigi pieteicies treninam ar ID " + treninaID + ".");
                }

                /*---------------------PIETEIKUMU FAILA (CSV) SAGLABĀŠANA---------------------- */

                public static void updatePieteikumiFailu() {

                        try {

                                BufferedWriter writer = new BufferedWriter(new FileWriter(filepathforPieteikumi));

                                writer.write("userID, treninaID");
                                writer.newLine();

                                for (String pieteikums : pieteikumi) {
                                        writer.write(pieteikums);
                                        writer.newLine();
                                }

                                writer.close();
                        } catch (IOException e) {
                                System.out.println("Radas kluda rakstot pieteikum faila: " + e.getMessage());
                }
        }

                /*---------------------TRENINU PLĀNU DZĒŠANA---------------------- */

                public static void dzestTrenanPlanu(int treninaID, boolean isTreneris) {

                        if(!isTreneris) {

                                System.out.println("Tikai treneri var dzest treninu planus!.");
                                return; 
                        }

                        boolean found = false;

                        for (int i = 0; i < treninuList.size(); i++) {
                                String[] parts = treninuList.get(i).split(",");
                                int id = Integer.parseInt(parts[0].trim());

                                if (id == treninaID) {
                                        treninuList.remove(i);
                                        updateFileTrenini();
                                        System.out.println("Treninu plans ar ID " + treninaID + " ir dzests.");
                                        found = true;
                                        break;
                                }
                        }

                        if (!found) {
                                System.out.println("Treninu plans ar ID " + treninaID + " netika atrasts.");

                        }
                }

                /*------------------TRENIŅA PLĀNA VALIDĀCIJA (TUKŠU LAUKU PĀRBAUDE UN RAKSTZĪMJU IEROBEŽOJUMI)------------------------ */

                        public static boolean validateAizpilditieLauki(String fields[]) {

                                for (String field : fields) {
                                        if (field == null || field.trim().isEmpty()) {
                                                System.out.println("Visiem laukiem jabut aizpilditiem!");
                                                return false;
                                        }
                                }

                                return true;

        }

                        public static boolean validateRakstzimjuGarumu (String input, int minLenght) {

                                if (input == null) return false;
                                return input.trim().length() >= minLenght;
                        }

                }
