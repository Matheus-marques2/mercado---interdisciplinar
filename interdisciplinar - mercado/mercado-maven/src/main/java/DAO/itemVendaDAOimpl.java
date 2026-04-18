package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.ItemVenda;
import model.Produto;
import utils.JPAUtils;

public class itemVendaDAOimpl implements itemVendaDAO {

    private final EntityManager em;

    public itemVendaDAOimpl() {
		this.em = JPAUtils.getEntityManager();
	}
    
    @Override
    public void adicionarItem(ItemVenda itemVenda) {
        em.getTransaction().begin();
        em.persist(itemVenda);
        em.getTransaction().commit(); 
    }

    @Override
    public List<ItemVenda> buscarPorVenda(Integer idVenda) {
        String jpql = "SELECT i FROM ItemVenda i WHERE i.venda.idVenda = :idVenda";

        TypedQuery<ItemVenda> query = em.createQuery(jpql, ItemVenda.class);
        query.setParameter("idVenda", idVenda);

        return query.getResultList();
    }
    
    @Override
    public double buscarPrecoProduto(Integer idProduto) {
        Produto produto = em.find(Produto.class, idProduto);
        if (produto == null || produto.getPreco() == null) {
            return 0;
        }
        return produto.getPreco().doubleValue();
    }

    
    @Override
    public void atualizarEstoque(Integer idProduto, int quantidadeVendida) {
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, idProduto);
        if (produto != null) {
            produto.reduzirEstoque(quantidadeVendida);
            em.merge(produto);
            }
            em.getTransaction().commit();
    }
}
