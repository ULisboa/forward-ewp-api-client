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

  /**
   * Returns the HTTP status code.
   * @return HTTP status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Returns the response's data as string.
   * @return Response's data as string
   */
  public String getResponseAsString() {
    return responseAsString;
  }

  /**
   * Returns the actual exception thrown while trying to decode an error.
   * @return Actual exception thrown while trying to decode an error
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Returns the summary of the reason for the error decoding exception.
   * @return Summary of the reason for the error decoding exception
   */
  @Override
  public String getMessage() {
    return "Failed to decode error response: " + exception.getMessage();
  }
}
