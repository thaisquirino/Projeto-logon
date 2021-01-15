package br.com.iblue.logon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.iblue.logon.entity.Usuario;
import br.com.iblue.logon.repository.UsuarioRepository;
import br.com.iblue.logon.service.UsuarioService;
 

@ResponseBody
@RestController
@RequestMapping("/api")
public class UsuarioController {
  
	@Autowired
	UsuarioRepository dao;
	@Autowired
	UsuarioService service;
	
	
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@RequestBody Usuario usuario){
		 try {
			 Map<String, Object>criptografa= service.criptografia(usuario);
			 usuario.setPassword((String) criptografa.get("user-password"));
			 
			 Usuario u = dao.save(usuario);
			 if (u==null) {
			  throw new IllegalArgumentException("Erro no no dado do usuario");
			 }
			 Map<String,Object> mapa =new HashMap<String,Object>(){
				 {
					 put("password",usuario.getPassword());
					 put("usuario-saved",u);
					 put("status","gravado com sucesso");
				 }
			 };
			 return ResponseEntity.status(200).body(mapa);
		 }catch(Exception ex) {
			 Map<String,Object> mapa =new HashMap<String,Object>(){
				{
					 put("usuario-error",ex.getMessage());
					 put("status","nao gravado");
				}
			 };
			 return ResponseEntity.status(500).body(mapa);
	}
	}

	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> findAll(){
		return ResponseEntity.status(200).body(dao.findAll());
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		try{ 
		Usuario usuario = dao.findById(id).get();
		 if (usuario==null) {
			  throw new IllegalAccessException("nao encontrado");
		 }
		 return ResponseEntity.status(200).body(usuario);
		}catch(Exception ex) {
			 Map<String,Object> mapa = new HashMap<String,Object>();
			 mapa.put("error-find", ex.getMessage());
			 return ResponseEntity.status(404).body(mapa);
		}
	}
	
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update( @PathVariable("id") Long id,
			                         @RequestBody Usuario usuario){
		try{ 
		Usuario u = dao.findById(id).get();
		 if (u==null) {
			  throw new IllegalAccessException("nao encontrado");
		 }
		  u.setUsername(usuario.getUsername());
		  u.setPassword(usuario.getPassword());
		  u.setEmail(usuario.getEmail());
		  Usuario resp = dao.save(u);
		 return ResponseEntity.status(200).body(resp);
		}catch(Exception ex) {
			 Map<String,Object> mapa = new HashMap<String,Object>();
			 mapa.put("error-find", ex.getMessage());
			 return ResponseEntity.status(404).body(mapa);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> update( @PathVariable("id") Long id){
		try{ 
		Usuario u = dao.findById(id).get();
		 if (u==null) {
			  throw new IllegalAccessException("nao encontrado");
		 }
		 dao.delete(u);
		 Map<String,Object> mapa = new HashMap<String,Object>();
		 mapa.put("msg-exclusao", "Dados Excluidos com sucesso");
		 return ResponseEntity.status(200).body(mapa);
		}catch(Exception ex) {
			 Map<String,Object> mapa = new HashMap<String,Object>();
			 mapa.put("error-find", ex.getMessage());
			 return ResponseEntity.status(404).body(mapa);
		}
	}
}
	
	
	
