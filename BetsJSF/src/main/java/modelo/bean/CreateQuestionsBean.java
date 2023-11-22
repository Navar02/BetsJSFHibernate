package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
//import org.w3c.dom.events.Event;

public class CreateQuestionsBean {
	//Atributos necesarios para para crear/almacenar la interfaz grafica createQuestions
	private Date fecha;
	private String evento;
	private String pregunta;
	private Integer apuestaMinima;
	private static List<String> eventos=new ArrayList<String>();
	
	public CreateQuestionsBean() {
		 eventos.add(new String("estudiante"));
		 eventos.add(new String("profesor"));
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEventos() {
		return evento;
	}
	public void setEventos(String eventos) {
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
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
	}
}
