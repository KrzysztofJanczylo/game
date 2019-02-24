public class NipValidator {

    /**
     * Rozważmy sumę kontrolną NIP, wystawioną przez Urząd Skarbowy Poznań-Nowe Miasto.
     * Początek tego numeru to 362-398-12-3.
     * Teraz kolejne cyfry należy przemnożyć przez odpowiednie wagi i dodać do siebie.
     * 3*6 + 6*5 + 2*7 + 3*2 + 9*3 + 8*4 + 1*5 + 2*6 + 3*7 =
     * 18 + 30 + 14 + 6 + 27 + 32 + 5 + 12 + 21 = 165
     * Następnie wynik dzielimy modulo 11
     * 165 mod 11 = 0
     * Zatem prawidłowy numer NIP to: 362-398-12-30
     *
     * @param nip
     * @return
     */
    public static boolean valid(long nip) {

        String nipString = String.valueOf(nip);

        if (nipString.length() != 10) {
            return false;
        }
        String[] split = nipString.split("");

        int sumaKontrolna = Integer.valueOf(split[0]) * 6 +
                Integer.valueOf(split[1]) * 5 +
                Integer.valueOf(split[2]) * 7 +
                Integer.valueOf(split[3]) * 2 +
                Integer.valueOf(split[4]) * 3 +
                Integer.valueOf(split[5]) * 4 +
                Integer.valueOf(split[6]) * 5 +
                Integer.valueOf(split[7]) * 6 +
                Integer.valueOf(split[8]) * 7;

        sumaKontrolna = sumaKontrolna % 11;

        return sumaKontrolna == Integer.valueOf(split[9]);
    }
}
