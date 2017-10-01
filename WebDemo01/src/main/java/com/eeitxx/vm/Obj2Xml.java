package com.eeitxx.vm;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.eeitxx.util.AuthWindow;
import com.eeitxx.xmlbean.User;


public class Obj2Xml {
	private static final Logger log = LogManager.getLogger(Obj2Xml.class);
	@Wire
	private Window win;
	@Wire
	private Label lab;
	private String xmlstr;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	public String getXmlstr() {
		return xmlstr;
	}

	public void setXmlstr(String xmlstr) {
		this.xmlstr = xmlstr;
	}

	@Command
	public void getXml(){
		AuthWindow subwin = new AuthWindow();
		subwin.getSubwin(win, null);
		win.addEventListener("onSubmit", new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				log.debug("getSubwi["+arg0.getData().toString());
				if((boolean)arg0.getData()){
					User user = new com.eeitxx.xmlbean.User();
					user.setId("1");
					user.setName("jion");
					Integer[] ph = {1,2,3};
					user.setPhone(Arrays.asList(ph));
					user.setAge(3);
					
					try {
						JAXBContext jaxbContext = JAXBContext.newInstance(com.eeitxx.xmlbean.User.class);
						
						Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//			jaxbMarshaller.marshal(user, System.out);
						StringWriter wr = new StringWriter();
						jaxbMarshaller.marshal(user, wr);
						log.debug(wr);
						lab.setValue(wr.toString());
						setXmlstr(wr.toString());
//						xmlstr = wr.toString();
					} catch (JAXBException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		log.debug(getXmlstr());
	}
	
	@Command
	@NotifyChange("xmlstr")
	public void getxml(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		log.debug("eventdata["+e.getData());
		User user = new com.eeitxx.xmlbean.User();
		user.setId("1");
		user.setName("jion");
		Integer[] ph = {1,2,3};
		user.setPhone(Arrays.asList(ph));
		user.setAge(3);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(com.eeitxx.xmlbean.User.class);
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//jaxbMarshaller.marshal(user, System.out);
			StringWriter wr = new StringWriter();
			jaxbMarshaller.marshal(user, wr);
			log.debug(wr);
			setXmlstr(wr.toString());
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}
}
