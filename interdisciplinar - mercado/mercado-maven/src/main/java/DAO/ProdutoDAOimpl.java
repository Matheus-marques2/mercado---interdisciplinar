package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Produto;
import utils.JPAUtils;

public class ProdutoDAOimpl implements ProdutoDAO{

    private final EntityManager em;

    public ProdutoDAOimpl() {
		this.em = JPAUtils.getEntityManager();
	}

    @Override
    public void cadastrar(Produto produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    @Override
    public void atualizar(Produto produto) {
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    @Override
    public Produto buscarPorCodigo(Integer idProduto) {
       return em.find(Produto.class, idProduto);
    }

    public Produto buscarPorCodigoDeBarras(String codigoDeBarras) {
       String jpql = "SELECT p FROM Produto p WHERE p.codigoDeBarras = :codigo";
       TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
       query.setParameter("codigo", codigoDeBarras);
       return query.getSingleResult();
    }

   @Override
    public List<Produto> buscarPorFornecedor(Integer idFornecedor) {
        String jpql = "SELECT p FROM Produto p WHERE p.fornecedor.id = :idFornecedor";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("idFornecedor", idFornecedor);
        return query.getResultList();
    }

    @Override
    public List<Produto> listarTodos() {
        String jpql = "SELECT p FROM Produto p";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        return query.getResultList();
    }

    @Override
    public long contarProdutos() {
        String jpql = "SELECT COUNT(p) FROM Produto p";
        return em.createQuery(jpql, Long.class).getSingleResult();
    }

    @Override
    public void atualizarEstoqueMinimo(Integer idProduto, int estoqueMinimo) {
        em.getTransaction().begin();
        String jpql = "UPDATE Produto p SET p.estoqueMinimo = :estoqueMinimo WHERE p.id = :idProduto";
        em.createQuery(jpql)
        .setParameter("estoqueMinimo", estoqueMinimo)
        .setParameter("idProduto", idProduto)
        .executeUpdate();
        em.getTransaction().commit(); 
    }

    @Override
    public List<Produto> listarEstoqueBaixo() {
        String jpql = "SELECT p FROM Produto p WHERE p.quantidade < p.estoqueMinimo";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        return query.getResultList();
    }
    
}
