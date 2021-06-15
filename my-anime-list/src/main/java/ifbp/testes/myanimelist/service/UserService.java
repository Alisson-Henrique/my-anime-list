package ifbp.testes.myanimelist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.repository.UserRepository;
import ifbp.testes.myanimelist.validations.AnimeValidations;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) throws Exception{
		String passwordBC = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPasswordUser(passwordBC);
		return userRepository.save(user);
	}

	public User findById(Long id) throws Exception {

		return userRepository.findById(id).orElseThrow();
	}

	public void deleteUserById(Long id) throws Exception{

		User user = findById(id);

		userRepository.delete(user);

	}
	
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);

	}

	public List<User> findAllPage() {

		Page<User> page = userRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC,"name")));
		List<User> list = page.getContent();

		return list;
	}

	public User findByUsername(String string) {
		
		return userRepository.findByUsername(string);
	}
	
	
	public String getLogin() {

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();

		return principal.getName();

	}

}
