package pl.flaaaxxx.springhomeworkweek5.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.flaaaxxx.springhomeworkweek5.model.currency.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class CurrencyService {

    private Currency currency;
    private BigDecimal currencyPLN;
    private int index;

    Random generator;

    private String[] codes = {"uSD", "aED", "aRS", "aUD", "bGN", "bRL", "bSD", "cAD",
            "cHF", "cLP", "cNY", "cZK", "dKK", "dOP", "eGP", "eUR", "gBP",
            "gTQ", "hKD", "hRK", "hUF", "iLS", "iNR", "iSK", "jPY",
            "mXN", "mYR", "nOK", "nZD", "pAB", "pEN", "pHP", "pKR", "rON", "rUB",
            "sAR", "sGD", "tHB", "tRY", "tWD", "uAH", "uYU", "zAR"};

    public CurrencyService() {
//        currency draw
        generator = new Random();
        index = generator.nextInt(codes.length);
        getCurrency(codes[index]);
    }


    private Currency getCurrency(String code) {
        RestTemplate restTemplate = new RestTemplate();
        currency = restTemplate.getForObject("https://api.exchangerate-api.com/v4/latest/" + code.toUpperCase(), Currency.class);
        currencyPLN = new BigDecimal(currency.getRates().getPLN());
        currencyPLN = currencyPLN.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Wylosowano: " + codes[index].toUpperCase() + "/PLN = " + currencyPLN);
        return currency;
    }

    public void draw() {
        index = generator.nextInt(codes.length);
        getCurrency(codes[index]);
    }

    public String getDrawCode() {
        return codes[index].toUpperCase();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyPLN() {
        return currencyPLN;
    }

    public void setCurrencyPLN(BigDecimal currencyPLN) {
        this.currencyPLN = currencyPLN;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}
