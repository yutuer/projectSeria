// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatResp.proto

package com.pureland.common.protocal;

public final class ChatRespProtocal {
  private ChatRespProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ChatRespOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional .ChatChannel chatChannel = 1 [default = Global];
    /**
     * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
     */
    boolean hasChatChannel();
    /**
     * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
     */
    com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel getChatChannel();

    // repeated .ChatVO chatVO = 2;
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> 
        getChatVOList();
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO getChatVO(int index);
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    int getChatVOCount();
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    java.util.List<? extends com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder> 
        getChatVOOrBuilderList();
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder getChatVOOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code ChatResp}
   */
  public static final class ChatResp extends
      com.google.protobuf.GeneratedMessage
      implements ChatRespOrBuilder {
    // Use ChatResp.newBuilder() to construct.
    private ChatResp(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ChatResp(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ChatResp defaultInstance;
    public static ChatResp getDefaultInstance() {
      return defaultInstance;
    }

    public ChatResp getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ChatResp(
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
              int rawValue = input.readEnum();
              com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel value = com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                chatChannel_ = value;
              }
              break;
            }
            case 18: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                chatVO_ = new java.util.ArrayList<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO>();
                mutable_bitField0_ |= 0x00000002;
              }
              chatVO_.add(input.readMessage(com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.PARSER, extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          chatVO_ = java.util.Collections.unmodifiableList(chatVO_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.pureland.common.protocal.ChatRespProtocal.internal_static_ChatResp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pureland.common.protocal.ChatRespProtocal.internal_static_ChatResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pureland.common.protocal.ChatRespProtocal.ChatResp.class, com.pureland.common.protocal.ChatRespProtocal.ChatResp.Builder.class);
    }

    public static com.google.protobuf.Parser<ChatResp> PARSER =
        new com.google.protobuf.AbstractParser<ChatResp>() {
      public ChatResp parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ChatResp(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ChatResp> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional .ChatChannel chatChannel = 1 [default = Global];
    public static final int CHATCHANNEL_FIELD_NUMBER = 1;
    private com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel chatChannel_;
    /**
     * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
     */
    public boolean hasChatChannel() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
     */
    public com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel getChatChannel() {
      return chatChannel_;
    }

    // repeated .ChatVO chatVO = 2;
    public static final int CHATVO_FIELD_NUMBER = 2;
    private java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> chatVO_;
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    public java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> getChatVOList() {
      return chatVO_;
    }
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    public java.util.List<? extends com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder> 
        getChatVOOrBuilderList() {
      return chatVO_;
    }
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    public int getChatVOCount() {
      return chatVO_.size();
    }
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO getChatVO(int index) {
      return chatVO_.get(index);
    }
    /**
     * <code>repeated .ChatVO chatVO = 2;</code>
     */
    public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder getChatVOOrBuilder(
        int index) {
      return chatVO_.get(index);
    }

    private void initFields() {
      chatChannel_ = com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel.Global;
      chatVO_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      for (int i = 0; i < getChatVOCount(); i++) {
        if (!getChatVO(i).isInitialized()) {
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
        output.writeEnum(1, chatChannel_.getNumber());
      }
      for (int i = 0; i < chatVO_.size(); i++) {
        output.writeMessage(2, chatVO_.get(i));
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
          .computeEnumSize(1, chatChannel_.getNumber());
      }
      for (int i = 0; i < chatVO_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, chatVO_.get(i));
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

    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pureland.common.protocal.ChatRespProtocal.ChatResp parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pureland.common.protocal.ChatRespProtocal.ChatResp prototype) {
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
     * Protobuf type {@code ChatResp}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pureland.common.protocal.ChatRespProtocal.ChatRespOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pureland.common.protocal.ChatRespProtocal.internal_static_ChatResp_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pureland.common.protocal.ChatRespProtocal.internal_static_ChatResp_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pureland.common.protocal.ChatRespProtocal.ChatResp.class, com.pureland.common.protocal.ChatRespProtocal.ChatResp.Builder.class);
      }

      // Construct using com.pureland.common.protocal.ChatRespProtocal.ChatResp.newBuilder()
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
          getChatVOFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        chatChannel_ = com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel.Global;
        bitField0_ = (bitField0_ & ~0x00000001);
        if (chatVOBuilder_ == null) {
          chatVO_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          chatVOBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pureland.common.protocal.ChatRespProtocal.internal_static_ChatResp_descriptor;
      }

      public com.pureland.common.protocal.ChatRespProtocal.ChatResp getDefaultInstanceForType() {
        return com.pureland.common.protocal.ChatRespProtocal.ChatResp.getDefaultInstance();
      }

      public com.pureland.common.protocal.ChatRespProtocal.ChatResp build() {
        com.pureland.common.protocal.ChatRespProtocal.ChatResp result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pureland.common.protocal.ChatRespProtocal.ChatResp buildPartial() {
        com.pureland.common.protocal.ChatRespProtocal.ChatResp result = new com.pureland.common.protocal.ChatRespProtocal.ChatResp(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.chatChannel_ = chatChannel_;
        if (chatVOBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002)) {
            chatVO_ = java.util.Collections.unmodifiableList(chatVO_);
            bitField0_ = (bitField0_ & ~0x00000002);
          }
          result.chatVO_ = chatVO_;
        } else {
          result.chatVO_ = chatVOBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pureland.common.protocal.ChatRespProtocal.ChatResp) {
          return mergeFrom((com.pureland.common.protocal.ChatRespProtocal.ChatResp)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pureland.common.protocal.ChatRespProtocal.ChatResp other) {
        if (other == com.pureland.common.protocal.ChatRespProtocal.ChatResp.getDefaultInstance()) return this;
        if (other.hasChatChannel()) {
          setChatChannel(other.getChatChannel());
        }
        if (chatVOBuilder_ == null) {
          if (!other.chatVO_.isEmpty()) {
            if (chatVO_.isEmpty()) {
              chatVO_ = other.chatVO_;
              bitField0_ = (bitField0_ & ~0x00000002);
            } else {
              ensureChatVOIsMutable();
              chatVO_.addAll(other.chatVO_);
            }
            onChanged();
          }
        } else {
          if (!other.chatVO_.isEmpty()) {
            if (chatVOBuilder_.isEmpty()) {
              chatVOBuilder_.dispose();
              chatVOBuilder_ = null;
              chatVO_ = other.chatVO_;
              bitField0_ = (bitField0_ & ~0x00000002);
              chatVOBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getChatVOFieldBuilder() : null;
            } else {
              chatVOBuilder_.addAllMessages(other.chatVO_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        for (int i = 0; i < getChatVOCount(); i++) {
          if (!getChatVO(i).isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pureland.common.protocal.ChatRespProtocal.ChatResp parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pureland.common.protocal.ChatRespProtocal.ChatResp) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional .ChatChannel chatChannel = 1 [default = Global];
      private com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel chatChannel_ = com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel.Global;
      /**
       * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
       */
      public boolean hasChatChannel() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
       */
      public com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel getChatChannel() {
        return chatChannel_;
      }
      /**
       * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
       */
      public Builder setChatChannel(com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        chatChannel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional .ChatChannel chatChannel = 1 [default = Global];</code>
       */
      public Builder clearChatChannel() {
        bitField0_ = (bitField0_ & ~0x00000001);
        chatChannel_ = com.pureland.common.protocal.enums.ChatEnumProtocal.ChatChannel.Global;
        onChanged();
        return this;
      }

      // repeated .ChatVO chatVO = 2;
      private java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> chatVO_ =
        java.util.Collections.emptyList();
      private void ensureChatVOIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          chatVO_ = new java.util.ArrayList<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO>(chatVO_);
          bitField0_ |= 0x00000002;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder> chatVOBuilder_;

      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> getChatVOList() {
        if (chatVOBuilder_ == null) {
          return java.util.Collections.unmodifiableList(chatVO_);
        } else {
          return chatVOBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public int getChatVOCount() {
        if (chatVOBuilder_ == null) {
          return chatVO_.size();
        } else {
          return chatVOBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO getChatVO(int index) {
        if (chatVOBuilder_ == null) {
          return chatVO_.get(index);
        } else {
          return chatVOBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder setChatVO(
          int index, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO value) {
        if (chatVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureChatVOIsMutable();
          chatVO_.set(index, value);
          onChanged();
        } else {
          chatVOBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder setChatVO(
          int index, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder builderForValue) {
        if (chatVOBuilder_ == null) {
          ensureChatVOIsMutable();
          chatVO_.set(index, builderForValue.build());
          onChanged();
        } else {
          chatVOBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder addChatVO(com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO value) {
        if (chatVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureChatVOIsMutable();
          chatVO_.add(value);
          onChanged();
        } else {
          chatVOBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder addChatVO(
          int index, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO value) {
        if (chatVOBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureChatVOIsMutable();
          chatVO_.add(index, value);
          onChanged();
        } else {
          chatVOBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder addChatVO(
          com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder builderForValue) {
        if (chatVOBuilder_ == null) {
          ensureChatVOIsMutable();
          chatVO_.add(builderForValue.build());
          onChanged();
        } else {
          chatVOBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder addChatVO(
          int index, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder builderForValue) {
        if (chatVOBuilder_ == null) {
          ensureChatVOIsMutable();
          chatVO_.add(index, builderForValue.build());
          onChanged();
        } else {
          chatVOBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder addAllChatVO(
          java.lang.Iterable<? extends com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO> values) {
        if (chatVOBuilder_ == null) {
          ensureChatVOIsMutable();
          super.addAll(values, chatVO_);
          onChanged();
        } else {
          chatVOBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder clearChatVO() {
        if (chatVOBuilder_ == null) {
          chatVO_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
          onChanged();
        } else {
          chatVOBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public Builder removeChatVO(int index) {
        if (chatVOBuilder_ == null) {
          ensureChatVOIsMutable();
          chatVO_.remove(index);
          onChanged();
        } else {
          chatVOBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder getChatVOBuilder(
          int index) {
        return getChatVOFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder getChatVOOrBuilder(
          int index) {
        if (chatVOBuilder_ == null) {
          return chatVO_.get(index);  } else {
          return chatVOBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public java.util.List<? extends com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder> 
           getChatVOOrBuilderList() {
        if (chatVOBuilder_ != null) {
          return chatVOBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(chatVO_);
        }
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder addChatVOBuilder() {
        return getChatVOFieldBuilder().addBuilder(
            com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.getDefaultInstance());
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder addChatVOBuilder(
          int index) {
        return getChatVOFieldBuilder().addBuilder(
            index, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.getDefaultInstance());
      }
      /**
       * <code>repeated .ChatVO chatVO = 2;</code>
       */
      public java.util.List<com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder> 
           getChatVOBuilderList() {
        return getChatVOFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder> 
          getChatVOFieldBuilder() {
        if (chatVOBuilder_ == null) {
          chatVOBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO.Builder, com.pureland.common.protocal.vo.ChatVOProtocal.ChatVOOrBuilder>(
                  chatVO_,
                  ((bitField0_ & 0x00000002) == 0x00000002),
                  getParentForChildren(),
                  isClean());
          chatVO_ = null;
        }
        return chatVOBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:ChatResp)
    }

    static {
      defaultInstance = new ChatResp(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ChatResp)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ChatResp_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ChatResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016ChatResp.proto\032\014ChatVO.proto\032\016ChatEnum" +
      ".proto\"N\n\010ChatResp\022)\n\013chatChannel\030\001 \001(\0162" +
      "\014.ChatChannel:\006Global\022\027\n\006chatVO\030\002 \003(\0132\007." +
      "ChatVOB0\n\034com.pureland.common.protocalB\020" +
      "ChatRespProtocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_ChatResp_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_ChatResp_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ChatResp_descriptor,
              new java.lang.String[] { "ChatChannel", "ChatVO", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.pureland.common.protocal.vo.ChatVOProtocal.getDescriptor(),
          com.pureland.common.protocal.enums.ChatEnumProtocal.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
