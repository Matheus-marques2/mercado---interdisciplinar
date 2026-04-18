package DAO;

import java.util.List;

import model.Produto;

public interface ProdutoDAO {
    // RF01 - Cadastrar produto
    void cadastrar(Produto produto);

    // RF02 - Editar produto
    void atualizar(Produto produto);

    // RF03 - Pesquisar por código
    Produto buscarPorCodigo(Integer idProduto);

    // RF03 - Pesquisar por fornecedor
    List<Produto> buscarPorFornecedor(Integer idFornecedor);

    // RF04 - Listar todos produtos
    List<Produto> listarTodos();

    // RF04 - Total de produtos cadastrados
    long contarProdutos();

    // RF07 - Definir estoque mínimo
    void atualizarEstoqueMinimo(Integer idProduto, int estoqueMinimo);

    // RF08 - Aviso de estoque baixo
    List<Produto> listarEstoqueBaixo();
}
