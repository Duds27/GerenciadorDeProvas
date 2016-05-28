/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eencarnacao
 *
 */
@Entity
public class Prova {
	
	@Id
	private long id;
	private String curso;
	private String faculdade;
	private String turma;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAplicacao;
	
	private int quantidadeQuestoes;

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
	
	@OneToMany(
		mappedBy = "prova",
		cascade = CascadeType.ALL
	)
	private List<Disciplina> disciplinas;
	
	@ManyToOne(
		cascade = CascadeType.ALL
	)
	private Prova prova;
	
	
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
	
	public Calendar getDataAplicacao() {
		return dataAplicacao;
	}
	
	public void setDataAplicacao(Calendar dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	
	public int getQuantidadeQuestoes() {
		return quantidadeQuestoes;
	}
	
	public void setQuantidadeQuestoes(int quantidadeQuestoes) {
		this.quantidadeQuestoes = quantidadeQuestoes;
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
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conteudos == null) ? 0 : conteudos.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataAplicacao == null) ? 0 : dataAplicacao.hashCode());
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + ((faculdade == null) ? 0 : faculdade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((prova == null) ? 0 : prova.hashCode());
		result = prime * result + quantidadeQuestoes;
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
		if (disciplinas == null) {
			if (other.disciplinas != null) {
				return false;
			}
		} else if (!disciplinas.equals(other.disciplinas)) {
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
		if (prova == null) {
			if (other.prova != null) {
				return false;
			}
		} else if (!prova.equals(other.prova)) {
			return false;
		}
		if (quantidadeQuestoes != other.quantidadeQuestoes) {
			return false;
		}
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
		return "Prova [id=" + id + ", curso=" + curso + ", faculdade=" + faculdade + ", turma=" + turma
				+ ", dataAplicacao=" + dataAplicacao + ", quantidadeQuestoes=" + quantidadeQuestoes + ", questoes="
				+ questoes + ", conteudos=" + conteudos + ", disciplinas=" + disciplinas + ", prova=" + prova + "]";
	}
	
}