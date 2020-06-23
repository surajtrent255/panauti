package com.ishanitech.ipalika.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import com.ishanitech.ipalika.dto.PaginationTypeClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomQueryCreator {

    private static final Integer LAST_SEEN_ID = 0;
    private static final Integer PAGE_SIZE = 10;
    private static final String ACTION = "first";
    private static final String SEARCH_KEY = "";
    private static final String SORT_BY = "";
    private static final String SORT_BY_ORDER = "";
    
	// Generates the dynamic query based on filters and query parameters
    public static String generateQueryWithCase(HttpServletRequest request, PaginationTypeClass ptc) {
        String caseQuery = "";
        Integer lastSeenId;
        String action;
        Integer pageSize;
        String searchKey;
        String wardNo;
        String sortBy;
        String sortByOrder;
        String currentPageNo;
        int currentPage;
        
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
        
        if (checkParameter("wardNo", request)) {
            wardNo = (String) getParameterFromRequestObject("wardNo", request);
        } else {
            wardNo = "";
        }
        
        if (checkParameter("sortBy", request)) {
            sortBy = (String) getParameterFromRequestObject("sortBy", request);
        } else {
            sortBy = "";
        }
        
        if (checkParameter("sortByOrder", request)) {
            sortByOrder = (String) getParameterFromRequestObject("sortByOrder", request);
        } else {
            sortByOrder = "";
        }
        
        if (checkParameter("currentPage", request)) {
            currentPageNo = (String) getParameterFromRequestObject("currentPage", request);
            currentPage = Integer.parseInt(currentPageNo);
        } else {
            currentPageNo = "";
            currentPage = 0;
        }
        
        switch(ptc) {
        	case RESIDENTS:
        		

        		switch (wardNo) {
                case "":
                    break;

                default:
                	caseQuery += " AND a.answer_3 LIKE '" + wardNo + "' ";
                    break;
        		}
        		
        		
        		switch (searchKey) {
                case "":
                    break;

                default:
                	log.info("SearchKey--->Embbzz-->"+ searchKey);
                	if(searchKey.contains("%")) {
                	try {
                	    String result = java.net.URLDecoder.decode(searchKey, StandardCharsets.UTF_8.name());
                	    searchKey = result;
                	} catch (UnsupportedEncodingException e) {
                	    // not going to happen - value came from JDK's own StandardCharsets
                	}
                	}
                	log.info("SearchKey--->Embbzz-->Converted-->"+ searchKey);
                	caseQuery += " AND a.answer_1 LIKE '%" + searchKey + "%'";
                    break;
        		}
        		
        		if(sortBy.equals("")) {
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
        		}else {
        			switch (sortBy.toLowerCase()) {
                    case "ownername":
                            //caseQuery += " AND a.id < " + lastSeenId;
                            caseQuery += " ORDER BY a.answer_1 " + sortByOrder + " ";
                            if(currentPage >= 0) {
                            caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                            }else {
                            	caseQuery += " LIMIT 0";
                            }
                            	
                        break;
                    case "housenumber":
                    	caseQuery += " ORDER BY a.answer_4 " + sortByOrder + " ";
                        if(currentPage >= 0) {
                        caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                        }else {
                        	caseQuery += " LIMIT 0";
                        }
                        break;
                    case "tole":
                    	caseQuery += " ORDER BY a.answer_2 " + sortByOrder + " ";
                        if(currentPage >= 0) {
                        caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                        }else {
                        	caseQuery += " LIMIT 0";
                        }
                        break;
                        
                    case "phonenumber":
                    	caseQuery += " ORDER BY a.answer_5 " + sortByOrder + " ";
                        if(currentPage >= 0) {
                        caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                        }else {
                        	caseQuery += " LIMIT 0";
                        }
                        break;
                        
                    case "kittanumber":
                    	caseQuery += " ORDER BY a.answer_13 " + sortByOrder + " ";
                        if(currentPage >= 0) {
                        caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                        }else {
                        	caseQuery += " LIMIT 0";
                        }
                        break;
                    
                    case "familysize":
                    	caseQuery += " ORDER BY totalFamilyMembers " + sortByOrder + " ";
                        if(currentPage >= 0) {
                        caseQuery += " LIMIT " + currentPage * pageSize + ", " + pageSize ;
                        }else {
                        	caseQuery += " LIMIT 0";
                        }
                        break;
                    default:
                        caseQuery += " ORDER BY a.id DESC ";
                        break;
            		}
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
        if(sortBy.equals("")) {
        caseQuery += "LIMIT " + pageSize;
        }
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