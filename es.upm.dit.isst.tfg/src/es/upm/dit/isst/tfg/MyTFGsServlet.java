/**
 * 
 */
package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

/**
 * @author federico
 *
 */
public class MyTFGsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		if (req.getUserPrincipal() == null){
			resp.sendRedirect("/");
		}
		TFGDAO dao = TFGDAOImpl.getInstance();
		
		// Retrieve user info
		String username = req.getUserPrincipal().getName();
		Boolean isUserStudent = this.isUserStudent(username);
		req.getSession().setAttribute("username", username);
		req.getSession().setAttribute("isUserStudent", isUserStudent);
		
		// Get user TFG(s)
		if (isUserStudent){
			TFG tfg = dao.getTFG(username);
			req.getSession().setAttribute("tfg", tfg);
		} else {
			List<TFG> tfgsAsTutor = dao.getTFGsByTutor(username);
			List<TFG> tfgsAsSecretary = dao.getTFGsBySecretary(username);
			req.getSession().setAttribute("tfgsAsTutor", tfgsAsTutor);
			req.getSession().setAttribute("tfgsAsSecretary", tfgsAsSecretary);
		}
		
		// Dispatch JSP
		RequestDispatcher view = req.getRequestDispatcher("myTFGsView.jsp");
		view.forward(req, resp);
	}
	
	public boolean isUserStudent(String username){
		TFGDAO dao = TFGDAOImpl.getInstance();
		if (dao.getTFG(username) != null)
			return true;
		else if (dao.getTFGsByTutor(username).size() > 0 || dao.getTFGsBySecretary(username).size() > 0)
			return false;
		else
			return true; //default is student
	}
	
}
