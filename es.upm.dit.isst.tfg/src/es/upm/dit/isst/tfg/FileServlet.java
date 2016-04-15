package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class FileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TFGDAO dao = TFGDAOImpl.getInstance();
		TFG tfg = dao.getTFG((String) req.getParameter("author"));

		if (tfg != null && tfg.getStatus() == 2) {
			Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory
					.getBlobstoreService().getUploads(req);
			List<BlobKey> blobKeys = blobs.get("file");
			if (blobKeys == null || blobKeys.isEmpty()
					|| blobKeys.get(0) == null) {
				resp.sendError(1200);
			}
			tfg.setFile(blobKeys.get(0).getKeyString());
			tfg.setStatus(3);
			dao.updateTFG(tfg);
			resp.sendRedirect("myTFGs");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getUserPrincipal() == null) {
			resp.sendRedirect("/");
		}
		TFGDAO dao = TFGDAOImpl.getInstance();
		TFG tfg = dao.getTFG((String) req.getParameter("author"));

		BlobKey blobKey = new BlobKey(tfg.getFile());
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();

		blobstoreService.serve(blobKey, resp);
	}

}
