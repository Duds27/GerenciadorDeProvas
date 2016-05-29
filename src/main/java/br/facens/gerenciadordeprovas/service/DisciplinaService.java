/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.dao.DisciplinaDAO;

/**
 * @author Eduardo
 *
 */
public class DisciplinaService {

	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Disciplina salvar(Disciplina disciplina) {
		disciplina = disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();
		return disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		List<Disciplina> list = disciplinaDAO.getAll(Disciplina.class);
		disciplinaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Disciplina disciplina) {
		disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();
	}

	public void remover(Disciplina disciplina) {
		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getId());
		disciplinaDAO.remove(disciplina);
		disciplinaDAO.closeEntityManager();
	}

}
