package view;

public class Messages {

    public static String messageMenu = """
                
                +----------------------------------------------------+
                |   *****                Menu                *****   |
                +----------------------------------------------------+
                |   [1] Criar Produto                                |
                |   [2] Editar Produto                               |
                |   [3] Remover Produto                              |
                |   [4] Listar Produto                               |
                |   [5] Pesquisar Produto                            |
                |   [6] Venda de Produtos                            |
                |   [0] Sair do Programa                             |
                +----------------------------------------------------+
                """;

    public static String messageListProduct = """
                
                +----------------------------------------------------+
                |   *****        Lista de Produtos           *****   |
                +----------------------------------------------------+
                | Índice |  Nome do Produto  | Quantidade |  Valor   |               
                """;

    public static String messageClosedCart = """
                
                +----------------------------------------------------+
                |   *****  Fechamento - Carrinho de Compras  *****   |
                +----------------------------------------------------+
                | Índice |  Nome do Produto  | Quantidade |  Valor   |               
                """;

    public static String messageFilteredProducts = """
                
                +----------------------------------------------------+
                |   *****    Lista de Produtos Filtrados     *****   |
                +----------------------------------------------------+
                | Índice |  Nome do Produto  | Quantidade |  Valor   |               
                """;

    public static String messageTableProduct = "|   %02d   | %-17s |     %-6s |  %6.2f  |\n";

    public static String messageTotalValueCart = """
                +----------------------------------------------------+
                | Valor total do carrinho: R$ %20.2f   |
                +----------------------------------------------------+
                """;

    public static String messageEndTable = "+----------------------------------------------------+";

    public static String messageInsufficientStock = """
                Produto em estoque insuficiente.
                Temos quantidade disponível %s a venda.
                Deseja comprar uma quantidade menor ou outro item? 's' para sim e 'n' para não 
                """;

    public static String messageInputLine = "> ";

    public static String messageProductExists = "";

    public static String messageQuestionEditProduct = "";

    public static String messageQuestionRemoveProduct = "";

    public static String messageNewPurchase = "";

    public static String messageReturnMenu = "";

    public static String messageOptionInvalid = "";

}