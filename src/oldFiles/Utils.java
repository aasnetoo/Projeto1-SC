package oldFiles;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static void mostrarMenu(Scanner scan, Produto produto, List<Produto> produtos, List<Produto> produtosFiltrados) {

        System.out.println("Olá! Digite a opção que você deseja: ");
        System.out.println("1 - Cadastrar oldFiles.Produto");
        System.out.println("2 - Editar oldFiles.Produto");
        System.out.println("3 - Excluir oldFiles.Produto");
        System.out.println("4 - Pesquisa de Produtos");
        System.out.println("5 - Compra de Produtos");
        System.out.println("6 - Sair do programa");

        int opcao = scan.nextInt();
        scan.nextLine();
        switch (opcao) {
            case 1 -> criarProduto(scan, produtos);
            case 2 -> editarProduto(produtos,scan,produto);
            case 3 -> excluirProduto(produtos,scan);
            case 4 -> pesquisarProduto(scan,produtos,produtosFiltrados);
            case 5 -> System.out.println("b");
            case 6 ->{ scan.close();
                System.exit(0);}
            default -> System.out.println("Escolha uma opção válida. ");
        }
    }

    private static void pesquisarProduto(Scanner scan, List<Produto> produtos, List<Produto> produtosFiltrados) {
        System.out.println("Digite o nome ou parte dele: ");
        String resposta = scan.nextLine().toLowerCase();
        produtosFiltrados.clear();
        for (Produto produto: produtos) {
            if (produto.getNome().toLowerCase().contains(resposta)){
                produtosFiltrados.add(produto);
            }
        }
        for (Produto produto: produtosFiltrados) {
            System.out.println(produto.getNome());
        }
    }



    public static void criarProduto(Scanner scan, List<Produto> produtos) {
//        oldFiles.Produto produto = new oldFiles.Produto();
        System.out.println("Digite o nome do oldFiles.Produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite a quantidade do oldFiles.Produto: ");
        String quantidade = scan.nextLine();
        System.out.println("Digite o preco do oldFiles.Produto: ");
        String preco = scan.nextLine();
//        produto.setQuantidade(Integer.parseInt(quantidade));
//        produto.setNome(nome);
//        produto.setPreco(Double.parseDouble(preco));
//        produtos.add(produto);
    }
    public static void editarProduto(List<Produto> produtos, Scanner scan, Produto produto) {
        System.out.println("Você deseja editar qual produto? Se não deseja remover nada digite 0");
        int identificador = 1;
        for (Produto produto1:
                produtos) {
            System.out.println("Opção "+identificador+" - "+produto1.getNome());
            identificador++;
        }
        int respostaEditar = scan.nextInt();
        scan.nextLine();
        if(respostaEditar == 0){
            System.out.println("Volte pro menu e digite a opção correta.");
        }else {
//            System.out.println("Digite o nome do novo oldFiles.Produto: ");
//            String nome = scan.nextLine();
//            System.out.println("Digite a quantidade do novo oldFiles.Produto: ");
//            String quantidade = scan.nextLine();
//            System.out.println("Digite o preco do novo oldFiles.Produto: ");
//            String preco = scan.nextLine();
//            produto.setQuantidade(Integer.parseInt(quantidade));
//            produto.setNome(nome);
//            produto.setPreco(Double.parseDouble(preco));
//            produtos.set(respostaEditar-1,produto);
        }
    }

    public static void excluirProduto(List<Produto> produtos, Scanner scan) {
        System.out.println("Você deseja remover qual produto? Se não deseja remover nada digite 0");
        int id = 1;
        for (Produto produto: produtos) {
            System.out.println("Opção "+id+" - "+produto.getNome());
            id++;
        }
        int respostaRemover = scan.nextInt();
        scan.nextLine();
        if(respostaRemover == 0){
            System.out.println("Volte pro menu e digite a opção correta.");
        }else {
            produtos.remove(respostaRemover - 1);
        }
        System.out.println("Nova lista de Produtos: ");
        for (Produto produto1:
                produtos) {
            System.out.println(produto1);
        }
    }


}
