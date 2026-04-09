import java.io.*;
import java.util.*;

public class Treneri {
    private static ArrayList<String> treneruList = new ArrayList<>();
    private static final String filePathforTreneri = "csv/trenerRegistration.csv";




     public static void treneruRegistresana() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ievadiet savu vardu:");
        String treneraVards = scanner.nextLine();  
        System.out.print("Ievadiet savu uzvardu:");
        String treneraUzvards = scanner.nextLine();
        System.out.print("Ievadiet savu e-pastu:");
        String treneraEpasts = scanner.nextLine();
        System.out.print("Ievadiet savu telefona numuru:");
        String treneraTelefons = scanner.nextLine();
        System.out.print("Ievadiet savu personas kodu:");
        String treneraPkods = scanner.nextLine();
        System.out.print("Ievadiet savu specializaciju:");
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
        String[] idPart = lastLine.split("\\. ");
        return Integer.parseInt(idPart[0]);
    
    }

    public static ArrayList<String> getTreneruList() {
        return treneruList;
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

    public static void izveletiesTreneri() {

        System.out.println("Treneru saraksts:");

        int index = 0;

        for (String treneris : treneruList) {
            System.out.println((index + 1) + ". " + treneris);
            index++;
        }
    }

    public static void redigetTerneraprofilaDatus() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Rediget profila datus");
        System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");

        String ievaditaisEpasts = scanner.nextLine();

        for (int i = 0; i < treneruList.size(); i++) {
            String[] trenerInfo = treneruList.get(i).split(",");
            if (trenerInfo[2].equals(ievaditaisEpasts)) {
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

                trenerInfo[0] = jaunsVards;
                trenerInfo[1] = jaunsUzvards;
                trenerInfo[2] = jaunsEpasts;
                trenerInfo[3] = jaunsTelefons;
                trenerInfo[4] = jaunsPkods;
                trenerInfo[5] = jaunsSpecializacija; 

                treneruList.set(i, String.join(", ", trenerInfo));

                updateFileTrener();

                System.out.println("Profila dati veiksmigi atjauninati!");
                return;
            }
        }
    }
    public static void treneruPieslegsanas() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Pieslegsanas");
        System.out.println("Ievadiet savu e-pastu: ");

        String ievaditaisEpasts = scanner.nextLine();

        boolean found = false;

        for (String treneris : treneruList) {

            String[] trenerInfo = treneris.split(",");
            if (trenerInfo[2].equals(ievaditaisEpasts)) { 

                found = true;

                System.out.println();
                System.out.println("Pieslegsanas veiksmiga! Laipni ludzam," + trenerInfo[1] + "!"); 
                System.out.println();

                break;
            }
        }

        if (!found) {

            System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
            treneruPieslegsanas();
        }
}

public static void loadTreneriFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathforTreneri))) {
            String line;
            
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                treneruList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Kluda ieladejot datus: " + e.getMessage());
        }
    }
}
