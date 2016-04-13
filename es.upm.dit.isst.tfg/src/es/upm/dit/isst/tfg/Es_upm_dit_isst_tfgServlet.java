package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.http.*;

import es.upm.dit.isst.tfg.dao.*;
import es.upm.dit.isst.tfg.model.TFG;

@SuppressWarnings("serial")
public class Es_upm_dit_isst_tfgServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		TFGDAO dao = TFGDAOImpl.getInstance();
		dao.createTFG("Alumno 1", "Titulo del TFG", "Resumen del TFG", "Tutor", "Secretario", "", 1);
		for (TFG tfg : dao.getAllTFGs()) {
			resp.getWriter().println(tfg.getTitle());
		}

	}
}
