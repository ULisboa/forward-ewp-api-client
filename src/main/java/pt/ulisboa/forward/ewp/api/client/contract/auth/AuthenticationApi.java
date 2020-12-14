package pt.ulisboa.forward.ewp.api.client.contract.auth;

import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.auth.AuthenticationTestResponseDto;

/**
 * Contract interface for the Authentication Forward EWP API. This API actually does not forward
 * requests to other EWP servers. It exists to help identify authentication problems from the client
 * to the EWP Node. Therefore, it may be used, for instance, to check the connection to the EWP Node
 * is working correctly.
 */
public interface AuthenticationApi extends BaseApi {

  /**
   * Returns the host code that was identified by the EWP Node.
   *
   * @return A response whose data contains the host code that was identified by the EWP Node.
   */
  @RequestLine("GET /api/forward/ewp/authentication/test")
  ResponseWithDataDto<AuthenticationTestResponseDto> test();
}
