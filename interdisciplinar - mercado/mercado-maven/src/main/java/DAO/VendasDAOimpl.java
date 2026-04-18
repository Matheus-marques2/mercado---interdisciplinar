package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Venda;
import utils.JPAUtils;

public class VendasDAOimpl implements vendasDAO {

    private final EntityManager em;

    public VendasDAOimpl() {
		this.em = JPAUtils.getEntityManager();
	}

    
    @Override
    public void salvar(Venda venda) {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
    }

    
    @Override
    public Venda buscarPorId(Integer idVenda) {
        return em.find(Venda.class, idVenda);
    }

    
    @Override
    public List<Venda> listarTodas() {
        String jpql = "SELECT v FROM Venda v";
        TypedQuery<Venda> query = em.createQuery(jpql, Venda.class);
        return query.getResultList();
    }


    @Override
    public void atualizarFormaPagamento(Integer idVenda, String formaPagamento) {

        em.getTransaction().begin();
        String jpql = "UPDATE Venda v SET v.formaPagamento = :forma WHERE v.idVenda = :id";
        em.createQuery(jpql)
        .setParameter("forma", formaPagamento)
        .setParameter("id", idVenda)
        .executeUpdate();
        em.getTransaction().commit();
       
    }

}    
