package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.HostingDao;
import com.softserve.edu.service.SearchService;
import com.softserve.edu.utils.Search;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private HostingDao hostingDao;

	@Override
	@Transactional
	public List<Object> getList(List<Search> listSearch, Integer page) {
		return hostingDao.getList(listSearch, page);
	}

    	@Override
    	@Transactional
    	public Integer getCountOfPages(List<Search> listSearch) {
        	return hostingDao.getCountOfPages(listSearch);
    }
}
