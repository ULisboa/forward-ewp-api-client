package pt.ulisboa.forward.ewp.api.client.exception;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;

public class RequestException extends RuntimeException {

  private final int statusCode;
  private final Long communicationId;
  private final transient List<Message> nodeMessages;
  private final ErrorResponseV1 targetErrorResponse;

  public RequestException(int statusCode, Long communicationId, List<Message> nodeMessages) {
    this(statusCode, communicationId, nodeMessages, null);
  }

  public RequestException(
      int statusCode, Long communicationId, List<Message> nodeMessages, ErrorResponseV1 targetErrorResponse) {
    this.statusCode = statusCode;
    this.communicationId = communicationId;
    this.nodeMessages = nodeMessages;
    this.targetErrorResponse = targetErrorResponse;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Long getCommunicationId() {
    return communicationId;
  }

  public List<Message> getNodeMessages() {
    return nodeMessages;
  }

  public boolean hasTargetErrorResponse() {
    return targetErrorResponse != null;
  }

  public ErrorResponseV1 getTargetErrorResponse() {
    return targetErrorResponse;
  }
}
