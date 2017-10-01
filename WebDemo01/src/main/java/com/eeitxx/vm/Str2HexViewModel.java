package com.eeitxx.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

public class Str2HexViewModel {

	
	private String label;
	
	public String getLabel() {
		return label;
	}

//	public void setLabel(String label) {
//		this.label = label;
//	}

	@Command
	@NotifyChange("label")
	public void sayHello(){
		System.out.println("sayHello");
		label = "Hello";
	}
	
	@Command("gotoIndex1")
	public void gotoIndex1(){
		Executions.getCurrent().sendRedirect("/zul/index1.zul");
	}
}
