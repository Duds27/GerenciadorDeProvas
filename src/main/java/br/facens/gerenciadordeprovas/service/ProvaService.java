/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.dao.ProvaDAO;

/**
 * @author Eduardo
 *
 */
public class ProvaService {

	ProvaDAO provaDAO = new ProvaDAO();

	public Prova salvar(Prova prova) {
		prova = provaDAO.save(prova);
		provaDAO.closeEntityManager();
		return prova;
	}

	public List<Prova> getProvas() {
		List<Prova> list = provaDAO.getAll(Prova.class);
		provaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Prova prova) {
		provaDAO.save(prova);
		provaDAO.closeEntityManager();
	}

	public void remover(Prova prova) {
		prova = provaDAO.getById(Prova.class, prova.getId());
		provaDAO.remove(prova);
		provaDAO.closeEntityManager();
	}

}
