package com.ishanitech.ipalika.utils;

import javax.servlet.http.HttpServletRequest;

import com.ishanitech.ipalika.dto.PaginationTypeClass;

public class CustomQueryCreator {

	private static final String STOCK_FILTER = "";
    private static final Integer LAST_SEEN_ID = 0;
    private static final Integer PAGE_SIZE = 3;
    private static final String ACTION = "first";
    private static final String SEARCH_KEY = "";
    
	// Generates the dynamic query based on filters and query parameters
    public static String generateQueryWithCase(HttpServletRequest request, PaginationTypeClass ptc) {
        String caseQuery = "";
        String stockFilter;
        Integer lastSeenId;
        String action;
        Integer pageSize;
        String searchKey;
        
        if (checkParameter("last_seen", request)) {
            lastSeenId = Integer.parseInt(getParameterFromRequestObject("last_seen", request));
        } else {
            lastSeenId = LAST_SEEN_ID;
        }
        
        if (checkParameter("action", request)) {
            action = (String) getParameterFromRequestObject("action", request);
        } else {
            action = ACTION;
        }
        
        if (checkParameter("pageSize", request)) {
            pageSize = Integer.parseInt(getParameterFromRequestObject("pageSize", request));
        } else {
            pageSize = PAGE_SIZE;
        }

        if (checkParameter("searchKey", request)) {
            searchKey = (String) getParameterFromRequestObject("searchKey", request);
        } else {
            searchKey = SEARCH_KEY;
        }
        
        switch(ptc) {
        	case RESIDENTS:
        		if (checkParameter("stockFilter", request)) {
                    stockFilter = (String) getParameterFromRequestObject("stockFilter", request);
                } else {
                    stockFilter = STOCK_FILTER;
                }
        		switch (stockFilter.toLowerCase()) {
	                case "in-stock":
	                    caseQuery += " AND p.available_items > 0 ";
	                    break;
	
	                case "out-of-stock":
	                    caseQuery += " AND p.available_items < 0 ";
	                    break;
	
	                default:
	                    break;
        		}
        		
        		//a.answer_1 LIKE CONCAT('%', :searchKey, '%')
        		switch (searchKey.toLowerCase()) {
                case "":
                    break;

                default:
                	caseQuery += " AND a.answer_1 LIKE '%" + searchKey + "%'";
                    break;
        		}
        		
        		
        		switch (action.toLowerCase()) {
                case "next":
                    if (lastSeenId > 0) {
                        caseQuery += " AND a.id < " + lastSeenId;
                        caseQuery += " ORDER BY a.id DESC ";
                    }
                    break;
                case "prev":
                    if (lastSeenId > 0) {
                        caseQuery += " AND a.id > " + lastSeenId;
                        caseQuery += " ORDER BY a.id ASC ";
                    }
                    break;
                default:
                    caseQuery += " ORDER BY a.id DESC ";
                    break;
        		}
        		break;
        	case FAV_PLACES:
        		switch(action.toLowerCase()) {
			    	case "next":
			    		if(lastSeenId > 0) {
			    			caseQuery += " AND o.order_id < " + lastSeenId;
			    			caseQuery += " ORDER BY o.order_id DESC ";
			    		}
			    		break;
			    	case "prev":
			    		if(lastSeenId > 0) {
							caseQuery += " AND o.order_id > " + lastSeenId;
							caseQuery += " ORDER BY o.order_id ASC ";
						}
			    		break;
			    	default: 
			    		caseQuery += " ORDER BY o.order_id DESC ";
			    		break;
	        		}
        		break;
        		
        	default: 
        		break;
        }
        caseQuery += "LIMIT " + pageSize;
        return caseQuery;
    }
    
    // This function checks whether there is given parameter in request object
    public static boolean checkParameter(String parameterName, HttpServletRequest request) {
        return (request.getParameter(parameterName) != null ? true : false);
    }

    // This function returns request parameter values as generic type. You have to
    // cast the result in caller object
    @SuppressWarnings("unchecked")
    public static <T> T getParameterFromRequestObject(String parameter, HttpServletRequest request) {
        return (T) request.getParameter(parameter);
    }
}
