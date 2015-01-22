package com.softserve.edu.controller.search;

import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.service.search.MegaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MegaSearchController {

    private enum SearchTypes {
        EVENT, SIGHTSEEING, ROUTE, HOSTING
    }

    @Autowired
    MegaSearchService megaSearchService;

    @RequestMapping(value = "/megaSearch")
    public String getMegaSearchService(){
        return "megaSearch";
    }

    @RequestMapping(value = "/superMegaSearch")
    public @ResponseBody List<?> getSearchResults(@RequestBody SearchRequestDto searchRequestDto) {
        if (SearchTypes.valueOf(searchRequestDto.getType()).equals(SearchTypes.ROUTE)) {
            return megaSearchService.searchRoutes(searchRequestDto.getRequest());
        }
        return null;
    }
}
