/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

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
	
	@OneToMany(mappedBy="conteudo", cascade=CascadeType.ALL)
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
	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
     
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";
         
        pdf.add(Image.getInstance(logo));
    }
	
}