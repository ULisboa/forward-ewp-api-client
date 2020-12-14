package pt.ulisboa.forward.ewp.api.client.contract.iias;

import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasGetResponseV3;
import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasIndexResponseV3;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.iias.InterInstitutionalAgreementsApiSpecificationResponseDTO;

/** Contract interface for the InterInstitutional Agreements V3 Forward EWP API. */
public interface InterInstitutionalAgreementsV3Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of InterInstitutional
   * Agreement IDs and codes accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of InterInstitutional Agreement IDs
   *     and codes accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/iias/v3/specification?hei_id={hei_id}")
  ResponseWithDataDto<InterInstitutionalAgreementsApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of InterInstitutional Agreement IDs that may be requested in a given
   * request to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of InterInstitutional Agreement IDs that the HEI accepts in a request.
   */
  default int getMaxIiaIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxIiaIds();
  }

  /**
   * Returns the maximum number of InterInstitutional Agreement codes that may be requested in a
   * given request to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of InterInstitutional Agreement codes that the HEI accepts in a request.
   */
  default int getMaxIiaCodesPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxIiaCodes();
  }

  @RequestLine("POST /api/forward/ewp/iias/v3/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasIndexResponseV3> findAllByHeiId(
      @Param("hei_id") String heiId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("receiving_academic_year_id") List<String> receivingAcademicYearIds,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /** @requires iiaIds.size() <= getMaxIiaIdsPerRequest(heiId) */
  @RequestLine("POST /api/forward/ewp/iias/v3/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV3> findByHeiIdAndIiaIds(
      @Param("hei_id") String heiId,
      @Param("iia_id") List<String> iiaIds,
      @Param("send_pdf") Boolean sendPdf);

  /** @requires iiaCodes.size() <= getMaxIiaCodesPerRequest(heiId) */
  @RequestLine("POST /api/forward/ewp/iias/v3/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV3> findByHeiIdAndIiaCodes(
      @Param("hei_id") String heiId,
      @Param("iia_code") List<String> iiaCodes,
      @Param("send_pdf") Boolean sendPdf);
}
