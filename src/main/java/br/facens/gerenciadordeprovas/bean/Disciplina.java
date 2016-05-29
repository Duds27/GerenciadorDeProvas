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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Disciplina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;
	
	@OneToMany(mappedBy="disciplina", cascade = CascadeType.ALL)
	private List<Conteudo> conteudo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provaID")
	private Prova prova;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professorID")
	private Professor professor;
	

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
	
	public List<Conteudo> getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(List<Conteudo> conteudo) {
		this.conteudo = conteudo;
	}
	
	public void addConteudo(Conteudo conteudo) {
		this.conteudo.add(conteudo);
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((prova == null) ? 0 : prova.hashCode());
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
		if (!(obj instanceof Disciplina)) {
			return false;
		}
		Disciplina other = (Disciplina) obj;
		if (conteudo == null) {
			if (other.conteudo != null) {
				return false;
			}
		} else if (!conteudo.equals(other.conteudo)) {
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
		if (professor == null) {
			if (other.professor != null) {
				return false;
			}
		} else if (!professor.equals(other.professor)) {
			return false;
		}
		if (prova == null) {
			if (other.prova != null) {
				return false;
			}
		} else if (!prova.equals(other.prova)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", conteudo=" + conteudo + ", prova=" + prova
				+ ", professor=" + professor + "]";
	}
	
}