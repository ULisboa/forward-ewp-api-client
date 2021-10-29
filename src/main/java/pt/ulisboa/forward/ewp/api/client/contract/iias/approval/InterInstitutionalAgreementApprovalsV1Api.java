package pt.ulisboa.forward.ewp.api.client.contract.iias.approval;

import eu.erasmuswithoutpaper.api.iias.approval.v1.IiasApprovalResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.iias.approval.InterInstitutionalAgreementsApprovalApiSpecificationResponseDTO;

/**
 * Contract interface for the InterInstitutional Agreement Approvals V1 Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias-approval/tree/stable-v1">Specification</a>
 */
public interface InterInstitutionalAgreementApprovalsV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of InterInstitutional
   * Agreement IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of InterInstitutional Agreement IDs
   * accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/iias/approval/v1/specification?hei_id={hei_id}")
  ResponseWithDataDto<InterInstitutionalAgreementsApprovalApiSpecificationResponseDTO> getApiSpecification(
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
   * This API allows HEIs to approve agreements sent by their partners in the Interinstitutional
   * Agreements API.
   *
   * @param approvingHeiId [REQUIRED] Identifier of the HEI from which we want to get the approval.
   *                       This MUST be the HEI covered by the server.
   * @param ownerHeiId     [REQUIRED] Identifier of the HEI which wants to get approval of his
   *                       copies of the agreements. Together with iia_id, uniquely identifies the
   *                       agreement copy.
   * @param iiaIds         [REQUIRED] A list of IIA identifiers as assigned by the calling partner,
   *                       no more than getMaxIiaIdsPerRequest(approvingHeiId).
   *                       <p>
   *                       HEIs identified by the approving_hei_id and owner_hei_id parameters MUST
   *                       be the partners of all the referenced IIAs.
   * @param sendPdf        Boolean, default false. Value false means, that the client is not
   *                       interested in receiving PDF version of the agreement and the PDF SHOULD
   *                       be skipped in the response.
   *                       <p>
   *                       Note: the PDF version can be missing in the response even if value true
   *                       is passed.
   * @return See <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iias-approval/blob/stable-v1/response.xsd">Response
   * specification</a>
   * @requires iiaIds.size() <= getMaxIiaIdsPerRequest(approvingHeiId)
   */
  @RequestLine("POST /api/forward/ewp/iias/approval/v1")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasApprovalResponseV1> getApprovals(
      @Param("approving_hei_id") String approvingHeiId,
      @Param("owner_hei_id") String ownerHeiId,
      @Param("iia_id") List<String> iiaIds,
      @Param("send_pdf") Boolean sendPdf);
}
