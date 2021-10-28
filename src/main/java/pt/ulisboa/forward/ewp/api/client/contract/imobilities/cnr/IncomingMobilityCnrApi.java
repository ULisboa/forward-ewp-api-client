package pt.ulisboa.forward.ewp.api.client.contract.imobilities.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/**
 * Contract interface for the Incoming Mobility CNR Forward EWP API.
 */
public interface IncomingMobilityCnrApi extends BaseApi {

  @RequestLine("POST /api/forward/ewp/imobilities/cnr")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseDto sendChangeNotification(
      @Param("sending_hei_id") String sendingHeiId,
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);

}
