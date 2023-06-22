package ru.korndev.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.String.valueOf;

public class DataService {
    static String[] name = {"Pavel", "George","Aleksandr", "Boris", "Olga", "Nikolay"};
    static String[] surname = {"Ivanov", "Petroff", "Levenguk", "Abcdefghijklmnopqrstuvwxyz"};
    static String[] namesNegative = {"Иван", "ἱερογλύφος", "中国大使馆的招待会", "الناس" };
    static String[] surnameNegative = {"Иванов", "γλύἱρος", "食物短缺促使价格上涨", "راراأ"};
    static String[] approvedCardNumber = {"4444444444444441"};
    static String[] declinedCardNumber = {"4444444444444442"};
    static String[] randomCardNumber = {
            "4444 4444 4444 4440",
            "4444 4444 4444 4443",
            "0000 0000 0000 0000",
            "1111 1111 1111 1111",
            "9999 9999 9999 9999",
            "5555 5555 5555 5555",
            "1234 5678 9012 3456"
    };
    static String[] randomFifteenCardNumber = {
            "4444 4444 4444 444",
            "0000 0000 0000 000",
            "1111 1111 1111 111",
            "9999 9999 9999 999",
            "5555 5555 5555 555",
            "1234 5678 9012 345"
    };
    static String[] randomSeventeenCardNumber = {
            "4444 4444 4444 44401",
            "0000 0000 0000 00031",
            "1111 1111 1111 11111",
            "9999 9999 9999 99991",
            "5555 5555 5555 55551",
            "1234 5678 9012 34567"
    };
    static String[] symbols = {"#","&","@","$","&","+","*","^","%","№","="};
    static String[] alphabetEn = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static String emptySymbol() {
        return "";
    }

    //Номера карт
    public static String approvedCardNumber() {
        Random random = new Random();
        return approvedCardNumber[random.nextInt(approvedCardNumber.length)];
    }

    public static String declinedCardNumber() {
        Random random = new Random();
        return declinedCardNumber[random.nextInt(declinedCardNumber.length)];
    }

    public static String randomDigit() {
        Random random = new Random();
        return valueOf(random.nextInt(9));
    }

    public static String randomCardNumber() {
        Random random = new Random();
        return randomCardNumber[random.nextInt(randomCardNumber.length)];
    }

    public static String fifteenCardNumber() {
        Random random = new Random();
        return randomFifteenCardNumber[random.nextInt(randomFifteenCardNumber.length)];
    }

    public static String seventeenCardNumber() {
        Random random = new Random();
        return randomSeventeenCardNumber[random.nextInt(randomSeventeenCardNumber.length)];
    }


    public static String lettersNumber() {
        int count = 16;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(alphabetEn[rand.nextInt(alphabetEn.length)]);
        }
        return sb.toString();
    }

    public static String getSymbolsNumber() {
        int count = 16;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(symbols[rand.nextInt(symbols.length)]);
        }
        return sb.toString();
    }

    // Владелец карты
    public static String cardHolder() {
        Random random = new Random();
        return (name[random.nextInt(name.length)] + " " + surname[random.nextInt(surname.length)]);
    }

    public static String overLimitCardHolder() {
        int count = 64;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(alphabetEn[rand.nextInt(alphabetEn.length)]);
        }
        return sb.toString();
    }

    public static String symbolCardHolder() {
        int count = 16;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(symbols[rand.nextInt(symbols.length)]);
        }
        return sb.toString();
    }

    public static String numbersCardHolder() {
        Random random = new Random();

        String numOne = valueOf(random.nextInt(9999999));
        String numTwo = valueOf(random.nextInt(9999999));
        return numOne + " " + numTwo;
    }

    public static String negativeCardHolder() {
        Random random = new Random();
        return (namesNegative[random.nextInt(namesNegative.length)] + " " + surnameNegative[random.nextInt(surnameNegative.length)]);
    }

    public static String onlyNameCardHolder() {
        Random random = new Random();
        return name[random.nextInt(name.length)];
    }

    public static String threeWordCardHolder() {
        Random random = new Random();
        return name[random.nextInt(name.length)] + " " + name[random.nextInt(name.length)] + " " + surname[random.nextInt(surname.length)];
    }

    //Тестовые данные МЕСЯЦ
    public static String month() {
        Random random = new Random();
        return LocalDate.now().plusMonths(random.nextInt(12)).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String thisMonth() {
        return LocalDate.now().plusMonths(0).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String lastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String zeroMonthorYear() {
        return "00";
    }

    public static String thirteenMonth() {
        return "13";
    }

    public static String twoLetters() {
        int count = 2;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(alphabetEn[rand.nextInt(alphabetEn.length)]);
        }
        return sb.toString();
    }

    //Тестовые данные ГОД
    public static String year() {
        Random random = new Random();
        return LocalDate.now().plusYears(random.nextInt(5)).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String thisYear() {
        return LocalDate.now().minusYears(0).format(DateTimeFormatter.ofPattern("yy"));
    }
    public static String pastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String moreFiveYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String symbolYear() {
        int count = 2;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(symbols[rand.nextInt(symbols.length)]);
        }
        return sb.toString();
    }

    //Тестовые данные CVV

    public static String cvv() {
        Random random = new Random();
        return valueOf(random.nextInt(900) + 100);
    }

    public static String zeroCvv() {
        return "000";
    }
    public static String oneOrTwoSymbolsCVV() {
        Random random = new Random();
        return valueOf(random.nextInt(99));
    }

    public static String lettersOnCVV() {
        int count = 3;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(alphabetEn[rand.nextInt(alphabetEn.length)]);
        }
        return sb.toString();
    }

    public static String symbolCVV() {
        int count = 3;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < count; x++) {
            sb.append(symbols[rand.nextInt(symbols.length)]);
        }
        return sb.toString();
    }
}