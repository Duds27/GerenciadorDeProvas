/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Conteudo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String topico;
	
	@OneToMany(mappedBy="conteudo")
	private List<Questao> questoes;

	@ManyToOne(cascade = CascadeType.ALL)
	private Disciplina disciplina;
	
	/*@ManyToMany(
		mappedBy = "conteudos",
		cascade = CascadeType.ALL
	)*/
	//private List<Prova> provas;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTopico() {
		return topico;
	}
	
	public void setTopico(String topico) {
		this.topico = topico;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public List<Questao> getQuestoes() {
		return questoes;
	}
	
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

/*	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		//result = prime * result + ((provas == null) ? 0 : provas.hashCode());
		result = prime * result + ((questoes == null) ? 0 : questoes.hashCode());
		result = prime * result + ((topico == null) ? 0 : topico.hashCode());
		return result;
	}

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
		/*if (provas == null) {
			if (other.provas != null) {
				return false;
			}
		} else if (!provas.equals(other.provas)) {
			return false;
		}*/
		if (questoes == null) {
			if (other.questoes != null) {
				return false;
			}
		} else if (!questoes.equals(other.questoes)) {
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

	@Override
	public String toString() {
		return "Conteudo [id=" + id + ", topico=" + topico + ", questoes=" + questoes + ", disciplina=" + disciplina;
				//+ ", provas=" + provas + "]";
	}
	
}