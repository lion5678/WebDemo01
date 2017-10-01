package com.eeitxx.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.eeitxx.util.Sayhello;

// ZK 取得spring的bean 需先加入@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MainPageViewModel {
	private String label;
	@WireVariable
	private Sayhello hello;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Command
	@NotifyChange("label")
	public void sayHello(){
		label = hello.sayHello()+" miro";
	}
	
	@Command("gotoIndex1")
	public void gotoIndex1(){
		Executions.getCurrent().sendRedirect("/zul/index1.zul");
	}
	
	
}
