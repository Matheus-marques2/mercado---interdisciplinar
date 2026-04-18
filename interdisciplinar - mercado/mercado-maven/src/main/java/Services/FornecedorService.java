package Services;

import java.util.List;
import java.util.Scanner;

import DAO.FornecedorDAOimpl;
import model.Fornecedor;
import model.Produto;

public class FornecedorService {

    private FornecedorDAOimpl fornecedorDAOimpl;
    private Scanner sc = new Scanner(System.in);

    public FornecedorService(FornecedorDAOimpl fornecedorDAOimpl) {
        this.fornecedorDAOimpl = fornecedorDAOimpl;
    }

    
    public void cadastrarFornecedor() {

        Fornecedor f = new Fornecedor();

        System.out.println("=== CADASTRAR FORNECEDOR ===");
        System.out.print("Nome: ");
        f.setNome_fornecedor(sc.nextLine());
        System.out.print("CNPJ: ");
        f.setCnpj(sc.nextLine());
        System.out.print("Telefone: ");
        f.setTelefone(sc.nextLine());
        System.out.print("Email: ");
        f.setEmail(sc.nextLine());
        System.out.print("Endereço: ");
        f.setEndereco(sc.nextLine());
        System.out.print("Inscrição Estadual: ");
        f.setInscricao_estadual(sc.nextLine());
        System.out.print("Complemento: ");
        f.setComplemento(sc.nextLine());
        fornecedorDAOimpl.cadastrar(f);

        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    
    public void atualizarFornecedor() {

        System.out.print("Digite o ID do fornecedor: ");
        int id = sc.nextInt();sc.nextLine();

        Fornecedor f = fornecedorDAOimpl.buscarPorId(id);

        if (f == null) {
            System.out.println("Fornecedor não encontrado!");
            return;
        }

        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Novo email: ");
        String email = sc.nextLine();

        f.atualizarContato(telefone, email);

        fornecedorDAOimpl.atualizar(f);

        System.out.println("Fornecedor atualizado!");
    }

  
    public void deletarFornecedor() {

        System.out.print("Digite o ID do fornecedor: ");
        int id = sc.nextInt(); sc.nextLine();

        fornecedorDAOimpl.deletar(id);

        System.out.println("Fornecedor removido!");
    }

    public void listarFornecedores() {

        List<Fornecedor> lista = fornecedorDAOimpl.listarTodos();
        System.out.println("=== FORNECEDORES ===");

        for (Fornecedor f : lista) {
            System.out.println("ID: " + f.getIdFornecedor());
            System.out.println("Nome: " + f.getNomeFornecedor());
            System.out.println("CNPJ: " + f.getCnpj());
            System.out.println("---------------------");
        }
    }

    public void listarProdutosDoFornecedor() {

        System.out.print("Digite o ID do fornecedor: ");
        int id = sc.nextInt();
        sc.nextLine();

        Fornecedor f = fornecedorDAOimpl.buscarPorId(id);

        if (f == null) {
            System.out.println("Fornecedor não encontrado!");
            return;
        }

        System.out.println("=== PRODUTOS DO FORNECEDOR: " + f.getNomeFornecedor() + " ===");

        List<Produto> produtos = f.getProdutos();

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            System.out.println("Produto: " + p.getNomeDoProduto());
            System.out.println("Preço: R$ " + p.getPreco());
            System.out.println("Estoque: " + p.getQuantidade());
            System.out.println("---------------------");
        }
    }

    public void listarFornecedorPorId(){
        System.out.print("Digite o id do Fornecedor: ");
        Integer idFornecedor = sc.nextInt();sc.next();

        Fornecedor f = fornecedorDAOimpl.buscarPorId(idFornecedor);
        System.out.println("Fornecedor: " + f.getNomeFornecedor());
        System.out.println("cnpj: R$ " + f.getCnpj());
        System.out.println("endereço: " + f.getEndereco());
         System.out.println("email: R$ " + f.getEmail());
        System.out.println("telefone: " + f.getTelefone());
        System.out.println("---------------------");
    }
}