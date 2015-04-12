package org.nuxeo.sample;

import java.io.Serializable;
import java.util.List;

public class GallaryJson implements Serializable {

	private GallaryDomain gallaryDomain;
	private List<GallaryDomain> gallaryDomains;
	private Integer size;

	public GallaryDomain getGallaryDomain() {
		return gallaryDomain;
	}

	public void setGallaryDomain(GallaryDomain gallaryDomain) {
		this.gallaryDomain = gallaryDomain;
	}

	public List<GallaryDomain> getGallaryDomains() {
		return gallaryDomains;
	}

	public void setGallaryDomains(List<GallaryDomain> gallaryDomains) {
		this.gallaryDomains = gallaryDomains;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
