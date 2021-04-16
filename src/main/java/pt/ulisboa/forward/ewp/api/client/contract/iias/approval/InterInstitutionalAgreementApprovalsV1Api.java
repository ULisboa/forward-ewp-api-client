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
   * @requires iiaIds.size() <= getMaxIiaIdsPerRequest(heiId)
   */
  @RequestLine("POST /api/forward/ewp/iias/approval/v1")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasApprovalResponseV1> getApprovals(
      @Param("approving_hei_id") String approvingHeiId,
      @Param("owner_hei_id") String ownerHeiId,
      @Param("iia_id") List<String> iiaIds,
      @Param("send_pdf") Boolean sendPdf);
}
