package com.jcg.examples.viewBean;


public class WebHookResponse {
	
	private String speech;
	private String displayText;
	private String source="java-webhook";
	
	public String getSpeech() {
		return speech;
	}
	public String getDisplayText() {
		return displayText;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	
	
}
