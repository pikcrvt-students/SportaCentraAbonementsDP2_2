import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

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

        public static void updateFileTrenini() {
                Scanner scanner = new Scanner(System.in);
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
                                        "An error occured while writing this file: "
                                                        + e.getMessage());
                }

                scanner.close();

        }

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
                        if (parts.length < 8) continue;

                        String sportaVeids = parts[1].trim().toLowerCase();
                        String grutibasPakape = parts[2].trim().toLowerCase();

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

                public static void paradaTreninuPlanus() {  //*parāda visus treniņus */

                        System.out.println("-----PIEEJAMIE TRENIŅU PLĀNI-----");

                        if (treninuList.isEmpty()) {
                                System.out.println("Nav pievienots neviens treniņu plāns!");
                                return;
                        }

                        for (String line : treninuList) {
                                String[] parts = line.split(",");

                                System.out.println("ID: " + parts[0]);
                                System.out.println("Sporta veids: " + parts[1]);
                                System.out.println("Grūtības pakāpe: " + parts[2]);
                                System.out.println("Treniņa datums: " + parts[3]);
                                System.out.println("Treniņa ilgums: " + parts[4]);
                                System.out.println("Treniņa apraksts: " + parts[5]);
                                System.out.println("Brīvās vietas: " + parts[6]);
                        
                                System.out.println("-----------------------------");
                        }
                }

                public static void paradaPieteikusosTreninus () {

                        System.out.println("-----TU ESI PIETEICIES ŠAJOS TRENIŅOS!-----");

                        boolean found = false;

                        for (String line : treninuList) {
                                
                                //*būs jāieraksta pārbaude, ka lietotajs ir pieteicies treninam */
                        }

                        if (!found) {
                                System.out.println("Tu neesi pieteicies nevienam treniņam!");
                        }
                }

        }
