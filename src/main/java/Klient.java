import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Klient {

    private String imie;
    private String nazwisko;
    private String nazwaFirmy;
    private long nip;
    private boolean firma;
    private long pesel;
    private String adres;

    public Klient(String imie, String nazwisko, long pesel, String adres) {

        this.imie = sprawdzCzyNiePuste(imie, "Imię");
        this.nazwisko = sprawdzCzyNiePuste(nazwisko, "Nazwisko");
        this.adres = sprawdzCzyNiePuste(adres ,"Adres");
        if (PeselValidator.valid(pesel)) {
            this.pesel = pesel;
        } else {
            throw new IllegalArgumentException("Niepoprawny PESEL");
        }
    }

    public Klient(String nazwaFirmy, long nip, String adres) {
        this.nazwaFirmy = sprawdzCzyNiePuste(nazwaFirmy, "Nazwa Firmy");
        this.firma = true;
        if (NipValidator.valid(nip)) {
            this.nip = nip;
        } else {
            throw new IllegalArgumentException("Niepoprawny NIP");
        }
        this.adres = sprawdzCzyNiePuste(adres ,"Adres");
    }
    private String sprawdzCzyNiePuste (String value, String message){

        if (value!= null && value.length() > 0){
            return value;
        } else {
            throw new IllegalArgumentException(message + " nie może być puste");
        }
    }
    public static void zapiszKlienta(Set<Klient> zbior, String nazwaPliku){
        try {
            PrintWriter printWriter = new PrintWriter("pliki/" + nazwaPliku);

            for (Klient k : zbior) {

                if (k.firma){

                }

                printWriter.print(k.getImie());
                printWriter.print(";");
                printWriter.print(k.getNazwisko());
                printWriter.print(";");
                printWriter.print(k.getPesel());
                printWriter.print(";");
                printWriter.print(k.getAdres());
                printWriter.print(";");
                printWriter.print(k.getNip());
                printWriter.print(";");
                printWriter.print(k.getNazwaFirmy());
                printWriter.print(";");

                printWriter.println();
            }


            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static HashSet<Klient> wczytajZPliku(String nazwaPliku){
    //    Klient klient;
        HashSet<Klient> daneZPliku = new HashSet<Klient>();

            try {
                Scanner scanner = new Scanner(new File("pliki/" + nazwaPliku));
               // boolean czyWyjsc = true;

                while (scanner.hasNextLine()) {

                    String odzyt = scanner.nextLine();
                    if (odzyt == null || odzyt == ""){
                        break;
                    }

                    String[] split = odzyt.split(";");
                    for (String s : split) {
                        System.out.println(s);
                    }
                    if (Boolean.valueOf(split[4])) {
                        Klient klient = new Klient(split[5], Long.valueOf(split[4]), split[3]);
                        klient.setFirma(true);
                        daneZPliku.add(klient);
                    } else {
                        daneZPliku.add(new Klient(split[0], split[1], Long.valueOf(split[2]), split[3]));
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

       return daneZPliku;
    }

}

