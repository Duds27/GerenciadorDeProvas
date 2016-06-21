/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.model.ExportarProvaPDF;
/**
 * @author eduardofesilva
 *
 */
public class ProvaManagedBean {
	private Conteudo conteudo = new Conteudo();
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private List<Conteudo> conteudos;
	private Prova prova = new Prova();
	private ExportarProvaPDF pdf = new ExportarProvaPDF(prova,"","");
	
	public ExportarProvaPDF getPdf() {
		return pdf;
	}
	public void setPdf(ExportarProvaPDF pdf) {
		this.pdf = pdf;
	}
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
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<Conteudo> getConteudos() {
		return conteudos;
	}
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
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
	
}
