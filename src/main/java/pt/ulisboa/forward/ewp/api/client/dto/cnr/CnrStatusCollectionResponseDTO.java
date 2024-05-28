package pt.ulisboa.forward.ewp.api.client.dto.cnr;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"cnrStatuses"})
@XmlRootElement(name = "cnr-statuses")
public class CnrStatusCollectionResponseDTO {

  @XmlElement(name = "cnr-status", required = true)
  private Collection<CnrStatusResponseDTO> cnrStatuses = new ArrayList<>();

  public CnrStatusCollectionResponseDTO() {}

  public CnrStatusCollectionResponseDTO(
      Collection<CnrStatusResponseDTO> cnrStatuses) {
    this.cnrStatuses = cnrStatuses;
  }

  public Collection<CnrStatusResponseDTO> getCnrStatuses() {
    return cnrStatuses;
  }

  public void setCnrStatuses(Collection<CnrStatusResponseDTO> cnrStatuses) {
    this.cnrStatuses = cnrStatuses;
  }
}
