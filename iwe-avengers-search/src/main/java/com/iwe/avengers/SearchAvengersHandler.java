package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class SearchAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		
		
		context.getLogger().log("[#] - Searching avenger y id:" + avenger.getId());
		
		final Avenger avengerFound = dao.search(avenger.getId());
		
		final HandlerResponse response = HandlerResponse
				.builder()
				.setObjectBody(avengerFound)
				.build();
		
		context.getLogger().log("[#] - Avenger found");
		
		return response;
	}
}
