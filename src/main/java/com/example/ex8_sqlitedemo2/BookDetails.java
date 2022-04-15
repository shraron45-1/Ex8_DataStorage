package com.example.ex8_sqlitedemo2;

public class BookDetails {
    private String name;
    private String category_name;
    private String price;

    public BookDetails(String name, String category_name, String price) {
        this.name = name;
        this.category_name = category_name;
        this.price = price;
    }

    public BookDetails() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getPrice() {
        return price;
    }
}
