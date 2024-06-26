package pt.ulisboa.forward.ewp.api.client.dto.cnr;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"changeNotificationIds"})
@XmlRootElement(name = "cnr-submission")
public class CnrSubmissionResponseDTO {

  @XmlElement(name = "change-notification-id", required = true)
  private Collection<Long> changeNotificationIds;

  public CnrSubmissionResponseDTO() {}

  public CnrSubmissionResponseDTO(Collection<Long> changeNotificationIds) {
    this.changeNotificationIds = changeNotificationIds;
  }

  /**
   * IDs of the submitted change notifications, per order of submission.
   *
   * @return IDs of the submitted change notifications.
   */
  public Collection<Long> getChangeNotificationIds() {
    return changeNotificationIds;
  }

  public void setChangeNotificationIds(Collection<Long> changeNotificationIds) {
    this.changeNotificationIds = changeNotificationIds;
  }
}
