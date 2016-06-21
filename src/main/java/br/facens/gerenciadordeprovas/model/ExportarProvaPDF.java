/**
 * 
 */
package br.facens.gerenciadordeprovas.model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
//import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.net.MalformedURLException;

import com.lowagie.text.pdf.draw.LineSeparator;
import java.util.*;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.bean.Questao;
//textpdf.text.Document;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
/**
 * @author eencarnacao
 *
 */
public class ExportarProvaPDF implements ExportarProvaInterface {
	private Prova p;
    private String senha_usuario,senha_professor;
    public ExportarProvaPDF(Prova p,String senha_usuario,String senha_professor)
    {
        super();
        this.p = p;
        this.senha_usuario = senha_usuario;
        this.senha_professor = senha_professor;
    }

    public void geraPdf(Prova p,String senha_usuario,String senha_professor) throws DocumentException, MalformedURLException, IOException
    {
    Document doc = null;
    OutputStream os = null;
	List<Questao> questoes = new ArrayList<Questao>();
	List<Conteudo> conteudos = p.getConteudos();
	
	for (Conteudo c : conteudos) {
		List<Questao> aux = c.getQuestoes();
		for (Questao q : aux) {
			questoes.add(q);
		}
	}

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("prova.pdf");
            PdfWriter writer = PdfWriter.getInstance(doc,os);
            //setEncryption(boolean strength, String userPassword, String ownerPassword, int permissions)
            writer.setEncryption(PdfWriter.STRENGTH128BITS, senha_usuario, senha_professor, PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
            //associa a stream de saída ao
            //PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();
            Image img = Image.getInstance("facens.jpg");
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);
            //adiciona o texto ao PDF
            Paragraph para = new Paragraph(p.getDisciplina().getNome());
            para.setAlignment(Element.ALIGN_CENTER);
            doc.add(para);
            //Adiciona uma linha divisoria após adicionar o Nome da Disciplina
            doc.add(Chunk.NEWLINE);
            LineSeparator ls = new LineSeparator();
            doc.add(new Chunk(ls));

            for (Questao q: questoes)
            {
                Paragraph paragrafo = new Paragraph(q.getEnunciado());
                paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                doc.add(paragrafo);

            }

        } finally {
            if (doc != null) {
                //fechamento do documento
                doc.close();
            }
            if (os != null) {
               //fechamento da stream de saída
               os.close();
            }
        }
    }
}
