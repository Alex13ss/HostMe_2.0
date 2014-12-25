package com.softserve.edu.service;

import com.softserve.edu.utils.Search;

import java.util.List;

public interface SearchService {

    public List<Object> getList(List<Search> listSearch, Integer page);

    public Integer getCountOfPages(List<Search> listSearch);

}
