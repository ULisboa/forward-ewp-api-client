package pt.ulisboa.forward.ewp.api.client.contract.iias;

import eu.erasmuswithoutpaper.api.iias.v6.endpoints.IiasGetResponseV6;
import eu.erasmuswithoutpaper.api.iias.v6.endpoints.IiasIndexResponseV6;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.iias.InterInstitutionalAgreementsApiSpecificationResponseDTO;

/**
 * Contract interface for the InterInstitutional Agreements V6 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias/tree/stable-v6">Specification</a>
 */
public interface InterInstitutionalAgreementsV6Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of InterInstitutional
   * Agreement IDs and codes accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of InterInstitutional Agreement IDs
   * and codes accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/iias/v6/specification?hei_id={hei_id}")
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

  /**
   * This API allows clients to see the list of all agreements (IIAs) known to a particular HEI.
   *
   * @param heiId                    [REQUIRED] Identifier of the HEI which we want to fetch the
   *                                 list of IIAs from.
   * @param partnerHeiId             [REQUIRED] If given, then the list of returned IIA IDs is
   *                                 limited to only those in which partner_hei_id is one of the
   *                                 partners. This value of this parameter MUST NOT equal the value
   *                                 passed in hei_id.
   * @param receivingAcademicYearIds If given, then the list of returned IIA IDs MAY be limited to
   *                                 only such, which are valid in at least one of the given
   *                                 academic years.
   * @param modifiedSince            If given, it MAY be used to filter the returned learning
   *                                 agreements to the ones which have been either created or
   *                                 modified after the given point in time.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias/blob/stable-v6/endpoints/index-response.xsd">Response
   * specification</a>
   */
  @RequestLine("POST /api/forward/ewp/iias/v6/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasIndexResponseV6> findAllByHeiId(
      @Param("hei_id") String heiId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("receiving_academic_year_id") List<String> receivingAcademicYearIds,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /**
   * This API allows the client to get the content of a specific IIAs (by their IDs).
   *
   * @param heiId   [REQUIRED] Identifier of the HEI to fetch the IIA from. (This HEI also needs to
   *                be one of the IIA's partners.)
   * @param iiaIds  [REQUIRED] A list of local IIA identifiers to be returned (no more than
   *                getMaxIiaIdsPerRequest(heiId) items, respectively).
   *                <p>
   *                HEI identified by the heiId parameter MUST be one of the partners of all the
   *                referenced IIAs (otherwise, IIA won't be found).
   * @param sendPdf Boolean, default false. Value false means, that the client is not interested in
   *                receiving PDF version of the agreement and the PDF SHOULD be skipped in the
   *                response.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias/blob/stable-v6/endpoints/get-response.xsd">Response
   * specification</a>
   * @requires iiaIds.size() <= getMaxIiaIdsPerRequest(heiId)
   */
  @RequestLine("POST /api/forward/ewp/iias/v6/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV6> findByHeiIdAndIiaIds(
      @Param("hei_id") String heiId,
      @Param("iia_id") List<String> iiaIds,
      @Param("send_pdf") Boolean sendPdf);

  /**
   * This API allows the client to get the content of a specific IIAs (by their codes).
   *
   * @param heiId    [REQUIRED] Identifier of the HEI to fetch the IIA from. (This HEI also needs to
   *                 be one of the IIA's partners.)
   * @param iiaCodes [REQUIRED] A list of local IIA codes to be returned (no more than
   *                 getMaxIiaCodesPerRequest(heiId) items, respectively).
   *                 <p>
   *                 HEI identified by the heiId parameter MUST be one of the partners of all the
   *                 referenced IIAs (otherwise, IIA won't be found).
   * @param sendPdf  Boolean, default false. Value false means, that the client is not interested in
   *                 receiving PDF version of the agreement and the PDF SHOULD be skipped in the
   *                 response.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias/blob/stable-v6/endpoints/get-response.xsd">Response
   * specification</a>
   * @requires iiaCodes.size() <= getMaxIiaCodesPerRequest(heiId)
   */
  @RequestLine("POST /api/forward/ewp/iias/v6/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV6> findByHeiIdAndIiaCodes(
      @Param("hei_id") String heiId,
      @Param("iia_code") List<String> iiaCodes,
      @Param("send_pdf") Boolean sendPdf);
}
