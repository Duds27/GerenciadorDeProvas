/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Professor;
import br.facens.gerenciadordeprovas.dao.ProfessorDAO;

/**
 * @author Eduardo
 *
 */
public class ProfessorService {

	ProfessorDAO professorDAO = new ProfessorDAO();

	public Professor salvar(Professor professor) {
		professor = professorDAO.save(professor);
		professorDAO.closeEntityManager();
		return professor;
	}

	public List<Professor> getProfessores() {
		List<Professor> list = professorDAO.getAll(Professor.class);
		professorDAO.closeEntityManager();
		return list;
	}

	public void alterar(Professor professor) {
		professorDAO.save(professor);
		professorDAO.closeEntityManager();
	}

	public void remover(Professor professor) {
		professor = professorDAO.getById(Professor.class, professor.getId());
		professorDAO.remove(professor);
		professorDAO.closeEntityManager();
	}

}
