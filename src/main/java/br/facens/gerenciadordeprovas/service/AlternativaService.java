/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Alternativa;
import br.facens.gerenciadordeprovas.bean.QuestaoAlternativa;
import br.facens.gerenciadordeprovas.dao.AlternativaDAO;

/**
 * @author Eduardo
 *
 */
public class AlternativaService {

	AlternativaDAO alternativaDAO = new AlternativaDAO();

	public Alternativa salvar(Alternativa alternativa) {
		alternativa = alternativaDAO.save(alternativa);
		alternativaDAO.closeEntityManager();
		return alternativa;
	}

	public List<Alternativa> getAlternativas() {
		List<Alternativa> list = alternativaDAO.getAll(Alternativa.class);
		alternativaDAO.closeEntityManager();
		return list;
	}
	
	public List<Alternativa> getAlternativas(QuestaoAlternativa questaoAlternativa) {
		List<Alternativa> list = alternativaDAO.getAll(Alternativa.class, questaoAlternativa);
		alternativaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Alternativa alternativa) {
		alternativaDAO.save(alternativa);
		alternativaDAO.closeEntityManager();
	}

	public void remover(Alternativa alternativa) {
		alternativa = alternativaDAO.getById(Alternativa.class, alternativa.getId());
		alternativaDAO.remove(alternativa);
		alternativaDAO.closeEntityManager();
	}
	
}