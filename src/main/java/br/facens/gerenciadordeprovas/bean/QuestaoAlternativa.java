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
@DiscriminatorValue(value="Alternativa")
public class QuestaoAlternativa extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	//@OneToMany(mappedBy="questaoAlternativa", cascade=CascadeType.ALL)
	//private List<Alternativa> listaAlternativas = new ArrayList<Alternativa>();
	@OneToOne
	@JoinColumn(name="id_alternativa")
	private Alternativa alternativa;
	
	public QuestaoAlternativa() {
		super();
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
		if (!(obj instanceof QuestaoAlternativa)) {
			return false;
		}
		QuestaoAlternativa other = (QuestaoAlternativa) obj;
		if (alternativa == null) {
			if (other.alternativa != null) {
				return false;
			}
		} else if (!alternativa.equals(other.alternativa)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "QuestaoAlternativa [alternativa=" + alternativa + "]";
	}
	
}