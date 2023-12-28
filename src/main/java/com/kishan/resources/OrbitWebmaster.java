package com.kishan.resources;

import com.kishan.core.Crawler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/webmaster")
@Produces("application/json")
@Consumes("application/json")
public class OrbitWebmaster {
    private Crawler crawler;

    @POST
    @Path("/crawl")
    public void crawl(String url) {
        crawler.crawl(url);
    }
}
