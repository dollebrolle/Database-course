package se.plushogskolan.taskhandler.assets;

import java.util.HashMap;

public final class MapBuilder<T> {
		
		private final HashMap<T, String> enumMap;
	
		public MapBuilder(){
			enumMap = new HashMap<T, String>();
		}
		
		public MapBuilder<T> append(T generalEnum, String args){
			enumMap.put(generalEnum, args);
			return this;
		}
		
		public HashMap<T, String> build(){
			return this.enumMap;
		}

}
