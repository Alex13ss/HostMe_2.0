package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.SightseeingType;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.SightseeingRepository;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.CountryService;
import com.softserve.edu.service.ImageService;
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
	private SightseeingRepository sightseeingRepository;

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
		return "redirect:/sightseeings";
	}

	@RequestMapping(value = "/all-sightseeings", params = { "page", "size",
			"orderBy", "orderType" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SightseeingDto> getAllSightseeings(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size") Integer size,
			@RequestParam(value = "orderBy") String orderBy,
			@RequestParam(value = "orderType") String orderType) {
		List<SightseeingDto> sightseeings = sightseeingService
				.getAllSightseeingsPaging(page, size, orderBy, orderType);
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
		return sightseeingService.getFavouriteSightseeingsPaging(liker, page,
				size, orderBy, orderType);
	}

	@RequestMapping(value = "/pagingSightseeings", params = { "size", "sender" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Long getPaging(
			@RequestParam(value = "size") Long size,
			@RequestParam(value = "sender") String sender) {
		User liker = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		return sightseeingService.getSightseeingsPaging(size, sender, liker);
	}

	@RequestMapping(value = "/sightseeing", method = RequestMethod.GET)
	public String showSightseeing(@RequestParam("id") int id, Model model) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		model.addAttribute("sightseeing", sightseeing);
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
		return "redirect:/sightseeing?id={id}";
	}

	@RequestMapping(value = "/addPhotosToSight", method = RequestMethod.POST)
	public String addPhotoToSight(@RequestParam("file") MultipartFile[] files,
			@ModelAttribute("sightseeing") final Sightseeing sightseeing,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("id", sightseeing.getId())
				.addFlashAttribute("sightseeingEdited", true);
		imageService.addImagesToSightseeing(files, sightseeing);
		return "redirect:/sightseeing?id={id}";
	}

	@RequestMapping("/sightseeing/delete/{id}")
	public String deleteSightseeing(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		sightseeingService.deleteSightseeing(sightseeing);
		return "redirect:/sightseeings";
	}

	@RequestMapping("/like/{id}")
	public String addToFavourite(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		User user = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		sightseeingService.saveLikerforSightseing(user, sightseeing);
		return "redirect:/sightseeing?id={id}";
	}

	@RequestMapping("/unlike/{id}")
	public String removeFromInteresting(@PathVariable("id") Integer id) {
		User liker = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		sightseeingService.unlikeSightseeing(id, liker);
		return "redirect:/sightseeing?id={id}";
	}
}
