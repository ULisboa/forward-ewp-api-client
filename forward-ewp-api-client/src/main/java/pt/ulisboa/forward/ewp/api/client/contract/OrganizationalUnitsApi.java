package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.ounits.OunitsResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

public interface OrganizationalUnitsApi extends BaseApi {

  @RequestLine("POST /rest/forward/ewp/ounits")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponse> findByIds(
      @Param("hei_id") String heiId, @Param("ounit_id") List<String> ounitIds);

  @RequestLine("POST /rest/forward/ewp/ounits")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponse> findByCodes(
      @Param("hei_id") String heiId, @Param("ounit_code") List<String> ounitCodes);
}
