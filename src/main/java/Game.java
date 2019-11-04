import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Game {


    public static void start(String nazwa) {
        Scanner scannerLine = new Scanner(System.in);
        if (nazwa.equals("start")) {System.out.println("-- NOWA GRA --");}
        if (nazwa.equals("lern")) {System.out.println("-- TRYB UCZENIA SIĘ --");}
        System.out.println("\nWybierz kontynent:");
        System.out.println("1 - Europa");
        System.out.println("2 - Azja");
        System.out.println("3 - Afryka");
        System.out.println("4 - Ameryka Północna");
        System.out.println("5 - Ameryka Południowa");
        System.out.println("6 - Australia");
        System.out.println("\n0 - Wróc od głownego menu");

        int menu = scannerLine.nextInt();
        boolean exit = false;
        while (!exit) {
            switch (menu) {
                case 1:
                    if (nazwa.equals("start")) {startG("Europa.txt");}
                    if (nazwa.equals("lern")) {lernGame("Europa.txt");}
                    exit = true;
                    break;
                case 2:
                    if (nazwa.equals("start")) {startG("Azja.txt");}
                    if (nazwa.equals("lern")) {lernGame("Azja.txt");}
                    exit = true;
                    break;
                case 0:
                    exit = true;

                default:
                    System.out.println("Błędna wartość, srpóbuj jeszcze raz");
            }
        }
    }

    public static void startG (String nazwaKontynentu){
        Scanner scannerLine = new Scanner(System.in);
        System.out.println("-- NOWA GRA --");
        System.out.println("\nWybierz poziom:");
        System.out.println("1 - Easy");
        System.out.println("2 - Normal");
        System.out.println("3 - Hard");
        System.out.println("\n0 - Wróc od głownego menu");

        int menu = scannerLine.nextInt();
        boolean exit = false;
        while (!exit) {
            switch (menu) {
                case 1:
                    startGameE(nazwaKontynentu);
                    exit = true;
                    break;
                case 2:
                    startGameN(nazwaKontynentu);
                    exit = true;
                    break;
                case 3:
                    startGameH(nazwaKontynentu);
                    exit = true;
                    break;
                case 0:
                    exit = true;

                default:
                    System.out.println("Błędna wartość, srpóbuj jeszcze raz");
            }
        }


    }

    private static void startGameH(String nazwaKontynentu) {
        Scanner scannerLine = new Scanner(System.in);
        try {
            int iloscPunktow =0;
            List<String[]> countryList = saveFile(nazwaKontynentu);
            for (int i =0; i<10;i++) {
                String[] country = random(countryList);
                System.out.println("podaj nazwę stolicy państwa: " + country[0]);
                    String odp = scannerLine.nextLine();
                    if (odp.equals(country[1])) {
                        System.out.println("Brawo, prawidłowa odpowiedz");
                        iloscPunktow=+10;
                        break;
                    }
                    else {
                        System.out.println("Niestety nie udało się");
                    }
                    Thread.sleep(1000);

            }
            replay(nazwaKontynentu, scannerLine, iloscPunktow);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void replay(String nazwaKontynentu, Scanner scannerLine, int iloscPunktow) {
        System.out.println("Zdobyłeś "+iloscPunktow+" punktów");

        System.out.println("1 - Zagraj jeszcze raz\n\n0 - wróc do MENU");
        int odpo = scannerLine.nextInt();
        switch (odpo) {
            case 1: {
                startG(nazwaKontynentu);
                break;
            }
            case 0:
                System.out.println("KONIEC");
                break;
        }
    }


    public static List<String[]> saveFile(String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("pliki/" + fileName));
        List<String[]> intID = new ArrayList<String[]>();

        String odzyt;
        String[] split;
        while (scanner.hasNextLine()) {
            odzyt = scanner.nextLine();
            split = odzyt.split(";");
            intID.add(new String[]{split[1], split[2]});
        }
        return intID;
    }
    public static String[] random(List<String[]> countryList) {

        String[] country ;
        Random generator = new Random();
        int los = generator.nextInt(countryList.size());
        country = countryList.get(los);
        countryList.remove(los);
        return country;
    }

    public static void lernGame(String nazwaPliku) {
        Scanner scannerLine = new Scanner(System.in);

        try {
            List<String[]> countryList = saveFile(nazwaPliku);
            String[] country = random(countryList);
            System.out.println("Stolicą "+country[0]+" jest "+country[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("1 - ucz kolejne\n0 - powrót");
        int odp = scannerLine.nextInt();
        switch (odp) {
            case 1: {
                lernGame(nazwaPliku);
                break;
            }
            case 0:
                System.out.println("KONIEC");
                break;
        }
    }
    public static void startGameE (String nazwaPliku){
        Scanner scannerLine = new Scanner(System.in);
        System.out.println("Zasady są proste, za prawidłową odpowiedz z jedną podopowiedzią zdobywasz 3 punkty\nZa 2 podpowiedzi - 2 punkty\nZa 3 podpowiedzi - 1 punkt");
        try {
            int iloscPunktow =0;
            List<String[]> countryList = saveFile(nazwaPliku);
            for (int i =0; i<10;i++) {
                String[] country = random(countryList);
                int end = 3;
                System.out.println("podaj nazwę stolicy państwa: " + country[0]);
                while (end > 0) {
                    System.out.println("Pozostało prób:" + end);
                    System.out.print("Podpowiedz: ");
                    for (int j = 0; j <4-end ; j++) {
                        System.out.print(country[1].charAt(j)+" ");
                    }
                    for (int j = 4-end; j < country[1].length() ; j++) {
                        System.out.print("_ ");
                    }
                    System.out.println("");
                    String odp = scannerLine.nextLine();
                    if (odp.equals(country[1])) {
                        System.out.println("Brawo, prawidłowa odpowiedz");
                        iloscPunktow=iloscPunktow+end;
                        break;
                    } else {
                        end--;
                    }
                    if (end == 0) {
                        System.out.println("Niestety nie udało się");
                    }
                    Thread.sleep(1000);
                }
            }
            System.out.println("Zdobyłeś "+iloscPunktow+" punktów na 30");

            replay(nazwaPliku, scannerLine, iloscPunktow);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        public static void startGameN (String nazwaPliku){
            Scanner scannerLine = new Scanner(System.in);
            try {
                int iloscPunktow =0;
                List<String[]> countryList = saveFile(nazwaPliku);
                for (int i =0; i<10;i++) {
                    String[] country = random(countryList);
                    int end = 3;
                    System.out.println("podaj nazwę stolicy państwa: " + country[0]);
                    while (end > 0) {
                        System.out.println("Pozostało prób:" + end);
                        String odp = scannerLine.nextLine();
                        if (odp.equals(country[1])) {
                            System.out.println("Brawo, prawidłowa odpowiedz");
                            iloscPunktow++;
                            break;
                        } else {
                            end--;
                        }
                        if (end == 0) {
                            System.out.println("Niestety nie udało się");
                        }
                        Thread.sleep(1000);
                    }
                }
                replay(nazwaPliku, scannerLine, iloscPunktow);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
