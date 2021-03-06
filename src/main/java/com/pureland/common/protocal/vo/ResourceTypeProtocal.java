// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ResourceType.proto

package com.pureland.common.protocal.vo;

public final class ResourceTypeProtocal {
  private ResourceTypeProtocal() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code ResourceType}
   */
  public enum ResourceType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>None = 0;</code>
     */
    None(0, 0),
    /**
     * <code>Gold = 1;</code>
     */
    Gold(1, 1),
    /**
     * <code>Oil = 2;</code>
     */
    Oil(2, 2),
    /**
     * <code>Diamond = 3;</code>
     */
    Diamond(3, 3),
    /**
     * <code>Medal = 4;</code>
     *
     * <pre>
     *奖牌
     * </pre>
     */
    Medal(4, 4),
    /**
     * <code>Number = 5;</code>
     *
     * <pre>
     *特殊类型,升级兵时使用的单位个数
     * </pre>
     */
    Number(5, 5),
    /**
     * <code>RMB = 6;</code>
     *
     * <pre>
     *人民币
     * </pre>
     */
    RMB(6, 6),
    /**
     * <code>GoldOrOil = 7;</code>
     *
     * <pre>
     *二选一
     * </pre>
     */
    GoldOrOil(7, 7),
    /**
     * <code>NewOil = 8;</code>
     *
     * <pre>
     *新能源
     * </pre>
     */
    NewOil(8, 8),
    ;

    /**
     * <code>None = 0;</code>
     */
    public static final int None_VALUE = 0;
    /**
     * <code>Gold = 1;</code>
     */
    public static final int Gold_VALUE = 1;
    /**
     * <code>Oil = 2;</code>
     */
    public static final int Oil_VALUE = 2;
    /**
     * <code>Diamond = 3;</code>
     */
    public static final int Diamond_VALUE = 3;
    /**
     * <code>Medal = 4;</code>
     *
     * <pre>
     *奖牌
     * </pre>
     */
    public static final int Medal_VALUE = 4;
    /**
     * <code>Number = 5;</code>
     *
     * <pre>
     *特殊类型,升级兵时使用的单位个数
     * </pre>
     */
    public static final int Number_VALUE = 5;
    /**
     * <code>RMB = 6;</code>
     *
     * <pre>
     *人民币
     * </pre>
     */
    public static final int RMB_VALUE = 6;
    /**
     * <code>GoldOrOil = 7;</code>
     *
     * <pre>
     *二选一
     * </pre>
     */
    public static final int GoldOrOil_VALUE = 7;
    /**
     * <code>NewOil = 8;</code>
     *
     * <pre>
     *新能源
     * </pre>
     */
    public static final int NewOil_VALUE = 8;


    public final int getNumber() { return value; }

    public static ResourceType valueOf(int value) {
      switch (value) {
        case 0: return None;
        case 1: return Gold;
        case 2: return Oil;
        case 3: return Diamond;
        case 4: return Medal;
        case 5: return Number;
        case 6: return RMB;
        case 7: return GoldOrOil;
        case 8: return NewOil;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ResourceType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ResourceType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ResourceType>() {
            public ResourceType findValueByNumber(int number) {
              return ResourceType.valueOf(number);
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
      return com.pureland.common.protocal.vo.ResourceTypeProtocal.getDescriptor().getEnumTypes().get(0);
    }

    private static final ResourceType[] VALUES = values();

    public static ResourceType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ResourceType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ResourceType)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022ResourceType.proto*s\n\014ResourceType\022\010\n\004" +
      "None\020\000\022\010\n\004Gold\020\001\022\007\n\003Oil\020\002\022\013\n\007Diamond\020\003\022\t" +
      "\n\005Medal\020\004\022\n\n\006Number\020\005\022\007\n\003RMB\020\006\022\r\n\tGoldOr" +
      "Oil\020\007\022\n\n\006NewOil\020\010B7\n\037com.pureland.common" +
      ".protocal.voB\024ResourceTypeProtocal"
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
