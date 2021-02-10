package pwma.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pwma.model.Item;
import pwma.service.ItemService;

@Path("/items")
@RequestScoped
public class ItemRESTfulService {

	@Inject
	private Logger log;
	
	@Inject
	private ItemService itemService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllItems") 
	public Response getAllItems() {
		List<Item> items = itemService.getAllItems();
		return Response.ok(items).build();
	}

}
