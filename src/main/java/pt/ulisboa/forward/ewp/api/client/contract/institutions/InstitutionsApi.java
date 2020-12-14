package pt.ulisboa.forward.ewp.api.client.contract.institutions;

import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.HeiIdsResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Institutions General Forward EWP API.
 *
 * <p>This contract contains methods that have no versions associated. For instance, it allows to
 * obtain the list of all HEI IDs on the EWP network.
 */
public interface InstitutionsApi extends BaseApi {

  /**
   * Returns all HEI IDs registered on the EWP Registry.
   *
   * @return A response whose data is the collection of all HEI IDs registered on the EWP Registry.
   */
  @RequestLine("GET /api/forward/ewp/institutions/hei-ids")
  ResponseWithDataDto<HeiIdsResponseDTO> getAllHeiIds();
}
