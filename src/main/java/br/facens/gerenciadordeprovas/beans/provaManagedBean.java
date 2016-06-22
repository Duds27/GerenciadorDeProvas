
package br.facens.gerenciadordeprovas.beans;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
//import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.Serializable;

import com.lowagie.text.pdf.draw.LineSeparator;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.bean.Prova;
import br.facens.gerenciadordeprovas.bean.Questao;

@ManagedBean
@SessionScoped
public class provaManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private StreamedContent content;
    private Prova p;
    private String senha_usuario,senha_professor;
    public provaManagedBean(Prova p,String senha_usuario,String senha_professor)
    {
        super();
        this.p = p;
        this.setSenha_usuario(senha_usuario);
        this.setSenha_professor(senha_professor);
    }

    public void onPrerender(ComponentSystemEvent event) {
        List<Questao> questoes = new ArrayList<Questao>();
	    List<Conteudo> conteudos = p.getConteudos();

    	for (Conteudo c : conteudos) {
    		List<Questao> aux = c.getQuestoes();
    		for (Questao q : aux) {
    			questoes.add(q);
    		}
    	}
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4, 72, 72, 72, 72);
            PdfWriter.getInstance(document, out);
            document.open();
            Image img = Image.getInstance("facens.jpg");
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);
            //adiciona o texto ao PDF
            Paragraph para = new Paragraph(p.getDisciplina().getNome());
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            //Adiciona uma linha divisoria após adicionar o Nome da Disciplina
            document.add(Chunk.NEWLINE);
           LineSeparator ls = new LineSeparator();
            document.add(new Chunk(ls));

            for (Questao q: questoes)
            {
                Paragraph paragrafo = new Paragraph(q.getEnunciado());
                paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(paragrafo);

            }

            document.close();
            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

	public String getSenha_usuario() {
		return senha_usuario;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}

	public String getSenha_professor() {
		return senha_professor;
	}

	public void setSenha_professor(String senha_professor) {
		this.senha_professor = senha_professor;
	}
}
