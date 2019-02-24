import org.junit.Test;

import static org.junit.Assert.*;

public class KlientTest {

    @Test
    public void testKlient() {
        //given
        Klient klient = new Klient();

        //when
        klient.setAdres("Błotna");
        klient.setImie("Tomek");
        klient.setNazwisko("Aaaa");
        klient.setPesel(12345678911l);
        //then
        assertEquals(false, klient.isFirma());
        assertEquals("Tomek", klient.getImie());
        assertEquals("Aaaa", klient.getNazwisko());
        assertEquals(12345678911l, klient.getPesel());
    }

    @Test
    public void testFirma() {
        //given
        Klient firma = new Klient();
        //when
        firma.setFirma(true);
        firma.setNazwaFirmy("Kogucik");
        firma.setNip(1234567891);
        //then
        assertEquals(true, firma.isFirma());
        assertEquals("Kogucik", firma.getNazwaFirmy());
        assertEquals(1234567891, firma.getNip());
    }

    @Test
    public void testKonstruktoraPoprawnyPesel() {
        //given
        long pesel = 27092924229L;
        //when
        Klient klient = new Klient("Jan", "Kowalski", pesel, "ulica Błotna");

        //then
        assertEquals(pesel, klient.getPesel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktoraNiePoprawnyPesel() {
        //given
        long pesel = 17092924229L;

        //when
        Klient klient = new Klient("Jan", "Kowalski", pesel, "ulica Błotna");

        //then
    }
}