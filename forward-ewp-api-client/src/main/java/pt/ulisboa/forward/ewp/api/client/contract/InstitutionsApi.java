package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

public interface InstitutionsApi extends BaseApi {

  @RequestLine("GET /rest/forward/ewp/institutions?hei_id={heiId}")
  ResponseWithDataDto<InstitutionsResponse> findByHeiId(@Param("heiId") String heiId);
}
