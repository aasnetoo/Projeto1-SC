package application;

import controller.ProductController;
import model.Product;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        try {
            Product.verifyStock();
            ProductController productController = new ProductController();
            productController.menu();
        }catch (InputMismatchException | IOException e){
            System.err.println("Erro: " + e.getMessage());
        }

    }
}
