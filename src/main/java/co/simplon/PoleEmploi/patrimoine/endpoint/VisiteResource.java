package co.simplon.PoleEmploi.patrimoine.endpoint;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import co.simplon.PoleEmploi.patrimoine.dao.VisiteDao;
import co.simplon.PoleEmploi.patrimoine.dao.MonumentDao;
import co.simplon.PoleEmploi.patrimoine.modele.Visite;

@Path("/visites")
@RequestScoped
public class VisiteResource {

	private static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	private VisiteDao visiteDao;

	@Inject
	private MonumentDao monumentDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Visite> getVisites(@QueryParam("from") Integer from,
			@QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<Visite> visites = visiteDao.findAll(from, limit);
		return visites;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisiteById(@PathParam("id") Long id) {
		Visite Visite = visiteDao.getVisiteById(id);
		if (Visite != null)
			return Response.ok(Visite).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createVisite(Visite VisiteACreer, @Context UriInfo uriInfo) {
		if (!isVisiteValid(VisiteACreer)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Visite Visite = visiteDao.createVisite(VisiteACreer);
		if (Visite != null) {
			URL url;
			URI uri;
			try {
				url = new URL(uriInfo.getAbsolutePath().toURL()
						.toExternalForm()
						+ "/" + Visite.getId());
				uri = url.toURI();
			} catch (MalformedURLException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			return Response.created(uri).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}


	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateVisite(@PathParam("id") Long id, Visite VisiteAModifier) {
		if (!isVisiteValid(VisiteAModifier)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		VisiteAModifier.setId(id);
		visiteDao.updateVisite(VisiteAModifier);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deleteVisiteById(@PathParam("id") Long id) {
		visiteDao.deleteVisiteById(id);
	}
	
	private boolean isVisiteValid(Visite visite) {
		boolean valid = true;
		if (!(visite.getMonument().getIdentifiant() >0))
			valid = false;
		return valid;
	}
}
