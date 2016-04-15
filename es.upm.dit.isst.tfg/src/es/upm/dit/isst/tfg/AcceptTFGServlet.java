package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		TFG tfg = dao.getTFG((String) req.getParameter("author"));
		String username = (String) req.getSession().getAttribute("username");
		String author = tfg.getAuthor();

		if (role.equals("tutor") && data.containsKey("secretary")) {
			String secretary = req.getParameter("secretary");
			tfg.setSecretary(secretary);
			tfg.setStatus(2);
			dao.updateTFG(tfg);

			String subject = "El profesor " + username
					+ "acepta actuar como tutor del TFG.";
			String text = "El profesor " + username
					+ "acepta actuar como tutor del TFG propuesto por "
					+ tfg.getAuthor() + "con título " + tfg.getTitle();

			sendMail(author, subject, text);

			resp.sendRedirect("myTFGs");
		} else if (role.equals("secretary")) {
			tfg.setStatus(4);
			dao.updateTFG(tfg);

			String subject = "El secretario " + username
					+ "ha calificado el TFG.";
			String text = "El secretario " + username
					+ "ha calificado el TFG propuesto por " + tfg.getAuthor()
					+ "con título " + tfg.getTitle() + "y tutorizado por "
					+ tfg.getTutor();

			sendMail(author, subject, text);
			sendMail(tfg.getTutor(), subject, text);

			resp.sendRedirect("myTFGs");
		} else
			resp.sendRedirect("myTFGs?error=Solicitud%20Incorrecta,%20por%20favor%20prueba%20de%20nuevo");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
