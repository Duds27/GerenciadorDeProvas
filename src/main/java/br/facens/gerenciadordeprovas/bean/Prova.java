/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Prova {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String curso;
	private String faculdade;
	private String turma;
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAplicacao;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "Prova_Questoes",
		joinColumns = { @JoinColumn(name = "provaID") },
		inverseJoinColumns = { @JoinColumn(name = "questaoID") }
	)
	private List<Questao> questoes;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "Prova_Conteudos",
		joinColumns = { @JoinColumn(name = "provaID") },
		inverseJoinColumns = { @JoinColumn(name = "conteudoID") }
	)
	private List<Conteudo> conteudos;
	
	/*@OneToMany(
		mappedBy = "prova",
		cascade = CascadeType.ALL
	)*/
	private Disciplina disciplina;
	
	/*@ManyToOne(
		cascade = CascadeType.ALL
	)*/
	//private Prova prova;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getFaculdade() {
		return faculdade;
	}
	
	public void setFaculdade(String faculdade) {
		this.faculdade = faculdade;
	}
	
	public String getTurma() {
		return turma;
	}
	
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAplicacao() {
		return dataAplicacao;
	}
	
	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	/*public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conteudos == null) ? 0 : conteudos.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataAplicacao == null) ? 0 : dataAplicacao.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((faculdade == null) ? 0 : faculdade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		//result = prime * result + ((prova == null) ? 0 : prova.hashCode());
		result = prime * result + ((questoes == null) ? 0 : questoes.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		if (!(obj instanceof Prova)) {
			return false;
		}
		Prova other = (Prova) obj;
		if (conteudos == null) {
			if (other.conteudos != null) {
				return false;
			}
		} else if (!conteudos.equals(other.conteudos)) {
			return false;
		}
		if (curso == null) {
			if (other.curso != null) {
				return false;
			}
		} else if (!curso.equals(other.curso)) {
			return false;
		}
		if (dataAplicacao == null) {
			if (other.dataAplicacao != null) {
				return false;
			}
		} else if (!dataAplicacao.equals(other.dataAplicacao)) {
			return false;
		}
		if (disciplina == null) {
			if (other.disciplina != null) {
				return false;
			}
		} else if (!disciplina.equals(other.disciplina)) {
			return false;
		}
		if (faculdade == null) {
			if (other.faculdade != null) {
				return false;
			}
		} else if (!faculdade.equals(other.faculdade)) {
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
		/*if (prova == null) {
			if (other.prova != null) {
				return false;
			}
		} else if (!prova.equals(other.prova)) {
			return false;
		}*/
		if (questoes == null) {
			if (other.questoes != null) {
				return false;
			}
		} else if (!questoes.equals(other.questoes)) {
			return false;
		}
		if (turma == null) {
			if (other.turma != null) {
				return false;
			}
		} else if (!turma.equals(other.turma)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Prova [id=" + id + ", curso=" + curso + ", faculdade=" + faculdade + ", turma=" + turma + ", nome="
				+ nome + ", dataAplicacao=" + dataAplicacao	+ ", questoes=" + questoes + ", conteudos=" + conteudos 
				+ ", disciplina=" + disciplina + "]";
	}
	
}