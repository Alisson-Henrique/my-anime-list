package ifbp.testes.myanimelist.unidade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.validations.UserValidations;

@ExtendWith(MockitoExtension.class)
public class UserTests {

	@Spy
	static UserValidations validation;
	@Spy
	User user;

	@Test
	void testeNomeEntradaLimiteUsurioEntrada() {
		
		assertFalse(validation.validateNameUser(user, "ad") == true);
		
		assertFalse(validation.validateNameUser(user, "Red Wacky League Antlez Broke the Stereo Neon Tide Bring Back"));
		
		//user.setUsername("Torgate");
		//assertFalse(validation.validarNomeUsuarioDuplicacao(user));
	}
	
	@Test
	void testeEntradaSenha(){
		
		
		assertTrue(validation.validarSenha("Starz225"));
		
		assertFalse(validation.validarSenha("H2O"));
		
		assertFalse(validation.validarSenha("arroz"));
		
		assertFalse(validation.validarSenha("40028922"));
		
		assertFalse(validation.validarSenha("abcdefghijklmnopkrstuvwxyz12345"));
		
		assertFalse(validation.validarSenha("Alisson Henrique1256"));
		
	}
	@Test
	void testeEmail() {
		
		assertTrue(validation.validarEmail("torgatesousa@gmail.com"));
		
		assertFalse(validation.validarEmail("abcdefghijklmnop"));
		
	}
	
	@Test
	void TesteDataUser() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = sdf.parse("03/07/2000");
		assertTrue(validation.validarDataNascimentoUsuario(date));
		
		date = sdf.parse("10/05/2030");
		assertFalse(validation.validarDataNascimentoUsuario(date));
		
		date = sdf.parse("01/01/2019");
		assertFalse(validation.validarDataNascimentoUsuario(date));
		
	}
	
	
}
