/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author eencarnacao
 *
 */
@Entity
@DiscriminatorValue(value="Alternativa")
public class QuestaoAlternativa extends Questao {

	private static final long serialVersionUID = 1L;

	private List<String> listaAlternativas;

	public QuestaoAlternativa() {
		super();
		this.listaAlternativas = new ArrayList<String>();
	}

	public List<String> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<String> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
	
}