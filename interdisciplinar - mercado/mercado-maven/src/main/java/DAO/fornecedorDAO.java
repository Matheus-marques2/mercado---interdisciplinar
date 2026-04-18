package DAO;
import java.util.List;

import model.Fornecedor;

public interface fornecedorDAO {
    // RF05 - Cadastrar fornecedor
    void cadastrar(Fornecedor fornecedor);

    // RF06 - Editar fornecedor
    void atualizar(Fornecedor fornecedor);

    // buscar por ID
    Fornecedor buscarPorId(Integer idFornecedor);

    // listar todos
    List<Fornecedor> listarTodos();

    void deletar(Integer idFornecedor);
}
