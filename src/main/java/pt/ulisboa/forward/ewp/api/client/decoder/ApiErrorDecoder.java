package pt.ulisboa.forward.ewp.api.client.decoder;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.exception.ErrorDecoderException;
import pt.ulisboa.forward.ewp.api.client.exception.RequestException;
import pt.ulisboa.forward.ewp.api.client.utils.EwpJaxbDecoder;
import pt.ulisboa.forward.ewp.api.client.utils.HttpConstants;

public class ApiErrorDecoder implements ErrorDecoder {

  private final Decoder decoder;

  public ApiErrorDecoder() {
    this.decoder = new EwpJaxbDecoder();
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    int statusCode = response.status();
    try {
      return decodeResponseByStatusCode(methodKey, response, statusCode);
    } catch (Exception e) {
      return new ErrorDecoderException(statusCode, response.toString(), e);
    }
  }

  private Exception decodeResponseByStatusCode(String methodKey, Response response, int statusCode)
      throws IOException {
    if (400 <= statusCode && statusCode < 500) {
      return resolveClientErrorToException(response);
    } else if (500 <= statusCode && statusCode < 600) {
      return resolveServerErrorToException(response);
    } else {
      return new ErrorDecoderException(
          statusCode, response.toString(), FeignException.errorStatus(methodKey, response));
    }
  }

  Exception resolveClientErrorToException(Response response) throws IOException {
    boolean hasDataObject =
        response.headers().getOrDefault(HttpConstants.HEADER_X_HAS_DATA_OBJECT, new ArrayList<>())
            .stream()
            .findFirst()
            .map(Boolean::parseBoolean)
            .orElse(false);
    if (hasDataObject) {
      @SuppressWarnings("unchecked")
      ResponseWithDataDto<ErrorResponseV1> responseDto =
          (ResponseWithDataDto<ErrorResponseV1>)
              decoder.decode(response, ResponseWithDataDto.class);
      return new RequestException(
          response.status(), responseDto.getCommunicationId(), responseDto.getMessages(), responseDto.getDataObject());
    } else {
      ResponseDto responseDto = (ResponseDto) decoder.decode(response, ResponseDto.class);
      Long communicationId = responseDto != null ? responseDto.getCommunicationId() : null;
      List<Message> messages =
          responseDto != null ? responseDto.getMessages() : Collections.emptyList();
      return new RequestException(response.status(), communicationId, messages);
    }
  }

  Exception resolveServerErrorToException(Response response) throws IOException {
    ResponseDto responseDto = (ResponseDto) decoder.decode(response, ResponseDto.class);
    Long communicationId = responseDto != null ? responseDto.getCommunicationId() : null;
    List<Message> messages =
        responseDto != null ? responseDto.getMessages() : Collections.emptyList();
    return new RequestException(response.status(), communicationId, messages);
  }
}
