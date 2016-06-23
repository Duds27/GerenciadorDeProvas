/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.service.DisciplinaService;

/**
 * @author Eduardo
 *
 */
@ManagedBean(eager=true)
@ViewScoped
public class DisciplinaManagedBean {

	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private DisciplinaService service = new DisciplinaService();

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void save() {
		disciplina = service.salvar(disciplina);

		if (disciplina != null)
			disciplinas.add(disciplina);

		disciplina = new Disciplina();
	}
	
	public void remove(Disciplina disciplina) {
		if (disciplina != null) {
			service.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}

	// Retorna a lista de alunos para a tabela
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = service.getDisciplinas();

		return disciplinas;
	}
	   
    // Edição de uma disciplina na tabela
 	public void onRowEdit(RowEditEvent event) {
 		Disciplina d = ((Disciplina) event.getObject());
 		service.alterar(d);
 	}

}
