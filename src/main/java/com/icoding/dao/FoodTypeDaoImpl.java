package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.icoding.domain.FoodType;

@Repository
public class FoodTypeDaoImpl extends GenericDaoImpl<FoodType, Integer>implements FoodTypeDao {

	@Override
	public List<FoodType> listParent() {
		List<FoodType> foodTypeList = new ArrayList<FoodType>();
		Query query = currentSession().createQuery("from FoodType f where f.foodType is null");
		foodTypeList = query.list();
		return foodTypeList;
	}

	@Override
	public List<FoodType> listStoreType() {
		List<FoodType> foodTypeList = new ArrayList<FoodType>();
		Query query = currentSession().createQuery("from FoodType f where f.type = '0' ");
		foodTypeList = query.list();
		return foodTypeList;
	}
	
	public List<FoodType> listFoodType() {
		List<FoodType> foodTypeList = new ArrayList<FoodType>();
		Query query = currentSession().createQuery("from FoodType f where f.type = '1' ");
		foodTypeList = query.list();
		return foodTypeList;
	}

	@Override
	public List<FoodType> listChild() {
		List<FoodType> foodTypeList = new ArrayList<FoodType>();
		Query query = currentSession().createQuery("from FoodType f where f.foodType is not null");
		foodTypeList = query.list();
		return foodTypeList;
	}

}
