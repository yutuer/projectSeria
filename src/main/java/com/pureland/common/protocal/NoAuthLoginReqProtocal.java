// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NoAuthLoginReq.proto

package com.pureland.common.protocal;

public final class NoAuthLoginReqProtocal {
  private NoAuthLoginReqProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface NoAuthLoginReqOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string machineId = 1;
    /**
     * <code>required string machineId = 1;</code>
     */
    boolean hasMachineId();
    /**
     * <code>required string machineId = 1;</code>
     */
    java.lang.String getMachineId();
    /**
     * <code>required string machineId = 1;</code>
     */
    com.google.protobuf.ByteString
        getMachineIdBytes();

    // optional int32 raceType = 2;
    /**
     * <code>optional int32 raceType = 2;</code>
     *
     * <pre>
     *参考PlayerVO.raceType注释
     * </pre>
     */
    boolean hasRaceType();
    /**
     * <code>optional int32 raceType = 2;</code>
     *
     * <pre>
     *参考PlayerVO.raceType注释
     * </pre>
     */
    int getRaceType();

    // optional string userName = 3;
    /**
     * <code>optional string userName = 3;</code>
     */
    boolean hasUserName();
    /**
     * <code>optional string userName = 3;</code>
     */
    java.lang.String getUserName();
    /**
     * <code>optional string userName = 3;</code>
     */
    com.google.protobuf.ByteString
        getUserNameBytes();
  }
  /**
   * Protobuf type {@code NoAuthLoginReq}
   */
  public static final class NoAuthLoginReq extends
      com.google.protobuf.GeneratedMessage
      implements NoAuthLoginReqOrBuilder {
    // Use NoAuthLoginReq.newBuilder() to construct.
    private NoAuthLoginReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private NoAuthLoginReq(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final NoAuthLoginReq defaultInstance;
    public static NoAuthLoginReq getDefaultInstance() {
      return defaultInstance;
    }

    public NoAuthLoginReq getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private NoAuthLoginReq(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              machineId_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              raceType_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              userName_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.pureland.common.protocal.NoAuthLoginReqProtocal.internal_static_NoAuthLoginReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.NoAuthLoginReqProtocal.internal_static_NoAuthLoginReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.class, com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.Builder.class);
    }

    public static com.google.protobuf.Parser<NoAuthLoginReq> PARSER =
        new com.google.protobuf.AbstractParser<NoAuthLoginReq>() {
      public NoAuthLoginReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new NoAuthLoginReq(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<NoAuthLoginReq> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string machineId = 1;
    public static final int MACHINEID_FIELD_NUMBER = 1;
    private java.lang.Object machineId_;
    /**
     * <code>required string machineId = 1;</code>
     */
    public boolean hasMachineId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string machineId = 1;</code>
     */
    public java.lang.String getMachineId() {
      java.lang.Object ref = machineId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          machineId_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string machineId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMachineIdBytes() {
      java.lang.Object ref = machineId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        machineId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int32 raceType = 2;
    public static final int RACETYPE_FIELD_NUMBER = 2;
    private int raceType_;
    /**
     * <code>optional int32 raceType = 2;</code>
     *
     * <pre>
     *参考PlayerVO.raceType注释
     * </pre>
     */
    public boolean hasRaceType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 raceType = 2;</code>
     *
     * <pre>
     *参考PlayerVO.raceType注释
     * </pre>
     */
    public int getRaceType() {
      return raceType_;
    }

    // optional string userName = 3;
    public static final int USERNAME_FIELD_NUMBER = 3;
    private java.lang.Object userName_;
    /**
     * <code>optional string userName = 3;</code>
     */
    public boolean hasUserName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string userName = 3;</code>
     */
    public java.lang.String getUserName() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string userName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      machineId_ = "";
      raceType_ = 0;
      userName_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasMachineId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getMachineIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, raceType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getUserNameBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getMachineIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, raceType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getUserNameBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code NoAuthLoginReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.NoAuthLoginReqProtocal.internal_static_NoAuthLoginReq_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.NoAuthLoginReqProtocal.internal_static_NoAuthLoginReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.class, com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.Builder.class);
      }

      // Construct using com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        machineId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        raceType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        userName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.NoAuthLoginReqProtocal.internal_static_NoAuthLoginReq_descriptor;
      }

      public com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq getDefaultInstanceForType() {
        return com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.getDefaultInstance();
      }

      public com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq build() {
        com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq buildPartial() {
        com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq result = new com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.machineId_ = machineId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.raceType_ = raceType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.userName_ = userName_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq) {
          return mergeFrom((com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq other) {
        if (other == com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq.getDefaultInstance()) return this;
        if (other.hasMachineId()) {
          bitField0_ |= 0x00000001;
          machineId_ = other.machineId_;
          onChanged();
        }
        if (other.hasRaceType()) {
          setRaceType(other.getRaceType());
        }
        if (other.hasUserName()) {
          bitField0_ |= 0x00000004;
          userName_ = other.userName_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasMachineId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string machineId = 1;
      private java.lang.Object machineId_ = "";
      /**
       * <code>required string machineId = 1;</code>
       */
      public boolean hasMachineId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string machineId = 1;</code>
       */
      public java.lang.String getMachineId() {
        java.lang.Object ref = machineId_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          machineId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string machineId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getMachineIdBytes() {
        java.lang.Object ref = machineId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          machineId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string machineId = 1;</code>
       */
      public Builder setMachineId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        machineId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string machineId = 1;</code>
       */
      public Builder clearMachineId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        machineId_ = getDefaultInstance().getMachineId();
        onChanged();
        return this;
      }
      /**
       * <code>required string machineId = 1;</code>
       */
      public Builder setMachineIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        machineId_ = value;
        onChanged();
        return this;
      }

      // optional int32 raceType = 2;
      private int raceType_ ;
      /**
       * <code>optional int32 raceType = 2;</code>
       *
       * <pre>
       *参考PlayerVO.raceType注释
       * </pre>
       */
      public boolean hasRaceType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 raceType = 2;</code>
       *
       * <pre>
       *参考PlayerVO.raceType注释
       * </pre>
       */
      public int getRaceType() {
        return raceType_;
      }
      /**
       * <code>optional int32 raceType = 2;</code>
       *
       * <pre>
       *参考PlayerVO.raceType注释
       * </pre>
       */
      public Builder setRaceType(int value) {
        bitField0_ |= 0x00000002;
        raceType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 raceType = 2;</code>
       *
       * <pre>
       *参考PlayerVO.raceType注释
       * </pre>
       */
      public Builder clearRaceType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        raceType_ = 0;
        onChanged();
        return this;
      }

      // optional string userName = 3;
      private java.lang.Object userName_ = "";
      /**
       * <code>optional string userName = 3;</code>
       */
      public boolean hasUserName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public java.lang.String getUserName() {
        java.lang.Object ref = userName_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          userName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getUserNameBytes() {
        java.lang.Object ref = userName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder setUserName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        userName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder clearUserName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        userName_ = getDefaultInstance().getUserName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder setUserNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        userName_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:NoAuthLoginReq)
    }

    static {
      defaultInstance = new NoAuthLoginReq(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:NoAuthLoginReq)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_NoAuthLoginReq_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_NoAuthLoginReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024NoAuthLoginReq.proto\"G\n\016NoAuthLoginReq" +
      "\022\021\n\tmachineId\030\001 \002(\t\022\020\n\010raceType\030\002 \001(\005\022\020\n" +
      "\010userName\030\003 \001(\tB6\n\034com.pureland.common.p" +
      "rotocalB\026NoAuthLoginReqProtocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_NoAuthLoginReq_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_NoAuthLoginReq_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_NoAuthLoginReq_descriptor,
              new java.lang.String[] { "MachineId", "RaceType", "UserName", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}