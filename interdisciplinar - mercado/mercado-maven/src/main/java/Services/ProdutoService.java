package Services;

import java.util.List;
import java.util.Scanner;

import DAO.FornecedorDAOimpl;
import DAO.ProdutoDAOimpl;
import model.Fornecedor;
import model.Produto;

public class ProdutoService {

    private ProdutoDAOimpl produtoDAOimpl;
    private FornecedorService fornecedorService;

    public ProdutoService(ProdutoDAOimpl produtoDAOimpl, FornecedorDAOimpl fornecedorDAOimpl) {
        this.produtoDAOimpl = produtoDAOimpl;
        this.fornecedorDAOimpl = fornecedorDAOimpl;
    }

    private FornecedorDAOimpl fornecedorDAOimpl;

    private Scanner sc = new Scanner(System.in);

    public void cadastrarProduto() {
        Produto p = new Produto();
        System.out.println("=== CADASTRO DE PRODUTO ===");
        System.out.print("Nome: ");
        p.setNomeDoProduto(sc.nextLine());

        System.out.print("Código de barras: ");
        String codigo = (sc.nextLine());
        p.setCodigoDeBarras(codigo);

        List<Produto> produtos = produtoDAOimpl.listarTodos();

        if (produtos != null) {
            for (Produto prod : produtos) {
                if (prod.getCodigoDeBarras() != null &&
                    prod.getCodigoDeBarras().equals(codigo)) {

                    System.out.println("Erro: já existe um produto com esse código de barras!");
                    return;
                }
            }
        }

        System.out.print("Preço: ");
        p.setPreco(sc.nextBigDecimal());sc.nextLine(); // limpar buffer
        System.out.print("Quantidade: ");
        p.setQuantidade(sc.nextInt());
        System.out.print("Estoque mínimo: ");
        p.setEstoque_minimo(sc.nextInt());
        sc.nextLine();
        System.out.print("Categoria: ");
        p.setCategoria(sc.nextLine());
        System.out.println("----------------------------------------------------");
        fornecedorDAOimpl.listarTodos();
        System.out.println("----------------------------------------------------");
        System.out.print("Digite o ID do fornecedor: ");
        int idFornecedor = sc.nextInt();sc.nextLine();

        Fornecedor fornecedor = fornecedorDAOimpl.buscarPorId(idFornecedor);

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado!");
            return;
        }

        produtoDAOimpl.cadastrar(p);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("----------------------------------------------------");
    }

    public void baixarEstoque(Produto produto, int quantidade) {
        if (produto.getQuantidade() >= quantidade) {
            produto.reduzirEstoque(quantidade);
        } else {
            System.out.println("Estoque insuficiente!");
        }
    }

    public void reporEstoque(Produto produto, int quantidade) {
        produto.aumentarEstoque(quantidade);
    }

    public void atualizarProduto() {

        System.out.print("Digite o ID do produto: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        Produto p = produtoDAOimpl.buscarPorCodigo(id);

        if (p == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        p.setNomeDoProduto(sc.nextLine());

        System.out.print("Novo preço: ");
        p.setPreco(sc.nextBigDecimal());
        sc.nextLine();

        System.out.print("Nova quantidade: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo código de barras: ");
        p.setCodigoDeBarras(sc.nextLine());

        p.setQuantidade(qtd);

         System.out.print("Deseja alterar o fornecedor? (s/n): ");
            String opcao = sc.nextLine();

             if (opcao.equalsIgnoreCase("s")) {


                System.out.print("Digite o ID do novo fornecedor: ");
                int idFornecedor = sc.nextInt();
                sc.nextLine();

                Fornecedor fornecedor = fornecedorDAOimpl.buscarPorId(idFornecedor);

                if (fornecedor != null) {
                    p.setFornecedor(fornecedor);
                } else {
                    System.out.println("Fornecedor não encontrado! Mantendo o atual.");
                }
            }

        produtoDAOimpl.atualizar(p);

        verificarEstoqueBaixo(p);

        System.out.println("Produto atualizado com sucesso!");
    }

    public void verificarEstoqueBaixo(Produto p) {

        if (p.getQuantidade() <= p.getEstoque_minimo() && p.getQuantidade() > 0) {
            System.out.println("⚠ AVISO: Produto " + p.getNomeDoProduto() + " está com estoque baixo!");
        }
        if (p.getQuantidade() == 0) {
            System.out.println("🚨 ALERTA: Produto " + p.getNomeDoProduto() + " está SEM ESTOQUE!");
        }
    }


    public void listarProdutosEmFalta() {

        List<Produto> produtos = produtoDAOimpl.listarTodos();

        System.out.println("=== PRODUTOS EM FALTA ===");

        for (Produto p : produtos) {
            if (p.getQuantidade() == 0) {
                System.out.println(p.getNomeDoProduto() + " - SEM ESTOQUE");
            }else{
                System.out.println("Não existem produtos sem estoque.");
            }
        }
    }

     public void listarEstoqueBaixo() {

        List<Produto> produtos = produtoDAOimpl.listarTodos();

        System.out.println("=== ESTOQUE BAIXO ===");

        for (Produto p : produtos) {
            if (p.getQuantidade() <= p.getEstoque_minimo() && p.getQuantidade() > 0) {
                System.out.println(p.getNomeDoProduto() +
                        " | Qtd: " + p.getQuantidade() +
                        " | Mínimo: " + p.getEstoque_minimo());
            }else{
                System.out.println("Não existem produtos com estoque baixo");
            }
        }
    }

    public void buscarPorCodigoDeBarras(){
        System.out.print("Digite o código de barras do produto: ");
        String codigoDeBarras = sc.next();

        Produto p = produtoDAOimpl.buscarPorCodigoDeBarras(codigoDeBarras);
        System.out.println("Produto: " + p.getNomeDoProduto());
        System.out.println("Preço: R$ " + p.getPreco());
        System.out.println("Estoque: " + p.getQuantidade());
        System.out.println("---------------------");
    }

    public void listarTodosProdutos() {

        List<Produto> produtos = produtoDAOimpl.listarTodos();

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }

        System.out.println("\n=== LISTA DE PRODUTOS ===");

        for (Produto p : produtos) {
            System.out.println("\nID: " + p.getIdProduto());
            System.out.println("Código de barras: " + p.getCodigoDeBarras());
            System.out.println("Nome: " + p.getNomeDoProduto());
            System.out.println("Preço: R$ " + p.getPreco());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Estoque mínimo: " + p.getEstoque_minimo());
            System.out.println("Categoria: " + p.getCategoria());

            if (p.getFornecedor() != null) {
                System.out.println("Fornecedor: " + p.getFornecedor().getNomeFornecedor());
            } else {
                System.out.println("Fornecedor: não informado");
            }

            System.out.println("------------------------");
        }
    }
}
