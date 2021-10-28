package pt.ulisboa.forward.ewp.api.client.dto.omobilities.las;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"maxOmobilityIds"})
@XmlRootElement(name = "imobilities-las-api-specification-response")
public class OutgoingMobilityLearningAgreementsApiSpecificationResponseDTO {

  @XmlElement(name = "max-omobility-ids", required = true)
  private int maxOmobilityIds;

  public OutgoingMobilityLearningAgreementsApiSpecificationResponseDTO() {
  }

  public OutgoingMobilityLearningAgreementsApiSpecificationResponseDTO(int maxOmobilityIds) {
    this.maxOmobilityIds = maxOmobilityIds;
  }

  public int getMaxOmobilityIds() {
    return maxOmobilityIds;
  }

  public void setMaxOmobilityIds(int maxOmobilityIds) {
    this.maxOmobilityIds = maxOmobilityIds;
  }
}
