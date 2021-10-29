package pt.ulisboa.forward.ewp.api.client.contract.omobilities.las;

import eu.erasmuswithoutpaper.api.omobilities.las.v1.endpoints.OmobilityLasGetResponseV1;
import eu.erasmuswithoutpaper.api.omobilities.las.v1.endpoints.OmobilityLasIndexResponseV1;
import eu.erasmuswithoutpaper.api.omobilities.las.v1.endpoints.OmobilityLasUpdateRequestV1;
import eu.erasmuswithoutpaper.api.omobilities.las.v1.endpoints.OmobilityLasUpdateResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.omobilities.las.OutgoingMobilityLearningAgreementsApiSpecificationResponseDTO;

/**
 * Contract interface for the Outgoing Mobility Learning Agreements V1 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobility-las/tree/stable-v1">Specification</a>
 */
public interface OutgoingMobilityLearningAgreementsV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   * in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/omobilities/las/v1/specification?hei_id={hei_id}")
  ResponseWithDataDto<OutgoingMobilityLearningAgreementsApiSpecificationResponseDTO> getApiSpecification(
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
   * This API allows the receiving institution to access a list of all learning agreements it can
   * read on the sending institution's server.
   * <p>
   * Only learning agreements currently approved by the student and the sending HEI should be
   * returned.
   *
   * @param sendingHeiId            [REQUIRED] An identifier of the institution which is the sending
   *                                partner of the mobilities.
   *                                <p>
   *                                By default,returns all learning agreements the requester has
   *                                access to (regardless of their receiving partner, arrival date,
   *                                nor status) are returned. This includes very old learning
   *                                agreements.
   * @param receivingHeiIds         A list of institution identifiers. If given, then the results
   *                                returned contain only those learning agreements whose receiving
   *                                institution matches at least one of the given receivingHeiIds
   *                                identifiers.
   * @param receivingAcademicYearId An academic year identifier, in the YYYY/YYYY format, as defined
   *                                in the EWP Academic Term Data Types document. If given, then are
   *                                returned learning agreements for only such mobilities which have
   *                                taken place (or are planned to take place) during this single
   *                                academic year.
   * @param globalId                Global student identifier. Should follow the specification of
   *                                the European Student Identifier. If given, then are returned
   *                                learning agreements belonging to the specified student.
   * @param mobilityType            One of the following mobility types: blended, doctoral,
   *                                semester. If given, then are returned learning agreements only
   *                                of the specified type.
   * @param modifiedSince           If given, it MAY be used to filter the returned learning
   *                                agreements to the ones which have been either created or
   *                                modified after the given point in time.
   */
  @RequestLine("POST /api/forward/ewp/omobilities/las/v1/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilityLasIndexResponseV1> findOutgoingMobilityIdsWithLearningAgreement(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") List<String> receivingHeiIds,
      @Param("receiving_academic_year_id") String receivingAcademicYearId,
      @Param("global_id") String globalId,
      @Param("mobility_type") String mobilityType,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /**
   * This API allows the client to retrieve information on specific learning agreements from the
   * sending HEI.
   *
   * @param sendingHeiId        [REQUIRED] SCHAC ID of the mobilities' owner HEI (in EWP, the
   *                            sending HEI is always the mobility's "owner").
   * @param outgoingMobilityIds [REQUIRED] A list of identifiers (no more than
   *                            getMaxOmobilityIdsPerRequest(sendingHeiId) items) of mobilities for
   *                            which the client wants to retrieve learning agreements. All of these
   *                            mobilities should be the outgoing mobilities of the sending HEI
   *                            provided in the sendingHeiId parameter (otherwise, they will be
   *                            ignored).
   * @requires outgoingMobilityIds.size() <= getMaxOmobilityIdsPerRequest(sendingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/omobilities/las/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilityLasGetResponseV1> findBySendingHeiIdAndOutgoingMobilityIds(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

  /**
   * This API allows the receiving HEI to comment or approve a Learning Agreement proposal, stored
   * on the sending HEI's servers.
   *
   * @param updateData [REQUIRED] Update data commenting or approving a Learning Agreement proposal.
   *                   See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobility-las/blob/stable-v1/endpoints/update-request.xsd">Specification</a>
   */
  @RequestLine("POST /api/forward/ewp/omobilities/las/v1/update")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilityLasUpdateResponseV1> updateOutgoingMobilityLearningAgreement(
      OmobilityLasUpdateRequestV1 updateData);
}
