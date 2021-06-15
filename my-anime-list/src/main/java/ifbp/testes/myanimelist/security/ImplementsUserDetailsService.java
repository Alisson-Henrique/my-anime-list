package ifbp.testes.myanimelist.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
///import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ifbp.testes.myanimelist.repository.UserRepository;

@Repository
@Transactional
//@Component
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		ifbp.testes.myanimelist.model.User usuario = ur.findByUsername(login);
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}
	
}
