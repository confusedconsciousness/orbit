package com.kishan.resources;


import com.kishan.core.Hit;
import com.kishan.core.Indexer;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("/search")
@Tag(name = "Search APIs", description = "Use these APIs to search the web")
@Produces("application/json")
@Consumes("application/json")
@AllArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class SearchEngine {

    private final Indexer indexer;

    @POST
    @Path("/")
    public Response search(@NonNull @QueryParam("query") final String query) throws Exception {
        try {
            long start = System.currentTimeMillis();
            List<Hit> hits = indexer.query(query);
            long end = System.currentTimeMillis();
            log.info("Successfully search the entire web for your query and it took only :{}ms", (end - start));
            return Response.ok(hits).build();
        } catch (Exception e) {
            log.error("Unable to crawl: ", e);
            throw e;
        }

    }
}
