package com.softserve.edu.controller;

import com.google.gson.Gson;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.service.SearchService;
import com.softserve.edu.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search")
	public String homeSearch(Map<String, Object> map) {
		map.put("host", new Hosting());
		return "search";
	}

	    @RequestMapping(value = "/searchhosts",
            method = RequestMethod.GET)
    public
    @ResponseBody
    String searchAll(
            @RequestParam(value = "country", required = false, defaultValue = "") String country,
            @RequestParam(value = "region", required = false, defaultValue = "") String region,
            @RequestParam(value = "pets", required = false, defaultValue = "") String pets,
            @RequestParam(value = "children", required = false, defaultValue = "") String children,
            @RequestParam(value = "smoking", required = false, defaultValue = "") String smoking,
            @RequestParam(value = "family", required = false, defaultValue = "") String family,
            @RequestParam(value = "maxNumberOfGuests", required = false, defaultValue = "") String count,
            @RequestParam(value = "gender", required = false, defaultValue = "UNSPECIFIED") String gender,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
//            @RequestParam(value = "beginDate", required = false, defaultValue = "") String beginDate,
//            @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
            Map<String, Object> map) {
        List<Search> searchList = new ArrayList<>();
        searchList.add(new Search.SearchBuilder()
                .setKey("country")
                .setValue(country)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("region")
                .setValue(region)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("pets")
                .setValue(pets)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("children")
                .setValue(children)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("smoking")
                .setValue(smoking)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("family")
                .setValue(family)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("minNumberOfGuests")
                .setValue(count)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("maxNumberOfGuests")
                .setValue(count)
                .build());
        searchList.add(new Search.SearchBuilder()
                .setKey("gender")
                .setValue(gender)
                .build());
//        searchList.add(new Search.SearchBuilder()
//                .setKey("request.begin_date")
//                .setValue(endDate)
//                .build());
//        searchList.add(new Search.SearchBuilder()
//                .setKey("request.end_date")
//                .setValue(beginDate)
//                .build());
        Integer pages = searchService.getCountOfPages(searchList);

//        final List<Object> hostingDTOs = new LinkedList<>();
        final List<Object> hostings = searchService.getList(searchList, page);
//        for (Hosting hosting : hostings) {
//            hostingDTOs.add(new HostingDTO(hosting, hosting.getOwner()));
//        }
        hostings.add(0, pages);
        Gson gson = new Gson();

        return gson.toJson(hostings);
    }

}
