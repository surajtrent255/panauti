/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 18, 2020
 */
package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.converter.impl.FavouritePlaceConverter;
import com.ishanitech.ipalika.converter.impl.WardConverter;
import com.ishanitech.ipalika.dao.FavouritePlaceDAO;
import com.ishanitech.ipalika.dao.WardDAO;
import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.dto.WardDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.FavouritePlace;
import com.ishanitech.ipalika.model.Ward;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.servicer.WardService;

@Service
public class WardServiceImpl implements WardService {
	private final DbService dbService;
	
	public WardServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public List<Integer> getAllWardNumbers() {
		 return dbService.getDao(WardDAO.class).getAllWardNumbers();
	}

	@Override
	public void addWard(WardDTO wardInfo) {
		Ward ward = new WardConverter().fromDto(wardInfo);
		
		try {
			dbService.getDao(WardDAO.class).addWard(ward);
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception :" + jex.getLocalizedMessage());
		}
	}

	@Override
	public void updateWardInfoByWardNumber(WardDTO wardInfo, int wardNo) {
		Ward ward = new WardConverter().fromDto(wardInfo);
		
		try {
			dbService.getDao(WardDAO.class).updateWardInfoByWardNumber(ward, wardNo);
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}

	@Override
	public void deleteWardByWardNumber(int wardNo) {
		dbService.getDao(WardDAO.class).deleteWardByWardNumber(wardNo);		
	}

	@Override
	public WardDTO getWardByWardNumber(int wardNo) {
		WardDTO ward = new WardDTO();
		
		try {
			Ward wardInfo = dbService.getDao(WardDAO.class)
					.getWardByWardNumber(wardNo);
			ward = new WardConverter().fromEntity(wardInfo);
			return ward;
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception : " + jex.getLocalizedMessage());
		}
	}

	@Override
	public List<WardDTO> getAllwardsInfo() {
		List<WardDTO> wards = new ArrayList<>();
		
		try {
			List<Ward> wardInfo = dbService.getDao(WardDAO.class).getAllWardsInfo();
			wards = new WardConverter().fromEntity(wardInfo);
			return wards;
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getLocalizedMessage());
		}
	}

	@Override
	public Integer getHouseCountByWard(int wardNo) {
		
		Integer totalHouseCount = dbService.getDao(WardDAO.class).getTotalHouseCountByWard(wardNo);
		
		return totalHouseCount;
	}

}
