/**
 * 
 */
package br.facens.gerenciadordeprovas.beans;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import br.facens.gerenciadordeprovas.service.*;
import br.facens.gerenciadordeprovas.bean.*;
 
@ManagedBean
public class OrderListView {
     
    @ManagedProperty("#{themeService}")
    private ProvaService serviceProva;
    private List<Prova> provas;     
    @PostConstruct
    public void init() {
        provas = serviceProva.getProvas();  
    }
 
    /**
	 * @return the serviceProva
	 */
	public ProvaService getServiceProva() {
		return serviceProva;
	}


	/**
	 * @param serviceProva the serviceProva to set
	 */
	public void setServiceProva(ProvaService serviceProva) {
		this.serviceProva = serviceProva;
	}


	/**
	 * @return the provas
	 */
	public List<Prova> getProvas() {
		return provas;
	}


	/**
	 * @param provas the provas to set
	 */
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}


	public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 
}