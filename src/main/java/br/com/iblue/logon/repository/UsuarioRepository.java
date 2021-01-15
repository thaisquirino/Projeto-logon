package br.com.iblue.logon.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.iblue.logon.entity.Usuario;

 
@Repository
	public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	

	    public Usuario findByEmailAndPassword(String email, String password);
	    public List<Usuario> findByUsername(String username);
	 

}
