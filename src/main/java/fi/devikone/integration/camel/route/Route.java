package fi.devikone.integration.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:tick?period=30s")
            .routeId("hello")
            .to("https://api.chucknorris.io/jokes/random")
            .unmarshal().json()
            .setProperty("joke").simple("${body[value]}")
            .log("${exchangeProperty.joke}");
    }
}
