package pt.ulisboa.forward.ewp.api.client.exception;

public class ErrorDecoderException extends RuntimeException {

  private final int statusCode;
  private final String responseAsString;
  private final Exception exception;

  public ErrorDecoderException(int statusCode, String responseAsString, Exception exception) {
    this.statusCode = statusCode;
    this.responseAsString = responseAsString;
    this.exception = exception;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getResponseAsString() {
    return responseAsString;
  }

  public Exception getException() {
    return exception;
  }

  @Override
  public String getMessage() {
    return "Failed to decode error response: " + exception.getMessage();
  }
}
