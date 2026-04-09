import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/health", new HealthHandler());
    server.setExecutor(Executors.newFixedThreadPool(4));
    server.start();
    System.out.println("backend-java listening on :" + port);
  }

  private static final class HealthHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
      String body = "{\"service\":\"backend-java\",\"status\":\"ok\"}";
      exchange.getResponseHeaders().set("Content-Type", "application/json");
      exchange.sendResponseHeaders(200, body.length());
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(body.getBytes(StandardCharsets.UTF_8));
      }
    }
  }
}
