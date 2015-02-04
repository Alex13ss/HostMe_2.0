package com.softserve.edu.controller.search;

import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.model.City;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.search.MegaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class MegaSearchController {

    private enum SearchTypes {
        EVENT, SIGHT, HOSTING, ROUTE, GROUPS, USER
    }

    @Autowired
    MegaSearchService megaSearchService;

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/megaSearch")
    public String getMegaSearchService(){
        return "megaSearch";
    }

    @RequestMapping(value = "/getCities", method = RequestMethod.POST)
    public @ResponseBody List<City> getCities(@RequestBody String cityName) {
        return cityService.searchCitiesByName(cityName.replace("=", ""));
    }

    @RequestMapping(value = "/superMegaSearch")
    public @ResponseBody List<?> getSearchResults(@RequestBody SearchRequestDto searchRequestDto) {
        if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.ROUTE)) {
            return megaSearchService.searchRoutes(searchRequestDto);
        } else if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.EVENT)) {
            return megaSearchService.searchEvents(searchRequestDto);
        } else if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.SIGHT)) {
            return megaSearchService.searchSights(searchRequestDto);
        } else if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.HOSTING)) {
            return megaSearchService.searchHosting(searchRequestDto.getRequest());
        } else if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.USER)) {
            return megaSearchService.searchUsers(searchRequestDto);
        } else if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.GROUPS)) {
            return megaSearchService.searchGroups(searchRequestDto);
        }
        return null;
    }

    @RequestMapping(value = "/searchType")
    public @ResponseBody SearchTypes[] setSearchTypes(){
        return SearchTypes.values();
    }
}
