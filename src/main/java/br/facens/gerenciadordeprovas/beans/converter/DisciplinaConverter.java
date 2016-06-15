/**
 * 
 */
package br.facens.gerenciadordeprovas.beans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.facens.gerenciadordeprovas.bean.Disciplina;
import br.facens.gerenciadordeprovas.service.DisciplinaService;

/**
 * @author Eduardo
 *
 */
@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter {

	private DisciplinaService servico = new DisciplinaService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		if (value != null && !value.isEmpty()) {
			
			  for(Disciplina d : servico.getDisciplinas())
				 if(d.getNome().equals(value))
				  	return d;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,	Object disciplina) {
		if (disciplina == null || disciplina.equals(""))
			return null;
		else
			return ((Disciplina) disciplina).getNome();
	}
	
}