package pt.ulisboa.forward.ewp.api.client.decoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import feign.Feign;
import feign.RequestLine;
import feign.Response;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import feign.mock.RequestKey;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message.MessageSeverity;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto.Data;
import pt.ulisboa.forward.ewp.api.client.exception.ErrorDecoderException;
import pt.ulisboa.forward.ewp.api.client.exception.RequestException;
import pt.ulisboa.forward.ewp.api.client.utils.HttpConstants;

class ApiErrorDecoderTest extends AbstractTest {

  @Test
  public void testErrorDecoder_ClientError_WithDataObject() throws JAXBException {
    ClientConfiguration.configure("", "host-code", "secret");

    ResponseWithDataDto<ErrorResponseV1> responseWithDataDto =
        createResponseDtoWithOneErrorMessageAndData("Test", new ErrorResponseV1());

    Map<String, Collection<String>> headers = new HashMap<>();
    headers.put(
        HttpConstants.HEADER_X_HAS_DATA_OBJECT, Collections.singletonList(String.valueOf(true)));

    MockClient mockClient = new MockClient();
    ErrorDecoderApiTest target =
        Feign.builder()
            .client(
                mockClient.add(
                    RequestKey.builder(HttpMethod.GET, "/test").build(),
                    Response.builder()
                        .status(400)
                        .headers(headers)
                        .body(marshallToXml(responseWithDataDto).getBytes())))
            .errorDecoder(new ApiErrorDecoder())
            .target(new MockTarget<>(ErrorDecoderApiTest.class));
    assertThatThrownBy(target::test)
        .isInstanceOf(RequestException.class)
        .satisfies(
            exception -> {
              RequestException requestException = (RequestException) exception;
              assertThat(requestException.getStatusCode()).isEqualTo(400);
              assertThat(requestException.getNodeMessages()).hasSize(1);
              assertThat(requestException.getNodeMessages().get(0).getSummary()).isEqualTo("Test");
              assertThat(requestException.getTargetErrorResponse()).isNotNull();
            });
  }

  @Test
  public void testErrorDecoder_ClientError_WithoutDataObject() throws JAXBException {
    ClientConfiguration.configure("", "host-code", "secret");

    ResponseDto responseDto = createResponseDtoWithOneErrorMessage("Test");

    MockClient mockClient = new MockClient();
    ErrorDecoderApiTest target =
        Feign.builder()
            .client(
                mockClient.add(
                    RequestKey.builder(HttpMethod.GET, "/test").build(),
                    Response.builder().status(400).body(marshallToXml(responseDto).getBytes())))
            .errorDecoder(new ApiErrorDecoder())
            .target(new MockTarget<>(ErrorDecoderApiTest.class));
    assertThatThrownBy(target::test)
        .isInstanceOf(RequestException.class)
        .satisfies(
            exception -> {
              RequestException requestException = (RequestException) exception;
              assertThat(requestException.getStatusCode()).isEqualTo(400);
              assertThat(requestException.getNodeMessages()).hasSize(1);
              assertThat(requestException.getNodeMessages().get(0).getSummary()).isEqualTo("Test");
              assertThat(requestException.getTargetErrorResponse()).isNull();
            });
  }

  @Test
  public void testErrorDecoder_ServerError() throws JAXBException {
    ClientConfiguration.configure("", "host-code", "secret");

    ResponseDto responseDto = createResponseDtoWithOneErrorMessage("Test");

    MockClient mockClient = new MockClient();
    ErrorDecoderApiTest target =
        Feign.builder()
            .client(mockClient.add(HttpMethod.GET, "/test", 500, marshallToXml(responseDto)))
            .errorDecoder(new ApiErrorDecoder())
            .target(new MockTarget<>(ErrorDecoderApiTest.class));
    assertThatThrownBy(target::test)
        .isInstanceOf(RequestException.class)
        .satisfies(
            exception -> {
              RequestException requestException = (RequestException) exception;
              assertThat(requestException.getStatusCode()).isEqualTo(500);
              assertThat(requestException.getNodeMessages()).hasSize(1);
              assertThat(requestException.getNodeMessages().get(0).getSummary()).isEqualTo("Test");
              assertThat(requestException.getTargetErrorResponse()).isNull();
            });
  }

  @Test
  public void testErrorDecoder_UnknownStatusCode() throws JAXBException {
    ClientConfiguration.configure("", "host-code", "secret");

    ResponseDto responseDto = createResponseDtoWithOneErrorMessage("Test");

    MockClient mockClient = new MockClient();
    int statusCode = 600;
    ErrorDecoderApiTest target =
        Feign.builder()
            .client(
                mockClient.add(
                    RequestKey.builder(HttpMethod.GET, "/test").build(),
                    Response.builder()
                        .status(statusCode)
                        .body(marshallToXml(responseDto).getBytes())))
            .errorDecoder(new ApiErrorDecoder())
            .target(new MockTarget<>(ErrorDecoderApiTest.class));
    assertThatThrownBy(target::test)
        .isInstanceOf(ErrorDecoderException.class)
        .satisfies(
            exception -> {
              ErrorDecoderException errorDecoderException = (ErrorDecoderException) exception;
              assertThat(errorDecoderException.getStatusCode()).isEqualTo(statusCode);
            });
  }

  private <T> ResponseWithDataDto<T> createResponseDtoWithOneErrorMessageAndData(
      String errorMessage, T dataObject) {
    Message message = new Message();
    message.setSeverity(MessageSeverity.ERROR);
    message.setSummary(errorMessage);
    ResponseWithDataDto<T> responseWithDataDto = new ResponseWithDataDto<>();
    responseWithDataDto.setMessages(Collections.singletonList(message));
    Data<T> data = new Data<>();
    data.setObject(dataObject);
    responseWithDataDto.setData(data);
    return responseWithDataDto;
  }

  private ResponseDto createResponseDtoWithOneErrorMessage(String errorMessage) {
    Message message = new Message();
    message.setSeverity(MessageSeverity.ERROR);
    message.setSummary(errorMessage);
    ResponseDto responseDto = new ResponseDto();
    responseDto.setMessages(Collections.singletonList(message));
    return responseDto;
  }

  private interface ErrorDecoderApiTest {
    @RequestLine("GET /test")
    void test();
  }
}
