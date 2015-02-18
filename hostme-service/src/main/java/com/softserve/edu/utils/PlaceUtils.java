package com.softserve.edu.utils;

import com.softserve.edu.model.routes.Place;
import java.util.Collection;

public class PlaceUtils {

    public static int countAvePrice(Collection<Place> places) {
        int result = 0;
        for (Place pc : places) {
            result += pc.getPriceCategory().getPriceCategoryId();
        }
        result /= places.size();
        return result;
    }
    
    public static int countAveRating(Collection<Place> places) {
        int result = 0;
        for (Place pc : places) {
            result += pc.getRating();
        }
        result /= places.size();
        return result;
    }
}
