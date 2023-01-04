package pt.ulisboa.forward.ewp.api.client.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "forward-ewp-api-response")
public class ResponseDto {

  private Long communicationId;

  @XmlElementWrapper(name = "messages")
  @XmlElement(name = "message")
  private List<Message> messages;

  public Long getCommunicationId() {
    return communicationId;
  }

  public void setCommunicationId(Long communicationId) {
    this.communicationId = communicationId;
  }

  public List<Message> getMessages() {
    if (this.messages == null) {
      this.messages = new ArrayList<>();
    }
    return this.messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
      name = "",
      propOrder = {"context", "severity", "summary"})
  public static class Message {

    @XmlElement(required = true)
    protected String context;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MessageSeverity severity;

    @XmlElement(required = true)
    protected String summary;

    public String getContext() {
      return context;
    }

    public void setContext(String value) {
      this.context = value;
    }

    public MessageSeverity getSeverity() {
      return severity;
    }

    public void setSeverity(MessageSeverity value) {
      this.severity = value;
    }

    public String getSummary() {
      return summary;
    }

    public void setSummary(String value) {
      this.summary = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Message message = (Message) o;
      return Objects.equals(context, message.context)
          && severity == message.severity
          && Objects.equals(summary, message.summary);
    }

    @Override
    public int hashCode() {
      return Objects.hash(context, severity, summary);
    }

    @XmlType(name = "message-severity")
    @XmlEnum
    public enum MessageSeverity {
      FATAL,
      ERROR,
      WARN,
      INFO;

      public String value() {
        return name();
      }

      public static MessageSeverity fromValue(String v) {
        return valueOf(v);
      }
    }
  }
}
