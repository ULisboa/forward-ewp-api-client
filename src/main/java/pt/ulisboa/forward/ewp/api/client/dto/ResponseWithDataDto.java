package pt.ulisboa.forward.ewp.api.client.dto;

import eu.erasmuswithoutpaper.api.architecture.v1.ErrorResponseV1;
import eu.erasmuswithoutpaper.api.courses.replication.v1.CourseReplicationResponseV1;
import eu.erasmuswithoutpaper.api.courses.v0.CoursesResponseV0;
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
