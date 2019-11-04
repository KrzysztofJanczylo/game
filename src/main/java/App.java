import java.io.File;
import java.util.*;

public class App {

    private static Map<String, Zamowienie> dane = new HashMap<String, Zamowienie>();
    private static Set<Klient> klients = new HashSet<Klient>();

    public static void main(String[] args) {
        System.out.println("Aplikacja wspomagajaca obsługe zamowień.");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("-- MENU --");
            System.out.println("1 - Nowa gra - zgadnij stolicę");
            System.out.println("2 - Nowa gra - zgadnij nazwę państwa");
            System.out.println("3- Tryb uczenia się");
            System.out.println("-------------------------------");
            System.out.println("4 - Edytuj zamówienie");
            System.out.println("5 - Wyświetl podsumowanie zamówień");
            System.out.println("6 - Dodaj klienta");
            System.out.println("7 - Wyświetl bazę klientów");
            System.out.println("8 - Zapis bazy klientów (plik)");
            System.out.println("9 - Odczyt bazy klientów (plik)");
            System.out.println();
            System.out.println("0 - Wyście z programu");
            System.out.println("--------");

            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    Game.start("start");
                    break;
                case 2:
                    Game.start("startCapitol");
                    break;
                case 3:
                    Game.start("lern");
                    break;
                case 4:
                    System.out.println("Podaj numer zamówienia");
                    String numerZamowienia = scanner.next();
                    Zamowienie poszukiwaneZamowienie = dane.get(numerZamowienia);
                    if (poszukiwaneZamowienie != null) {
                        System.out.println("Czy edytować pozycję zamówienia? (t/n)");
                        if (scanner.next().equals("t")) {
                            edytujPozycje(scanner, poszukiwaneZamowienie);
                        }
                        System.out.println("Czy dodać nowa pozycję zamówienia? (t/n)");
                        if (scanner.next().equals("t")) {
                            dodajPozycje(scanner, poszukiwaneZamowienie);
                        }
                    } else {
                        System.out.println("Nieudało się odnaleźć zamówienia o numerze " + poszukiwaneZamowienie);
                    }
                    break;
                case 5:
                    System.out.println("##########################");
                    for (Map.Entry<String, Zamowienie> entry : dane.entrySet()) {
                        System.out.println("Zamowienie o nr: " + entry.getKey());
                        System.out.println(entry.getValue());
                        System.out.println("##########################");
                    }
                    break;
                case 6:
                    System.out.println("Czy chcesz dodać klienta indywidualnego czy firmę? (i/f)");
                    if (scanner.next().equals("i")) {
                        System.out.println("Podaj imię");
                        String imie = scanner.next();
                        System.out.println("Podaj nazwisko");
                        String nazwisko = scanner.next();
                        System.out.println("Podaj pesel");
                        Long pesel = scanner.nextLong();
                        System.out.println("Podaj adres");
                        String adres = scanner.next();

                        try {
                            Klient klient = new Klient(imie, nazwisko, pesel, adres);
                            klients.add(klient);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Nie można dodać klienta do bazy ponieważ dane są nieprawidłowe");
                        }
                    } else {
                        System.out.println("Podaj nazwę firmy");
                        String nazwa = scanner.next();
                        System.out.println("Podaj NIP");
                        Long nip = scanner.nextLong();
                        System.out.println("Podaj adres");
                        String adres = scanner.next();
                        try {
                            Klient klient = new Klient(nazwa, nip, adres);
                            klients.add(klient);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Nie można dodać klienta do bazy ponieważ dane są nieprawidłowe");
                        }
                    }
                    break;
                case 7:
                    for (Klient klient : klients) {
                        if (klient.isFirma()) {
                            System.out.println("Klient firmowy: \n" +
                                    klient.getNazwaFirmy() + ", " +
                                    klient.getNip() + ", " +
                                    klient.getAdres());
                        } else {
                            System.out.println("Klient indywidualny: \n" +
                                    klient.getImie() + ", " +
                                    klient.getNazwisko() + ", " +
                                    klient.getPesel() + ", " +
                                    klient.getAdres());
                        }

                    }
                    break;
                case 8:
                    Klient.zapiszKlienta(klients,
                                 "Klienci.txt");

                    break;
                case 9:


                        HashSet<Klient> klients2 = Klient.wczytajZPliku("Klienci.txt");
                        klients=klients2;

                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    public static void edytujPozycje(Scanner scanner, Zamowienie zamowienie) {
        boolean czyEdytowaVPozycje = true;
        do {
            System.out.println("Podaj numer pozycji na zamówieniu");
            int numerPozycji = scanner.nextInt();

            String nazwa = nazwaTowaru(scanner);
            double cena = cenaTowaru(scanner);
            int ilosc = iloscTowaru(scanner);

            zamowienie.edytujPozycje(numerPozycji, nazwa, ilosc, cena);
            System.out.println("Czy edytować kolejną pozycję? (t/n)");
            String znak = scanner.next();
            czyEdytowaVPozycje = znak.equals("t");
        } while (czyEdytowaVPozycje);
    }

    private static void dodajPozycje(Scanner scanner, Zamowienie zamowienie) {
        boolean czyDodacPozycje = true;
        do {
            String nazwa = nazwaTowaru(scanner);
            double cena = cenaTowaru(scanner);
            int ilosc = iloscTowaru(scanner);

            zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
            System.out.println("Czy dodac kolejna pozycje? (t/n)");
            String znak = scanner.next();
            czyDodacPozycje = znak.equals("t");
        } while (czyDodacPozycje);
    }

    public static String nazwaTowaru(Scanner scanner) {
        System.out.println("Podaj nazwe towaru?");
        return scanner.next();
    }

    public static double cenaTowaru(Scanner scanner) {
        System.out.println("Podaj cena towaru?");
        return scanner.nextDouble();
    }

    public static int iloscTowaru(Scanner scanner) {
        System.out.println("Podaj ilosc towaru?");
        return scanner.nextInt();
    }

}
