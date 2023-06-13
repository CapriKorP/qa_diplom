package data;

public class Card {

    public String number;
    public String month;
    public String year;
    public String holder;
    public String cvc;

    public Card(String number, String month, String year, String holder, String cvc) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.holder = holder;
        this.cvc = cvc;
    }
}
