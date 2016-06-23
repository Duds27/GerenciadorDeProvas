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
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.bean.Questao;


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
			
		q = em.createQuery("Select l From " + Questao.class.getSimpleName() + " l Where l.conteudo.id = " + Integer.parseInt("7"));
		List<Questao> lista = q.getResultList();
		for(Questao l: lista)
		{
		      System.out.println("Disciplina: " + l.getConteudo().getDisciplina().getNome() + " Conteudo: " + l.getConteudo().getTopico());
		}
		q = em.createQuery("Select l From " + Questao.class.getSimpleName() +" l Inner Join " + Conteudo.class.getSimpleName() +  " c On c.disciplina.id = l.disciplina.id Where l.disciplina.id = " + Integer.parseInt("4"));
		list = q.getResultList();
		for (Conteudo c : list) {
			System.out.println("Conteudo: " + c.getTopico() + " Disciplina: " + c.getDisciplina().getNome());
		}
		
		
		
		em.close();
		factory.close();

	}
	
}
