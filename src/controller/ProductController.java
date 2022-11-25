package controller;

import java.util.*;

import model.Product;
import view.ProductView;

/**
 * Productcontroller
 */
public class ProductController {
    ProductView view = new ProductView();

    public void menu(){
        Boolean saida = true;
        while(saida){
            String opcao = view.optionMenu();

            switch(opcao){
                case "1" -> createProduct();
                case "2" -> editProduct();
                case "3" -> removeProduct();
                case "4" -> listProducts();
                case "5" -> searchProduct();
                case "6" -> sellProducts();
                case "0" -> {
                    saida = false;
                }
                default -> System.out.println("Opção Inválida. ");
            }
        }
    }

    private void createProduct() {
        while(true){
            Map<String, Object> product = view.getProductInformation();
            Product.productsStock.add(product);

            System.out.println("Continuar adicionando produtos? ");
            if(!view.answer().equals("s")) break;
        }
        Product.saveStock();
    }

    private void editProduct() {
        String option = view.editProduct();

        if(option.equals("0")){
            System.out.println("Voltando para o menu. ");
        } else {
            Map<String, Object> newProduct = view.getProductInformation();
            Product.productsStock.get(Integer.parseInt(option) - 1).putAll(newProduct);
        }
    }

    private void removeProduct() {
        String option = view.removeProduct();
        Product.productsStock.remove(Integer.parseInt(option) - 1);
    }

    private void listProducts() {
        view.listProducts();
    }

    private void searchProduct() {
        String search = view.searchProduct();
        List<Map<String, Object>> filteredProducts = new ArrayList<>();
        for(Map<String, Object> product : Product.productsStock){
            String name = (String) product.get("nome");

            if(name.toLowerCase().contains(search)){
                filteredProducts.add(product);
            }
        }
        view.listFilteredProducts(filteredProducts);
    }

    private void sellProducts() {
        boolean continueLoop = true;
        List<Map<String, Object>> cart = new ArrayList<>();
        while(continueLoop){
            view.listProducts();
            String[] selectedProduct = view.sellProducts().split(",");
            Map<String, Object> productInStock = Product.productsStock.get(Integer.parseInt(selectedProduct[0]) - 1);
            // Verificando se o estoque for igual a zero ou menor que o valor pedido
            if(         Integer.parseInt(productInStock.get("quantidade").toString().trim()) == 0
                    ||  Integer.parseInt(productInStock.get("quantidade").toString().trim()) < Integer.parseInt(selectedProduct[1])
            ){
                String answer = view.insufficientStock(productInStock);
                if(answer.equals("s")) continue;
                break;
            }

            // Inserido no Map a quantidade desejada na compra
         //   productInStock.put("quantidade", (Integer.parseInt(selectedProduct[1])));
            // Adicionado o produto a lista do carrinho
            cart.add(productInStock);

            // Método para atualizar a quantidade de itens no estoque
            updateStock(productInStock, selectedProduct);

            String answer = view.newPurchase();
            if(answer.equals("n")) continueLoop = false;
        }
        view.listFilteredProducts(cart);
    }

    private void updateStock(Map<String, Object> productInStock, String[] selectedProduct) {
        // Map para encontrar o produto a ser atualizado o estoque
        int cont = 0;
        for (int i = 0; i < Product.productsStock.size(); i++) {
            Map<String,Object> product = new HashMap<>();
            product = Product.productsStock.get(i);

            String name = (String) product.get("nome");
            String searchProduct = (String) productInStock.get("nome");

            // Calculo do novo estoque onde é pego o estoque do arquivo menos o estoque da compra efetuada

            Integer a =  Integer.parseInt(product.get("quantidade").toString());
            Integer b = Integer.parseInt(selectedProduct[1]);

            Integer newStock = (a - b);
            // Ao encontrar o produto correto atualizar a quantidade no arquivo
            if (name.toLowerCase().equals(searchProduct) && newStock == 0) {
                Product.productsStock.remove(i);
            }

            if(name.toLowerCase().equals(searchProduct)){
                String newStocks = Integer.toString(newStock);
                product.put("quantidade", newStocks);
            }
        }
        Product.saveStock();
    }

}
