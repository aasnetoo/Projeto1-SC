package model;

import javax.management.ObjectName;
import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Array;
import java.util.*;
import java.util.stream.Stream;

public class Product{
    public static List<Map<String, Object>> productsStock = new ArrayList<>();
    final private static Path pathOfFile = Paths.get("productsStock.txt");



    public static void verifyStock() throws IOException {
        if (Files.exists(pathOfFile) && Files.size(pathOfFile)==0){
            Files.delete(pathOfFile);
        }
        if (Files.exists(pathOfFile)) {
            Stream<String> stream = Files.lines(pathOfFile, StandardCharsets.UTF_8);
            stream.forEach(stringProduct -> {
                Map<String, Object> product = new HashMap<>();
                String[] productInfos = stringProduct.split(",");
                product.put("nome", productInfos[0]);
                product.put("quantidade", productInfos[1]);
                product.put("valor", productInfos[2]);
                productsStock.add(product);

            });
        }
    }

    public static void saveStock(){
        List<String> toSaveStock = new ArrayList<>();

        productsStock.forEach(product -> {
            var name = product.get("nome").toString().toLowerCase();
            var quantity = product.get("quantidade");
            var price = product.get("valor");

            toSaveStock.add("" + name + "," + quantity + ","  + price);
        });

        try {
            Files.deleteIfExists(pathOfFile);
            Files.write(pathOfFile, toSaveStock, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erro de arquivo: " + e.getMessage());;
        }
    }


}
