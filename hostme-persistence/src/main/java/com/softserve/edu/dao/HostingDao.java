package com.softserve.edu.dao;

import com.softserve.edu.entity.Gender;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.utils.Search;

import java.util.List;

public interface HostingDao extends GenericDao<Hosting, Integer> {

    public List<Object> getList(List<Search> listSearch, Integer page);
    
    public Gender parsingGender(String gender);
    
    public Integer getCountOfPages(List<Search> listSearch);    
}
