package pt.ulisboa.forward.ewp.api.client.contract.omobilities.las.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.ForwardEwpApiCnrSubmissionResponseDTO;

/**
 * Contract interface for the Outgoing Mobility Learning Agreement CNR Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-omobility-la-cnr/tree/stable-v1">Specification</a>
 */
public interface OutgoingMobilityLearningAgreementCnrApi extends BaseApi {

  /**
   * Send a change notification to the sendingHeiId. The notification may or may not succeed due to
   * miscellaneous reasons.
   *
   * @param sendingHeiId        Identifier of the sending HEI - the master of the Outgoing Mobility
   *                            Learning Agreement objects which just have been changed.
   * @param sendingOunitId      Identifier of the organizational unit that is the owner of the
   *                            provided outgoingMobilityIds, if applicable.
   * @param receivingHeiId      Identifier of the receiving HEI - the target of the current change
   *                            notification.
   * @param outgoingMobilityIds A list of identifiers of Outgoing Mobility (Learning Agreement)
   *                            objects. These are the mobilities for which the learning agreement
   *                            has been recently updated (or created) on the caller's side.
   * @return A response indicating that the change notification is scheduled to be sent by the EWP
   * Node.
   */
  @RequestLine("POST /api/forward/ewp/omobilities/las/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ForwardEwpApiCnrSubmissionResponseDTO> sendChangeNotification(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("sending_ounit_id") String sendingOunitId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

}
