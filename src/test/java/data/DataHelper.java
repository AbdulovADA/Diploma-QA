package data;


import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    @Value
    public static class Card {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    private static final Faker fakerEng = new Faker(Locale.ENGLISH);
    private static final Faker fakerRu = new Faker(new Locale("ru", "RU"));

    public static String getApprovedNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getZeroNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getRandomNumber() {
        return fakerEng.numerify("#### #### #### ####");
    }

    public static String getFifteenRandomNumber() {
        return fakerEng.numerify("#### #### #### ###");
    }

    public static String getOneRandomNumber() {
        return fakerEng.numerify("#");
    }

    public static String getEmptyNumber() {
        return "";
    }

    public static String getMonth(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getTwelveMonth() {
        return "12";
    }

    public static String getElevenMonth() {
        return "11";
    }

    public static String getFirstMonth() {
        return "01";
    }

    public static String getSecondMonth() {
        return "02";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getOneSymbolMonth() {
        return "2";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getYear(int plusYear) {
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getOneSymbolYear() {
        return "1";
    }

    public static String getZeroYear() {
        return "00";
    }

    public static String getEngHolder() {
        return fakerEng.name().name().toUpperCase();
    }

    public static String getChoiceSymbolHolder(String countSymbols) {
        return fakerEng.letterify(countSymbols).toUpperCase();
    }

    public static String getEmptyHolder() {
        return "";
    }

    public static String getSpacesHolder() {
        return "      ";
    }

    public static String getSpecialCharactersHolder() {
        return "#@$%^^&*!.,)(+=-'/|№";
    }

    public static String getDigitsHolder(String countSymbols) {
        return fakerEng.numerify(countSymbols);
    }


    public static String getCyrillicHolder() {
        return fakerRu.name().name().toUpperCase();
    }

    public static String getCVC() {
        return fakerEng.numerify("###");
    }

    public static String getTwoSymbolsCVC() {
        return fakerEng.numerify("##");
    }

    public static String getOneSymbolCVC() {
        return fakerEng.numerify("#");
    }

    public static String getZeroSymbolsCVC() {
        return "000";
    }

    public static String getEmptyCVC() {
        return "";
    }

}