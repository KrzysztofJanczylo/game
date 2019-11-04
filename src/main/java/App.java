
import java.util.*;

public class App {


    public static void main(String[] args) {
        System.out.println("Gra w której zgadujemy nazwę stolicy danego państwa lub nazwę państwa po jego stolicy");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("-- MENU --");
            System.out.println("1 - Nowa gra - zgadnij stolicę");
            System.out.println("2 - Nowa gra - zgadnij nazwę państwa");
            System.out.println("3 - Tryb uczenia się");
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
                case 0:
                    exit = true;
                    break;
            }
        }
    }

}
