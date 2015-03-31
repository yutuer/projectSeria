// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GatherReq.proto

package com.pureland.common.protocal;

public final class GatherReqProtocal {
  private GatherReqProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GatherReqOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int64 sid = 1;
    /**
     * <code>required int64 sid = 1;</code>
     */
    boolean hasSid();
    /**
     * <code>required int64 sid = 1;</code>
     */
    long getSid();

    // required .ResourceVO resourceVO = 2;
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    boolean hasResourceVO();
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO getResourceVO();
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder getResourceVOOrBuilder();

    // required int64 gatherTime = 3;
    /**
     * <code>required int64 gatherTime = 3;</code>
     */
    boolean hasGatherTime();
    /**
     * <code>required int64 gatherTime = 3;</code>
     */
    long getGatherTime();
  }
  /**
   * Protobuf type {@code GatherReq}
   */
  public static final class GatherReq extends
      com.google.protobuf.GeneratedMessage
      implements GatherReqOrBuilder {
    // Use GatherReq.newBuilder() to construct.
    private GatherReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GatherReq(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GatherReq defaultInstance;
    public static GatherReq getDefaultInstance() {
      return defaultInstance;
    }

    public GatherReq getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GatherReq(
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
            case 8: {
              bitField0_ |= 0x00000001;
              sid_ = input.readInt64();
              break;
            }
            case 18: {
              com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder subBuilder = null;
              if (((bitField0_ & 0x00000002) == 0x00000002)) {
                subBuilder = resourceVO_.toBuilder();
              }
              resourceVO_ = input.readMessage(com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(resourceVO_);
                resourceVO_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000002;
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              gatherTime_ = input.readInt64();
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
      return com.pureland.common.protocal.GatherReqProtocal.internal_static_GatherReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.GatherReqProtocal.internal_static_GatherReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.GatherReqProtocal.GatherReq.class, com.pureland.common.protocal.GatherReqProtocal.GatherReq.Builder.class);
    }

    public static com.google.protobuf.Parser<GatherReq> PARSER =
        new com.google.protobuf.AbstractParser<GatherReq>() {
      public GatherReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GatherReq(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GatherReq> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int64 sid = 1;
    public static final int SID_FIELD_NUMBER = 1;
    private long sid_;
    /**
     * <code>required int64 sid = 1;</code>
     */
    public boolean hasSid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 sid = 1;</code>
     */
    public long getSid() {
      return sid_;
    }

    // required .ResourceVO resourceVO = 2;
    public static final int RESOURCEVO_FIELD_NUMBER = 2;
    private com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO resourceVO_;
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    public boolean hasResourceVO() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    public com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO getResourceVO() {
      return resourceVO_;
    }
    /**
     * <code>required .ResourceVO resourceVO = 2;</code>
     */
    public com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder getResourceVOOrBuilder() {
      return resourceVO_;
    }

    // required int64 gatherTime = 3;
    public static final int GATHERTIME_FIELD_NUMBER = 3;
    private long gatherTime_;
    /**
     * <code>required int64 gatherTime = 3;</code>
     */
    public boolean hasGatherTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int64 gatherTime = 3;</code>
     */
    public long getGatherTime() {
      return gatherTime_;
    }

    private void initFields() {
      sid_ = 0L;
      resourceVO_ = com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.getDefaultInstance();
      gatherTime_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasSid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasResourceVO()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasGatherTime()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getResourceVO().isInitialized()) {
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
        output.writeInt64(1, sid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, resourceVO_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt64(3, gatherTime_);
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
          .computeInt64Size(1, sid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, resourceVO_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, gatherTime_);
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

    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.GatherReqProtocal.GatherReq parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.GatherReqProtocal.GatherReq prototype) {
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
     * Protobuf type {@code GatherReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.GatherReqProtocal.GatherReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.GatherReqProtocal.internal_static_GatherReq_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.GatherReqProtocal.internal_static_GatherReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.GatherReqProtocal.GatherReq.class, com.pureland.common.protocal.GatherReqProtocal.GatherReq.Builder.class);
      }

      // Construct using com.pureland.common.protocal.GatherReqProtocal.GatherReq.newBuilder()
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
          getResourceVOFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        sid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        if (resourceVOBuilder_ == null) {
          resourceVO_ = com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.getDefaultInstance();
        } else {
          resourceVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        gatherTime_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.GatherReqProtocal.internal_static_GatherReq_descriptor;
      }

      public com.pureland.common.protocal.GatherReqProtocal.GatherReq getDefaultInstanceForType() {
        return com.pureland.common.protocal.GatherReqProtocal.GatherReq.getDefaultInstance();
      }

      public com.pureland.common.protocal.GatherReqProtocal.GatherReq build() {
        com.pureland.common.protocal.GatherReqProtocal.GatherReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.GatherReqProtocal.GatherReq buildPartial() {
        com.pureland.common.protocal.GatherReqProtocal.GatherReq result = new com.pureland.common.protocal.GatherReqProtocal.GatherReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.sid_ = sid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (resourceVOBuilder_ == null) {
          result.resourceVO_ = resourceVO_;
        } else {
          result.resourceVO_ = resourceVOBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.gatherTime_ = gatherTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.GatherReqProtocal.GatherReq) {
          return mergeFrom((com.pureland.common.protocal.GatherReqProtocal.GatherReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.GatherReqProtocal.GatherReq other) {
        if (other == com.pureland.common.protocal.GatherReqProtocal.GatherReq.getDefaultInstance()) return this;
        if (other.hasSid()) {
          setSid(other.getSid());
        }
        if (other.hasResourceVO()) {
          mergeResourceVO(other.getResourceVO());
        }
        if (other.hasGatherTime()) {
          setGatherTime(other.getGatherTime());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasSid()) {
          
          return false;
        }
        if (!hasResourceVO()) {
          
          return false;
        }
        if (!hasGatherTime()) {
          
          return false;
        }
        if (!getResourceVO().isInitialized()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.GatherReqProtocal.GatherReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.GatherReqProtocal.GatherReq) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int64 sid = 1;
      private long sid_ ;
      /**
       * <code>required int64 sid = 1;</code>
       */
      public boolean hasSid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 sid = 1;</code>
       */
      public long getSid() {
        return sid_;
      }
      /**
       * <code>required int64 sid = 1;</code>
       */
      public Builder setSid(long value) {
        bitField0_ |= 0x00000001;
        sid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 sid = 1;</code>
       */
      public Builder clearSid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        sid_ = 0L;
        onChanged();
        return this;
      }

      // required .ResourceVO resourceVO = 2;
      private com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO resourceVO_ = com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder> resourceVOBuilder_;
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public boolean hasResourceVO() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO getResourceVO() {
        if (resourceVOBuilder_ == null) {
          return resourceVO_;
        } else {
          return resourceVOBuilder_.getMessage();
        }
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public Builder setResourceVO(com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO value) {
        if (resourceVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          resourceVO_ = value;
          onChanged();
        } else {
          resourceVOBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public Builder setResourceVO(
          com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder builderForValue) {
        if (resourceVOBuilder_ == null) {
          resourceVO_ = builderForValue.build();
          onChanged();
        } else {
          resourceVOBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public Builder mergeResourceVO(com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO value) {
        if (resourceVOBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              resourceVO_ != com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.getDefaultInstance()) {
            resourceVO_ =
              com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.newBuilder(resourceVO_).mergeFrom(value).buildPartial();
          } else {
            resourceVO_ = value;
          }
          onChanged();
        } else {
          resourceVOBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public Builder clearResourceVO() {
        if (resourceVOBuilder_ == null) {
          resourceVO_ = com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.getDefaultInstance();
          onChanged();
        } else {
          resourceVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder getResourceVOBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getResourceVOFieldBuilder().getBuilder();
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder getResourceVOOrBuilder() {
        if (resourceVOBuilder_ != null) {
          return resourceVOBuilder_.getMessageOrBuilder();
        } else {
          return resourceVO_;
        }
      }
      /**
       * <code>required .ResourceVO resourceVO = 2;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder> 
          getResourceVOFieldBuilder() {
        if (resourceVOBuilder_ == null) {
          resourceVOBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO.Builder, com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVOOrBuilder>(
                  resourceVO_,
                  getParentForChildren(),
                  isClean());
          resourceVO_ = null;
        }
        return resourceVOBuilder_;
      }

      // required int64 gatherTime = 3;
      private long gatherTime_ ;
      /**
       * <code>required int64 gatherTime = 3;</code>
       */
      public boolean hasGatherTime() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int64 gatherTime = 3;</code>
       */
      public long getGatherTime() {
        return gatherTime_;
      }
      /**
       * <code>required int64 gatherTime = 3;</code>
       */
      public Builder setGatherTime(long value) {
        bitField0_ |= 0x00000004;
        gatherTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 gatherTime = 3;</code>
       */
      public Builder clearGatherTime() {
        bitField0_ = (bitField0_ & ~0x00000004);
        gatherTime_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:GatherReq)
    }

    static {
      defaultInstance = new GatherReq(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GatherReq)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_GatherReq_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GatherReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017GatherReq.proto\032\020ResourceVO.proto\"M\n\tG" +
      "atherReq\022\013\n\003sid\030\001 \002(\003\022\037\n\nresourceVO\030\002 \002(" +
      "\0132\013.ResourceVO\022\022\n\ngatherTime\030\003 \002(\003B1\n\034co" +
      "m.pureland.common.protocalB\021GatherReqPro" +
      "tocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_GatherReq_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_GatherReq_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_GatherReq_descriptor,
              new java.lang.String[] { "Sid", "ResourceVO", "GatherTime", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.pureland.common.protocal.vo.ResourceVOProtocal.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}