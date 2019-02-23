import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    public String toString(){
        String out = "Zamowienie:\n";
        for (Pozycja pozycja : pozycje) {
            out = out + pozycja.toString() + "\n";
        }
        out = out + "Razem: " + String.format("%.2f zł",obliczWartosc());

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
}