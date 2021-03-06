// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ArmyExpVO.proto

package com.pureland.common.protocal.vo;

public final class ArmyExpVOProtocal {
  private ArmyExpVOProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ArmyExpVOOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 cid = 1;
    /**
     * <code>required int32 cid = 1;</code>
     *
     * <pre>
     *当前等级的cid
     * </pre>
     */
    boolean hasCid();
    /**
     * <code>required int32 cid = 1;</code>
     *
     * <pre>
     *当前等级的cid
     * </pre>
     */
    int getCid();

    // required int32 exp = 2;
    /**
     * <code>required int32 exp = 2;</code>
     *
     * <pre>
     *这一类兵的总训练数量(经验)
     * </pre>
     */
    boolean hasExp();
    /**
     * <code>required int32 exp = 2;</code>
     *
     * <pre>
     *这一类兵的总训练数量(经验)
     * </pre>
     */
    int getExp();
  }
  /**
   * Protobuf type {@code ArmyExpVO}
   */
  public static final class ArmyExpVO extends
      com.google.protobuf.GeneratedMessage
      implements ArmyExpVOOrBuilder {
    // Use ArmyExpVO.newBuilder() to construct.
    private ArmyExpVO(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ArmyExpVO(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ArmyExpVO defaultInstance;
    public static ArmyExpVO getDefaultInstance() {
      return defaultInstance;
    }

    public ArmyExpVO getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ArmyExpVO(
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
              cid_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              exp_ = input.readInt32();
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
      return com.pureland.common.protocal.vo.ArmyExpVOProtocal.internal_static_ArmyExpVO_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.vo.ArmyExpVOProtocal.internal_static_ArmyExpVO_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.class, com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.Builder.class);
    }

    public static com.google.protobuf.Parser<ArmyExpVO> PARSER =
        new com.google.protobuf.AbstractParser<ArmyExpVO>() {
      public ArmyExpVO parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ArmyExpVO(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ArmyExpVO> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 cid = 1;
    public static final int CID_FIELD_NUMBER = 1;
    private int cid_;
    /**
     * <code>required int32 cid = 1;</code>
     *
     * <pre>
     *当前等级的cid
     * </pre>
     */
    public boolean hasCid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 cid = 1;</code>
     *
     * <pre>
     *当前等级的cid
     * </pre>
     */
    public int getCid() {
      return cid_;
    }

    // required int32 exp = 2;
    public static final int EXP_FIELD_NUMBER = 2;
    private int exp_;
    /**
     * <code>required int32 exp = 2;</code>
     *
     * <pre>
     *这一类兵的总训练数量(经验)
     * </pre>
     */
    public boolean hasExp() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 exp = 2;</code>
     *
     * <pre>
     *这一类兵的总训练数量(经验)
     * </pre>
     */
    public int getExp() {
      return exp_;
    }

    private void initFields() {
      cid_ = 0;
      exp_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasCid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasExp()) {
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
        output.writeInt32(1, cid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, exp_);
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
          .computeInt32Size(1, cid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, exp_);
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

    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO prototype) {
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
     * Protobuf type {@code ArmyExpVO}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVOOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.vo.ArmyExpVOProtocal.internal_static_ArmyExpVO_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.vo.ArmyExpVOProtocal.internal_static_ArmyExpVO_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.class, com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.Builder.class);
      }

      // Construct using com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.newBuilder()
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
        cid_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        exp_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.vo.ArmyExpVOProtocal.internal_static_ArmyExpVO_descriptor;
      }

      public com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO getDefaultInstanceForType() {
        return com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.getDefaultInstance();
      }

      public com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO build() {
        com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO buildPartial() {
        com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO result = new com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.cid_ = cid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.exp_ = exp_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO) {
          return mergeFrom((com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO other) {
        if (other == com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO.getDefaultInstance()) return this;
        if (other.hasCid()) {
          setCid(other.getCid());
        }
        if (other.hasExp()) {
          setExp(other.getExp());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCid()) {
          
          return false;
        }
        if (!hasExp()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.vo.ArmyExpVOProtocal.ArmyExpVO) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 cid = 1;
      private int cid_ ;
      /**
       * <code>required int32 cid = 1;</code>
       *
       * <pre>
       *当前等级的cid
       * </pre>
       */
      public boolean hasCid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 cid = 1;</code>
       *
       * <pre>
       *当前等级的cid
       * </pre>
       */
      public int getCid() {
        return cid_;
      }
      /**
       * <code>required int32 cid = 1;</code>
       *
       * <pre>
       *当前等级的cid
       * </pre>
       */
      public Builder setCid(int value) {
        bitField0_ |= 0x00000001;
        cid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 cid = 1;</code>
       *
       * <pre>
       *当前等级的cid
       * </pre>
       */
      public Builder clearCid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        cid_ = 0;
        onChanged();
        return this;
      }

      // required int32 exp = 2;
      private int exp_ ;
      /**
       * <code>required int32 exp = 2;</code>
       *
       * <pre>
       *这一类兵的总训练数量(经验)
       * </pre>
       */
      public boolean hasExp() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 exp = 2;</code>
       *
       * <pre>
       *这一类兵的总训练数量(经验)
       * </pre>
       */
      public int getExp() {
        return exp_;
      }
      /**
       * <code>required int32 exp = 2;</code>
       *
       * <pre>
       *这一类兵的总训练数量(经验)
       * </pre>
       */
      public Builder setExp(int value) {
        bitField0_ |= 0x00000002;
        exp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 exp = 2;</code>
       *
       * <pre>
       *这一类兵的总训练数量(经验)
       * </pre>
       */
      public Builder clearExp() {
        bitField0_ = (bitField0_ & ~0x00000002);
        exp_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ArmyExpVO)
    }

    static {
      defaultInstance = new ArmyExpVO(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ArmyExpVO)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ArmyExpVO_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ArmyExpVO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017ArmyExpVO.proto\"%\n\tArmyExpVO\022\013\n\003cid\030\001 " +
      "\002(\005\022\013\n\003exp\030\002 \002(\005B4\n\037com.pureland.common." +
      "protocal.voB\021ArmyExpVOProtocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_ArmyExpVO_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_ArmyExpVO_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ArmyExpVO_descriptor,
              new java.lang.String[] { "Cid", "Exp", });
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
