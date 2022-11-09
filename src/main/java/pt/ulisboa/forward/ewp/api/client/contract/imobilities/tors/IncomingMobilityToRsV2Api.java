package pt.ulisboa.forward.ewp.api.client.contract.imobilities.tors;

import eu.erasmuswithoutpaper.api.imobilities.tors.v2.endpoints.ImobilityTorsGetResponseV2;
import eu.erasmuswithoutpaper.api.imobilities.tors.v2.endpoints.ImobilityTorsIndexResponseV2;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.imobilities.tors.IncomingMobilityToRsApiSpecificationResponseDTO;

/**
 * Contract interface for the Outgoing Mobility ToRs V2 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobility-tors/tree/stable-v2">Specification</a>
 */
public interface IncomingMobilityToRsV2Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   * in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/omobilities/las/v2/specification?hei_id={hei_id}")
  ResponseWithDataDto<IncomingMobilityToRsApiSpecificationResponseDTO> getApiSpecification(
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
   * This API allows the sending institution to access a list of all mobility IDs for which the
   * receiving institution has already attached corresponding Transcripts of Records, and which the
   * caller can read (via the findByReceivingHeiIdAndOutgoingMobilityIds API).
   *
   * @param receivingHeiId [REQUIRED] An identifier of the institution which is the receiving
   *                       partner of the mobilities, and the issuer of the returned Transcripts of
   *                       Records.
   *                       <p>
   *                       By default, all transcripts the requester has access to (regardless of
   *                       their sending partner) are returned. This includes transcripts issued for
   *                       very old mobilities.
   * @param sendingHeiIds  A list of institution identifiers. If given, then the results returned
   *                       contain only such transcripts which were issued for those mobilities
   *                       whose sending institution matches at least one of the given sendingHeiIds
   *                       identifiers.
   * @param modifiedSince  If given, it MAY be used by the receiving HEI to filter the returned
   *                       transcripts of records to the ones which have been either created or
   *                       modified after the given point in time.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobility-tors/blob/stable-v2/endpoints/index-response.xsd">Response
   * specification</a>
   */
  @RequestLine("POST /api/forward/ewp/imobilities/tors/v2/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ImobilityTorsIndexResponseV2> findOutgoingMobilityIdsWithTranscriptOfRecord(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("sending_hei_id") List<String> sendingHeiIds,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /**
   * This API allows the client (usually the sending HEI) to retrieve Transcripts of Records for
   * specific Incoming Mobilities from the receiving HEI.
   *
   * @param receivingHeiId      [REQUIRED] SCHAC ID of the institution which is (or was) the
   *                            receiving partner of all the mobilities provided in
   *                            outgoingMobilityIds parameter list.
   * @param outgoingMobilityIds [REQUIRED] A list of Outgoing Mobility identifiers (max
   *                            getMaxOmobilityIdsPerRequest(receivingHeiId) items) - IDs of
   *                            Outgoing Mobility objects for which the client wants to retrieve
   *                            corresponding Transcripts of Records. The HEI referenced in the
   *                            receivingHeiId parameter must be the receiving partner of all these
   *                            mobilities (unmatched mobilities will be ignored).
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobility-tors/blob/stable-v2/endpoints/get-response.xsd">Response
   * specification</a>
   * @requires outgoingMobilityIds.size() <= getMaxOmobilityIdsPerRequest(receivingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/imobilities/tors/v2/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ImobilityTorsGetResponseV2> findByReceivingHeiIdAndOutgoingMobilityIds(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);
}
