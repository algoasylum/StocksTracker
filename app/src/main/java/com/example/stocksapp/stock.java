package com.example.stocksapp;

public class stock {

    //    private String id;
    private String compn;
    private String symbol;
    private String noofshares;
    private String price;
    private String tot_price;

    public stock(String compn, String symbol, String noofshares, String price, String tot_price) {
//        this.id = id;
        this.compn = compn;
        this.symbol = symbol;
        this.noofshares = noofshares;
        this.price = price;
        this.tot_price = tot_price;
    }

    public String getCompn() {
        return compn;
    }

    public void setCompn(String compn) {
        this.compn = compn;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNoofshares() {
        return noofshares;
    }

    public void setNoofshares(String noofshares) {
        this.noofshares = noofshares;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTot_price() {
        return tot_price;
    }

    public void setTot_price(String tot_price) {
        this.tot_price = tot_price;
    }
}
