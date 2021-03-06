// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AirPolutionMonitoring.proto

package grpc.examples.SmartAir;

/**
 * <pre>
 * The request message for all rooms air quality.
 * </pre>
 *
 * Protobuf type {@code SmartAir.AllAirQualityRequest}
 */
public  final class AllAirQualityRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:SmartAir.AllAirQualityRequest)
    AllAirQualityRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AllAirQualityRequest.newBuilder() to construct.
  private AllAirQualityRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllAirQualityRequest() {
    allRoom_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllAirQualityRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            allRoom_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return grpc.examples.SmartAir.SmartAirServiceImpl.internal_static_SmartAir_AllAirQualityRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.examples.SmartAir.SmartAirServiceImpl.internal_static_SmartAir_AllAirQualityRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.examples.SmartAir.AllAirQualityRequest.class, grpc.examples.SmartAir.AllAirQualityRequest.Builder.class);
  }

  public static final int ALLROOM_FIELD_NUMBER = 1;
  private volatile java.lang.Object allRoom_;
  /**
   * <code>string allRoom = 1;</code>
   */
  public java.lang.String getAllRoom() {
    java.lang.Object ref = allRoom_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allRoom_ = s;
      return s;
    }
  }
  /**
   * <code>string allRoom = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAllRoomBytes() {
    java.lang.Object ref = allRoom_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allRoom_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getAllRoomBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, allRoom_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAllRoomBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, allRoom_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof grpc.examples.SmartAir.AllAirQualityRequest)) {
      return super.equals(obj);
    }
    grpc.examples.SmartAir.AllAirQualityRequest other = (grpc.examples.SmartAir.AllAirQualityRequest) obj;

    boolean result = true;
    result = result && getAllRoom()
        .equals(other.getAllRoom());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALLROOM_FIELD_NUMBER;
    hash = (53 * hash) + getAllRoom().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.AllAirQualityRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(grpc.examples.SmartAir.AllAirQualityRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The request message for all rooms air quality.
   * </pre>
   *
   * Protobuf type {@code SmartAir.AllAirQualityRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:SmartAir.AllAirQualityRequest)
      grpc.examples.SmartAir.AllAirQualityRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.examples.SmartAir.SmartAirServiceImpl.internal_static_SmartAir_AllAirQualityRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.examples.SmartAir.SmartAirServiceImpl.internal_static_SmartAir_AllAirQualityRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.examples.SmartAir.AllAirQualityRequest.class, grpc.examples.SmartAir.AllAirQualityRequest.Builder.class);
    }

    // Construct using grpc.examples.SmartAir.AllAirQualityRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      allRoom_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.examples.SmartAir.SmartAirServiceImpl.internal_static_SmartAir_AllAirQualityRequest_descriptor;
    }

    @java.lang.Override
    public grpc.examples.SmartAir.AllAirQualityRequest getDefaultInstanceForType() {
      return grpc.examples.SmartAir.AllAirQualityRequest.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.examples.SmartAir.AllAirQualityRequest build() {
      grpc.examples.SmartAir.AllAirQualityRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.examples.SmartAir.AllAirQualityRequest buildPartial() {
      grpc.examples.SmartAir.AllAirQualityRequest result = new grpc.examples.SmartAir.AllAirQualityRequest(this);
      result.allRoom_ = allRoom_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof grpc.examples.SmartAir.AllAirQualityRequest) {
        return mergeFrom((grpc.examples.SmartAir.AllAirQualityRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.examples.SmartAir.AllAirQualityRequest other) {
      if (other == grpc.examples.SmartAir.AllAirQualityRequest.getDefaultInstance()) return this;
      if (!other.getAllRoom().isEmpty()) {
        allRoom_ = other.allRoom_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      grpc.examples.SmartAir.AllAirQualityRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.examples.SmartAir.AllAirQualityRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object allRoom_ = "";
    /**
     * <code>string allRoom = 1;</code>
     */
    public java.lang.String getAllRoom() {
      java.lang.Object ref = allRoom_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allRoom_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string allRoom = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAllRoomBytes() {
      java.lang.Object ref = allRoom_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allRoom_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string allRoom = 1;</code>
     */
    public Builder setAllRoom(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allRoom_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string allRoom = 1;</code>
     */
    public Builder clearAllRoom() {
      
      allRoom_ = getDefaultInstance().getAllRoom();
      onChanged();
      return this;
    }
    /**
     * <code>string allRoom = 1;</code>
     */
    public Builder setAllRoomBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allRoom_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:SmartAir.AllAirQualityRequest)
  }

  // @@protoc_insertion_point(class_scope:SmartAir.AllAirQualityRequest)
  private static final grpc.examples.SmartAir.AllAirQualityRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.examples.SmartAir.AllAirQualityRequest();
  }

  public static grpc.examples.SmartAir.AllAirQualityRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllAirQualityRequest>
      PARSER = new com.google.protobuf.AbstractParser<AllAirQualityRequest>() {
    @java.lang.Override
    public AllAirQualityRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AllAirQualityRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllAirQualityRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllAirQualityRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.examples.SmartAir.AllAirQualityRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

