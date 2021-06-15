package ifbp.testes.myanimelist.validations;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.service.UserService;

@Controller
public class UserValidations {
	
	@Autowired
	private UserService userService;
	

	public boolean validateNameUser(User newUser, String userName) {
		if (userName.length() >= 3 && userName.length() <= 40) {
			newUser.setUsername(userName);
			return true;
		}
		return false;
	}

	public boolean validarEmail(String email){
		String regex = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
		if(email.matches(regex)==false){
			return false;
		}
		return true;

	}

	public boolean validarSenha(String entradaParaValidar){

		int numeros = 0;
		int letras = 0;
		for(int cont = 0;cont<entradaParaValidar.length();cont++){
			
			char c = entradaParaValidar.charAt(cont);
			
			if(c == ' '){
				
				return false;
			}
			
			if(Character.isDigit(c)==true){
				numeros++;;
			}
			if(Character.isAlphabetic(c)==true){
				letras ++;
			}
		}
		if(numeros>=1 && letras > 1 &&entradaParaValidar.length() <= 25 && entradaParaValidar.length() >= 4)
			return true;

		return false;
	}

	public boolean validarNomeUsuarioDuplicacao(User newUser) {
		
		User user = userService.findByUsername(newUser.getUsername());
		
		if(user == null) {
			return true;
		}
		
		return false;
	}
	
	public boolean validarDataNascimentoUsuario(Date date) {
		Date cDate = new Date(System.currentTimeMillis());
		
		if(date.after(cDate)) {
			return false;
		}
		
		
		
		
		LocalDate dateUser = Instant.ofEpochMilli(date.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		LocalDate currentDate = Instant.ofEpochMilli(cDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		Period periodo = Period.between(dateUser, currentDate);
		
		if(periodo.getYears() < 10) {
			return false;
		}
		
		return true;
	}
	
	
}
