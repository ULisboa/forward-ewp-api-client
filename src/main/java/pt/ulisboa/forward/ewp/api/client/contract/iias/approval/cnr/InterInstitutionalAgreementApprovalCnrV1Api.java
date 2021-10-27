package pt.ulisboa.forward.ewp.api.client.contract.iias.approval.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/**
 * Contract interface for the InterInstitutional Agreement Approval CNR V1 Forward EWP API.
 */
public interface InterInstitutionalAgreementApprovalCnrV1Api extends BaseApi {

  @RequestLine("POST /api/forward/ewp/iias/approval/cnr/v1")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseDto sendChangeNotification(
      @Param("approving_hei_id") String approvingHeiId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("owner_hei_id") String ownerHeiId,
      @Param("iia_id") String iiaId);

}
