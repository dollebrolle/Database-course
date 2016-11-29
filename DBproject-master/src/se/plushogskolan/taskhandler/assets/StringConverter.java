package se.plushogskolan.taskhandler.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;



import se.plushogskolan.taskhandler.assets.enums.QueryType;

public class StringConverter {

	public String statementStringBuilder(String initialString, QueryType qType, HashMap<String, String> paramMap) {
		return statementStringBuilder(initialString, true, qType, paramMap);
	}
	
	public String statementStringBuilder(String initialString, boolean and, QueryType qType, HashMap<String, String> paramMap) {
		String[] stringArray = new String[paramMap.size()];
		List<String> keys = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		switch (qType) {
		case SELECT:
			initialString += " WHERE %s = ?";
			if(and) {
				for (int i = 1; i < paramMap.size(); i++) {
					initialString += " AND %s = ?";
				}
			} else {
				for (int i = 1; i < paramMap.size(); i++) {
					initialString += " OR %s = ?";
				}
			}			
			for (String key : paramMap.keySet()) {
				keys.add(key);
			}

			for (int i = 0; i < stringArray.length; i++) {
				stringArray[i] = keys.get(i);
			}
			return String.format(initialString, stringArray);
		case INSERT:
			builder.append(initialString).append("(NULL,");
			for (int i = 0; i < stringArray.length; i++) {
				if (i != stringArray.length - 1) {
					builder.append(" ? ,");
				} else {
					builder.append(" ?)");
				}
			}
			return builder.toString();
		case DELETE:
			for(String key : paramMap.keySet()){
				initialString = String.format(initialString, key);	
			}
			return initialString;
		case UPDATE:
			for (int i = 0; i < stringArray.length; i++) {
				if (i != stringArray.length - 1) {
					initialString += " %s = ?,";
				} else {
					initialString += " %s = ?";
				}
			}
			for (String key : paramMap.keySet()) {
				keys.add(key);
			}
			for (int i = 0; i < stringArray.length; i++) {
				stringArray[i] = keys.get(i);
			}			
			initialString += " WHERE id = ?";
			return String.format(initialString, stringArray);
		default:
			return null;
		}
	}

}
