package fish.payara.demo.cloudready;

import java.util.Arrays;
import java.util.List;
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
    
    @ConfigProperty(name = "stockticker.urls", defaultValue = "")
    @Inject
    private String[] stockTickerUrls;
    
    public List<String> getStockTickerUrls() {
        return Arrays.asList(stockTickerUrls);
    }
}

