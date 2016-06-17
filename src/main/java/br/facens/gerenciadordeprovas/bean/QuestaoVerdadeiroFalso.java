/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author eencarnacao
 *
 */
@Entity
@DiscriminatorValue(value="VerdadeiroOuFalso")
public class QuestaoVerdadeiroFalso extends Questao {

	private static final long serialVersionUID = 1L;

	private TrueOrFalse enumTrueOrFalse;

	public QuestaoVerdadeiroFalso() {
		super();
	}

	public TrueOrFalse getEnumTrueOrFalse() {
		return enumTrueOrFalse;
	}

	public void setEnumTrueOrFalse(TrueOrFalse enumTrueOrFalse) {
		this.enumTrueOrFalse = enumTrueOrFalse;
	}
	
}