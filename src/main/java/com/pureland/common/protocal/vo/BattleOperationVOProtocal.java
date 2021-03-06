// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BattleOperationVO.proto

package com.pureland.common.protocal.vo;

public final class BattleOperationVOProtocal {
  private BattleOperationVOProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface BattleOperationVOOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 frame = 1;
    /**
     * <code>required int32 frame = 1;</code>
     */
    boolean hasFrame();
    /**
     * <code>required int32 frame = 1;</code>
     */
    int getFrame();

    // required .BattleOperationVO.BattleOperationType battleOperationType = 2;
    /**
     * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
     */
    boolean hasBattleOperationType();
    /**
     * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
     */
    com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType getBattleOperationType();

    // optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    boolean hasBattleOperationPlaceSoldierVO();
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO getBattleOperationPlaceSoldierVO();
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder getBattleOperationPlaceSoldierVOOrBuilder();
  }
  /**
   * Protobuf type {@code BattleOperationVO}
   */
  public static final class BattleOperationVO extends
      com.google.protobuf.GeneratedMessage
      implements BattleOperationVOOrBuilder {
    // Use BattleOperationVO.newBuilder() to construct.
    private BattleOperationVO(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private BattleOperationVO(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final BattleOperationVO defaultInstance;
    public static BattleOperationVO getDefaultInstance() {
      return defaultInstance;
    }

    public BattleOperationVO getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private BattleOperationVO(
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
              frame_ = input.readInt32();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType value = com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                battleOperationType_ = value;
              }
              break;
            }
            case 26: {
              com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder subBuilder = null;
              if (((bitField0_ & 0x00000004) == 0x00000004)) {
                subBuilder = battleOperationPlaceSoldierVO_.toBuilder();
              }
              battleOperationPlaceSoldierVO_ = input.readMessage(com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(battleOperationPlaceSoldierVO_);
                battleOperationPlaceSoldierVO_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000004;
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
      return com.pureland.common.protocal.vo.BattleOperationVOProtocal.internal_static_BattleOperationVO_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.vo.BattleOperationVOProtocal.internal_static_BattleOperationVO_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.class, com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.Builder.class);
    }

    public static com.google.protobuf.Parser<BattleOperationVO> PARSER =
        new com.google.protobuf.AbstractParser<BattleOperationVO>() {
      public BattleOperationVO parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BattleOperationVO(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<BattleOperationVO> getParserForType() {
      return PARSER;
    }

    /**
     * Protobuf enum {@code BattleOperationVO.BattleOperationType}
     */
    public enum BattleOperationType
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>PlaceSoldier = 1;</code>
       */
      PlaceSoldier(0, 1),
      ;

      /**
       * <code>PlaceSoldier = 1;</code>
       */
      public static final int PlaceSoldier_VALUE = 1;


      public final int getNumber() { return value; }

      public static BattleOperationType valueOf(int value) {
        switch (value) {
          case 1: return PlaceSoldier;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<BattleOperationType>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<BattleOperationType>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<BattleOperationType>() {
              public BattleOperationType findValueByNumber(int number) {
                return BattleOperationType.valueOf(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(index);
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.getDescriptor().getEnumTypes().get(0);
      }

      private static final BattleOperationType[] VALUES = values();

      public static BattleOperationType valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int index;
      private final int value;

      private BattleOperationType(int index, int value) {
        this.index = index;
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:BattleOperationVO.BattleOperationType)
    }

    private int bitField0_;
    // required int32 frame = 1;
    public static final int FRAME_FIELD_NUMBER = 1;
    private int frame_;
    /**
     * <code>required int32 frame = 1;</code>
     */
    public boolean hasFrame() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 frame = 1;</code>
     */
    public int getFrame() {
      return frame_;
    }

    // required .BattleOperationVO.BattleOperationType battleOperationType = 2;
    public static final int BATTLEOPERATIONTYPE_FIELD_NUMBER = 2;
    private com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType battleOperationType_;
    /**
     * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
     */
    public boolean hasBattleOperationType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
     */
    public com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType getBattleOperationType() {
      return battleOperationType_;
    }

    // optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;
    public static final int BATTLEOPERATIONPLACESOLDIERVO_FIELD_NUMBER = 3;
    private com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO_;
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    public boolean hasBattleOperationPlaceSoldierVO() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    public com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO getBattleOperationPlaceSoldierVO() {
      return battleOperationPlaceSoldierVO_;
    }
    /**
     * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
     */
    public com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder getBattleOperationPlaceSoldierVOOrBuilder() {
      return battleOperationPlaceSoldierVO_;
    }

    private void initFields() {
      frame_ = 0;
      battleOperationType_ = com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.PlaceSoldier;
      battleOperationPlaceSoldierVO_ = com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasFrame()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasBattleOperationType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (hasBattleOperationPlaceSoldierVO()) {
        if (!getBattleOperationPlaceSoldierVO().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, frame_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, battleOperationType_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeMessage(3, battleOperationPlaceSoldierVO_);
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
          .computeInt32Size(1, frame_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, battleOperationType_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, battleOperationPlaceSoldierVO_);
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

    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO prototype) {
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
     * Protobuf type {@code BattleOperationVO}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVOOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.vo.BattleOperationVOProtocal.internal_static_BattleOperationVO_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.vo.BattleOperationVOProtocal.internal_static_BattleOperationVO_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.class, com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.Builder.class);
      }

      // Construct using com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.newBuilder()
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
          getBattleOperationPlaceSoldierVOFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        frame_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        battleOperationType_ = com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.PlaceSoldier;
        bitField0_ = (bitField0_ & ~0x00000002);
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          battleOperationPlaceSoldierVO_ = com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.getDefaultInstance();
        } else {
          battleOperationPlaceSoldierVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.vo.BattleOperationVOProtocal.internal_static_BattleOperationVO_descriptor;
      }

      public com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO getDefaultInstanceForType() {
        return com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.getDefaultInstance();
      }

      public com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO build() {
        com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO buildPartial() {
        com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO result = new com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.frame_ = frame_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.battleOperationType_ = battleOperationType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          result.battleOperationPlaceSoldierVO_ = battleOperationPlaceSoldierVO_;
        } else {
          result.battleOperationPlaceSoldierVO_ = battleOperationPlaceSoldierVOBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO) {
          return mergeFrom((com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO other) {
        if (other == com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.getDefaultInstance()) return this;
        if (other.hasFrame()) {
          setFrame(other.getFrame());
        }
        if (other.hasBattleOperationType()) {
          setBattleOperationType(other.getBattleOperationType());
        }
        if (other.hasBattleOperationPlaceSoldierVO()) {
          mergeBattleOperationPlaceSoldierVO(other.getBattleOperationPlaceSoldierVO());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFrame()) {
          
          return false;
        }
        if (!hasBattleOperationType()) {
          
          return false;
        }
        if (hasBattleOperationPlaceSoldierVO()) {
          if (!getBattleOperationPlaceSoldierVO().isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 frame = 1;
      private int frame_ ;
      /**
       * <code>required int32 frame = 1;</code>
       */
      public boolean hasFrame() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 frame = 1;</code>
       */
      public int getFrame() {
        return frame_;
      }
      /**
       * <code>required int32 frame = 1;</code>
       */
      public Builder setFrame(int value) {
        bitField0_ |= 0x00000001;
        frame_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 frame = 1;</code>
       */
      public Builder clearFrame() {
        bitField0_ = (bitField0_ & ~0x00000001);
        frame_ = 0;
        onChanged();
        return this;
      }

      // required .BattleOperationVO.BattleOperationType battleOperationType = 2;
      private com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType battleOperationType_ = com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.PlaceSoldier;
      /**
       * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
       */
      public boolean hasBattleOperationType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
       */
      public com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType getBattleOperationType() {
        return battleOperationType_;
      }
      /**
       * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
       */
      public Builder setBattleOperationType(com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        battleOperationType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .BattleOperationVO.BattleOperationType battleOperationType = 2;</code>
       */
      public Builder clearBattleOperationType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        battleOperationType_ = com.pureland.common.protocal.vo.BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.PlaceSoldier;
        onChanged();
        return this;
      }

      // optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;
      private com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO_ = com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder> battleOperationPlaceSoldierVOBuilder_;
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public boolean hasBattleOperationPlaceSoldierVO() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO getBattleOperationPlaceSoldierVO() {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          return battleOperationPlaceSoldierVO_;
        } else {
          return battleOperationPlaceSoldierVOBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public Builder setBattleOperationPlaceSoldierVO(com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO value) {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          battleOperationPlaceSoldierVO_ = value;
          onChanged();
        } else {
          battleOperationPlaceSoldierVOBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public Builder setBattleOperationPlaceSoldierVO(
          com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder builderForValue) {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          battleOperationPlaceSoldierVO_ = builderForValue.build();
          onChanged();
        } else {
          battleOperationPlaceSoldierVOBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public Builder mergeBattleOperationPlaceSoldierVO(com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO value) {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          if (((bitField0_ & 0x00000004) == 0x00000004) &&
              battleOperationPlaceSoldierVO_ != com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.getDefaultInstance()) {
            battleOperationPlaceSoldierVO_ =
              com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.newBuilder(battleOperationPlaceSoldierVO_).mergeFrom(value).buildPartial();
          } else {
            battleOperationPlaceSoldierVO_ = value;
          }
          onChanged();
        } else {
          battleOperationPlaceSoldierVOBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public Builder clearBattleOperationPlaceSoldierVO() {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          battleOperationPlaceSoldierVO_ = com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.getDefaultInstance();
          onChanged();
        } else {
          battleOperationPlaceSoldierVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder getBattleOperationPlaceSoldierVOBuilder() {
        bitField0_ |= 0x00000004;
        onChanged();
        return getBattleOperationPlaceSoldierVOFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      public com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder getBattleOperationPlaceSoldierVOOrBuilder() {
        if (battleOperationPlaceSoldierVOBuilder_ != null) {
          return battleOperationPlaceSoldierVOBuilder_.getMessageOrBuilder();
        } else {
          return battleOperationPlaceSoldierVO_;
        }
      }
      /**
       * <code>optional .BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder> 
          getBattleOperationPlaceSoldierVOFieldBuilder() {
        if (battleOperationPlaceSoldierVOBuilder_ == null) {
          battleOperationPlaceSoldierVOBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.Builder, com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVOOrBuilder>(
                  battleOperationPlaceSoldierVO_,
                  getParentForChildren(),
                  isClean());
          battleOperationPlaceSoldierVO_ = null;
        }
        return battleOperationPlaceSoldierVOBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:BattleOperationVO)
    }

    static {
      defaultInstance = new BattleOperationVO(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:BattleOperationVO)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_BattleOperationVO_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_BattleOperationVO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027BattleOperationVO.proto\032#BattleOperati" +
      "onPlaceSoldierVO.proto\"\327\001\n\021BattleOperati" +
      "onVO\022\r\n\005frame\030\001 \002(\005\022C\n\023battleOperationTy" +
      "pe\030\002 \002(\0162&.BattleOperationVO.BattleOpera" +
      "tionType\022E\n\035battleOperationPlaceSoldierV" +
      "O\030\003 \001(\0132\036.BattleOperationPlaceSoldierVO\"" +
      "\'\n\023BattleOperationType\022\020\n\014PlaceSoldier\020\001" +
      "B<\n\037com.pureland.common.protocal.voB\031Bat" +
      "tleOperationVOProtocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_BattleOperationVO_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_BattleOperationVO_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_BattleOperationVO_descriptor,
              new java.lang.String[] { "Frame", "BattleOperationType", "BattleOperationPlaceSoldierVO", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.pureland.common.protocal.vo.BattleOperationPlaceSoldierVOProtocal.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
