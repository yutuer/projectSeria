package com.pureland.seria.module;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pureland.seria.db.seriaData.Resource;

public class ResourceModule extends AbstractModule {

	private static final long serialVersionUID = -8040468980481055309L;

	private Map<String, Resource> resourceMap = Maps.newHashMap();

	@Override
	public String getName() {
		return ModuleEnum.Resource.name();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(resourceMap);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		resourceMap = (Map<String, Resource>) in.readObject();
	}

}
