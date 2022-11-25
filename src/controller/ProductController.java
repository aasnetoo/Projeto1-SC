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
            boolean existProduct = false;
            Map<String, Object> product = view.getProductInformation();
            for (int i = 0; i < Product.productsStock.size(); i++) {
                var A = Product.productsStock.get(i).get("nome").toString();
                var B = product.get("nome").toString();
                if (A.equalsIgnoreCase(B)){
                    System.err.println("Produto existente. Deseja continuar e somar os valores? 's' para continuar");
                    var a = view.answer();
                    if (!a.equalsIgnoreCase("s")){
                        existProduct = true;
                        continue;
                    }
                    Integer stockQuantity = Integer.parseInt(Product.productsStock.get(i).get("quantidade").toString());
                    Integer newQuantity = Integer.parseInt(product.get("quantidade").toString());

                    Product.productsStock.get(i).put("quantidade",stockQuantity+ newQuantity);
                    Product.productsStock.get(i).put("valor",product.get("valor"));
                    existProduct = true;

                }

            }
            if(!existProduct) {
                Product.productsStock.add(product);
            }


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
        List<Integer> teste = new ArrayList<>();
        while(continueLoop){
            Product product = new Product();
            view.listProducts();
            String[] selectedProduct = view.sellProducts().split(",");
            var quantityItem = Integer.parseInt(selectedProduct[1]);
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
//            productForCart.put("quantidade", quantityItem);
            // Adicionado o produto a lista do carrinho
            cart.add(productInStock);
            teste.add(quantityItem);

            // Método para atualizar a quantidade de itens no estoque
            updateStock(productInStock, (quantityItem));

            String answer = view.newPurchase();
            if(answer.equals("n")) continueLoop = false;
        }
        double soma = 0;
        System.out.println("Produtos no carrinho: ");
        for (int i = 0; i < cart.size(); i++) {
            System.out.println("Nome: "+cart.get(i).get("nome")+"- Quantidade: "+teste.get(i)+" - Valor: R$ "+cart.get(i).get("valor"));
            soma += Double.parseDouble(cart.get(i).get("valor").toString())*teste.get(i);
        }
        System.out.println("Valor total do carrinho: R$ "+soma);
    }

    private void updateStock(Map<String, Object> productInStock, Integer quantityItem) {
        // Map para encontrar o produto a ser atualizado o estoque
        int cont = 0;
        for (int i = 0; i < Product.productsStock.size(); i++) {
            Map<String,Object> product = new HashMap<>();
            product = Product.productsStock.get(i);

            String name = (String) product.get("nome");
            String searchProduct = (String) productInStock.get("nome");

            // Calculo do novo estoque onde é pego o estoque do arquivo menos o estoque da compra efetuada

            Integer a =  Integer.parseInt(product.get("quantidade").toString());
            Integer b = quantityItem;

            Integer newStock = (a - b);
            // Ao encontrar o produto correto atualizar a quantidade no arquivo
            if (name.toLowerCase().equals(searchProduct) && newStock == 0) {
                Product.productsStock.remove(i);
            }




            if(name.toLowerCase().equals(searchProduct)){
                String teste = Integer.toString(newStock);
                product.put(("quantidade"), teste);
            }
        }
        Product.saveStock();
    }

}
