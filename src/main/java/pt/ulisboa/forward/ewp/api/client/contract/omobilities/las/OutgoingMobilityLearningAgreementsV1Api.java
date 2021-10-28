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
   * @requires omobilityIds.size() <= getMaxOmobilityIdsPerRequest(heiId)
   */
  @RequestLine("POST /api/forward/ewp/omobilities/las/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilityLasGetResponseV1> findBySendingHeiIdAndOutgoingMobilityIds(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("omobility_id") List<String> omobilityIds);

  @RequestLine("POST /api/forward/ewp/omobilities/las/v1/update")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilityLasUpdateResponseV1> updateOutgoingMobilityLearningAgreement(
      OmobilityLasUpdateRequestV1 updateData);
}
