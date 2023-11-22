package modelo.bean;

import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;

public class QueryQuestionBean {
	public Date dateOE;
	public List<Event> listEv;
	public List<domain.Question> listQ;
	public String event;
	public String Question;
	public BLFacade bl=new BLFacadeImplementation();
	
	public Date getFecha() {
		return dateOE;
	}
	
	public void setFecha(Date dateOE) {
		this.dateOE = dateOE;
	}
	
	public List<Event> getEvents(Date dateOE){
		listEv=bl.getEvents(dateOE);
		return listEv;
	}
}
