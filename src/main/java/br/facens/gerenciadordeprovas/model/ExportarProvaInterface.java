/**
 * 
 */
package br.facens.gerenciadordeprovas.model;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.DocumentException;

/**
 * @author eencarnacao
 *
 */
public interface ExportarProvaInterface {
	void geraPdf() throws DocumentException, MalformedURLException, IOException;
}
