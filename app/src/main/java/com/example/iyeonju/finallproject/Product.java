package com.example.iyeonju.finallproject;

/**
 * Created by iyeonju on 2017. 11. 25..
 */

public class Product {
    private int _productid;
    private String _productname;
    private int _quantity;

    public Product() {
    }

    public Product(int id, String productname, int quantity) {
        this._productid = id;
        this._productname = productname;
        this._quantity = quantity;
    }

    public Product(String productname, int quantity) {
        this._productname = productname;
        this._quantity = quantity;
    }

    public void setID(int id) {
        this._productid = id;
    }

    public int getID() {
        return this._productid;
    }

    public void setProductName(String productname) {
        this._productname = productname;
    }

    public String getProductName() {
        return this._productname;
    }

    public void setQuantity(int quantity) {
        this._quantity = quantity;
    }

    public int getQuantity() {
        return this._quantity;
    }
}