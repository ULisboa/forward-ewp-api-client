package pt.ulisboa.forward.ewp.api.client.contract.imobilities;

import eu.erasmuswithoutpaper.api.imobilities.v1.endpoints.ImobilitiesGetResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.imobilities.IncomingMobilitiesApiSpecificationResponseDTO;

/**
 * Contract interface for the Incoming Mobilities V1 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobilities/tree/stable-v1">Specification</a>
 */
public interface IncomingMobilitiesV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Incoming
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Incoming Mobilities IDs and codes
   * accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/imobilities/v3/specification?hei_id={hei_id}")
  ResponseWithDataDto<IncomingMobilitiesApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of Outgoing Mobility IDs that may be requested in a given request to
   * the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Outgoing Mobility IDs that the HEI accepts in a request.
   */
  default int getMaxOutgoingMobilityIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOmobilityIds();
  }

  /**
   * This API allows the client to retrieve information on specific incoming mobilities from the
   * receiving HEI.
   *
   * @param receivingHeiId      [REQUIRED] SCHAC ID of the receiving HEI of the mobilities.
   * @param outgoingMobilityIds [REQUIRED] A list of identifiers (no more than
   *                            getMaxOutgoingMobilityIdsPerRequest(receivingHeiId) items) of
   *                            mobilities which the client wants to retrieve information on. All of
   *                            these mobilities should be the mobilities of which the HEI provided
   *                            in the receiving_hei_id parameter is the receiving partner of
   *                            (otherwise, they will be ignored).
   *                            <p>
   *                            Note, that mobilities in this API are identified by their outgoing
   *                            mobility identifiers. These are the identifiers supplied by the
   *                            sending HEI. This means that the receiving HEI can use this API to
   *                            publish data regarding only those mobilities, which were previously
   *                            published by the sending HEI via the Outgoing Mobilities API.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobilities/blob/stable-v1/endpoints/get-response.xsd">Response
   * specification</a>
   * @requires outgoingMobilityIds.size() <= getMaxOutgoingMobilityIdsPerRequest(receivingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/imobilities/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ImobilitiesGetResponseV1> findByReceivingHeiIdAndOmobilityIds(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);
}
