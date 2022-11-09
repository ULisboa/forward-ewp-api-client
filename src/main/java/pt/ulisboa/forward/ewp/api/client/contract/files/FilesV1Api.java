package pt.ulisboa.forward.ewp.api.client.contract.files;

import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.exception.RequestException;

/**
 * Contract interface for the Files V1 EWP API.
 */
public interface FilesV1Api extends BaseApi {

  /**
   * Returns the file corresponding to a given file ID, if it exists.
   *
   * @param heiId HEI ID of an institution.
   * @param fileId File ID of the file to retrieve.
   * @return The file contents.
   * @throws RequestException If the request is invalid, including if the file ID does not exist.
   */
  @RequestLine("GET /api/forward/ewp/files/v1?hei_id={heiId}&file_id={fileId}")
  ResponseWithDataDto<FileResponse> findByHeiId(@Param("heiId") String heiId, @Param("fileId") String fileId);

}
