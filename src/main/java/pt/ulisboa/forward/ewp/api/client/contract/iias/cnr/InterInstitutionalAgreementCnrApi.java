package pt.ulisboa.forward.ewp.api.client.contract.iias.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.CnrSubmissionResponseDTO;

/**
 * Contract interface for the InterInstitutional Agreements CNR Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-iia-cnr/tree/stable-v2">Specification</a>
 */
public interface InterInstitutionalAgreementCnrApi extends BaseApi {

  /**
   * Send a change notification to the partnerHeiId. The notification may or may not succeed due to
   * miscellaneous reasons.
   *
   * @param notifierHeiId   Identifier of the HEI which has recently updated their copy of the IIA
   *                        on their servers, and is now sending the notification about this event.
   * @param notifierOunitId Identifier of the organizational unit that is the owner of the provided
   *                        iiaId, if applicable.
   * @param partnerHeiId    Identifier of the HEI which is the target of the current change
   *                        notification.
   * @param iiaId           Identifier of the IIA which has recently been changed (created, updated
   *                        or deleted) on the notifier-HEI's servers.
   * @return A response indicating that the change notification is scheduled to be sent by the EWP
   * Node.
   */
  @RequestLine("POST /api/forward/ewp/iias/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CnrSubmissionResponseDTO> sendChangeNotification(
      @Param("notifier_hei_id") String notifierHeiId,
      @Param("notifier_ounit_id") String notifierOunitId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("iia_id") String iiaId);

}
