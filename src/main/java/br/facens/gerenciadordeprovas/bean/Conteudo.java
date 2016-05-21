/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Conteudo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String topico;
	
	@OneToMany(mappedBy="conteudo")
	private List<Questao> questao;

	@ManyToOne
	private Disciplina disciplina;
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the topico
	 */
	public String getTopico() {
		return topico;
	}
	
	/**
	 * @param topico the topico to set
	 */
	public void setTopico(String topico) {
		this.topico = topico;
	}
	
	/**
	 * @return the disciplina
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	/**
	 * @param disciplina the disciplina to set
	 */
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	/**
	 * @return the questao
	 */
	public List<Questao> getQuestao() {
		return questao;
	}
	
	/**
	 * @param questao the questao to set
	 */
	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + ((topico == null) ? 0 : topico.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Conteudo)) {
			return false;
		}
		Conteudo other = (Conteudo) obj;
		if (disciplina == null) {
			if (other.disciplina != null) {
				return false;
			}
		} else if (!disciplina.equals(other.disciplina)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (questao == null) {
			if (other.questao != null) {
				return false;
			}
		} else if (!questao.equals(other.questao)) {
			return false;
		}
		if (topico == null) {
			if (other.topico != null) {
				return false;
			}
		} else if (!topico.equals(other.topico)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Conteudo [id=" + id + ", topico=" + topico + ", disciplina=" + disciplina + ", questao=" + questao
				+ "]";
	}
	
}