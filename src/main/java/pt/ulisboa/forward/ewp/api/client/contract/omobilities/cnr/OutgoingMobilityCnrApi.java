package pt.ulisboa.forward.ewp.api.client.contract.omobilities.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.CnrSubmissionResponseDTO;

/**
 * Contract interface for the Outgoing Mobility CNR Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobility-cnr/tree/stable-v1">Specification</a>
 */
public interface OutgoingMobilityCnrApi extends BaseApi {

  /**
   * Send a change notification to the sendingHeiId. The notification may or may not succeed due to
   * miscellaneous reasons.
   *
   * @param sendingHeiId        Identifier of the sending HEI - the master of the Outgoing Mobility
   *                            objects which just have been changed.
   * @param sendingOunitId      Identifier of the organizational unit that is the owner of the
   *                            provided outgoingMobilityIds, if applicable.
   * @param receivingHeiId      Identifier of the receiving HEI - the target of the current change
   *                            notification.
   * @param outgoingMobilityIds A list of identifiers of Outgoing Mobility objects . These are the
   *                            Mobility objects which have been recently updated (or created) on
   *                            the caller's side.
   * @return A response indicating that the change notification is scheduled to be sent by the EWP
   * Node.
   */
  @RequestLine("POST /api/forward/ewp/omobilities/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CnrSubmissionResponseDTO> sendChangeNotification(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("sending_ounit_id") String sendingOunitId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

}
