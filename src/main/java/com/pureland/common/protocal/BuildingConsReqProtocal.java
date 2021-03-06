// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BuildingConsReq.proto

package com.pureland.common.protocal;

public final class BuildingConsReqProtocal {
  private BuildingConsReqProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface BuildingConsReqOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .BuildingVO buildingVO = 1;
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    boolean hasBuildingVO();
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO getBuildingVO();
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder getBuildingVOOrBuilder();

    // required .ResourceType resourceType = 2;
    /**
     * <code>required .ResourceType resourceType = 2;</code>
     */
    boolean hasResourceType();
    /**
     * <code>required .ResourceType resourceType = 2;</code>
     */
    com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType getResourceType();
  }
  /**
   * Protobuf type {@code BuildingConsReq}
   */
  public static final class BuildingConsReq extends
      com.google.protobuf.GeneratedMessage
      implements BuildingConsReqOrBuilder {
    // Use BuildingConsReq.newBuilder() to construct.
    private BuildingConsReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private BuildingConsReq(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final BuildingConsReq defaultInstance;
    public static BuildingConsReq getDefaultInstance() {
      return defaultInstance;
    }

    public BuildingConsReq getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private BuildingConsReq(
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
              com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = buildingVO_.toBuilder();
              }
              buildingVO_ = input.readMessage(com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(buildingVO_);
                buildingVO_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType value = com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                resourceType_ = value;
              }
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
      return com.pureland.common.protocal.BuildingConsReqProtocal.internal_static_BuildingConsReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.BuildingConsReqProtocal.internal_static_BuildingConsReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.class, com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.Builder.class);
    }

    public static com.google.protobuf.Parser<BuildingConsReq> PARSER =
        new com.google.protobuf.AbstractParser<BuildingConsReq>() {
      public BuildingConsReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuildingConsReq(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<BuildingConsReq> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .BuildingVO buildingVO = 1;
    public static final int BUILDINGVO_FIELD_NUMBER = 1;
    private com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO buildingVO_;
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    public boolean hasBuildingVO() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    public com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO getBuildingVO() {
      return buildingVO_;
    }
    /**
     * <code>required .BuildingVO buildingVO = 1;</code>
     */
    public com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder getBuildingVOOrBuilder() {
      return buildingVO_;
    }

    // required .ResourceType resourceType = 2;
    public static final int RESOURCETYPE_FIELD_NUMBER = 2;
    private com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType resourceType_;
    /**
     * <code>required .ResourceType resourceType = 2;</code>
     */
    public boolean hasResourceType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .ResourceType resourceType = 2;</code>
     */
    public com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType getResourceType() {
      return resourceType_;
    }

    private void initFields() {
      buildingVO_ = com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.getDefaultInstance();
      resourceType_ = com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType.None;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasBuildingVO()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasResourceType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getBuildingVO().isInitialized()) {
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
        output.writeMessage(1, buildingVO_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, resourceType_.getNumber());
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
          .computeMessageSize(1, buildingVO_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, resourceType_.getNumber());
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

    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq prototype) {
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
     * Protobuf type {@code BuildingConsReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.BuildingConsReqProtocal.internal_static_BuildingConsReq_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.BuildingConsReqProtocal.internal_static_BuildingConsReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.class, com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.Builder.class);
      }

      // Construct using com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.newBuilder()
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
          getBuildingVOFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (buildingVOBuilder_ == null) {
          buildingVO_ = com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.getDefaultInstance();
        } else {
          buildingVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        resourceType_ = com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType.None;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.BuildingConsReqProtocal.internal_static_BuildingConsReq_descriptor;
      }

      public com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq getDefaultInstanceForType() {
        return com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.getDefaultInstance();
      }

      public com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq build() {
        com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq buildPartial() {
        com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq result = new com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (buildingVOBuilder_ == null) {
          result.buildingVO_ = buildingVO_;
        } else {
          result.buildingVO_ = buildingVOBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.resourceType_ = resourceType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq) {
          return mergeFrom((com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq other) {
        if (other == com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq.getDefaultInstance()) return this;
        if (other.hasBuildingVO()) {
          mergeBuildingVO(other.getBuildingVO());
        }
        if (other.hasResourceType()) {
          setResourceType(other.getResourceType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasBuildingVO()) {
          
          return false;
        }
        if (!hasResourceType()) {
          
          return false;
        }
        if (!getBuildingVO().isInitialized()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .BuildingVO buildingVO = 1;
      private com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO buildingVO_ = com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder> buildingVOBuilder_;
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public boolean hasBuildingVO() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO getBuildingVO() {
        if (buildingVOBuilder_ == null) {
          return buildingVO_;
        } else {
          return buildingVOBuilder_.getMessage();
        }
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public Builder setBuildingVO(com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO value) {
        if (buildingVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          buildingVO_ = value;
          onChanged();
        } else {
          buildingVOBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public Builder setBuildingVO(
          com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder builderForValue) {
        if (buildingVOBuilder_ == null) {
          buildingVO_ = builderForValue.build();
          onChanged();
        } else {
          buildingVOBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public Builder mergeBuildingVO(com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO value) {
        if (buildingVOBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              buildingVO_ != com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.getDefaultInstance()) {
            buildingVO_ =
              com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.newBuilder(buildingVO_).mergeFrom(value).buildPartial();
          } else {
            buildingVO_ = value;
          }
          onChanged();
        } else {
          buildingVOBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public Builder clearBuildingVO() {
        if (buildingVOBuilder_ == null) {
          buildingVO_ = com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.getDefaultInstance();
          onChanged();
        } else {
          buildingVOBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder getBuildingVOBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getBuildingVOFieldBuilder().getBuilder();
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      public com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder getBuildingVOOrBuilder() {
        if (buildingVOBuilder_ != null) {
          return buildingVOBuilder_.getMessageOrBuilder();
        } else {
          return buildingVO_;
        }
      }
      /**
       * <code>required .BuildingVO buildingVO = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder> 
          getBuildingVOFieldBuilder() {
        if (buildingVOBuilder_ == null) {
          buildingVOBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO.Builder, com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVOOrBuilder>(
                  buildingVO_,
                  getParentForChildren(),
                  isClean());
          buildingVO_ = null;
        }
        return buildingVOBuilder_;
      }

      // required .ResourceType resourceType = 2;
      private com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType resourceType_ = com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType.None;
      /**
       * <code>required .ResourceType resourceType = 2;</code>
       */
      public boolean hasResourceType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .ResourceType resourceType = 2;</code>
       */
      public com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType getResourceType() {
        return resourceType_;
      }
      /**
       * <code>required .ResourceType resourceType = 2;</code>
       */
      public Builder setResourceType(com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        resourceType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .ResourceType resourceType = 2;</code>
       */
      public Builder clearResourceType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        resourceType_ = com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType.None;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:BuildingConsReq)
    }

    static {
      defaultInstance = new BuildingConsReq(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:BuildingConsReq)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_BuildingConsReq_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_BuildingConsReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025BuildingConsReq.proto\032\020BuildingVO.prot" +
      "o\032\022ResourceType.proto\"W\n\017BuildingConsReq" +
      "\022\037\n\nbuildingVO\030\001 \002(\0132\013.BuildingVO\022#\n\014res" +
      "ourceType\030\002 \002(\0162\r.ResourceTypeB7\n\034com.pu" +
      "reland.common.protocalB\027BuildingConsReqP" +
      "rotocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_BuildingConsReq_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_BuildingConsReq_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_BuildingConsReq_descriptor,
              new java.lang.String[] { "BuildingVO", "ResourceType", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.pureland.common.protocal.vo.BuildingVOProtocal.getDescriptor(),
          com.pureland.common.protocal.vo.ResourceTypeProtocal.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
