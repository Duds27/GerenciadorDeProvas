/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.service.ConteudoService;
import br.facens.gerenciadordeprovas.service.DisciplinaService;

/**
 * @author Eduardo
 *
 */
@ManagedBean(eager=true)
@ViewScoped
public class ConteudoManagedBean {

	private Conteudo conteudo = new Conteudo();
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private List<Conteudo> conteudos;
	private ConteudoService serviceConteudo = new ConteudoService();
	private DisciplinaService serviceDisciplina = new DisciplinaService();


	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void save() {
		disciplina.addConteudo(conteudo);
		conteudo.setDisciplina(disciplina);

		conteudo = serviceConteudo.salvar(conteudo);

		if (conteudos != null)
			conteudos.add(conteudo);

		conteudo = new Conteudo();
		disciplina = null;
	}
	
	public void remove(Conteudo conteudo) {
		if (conteudo != null) {
			serviceConteudo.remover(conteudo);
			conteudos.remove(conteudo);
		}
	}

	// Retorna a lista de conteudos para a tabela
	public List<Conteudo> getConteudos() {
		if (conteudos == null)
			conteudos = serviceConteudo.getConteudos();

		return conteudos;
	}
	
	// Retorna a lista de conteudos para a tabela a partir de uma disciplina
	public List<Conteudo> getConteudos(Disciplina disciplina) {
		if (conteudos == null)
			conteudos = serviceConteudo.getConteudos(disciplina);

		return conteudos;
	}
	
	// Retorna a lista de disciplinas
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = serviceDisciplina.getDisciplinas();

		return disciplinas;
	}
	   
    // Edição de uma disciplina na tabela
 	public void onRowEdit(RowEditEvent event) {
 		Conteudo c = ((Conteudo) event.getObject());
 		serviceConteudo.alterar(c);
 	}
	
}
