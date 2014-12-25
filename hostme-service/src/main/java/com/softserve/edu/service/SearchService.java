package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.utils.Search;

public interface SearchService {

    public List<Object> getList(List<Search> listSearch, Integer page);

    public Integer getCountOfPages(List<Search> listSearch);

}
