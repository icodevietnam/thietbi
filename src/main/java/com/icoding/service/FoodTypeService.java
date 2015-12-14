package com.icoding.service;

import java.util.List;

import com.icoding.domain.FoodType;

public interface FoodTypeService extends GenericService<FoodType, Integer> {
	List<FoodType> listParent();
	
	List<FoodType> listStoreType();
	
	List<FoodType> listFoodType();
	
	List<FoodType> listChild();
}
