package pt.ulisboa.forward.ewp.api.client.contract;

import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.dto.AuthenticationTestResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

public interface AuthenticationApi extends BaseApi {

  @RequestLine("GET /rest/forward/ewp/authentication/test")
  ResponseWithDataDto<AuthenticationTestResponseDto> test();
}
