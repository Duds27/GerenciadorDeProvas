/**
 * 
 */
package br.facens.gerenciadordeprovas.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author eencarnacao
 *
 */
@Entity
@DiscriminatorValue(value="Dissertativa")
public class QuestaoDissertativa extends Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
}