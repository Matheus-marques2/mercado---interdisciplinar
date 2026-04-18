package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Fornecedor;
import utils.JPAUtils;

public class FornecedorDAOimpl implements fornecedorDAO {

    private final EntityManager em;

    public FornecedorDAOimpl() {
		this.em = JPAUtils.getEntityManager();
	}

    @Override
    public void cadastrar(Fornecedor fornecedor) {  
        em.getTransaction().begin();
        em.persist(fornecedor);
        em.getTransaction().commit();
       
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        em.getTransaction().begin();
        em.merge(fornecedor);
        em.getTransaction().commit();
    }

    
    @Override
    public Fornecedor buscarPorId(Integer idFornecedor) {
        return em.find(Fornecedor.class, idFornecedor);
    }

    
    @Override
    public List<Fornecedor> listarTodos() {
        String jpql = "SELECT f FROM Fornecedor f";
        TypedQuery<Fornecedor> query = em.createQuery(jpql, Fornecedor.class);
        return query.getResultList();
    }

    
    @Override
    public void deletar(Integer idFornecedor) {
        em.getTransaction().begin(); 
        Fornecedor fornecedor = em.find(Fornecedor.class, idFornecedor); 
        if (fornecedor != null) {
            em.remove(fornecedor);
        } 
        em.getTransaction().commit();
    }
    
}
