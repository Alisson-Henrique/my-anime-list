package ifbp.testes.myanimelist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.service.AnimeService;
import ifbp.testes.myanimelist.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private  AnimeService animeService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/")
	public ModelAndView index() {
		List<Anime> totalAnimes = animeService.getPageFiveAnimes();
		User user = userService.findByUsername(getLogin());
		ModelAndView mv = new ModelAndView("animes_list");
		mv.addObject("animesList",totalAnimes);
		mv.addObject(user);
		
		return mv;
	}
	
	private String getLogin(){
		
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		
		return principal.getName();
		
	}
}
