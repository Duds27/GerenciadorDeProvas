/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.RowEditEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.service.DisciplinaService;

/**
 * @author Eduardo
 *
 */
@ManagedBean
@ViewScoped
public class DisciplinaManagedBean {

	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private DisciplinaService service = new DisciplinaService();

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void save() {
		disciplina = service.salvar(disciplina);

		if (disciplina != null)
			disciplinas.add(disciplina);

		disciplina = new Disciplina();
	}
	
	public void remove(Disciplina disciplina) {
		if (disciplina != null) {
			service.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}

	// Retorna a lista de alunos para a tabela
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = service.getDisciplinas();

		return disciplinas;
	}
	   
    // Edi��o de uma disciplina na tabela
 	public void onRowEdit(RowEditEvent event) {
 		Disciplina d = ((Disciplina) event.getObject());
 		service.alterar(d);
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
        //String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";
         
        //pdf.add(Image.getInstance(logo));
    }

}
