package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Candy {
    private int id;
    private String name;
    private String description;
    private int price;
    private String type;

    public Candy(int id, String name, String description, int price, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public String printPrice() {
        Double realPrice = Double.parseDouble(Integer.toString(price));
        realPrice = realPrice / 100;

        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(realPrice);
    }

    public void setRealPrice(String toFormat){
        toFormat = toFormat.replaceAll("[^\\d.,]","").replaceAll(",","\\.");
        double realPrice = Double.parseDouble(toFormat);
        double newPrice = realPrice * 100;
        this.price = (int) newPrice;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
