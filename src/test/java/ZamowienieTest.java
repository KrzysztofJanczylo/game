import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZamowienieTest {

    @Test
    public void testKonstruktoraBezArgumentow(){
        //given
        Zamowienie zamowienie = new Zamowienie();
        //when
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja2);
        zamowienie.setPozycje(listaPozycji);
        //then
        assertEquals(2, zamowienie.getPozycje().size());
    }

    @Test
    public void testKonstruktoraZArgumentami(){
        //given
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja2);
        //when
        Zamowienie zamowienie2 = new Zamowienie(listaPozycji);
        //then
        assertEquals(2, zamowienie2.getPozycje().size());
    }

    @Test
    public void testDodawaniaJednejPozycji(){
        //given
        Zamowienie zamowienie3 = new Zamowienie();
        //when
        Pozycja pozycja3 = new Pozycja("Woda", 10, 1.40);
        zamowienie3.dodajPozycje(pozycja3);
        //then
        assertEquals(1,zamowienie3.getPozycje().size());
    }

    @Test
    public void testDodawaniaJednejPozycjiNullowej(){
        //given
        Zamowienie zamowienie4 = new Zamowienie();
        //when
        zamowienie4.dodajPozycje(null);
        //then
        assertEquals(0,zamowienie4.getPozycje().size());
    }
    @Test
    public void testSumyZamowienia2Pozycji(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //when
        double suma = zamowienie5.obliczWartosc();
        //then
        assertEquals(Double.valueOf(pozycja.obliczWartosc() +pozycja2.obliczWartosc())
                ,Double.valueOf(suma));
    }

    @Test
    public void testWyswietlZamowienie(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //when

        //then
        //System.out.println(zamowienie5);

        assertTrue(zamowienie5.toString().contains("Razem: "));
        assertTrue(zamowienie5.toString().contains("Zamowienie:"));
        assertTrue(zamowienie5.toString().contains(
                String.format("%.2f",zamowienie5.obliczWartosc())));

    }

    @Test
    public void testUsuwaniaPozycji(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //then
        zamowienie5.usunPozycje(0);
        //when
        assertEquals(1,zamowienie5.getPozycje().size());
        System.out.println(zamowienie5.getPozycje().get(0));

    }
    @Test
    public void testUsuwaniaPozycjiSpozaZakresu(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //then
        zamowienie5.usunPozycje(2);
        //when
        assertEquals(2,zamowienie5.getPozycje().size());
        //System.out.println(zamowienie5.getPozycje().get(0));

    }

    @Test
    public void testEdycjiPozycji(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 8.5);
        Pozycja pozycja3 = new Pozycja("Kiełba", 13, 19.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        zamowienie5.dodajPozycje(pozycja3);
        //when
        System.out.println(zamowienie5);
        zamowienie5.edytujPozycje(1,"Jogurt", 8, 1.20);
        System.out.println(zamowienie5);
        //then
        assertEquals("Jogurt", zamowienie5.getPozycje().get(1).getNazwaTowaru());
    }

    @Test
    public void testDodaje2ChlebyWTejSamejCenie(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Chleb", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 2.5);
       // Pozycja pozycja3 = new Pozycja("Kiełba", 13, 19.5);

       // zamowienie5.dodajPozycje(pozycja3);
        //when
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //then
        assertEquals(1, zamowienie5.getPozycje().size());
    }

    @Test
    public void testDodaje2ChlebyWInnejCenie(){
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Chleb", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 2.55);
        // Pozycja pozycja3 = new Pozycja("Kiełba", 13, 19.5);

        // zamowienie5.dodajPozycje(pozycja3);
        //when
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //then
        assertEquals(2, zamowienie5.getPozycje().size());
    }

}