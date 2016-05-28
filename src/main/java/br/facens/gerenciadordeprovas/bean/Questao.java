/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author eencarnacao
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipoQuestao", discriminatorType=DiscriminatorType.STRING,length=20)
public abstract class Questao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int dificuldade;
	private String enunciado;
	private String resposta;
	private int tempo;
	private int quantidadeUso;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Conteudo conteudo;
	
	@ManyToMany(
		mappedBy = "questoes",
		cascade=CascadeType.ALL
	)
	private List<Prova> prova;

	public abstract void gerarProva();
	
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
	 * @return the dificuldade
	 */
	public int getDificuldade() {
		return dificuldade;
	}

	/**
	 * @param dificuldade the dificuldade to set
	 */
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	/**
	 * @return the enunciado
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado the enunciado to set
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * @return the resposta
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * @param resposta the resposta to set
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	/**
	 * @return the tempo
	 */
	public int getTempo() {
		return tempo;
	}

	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	/**
	 * @return the quantidadeUso
	 */
	public int getQuantidadeUso() {
		return quantidadeUso;
	}

	/**
	 * @param quantidadeUso the quantidadeUso to set
	 */
	public void setQuantidadeUso(int quantidadeUso) {
		this.quantidadeUso = quantidadeUso;
	}

	/**
	 * @return the conteudo
	 */
	public Conteudo getConteudo() {
		return conteudo;
	}

	/**
	 * @param conteudo the conteudo to set
	 */
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * @return the prova
	 */
	public List<Prova> getProva() {
		return prova;
	}

	/**
	 * @param prova the prova to set
	 */
	public void setProva(List<Prova> prova) {
		this.prova = prova;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + dificuldade;
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((prova == null) ? 0 : prova.hashCode());
		result = prime * result + quantidadeUso;
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result + tempo;
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
		if (!(obj instanceof Questao)) {
			return false;
		}
		Questao other = (Questao) obj;
		if (conteudo == null) {
			if (other.conteudo != null) {
				return false;
			}
		} else if (!conteudo.equals(other.conteudo)) {
			return false;
		}
		if (dificuldade != other.dificuldade) {
			return false;
		}
		if (enunciado == null) {
			if (other.enunciado != null) {
				return false;
			}
		} else if (!enunciado.equals(other.enunciado)) {
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
		if (quantidadeUso != other.quantidadeUso) {
			return false;
		}
		if (resposta == null) {
			if (other.resposta != null) {
				return false;
			}
		} else if (!resposta.equals(other.resposta)) {
			return false;
		}
		if (tempo != other.tempo) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Questao [id=" + id + ", dificuldade=" + dificuldade + ", enunciado=" + enunciado + ", resposta="
				+ resposta + ", tempo=" + tempo + ", quantidadeUso=" + quantidadeUso + ", conteudo=" + conteudo
				+ ", prova=" + prova + "]";
	}
	
}