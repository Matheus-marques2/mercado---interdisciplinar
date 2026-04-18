package Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import DAO.ProdutoDAOimpl;
import DAO.VendasDAOimpl;
import model.ItemVenda;
import model.Produto;
import model.Venda;

public class VendaService {
    
    Scanner sc = new Scanner(System.in);
    private ProdutoDAOimpl produtoDAOimpl;

    private VendasDAOimpl vendasDAOimpl;

    public VendaService(ProdutoDAOimpl produtoDAOimpl, VendasDAOimpl vendasDAOimpl) {
        this.produtoDAOimpl = produtoDAOimpl;
        this.vendasDAOimpl = vendasDAOimpl;
    }

    public Venda iniciarVenda() {
        Venda venda = new Venda();
        return venda;
    }

    public void adicionarItensNaVenda(Venda venda) {

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\n=== ADICIONAR ITEM ===");
            System.out.print("Digite o código do produto: ");
            String codigo = sc.nextLine();
            Produto produto = produtoDAOimpl.buscarPorCodigoDeBarras(codigo);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.println("Produto: " + produto.getNomeDoProduto());
            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();sc.nextLine();

            if (produto.getQuantidade() < qtd) {
                System.out.println("Estoque insuficiente!");
                continue;
            }

            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(qtd);
            item.setprecoUnitario(produto.getPreco());

            venda.adicionarItem(item);
            produto.reduzirEstoque(qtd);
            System.out.println("Item adicionado com sucesso!");
            System.out.print("\nDeseja adicionar outro item? (s/n): ");
            continuar = sc.nextLine();
        }

        System.out.println("\nFinalizando adição de itens...");
        vendasDAOimpl.salvar(venda);
    }



    public void calcularTotal(Venda venda) {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : venda.getItens()) {
            total = total.add(item.getSubtotal());
        }
        venda.setValorTotal(total);
    }

    
    public void finalizarVenda(Venda venda) {
        calcularTotal(venda);

        System.out.println("TOTAL: " + venda.getValorTotal());

        System.out.print("Forma de pagamento (dinheiro/cartão débito/cartão crédito/PIX): ");
        String formaPagamento = sc.nextLine();
        venda.setFormaPagamento(formaPagamento);

        if (formaPagamento.equalsIgnoreCase("dinheiro")) {
            System.out.print("Valor pago: ");
            BigDecimal pago = sc.nextBigDecimal();
            sc.nextLine();
            venda.setValorPago(pago);

            if (pago.compareTo(venda.getValorTotal()) > 0) {
                BigDecimal troco = pago.subtract(venda.getValorTotal());
                venda.setTroco(troco);
                System.out.println("Pagamento via dinheiro confirmado. Seu troco é: " + troco);
            } else {
                System.out.println("Valor pago insuficiente!");
                venda.setTroco(BigDecimal.ZERO);
            }

        } else {
            venda.setValorPago(venda.getValorTotal());
            venda.setTroco(BigDecimal.ZERO);
            System.out.println("Pagamento via " + formaPagamento + " confirmado.");
        }
    }

    public void consultarProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o código de barras: ");
        String codigo = sc.nextLine();
        Produto produto = produtoDAOimpl.buscarPorCodigoDeBarras(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }
        System.out.println("\n=== PRODUTO ===");
        System.out.println("Nome: " + produto.getNomeDoProduto());
        System.out.println("Preço: R$ " + produto.getPreco());
        System.out.println("Estoque: " + produto.getQuantidade());
    }
    
   public void buscarVendaPorId() {

        System.out.print("Digite o ID da venda: ");
        int id = sc.nextInt();
        sc.nextLine();

        Venda venda = vendasDAOimpl.buscarPorId(id);

        if (venda == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        System.out.println("\n=== VENDA ENCONTRADA ===");
        System.out.println("ID: " + venda.getIdVenda());
        System.out.println("Data: " + venda.getDataVenda());
        System.out.println("Forma de pagamento: " + venda.getFormaPagamento());
        System.out.println("Total: R$ " + venda.getValorTotal());

        System.out.println("\n--- ITENS ---");

        for (ItemVenda item : venda.getItens()) {
            System.out.println("Produto: " + item.getProduto().getNomeDoProduto());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Preço Unitário: R$ " + item.getprecoUnitario());
            System.out.println("Subtotal: R$ " + item.getSubtotal());
            System.out.println("------------------------");
        }
    }


    public void listarVendas() {

        List<Venda> vendas = vendasDAOimpl.listarTodas();

        if (vendas == null || vendas.isEmpty()) {
            System.out.println("Nenhuma venda cadastrada!");
            return;
        }

        System.out.println("\n=== LISTA DE VENDAS ===");

        for (Venda venda : vendas) {

            System.out.println("\nID: " + venda.getIdVenda());
            System.out.println("Data: " + venda.getDataVenda());
            System.out.println("Forma de pagamento: " + venda.getFormaPagamento());
            System.out.println("Total: R$ " + venda.getValorTotal());

            System.out.println("--- ITENS ---");

            for (ItemVenda item : venda.getItens()) {
                System.out.println("Produto: " + item.getProduto().getNomeDoProduto());
                System.out.println("Qtd: " + item.getQuantidade());
                System.out.println("Subtotal: R$ " + item.getSubtotal());
            }

            System.out.println("------------------------");
        }
    }
}