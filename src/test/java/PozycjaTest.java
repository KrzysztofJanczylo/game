import org.junit.Test;

import static org.junit.Assert.*;

public class PozycjaTest {
    @Test
    public void testTworzeniePozycji(){
        //givem

        Pozycja pozycja = new Pozycja();

        //when

        pozycja.setNazwaTowaru("Chleb");
        pozycja.setIleSztuk(2);
        pozycja.setCena(5.20);
        //then
        assertEquals("Chleb", pozycja.getNazwaTowaru());
        assertEquals(2, pozycja.getIleSztuk());
        assertEquals(Double.valueOf(5.20), Double.valueOf(pozycja.getCena()));
    }

    @Test
    public void testKonstruktoraZParametrami(){
        //given
        Pozycja pozycja1 = new Pozycja("Cukier", 5, 4.80);
        //when

        //then
        assertEquals("Cukier", pozycja1.getNazwaTowaru());
        assertEquals(5, pozycja1.getIleSztuk());
        assertEquals(Double.valueOf(4.80), Double.valueOf(pozycja1.getCena()));

    }
    @Test
    public void testObliczWartosc(){
        //given
        Pozycja pozycja2 = new Pozycja("Cukier", 5, 4.80);
        //when
        double wartoscPozycji = pozycja2.obliczWartosc();
        //then
        assertEquals(Double.valueOf(24), Double.valueOf(wartoscPozycji));
    }

    @Test
    public void testObliczWartoscPoZmianie(){
        //given
        Pozycja pozycja3 = new Pozycja("Cukier", 5, 4.80);

        //when
        pozycja3.setIleSztuk(10);
        double wartoscPozycji = pozycja3.obliczWartosc();
        //then
        assertEquals(Double.valueOf(48), Double.valueOf(wartoscPozycji));

    }

    @Test
    public void testToString(){

        //given
        Pozycja pozycja4 = new Pozycja("Jajka", 10, 0.6);

        //when
        System.out.println(pozycja4);

        //then
    }
    @Test
    public void testPromocjaPoniżej5Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 4, 10);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(40), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej5Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 6, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(570), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej10Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 19, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(1710), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej20Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 40, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(3400), Double.valueOf(wartoscZRabatem));
    }

}