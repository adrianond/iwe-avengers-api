package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {

		context.getLogger().log("[#] - Creating a new avenger" + newAvenger);

		final Avenger avengerCreated = dao.create(newAvenger);

		if (avengerCreated != null) {
			context.getLogger().log("[#] - Avenger created");
		}
		

		final HandlerResponse response = HandlerResponse.builder().setObjectBody(avengerCreated).build();

		return response;
	}

}
