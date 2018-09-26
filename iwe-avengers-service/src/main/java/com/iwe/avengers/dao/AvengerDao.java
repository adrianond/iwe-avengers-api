package com.iwe.avengers.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.repository.DynamoDBManager;

public class AvengerDao {
	
	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();
    
	/**
     * 
     * @param id
     * @return
     */
	public Avenger search(String id) {
		return mapper.load(Avenger.class, id);
	}

    /**
     * 
     * @param avenger
     * @return
     */
	public Avenger create(Avenger avenger) {
		mapper.save(avenger);
		return avenger;
	}
	
	/**
	 * 
	 * @param avenger
	 */
	public void remove(Avenger avenger) {
		mapper.delete(avenger);
	}

}
