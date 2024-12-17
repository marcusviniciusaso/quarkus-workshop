package org.redhat;

import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.cache.CacheResult;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/long-request")
public class GreetingResource {

    @Inject
    @RestClient
    MyRemoteService myRemoteService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String someRequest() {
        return myRemoteService.getTooLongResponse();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/cache")
    @CacheResult(cacheName = "too-long-request")
    public String someRequestWithCache() {
        return myRemoteService.getTooLongResponse();
    }
}
