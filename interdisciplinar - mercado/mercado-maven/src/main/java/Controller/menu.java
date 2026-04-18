package Controller;

public class menu {
    private static final String MENU_PRINCIPAL = """
            Bem-vindo ao Sistema de Mercado
            1. Produtos
            2. Fornecedores
            3. Caixa (Vendas)
            4. Encerrar
            """;

    private static final String MENU_PRODUTOS = """
            1. Cadastrar Produto
            2. Atualizar Produto
            3. Buscar Produto por Código de Barras
            4. Produtos em Falta
            5. Produtos com Estoque Baixo
            6. listar todos
            7. Voltar
            """;

    private static final String MENU_FORNECEDORES = """
            1. Cadastrar Fornecedor
            2. Atualizar Fornecedor
            3. Localizar Fornecedor por ID
            4. Listar Todos os Fornecedores
            5. Listar Produtos por Fornecedor
            6. Deletar Fornecedor
            7. Voltar
            """;

    private static final String MENU_VENDAS = """
            1. Adicionar Itens na Venda
            2. Consultar Produto (Preço)
            3. Finalizar Venda
            4. Listar Vendas
            5. Buscar Venda por ID
            6. Voltar
            """;

    // 🔹 Menu principal
    public static void exibirMenuPrincipal() {
        System.out.println(MENU_PRINCIPAL);
    }

    // 🔹 Menu de produtos
    public static void exibirMenuProdutos() {
        System.out.println(MENU_PRODUTOS);
    }

    // 🔹 Menu de fornecedores
    public static void exibirMenuFornecedores() {
        System.out.println(MENU_FORNECEDORES);
    }

    // 🔹 Menu de vendas (caixa)
    public static void exibirMenuVendas() {
        System.out.println(MENU_VENDAS);
    }
}

