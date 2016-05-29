/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Questao;
import br.facens.gerenciadordeprovas.dao.QuestaoDAO;

/**
 * @author Eduardo
 *
 */
public class QuestaoService {

	QuestaoDAO questaoDAO = new QuestaoDAO();

	public Questao salvar(Questao questao) {
		questao = questaoDAO.save(questao);
		questaoDAO.closeEntityManager();
		return questao;
	}

	public List<Questao> getQuestoes() {
		List<Questao> list = questaoDAO.getAll(Questao.class);
		questaoDAO.closeEntityManager();
		return list;
	}

	public void alterar(Questao questao) {
		questaoDAO.save(questao);
		questaoDAO.closeEntityManager();
	}

	public void remover(Questao questao) {
		questao = questaoDAO.getById(Questao.class, questao.getId());
		questaoDAO.remove(questao);
		questaoDAO.closeEntityManager();
	}

}
