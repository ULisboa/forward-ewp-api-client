package pt.ulisboa.forward.ewp.api.client.contract.files;

import java.io.Serializable;

public class FileResponse implements Serializable {

  private String mediaType;
  private byte[] data;

  public FileResponse(String mediaType, byte[] data) {
    this.mediaType = mediaType;
    this.data = data;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
