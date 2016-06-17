/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author eencarnacao
 *
 */
@Entity
@DiscriminatorValue(value="Dissertativa")
public class QuestaoDissertativa extends Questao {

	private static final long serialVersionUID = 1L;

	
	
}