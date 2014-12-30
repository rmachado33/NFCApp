package com.example.objetos;

public class TagContent {
	  private long id;
	  private String payload;
	  private String payloadHeader;
	  private String payloadType;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getPayload() {
	    return payload;
	  }

	  public void setPayload(String comment) {
	    this.payload = comment;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return payload+" - "+ payloadHeader+" - "+ payloadType;
	  }

	public String getPayloadHeader() {
		return payloadHeader;
	}

	public void setPayloadHeader(String payloadHeader) {
		this.payloadHeader = payloadHeader;
	}

	public String getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(String payloadType) {
		this.payloadType = payloadType;
	}
	
	} 