package modelo.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionsBean {
	//Atributos necesarios para para crear/almacenar la interfaz grafica createQuestions
	private Date fecha;
	private Event evento;
	private String pregunta;
	private Integer apuestaMinima;
	private static List<Event> eventos=new ArrayList<Event>();
	private BLFacade bl=new BLFacadeImplementation();
	
	public CreateQuestionsBean() {
		eventos=bl.getEvents(fecha);		
	}
	
	public List<Event> getEventos() {
		return eventos;
	}

	public void setEventos(List<Event> event) {
		eventos = event;
	}

	public BLFacade getBl() {
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
	public void setEvento(Event event) {
		this.evento = event;
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
		
		 fecha=(Date)event.getObject();
		 eventos=bl.getEvents(fecha);
		 System.out.println(eventos.toString());
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida. "+event.getObject()));
	}

	public void eventosEnFecha(Date dia) {
		eventos.clear();
		
		eventos=bl.getEvents(dia);
		System.out.println(eventos);
	
	}
	
	
	public void crearPregunta() throws EventFinished, QuestionAlreadyExist {
//		String res=comprobar();
//		if(res.equals("ok")) {
//			bl.createQuestion(evento, pregunta, apuestaMinima);
//		}
		if (this.getFecha()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe seleccionar una fecha"));
		}
		//no elegido evento
		else if(this.getEvento()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe seleccionar un evento"));//return "error";
		}
		//no creada preegunta
		else if(this.getPregunta()==null||this.getPregunta().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe crear una pregunta"));
		}
		//no hay apuesta minima
		else if(this.getApuestaMinima()==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Debe introducir una apuesta minima"));
		}
		else {
			bl.createQuestion(evento, pregunta, apuestaMinima);
		}
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
//		else {
//			return "ok";
//		}
		return "ok";
	}
	
	public static Event getObject(String event) {
		 for (Event t: eventos){
			if (event.equals(t.getDescription()))
				return t;
		 }
		 return null;
	}
	
	public void listener(AjaxBehaviorEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El tipo del usuario:"+evento.getEventNumber()+"/"+evento.getDescription()));
	}
	
	public void onEventSelect(SelectEvent event) {
		this.evento=(Event)event.getObject();
		System.out.println(evento);
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes", new FacesMessage("El tipo del usuario (tabla):"+evento.getEventNumber()+"/"+evento.getDescription()));
	}
	
	public String volver() {
		return "volver";
	}
}
