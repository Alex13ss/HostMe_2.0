package com.softserve.edu.controller.search;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.SearchDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MegaSearchController {

    @RequestMapping(value = "/megaSearch")
    public String getMegaSearch(){
        return "megaSearch";
    }

    @RequestMapping(value = "/superMegaSearch")
    public @ResponseBody List<PlaceDto> getSearchResults(@RequestBody SearchDto searchDto) {
        System.out.println(searchDto.getRequest());
        System.out.println(searchDto.getType());
        return new ArrayList<>();
    }
}
