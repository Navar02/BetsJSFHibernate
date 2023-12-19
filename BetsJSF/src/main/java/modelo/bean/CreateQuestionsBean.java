package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;

public class CreateQuestionsBean {
	//Atributos necesarios para para crear/almacenar la interfaz grafica createQuestions
	private Date fecha;
	private Event evento;
	private String pregunta;
	private Integer apuestaMinima;
	private static List<Event> eventos=new ArrayList<Event>();
	private static BLFacade bl=new BLFacadeImplementation();
	
	public CreateQuestionsBean() {
		Vector<Event> v=bl.getEvents(fecha);
		for(Event e:v) {
			eventos.add(e);
		}
	}
	
	public static List<Event> getEventos(Date date) {
		List<Event>evento= new ArrayList<Event>(getBl().getEvents(date));
		return evento;
	}

	public static void setEventos(List<Event> eventos) {
		CreateQuestionsBean.eventos = eventos;
	}

	public static BLFacade getBl() {
		return bl;
	}

	public void setBl(BLFacade bl) {
		this.bl = bl;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event eventos) {
		this.evento = eventos;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Integer getApuestaMinima() {
		return apuestaMinima;
	}
	public void setApuestaMinima(Integer apuestaMinima) {
		this.apuestaMinima = apuestaMinima;
	}
	
	public void onDateSelect(SelectEvent event) {
		/*
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("Fecha escogida: "+event.getObject()));
		 */
		getEventos((Date)event.getObject());
	}
	
	
	public String comprobar() {
		//no elegido fecha
		if (this.getFecha()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe seleccionar una fecha"));
			return null;
		}
		//no elegido evento
		if(this.getEvento()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe seleccionar un evento"));
			return null;//return "error";
		}
		//no creada preegunta
		if(this.getPregunta()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe crear una pregunta"));
			return null;
		}
		//no hay apuesta minima
		if(this.getApuestaMinima()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe introducir una apuesta minima"));
			return null;
		}
		else {
			return "ok";
		}
	} 
}
