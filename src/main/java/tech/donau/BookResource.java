package tech.donau;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import tech.donau.data.Book;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/books")
public class BookResource {
    @Inject
    @Remote("books")
    RemoteCache<String, Book> remoteCache;

    @Inject
    RemoteCacheManager cacheManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@QueryParam("id") String id) {
        return remoteCache.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book createBook(@QueryParam("id")String id, @QueryParam("title") String title) {
        final Book book = new Book(id, title, "Quarkus");
        remoteCache.put(id, book);
        return book;
    }
}