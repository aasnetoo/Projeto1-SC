package view;

import model.Product;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductView {
    Scanner scan = new Scanner(System.in);

    public String optionMenu(){
        System.out.print(Messages.messageMenu);
        System.out.print(Messages.messageInputLine);
        return scan.nextLine();
    }

    public Map<String, Object> getProductInformation() {
        Map<String, Object> product = new HashMap<>();
        System.out.println("Nome do produto: ");
        System.out.print("> ");
        product.put("nome", scan.nextLine());
        System.out.println("Quantidade do produto: ");
        System.out.print("> ");
        char quantity = scan.next().charAt(0);
        scan.nextLine();
        boolean quantityTest = true;
        while(quantityTest) {
            if (Character.isAlphabetic(quantity)){
                System.err.println("Opção inválida. Digite um número.");
                System.out.print("> ");
                quantity = scan.next().charAt(0);
                scan.nextLine();
            }else{
                quantityTest = false;
            }
        }
        product.put("quantidade",Character.toString(quantity));
        System.out.println("Valor do produto: ");
        System.out.print("> ");
        char value = scan.next().charAt(0);
        scan.nextLine();
        boolean valueTest = true;
        while(valueTest) {
            if (Character.isAlphabetic(value)){
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("\n> ");
                value = scan.next().charAt(0);
                scan.nextLine();
            }else{
                valueTest = false;
            }
        }
        product.put("valor",Character.toString(value));

        return product;
    }

    public String editProduct(){
        System.out.println("Você deseja editar qual produto? Escolha o produto pelo índice ou digite '0' para voltar ao menu.");
        listProducts();
        return answer();
    }

    public String removeProduct() {
        System.out.println("Você deseja remover qual produto? Escolha o produto pelo índice ou digite '0' para voltar ao menu.");
        listProducts();
        String option = answer();
        char optionVerification = option.charAt(0);
        if (Character.isAlphabetic(optionVerification)){
            System.err.println("Opção inválida. Digite um número.");
            option = "0";

        }
        if(option.equals("0")){
            System.out.println("Voltando para o menu. ");
        }

        return option;
    }

    public void listProducts() {
        System.out.print(Messages.messageListProduct);
        for (int i = 0; i < Product.productsStock.size(); i++){
            Map<String, Object> product = Product.productsStock.get(i);
            String stringNameProduct = product.get("nome").toString();

            // Deixar o nome do produto com a primeira letra maíuscula e restante minúsculo
            String nameCapitalized = stringNameProduct.substring(0,1).toUpperCase() + stringNameProduct.substring(1).toLowerCase();
            String quantity = product.get("quantidade").toString();
            Float price = Float.parseFloat(product.get("valor").toString());
            System.out.printf(Messages.messageTableProduct,
                    (i+1), nameCapitalized , quantity , price);
        }
        System.out.println(Messages.messageEndTable);
    }

    public String searchProduct() {
        System.out.println("Digite o nome do produto ou parte dele: ");

        return scan.nextLine().toLowerCase();
    }

    public String sellProducts() {
        System.out.println("O que deseja comprar? Escolha o produto pelo índice. ");
        String option = answer();
        boolean testValue = true;
        while(testValue) {
            if (Integer.parseInt(option) > Product.productsStock.size() || Integer.parseInt(option) <= 0) {
                System.out.println("Valor inválido. Tente novamente");
                option = scan.nextLine();
            } else {
                testValue = false;

            }
        }
        String quantity;
        System.out.println("Qual a quantidade? Digite um número.");
        quantity = answer();
        boolean quantityValue = true;
        while(quantityValue) {
            if (Integer.parseInt(quantity) <= 0) {
                System.out.println("Valor inválido. Tente novamente");
                quantity = scan.nextLine();
            } else {
                quantityValue = false;
            }
        }
        option += "," + quantity;

        return option;
    }

    public void listFilteredProducts(List<Map<String, Object>> products){
        System.out.print(Messages.messageFilteredProducts);
        for (int i = 0; i < products.size(); i++){
            Map<String, Object> product = products.get(i);

            String stringNameProduct = product.get("nome").toString();

            // Deixar o nome do produto com a primeira letra maíuscula e restante minúsculo
            String nameCapitalized = stringNameProduct.substring(0,1).toUpperCase() + stringNameProduct.substring(1).toLowerCase();
            String quantity = product.get("quantidade").toString();
            Float price = Float.parseFloat(product.get("valor").toString());

            System.out.printf(Messages.messageTableProduct,
                    (i+1), nameCapitalized, quantity,  price);
        }
        System.out.println(Messages.messageEndTable);
    }

    public String insufficientStock(Map<String, Object> productInStock) {
        System.err.printf(Messages.messageProductExists, productInStock.get("quantidade"));
        return answer();
    }

    public String newPurchase() {
        System.out.println("Deseja algo mais? 's' para sim ou 'n' para não.");
        return answer();
    }

    public String answer(){
        return scan.nextLine();
    }
}