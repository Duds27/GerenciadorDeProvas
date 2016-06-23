/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import br.facens.gerenciadordeprovas.bean.Alternativa;
import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.bean.Questao;
import br.facens.gerenciadordeprovas.bean.QuestaoAlternativa;
import br.facens.gerenciadordeprovas.bean.QuestaoVerdadeiroFalso;
import br.facens.gerenciadordeprovas.bean.TrueOrFalse;
import br.facens.gerenciadordeprovas.service.AlternativaService;
import br.facens.gerenciadordeprovas.service.ConteudoService;
import br.facens.gerenciadordeprovas.service.DisciplinaService;
import br.facens.gerenciadordeprovas.service.QuestaoService;

/**
 * @author Eduardo
 *
 */
@ManagedBean
@ViewScoped
public class QuestaoVerdadeiroFalsoManagedBean {

	private QuestaoVerdadeiroFalso questao = new QuestaoVerdadeiroFalso();
	private List<QuestaoVerdadeiroFalso> questoes = new ArrayList<QuestaoVerdadeiroFalso>();
	private QuestaoService serviceQuestao = new QuestaoService();
	
	private Alternativa alternativa = new Alternativa();
	private List<Alternativa> alternativas = new ArrayList<Alternativa>();
	private AlternativaService serviceAlternativa = new AlternativaService();
	
	private Disciplina disciplina = new Disciplina();
	private List<Disciplina> disciplinas;
	private DisciplinaService serviceDisciplina = new DisciplinaService();
	
	private Conteudo conteudo = new Conteudo();
	private List<Conteudo> conteudos;
	private ConteudoService serviceConteudo = new ConteudoService();
	
	public QuestaoVerdadeiroFalso getQuestao() {
		return questao;
	}
	
	public void setQuestao(QuestaoVerdadeiroFalso questao) {
		this.questao = questao;
	}
	
	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
	
	public void save() {
		questao.setConteudo(conteudo);
		//questao.setDisciplina(disciplina);
		alternativa.setVerdade(alternativa.getVerdade());
		questao = serviceQuestao.salvar(questao);
		
		if (questoes != null)
			questoes.add(questao);
		
		questao = new QuestaoVerdadeiroFalso();
	}
	
	public void remove(Questao questao) {
		if (questao != null) {
			serviceQuestao.remover(questao);
			questoes.remove(questao);
		}
	}
	
	public void remove(Alternativa alternativa) {
		if (alternativa != null) {
			serviceAlternativa.remover(alternativa);
			alternativas.remove(alternativa);
		}
	}
	
	public void remove(Disciplina disciplina) {
		if (disciplina != null) {
			serviceDisciplina.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}
	
	public void remove(Conteudo conteudo) {
		if (conteudo != null) {
			serviceConteudo.remover(conteudo);
			conteudos.remove(conteudo);
		}
	}
	
	// Retorna a lista de quest�es alternativas
	public List<QuestaoVerdadeiroFalso> getQuestoes() {
		if (questoes == null)
			questoes = serviceQuestao.getQuestoesVerdadeiroFalso();

		return questoes;
	}
		
	// Retorna a lista de alternativas
	/*public List<Alternativa> getAlternativas() {
		if (alternativas == null)
			alternativas = serviceAlternativa.getAlternativas(questao);

		return alternativas;
	}*/
	
	// Retorna a lista de disciplinas
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = serviceDisciplina.getDisciplinas();

		return disciplinas;
	}
			
	// Retorna a lista de disciplinas
	public List<Conteudo> getConteudos() {
		if (conteudos == null)
			conteudos = serviceConteudo.getConteudos(disciplina);

		return conteudos;
	}
		
	// Edi��o de uma disciplina na tabela
	 public void onRowEdit(RowEditEvent event) {
	 	Questao q = ((Questao) event.getObject());
	 	serviceQuestao.alterar(q);
	 }
		 	
	 public void onSubjectChange() {
	 	if (disciplina != null && !disciplina.getNome().equals(""))
	 		conteudos = serviceConteudo.getConteudos(disciplina);
	 	else
	 		conteudos = new ArrayList<Conteudo>();
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
