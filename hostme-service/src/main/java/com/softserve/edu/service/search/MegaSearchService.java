package com.softserve.edu.service.search;

import com.softserve.edu.dto.*;

import java.util.List;

public interface MegaSearchService {

    public List<RouteDto> searchRoutes(SearchRequestDto searchRequestDto);

    public List<EventDto> searchEvents(SearchRequestDto input);

    public List<UserDto> searchUsers(SearchRequestDto input);

    public List<SightseeingDto> searchSights(SearchRequestDto searchRequestDto);

    public List<GroupDto> searchGroups(SearchRequestDto searchRequestDto);

    public List<HostingDto> searchHosting(String input);
}
