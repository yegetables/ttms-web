package com.xupt.common;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oos")
public class ConstantProperties {
  private String endpoint;
  private String keyid;
  private String keysecret;
  private String bucketname;
  private String filehost;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getKeyid() {
    return keyid;
  }

  public void setKeyid(String keyid) {
    this.keyid = keyid;
  }

  public String getKeysecret() {
    return keysecret;
  }

  public void setKeysecret(String keysecret) {
    this.keysecret = keysecret;
  }

  public String getBucketname() {
    return bucketname;
  }

  public void setBucketname(String bucketname) {
    this.bucketname = bucketname;
  }

  public String getFilehost() {
    return filehost;
  }

  public void setFilehost(String filehost) {
    this.filehost = filehost;
  }
}
