/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Eduardo
 *
 */
@Entity
public class Alternativa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String alternativa1;
	private String alternativa2;
	private String alternativa3;
	private String alternativa4;
	private String alternativa5;
	

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getAlternativa1() {
		return alternativa1;
	}

	public void setAlternativa1(String alternativa1) {
		this.alternativa1 = alternativa1;
	}

	public String getAlternativa2() {
		return alternativa2;
	}

	public void setAlternativa2(String alternativa2) {
		this.alternativa2 = alternativa2;
	}

	public String getAlternativa3() {
		return alternativa3;
	}

	public void setAlternativa3(String alternativa3) {
		this.alternativa3 = alternativa3;
	}

	public String getAlternativa4() {
		return alternativa4;
	}

	public void setAlternativa4(String alternativa4) {
		this.alternativa4 = alternativa4;
	}

	public String getAlternativa5() {
		return alternativa5;
	}

	public void setAlternativa5(String alternativa5) {
		this.alternativa5 = alternativa5;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternativa1 == null) ? 0 : alternativa1.hashCode());
		result = prime * result + ((alternativa2 == null) ? 0 : alternativa2.hashCode());
		result = prime * result + ((alternativa3 == null) ? 0 : alternativa3.hashCode());
		result = prime * result + ((alternativa4 == null) ? 0 : alternativa4.hashCode());
		result = prime * result + ((alternativa5 == null) ? 0 : alternativa5.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (!(obj instanceof Alternativa)) {
			return false;
		}
		Alternativa other = (Alternativa) obj;
		if (alternativa1 == null) {
			if (other.alternativa1 != null) {
				return false;
			}
		} else if (!alternativa1.equals(other.alternativa1)) {
			return false;
		}
		if (alternativa2 == null) {
			if (other.alternativa2 != null) {
				return false;
			}
		} else if (!alternativa2.equals(other.alternativa2)) {
			return false;
		}
		if (alternativa3 == null) {
			if (other.alternativa3 != null) {
				return false;
			}
		} else if (!alternativa3.equals(other.alternativa3)) {
			return false;
		}
		if (alternativa4 == null) {
			if (other.alternativa4 != null) {
				return false;
			}
		} else if (!alternativa4.equals(other.alternativa4)) {
			return false;
		}
		if (alternativa5 == null) {
			if (other.alternativa5 != null) {
				return false;
			}
		} else if (!alternativa5.equals(other.alternativa5)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Alternativa [id=" + id + ", alternativa1=" + alternativa1 + ", alternativa2=" + alternativa2
				+ ", alternativa3=" + alternativa3 + ", alternativa4=" + alternativa4 + ", alternativa5=" + alternativa5 + "]";
	}
	
}