package com.icoding.dao;

import java.util.List;

import com.icoding.domain.FoodType;

public interface FoodTypeDao extends GenericDao<FoodType, Integer> {
	List<FoodType> listParent();
	
	List<FoodType> listStoreType();
	
	List<FoodType> listFoodType();
	
	List<FoodType> listChild();
}
