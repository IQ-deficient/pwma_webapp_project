package pwma.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pwma.model.Manufacturer;
import pwma.service.ItemService;
import pwma.service.ManufacturerService;

@Path("/manufacturers")
@RequestScoped
public class ManufacturerRESTfulService {

	@Inject
	private ManufacturerService manufacturerService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public Response getAllItems() {
		List<Manufacturer> items = manufacturerService.getAllManufacturers();
		return Response.ok(items).build();
	}

}