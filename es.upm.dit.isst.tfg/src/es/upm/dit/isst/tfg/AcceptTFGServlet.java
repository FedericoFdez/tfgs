package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class AcceptTFGServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role = req.getParameter("role");
		Map data = req.getParameterMap();
		TFGDAO dao = TFGDAOImpl.getInstance();
		TFG tfg = dao.getTFG((String)req.getParameter("author"));
		
		if (role.equals("tutor") && data.containsKey("secretary")){
			String secretary = req.getParameter("secretary");
			tfg.setSecretary(secretary);
			tfg.setStatus(2);
			dao.updateTFG(tfg);
			resp.sendRedirect("myTFGs");
		} else if (role.equals("secretary")){
			tfg.setStatus(4);
			dao.updateTFG(tfg);
			resp.sendRedirect("myTFGs");
		} else
			resp.sendRedirect("myTFGs?error=Solicitud%20Incorrecta,%20por%20favor%20prueba%20de%20nuevo");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("myTFGs");
	}
	
}
