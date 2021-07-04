package com.dvdrental.inventory.common;

public class ApplicationProperties {
	private String exportPath=null;

	public ApplicationProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getExportPath() {
		return exportPath;
	}

	public void setExportPath(String exportPath) {
		this.exportPath = exportPath;
	}

	@Override
	public String toString() {
		return "ApplicationProperties [exportPath=" + exportPath + "]";
	}
			

}
