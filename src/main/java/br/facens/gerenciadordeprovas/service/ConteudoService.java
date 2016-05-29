/**
 * 
 */
package br.facens.gerenciadordeprovas.service;

import java.util.List;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.dao.ConteudoDAO;


/**
 * @author Eduardo
 *
 */
public class ConteudoService {

	ConteudoDAO conteudoDAO = new ConteudoDAO();

	public Conteudo salvar(Conteudo conteudo) {
		conteudo = conteudoDAO.save(conteudo);
		conteudoDAO.closeEntityManager();
		return conteudo;
	}

	public List<Conteudo> getConteudos() {
		List<Conteudo> list = conteudoDAO.getAll(Conteudo.class);
		conteudoDAO.closeEntityManager();
		return list;
	}

	public void alterar(Conteudo conteudo) {
		conteudoDAO.save(conteudo);
		conteudoDAO.closeEntityManager();
	}

	public void remover(Conteudo conteudo) {
		conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getId());
		conteudoDAO.remove(conteudo);
		conteudoDAO.closeEntityManager();
	}

}
