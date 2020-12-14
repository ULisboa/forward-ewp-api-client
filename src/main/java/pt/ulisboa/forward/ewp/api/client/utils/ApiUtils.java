package pt.ulisboa.forward.ewp.api.client.utils;

import feign.Param;
import feign.RequestLine;
import java.util.Collection;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.SupportedMajorVersionsResponseDTO;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

public class ApiUtils {

  private ApiUtils() {}

  public static Collection<Integer> getSupportedMajorVersionsOfInstitutionsApi(String heiId) {
    return getSupportedMajorVersionsByApi("institutions", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOrganizationalUnitsApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("ounits", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfCoursesApi(String heiId) {
    return getSupportedMajorVersionsByApi("courses", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfSimpleCourseReplicationApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("courses/replication", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsByApi(
      String apiLocalName, String heiId) {
    GeneralApi client = ApiClientFactory.createClient(GeneralApi.class);
    return client
        .getMajorVersionsSupportedByApi(apiLocalName, heiId)
        .getDataObject()
        .getSupportedMajorVersions();
  }

  private interface GeneralApi extends BaseApi {

    /**
     * Returns the list of major versions supported by an institution for a given API.
     *
     * @param api API to obtain the list of major versions supported by institution.
     * @param heiId HEI ID of an institution.
     * @return A response whose data is the list of major versions supported by an institution for a
     *     given API.
     */
    @RequestLine("GET /api/forward/ewp/{api}/versions/supported?hei_id={heiId}")
    ResponseWithDataDto<SupportedMajorVersionsResponseDTO> getMajorVersionsSupportedByApi(
        @Param("api") String api, @Param("heiId") String heiId);
  }
}
