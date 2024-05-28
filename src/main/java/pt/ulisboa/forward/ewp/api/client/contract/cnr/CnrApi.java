package pt.ulisboa.forward.ewp.api.client.contract.cnr;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.CnrStatusCollectionResponseDTO;

/**
 * Contract interface for the CNR Forward EWP API. This interface allows to execute operations over
 * scheduled change notifications, given their IDs.
 */
public interface CnrApi extends BaseApi {

    /**
     * Returns the statuses of one or more given change notification(s).
     *
     * @param ids Identifiers of the change notifications. These are returned when sending a CNR request.
     * @return A response with the statuses of the change notification(s).
     */
    @RequestLine("POST /api/forward/ewp/cnr/status")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseWithDataDto<CnrStatusCollectionResponseDTO> getCnrStatuses(@Param("id") List<Long> ids);

}
