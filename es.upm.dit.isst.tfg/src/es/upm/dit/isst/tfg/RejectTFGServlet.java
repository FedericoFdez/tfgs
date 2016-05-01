package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;
import es.upm.dit.isst.tfg.model.TFG;

public class RejectTFGServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role = req.getParameter("role");
		TFGDAO dao = TFGDAOImpl.getInstance();
		TFG tfg = dao.getTFG((String) req.getParameter("author"));
		String username = (String) req.getSession().getAttribute("username");
		String author = tfg.getAuthor();
		
		switch (tfg.getStatus()) {
		case 1:
			if (role.equals("tutor")) {
				tfg.setRejected(true);
				dao.updateTFG(tfg);

				String subject = "El profesor " + username
						+ " rechaza la solicitud del TFG.";
				String text = "El profesor " + username
						+ " rechaza la solicitud del TFG propuesto por "
						+ tfg.getAuthor() + "con título " + tfg.getTitle();

				sendMail(author, subject, text);
				break;
			} else{
				resp.sendRedirect("myTFGs?error=Solicitud%20Incorrecta,%20por%20favor%20prueba%20de%20nuevo");
				return;
			}
		case 3:
			if (role.equals("tutor")) {
				tfg.setRejected(true);
				dao.updateTFG(tfg);

				String subject = "El profesor " + username
						+ " rechaza la memoria del TFG.";
				String text = "El profesor " + username
						+ " rechaza la memoria del TFG propuesto por "
						+ tfg.getAuthor() + "con título " + tfg.getTitle();

				sendMail(author, subject, text);
			}
			break;
		case 4:
			if (role.equals("secretary")) {
				tfg.setRejected(true);
				dao.updateTFG(tfg);

				String subject = "El secretario " + username
						+ " ha calificado el TFG.";
				String text = "El secretario " + username
						+ " ha calificado el TFG propuesto por "
						+ tfg.getAuthor() + "con título " + tfg.getTitle()
						+ "y tutorizado por " + tfg.getTutor();

				sendMail(author, subject, text);
				sendMail(tfg.getTutor(), subject, text);
			}
			break;
		}
		resp.sendRedirect("myTFGs");
		
	}
	
	public void sendMail(String recipient, String subject, String text) {
		Message msg = new MimeMessage(Session.getDefaultInstance(
				new Properties(), null));
		try {
			msg.setFrom(new InternetAddress("tfg@isst-tfg.appspotmail.com",
					"Sistema de gestion de TFGs"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient, "Solicitante de TFG"));
			msg.setSubject(subject);
			msg.setText(text);
			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
