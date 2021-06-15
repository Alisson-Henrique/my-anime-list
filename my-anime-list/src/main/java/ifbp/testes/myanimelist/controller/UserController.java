package ifbp.testes.myanimelist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.AnimesUser;
import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.service.AnimesUserService;
import ifbp.testes.myanimelist.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AnimesUserService animesUserService;

	/**
	 * @RequestMapping(method = RequestMethod.POST) public ModelAndView login(User
	 *                        user) { User userBanco = null; try { userBanco =
	 *                        userservice.login(user); } catch (Exception e) { //
	 *                        TODO Auto-generated catch block e.printStackTrace(); }
	 *                        ModelAndView mv = new ModelAndView("animes_list");
	 *                        mv.addObject(userBanco);
	 * 
	 *                        return mv; }
	 **/
	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
	public String login() {

		return "login";

	}

	@RequestMapping("/user/signUp")
	public ModelAndView signUp() {
		ModelAndView mv = new ModelAndView("user_registration");
		mv.addObject(new User());
		return mv;

	}

	@RequestMapping(value = "/user/signUp", method = RequestMethod.POST)
	public String newUser(@Valid User user, Errors errors,  RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "user_registration";
		}
		
		
		try {
			userService.save(user);
		} catch (Exception e) {

		}
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/user/{username}")
	public ModelAndView listAnimesUser(@PathVariable String username) {
		ModelAndView mv = new ModelAndView("user_anime_list");
		User user = userService.findByUsername(username);
		List<AnimesUser> totalAnimes = animesUserService.getAnimesUser(username);
		mv.addObject(user);
		mv.addObject("animesUser", totalAnimes);
		return mv;
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/user/myanimelist")
	public ModelAndView listAnimesUser() {
		ModelAndView mv = new ModelAndView("user_anime_list");
		User user = userService.findByUsername(getLogin());
		List<AnimesUser> totalAnimes = animesUserService.getAnimesUser(user.getUsername());
		mv.addObject(user);
		mv.addObject("animesUser", totalAnimes);
		return mv;
	}

	private String getLogin(){

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();

		return principal.getName();

	}
}
