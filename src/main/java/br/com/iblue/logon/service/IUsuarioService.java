package br.com.iblue.logon.service;

import java.util.Map;

import br.com.iblue.logon.entity.Usuario;

public interface IUsuarioService {
	
	public Map<String,Object> gerarUuid(Usuario u);
	public Map<String,Object> criptografia (Usuario u);
	public Map<String,Object> enviarEmail(Usuario u);
}


