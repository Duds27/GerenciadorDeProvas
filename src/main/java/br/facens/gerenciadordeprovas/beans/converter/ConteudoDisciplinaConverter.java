/**
 * 
 */
package br.facens.gerenciadordeprovas.beans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.facens.gerenciadordeprovas.bean.Conteudo;
import br.facens.gerenciadordeprovas.service.ConteudoService;

/**
 * @author Eduardo
 *
 */
@FacesConverter("conteudoDisciplinaConverter")
public class ConteudoDisciplinaConverter implements Converter {

	private ConteudoService servico = new ConteudoService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			
			  for(Conteudo c : servico.getConteudos())
				 if(c.getTopico().equals(value))
				  	return c;
					
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object conteudo) {
		if (conteudo == null || conteudo.equals("")) {
			return null;
		} else {
			return ((Conteudo) conteudo).getTopico();

		}
	}
	
}