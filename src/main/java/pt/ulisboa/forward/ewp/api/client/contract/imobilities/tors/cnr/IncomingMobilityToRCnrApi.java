package pt.ulisboa.forward.ewp.api.client.contract.imobilities.tors.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/**
 * Contract interface for the Incoming Mobility ToR CNR Forward EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-imobility-tor-cnr/tree/stable-v1">Specification</a>
 */
public interface IncomingMobilityToRCnrApi extends BaseApi {

  /**
   * Send a change notification to the sendingHeiId. The notification may or may not succeed due to
   * miscellaneous reasons.
   *
   * @param sendingHeiId        Identifier of the sending HEI - the target of the current change
   *                            notification.
   * @param receivingHeiId      Identifier of the receiving HEI - the master of the Transcript of
   *                            Records which just have been changed.
   * @param outgoingMobilityIds A list of identifiers of Outgoing Mobility objects. These are the
   *                            Mobility objects for which Transcripts of Records have been recently
   *                            updated (or created) on the caller's side.
   * @return A response indicating that the change notification is scheduled to be sent by the EWP
   * Node.
   */
  @RequestLine("POST /api/forward/ewp/imobilities/tors/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseDto sendChangeNotification(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

}
