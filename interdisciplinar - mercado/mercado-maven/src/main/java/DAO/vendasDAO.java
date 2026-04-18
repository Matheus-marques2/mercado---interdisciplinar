package DAO;

import java.util.List;

import model.Venda;

public interface vendasDAO {
    // RF09 - Registrar venda
    void salvar(Venda venda);

    // buscar venda por ID
    Venda buscarPorId(Integer idVenda);

    // listar vendas
    List<Venda> listarTodas();

    // RF11 - forma de pagamento (pode ser campo na entidade, mas controle aqui se quiser validar)
    void atualizarFormaPagamento(Integer idVenda, String formaPagamento);

}
