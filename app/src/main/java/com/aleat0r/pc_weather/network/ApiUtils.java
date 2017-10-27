package com.aleat0r.pc_weather.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class ApiUtils {

    public static Map getQueryParams(double longitude, double latitude) {
        Map params = getGeneralQueryParams();
        params.put(ApiConstants.QUERY_NAME_LONGITUDE, longitude);
        params.put(ApiConstants.QUERY_NAME_LATITUDE, latitude);
        return params;
    }

    private static Map getGeneralQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put(ApiConstants.QUERY_NAME_APP_ID, ApiConstants.API_KEY);
        params.put(ApiConstants.QUERY_NAME_UNITS_OF_MEASURE, ApiConstants.METRIC_UNITS_OF_MEASURE);
        return params;
    }

}
