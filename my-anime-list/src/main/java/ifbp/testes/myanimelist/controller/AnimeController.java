package ifbp.testes.myanimelist.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.AnimesUser;
import ifbp.testes.myanimelist.model.Score;
import ifbp.testes.myanimelist.model.State;
import ifbp.testes.myanimelist.model.StatusAnime;
import ifbp.testes.myanimelist.model.User;

import ifbp.testes.myanimelist.service.AnimeService;
import ifbp.testes.myanimelist.service.AnimesUserService;

@Controller
@RequestMapping("/anime")
public class AnimeController {

	private static String imagePath = "C:/Users/torga/Desktop/images/";
	
	private String animeAvaliation;

	@Autowired
	private AnimeService animeService;

	@Autowired
	private AnimesUserService animeUserService;

	@RequestMapping("/newAnime")
	public ModelAndView animeRegistration() {

		ModelAndView mv = new ModelAndView("anime_registration");

		mv.addObject(new Anime());
		return mv;
	}
	
	@GetMapping("/info/exibir/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
	
		File imagemArquivo = new File(imagePath + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
		
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	@GetMapping("/info/animesUser/{id}")
	@ResponseBody
	public byte[] retornarImagemAnimeUser(@PathVariable("id") String id) throws IOException {
		Anime anime = animeService.findByName(id);
		String imagem = anime.getImgPath();
		
		File imagemArquivo = new File(imagePath + imagem);
		
		if (imagem != null || imagem.trim().length() > 0) {
			
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/delete/evaluation/{id}")
	public String deleteEvaluation(@PathVariable("id") long id) {
		
		try {
			animeUserService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	

	@RequestMapping(value = "/newAnime", method = RequestMethod.POST)
	public String newAnime(@Valid Anime anime, Errors errors, RedirectAttributes attributes,
			@RequestParam("file") MultipartFile arquivo) {

		if (errors.hasErrors()) {
			return "anime_registration";
		}

		Anime animeImg = animeService.save(anime);
		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(imagePath + String.valueOf(animeImg.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				animeImg.setImgPath(String.valueOf(animeImg.getId()) + arquivo.getOriginalFilename());
				animeService.save(anime);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		attributes.addFlashAttribute("mensagem", "Anime salvo com sucesso!");
		return "redirect:/anime/newAnime";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("{id}")
	public ModelAndView editionAnime(@PathVariable("id") Long id) {

		Anime anime = animeService.getOne(id);
		ModelAndView mv = new ModelAndView("anime_registration");
		mv.addObject(anime);
		return mv;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteAnime(@PathVariable("id")long id) {
		
		try {
			animeService.deleteAnimeById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/animes_user")
	public ModelAndView listAnimesUser(User user) {
		System.out.println(user.getUsername());
		List<Anime> totalAnimes = animeService.getPageFiveAnimes();
		ModelAndView mv = new ModelAndView("user_anime_list");
		mv.addObject("animesList", totalAnimes);

		return mv;

	}

	@ModelAttribute("animeStatus")
	public List<StatusAnime> statusAnimes() {

		return Arrays.asList(StatusAnime.values());
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/info/{id}")
	public ModelAndView animeInfo(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("modal_add_anime");

		Anime anime = animeService.findById(id);
		animeAvaliation = anime.getName();
		mv.addObject(anime);
		AnimesUser animeUser = new AnimesUser();
		animeUser.setAnime(anime.getName());
		mv.addObject("animeUser", animeUser);
		return mv;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/info/evaluation", method = RequestMethod.POST)
	public String saveEvaluation(AnimesUser animeUser) {

		animeUser.setUsername(getLogin());
		animeUser.setAnime(animeAvaliation);
		
		AnimesUser animeUserTeste = null;
		animeUserTeste = animeUserService.findAnimesUserByAnime(animeAvaliation);
		if( animeUserTeste == null) {
			animeUserService.save(animeUser);
		}else {
			animeUserService.update(animeUserTeste.getId(), animeUser);
		}

		System.out.println("Salvou " + animeUser.getAnime());
		return "redirect:/";
	}

	private String getLogin() {

		Authentication principal = SecurityContextHolder.getContext().getAuthentication();

		return principal.getName();

	}

	@ModelAttribute("state")
	public List<State> stateAnime() {

		return Arrays.asList(State.values());
	}

	@ModelAttribute("score")
	public List<Score> scoreAnime() {

		return Arrays.asList(Score.values());
	}
	

	@RequestMapping("/f")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
	
	
	
}
