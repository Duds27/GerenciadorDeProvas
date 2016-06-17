/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Eduardo
 *
 */
@Converter(autoApply=true)
public class TrueOrFalseConverter implements AttributeConverter<TrueOrFalse, String> {

	@Override
	public String convertToDatabaseColumn(TrueOrFalse constante) {
		switch (constante) 
		{
			case VERDADEIRO: 		return "V"; 
			case FALSO: 			return "F"; 
			default: throw new IllegalArgumentException("Unknown" + constante);
		}
	}

	@Override
	public TrueOrFalse convertToEntityAttribute(String constante) {
		switch (constante)
		{
			case "V": return TrueOrFalse.VERDADEIRO;
			case "F": return TrueOrFalse.FALSO;
			default: throw new IllegalArgumentException("Unknown" + constante);
		}
	}

}