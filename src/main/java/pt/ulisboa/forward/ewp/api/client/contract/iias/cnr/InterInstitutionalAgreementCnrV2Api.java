package pt.ulisboa.forward.ewp.api.client.contract.iias.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/**
 * Contract interface for the InterInstitutional Agreements CNR V2 Forward EWP API.
 */
public interface InterInstitutionalAgreementCnrV2Api extends BaseApi {

  @RequestLine("POST /api/forward/ewp/iias/cnr/v2")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseDto sendChangeNotification(
      @Param("notifier_hei_id") String notifierHeiId,
      @Param("partner_hei_id") String partnerHeiId,
      @Param("iia_id") List<String> iiaIds);

}
