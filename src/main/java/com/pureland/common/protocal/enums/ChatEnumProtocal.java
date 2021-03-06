// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatEnum.proto

package com.pureland.common.protocal.enums;

public final class ChatEnumProtocal {
  private ChatEnumProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code ChatChannel}
   */
  public enum ChatChannel
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>Global = 0;</code>
     *
     * <pre>
     *公共频道
     * </pre>
     */
    Global(0, 0),
    /**
     * <code>Clan = 1;</code>
     *
     * <pre>
     *公会频道
     * </pre>
     */
    Clan(1, 1),
    ;

    /**
     * <code>Global = 0;</code>
     *
     * <pre>
     *公共频道
     * </pre>
     */
    public static final int Global_VALUE = 0;
    /**
     * <code>Clan = 1;</code>
     *
     * <pre>
     *公会频道
     * </pre>
     */
    public static final int Clan_VALUE = 1;


    public final int getNumber() { return value; }

    public static ChatChannel valueOf(int value) {
      switch (value) {
        case 0: return Global;
        case 1: return Clan;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ChatChannel>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ChatChannel>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ChatChannel>() {
            public ChatChannel findValueByNumber(int number) {
              return ChatChannel.valueOf(number);
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
      return com.pureland.common.protocal.enums.ChatEnumProtocal.getDescriptor().getEnumTypes().get(0);
    }

    private static final ChatChannel[] VALUES = values();

    public static ChatChannel valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ChatChannel(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ChatChannel)
  }

  /**
   * Protobuf enum {@code ChatType}
   */
  public enum ChatType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>Normal = 0;</code>
     *
     * <pre>
     *普通聊天
     * </pre>
     */
    Normal(0, 0),
    /**
     * <code>Sound = 1;</code>
     *
     * <pre>
     *语音聊天
     * </pre>
     */
    Sound(1, 1),
    /**
     * <code>Donate = 2;</code>
     *
     * <pre>
     *请求支援									
     * </pre>
     */
    Donate(2, 2),
    ;

    /**
     * <code>Normal = 0;</code>
     *
     * <pre>
     *普通聊天
     * </pre>
     */
    public static final int Normal_VALUE = 0;
    /**
     * <code>Sound = 1;</code>
     *
     * <pre>
     *语音聊天
     * </pre>
     */
    public static final int Sound_VALUE = 1;
    /**
     * <code>Donate = 2;</code>
     *
     * <pre>
     *请求支援									
     * </pre>
     */
    public static final int Donate_VALUE = 2;


    public final int getNumber() { return value; }

    public static ChatType valueOf(int value) {
      switch (value) {
        case 0: return Normal;
        case 1: return Sound;
        case 2: return Donate;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ChatType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ChatType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ChatType>() {
            public ChatType findValueByNumber(int number) {
              return ChatType.valueOf(number);
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
      return com.pureland.common.protocal.enums.ChatEnumProtocal.getDescriptor().getEnumTypes().get(1);
    }

    private static final ChatType[] VALUES = values();

    public static ChatType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ChatType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ChatType)
  }

  /**
   * Protobuf enum {@code ChatAuth}
   */
  public enum ChatAuth
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>User = 0;</code>
     *
     * <pre>
     *普通
     * </pre>
     */
    User(0, 0),
    /**
     * <code>System = 1;</code>
     *
     * <pre>
     *系统
     * </pre>
     */
    System(1, 1),
    ;

    /**
     * <code>User = 0;</code>
     *
     * <pre>
     *普通
     * </pre>
     */
    public static final int User_VALUE = 0;
    /**
     * <code>System = 1;</code>
     *
     * <pre>
     *系统
     * </pre>
     */
    public static final int System_VALUE = 1;


    public final int getNumber() { return value; }

    public static ChatAuth valueOf(int value) {
      switch (value) {
        case 0: return User;
        case 1: return System;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ChatAuth>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ChatAuth>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ChatAuth>() {
            public ChatAuth findValueByNumber(int number) {
              return ChatAuth.valueOf(number);
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
      return com.pureland.common.protocal.enums.ChatEnumProtocal.getDescriptor().getEnumTypes().get(2);
    }

    private static final ChatAuth[] VALUES = values();

    public static ChatAuth valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ChatAuth(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ChatAuth)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016ChatEnum.proto*#\n\013ChatChannel\022\n\n\006Globa" +
      "l\020\000\022\010\n\004Clan\020\001*-\n\010ChatType\022\n\n\006Normal\020\000\022\t\n" +
      "\005Sound\020\001\022\n\n\006Donate\020\002* \n\010ChatAuth\022\010\n\004User" +
      "\020\000\022\n\n\006System\020\001B6\n\"com.pureland.common.pr" +
      "otocal.enumsB\020ChatEnumProtocal"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
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
