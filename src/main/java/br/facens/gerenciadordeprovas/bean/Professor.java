/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Professor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	private String nome;
	
	@OneToMany(
		mappedBy = "prova",
		cascade = CascadeType.ALL
	)
	private List<Prova> provas;
	
	@OneToMany(
		mappedBy = "professor",
		cascade = CascadeType.ALL
	)
	private List<Disciplina> disciplinas;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((provas == null) ? 0 : provas.hashCode());
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
		if (!(obj instanceof Professor)) {
			return false;
		}
		Professor other = (Professor) obj;
		if (disciplinas == null) {
			if (other.disciplinas != null) {
				return false;
			}
		} else if (!disciplinas.equals(other.disciplinas)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (provas == null) {
			if (other.provas != null) {
				return false;
			}
		} else if (!provas.equals(other.provas)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", provas=" + provas + ", disciplinas=" + disciplinas + "]";
	}	
	
}