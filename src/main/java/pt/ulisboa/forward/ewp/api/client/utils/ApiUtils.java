package pt.ulisboa.forward.ewp.api.client.utils;

import feign.Param;
import feign.RequestLine;
import java.util.Collection;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.SupportedMajorVersionsResponseDTO;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

public class ApiUtils {

  private ApiUtils() {
  }

  public static Collection<Integer> getSupportedMajorVersionsOfInstitutionsApi(String heiId) {
    return getSupportedMajorVersionsByApi("institutions", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOrganizationalUnitsApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("organizational-units", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfCoursesApi(String heiId) {
    return getSupportedMajorVersionsByApi("courses", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfSimpleCourseReplicationApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("simple-course-replication", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfInterInstitutionalAgreementsApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("iias", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfInterInstitutionalAgreementCnrApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("iia-cnr", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfInterInstitutionalAgreementApprovalApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("iias-approval", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfInterInstitutionalAgreementApprovalCnrApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("iia-approval-cnr", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfFactsheetsApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("factsheet", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOutgoingMobilitiesApi(String heiId) {
    return getSupportedMajorVersionsByApi("omobilities", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOutgoingMobilityCnrApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("omobility-cnr", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOutgoingMobilityLearningAgreementsApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("omobility-las", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfOutgoingMobilityLearningAgreementCnrApi(
      String heiId) {
    return getSupportedMajorVersionsByApi("omobility-la-cnr", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsOfIncomingMobilitiesApi(String heiId) {
    return getSupportedMajorVersionsByApi("imobilities", heiId);
  }

  public static Collection<Integer> getSupportedMajorVersionsByApi(
      String apiLocalName, String heiId) {
    GeneralApi client = ApiClientFactory.createClient(GeneralApi.class);
    return client
        .getMajorVersionsSupportedByApi(apiLocalName, heiId)
        .getDataObject()
        .getSupportedMajorVersions();
  }

  public static Jaxb2Marshaller getJaxb2Marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller
        .setPackagesToScan("eu.erasmuswithoutpaper.api", "pt.ulisboa.forward.ewp.api.client.dto");
    return marshaller;
  }

  private interface GeneralApi extends BaseApi {

    /**
     * Returns the list of major versions supported by an institution for a given API.
     *
     * @param heiId HEI ID of an institution.
     * @param api   API to obtain the list of major versions supported by institution.
     * @return A response whose data is the list of major versions supported by an institution for a
     * given API.
     */
    @RequestLine("GET /api/forward/ewp/apis/{heiId}/versions/supported?api={api}")
    ResponseWithDataDto<SupportedMajorVersionsResponseDTO> getMajorVersionsSupportedByApi(
        @Param("api") String api, @Param("heiId") String heiId);
  }
}
