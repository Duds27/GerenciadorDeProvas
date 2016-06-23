package br.facens.gerenciadordeprovas.dao.generics;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.bean.Questao;

public abstract class DAOImpl<T, I extends Serializable, C,D extends Questao> {

	private static EntityManagerFactory emf;
	private EntityManager em;

	public DAOImpl() {

		emf = Persistence.createEntityManagerFactory("GerenciadorDeProvas");

	}

	public T save(T entity) {

		T saved = null;

		getEntityManager().getTransaction().begin();
		saved = getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();

		return saved;
	}

	public void remove(T entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();

	}

	public T getById(Class<T> classe, I pk) {

		try {
			return getEntityManager().find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> classe) {

		return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<C> getAllAlternativa(Class<C> classe) {

		return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<D> getAllVerdadeiroFalso(Class<D> classe) {

		return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <K> List<T> getAll(Class<T> classe, K objeto) {
		
		return getEntityManager().createQuery("select c from " + classe.getSimpleName() + " c where c.disciplina.id = " + ((Disciplina) objeto).getId()).getResultList();
		//"Select l From " + Conteudo.class.getSimpleName() + " l Where l.disciplina.id = 4")
	}
	
	@SuppressWarnings("unchecked")
	public <K> List<T> getAllQuestoesConteudos(Class<T> classe, K objeto) {
		
		return getEntityManager().createQuery("select c from " + classe.getSimpleName() + " c where c.conteudo.id = " + ((Conteudo) objeto).getId()).getResultList();
		//"Select l From " + Conteudo.class.getSimpleName() + " l Where l.disciplina.id = 4")
	}
	
	@SuppressWarnings("unchecked")
	public <O,K> List<O> getAllQuestoesDisciplinas(Class<O> classe, K objeto) {
		
		return getEntityManager().createQuery("select c from " + classe.getSimpleName() + " c where c.disciplina.id = " + ((Disciplina) objeto).getId()).getResultList();
		//"Select l From " + Conteudo.class.getSimpleName() + " l Where l.disciplina.id = 4")
	}
	

	public EntityManager getEntityManager() {

		if (em == null)
			em = emf.createEntityManager();

		return em;
	}

	public void closeEntityManager() {

		if (em != null)
			em.close();

		em = null;
	}

}