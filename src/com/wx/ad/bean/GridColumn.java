package com.wx.ad.bean;
public class GridColumn {
	private String header;
	
	private String name;

	private String width;
	
	private String align;
	
	public GridColumn(String header,String name,String width,String align){
		this.header=header;
		this.name=name;
		this.width=width;
		this.align=align;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}

}
