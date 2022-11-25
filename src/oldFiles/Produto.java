package oldFiles;

public class Produto{

    private String nome;
//    private int quantidade;
    private double preco;

    public Produto(String s, double parseDouble) {
        nome = s;
        preco = parseDouble;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public int getQuantidade() {
//        return quantidade;
//    }

//    public void setQuantidade(int quantidade) {
//        this.quantidade = quantidade;
//    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String toString(){
        return ""
                + nome
                +", "
                +preco;
    }
}


