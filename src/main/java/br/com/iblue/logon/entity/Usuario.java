package br.com.iblue.logon.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tblusuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "seq_usuario")
	@JsonProperty("id-usuario")
	@Column(name = "idusuario")
	private Long idUsuario;
	@JsonProperty("uuid")
	@Column(name = "uuid")
	private String uuid;
	@JsonProperty("username")
	@Column(name = "username")
	private String username;
	@JsonProperty("email")
	@Column(name = "email")
	@Pattern(regexp="([a-z0-9]+)@([a-z]+)\\.([a-z]+)")
	private String email;
	@JsonProperty("password")
	@Column(name = "password")
	@Pattern(regexp ="[a-zA-Z0-9]{6,30}",message="Senha nao definida !!!")
	private String password;
	
	
	public Usuario() {
	}
	
	
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", uuid=" + uuid + ", username=" + username + ", email=" + email
				+ ", password=" + password + "]";
	}


	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public Usuario(@Pattern(regexp = "([a-z0-9]+)@([a-z]+)\\.([a-z]+)") String email,
			@Pattern(regexp = "[a-zA-Z0-9]{6,30}", message = "Senha nao definida !!!") String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public Usuario(Long idUsuario, String uuid, String username,
			@Pattern(regexp = "([a-z0-9]+)@([a-z]+)\\.([a-z]+)") String email,
			@Pattern(regexp = "[a-zA-Z0-9]{6,30}", message = "Senha nao definida !!!") String password) {
		super();
		this.idUsuario = idUsuario;
		this.uuid = uuid;
		this.username = username;
		this.email = email;
		this.password = password;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}

