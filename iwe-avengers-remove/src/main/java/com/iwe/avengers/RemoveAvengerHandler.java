package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		context.getLogger().log("[#] - deleting s avenger by id:" + avenger.getId());

		final Avenger avengerFound = dao.search(avenger.getId());

		if (avengerFound == null) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId());
		}  
			
			dao.remove(avenger.getId());
			context.getLogger().log("[#] - Avenger deleted");
		

		final HandlerResponse response = HandlerResponse
				.builder()
				.build();
		
		return response;
	}
}