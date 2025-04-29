/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows
 */

package com.example.lancheTDS.controller;

import com.example.lancheTDS.dao.ProductDAO;
import com.example.lancheTDS.model.Product;

public class ProductController {

    private static ProductDAO productDAO = new ProductDAO();

    public static boolean addProduct(String name, double price, String description) {
        Product product = new Product(name, price, description);
        return productDAO.addProduct(product);
    }

    public static Product getProductById(int id) {
        return productDAO.getProdById(id);
    }

    public static boolean updateProduct(int id, String name, double price, String description) {
        Product product = new Product(name, price, description);
        product.setId(id);
        return productDAO.updateProduct(product);
    }

    public static boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }
}

