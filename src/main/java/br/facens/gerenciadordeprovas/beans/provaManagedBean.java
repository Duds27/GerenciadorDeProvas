
package br.facens.gerenciadordeprovas.beans;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.fonts.*;
import com.lowagie.text.Image;
//import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.Serializable;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.util.*;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import org.apache.poi.ss.usermodel.FontFamily;
//import org.apache.poi.ss.usermodel.FontFamily;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.bean.Questao;
import br.facens.gerenciadordeprovas.bean.QuestaoAlternativa;
import br.facens.gerenciadordeprovas.bean.QuestaoVerdadeiroFalso;
import br.facens.gerenciadordeprovas.bean.QuestaoDissertativa;
import br.facens.gerenciadordeprovas.service.ProvaService;

@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class provaManagedBean {
	private Prova prova;
	private List<Prova> provas;
	private ProvaService provaService = new ProvaService();
	
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public List<Prova> getProvas() {
		if(provas == null)
		{
			provas = provaService.getProvas();
		}
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public ProvaService getProvaService() {
		return provaService;
	}
	public void setProvaService(ProvaService provaService) {
		this.provaService = provaService;
	}
	
	public void GeradorPDF()
	{
		Document pdf = new Document(PageSize.A4);
		Paragraph titulo = new Paragraph("Avaliacao");
		
		titulo.setAlignment(Element.ALIGN_CENTER);
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("C:\\prova"+ prova.getDisciplina() + ".pdf"));
          pdf.open();        
          
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade() + "     Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma() + "     Disciplina : " + prova.getDisciplina().getNome()));
          pdf.add(Chunk.NEWLINE);
          pdf.add(titulo);
          
          pdf.add(Chunk.NEWLINE);
          
          for(int i = 0; i < prova.getQuestoes().size(); i++)
          {
        	  if(prova.getQuestoes().get(i) instanceof QuestaoAlternativa)
        	  {
        		  QuestaoAlternativa alt = (QuestaoAlternativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  A - " +  
     	                 alt.getAlternativa()));
        		  pdf.add(new Paragraph("  B - " +  
        				  alt.getAlternativa()));
        		  pdf.add(new Paragraph("  C - " +  
        				  alt.getAlternativa()));
        		  pdf.add(new Paragraph("  D - " +  
        				  alt.getAlternativa()));
        	     pdf.add(Chunk.NEWLINE);
        	  }
        	  else if(prova.getQuestoes().get(i) instanceof QuestaoVerdadeiroFalso)
        	  {
        		  pdf.add(new Paragraph(""+(i + 1)+") " + "Assinale (V)erdadeiro ou (F)also"));
        		  pdf.add(new Paragraph("  (  ) " +  
     	                 prova.getQuestoes().get(i).getEnunciado())); 
     	        	  pdf.add(Chunk.NEWLINE);
        	  }
        	  else
        	  {
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
        	                 prova.getQuestoes().get(i).getEnunciado())); 
        	        	  pdf.add(Chunk.NEWLINE);
        	  }
          }
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
	
	public void GeradorGabaritoPDF()
	{
		Document pdf = new Document(PageSize.A4);
		//Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
		Paragraph titulo = new Paragraph("Gabarito", f);
		
		titulo.setAlignment(Element.ALIGN_CENTER);
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("gabarito"+ ".pdf"));
          pdf.open();
          
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade() + "     Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma() + "     Disciplina : " + prova.getDisciplina().getNome()));
          pdf.add(Chunk.NEWLINE);
          pdf.add(titulo);
          
          pdf.add(Chunk.NEWLINE);
          
          for(int i = 0; i < prova.getQuestoes().size(); i++)
          {
        	  if(prova.getQuestoes().get(i) instanceof QuestaoAlternativa)
        	  {
        		  QuestaoAlternativa alt = (QuestaoAlternativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  R: " +  
        				  alt.getRespostaalt())); 
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoDissertativa)
        	  {
        		  QuestaoDissertativa dis = (QuestaoDissertativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
      	                 prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  R: " +  
        				  dis.getRespostadis())); 
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoVerdadeiroFalso)
        	  {
        		  QuestaoVerdadeiroFalso verd = (QuestaoVerdadeiroFalso)prova.getQuestoes().get(i);
        		 pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getQuestoes().get(i).getEnunciado()));
        	     pdf.add(new Paragraph("  R: " +  
        	    		 verd.getRespostavf())); 
        	     pdf.add(Chunk.NEWLINE);
        	  }
          }
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
}