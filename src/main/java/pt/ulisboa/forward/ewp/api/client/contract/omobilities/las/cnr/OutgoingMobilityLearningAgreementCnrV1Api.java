package pt.ulisboa.forward.ewp.api.client.contract.omobilities.las.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/**
 * Contract interface for the Outgoing Mobility Learning Agreement CNR V1 Forward EWP API.
 */
public interface OutgoingMobilityLearningAgreementCnrV1Api extends BaseApi {

  @RequestLine("POST /api/forward/ewp/omobilities/las/cnr/v1")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseDto sendChangeNotification(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

}
