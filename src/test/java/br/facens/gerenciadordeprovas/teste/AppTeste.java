/**
 * 
 */
package br.facens.gerenciadordeprovas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.facens.gerenciadordeprovas.bean.Conteudo;


/**
 * @author Eduardo
 *
 */
public class AppTeste {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("GerenciadorDeProvas");
		EntityManager        em      = factory.createEntityManager();
		
	    
		//Consulta (mais flexivel)	
		//Acrescente a consulta na classe Livro02
		//Query q = em.createQuery("Select l From Livro02 l");
		//Query q = em.createQuery("Select l From Livro02 l Where l.titulo = 'Book'");
		Query q = em.createQuery("Select l From " + Conteudo.class.getSimpleName() + " l Where l.disciplina.id = " + Integer.parseInt("2"));
		List <Conteudo> list = q.getResultList();
		//return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o").getResultList();
		for(Conteudo l: list)
		{
		      System.out.println(l.getDisciplina().getNome());	
		}
			
		
		em.close();
		factory.close();

	}
	
}
