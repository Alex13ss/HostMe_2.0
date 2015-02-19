package com.softserve.edu.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.PostDto;
import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;
import com.softserve.edu.model.Post;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.SightseeingType;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.CountryService;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.PostService;
import com.softserve.edu.service.PriceCategoryService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.SightseeingService;

@Controller
public class SightseeingController {

	@Autowired
	private SightseeingService sightseeingService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	@Autowired
	private PriceCategoryService priceCategoryService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private PostService postService;
	
	public static final String REDIRECT_SIGHTSEEING_ID_VALUE = "redirect:/sightseeing?id={id}";
	public static final String REDIRECT_SIGHTSEEINGS = "redirect:/sightseeings";
	
	@RequestMapping(value = "/sightseeings", method = RequestMethod.GET)
	public String showSightseeings(Model model) {
		model.addAttribute("sightseeing", new Sightseeing());
		return "sightseeings";
	}

	@RequestMapping(value = "/getPriceCategories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PriceCategory> getAllPriceCategories() {
		List<PriceCategory> priceCategories = priceCategoryService
				.getAllPriceCategory();
		return priceCategories;
	}

	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Country> getAllCountries() {
		List<Country> countries = countryService.getAllCountry();
		return countries;
	}

	@RequestMapping(value = "/getAllTypes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SightseeingType[] getAllTypes() {
		SightseeingType[] types = SightseeingType.values();
		return types;
	}

	@RequestMapping(value = "/sightseeings", method = RequestMethod.POST)
	public String addSightseeing(Model model,
			@ModelAttribute("sightseeing") Sightseeing sightseeing) {
		String priceCategory = sightseeing.getPriceCategory()
				.getPriceCategory();
		String city = sightseeing.getCity().getCity();
		sightseeingService.saveSightseeing(sightseeing, priceCategory, city);
		return REDIRECT_SIGHTSEEINGS;
	}

	@RequestMapping(value = "/all-sightseeings", params = { "page", "size",
			"orderBy", "orderType" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SightseeingDto> getAllSightseeings(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size,
			@RequestParam(value = "orderBy") String orderBy,
			@RequestParam(value = "orderType") String orderType) {
		List<SightseeingDto> sightseeings = sightseeingService
				.getAllSightseeings(page, size, orderBy, orderType);
		return sightseeings;
	}

	@RequestMapping(value = "/favourite-sightseeings", params = { "page",
			"size", "orderBy", "orderType" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SightseeingDto> getFavouriteSightseeings(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size,
			@RequestParam(value = "orderBy") String orderBy,
			@RequestParam(value = "orderType") String orderType) {
		User liker = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		return sightseeingService.getFavouriteSightseeings(liker, page, size,
				orderBy, orderType);
	}

	@RequestMapping(value = "/my-sightseeings", params = { "page", "size",
			"orderBy", "orderType" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SightseeingDto> getMySightseeings(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size,
			@RequestParam(value = "orderBy") String orderBy,
			@RequestParam(value = "orderType") String orderType) {
		List<SightseeingDto> sightseeings = sightseeingService
				.getSightseeingByOwner(page, size, orderBy, orderType);
		return sightseeings;
	}

	@RequestMapping(value = "/pagingSightseeings", params = { "size", "sender" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Long getPaging(
			@RequestParam(value = "size") Long size,
			@RequestParam(value = "sender") String sender) {
		User currentUser = profileService.getCurrentUser();
		return sightseeingService.getSightseeingsPaging(size, sender,
				currentUser);
	}

	@RequestMapping(value = "/sightseeing", method = RequestMethod.GET)
	public String showSightseeing(@RequestParam("id") int id, Model model) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		model.addAttribute("sightseeing", sightseeing);
		model.addAttribute("rating", sightseeingService.getCurrentRating(id));
		List<Country> countries = countryService.getAllCountry();
		List<City> cities = cityService.getAllCity();
		List<PriceCategory> priceCategories = priceCategoryService
				.getAllPriceCategory();
		User liker = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		boolean isFavourite = sightseeingService.favouriteCheck(sightseeing,
				liker);
		model.addAttribute("sightseeing", sightseeing);
		model.addAttribute("sType", SightseeingType.values());
		model.addAttribute("status", Status.values());
		model.addAttribute("countries", countries);
		model.addAttribute("cities", cities);
		model.addAttribute("priceCategories", priceCategories);
		model.addAttribute("isFavourite", isFavourite);
		return "sightseeing";
	}

	@RequestMapping(value = "/sightseeing", method = RequestMethod.POST)
	public String editSightseeing(
			@ModelAttribute("sightseeing") final Sightseeing sightseeing,
			RedirectAttributes redirectAttributes) {
		String priceCategory = sightseeing.getPriceCategory()
				.getPriceCategory();
		String city = sightseeing.getCity().getCity();
		sightseeingService.updateSightseeing(sightseeing, priceCategory, city);
		redirectAttributes.addAttribute("id", sightseeing.getId())
				.addFlashAttribute("sightseeingEdited", true);
		return REDIRECT_SIGHTSEEING_ID_VALUE;
	}

	@RequestMapping(value = "/addPhotosToSight", method = RequestMethod.POST)
	public String addPhotoToSight(@RequestParam("file") MultipartFile[] files,
			@ModelAttribute("sightseeing") final Sightseeing sightseeing,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("id", sightseeing.getId())
				.addFlashAttribute("sightseeingEdited", true);
		imageService.addImagesToSightseeing(files, sightseeing);
		return REDIRECT_SIGHTSEEING_ID_VALUE;
	}

	@RequestMapping(value = "/findComments.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PostDto> findPostsJson(
			@RequestParam(value = "placeId") Integer id) {
		return postService.findByPlaceId(id);
	}

	@RequestMapping(value = "/sendComment", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PostDto> sendPost(
			@RequestParam(value = "placeId") Integer id,
			@RequestParam(value = "message") String message) {
		Post post = new Post();
		post.setAuthor(profileService.getCurrentUser());
		post.setContent(message);
		post.setPlace(sightseeingService.findOne(id));
		post.setPostedAt(Calendar.getInstance());
		postService.save(post);
		return findPostsJson(id);
	}

	@RequestMapping(value = "/sightseeing-update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Sightseeing updateSightseeingStatus(
			@RequestBody Sightseeing sightseeing) {
		String priceCategory = sightseeing.getPriceCategory()
				.getPriceCategory();
		Sightseeing newSightseeing = sightseeingService.findOne(sightseeing
				.getId());
		String city = newSightseeing.getCity().getCity();
		newSightseeing.setStatus(Status.valueOf(sightseeing.getStatus().toString().toUpperCase()));
		sightseeingService.updateSightseeing(newSightseeing, priceCategory,
				city);
		return sightseeing;
	}

	@RequestMapping("/sightseeing/delete/{id}")
	public String deleteSightseeing(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		sightseeingService.deleteSightseeing(sightseeing);
		return REDIRECT_SIGHTSEEINGS;
	}

	@RequestMapping("/like/{id}")
	public String rateSightseeing(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		User user = profileService.getCurrentUser();
		if (sightseeingService.favouriteCheck(sightseeing, user)) {
			sightseeingService.unlikeSightseeing(id, user);
		} else {
			sightseeingService.saveLikerforSightseing(user, sightseeing);
		}
		return REDIRECT_SIGHTSEEING_ID_VALUE;
	}
}
