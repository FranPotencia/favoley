package com.mkyong.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mkyong.model.Usuario;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmailEquipo(Usuario usuario) throws Exception {

		try {
			String emails[] = { usuario.getUsername() };
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			helper.setTo(emails);
			helper.setText("Buenas, hemos resuelto su solicitud del equipo. "
					+ "Podrá acceder al portal telemático y comprobar su información. Usuario: " + usuario.getUsername()
					+ " Contraseña: " + usuario.getUsername(), false);
			helper.setSubject("Resolución equipo FAVB");
			emailSender.send(message);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void sendEmailArbitro(Usuario usuario) throws Exception {

		try {
			String emails[] = { usuario.getUsername() };
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			helper.setTo(emails);
			helper.setText("Buenas, hemos resuelto su solicitud de árbitro. "
					+ "Podrá acceder al portal telemático y comprobar su información. Usuario: " + usuario.getUsername()
					+ " Contraseña: " + usuario.getUsername(), false);
			helper.setSubject("Resolución árbitro FAVB");
			emailSender.send(message);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}