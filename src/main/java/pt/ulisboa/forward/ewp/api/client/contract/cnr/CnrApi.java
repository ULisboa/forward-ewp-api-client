package pt.ulisboa.forward.ewp.api.client.contract.cnr;

import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.cnr.ForwardEwpApiCnrStatusResponseDTO;

/**
 * Contract interface for the CNR Forward EWP API. This interface allows to execute operations over
 * scheduled change notifications, given their IDs.
 */
public interface CnrApi extends BaseApi {

    /**
     * Returns the status of a given change notification.
     *
     * @param id Identifier of the change notification. It is returned when sending a CNR request.
     * @return A response with the status of the change notification.
     */
    @RequestLine("GET /api/forward/ewp/cnr/status?id={id}")
    ResponseWithDataDto<ForwardEwpApiCnrStatusResponseDTO> getCnrStatus(@Param("id") long id);

}
