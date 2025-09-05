package pt.ulisboa.forward.ewp.api.client.contract.omobilities;

import eu.erasmuswithoutpaper.api.omobilities.v3.endpoints.OmobilitiesGetResponseV3;
import eu.erasmuswithoutpaper.api.omobilities.v3.endpoints.OmobilitiesIndexResponseV3;
import eu.erasmuswithoutpaper.api.omobilities.v3.endpoints.OmobilitiesUpdateRequestV3;
import eu.erasmuswithoutpaper.api.omobilities.v3.endpoints.OmobilitiesUpdateResponseV3;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.omobilities.OutgoingMobilitiesApiSpecificationResponseDTO;

/**
 * Contract interface for the Outgoing Mobilities V3 Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/tree/stable-v3">Specification</a>
 */
public interface OutgoingMobilitiesV3Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   *     in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/omobilities/v3/specification?hei_id={hei_id}")
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
   * @param sendingHeiId [REQUIRED] An identifier of the institution which is the sending partner of
   *     the mobilities.
   *     <p>By default, all mobilities the requester has access to (regardless of their receiving
   *     partner, arrival date, nor status) are returned. This includes very old mobilities, and the
   *     cancelled ones.
   * @param receivingHeiId Institution making the request.
   * @param receivingAcademicYearId An academic year identifier, in the YYYY/YYYY format, as defined
   *     in the EWP Academic Term Data Types document. If given, then are returned only such
   *     mobilities which have taken place (or are planned to take place) during this single
   *     academic year.
   * @param modifiedSince If given, it MAY be used to filter the returned mobility IDs to the ones
   *     which have been either created or modified after the given point in time.
   * @param globalId A global identifier of a student. If given, only those mobilities belonging to
   *     the student are returned.
   * @param activityAttributes An activity attribute (mobility type) of mobility. If given, only
   *     those mobilities with that activity attribute value are returned.
   * @return See <a
   *     href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v3/endpoints/index-response.xsd">Response
   *     specification</a>
   */
  @RequestLine("POST /api/forward/ewp/omobilities/v3/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilitiesIndexResponseV3> findAllByHeiId(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("receiving_academic_year_id") String receivingAcademicYearId,
      @Param("modified_since") ZonedDateTime modifiedSince,
      @Param("global_id") String globalId,
      @Param("activity_attributes") String activityAttributes);

  /**
   * This API allows the client to retrieve information on specific outgoing mobilities from the
   * sending HEI.
   *
   * @param sendingHeiId [REQUIRED] SCHAC ID of the mobilities' owner HEI (in EWP, the sending HEI
   *     is always the mobility's "owner").
   * @param outgoingMobilityIds [REQUIRED] A list of identifiers (no more than
   *     getMaxOmobilityIdsPerRequest(sendingHeiId) items) of mobilities which the client wants to
   *     retrieve information on.
   * @return See <a
   *     href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v3/endpoints/get-response.xsd">Response
   *     specification</a>
   * @requires outgoingMobilityIds.size() <= getMaxOmobilityIdsPerRequest(sendingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/omobilities/v3/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilitiesGetResponseV3> findByHeiIdAndOmobilityIds(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

  /**
   * This API allows the client to approve or reject an outgoing mobility.
   *
   * @param sendingHeiId [REQUIRED] SCHAC ID of the mobilities' owner HEI (in EWP, the sending HEI
   *     is always the mobility's "owner").
   * @param updateData [REQUIRED] The update data (approval or rejection). * See <a
   *     href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v3/endpoints/update-request.xsd">Specification</a>
   * @return See <a
   *     href="https://github.com/erasmus-without-paper/ewp-specs-api-omobilities/blob/stable-v3/endpoints/update-response.xsd">Specification</a>
   */
  @RequestLine("POST /api/forward/ewp/omobilities/v3/update?sending_hei_id={sendingHeiId}")
  @Headers("Content-Type: application/xml")
  ResponseWithDataDto<OmobilitiesUpdateResponseV3> updateOmobility(
      OmobilitiesUpdateRequestV3 updateData, @Param("sendingHeiId") String sendingHeiId);
}
