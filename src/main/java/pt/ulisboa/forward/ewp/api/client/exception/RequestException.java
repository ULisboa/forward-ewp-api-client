package pt.ulisboa.forward.ewp.api.client.exception;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import java.util.List;

import eu.erasmuswithoutpaper.api.architecture.v1.MultilineStringWithOptionalLangV1;
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

  /**
   * Returns the HTTP status code.
   * @return HTTP status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Returns the ID of the communication that originated the current exception.
   * It should be provided to an EWP Node admin for easier troubleshooting
   * (allowing the identification of the exact request) on the EWP Node.
   * @return ID of the communication
   */
  public Long getCommunicationId() {
    return communicationId;
  }

  /**
   * Returns messages that the EWP Node produced itself.
   * For instance, it may include invalid input.
   */
  public List<Message> getNodeMessages() {
    return nodeMessages;
  }

  public boolean hasTargetErrorResponse() {
    return targetErrorResponse != null;
  }

  /**
   * Returns an EWP ErrorResponseV1 instance, if available.
   * This is the same instance that a remote EWP node has originally.
   * @return The original error response instance returned by a remote EWP node.
   */
  public ErrorResponseV1 getTargetErrorResponse() {
    return targetErrorResponse;
  }

  @Override
  public String getMessage() {
    StringBuilder result = new StringBuilder();

    result.append("Communication ID: ").append(this.communicationId).append(System.lineSeparator());

    if (!this.nodeMessages.isEmpty()) {
      result.append("Node Messages: ").append(System.lineSeparator());
      for (Message nodeMessage : this.nodeMessages) {
        result.append("- ").append(nodeMessage.getSeverity()).append(": ").append(nodeMessage.getSummary()).append(System.lineSeparator());
      }
    }

    if (this.hasTargetErrorResponse()) {
      result.append("Target Error Message: ").append(System.lineSeparator());
      result.append("'-> User Messages: ").append(System.lineSeparator());
      for (MultilineStringWithOptionalLangV1 userMessage : this.getTargetErrorResponse().getUserMessage()) {
        result.append("    - ").append(userMessage.getValue()).append(System.lineSeparator());
      }
    }

    return result.toString();
  }
}
