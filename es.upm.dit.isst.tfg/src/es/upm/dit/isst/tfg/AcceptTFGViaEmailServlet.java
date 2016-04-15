package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class AcceptTFGViaEmailServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties(), null), req.getInputStream());
			String author = message.getSubject();
			TFGDAO dao = TFGDAOImpl.getInstance();
			
			TFG tfg = dao.getTFG(author);
			tfg.setStatus(4);
			dao.updateTFG(tfg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}

}
