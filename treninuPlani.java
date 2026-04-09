import java.io.*;
import java.util.*;

public class treninuPlani {

        private static ArrayList<String> treninuList = new ArrayList<>();
        private static final String filepathforTrenini = "csv/treninuPlaniglab.csv";

        private static ArrayList<String> pieteikumi = new ArrayList<>();
        private static final String filepathforPieteikumi = "csv/pieteikumi.csv";

        /*------------------TRENIŅA IZVEIDE-------------------------- */

        public static void treninuPlanaIevade() {  /*funkcija treninuPlanaIevade pieņem string tipa vērtības treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits un pieņem int tipa vērtības treninaID, un atgriež void */
                
                Scanner scanner = new Scanner( System.in );

                System.out.println( "Ievadiet treniņa nosaukumu: " );
                String treninaNosaukums = scanner.nextLine();
                System.out.println("Ievadiet sporta veidu(klinšu kāpšana, kalistēnika):");
                String sportaVeids = scanner.nextLine();
                System.out.println("Ievadiet grūtības pakāpi(viegls, vidējs, grūts):");
                String grutibasPakape = scanner.nextLine();

                System.out.println( "Ievadiet muskuļu grupu: " );
                String muskuluGrupa = scanner.nextLine();

                System.out.println( "Ievadiet treniņa datumu: " );
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

        public static int getIDtreninam() {  /* Funkcija getIDtreninam atgriež int tipa vērtību idPart */
                
                if ( treninuList.isEmpty() ) {
                        return 0;
                }

                String lastLine = treninuList.get( treninuList.size() - 1 );  /* no treninuList iegūst pēdējo elementu */

                String[] parts = lastLine.split( ", " );

                String idPart = parts[0].trim();

                if ( idPart.endsWith( ". " )) { /* pārbauda vai idPart beidzas ar punktu un atstarpēm */

                        idPart = idPart.substring( 0, idPart.length() - 1 ).trim(); /* ja beidzas, tad noņem punktu un atstarpes */
                }

                try {

                        return Integer.parseInt( idPart ); /* pārvērš idPart par int un atgriež to */

                } catch ( NumberFormatException e ) {

                        return 0;
                }
        }

        /*------------------TRENIŅA FAILA (CSV) SAGLABĀŠANA-------------------------- */
        
        public static void updateFileTrenini() { /* funkcija updateFileTrenini pieņem string tipa vērtību id, treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits un atgriež void */
                
                try {
                        BufferedWriter writer = new BufferedWriter( new FileWriter ( filepathforTrenini ) ); /* Izveido bufferēto rakstītāju */

                        writer.write( "id, treninaNosaukums, sportaVeids, grutibasPakape, muskuluGrupa, treninaDatums, treninaIlgums, treninaApraksts, brivoVietuSkaits" ); /* ieraksta kolonu nosaukumus CSV failā */
                        
                        writer.newLine(); /* pāriet jaunā rindā */

                        for ( String trenina : treninuList ) { /* Sākas cikls, kas iet caur visiem treniņu ierakstiem */
                                
                                writer.write( trenina ); /* ieraksta katra treniņa datus CSV failā */
                                
                                writer.newLine();
                        }

                        writer.close();
                
                } catch ( IOException e ) {
                        
                        System.out.println( "An error occured while writing this file: " + e.getMessage() );
                }


        }

        /*---------------------TRENIŅU FILTRĒŠANA---------------------- */

        public static void searchByTypeandDifficulty() { /* funkcija searchByTypeandDifficulty pieņem string tipa vērtību Sport un Difficulty, un atgriež void */

                Scanner scanner = new Scanner( System.in );

                System.out.println( "Ievadiet sporta veidu: " );
                String Sport = scanner.nextLine(). trim(). toLowerCase(); /* nolasa ievadīto sporta veidu un noņem atstarpes un lielos burtus, lai precīzāk salīdzinātu */

                System.out.println("Ievadiet grūtības pakāpi: ");
                String Difficulty = scanner.nextLine(). trim(). toLowerCase();

                boolean found = false;

                        System.out.println("-----ATRASTIE TRENIŅI-----");

                for ( String line: treninuList ) { /* iet cauri katrai treniņu rindai sarakstā */
                        
                        String[] parts = line.split( ", " );

                        if  (parts.length < 9 ) continue; /* drošības pārbaude - pārbauda, vai rinda satur visus nepieciešamos laukus */

                        String sportaVeids = parts[2].trim().toLowerCase(); /* no rindas paņem sporta veidu un noņem atstarpes un lielos burtus */

                        String grutibasPakape = parts[3].trim().toLowerCase(); /* no rindas paņem grūtības pakāpi un noņem atstarpes un lielos burtus */

                        if ( sportaVeids.equals( Sport ) && grutibasPakape.equals( Difficulty ) ) { /* pārbauda vai lauki sakrīt ar lietotāja ievadi */
                                
                                System.out.println( line ); /* ja sakrīt izvada visu treniņa rindu */
                                
                                found = true;

                        }
                              }
                if (!found) {
                System.out.println("Treniņš un/vai grūtības pakāpe netika atrasta.");
                        }
                scanner.close();

        }

                /*---------------------TRENIŅU PLĀNU IZVADE---------------------- */

                public static void paradaTreninuPlanus() {  //*parāda visus treniņus *// 
                        System.out.println("-----PIEEJAMIE TRENIŅU PLĀNI-----");

                        if (treninuList.isEmpty()) {
                                System.out.println("Nav pievienots neviens treniņu plāns!");
                                return;
                        }

                for (String line : treninuList) {

                        String[] parts = line.split( ", " );

                                System.out.println("ID: " + parts[0]);
                                System.out.println("Nosaukums: " + parts[1]);
                                System.out.println("Sporta veids: " + parts[2]);
                                System.out.println("Grūtības pakāpe: " + parts[3]);
                                System.out.println("Muskuļu grupa: " + parts[4]);
                                System.out.println("Datums: " + parts[5]);
                                System.out.println("Ilgums: " + parts[6]);
                                System.out.println("Apraksts: " + parts[7]);
                                System.out.println("Brīvās vietas: " + parts[8]);

                        
                        System.out.println( "-----------------------------" );
                        
                }
                
        }

                /*-------------------PĀRBAUDA PIETEIKUMU--------------------- */

        public static boolean isUserPieteiciesTreninam( int treninaID ) { /* funkcija isUserPieteiciesTreninam pieņem int tipa vērtību treninaID un atgriež boolean */
                        
                for ( String pieteikums : pieteikumi ) {
                                
                        String[] parts = pieteikums.split( ", " );
                                
                        if ( parts.length < 2 ) continue; /* drošības pārbaude - pārbauda, vai rinda satur visus nepieciešamos laukus */

                                
                        int id = Integer.parseInt( parts[1].trim() ); /* no pieteikuma rindas paņem treninaID un pārvērš to par int */
                                
                        if ( id == treninaID ) {
                                        
                                return true;
                                
                        }
                        
                }
                        
                return false;
                
        }

                /*---------------------PARĀDA TRENIŅUS, KUROS IR PIETEICIES---------------------- */

        public static void paradaPieteikusosTreninus() {  /* funkcija paradaPieteikusosTreninus atgriež void */

                        System.out.println("-----TU ESI PIETEICIES ŠAJOS TRENIŅOS!-----");

                boolean found = false;

                for ( String line : treninuList ) {
                                
                        String[] parts = line.split( ", " );

                        int brivasVietas = Integer.parseInt( parts[8].trim() ); /* no rindas paņem brīvo vietu skaitu un pārvērš to par int */

                        System.out.println( line );

                        System.out.println( "Brīvās vietas: " + brivasVietas ); /* parāda brīvo vietu skaitu  lietotājam */
                
                        int treninaID = Integer.parseInt( parts[0].trim() ); /* no rindas paņem treninaID un pārvērš to par int */

                        if ( isUserPieteiciesTreninam( treninaID ) ) { /* pārbauda, vai lietotājs ir pieteicies treniņam */
                                        
                                System.out.println( line );
                                        
                                found = true;
                                
                        }

                        if (!found) {
                                System.out.println("Tu neesi pieteicies nevienam treniņam!"); }
                        }



                        /*---------------PĀRBAUD VAI TRENIŅŠ IR PILNS---------------------- */

        public static boolean parbaudaVaiTreninsPilns( String treninaData ) { /* funkcija parbaudaVaiTreninsPilns pieņem string tipa vērtību treninaData un atgriež boolean */
                                
                String[] parts = treninaData.split( ", " ); /* sadala treniņu masīvos un atdala katru elementu ar komatu */
                                
                int brivasVietas = Integer.parseInt( parts[8].trim() ); /* no masīva paņem brīvo vietu skaitu un pārvērš to par int */
                                
                return brivasVietas <= 0;  /* pārbauda vai brīvo vietu skaits ir mazāks vai vienāds ar 0, ja jā, tad treniņš ir pilns */
                        
        }

                        /*---------------ATJAUNO BRĪVĀS VIETAS---------------------- */

        public static void updateBrivasVietas( int treninaID ) {  /* funkcija updateBrivasVietas pieņem int tipa vērtību treninaID un atgriež void */
                                
                for ( int i = 0 ; i < treninuList.size(); i++ ) {
                                        
                        String[] parts = treninuList.get(i).split( ", " );
                                        
                        int id = Integer.parseInt( parts[0].trim() ); /* no rindas paņem treninaID un pārvērš to par int */

                if (id == treninaID) { /* pārbauda vai šis ir pareizais treniņš, kuram jasamazina  brīvās vietas */

                        int brivasVietas = Integer.parseInt( parts[8].trim() ); /* no masīva paņem brīvo vietu skaitu un pārvērš to par int */
                                                
                        brivasVietas--; /* samazina brīvās vietas par 1 */
                                                
                        parts[8] = String.valueOf( brivasVietas ); /* atjauno brīvo vietu skaitu */
                 
                        String updatedTreninaData = String.join( ", ", parts ); /* savieno kopā ar csv failu */
                                                
                        treninuList.set( i, updatedTreninaData ); /* aizstāv vecos treniņa datus ar jaunajiem */
                                                
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
                        
                }

                if ( treninaData == null ) {

                        System.out.println( "Treniņš ar ID " + treninaID + " netika atrasts." );
                        
                        return;
                }

                        if (parbaudaVaiTreninsPilns(treninaData)) {
                                System.out.println("Šis treniņš ir pilns. Izvēlieties citu treniņu.");
                                return;
                        }

                String pieteikums = userID + "," + treninaID; /* izveido pieteikuma rindu csv failā */
                
                pieteikumi.add( pieteikums );
                
                updatePieteikumiFailu();

                        updateBrivasVietas(treninaID);
                        System.out.println("Tu esi veiksmīgi pieteicies treniņam ar ID " + treninaID + ".");
                }

                /*---------------------PIETEIKUMU FAILA (CSV) SAGLABĀŠANA---------------------- */

        public static void updatePieteikumiFailu() { /* funkcija updatePieteikumiFailu atgriež void */ /* pārraksta pieteikumu failu ar jaunāko informāciju */

                try {

                        BufferedWriter writer = new BufferedWriter( new FileWriter( filepathforPieteikumi ) );

                        writer.write( "userID, treninaID " );
                                
                        writer.newLine();

                for ( String pieteikums : pieteikumi ) {
                                        
                        writer.write( pieteikums );
                        
                        writer.newLine();
                                
                        }

                                writer.close();
                        } catch (IOException e) {
                                System.out.println("Radās kļūda rakstot pieteikum failā: " + e.getMessage());
                }

        }

                /*---------------------TRENINU PLĀNU DZĒŠANA---------------------- */

        public static void dzestTrenanPlanu( int treninaID, boolean isTreneris ) { /* funkcija dzestTrenanPlanu pieņem int tipa vērtību treninaID un boolean tipa vērtību isTreneris un atgriež void */

                if( !isTreneris ) {

                        if(!isTreneris) {

                                System.out.println("Tikai treneri var dzēst treniņu plānus!.");
                                return; 
                        }

                boolean found = false;

                for ( int i = 0; i < treninuList.size(); i++ ) {
                
                        String[] parts = treninuList.get(i).split( ", " );
                                
                        int id = Integer.parseInt( parts[0].trim() );

                                if (id == treninaID) {
                                        treninuList.remove(i);
                                        updateFileTrenini();
                                        System.out.println("Treniņu plāns ar ID " + treninaID + " ir dzēsts.");
                                        found = true;
                                        break;
                                }
                        }

                        if (!found) {
                                System.out.println("Treniņu plāns ar ID " + treninaID + " netika atrasts.");

                        }
                }

                /*------------------TRENIŅA PLĀNA VALIDĀCIJA (TUKŠU LAUKU PĀRBAUDE UN RAKSTZĪMJU IEROBEŽOJUMI)------------------------ */

                        public static boolean validateAizpilditieLauki(String fields[]) {

                                for (String field : fields) {
                                        if (field == null || field.trim().isEmpty()) {
                                                System.out.println("Visiem laukiem jābūt aizpildītiem!");
                                                return false;
                                        }
                                }

                return true;

        }

        public static boolean validateRakstzimjuGarumu ( String input, int minLenght ) {  /* funkcija validateRakstzimjuGarumu pieņem string tipa vērtību input un int tipa vērtību minLenght un atgriež boolean */

                if ( input == null ) return false;
                        
                return input.trim().length() >= minLenght;
                        
        }

}
