package pt.ulisboa.forward.ewp.api.client.exception;

import eu.erasmuswithoutpaper.api.architecture.ErrorResponse;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;

public class RequestException extends RuntimeException {

  private final int statusCode;
  private final transient List<Message> nodeMessages;
  private final ErrorResponse targetErrorResponse;

  public RequestException(int statusCode, List<Message> nodeMessages) {
    this(statusCode, nodeMessages, null);
  }

  public RequestException(
      int statusCode, List<Message> nodeMessages, ErrorResponse targetErrorResponse) {
    this.statusCode = statusCode;
    this.nodeMessages = nodeMessages;
    this.targetErrorResponse = targetErrorResponse;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public List<Message> getNodeMessages() {
    return nodeMessages;
  }

  public boolean hasTargetErrorResponse() {
    return targetErrorResponse != null;
  }

  public ErrorResponse getTargetErrorResponse() {
    return targetErrorResponse;
  }
}
