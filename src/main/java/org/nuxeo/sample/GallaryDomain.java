package org.nuxeo.sample;


import java.util.Date;

import javax.ws.rs.Produces;

import org.nuxeo.ecm.webengine.model.WebObject;

@WebObject(type = "GallaryDomain")
@Produces("text/html; charset=UTF-8")
public class GallaryDomain {
	private String title;
	private String id;
	private String state;
	private String content;
	private String dowloadLink;
	private String docType;
	private String createdBy;
	private String creationDate;
	private String Format;
	private String verssion;
	private String modefieddate;
	private String lastModefiedBy;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getVerssion() {
		return verssion;
	}

	public void setVerssion(String verssion) {
		this.verssion = verssion;
	}

	public String getModefieddate() {
		return modefieddate;
	}

	public void setModefieddate(String modefieddate) {
		this.modefieddate = modefieddate;
	}

	public String getLastModefiedBy() {
		return lastModefiedBy;
	}

	public void setLastModefiedBy(String lastModefiedBy) {
		this.lastModefiedBy = lastModefiedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDowloadLink() {
		return dowloadLink;
	}

	public void setDowloadLink(String dowloadLink) {
		this.dowloadLink = dowloadLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
