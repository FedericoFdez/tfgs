package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;

public class NewTFGServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map data = req.getParameterMap();
		if (data.containsKey("title") && data.containsKey("summary")
				&& data.containsKey("tutor")) {
			String title = req.getParameter("title");
			String summary = req.getParameter("summary");
			String tutor = req.getParameter("tutor");
			TFGDAO dao = TFGDAOImpl.getInstance();
			dao.createTFG((String)req.getSession().getAttribute("username"), title,
					summary, tutor, "", "", 1, false);
			resp.sendRedirect("myTFGs");
		} else {
			resp.sendRedirect("myTFGs?error=Solicitud%20Incorrecta,%20por%20favor%20prueba%20de%20nuevo");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("myTFGs");
	}

}
