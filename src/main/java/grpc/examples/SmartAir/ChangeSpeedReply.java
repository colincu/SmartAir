// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AirPurificationSystem.proto

package grpc.examples.SmartAir;

/**
 * <pre>
 * The response message containing the current speed of the system low/med/high
 * </pre>
 *
 * Protobuf type {@code SmartAir.ChangeSpeedReply}
 */
public  final class ChangeSpeedReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:SmartAir.ChangeSpeedReply)
    ChangeSpeedReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ChangeSpeedReply.newBuilder() to construct.
  private ChangeSpeedReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ChangeSpeedReply() {
    speed_ = "";
    responseCode_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ChangeSpeedReply(
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

            speed_ = s;
            break;
          }
          case 16: {

            responseCode_ = input.readInt32();
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
    return grpc.examples.SmartAir.SmartAirSystemImpl.internal_static_SmartAir_ChangeSpeedReply_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return grpc.examples.SmartAir.SmartAirSystemImpl.internal_static_SmartAir_ChangeSpeedReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.examples.SmartAir.ChangeSpeedReply.class, grpc.examples.SmartAir.ChangeSpeedReply.Builder.class);
  }

  public static final int SPEED_FIELD_NUMBER = 1;
  private volatile java.lang.Object speed_;
  /**
   * <code>string speed = 1;</code>
   */
  public java.lang.String getSpeed() {
    java.lang.Object ref = speed_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      speed_ = s;
      return s;
    }
  }
  /**
   * <code>string speed = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSpeedBytes() {
    java.lang.Object ref = speed_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      speed_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RESPONSECODE_FIELD_NUMBER = 2;
  private int responseCode_;
  /**
   * <code>int32 responseCode = 2;</code>
   */
  public int getResponseCode() {
    return responseCode_;
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
    if (!getSpeedBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, speed_);
    }
    if (responseCode_ != 0) {
      output.writeInt32(2, responseCode_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSpeedBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, speed_);
    }
    if (responseCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, responseCode_);
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
    if (!(obj instanceof grpc.examples.SmartAir.ChangeSpeedReply)) {
      return super.equals(obj);
    }
    grpc.examples.SmartAir.ChangeSpeedReply other = (grpc.examples.SmartAir.ChangeSpeedReply) obj;

    boolean result = true;
    result = result && getSpeed()
        .equals(other.getSpeed());
    result = result && (getResponseCode()
        == other.getResponseCode());
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
    hash = (37 * hash) + SPEED_FIELD_NUMBER;
    hash = (53 * hash) + getSpeed().hashCode();
    hash = (37 * hash) + RESPONSECODE_FIELD_NUMBER;
    hash = (53 * hash) + getResponseCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static grpc.examples.SmartAir.ChangeSpeedReply parseFrom(
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
  public static Builder newBuilder(grpc.examples.SmartAir.ChangeSpeedReply prototype) {
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
   * The response message containing the current speed of the system low/med/high
   * </pre>
   *
   * Protobuf type {@code SmartAir.ChangeSpeedReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:SmartAir.ChangeSpeedReply)
      grpc.examples.SmartAir.ChangeSpeedReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return grpc.examples.SmartAir.SmartAirSystemImpl.internal_static_SmartAir_ChangeSpeedReply_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return grpc.examples.SmartAir.SmartAirSystemImpl.internal_static_SmartAir_ChangeSpeedReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.examples.SmartAir.ChangeSpeedReply.class, grpc.examples.SmartAir.ChangeSpeedReply.Builder.class);
    }

    // Construct using grpc.examples.SmartAir.ChangeSpeedReply.newBuilder()
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
      speed_ = "";

      responseCode_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return grpc.examples.SmartAir.SmartAirSystemImpl.internal_static_SmartAir_ChangeSpeedReply_descriptor;
    }

    @java.lang.Override
    public grpc.examples.SmartAir.ChangeSpeedReply getDefaultInstanceForType() {
      return grpc.examples.SmartAir.ChangeSpeedReply.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.examples.SmartAir.ChangeSpeedReply build() {
      grpc.examples.SmartAir.ChangeSpeedReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.examples.SmartAir.ChangeSpeedReply buildPartial() {
      grpc.examples.SmartAir.ChangeSpeedReply result = new grpc.examples.SmartAir.ChangeSpeedReply(this);
      result.speed_ = speed_;
      result.responseCode_ = responseCode_;
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
      if (other instanceof grpc.examples.SmartAir.ChangeSpeedReply) {
        return mergeFrom((grpc.examples.SmartAir.ChangeSpeedReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.examples.SmartAir.ChangeSpeedReply other) {
      if (other == grpc.examples.SmartAir.ChangeSpeedReply.getDefaultInstance()) return this;
      if (!other.getSpeed().isEmpty()) {
        speed_ = other.speed_;
        onChanged();
      }
      if (other.getResponseCode() != 0) {
        setResponseCode(other.getResponseCode());
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
      grpc.examples.SmartAir.ChangeSpeedReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (grpc.examples.SmartAir.ChangeSpeedReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object speed_ = "";
    /**
     * <code>string speed = 1;</code>
     */
    public java.lang.String getSpeed() {
      java.lang.Object ref = speed_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        speed_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string speed = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSpeedBytes() {
      java.lang.Object ref = speed_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        speed_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string speed = 1;</code>
     */
    public Builder setSpeed(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      speed_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string speed = 1;</code>
     */
    public Builder clearSpeed() {
      
      speed_ = getDefaultInstance().getSpeed();
      onChanged();
      return this;
    }
    /**
     * <code>string speed = 1;</code>
     */
    public Builder setSpeedBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      speed_ = value;
      onChanged();
      return this;
    }

    private int responseCode_ ;
    /**
     * <code>int32 responseCode = 2;</code>
     */
    public int getResponseCode() {
      return responseCode_;
    }
    /**
     * <code>int32 responseCode = 2;</code>
     */
    public Builder setResponseCode(int value) {
      
      responseCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 responseCode = 2;</code>
     */
    public Builder clearResponseCode() {
      
      responseCode_ = 0;
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


    // @@protoc_insertion_point(builder_scope:SmartAir.ChangeSpeedReply)
  }

  // @@protoc_insertion_point(class_scope:SmartAir.ChangeSpeedReply)
  private static final grpc.examples.SmartAir.ChangeSpeedReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.examples.SmartAir.ChangeSpeedReply();
  }

  public static grpc.examples.SmartAir.ChangeSpeedReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ChangeSpeedReply>
      PARSER = new com.google.protobuf.AbstractParser<ChangeSpeedReply>() {
    @java.lang.Override
    public ChangeSpeedReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ChangeSpeedReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ChangeSpeedReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ChangeSpeedReply> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.examples.SmartAir.ChangeSpeedReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

