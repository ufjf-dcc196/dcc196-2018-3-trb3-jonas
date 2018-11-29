package model;

import java.util.Map;

public class Report {
    private Map<Integer,Integer> stock;
    private int revenues;

    public Report(Map<Integer, Integer> stock, int revenues) {
        this.stock = stock;
        this.revenues = revenues;
    }

    public Map<Integer, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Integer, Integer> stock) {
        this.stock = stock;
    }

    public int getRevenues() {
        return revenues;
    }

    public void setRevenues(int revenues) {
        this.revenues = revenues;
    }
}
