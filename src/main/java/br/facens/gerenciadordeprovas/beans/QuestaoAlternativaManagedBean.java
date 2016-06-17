/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.bean.Questao;
import br.facens.gerenciadordeprovas.bean.QuestaoAlternativa;
import br.facens.gerenciadordeprovas.service.ConteudoService;
import br.facens.gerenciadordeprovas.service.DisciplinaService;
import br.facens.gerenciadordeprovas.service.QuestaoService;

/**
 * @author Eduardo
 *
 */
@ManagedBean
@ViewScoped
public class QuestaoAlternativaManagedBean {

	private Questao questao = new QuestaoAlternativa();
	private List<Questao> questoes;
	private QuestaoService serviceQuestao = new QuestaoService();
	
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private DisciplinaService serviceDisciplina = new DisciplinaService();
	
	private Conteudo conteudo = new Conteudo();
	private List<Conteudo> conteudos;
	private ConteudoService serviceConteudo = new ConteudoService();
	
	private String alternativa;
	
	public Questao getQuestao() {
		return questao;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
	
	public String getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}

	public void save() {
		questao.setConteudo(conteudo);
		questao.setDisciplina(disciplina);
		questao = serviceQuestao.salvar(questao);
		
		if (questoes != null)
			questoes.add(questao);
		
		questao = new QuestaoAlternativa();
	}
	
	public void remove(Questao questao) {
		if (questao != null) {
			serviceQuestao.remover(questao);
			questoes.remove(questao);
		}
	}
	
	public void remove(Disciplina disciplina) {
		if (disciplina != null) {
			serviceDisciplina.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}
	
	public void remove(Conteudo conteudo) {
		if (conteudo != null) {
			serviceConteudo.remover(conteudo);
			conteudos.remove(conteudo);
		}
	}
	
	// Retorna a lista de disciplinas
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = serviceDisciplina.getDisciplinas();

		return disciplinas;
	}
	
	public void addAlternativa() {
		((QuestaoAlternativa) this.questao).getListaAlternativas().add(alternativa); 
	}
		
	// Retorna a lista de disciplinas
	public List<Conteudo> getConteudos() {
		if (conteudos == null)
			conteudos = serviceConteudo.getConteudos(disciplina);

		return conteudos;
	}
	
	// Retorna a lista de questões alternativas
	public List<Questao> getQuestoes() {
		if (questoes == null)
			questoes = serviceQuestao.getQuestoesAlternativas();

		return questoes;
	}
	
	// Edição de uma disciplina na tabela
	 public void onRowEdit(RowEditEvent event) {
	 	Questao q = ((Questao) event.getObject());
	 	serviceQuestao.alterar(q);
	 }
	 	
	 public void onSubjectChange() {
	 	if (disciplina != null && !disciplina.getNome().equals(""))
	 		conteudos = serviceConteudo.getConteudos(disciplina);
	 	else
	 		conteudos = new ArrayList<Conteudo>();
	 }
	
}