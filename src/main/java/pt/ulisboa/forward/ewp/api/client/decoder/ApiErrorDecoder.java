package pt.ulisboa.forward.ewp.api.client.decoder;

import eu.erasmuswithoutpaper.api.architecture.ErrorResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBContextFactory.Builder;
import feign.jaxb.JAXBDecoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.exception.ErrorDecoderException;
import pt.ulisboa.forward.ewp.api.client.exception.RequestException;
import pt.ulisboa.forward.ewp.api.client.utils.HttpConstants;

public class ApiErrorDecoder implements ErrorDecoder {

  private JAXBDecoder jaxbDecoder;

  public ApiErrorDecoder() {
    this.initJaxbDecoder();
  }

  void initJaxbDecoder() {
    JAXBContextFactory jaxbContextFactory =
        new Builder().withMarshallerJAXBEncoding("UTF-8").build();
    this.jaxbDecoder = new JAXBDecoder(jaxbContextFactory);
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    int statusCode = response.status();
    try {
      if (400 <= statusCode && statusCode < 500) {
        return resolveClientErrorToException(response);
      } else if (500 <= statusCode && statusCode < 600) {
        return resolveServerErrorToException(response);
      } else {
        return new ErrorDecoderException(
            statusCode, response.toString(), FeignException.errorStatus(methodKey, response));
      }
    } catch (Exception e) {
      return new ErrorDecoderException(statusCode, response.toString(), e);
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
      ResponseWithDataDto<ErrorResponse> responseDto =
          (ResponseWithDataDto<ErrorResponse>)
              jaxbDecoder.decode(response, ResponseWithDataDto.class);
      return new RequestException(
          response.status(), responseDto.getMessages(), responseDto.getDataObject());
    } else {
      ResponseDto responseDto = (ResponseDto) jaxbDecoder.decode(response, ResponseDto.class);
      List<Message> messages =
          responseDto != null ? responseDto.getMessages() : Collections.emptyList();
      return new RequestException(response.status(), messages);
    }
  }

  Exception resolveServerErrorToException(Response response) throws IOException {
    ResponseDto responseDto = (ResponseDto) jaxbDecoder.decode(response, ResponseDto.class);
    List<Message> messages =
        responseDto != null ? responseDto.getMessages() : Collections.emptyList();
    return new RequestException(response.status(), messages);
  }
}
