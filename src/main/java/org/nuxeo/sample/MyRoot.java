/**
 * 
 */

package org.nuxeo.sample;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.GregorianCalendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.blobholder.BlobHolder;
import org.nuxeo.ecm.core.api.impl.blob.InputStreamBlob;
import org.nuxeo.ecm.core.storage.StorageBlob;
import org.nuxeo.ecm.platform.preview.helper.PreviewHelper;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.exceptions.WebDocumentException;
import org.nuxeo.ecm.webengine.model.impl.ModuleRoot;

/**
 * The root entry for the WebEngine module.
 * 
 * @author user
 */
@Path("/gallary")
@Produces("text/html;charset=UTF-8")
@WebObject(type = "MyRoot")
public class MyRoot extends ModuleRoot {

	Log logger = LogFactory.getLog(ModuleRoot.class);

	@GET
	public Object doGet() throws Exception {
		List<GallaryDomain> list = getAllDocumentModel(getContext()
				.getCoreSession());

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("gallaryDomains", list);

		return getView("index").args(args);
	}

	@GET
	@Path("/json")
	@Produces("application/json;charset=UTF-8")
	public Object doGetJson() throws Exception {
		try {
			List<GallaryDomain> list = getAllDocumentModel(getContext()
					.getCoreSession());
			GallaryJson gallaryJson = new GallaryJson();
			gallaryJson.setGallaryDomains(list);
			gallaryJson.setSize(list.size());
			return toJsonBlob(gallaryJson);

		} catch (ClientException e) {
			throw new WebDocumentException(e);
		}
	}

	private List<GallaryDomain> getAllDocumentModel(CoreSession session)
			throws Exception {

		String query = "SELECT * FROM Document WHERE ecm:path STARTSWITH '/default-domain/workspaces/Books'";

		DocumentModelList docs = session.query(query);
		List<GallaryDomain> gallaryDomains = new ArrayList<GallaryDomain>();
		for (DocumentModel doc : docs) {
			GallaryDomain domain = getGallaryDomain(doc);
			gallaryDomains.add(domain);
		}
		return gallaryDomains;
	}

	private Blob toJsonBlob(Object object) throws ClientException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, object);
			return new InputStreamBlob(new ByteArrayInputStream(writer
					.toString().getBytes("UTF-8")), "application/json");
		} catch (Exception e) {
			throw new ClientException(e);
		}
	}

	private GallaryDomain getGallaryDomain(final DocumentModel doc)
			throws Exception {
		GallaryDomain domain = new GallaryDomain();
		domain.setTitle(doc.getTitle());
		domain.setId(doc.getId());
		domain.setState(doc.getLifeCyclePolicy());
		StorageBlob bob = (StorageBlob) doc.getPropertyValue("file:content");
		String file = getContext().getBaseURL() + "/nuxeo/nxbigfile/default/"
				+ doc.getId() + "/file:content/" + bob.getFilename();

		// String file = PreviewHelper.getPreviewURL(doc);
		// if (PreviewHelper.docHasBlobToPreview(doc)) {
		// domain.setContent(getContext().getBaseURL() + "/nuxeo/" + file);
		// }
		domain.setContent(file);
		domain.setFormat(bob.getMimeType());
		domain.setDowloadLink(getDownloadURL(doc));
		domain.setDocType(doc.getDocumentType().getName());
		domain.setVerssion(doc.getVersionLabel());
		domain.setCreatedBy((String) doc.getPropertyValue("dc:creator"));
		GregorianCalendar created = (GregorianCalendar) doc
				.getPropertyValue("dc:created");
		domain.setCreationDate(getFormatedDate(new Date(created
				.getTimeInMillis())));
		GregorianCalendar modified = (GregorianCalendar) doc
				.getPropertyValue("dc:modified");
		domain.setModefieddate(getFormatedDate(new Date((modified
				.getTimeInMillis()))));
		return domain;
	}

	private String getFormatedDate(Date date) {
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy:HH:mm:SS Z");
		return ft.format(date);
	}

	public String getDownloadURL(DocumentModel docModel) throws Exception {
		BlobHolder bh = docModel.getAdapter(BlobHolder.class);
		String filename = bh.getBlob().getFilename();
		String mimetype = bh.getBlob().getMimeType();

		String downloadURL = getContext().getBaseURL() + "/nuxeo/";
		downloadURL += "nxbigfile" + "/";
		downloadURL += docModel.getRepositoryName() + "/";
		downloadURL += docModel.getRef().toString() + "/";
		downloadURL += "blobholder:0" + "/";
		downloadURL += filename;
		downloadURL += "?mimetype=" + mimetype;

		return downloadURL;
	}

}
