package pt.ulisboa.forward.ewp.api.client.contract.iias.approval.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.ForwardEwpApiCnrSubmissionResponseDTO;

/**
 * Contract interface for the InterInstitutional Agreement Approval CNR Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iia-approval-cnr/tree/stable-v1">Specification</a>
 */
public interface InterInstitutionalAgreementApprovalCnrApi extends BaseApi {

  /**
   * Send a change notification to the partnerHeiId. The notification may or may not succeed due to
   * miscellaneous reasons.
   *
   * @param approvingHeiId Identifier of the HEI which has recently approved the partner's copy of
   *                       the IIA and is now sending the notification about this event.
   * @param partnerHeiId   Identifier of the HEI which is the target of the current change
   *                       notification.
   * @param ownerHeiId     Identifier of the HEI which is the owner of the copy of the agreement,
   *                       that has been approved. Together with iia_id, uniquely identifies the
   *                       agreement copy.
   * @param iiaId          Partner's identifier of the IIA which has been approved. Note: this is
   *                       NOT the ID assigned by the notifier (approving HEI), but by the owner of
   *                       the copy which has been approved.
   * @return A response indicating that the change notification is scheduled to be sent by the EWP
   * Node.
   */
  @RequestLine("POST /api/forward/ewp/iias/approval/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ForwardEwpApiCnrSubmissionResponseDTO> sendChangeNotification(
      @Param("approving_hei_id") String approvingHeiId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("owner_hei_id") String ownerHeiId,
      @Param("iia_id") String iiaId);

}
