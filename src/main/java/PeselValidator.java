public class PeselValidator {
    /**
     * Rozważmy PESEL osoby urodzonej 8 lipca 1902 roku, płci żeńskiej (parzysta końcówka numeru z serii – 0362). Czyli mamy wówczas 0207080362. Teraz kolejne cyfry należy przemnożyć przez odpowiednie wagi i dodać do siebie.
     * 0*1 + 2*3 + 0*7 + 7*9 + 0*1 + 8*3 + 0*7 + 3*9 + 6*1 + 2*3 = 0 + 6 + 0 + 63 + 0 + 24 + 0 + 27 + 6 + 6 = 132
     * Wynik dzielimy modulo przez 10.
     * 132 mod 10 = 2
     * A następnie odejmujemy od 10
     * 10 - 2 = 8.
     * I wynik dzielimy znów modulo 10
     * 8 mod 10 = 8
     * Cyfra kontrolna to 8, zatem nasz prawidłowy numer PESEL to: 02070803628
     *
     * @param pesel
     * @return
     */
    public static boolean valid(long pesel) {
        String peselNaStringu = String.valueOf(pesel);
        if (peselNaStringu.length() != 11) {
            return false;
        }
        String[] split = peselNaStringu.split("");
        int sumaKontrolna = Integer.valueOf(split[0]) * 1 +
                Integer.valueOf(split[1]) * 3 +
                Integer.valueOf(split[2]) * 7 +
                Integer.valueOf(split[3]) * 9 +
                Integer.valueOf(split[4]) * 1 +
                Integer.valueOf(split[5]) * 3 +
                Integer.valueOf(split[6]) * 7 +
                Integer.valueOf(split[7]) * 9 +
                Integer.valueOf(split[8]) * 1 +
                Integer.valueOf(split[9]) * 3;


        sumaKontrolna = sumaKontrolna % 10;
        sumaKontrolna = 10 - sumaKontrolna;
        sumaKontrolna = sumaKontrolna % 10;

        return sumaKontrolna == Integer.valueOf(split[10]);
    }
}
