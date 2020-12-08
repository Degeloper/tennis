package ch.css.coaching;

import io.helidon.config.Config;
import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.metrics.MetricsSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.StaticContentSupport;
import io.helidon.webserver.WebServer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelidonServer {

  private static final Logger LOGGER = Logger.getLogger(HelidonServer.class.getName());

  private HelidonServer() {
  }

  private static Routing createRouting() {
    MetricsSupport metrics = MetricsSupport.create();
    HealthSupport health = HealthSupport.builder()
      .addLiveness(HealthChecks.deadlockCheck(), HealthChecks.heapMemoryCheck())
      .build();

    return Routing.builder()
      .register(health)  // Health at "/health"
      .register(metrics) // Metrics at "/metrics"
      .register("/", StaticContentSupport.builder("/assets")
        .welcomeFileName("index.html"))
      .build();
  }

  static void start() {
    // By default this will pick up application.yaml from the classpath
    Config config = Config.create();

    WebServer server = WebServer.builder(createRouting())
      .config(config.get("server"))
      .addMediaSupport(JsonpSupport.create())
      .build();

    server.start()
      .thenAccept(ws -> {
        LOGGER.info("Web server is up! http://localhost:" + ws.port());
        ws.whenShutdown().thenRun(()
          -> LOGGER.info("Web server is DOWN. Good bye!"));
      })
      .exceptionally(t -> {
        LOGGER.log(Level.SEVERE, "Startup failed: ", t);
        return null;
      });
  }
}
