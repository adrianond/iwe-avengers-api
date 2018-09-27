package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDao;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avengerUpdate, final Context context) {
		Avenger updatedAvenger = null;
		
		context.getLogger().log("[#] - Searching avenger y id:" + avengerUpdate.getId());
		
		final Avenger avengerFound = dao.search(avengerUpdate.getId());
		
		if(avengerFound == null) {
            throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avengerUpdate.getId());
        }
		
		//if(dao.search(avenger.getId()) != null) {
			context.getLogger().log("[#] - Avenger found, updating...");
			updatedAvenger = dao.update(avengerUpdate);
			context.getLogger().log("[#] - Avenger sucessfully updated");
		//}

		final HandlerResponse response = HandlerResponse
				.builder()
				.setObjectBody(updatedAvenger)
				.build();
		
		return response;
	}
}
