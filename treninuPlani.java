import java.io.*;
import java.util.*;

public class treninuPlani {
    private static ArrayList<String> treninuList = new ArrayList<>();
    private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

    public static void treninuPlanaIevade() {
        Scanner scanner = new Scanner(System.in);
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

        String treninaData = newID + "," + muskuluGrupa + "," + treninaDatums + "," + treninaIlgums + "," + treninaApraksts + "," + brivoVietuSkaits;
        treninuList.add(treninaData);

        updateFileTrenini();

        scanner.close();
    }

    public static int getIDtreninam() {
        if (treninuList.isEmpty()){
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

            writer.write("id, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits");
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
}

