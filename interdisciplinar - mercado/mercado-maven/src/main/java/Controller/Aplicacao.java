package Controller;

import java.util.Scanner;

import DAO.FornecedorDAOimpl;
import DAO.ProdutoDAOimpl;
import DAO.VendasDAOimpl;
import Services.FornecedorService;
import Services.ProdutoService;
import Services.VendaService;
import model.Venda;

public class Aplicacao {

    private static final Scanner scan = new Scanner(System.in);

    private static final ProdutoService produtoService = new ProdutoService(new ProdutoDAOimpl(), new FornecedorDAOimpl());
    private static final FornecedorService fornecedorService = new FornecedorService(new FornecedorDAOimpl());
    private static final VendaService vendaService = new VendaService(new ProdutoDAOimpl(), new VendasDAOimpl());

    // ===================== MENU PRINCIPAL =====================
    public static void iniciarMenu() {

        while (true) {

            menu.exibirMenuPrincipal();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {

                case 1 -> iniciarMenuProdutos();
                case 2 -> iniciarMenuFornecedores();
                case 3 -> iniciarMenuVendas();

                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                }

                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // ===================== MENU PRODUTOS =====================
    public static void iniciarMenuProdutos() {

        while (true) {

            menu.exibirMenuProdutos();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {

                case 1 -> produtoService.cadastrarProduto();
                case 2 -> produtoService.atualizarProduto();
                case 3 -> produtoService.buscarPorCodigoDeBarras();
                case 4 -> produtoService.listarProdutosEmFalta();
                case 5 -> produtoService.listarEstoqueBaixo();
                case 6 -> produtoService.listarTodosProdutos();

                case 7 -> {
                    return; // volta ao menu principal
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ===================== MENU FORNECEDORES =====================
    public static void iniciarMenuFornecedores() {

        while (true) {

            menu.exibirMenuFornecedores();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {

                case 1 -> fornecedorService.cadastrarFornecedor();
                case 2 -> fornecedorService.atualizarFornecedor();
                case 3 -> fornecedorService.listarFornecedorPorId();
                case 4 -> fornecedorService.listarFornecedores();
                case 5 -> fornecedorService.listarProdutosDoFornecedor();
                case 6 -> fornecedorService.deletarFornecedor();

                case 7 -> {
                    return; // volta ao menu principal
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ===================== MENU VENDAS (CAIXA) =====================
    public static void iniciarMenuVendas() {

        Venda vendaAtual = null;
        
        while (true) {

            menu.exibirMenuVendas();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {

                
                case 1 -> {
                    vendaAtual = vendaService.iniciarVenda();
                    System.out.println("Venda iniciada com sucesso!");
                    if (vendaAtual == null){
                        System.out.println("Nenhuma venda encontrada, inicie uma venda primeiro.");
                    }else{
                        vendaService.adicionarItensNaVenda(vendaAtual);
                    } 
                }

                case 2 -> vendaService.consultarProduto();
                case 3 -> {
                    if(vendaAtual == null){
                        System.out.println("nenhuma venda em andamento.");
                    }else{
                        vendaService.finalizarVenda(vendaAtual);
                    }
                }

                case 4 -> vendaService.listarVendas();
                case 5 -> vendaService.buscarVendaPorId();

                case 6 -> {
                    return; // volta ao menu principal
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
