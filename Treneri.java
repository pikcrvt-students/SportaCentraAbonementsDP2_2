import java.io.*;
import java.util.*;

public class Treneri {
    private static ArrayList<String> treneruList = new ArrayList<>();
    private static final String filePathforTreneri = "csv/trenerRegistration.csv";




     public static void treneruRegistresana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu vardu:");
        String treneraVards = scanner.nextLine();  
        System.out.println("Ievadiet savu uzvardu:");
        String treneraUzvards = scanner.nextLine();
        System.out.println("Ievadiet savu e-pastu:");
        String treneraEpasts = scanner.nextLine();
        System.out.println("Ievadiet savu telefona numuru:");
        String treneraTelefons = scanner.nextLine();
        System.out.println("Ievadiet savu personas kodu:");
        String treneraPkods = scanner.nextLine();
        System.out.println("Ievadiet savu specializaciju:");
        String treneraSpecializacija = scanner.nextLine();
        int newID = getIDtreneri() + 1;

        String trenerData = newID + ". " + treneraVards + ", " + treneraUzvards + ", " + treneraEpasts + ", " + treneraTelefons + ", " + treneraPkods + ", " + treneraSpecializacija;
        treneruList.add(trenerData);

       updateFileTrener();
    }

    public static int getIDtreneri() {
        if (treneruList.isEmpty()) {
            return 0;
        }

        String lastLine = treneruList.get(treneruList.size() - 1);
        String[] parts = lastLine.split(",");
        return Integer.parseInt(parts[0]);
    }

    private static void updateFileTrener(){
         try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filePathforTreneri));

            writer.write("id,vards,uzvards,epasts,telefons,pkods,specializacija");
            writer.newLine();

            for (String treneris : treneruList) {
                writer.write(treneris);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(
                "An error occurred while writing the file: "
                + e.getMessage());
        }
    }



    
}
