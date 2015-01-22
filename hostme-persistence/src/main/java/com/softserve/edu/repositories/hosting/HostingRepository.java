package com.softserve.edu.repositories.hosting;

import com.softserve.edu.model.Hosting;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HostingRepository extends PagingAndSortingRepository<Hosting, Integer>{
    public List<Hosting> findByCityLike(String city);
}
