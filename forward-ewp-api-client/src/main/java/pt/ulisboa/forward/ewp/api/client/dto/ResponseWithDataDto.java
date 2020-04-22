package pt.ulisboa.forward.ewp.api.client.dto;

import eu.erasmuswithoutpaper.api.architecture.ErrorResponse;
import eu.erasmuswithoutpaper.api.courses.replication.CourseReplicationResponse;
import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import eu.erasmuswithoutpaper.api.ounits.OunitsResponse;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "forward-ewp-api-response-with-data")
@XmlSeeAlso(
    value = {
      AuthenticationTestResponseDto.class,
      InstitutionsResponse.class,
      OunitsResponse.class,
      CourseReplicationResponse.class,
      ErrorResponse.class
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
