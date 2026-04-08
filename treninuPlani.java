import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

        private static ArrayList<String> pieteikumi = new ArrayList<>();
        private static final String filepathforPieteikumi = "csv/pieteikumi.csv";

        /*------------------TRENIŅA IZVEIDE-------------------------- */

        public static void treninuPlanaIevade() {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Ievadiet treniņa nosaukumu:");
                String treninaNosaukums = scanner.nextLine();
                System.out.println("Ievadiet sporta veidu(klinšu kāpšana, kalistēnika):");
                String sportaVeids = scanner.nextLine();
                System.out.println("Ievadiet grūtības pakāpi(viegls, vidējs, grūts):");
                String grutibasPakape = scanner.nextLine();
                System.out.println("Ievadiet muskuļu grupu:");
                String muskuluGrupa = scanner.nextLine();
                System.out.println("Ievadiet treniņa datumu: ");
                String treninaDatums = scanner.nextLine();
                System.out.println("Ievadiet treniņa ilgumu:");
                String treninaIlgums = scanner.nextLine();
                System.out.println("Ievadiet treniņa aprakstu: ");
                String treninaApraksts = scanner.nextLine();
                System.out.println("Ievadiet brīvo vietu skaitu:");
                String brivoVietuSkaits = scanner.nextLine();
                int newID = getIDtreninam() + 1;

                String treninaData = newID + "," + treninaNosaukums + "," + sportaVeids + "," + grutibasPakape + "," + muskuluGrupa + "," + treninaDatums + "," + treninaIlgums + ","
                                + treninaApraksts + "," + brivoVietuSkaits;
                treninuList.add(treninaData);

                updateFileTrenini();

                scanner.close();
        }

        /*------------------TRENIŅA ID PIEŠĶIRŠANA-------------------------- */

        public static int getIDtreninam() {
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
        
        public static void updateFileTrenini() {
                
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
                } catch (IOException e) {
                        System.out.println(
                                        "An error occured while writing this file: " + e.getMessage());
                }


        }

        /*---------------------TRENIŅU FILTRĒŠANA---------------------- */

        public static void searchByTypeandDifficulty() {

                Scanner scanner = new Scanner(System.in);

                System.out.println("Ievadiet sporta veidu: ");
                String Sport = scanner.nextLine(). trim(). toLowerCase();

                System.out.println("Ievadiet grūtības pakāpi: ");
                String Difficulty = scanner.nextLine(). trim(). toLowerCase();

                boolean found = false;

                        System.out.println("-----ATRASTIE TRENIŅI-----");

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
                System.out.println("Treniņš un/vai grūtības pakāpe netika atrasta.");
                        }
                scanner.close();
                }

                /*---------------------TRENIŅU PLĀNU IZVADE---------------------- */

                public static void paradaTreninuPlanus() {  //*parāda visus treniņus */

                        System.out.println("-----PIEEJAMIE TRENIŅU PLĀNI-----");

                        if (treninuList.isEmpty()) {
                                System.out.println("Nav pievienots neviens treniņu plāns!");
                                return;
                        }

                        for (String line : treninuList) {
                                String[] parts = line.split(",");

                                System.out.println("ID: " + parts[0]);
                                System.out.println("Nosaukums: " + parts[1]);
                                System.out.println("Sporta veids: " + parts[2]);
                                System.out.println("Grūtības pakāpe: " + parts[3]);
                                System.out.println("Muskuļu grupa: " + parts[4]);
                                System.out.println("Datums: " + parts[5]);
                                System.out.println("Ilgums: " + parts[6]);
                                System.out.println("Apraksts: " + parts[7]);
                                System.out.println("Brīvās vietas: " + parts[8]);

                        
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

                        System.out.println("-----TU ESI PIETEICIES ŠAJOS TRENIŅOS!-----");

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
                                System.out.println("Tu neesi pieteicies nevienam treniņam!"); }
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
                                System.out.println("Treneri nevar pieteikties treniņiem!");
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
                                System.out.println("Šis treniņš ir pilns. Izvēlieties citu treniņu.");
                                return;
                        }

                        String pieteikums = userID + "," + treninaID;
                        pieteikumi.add(pieteikums);
                        updatePieteikumiFailu();

                        updateBrivasVietas(treninaID);
                        System.out.println("Tu esi veiksmīgi pieteicies treniņam ar ID " + treninaID + ".");
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
                                System.out.println("Radās kļūda rakstot pieteikum failā: " + e.getMessage());
                }

        }

                }