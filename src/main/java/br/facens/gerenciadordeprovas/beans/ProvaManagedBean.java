
package br.facens.gerenciadordeprovas.beans;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
//import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;

import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.bean.QuestaoAlternativa;
import br.facens.gerenciadordeprovas.bean.QuestaoDissertativa;
import br.facens.gerenciadordeprovas.bean.QuestaoVerdadeiroFalso;
import br.facens.gerenciadordeprovas.service.ProvaService;

@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class ProvaManagedBean {
	
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
	
/*	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}*/
	

	
	public void geradorPDF(Prova prova)
	{
		String usuario = "aluno";
        String senha = "facens";
		
		Document pdf = new Document(PageSize.A4);
		
		try
		{
          PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream("C:\\prova.pdf"));
          
          writer.setEncryption(usuario.getBytes(), senha.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
          
          pdf.open();        
        
          Paragraph titulo = new Paragraph("AVALIAÇÃO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
  		  titulo.setAlignment(Element.ALIGN_CENTER);
  		  
          
          Image image2 = Image.getInstance("C:\\facens-logo-topo.jpg");
          Paragraph img = new Paragraph();
          img.add(image2);
          image2.setAlignment(Image.ALIGN_CENTER);
          img.setAlignment(Element.ALIGN_CENTER);
          pdf.add(image2);
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          pdf.add(titulo);
          
          Paragraph bolder  = new Paragraph("Faculdade: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          Paragraph aux 	= new Paragraph(prova.getFaculdade());
          
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Curso: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getCurso());
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Disciplina : ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getDisciplina().getNome());
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Turma: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getTurma());
          bolder.add(aux);
          pdf.add(bolder);

          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          
          
          
          for(int i = 0; i < prova.getQuestoes().size(); i++)
          {
        	  Paragraph title1 = new Paragraph("Questão " + String.valueOf(i+1),
							FontFactory.getFont(FontFactory.HELVETICA,
							18,
							Font.BOLDITALIC,
							new CMYKColor(0, 255, 255,17)));
				Chapter chapter1 = new Chapter(title1, 1);
				chapter1.setNumberDepth(0);
				pdf.add(chapter1);
        	  
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoAlternativa)
        	  {
        		  QuestaoAlternativa alt = (QuestaoAlternativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph("Enunciado: " +  
     	                 prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("a) " +  
     	                 alt.getAlternativa().getAlternativa1()));
        		  pdf.add(new Paragraph("b) " +  
        				  alt.getAlternativa().getAlternativa2()));
        		  pdf.add(new Paragraph("c) " +  
        				  alt.getAlternativa().getAlternativa3()));
        		  pdf.add(new Paragraph("d) " +  
        				  alt.getAlternativa().getAlternativa4()));
        		  pdf.add(new Paragraph("e) " +  
        				  alt.getAlternativa().getAlternativa5()));
        	     pdf.add(Chunk.NEWLINE);
        	  }
        	  else if(prova.getQuestoes().get(i) instanceof QuestaoVerdadeiroFalso)
        	  {
        		  pdf.add(new Paragraph("Enunciado: " + prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("(  ) FALSO"));
        		  pdf.add(new Paragraph("(  ) VERDADEIRO"));
     	          pdf.add(Chunk.NEWLINE);
        	  }
        	  else
        	  {        		  
        		  pdf.add(new Paragraph("Enunciado: " + prova.getQuestoes().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("Resposta: "));
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
	
	public void geradorGabaritoPDF(Prova prova)
	{
		String usuario = "aluno";
        String senha = "facens";
		
		Document pdf = new Document(PageSize.A4);
		
		try
		{
          PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream("C:\\gabarito.pdf"));
          
          writer.setEncryption(usuario.getBytes(), senha.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
          
          pdf.open();        
        
          Paragraph titulo = new Paragraph("GABARITO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
  		  titulo.setAlignment(Element.ALIGN_CENTER);
  		  
          
          Image image2 = Image.getInstance("C:\\facens-logo-topo.jpg");
          Paragraph img = new Paragraph();
          img.add(image2);
          image2.setAlignment(Image.ALIGN_CENTER);
          img.setAlignment(Element.ALIGN_CENTER);
          pdf.add(image2);
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          pdf.add(titulo);
          
          Paragraph bolder  = new Paragraph("Faculdade: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          Paragraph aux 	= new Paragraph(prova.getFaculdade());
          
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Curso: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getCurso());
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Disciplina : ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getDisciplina().getNome());
          bolder.add(aux);
          pdf.add(bolder);
          
          bolder = new Paragraph("Turma: ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(100, 60, 40, 37)));
          aux 	= new Paragraph(prova.getTurma());
          bolder.add(aux);
          pdf.add(bolder);

          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          Paragraph title1 = new Paragraph("GABARITO - RESPOSTAS",
					FontFactory.getFont(FontFactory.HELVETICA,
					18,
					Font.BOLDITALIC,
					new CMYKColor(0, 255, 255,17)));
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);
		pdf.add(chapter1);
          
          for(int i = 0; i < prova.getQuestoes().size(); i++)
          {
        	  
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoAlternativa)
        	  {
        		 // QuestaoAlternativa alt = (QuestaoAlternativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph("Questão "+(i + 1) + ": " +   
     	                 prova.getQuestoes().get(i).getResposta()));
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoDissertativa)
        	  {
        		  //QuestaoDissertativa dis = (QuestaoDissertativa)prova.getQuestoes().get(i);
        		  pdf.add(new Paragraph("Questão "+(i + 1) + ": " +  
      	                 prova.getQuestoes().get(i).getResposta()));
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getQuestoes().get(i) instanceof QuestaoVerdadeiroFalso)
        	  {
        		  //QuestaoVerdadeiroFalso verd = (QuestaoVerdadeiroFalso)prova.getQuestoes().get(i);
        		 pdf.add(new Paragraph("Questão "+ (i + 1) + ": " + 
     	                 prova.getQuestoes().get(i).getResposta()));
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
	
	
	public void onChange() {
		if (prova != null && !prova.getNome().equals(""))
			provas = provaService.getProvas();
		else
			provas = new ArrayList<Prova>();
	}
	
	
	
	public void onRowSelect() {
		
	}
	
}