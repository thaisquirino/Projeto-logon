package br.com.iblue.logon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iblue.logon.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private JavaMailSender senderEmail;

	public Map<String, Object> gerarUuid(Usuario u) {
		Map<String, Object> mapa = new HashMap<String, Object>() {
			{
				u.setUuid(UUID.randomUUID().toString());
				put("uuid-user", u.getIdUsuario());
			}
		};
		return mapa;
	}

	@Override
	public Map<String, Object> criptografia(Usuario u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String resposta = passwordEncoder.encode(u.getPassword());
		Map<String, Object> mapa = new HashMap<String, Object>() {
			{
				put("user-password", resposta);
			}
		};
		return mapa;
	}

	@Override
	public Map<String, Object> enviarEmail(Usuario u) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(u.getEmail());
			msg.setSubject("Recuperacao java dia 25 de dezembro hohoho...");
			msg.setText("Seja Bem vindo a minha loja Virtual SR :" + u.getUsername());
			senderEmail.send(msg);
			// Finish
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("email", "email enviado para :" + u.getEmail());
				}
			};
			return mapa;
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("error-email", "deu ruim no envio, " + u.getEmail());
				}
			};
			return mapa;
		}
	}
}