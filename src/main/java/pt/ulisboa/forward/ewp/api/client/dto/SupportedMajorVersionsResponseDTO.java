package pt.ulisboa.forward.ewp.api.client.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"supportedMajorVersions"})
@XmlRootElement(name = "supported-major-versions-response")
public class SupportedMajorVersionsResponseDTO {

  @XmlElementWrapper(name = "supported-major-versions")
  @XmlElement(name = "major-version")
  private List<Integer> supportedMajorVersions;

  public SupportedMajorVersionsResponseDTO() {}

  public SupportedMajorVersionsResponseDTO(List<Integer> supportedMajorVersions) {
    this.supportedMajorVersions = supportedMajorVersions;
  }

  public List<Integer> getSupportedMajorVersions() {
    return supportedMajorVersions;
  }

  public void setSupportedMajorVersions(List<Integer> supportedMajorVersions) {
    this.supportedMajorVersions = supportedMajorVersions;
  }
}