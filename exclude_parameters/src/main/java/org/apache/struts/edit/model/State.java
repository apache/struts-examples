package org.apache.struts.edit.model;

public class State {
	
	private String stateAbbr;
	
	private String stateName;
	
	public State (String stateAbbr, String stateName) {
		
		this.stateAbbr = stateAbbr;
		this.stateName = stateName;
		
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public String getStateAbbr() {
		return stateAbbr;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}
	
	public String toString() {
		
		return getStateAbbr() ;
		
	}
	

}
