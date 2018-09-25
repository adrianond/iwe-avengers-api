package com.iwe.avengers.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDao {
	
	private Map<String, Avenger> mapper = new HashMap<String, Avenger>();
	
	public AvengerDao() {
		mapper.put("aaaa-bbbb-cccc-dddd", new Avenger("aaaa-bbbb-cccc-dddd", "Captain America", "Steve Rogers"));
		mapper.put("aaaa-aaaa-aaaa-aaaa", new Avenger("aaaa-aaaa-aaaa-aaaa", "Hulk", "Bruce Banner"));
	}
	
    
	/**
     * 
     * @param id
     * @return
     */
	public Avenger search(String id) {
		return mapper.get(id);
	}

    /**
     * 
     * @param avenger
     * @return
     */
	public Avenger update(Avenger avenger) {
		return mapper.replace(avenger.getId(), avenger);
	}
	
	public Avenger create(Avenger avenger) {
		String id = "IssoFoiGerado";
		avenger.setId(id);
		mapper.put(id, avenger);
		return avenger;
	}
	
	public void remove(Avenger avenger) {
		mapper.remove(avenger.getId());
	}
}
