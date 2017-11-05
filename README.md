# Run demo

## Build

```
mvn install
```

## Run StockWeb

```
cd StockWeb
java -Xmx128m -jar target/StockWeb-1.0-SNAPSHOT-microbundle.jar
```

Open WebSocket version at [http://localhost:8080/StockWeb-1.0-SNAPSHOT/](http://localhost:8080/StockWeb-1.0-SNAPSHOT/)

Open SSE version at [http://localhost:8080/StockWeb-1.0-SNAPSHOT/sse.jsp](http://localhost:8080/StockWeb-1.0-SNAPSHOT/sse.jsp)

None of them displays data yet.

## Run StockTicker

```
cd StockTicker
java -Xmx128m -jar target/StockTicker-1.0-SNAPSHOT-microbundle.jar
```

WebSocket version of Stockticker should display data.

SSE version is still not yet configured to find SSE endpoints.

## Dynamically modify configuration of StockTicker

Run Payara Server.

In Admin console select StockTicker node and send the following command:

```
set-config-property --propertyName=stockticker.symbol --propertyValue=Frankfurt --source=config --sourceName=server-config
```

StockTicker now sends data for stocks in "Frankfurt". A new graph should be created with name "Frankfurt" and old graph should stop to be updated. 

## Dynamically modify SSE configuration of StockWeb

In Admin console select StockTicker node and send the following command:

```
set-config-property --propertyName=stockticker.url --propertyValue=http://localhost:8081/StockTicker-1.0-SNAPSHOT/rest/sse;http://localhost:8082/StockTicker-1.0-SNAPSHOT/rest/sse --source=config --sourceName=server-config
```

The configuration value is parsed by a custom converter `ConfigListConverter` into `String[]`.

SSE version now discovers endpoints. Only one of them responds, the other one gives errors and keeps to be retried due to SSE auto-reconnect.

## Start another StockTicker

```
cd StockTicker
java -Xmx128m -Dstockticker.symbol=London -jar target/StockTicker-1.0-SNAPSHOT-microbundle.jar
```

Will start another Ticker for stocks in "London". WebSocket version will automatically pick it up. SSE version will pick it up too because of SSE autoreconnect.

## Start Micro Console

```
java -Xmx128m -jar payara-micro.jar --deploy micro-console.war --port 8085
```

Open microconsole at [http://localhost:8085/micro-console/](http://localhost:8085/micro-console/) and show all running apss and nodes.

## Show memory usage in jVisualVM