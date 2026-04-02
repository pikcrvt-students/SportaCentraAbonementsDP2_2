
import java.util.*;
import java.io.*;

public class Lietotaji {

    private static ArrayList<String> klientuList = new ArrayList<>();
    
    private static final String filePathforKlienti = "csv/klientRegistration.csv";

    public static void klientuRegistresana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu vārdu:");
        String klientaVards = scanner.nextLine();  
        System.out.println("Ievadiet savu uzvārdu:");
        String klientaUzvards = scanner.nextLine();
        System.out.println("Ievadiet savu e-pastu:");
        String klientaEpasts = scanner.nextLine();
        System.out.println("Ievadiet savu telefona numuru:");
        String klientaTelefons = scanner.nextLine();

        int newID = getIDklients() + 1;
        String klientData = newID + ". " + klientaVards + "," + klientaUzvards + "," + klientaEpasts + "," + klientaTelefons;
        klientuList.add(klientData);

        updateFileforklient();
    }


    public static int getIDklients() {
        if (klientuList.isEmpty()) {
            return 0;
        }

        String lastLine = klientuList.get(klientuList.size() - 1);
        String[] parts = lastLine.split(",");
        return Integer.parseInt(parts[0]);
    }



    private static void updateFileKlietn(){
        try (FileWriter writer = new FileWriter(filePathforKlienti, false)) {
            for (String klientData : klientuList) {
                writer.append(klientData).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot datus: " + e.getMessage());
        }
    }

    private static void updateFileforklient() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filePathforKlienti));

            writer.write("id,vards,uzvards,epasts,telefons");
            writer.newLine();

            for (String klients : klientuList) {
                writer.write(klients);
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

