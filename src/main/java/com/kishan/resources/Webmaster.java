package com.kishan.resources;

import com.kishan.core.Crawler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Path("/")
@Tag(name = "Webmaster APIs", description = "Use these APIs to crawl a website and make it indexable")
@Produces("application/json")
@Consumes("application/json")
@AllArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class Webmaster {
    private Crawler crawler;

    @POST
    @Path("/crawl")
    public Response crawl(@NonNull @QueryParam("url") final String url,
                          @DefaultValue("false") @QueryParam("crawlChildren") final boolean crawlChildren) throws Exception {
        try {
            long start = System.currentTimeMillis();
            crawler.crawl(url, crawlChildren, 0L);
            long end = System.currentTimeMillis();
            log.info("Successfully crawled the website in :{}ms", (end - start));
            return Response.ok(202).build();
        } catch (Exception e) {
            log.error("Unable to crawl: ", e);
            throw e;
        }
    }
}
