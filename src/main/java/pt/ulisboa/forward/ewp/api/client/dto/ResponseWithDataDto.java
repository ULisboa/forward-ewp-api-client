package pt.ulisboa.forward.ewp.api.client.dto;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import eu.erasmuswithoutpaper.api.courses.replication.v1.CourseReplicationResponseV1;
import eu.erasmuswithoutpaper.api.courses.v0.CoursesResponseV0;
import eu.erasmuswithoutpaper.api.factsheet.v1.FactsheetResponseV1;
import eu.erasmuswithoutpaper.api.iias.approval.v1.IiasApprovalResponseV1;
import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasGetResponseV3;
import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasIndexResponseV3;
import eu.erasmuswithoutpaper.api.iias.v4.endpoints.IiasGetResponseV4;
import eu.erasmuswithoutpaper.api.iias.v4.endpoints.IiasIndexResponseV4;
import eu.erasmuswithoutpaper.api.institutions.v2.InstitutionsResponseV2;
import eu.erasmuswithoutpaper.api.ounits.v2.OunitsResponseV2;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import pt.ulisboa.forward.ewp.api.client.dto.auth.AuthenticationTestResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.courses.CoursesApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.iias.InterInstitutionalAgreementsApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.iias.approval.InterInstitutionalAgreementsApprovalApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ounits.OrganizationalUnitsApiSpecificationResponseDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "forward-ewp-api-response-with-data")
// NOTE: All admissible data types must be registered here
@XmlSeeAlso(
    value = {
        AuthenticationTestResponseDto.class,
        HeiIdsResponseDTO.class,
        InstitutionsResponseV2.class,
        OrganizationalUnitsApiSpecificationResponseDTO.class,
        OunitsResponseV2.class,
        CourseReplicationResponseV1.class,
        CoursesApiSpecificationResponseDTO.class,
        CoursesResponseV0.class,
        InterInstitutionalAgreementsApiSpecificationResponseDTO.class,
        IiasIndexResponseV3.class,
        IiasIndexResponseV4.class,
        IiasGetResponseV3.class,
        IiasGetResponseV4.class,
        InterInstitutionalAgreementsApprovalApiSpecificationResponseDTO.class,
        IiasApprovalResponseV1.class,
        FactsheetResponseV1.class,
        ErrorResponseV1.class
    })
public class ResponseWithDataDto<T> extends ResponseDto {

  private Data<T> data;

  public T getDataObject() {
    return data.getObject();
  }

  public Data<T> getData() {
    if (this.data == null) {
      this.data = new Data<>();
    }
    return this.data;
  }

  public void setData(Data<T> data) {
    this.data = data;
  }

  public static <T> ResponseWithDataDto<T> createWithoutMessages(T dataObject) {
    ResponseWithDataDto<T> result = new ResponseWithDataDto<>();
    Data<T> responseBodyData = new Data<>();
    responseBodyData.setObject(dataObject);
    result.data = responseBodyData;
    return result;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
      name = "",
      propOrder = {"object"})
  public static class Data<T> {

    @XmlAnyElement(lax = true)
    private T object;

    public T getObject() {
      return object;
    }

    public void setObject(T object) {
      this.object = object;
    }
  }
}
