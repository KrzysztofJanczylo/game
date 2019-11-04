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

    @Test
    public void testKonstruktoraPoprawnyNip() {
        //given
        long nip = 7612731690L;
        //when
        Klient klient = new Klient("DELTA",nip,"ul Sienkiewicza");

        //then
        assertEquals(nip, klient.getNip());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktoraNiePoprawnyNip() {
        //given
        long nip = 7612731190L;
        //when
        Klient klient = new Klient("DELTA",nip,"ul Sienkiewicza");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNulloweImie(){
        //give
        Klient klient = new Klient(null,"kowalski", 60022733609L,"Londek Zdrój");
        //then
        //when
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPusteImie(){
        //give
        Klient klient = new Klient("","kowalski", 60022733609L,"Londek Zdrój");
        //then
        //when
    }


    @Test(expected = IllegalArgumentException.class)
    public void testPustyAdresFirmy(){
        //give
        Klient klient = new Klient("FIRST DAT", 5335047650L,"");
        //then
        //when
    }
}