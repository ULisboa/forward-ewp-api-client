package pt.ulisboa.forward.ewp.api.client.contract.omobilities;

import eu.erasmuswithoutpaper.api.omobilities.v1.endpoints.OmobilitiesGetResponseV1;
import eu.erasmuswithoutpaper.api.omobilities.v1.endpoints.OmobilitiesIndexResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.omobilities.OutgoingMobilitiesApiSpecificationResponseDTO;

/**
 * Contract interface for the Outgoing Mobilities V1 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/tree/stable-v1">Specification</a>
 */
public interface OutgoingMobilitiesV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   * in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/omobilities/v1/specification?hei_id={hei_id}")
  ResponseWithDataDto<OutgoingMobilitiesApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of Outgoing Mobilities IDs that may be requested in a given request
   * to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Outgoing Mobilities IDs that the HEI accepts in a request.
   */
  default int getMaxOmobilityIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOmobilityIds();
  }

  /**
   * This API allows the receiving institution to access a list of all mobilities it can read on the
   * sending institution's server.
   *
   * @param sendingHeiId            [REQUIRED] An identifier of the institution which is the sending
   *                                partner of the mobilities.
   *                                <p>
   *                                By default, all mobilities the requester has access to
   *                                (regardless of their receiving partner, arrival date, nor
   *                                status) are returned. This includes very old mobilities, and the
   *                                cancelled ones.
   * @param receivingHeiIds         A list of institution identifiers. If given, then the results
   *                                returned contain only those mobilities whose receiving
   *                                institution matches at least one of the given receivingHeiIds
   *                                identifiers.
   * @param receivingAcademicYearId An academic year identifier, in the YYYY/YYYY format, as defined
   *                                in the EWP Academic Term Data Types document. If given, then are
   *                                returned only such mobilities which have taken place (or are
   *                                planned to take place) during this single academic year.
   * @param modifiedSince           If given, it MAY be used to filter the returned mobility IDs to
   *                                the ones which have been either created or modified after the
   *                                given point in time.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v1/endpoints/index-response.xsd">Response
   * specification</a>
   */
  @RequestLine("POST /api/forward/ewp/omobilities/v1/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilitiesIndexResponseV1> findAllByHeiId(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") List<String> receivingHeiIds,
      @Param("receiving_academic_year_id") String receivingAcademicYearId,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /**
   * This API allows the client to retrieve information on specific outgoing mobilities from the
   * sending HEI.
   *
   * @param sendingHeiId        [REQUIRED] SCHAC ID of the mobilities' owner HEI (in EWP, the
   *                            sending HEI is always the mobility's "owner").
   * @param outgoingMobilityIds [REQUIRED] A list of identifiers (no more than
   *                            getMaxOmobilityIdsPerRequest(sendingHeiId) items) of mobilities
   *                            which the client wants to retrieve information on.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v1/endpoints/get-response.xsd">Response
   * specification</a>
   * @requires iiaIds.size() <= getMaxOmobilityIdsPerRequest(sendingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/omobilities/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilitiesGetResponseV1> findByHeiIdAndIiaIds(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);
}
