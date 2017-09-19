package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 * @author Mike Croft
 */

@Path("sse")
@ApplicationScoped
public class StockTickerResource {

    @Inject
    private StockTicker stockTicker;

    @Context
    private Sse sse;

    private volatile SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init(){
        this.sseBroadcaster = sse.newBroadcaster();
    }


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventOutput(@Context SseEventSink eventSink){
        // registers the requester as a consumer of events
        eventSink.send(sse.newEvent("Registered as event consumer"));
        sseBroadcaster.register(eventSink);
    }

    @POST
    @Path("broadcast")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void broadcast(@FormParam("event") String event){
        // broadcasts the event to all registered consumers
        sseBroadcaster.broadcast(sse.newEvent(event));
    }

}