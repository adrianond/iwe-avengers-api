package com.iwe.avengers.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDao {
	
	private Map<String, Avenger> mapper = new HashMap<String, Avenger>();
	
	public AvengerDao() {
		mapper.put("aaaa-bbbb-cccc-dddd", new Avenger("aaaa-bbbb-cccc-dddd", "Captain America", "Steve Rogres"));
		mapper.put("aaaa-bbbb-cccc-dddd", new Avenger("aaaa-bbbb-cccc-dddd", "Hulk", "Bruce Banner"));
	}
	

	public Avenger search(String id) {
		return mapper.get(id);
	}

}
