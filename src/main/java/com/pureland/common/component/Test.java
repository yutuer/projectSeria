package com.pureland.common.component;

import com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType;

public class Test {
	
	public static void main(String[] args) {
          for(ResourceType resourceType : ResourceType.values()) {
        	  System.out.println(resourceType.toString());
          }
	}

}
