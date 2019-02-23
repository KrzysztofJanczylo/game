import lombok.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Zamowienie {

    private List<Pozycja> pozycje = new ArrayList<Pozycja>();

    public void dodajPozycje(Pozycja pozycja){
        if (pozycja != null) {
            boolean czyUdaloSieZnalesc = false;
            for (Pozycja pozycja1 : pozycje) {
                if (pozycja1.getNazwaTowaru().equals(pozycja.getNazwaTowaru()) && pozycja1.getCena() == pozycja.getCena()){
                    pozycja1.setIleSztuk(pozycja1.getIleSztuk() + pozycja.getIleSztuk());
                    czyUdaloSieZnalesc = true;
                    break;
                }
            }
            if (!czyUdaloSieZnalesc) {
                pozycje.add(pozycja);
            }
        }
    }

    public double obliczWartoscZRabatek(){
        double wynik = 0;
        for (Pozycja pozycja : pozycje) {
            wynik = wynik + pozycja.obliczWartoscZRabatem();
        }
        return wynik;
    }

    public double obliczWartosc(){
        double suma = 0;

        for (Pozycja pozycja : pozycje) {
            suma = suma + pozycja.obliczWartosc();
        }
        return suma;
    }

    public String toString() {
        String out = "Zamowienie:\n";
        for (Pozycja pozycja : pozycje) {
            out = out + pozycja.toString() + "\n";
        }
        out = out + "Razem: " + String.format("%.2f zł", obliczWartoscZRabatek()) + "\n";
        out = out + "Rabat: " + String.format("%.2f zł", obliczWartosc() - obliczWartoscZRabatek());

        return out;
    }

    public void usunPozycje(int index){
        try {
            pozycje.remove(index);

        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Brak pozycji o zadanym indeksie");
        }

}

    public void edytujPozycje(int index, String nazwaTowaru, int ilosc, double cena){
        try {
            Pozycja pozycja = pozycje.get(index);
            pozycja.setNazwaTowaru(nazwaTowaru);
            pozycja.setIleSztuk(ilosc);
            pozycja.setCena(cena);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Brak pozycji o zadanym indeksie");
        }
    }

    public static void zapiszZamowienie(Zamowienie z, String nazwaPliku){
        try {
            PrintWriter printWriter = new PrintWriter("pliki/" + nazwaPliku);
            //printWriter.println("teścik");

            for(Pozycja pozycja: z.getPozycje()){
                printWriter.print(pozycja.getNazwaTowaru());
                printWriter.print(";");
                printWriter.print(pozycja.getIleSztuk());
                printWriter.print(";");
                printWriter.print(pozycja.getCena());
                printWriter.print(";");
                printWriter.println();
            }

            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Zamowienie wczytajZPliku(String nazwaPliku){
        Zamowienie zamowienie = new Zamowienie();
        try {
            Scanner scanner = new Scanner(new File("pliki/" + nazwaPliku));

            while (scanner.hasNextLine()){
                String odzyt = scanner.nextLine();
                String[] split = odzyt.split(";");
                System.out.println(split[0]);
                System.out.println(split[1]);
                System.out.println(split[2]);
                zamowienie.dodajPozycje(new Pozycja(split[0],Integer.valueOf(split[1]), Double.valueOf(split[2])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return zamowienie;
    }
}