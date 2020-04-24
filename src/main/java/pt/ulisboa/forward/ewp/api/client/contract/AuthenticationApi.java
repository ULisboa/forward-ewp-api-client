package pt.ulisboa.forward.ewp.api.client.contract;

import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.dto.AuthenticationTestResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Authentication Forward EWP API. This API actually does not forward
 * requests to other EWP servers. It exists to help identify authentication problems from the client
 * to the EWP Node.
 */
public interface AuthenticationApi extends BaseApi {

  @RequestLine("GET /rest/forward/ewp/authentication/test")
  ResponseWithDataDto<AuthenticationTestResponseDto> test();
}
