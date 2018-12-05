package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Revenue {
    int amount;

    public Revenue(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String printPrice() {
        Double realPrice = Double.parseDouble(Integer.toString(amount));
        realPrice = realPrice / 100;

        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(realPrice);
    }

    public void setRealPrice(String toFormat){
        toFormat = toFormat.replaceAll("[^\\d.,]","").replaceAll(",","\\.");
        double realPrice = Double.parseDouble(toFormat);
        double newPrice = realPrice * 100;
        this.amount = (int) newPrice;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }
}
