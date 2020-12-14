package pt.ulisboa.forward.ewp.api.client.contract.omobilities;

import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasGetResponseV3;
import eu.erasmuswithoutpaper.api.omobilities.v1.endpoints.OmobilitiesIndexResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.omobilities.OutgoingMobilitiesApiSpecificationResponseDTO;

/** Contract interface for the Outgoing Mobilities V1 Forward EWP API. */
public interface OutgoingMobilitiesV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   *     in any given request for a specific HEI ID.
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

  @RequestLine("POST /api/forward/ewp/omobilities/v1/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OmobilitiesIndexResponseV1> findAllByHeiId(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") List<String> receivingHeiIds,
      @Param("receiving_academic_year_id") String receivingAcademicYearId,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /** @requires iiaIds.size() <= getMaxOmobilityIdsPerRequest(heiId) */
  @RequestLine("POST /api/forward/ewp/omobilities/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV3> findByHeiIdAndIiaIds(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("omobility_id") List<String> omobilityIds);
}
