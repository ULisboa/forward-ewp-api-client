package pt.ulisboa.forward.ewp.api.client.dto.iias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"maxIiaIds", "maxIiaCodes"})
@XmlRootElement(name = "iias-api-specification-response")
public class InterInstitutionalAgreementsApiSpecificationResponseDTO {

  @XmlElement(name = "max-iia-ids", required = true)
  private int maxIiaIds;

  @XmlElement(name = "max-iia-codes", required = true)
  private int maxIiaCodes;

  public InterInstitutionalAgreementsApiSpecificationResponseDTO() {}

  public InterInstitutionalAgreementsApiSpecificationResponseDTO(int maxIiaIds, int maxIiaCodes) {
    this.maxIiaIds = maxIiaIds;
    this.maxIiaCodes = maxIiaCodes;
  }

  public int getMaxIiaIds() {
    return maxIiaIds;
  }

  public void setMaxIiaIds(int maxIiaIds) {
    this.maxIiaIds = maxIiaIds;
  }

  public int getMaxIiaCodes() {
    return maxIiaCodes;
  }

  public void setMaxIiaCodes(int maxIiaCodes) {
    this.maxIiaCodes = maxIiaCodes;
  }
}
