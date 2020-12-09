package ch.css.coaching.web;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public class GameEndpoint extends Endpoint {

  private static final WebSocketGame webSocketGame = new WebSocketGame();

  @Override
  public void onOpen(Session session, EndpointConfig config) {
    webSocketGame.addPlayerSession(session);
  }

}
