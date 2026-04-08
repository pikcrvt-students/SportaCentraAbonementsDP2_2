import java.util.*;
import java.io.*;

public class Lietotaji {

    private static ArrayList<String> klientuList = new ArrayList<>();
    private static final String filePathforKlienti = "csv/klientRegistration.csv";
    private static String currentUserEmail = null;

    public static void klientuRegistresana() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu vardu:");
        String klientaVards = scanner.nextLine();  
        System.out.println("Ievadiet savu uzvardu:");
        String klientaUzvards = scanner.nextLine();
        System.out.println("Ievadiet savu e-pastu:");
        String klientaEpasts = scanner.nextLine();
        System.out.println("Ievadiet savu telefona numuru:");
        String klientaTelefons = scanner.nextLine();

        // Check if the email already exists in the client list
        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo.length > 2 && klientInfo[2].trim().equals(klientaEpasts)) {
                System.out.println("Sis e-pasts jau ir reģistrets. Ludzu, izmantojiet citu e-pastu.");
                return;
            }
        }

        // If the email is unique, proceed with registration
        int newID = getIDklients() + 1;
        String klientData = newID + ". " + klientaVards + "," + klientaUzvards + "," + klientaEpasts + "," + klientaTelefons + ",abonements,0.0";
        klientuList.add(klientData);
        
        updateFileforklient();

        scanner.close();
    }

    public static int getIDklients() {
        if (klientuList.isEmpty()) {
            return 0;
        }

        String lastLine = klientuList.get(klientuList.size() - 1);
<<<<<<< HEAD
        String[] idPart = lastLine.split("\\. ");
        return Integer.parseInt(idPart[0]);
=======
        String[] parts = lastLine.split(",");
        String idPart = parts[0].split("\\.")[0].trim();
        try {
            return Integer.parseInt(idPart);
        } catch (NumberFormatException e) {
            return 0;
        }
>>>>>>> 71872e3 (Izveidotas 11 funkcijas)
    }

    private static void updateFileKlietn() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathforKlienti))) {
            writer.write("id,vards,uzvards,epasts,telefons,abonements");
            writer.newLine();

            for (String klientData : klientuList) {
                writer.write(klientData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Kluda saglabajot datus: " + e.getMessage());
        }
    }

    private static void updateFileforklient() {
        updateFileKlietn();
    }

    public static void pieslegtiesKlientam() {
        System.out.println("Pieslegties");
        System.out.println("Ievadiet savu e-pastu:");
        Scanner scanner = new Scanner(System.in);
        String ievaditaisEpasts = scanner.nextLine();

        boolean found = false;

        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                found = true;
                System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[0] + "!");
                break;
            }
        }

<<<<<<< HEAD
        if (!found) {
            System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
=======
            writer.close();
        } catch (IOException e) {
            System.out.println(
                "An error occurred while writing the file: "
                + e.getMessage());

                updateFileKlietn();
>>>>>>> 71872e3 (Izveidotas 11 funkcijas)
        }
    }

    public static void mansKonts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu e-pastu:");
        String ievaditaisEpasts = scanner.nextLine();

        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                System.out.println("Mans konts");
                System.out.println("Mans vards: " + klientInfo[0]);
                System.out.println("Mans uzvards: " + klientInfo[1]);
                System.out.println("Mans e-pasts: " + klientInfo[2]);
                System.out.println("Mans telefons: " + klientInfo[3]);
                System.out.println("Abonements: " + klientInfo[4]);
                
                System.out.println();
                System.out.println("Ko jus velaties darit?");
                System.out.println("1. Rediget profila datus");
                System.out.println("2. Apskatit jusu abonementu");
                System.out.println("3. Dzest savu kontu");
                int kontaIzvele = scanner.nextInt();
                switch(kontaIzvele) {
                    case 1:
                        redigetProfilaDatus();
                        break;
                    case 2: 
                        //Abonements.apskatitManuabonementu();
                        break;
                    case 3: 
                        klientuList.remove(klients);
                        updateFileKlietn();
                        System.out.println("Jusu konts ir dzests. Uz redzesanos!");
                }
                return;
            }
        }
        System.out.println("E-pasts nav atrasts. LLudzu, meginiet velreiz.");
    }

    public static void redigetProfilaDatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rediget profila datus");
        System.out.println("Ievadiet savu e-pastu, lai redigetu datus:");
        String ievaditaisEpasts = scanner.nextLine();

        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = klientuList.get(i).split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                System.out.println("Ievadiet jaunu vardu:");
                String jaunsVards = scanner.nextLine();
                System.out.println("Ievadiet jaunu uzvardu:");
                String jaunsUzvards = scanner.nextLine();
                System.out.println("Ievadiet jaunu telefona numuru:");
                String jaunsTelefons = scanner.nextLine();

                klientInfo[0] = jaunsVards;
                klientInfo[1] = jaunsUzvards;
                klientInfo[3] = jaunsTelefons;

                klientuList.set(i, String.join(", ", klientInfo));
                updateFileKlietn();
                System.out.println("Profila dati veiksmigi atjauninati!");
                return;
            }
        }

        System.out.println("E-pasts nav atrasts. LLudzu, meginiet velreiz.");
    }

    public static void loadKlientiFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathforKlienti))) {
            String line;
            // Skip header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                klientuList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Kluda ieladejot datus: " + e.getMessage());
        }
    }

    public static void naudasIemaksa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ievadiet savu e-pastu:");
        String ievaditaisEpasts = scanner.nextLine();

        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = klientuList.get(i).split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) {
                System.out.println("Ievadiet iemaksa summu:");
                double depositAmount = scanner.nextDouble();
                
                
                double currentBalance = Double.parseDouble(klientInfo[5]);
                double newBalance = currentBalance + depositAmount;
                
                
                klientInfo[5] = String.valueOf(newBalance);
                klientuList.set(i, String.join(",", klientInfo));
                
                updateFileforklient();  
                
                System.out.println("Naudas iemaksa veiksmiga! Jusu summa: " + newBalance);
                return;}}
            
        }

public static void klientuPieslegsanas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pieslegsanas");
        System.out.println("Ievadiet savu e-pastu: ");
        String ievaditaisEpasts = scanner.nextLine();
        boolean found = false;

        for (String klients : klientuList) {
            String[] klientInfo = klients.split(",");
            if (klientInfo[2].equals(ievaditaisEpasts)) { 
                found = true;
                System.out.println();
                System.out.println("Pieslegsanas veiksmiga! Laipni ludzam, " + klientInfo[1] + "!"); 
                System.out.println();
                break;
            }
        }

            if (!found) {
                System.out.println("E-pasts nav atrasts. Ludzu, meginiet velreiz.");
                klientuPieslegsanas(); 
            }
        }
    
        public static double getCurrentUserBalance(){
            if(currentUserEmail == null) {
                System.out.println("Nav pieslegts neviens klients.");
                return 0.0;
            }
            for (String klients : klientuList) {
                String[] klientInfo = klients.split(",");
                if (klientInfo[2].equals(currentUserEmail)) {
                    return Double.parseDouble(klientInfo[5]);  
                }
            }
            return 0.0;
        }



     public static void updateCurrentUserBalance(double newBalance) {
        if (currentUserEmail == null) return;
        for (int i = 0; i < klientuList.size(); i++) {
            String[] klientInfo = klientuList.get(i).split(",");
            if (klientInfo[2].equals(currentUserEmail)) {
                klientInfo[5] = String.valueOf(newBalance);
                klientuList.set(i, String.join(",", klientInfo));
                updateFileforklient();  
                return;
            }
        }
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail; 
    }

    public static ArrayList<String> klientuList() {
        return klientuList; 
    }

}












