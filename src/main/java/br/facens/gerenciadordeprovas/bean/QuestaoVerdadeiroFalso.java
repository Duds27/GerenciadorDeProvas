/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author eencarnacao
 *
 */
@Entity
@DiscriminatorValue(value="VerdadeiroOuFalso")
public class QuestaoVerdadeiroFalso extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	private TrueOrFalse enumTrueOrFalse;
	
	@OneToOne
	@JoinColumn(name="id_verdadefalso")
	private Alternativa alternativa;
	

	public QuestaoVerdadeiroFalso() {
		super();
	}

	public TrueOrFalse getEnumTrueOrFalse() {
		return enumTrueOrFalse;
	}

	public void setEnumTrueOrFalse(TrueOrFalse enumTrueOrFalse) {
		this.enumTrueOrFalse = enumTrueOrFalse;
	}
	
	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alternativa == null) ? 0 : alternativa.hashCode());
		result = prime * result + ((enumTrueOrFalse == null) ? 0 : enumTrueOrFalse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof QuestaoVerdadeiroFalso)) {
			return false;
		}
		QuestaoVerdadeiroFalso other = (QuestaoVerdadeiroFalso) obj;
		if (alternativa == null) {
			if (other.alternativa != null) {
				return false;
			}
		} else if (!alternativa.equals(other.alternativa)) {
			return false;
		}
		if (enumTrueOrFalse != other.enumTrueOrFalse) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "QuestaoVerdadeiroFalso [enumTrueOrFalse=" + enumTrueOrFalse + ", alternativa=" + alternativa + "]";
	}
	
}