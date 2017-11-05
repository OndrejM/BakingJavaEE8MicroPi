package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.ejb.Schedule;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import fish.payara.micro.cdi.Outbound;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author Mike Croft
 */
@Stateless
public class StockTicker implements Serializable{

    private Stock stock;

    @Inject
    @Outbound(loopBack = true)
    private Event<Stock> stockEvents;
    
    @ConfigProperty(name = "stockticker.symbol", defaultValue = "PYA")
    @Inject
    private Instance<String> symbolConfig;
    

    @Schedule(hour = "*", minute="*", second = "*/1", persistent = true)
    private void generatePrice() {
        stock = new Stock(symbolConfig.get(), "Some very long description of Payara", Math.random() * 100.0);
        System.out.println(stock);
        stockEvents.fire(stock);
    }

    public Stock getStock() {
        return stock;
    }

}
