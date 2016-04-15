package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class CleanTFGsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TFGDAO dao = TFGDAOImpl.getInstance();
		List<TFG> tfgs = dao.getAllTFGs();
		
		for (TFG tfg : tfgs){
			dao.deleteTFG(tfg);
		}
		
	}

}
