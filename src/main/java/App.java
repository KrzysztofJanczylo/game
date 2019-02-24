import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Aplikacja wspomagajaca obsluge zamowien");

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("-- MENU --");
            System.out.println("1 - Odczyt zamówień (plik)");
            System.out.println("2 - Zapis zamówień (plik)");
            System.out.println("3 - Dodaj nowe zamowienie");
            System.out.println("4 - Edytuj zamowienie");
            System.out.println("5 - Wyświetl podsumowanie zamówienia");
            System.out.println("----------");

            int menu = scanner.nextInt();
            switch (menu){
                case 1:
                    System.out.println("Podaj nazwę pliku");
                    String nazwa = scanner.nextLine();
                    Zamowienie.wczytajZPliku(nazwa);
                    break;
                case 2:


                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    exit = true;
                    break;
            }


        }


    }
}
