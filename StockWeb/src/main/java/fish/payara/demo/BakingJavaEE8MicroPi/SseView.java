package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author Ondrej Mihalyi
 */
@RequestScoped
@Named
public class SseView {
    
    @ConfigProperty(name = "stockticker.url", defaultValue = "UNDEFINED")
    @Inject
    private String stockTickerUrl;
    
    public String getStockTickerUrl() {
        return stockTickerUrl;
    }
}

