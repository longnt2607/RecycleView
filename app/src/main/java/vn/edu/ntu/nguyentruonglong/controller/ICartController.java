package vn.edu.ntu.nguyentruonglong.controller;

import java.util.List;

import vn.edu.ntu.nguyentruonglong.model.Product;

public interface ICartController {
    List<Product> getAllProduct();
    public boolean addToCart(Product p);
    public List<Product> getShoppingCart();
    public void clearShoppingCart();
}
