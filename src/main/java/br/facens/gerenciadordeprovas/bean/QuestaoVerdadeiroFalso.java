/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
@DiscriminatorValue(value="VerdadeiroOuFalso")
public class QuestaoVerdadeiroFalso extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	private TrueOrFalse enumTrueOrFalse;
	
	@OneToOne
	@JoinColumn(name="id_verdadefalso")
	private Alternativa alternativa;
	

	public QuestaoVerdadeiroFalso() {
		super();
	}

	public TrueOrFalse getEnumTrueOrFalse() {
		return enumTrueOrFalse;
	}

	public void setEnumTrueOrFalse(TrueOrFalse enumTrueOrFalse) {
		this.enumTrueOrFalse = enumTrueOrFalse;
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
		result = prime * result + ((enumTrueOrFalse == null) ? 0 : enumTrueOrFalse.hashCode());
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
		if (!(obj instanceof QuestaoVerdadeiroFalso)) {
			return false;
		}
		QuestaoVerdadeiroFalso other = (QuestaoVerdadeiroFalso) obj;
		if (alternativa == null) {
			if (other.alternativa != null) {
				return false;
			}
		} else if (!alternativa.equals(other.alternativa)) {
			return false;
		}
		if (enumTrueOrFalse != other.enumTrueOrFalse) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "QuestaoVerdadeiroFalso [enumTrueOrFalse=" + enumTrueOrFalse + ", alternativa=" + alternativa + "]";
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