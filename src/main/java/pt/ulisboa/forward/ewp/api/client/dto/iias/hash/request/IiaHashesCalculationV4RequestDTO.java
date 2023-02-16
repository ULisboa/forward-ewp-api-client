package pt.ulisboa.forward.ewp.api.client.dto.iias.hash.request;

import eu.erasmuswithoutpaper.api.iias.v4.endpoints.IiasGetResponseV4.Iia;
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
    propOrder = {"iias"})
@XmlRootElement(name = "iia-hashes-calculation-request")
public class IiaHashesCalculationV4RequestDTO {

  @XmlElementWrapper(name = "iias")
  @XmlElement(name = "iia", required = true)
  private List<Iia> iias;

  public IiaHashesCalculationV4RequestDTO() {
  }

  public IiaHashesCalculationV4RequestDTO(List<Iia> iias) {
    this.iias = iias;
  }

  public List<Iia> getIias() {
    return iias;
  }

  public void setIias(List<Iia> iias) {
    this.iias = iias;
  }
}
