package pt.ulisboa.forward.ewp.api.client.dto;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"heiIds"})
@XmlRootElement(name = "hei-ids")
public class HeiIdsResponseDTO {

  @XmlElement(name = "hei-id", required = true)
  private Collection<String> heiIds;

  public HeiIdsResponseDTO() {}

  public HeiIdsResponseDTO(Collection<String> heiIds) {
    this.heiIds = heiIds;
  }

  public Collection<String> getHeiIds() {
    return heiIds;
  }

  public void setHeiIds(Collection<String> heiIds) {
    this.heiIds = heiIds;
  }
}
