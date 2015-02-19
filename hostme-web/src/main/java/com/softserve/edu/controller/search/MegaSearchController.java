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

import java.util.List;

@Controller
public class MegaSearchController {

    private enum SearchTypes {
        HOSTING, EVENT, SIGHT, ROUTE, GROUPS, USER
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
    @ResponseBody
    public List<City> getCities(@RequestBody String cityName) {
        return cityService.searchCitiesByName(cityName.replace("=", ""));
    }

    @RequestMapping(value = "/superMegaSearch")
    @ResponseBody
    public List<?> getSearchResults(@RequestBody SearchRequestDto searchRequestDto) {
        SearchTypes searchType = SearchTypes.valueOf(searchRequestDto.getType());
        switch (searchType) {
            case HOSTING: {
                return megaSearchService.searchHosting(searchRequestDto);
            }
            case ROUTE: {
                return megaSearchService.searchRoutes(searchRequestDto);
            }
            case EVENT: {
                return megaSearchService.searchEvents(searchRequestDto);
            }
            case SIGHT: {
                return megaSearchService.searchSights(searchRequestDto);
            }
            case USER: {
                return megaSearchService.searchUsers(searchRequestDto);
            }
            case GROUPS: {
                return megaSearchService.searchGroups(searchRequestDto);
            }
            default: {
                return null;
            }
        }
    }

    @RequestMapping(value = "/searchType")
    @ResponseBody
    public SearchTypes[] setSearchTypes(){
        return SearchTypes.values();
    }
}
