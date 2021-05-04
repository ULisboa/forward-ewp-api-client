package pt.ulisboa.forward.ewp.api.client.contract.factsheet;

import eu.erasmuswithoutpaper.api.factsheet.v1.FactsheetResponseV1;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Factsheets V1 EWP API.
 *
 * @see <a href="https://github.com/erasmus-without-paper/ewp-specs-api-factsheet/blob/stable-v1/response.xsd">FactsheetResponseV1
 * specification (element factsheet-response)</a>
 */
public interface FactsheetsV1Api extends BaseApi {

  /**
   * Returns the factsheet for a given HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data is the factsheet details.
   */
  @RequestLine("GET /api/forward/ewp/factsheets/v1?hei_id={heiId}")
  ResponseWithDataDto<FactsheetResponseV1> findByHeiId(@Param("heiId") String heiId);
}
