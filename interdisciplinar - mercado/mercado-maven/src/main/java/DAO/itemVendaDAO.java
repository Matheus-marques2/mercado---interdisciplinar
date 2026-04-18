package DAO;
import java.util.List;

import model.ItemVenda;

public interface itemVendaDAO {
    // RF09 - adicionar item na venda
    void adicionarItem(ItemVenda itemVenda);

    // listar itens de uma venda
    List<ItemVenda> buscarPorVenda(Integer idVenda);

    // RF10 - exibir preço do produto (via item ou produto)
    double buscarPrecoProduto(Integer idProduto);

    // RF13 - atualizar estoque após venda
    void atualizarEstoque(Integer idProduto, int quantidadeVendida);
}
