package io.federecio.dropwizard.swagger.sample.resources;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import io.federecio.dropwizard.swagger.sample.SamplePojo;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Federico Recio
 */
@Path("/sample")
@Api("/sample")
public class SampleResource {

    @GET
    @ApiOperation("Sample endpoint")
    public Response get() {
        return Response.ok("This is okay").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Sample endpoint with path param")
    @Path("/hello-with-path-param/{name}")
    public Response getWithPathParam(@PathParam("name") String name) {
        return Response.ok(new SamplePojo("Hello " + name, 333)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Sample endpoint with query param")
    @Path("/hello-with-query-param")
    public Response getWithQueryParam(@QueryParam("name") String name) {
        return Response.ok(new SamplePojo("Hello " + name, 444)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Get access token",
            notes = "Authenticate user and get a access token.",
            response = SamplePojo.class
    )
    public SamplePojo postForToken(
            @FormParam("username") @ApiParam(defaultValue = "username") String username,
            @FormParam("password") @ApiParam(defaultValue = "q") String password
    ) {
        return new SamplePojo(username, 1234);
    }
}
