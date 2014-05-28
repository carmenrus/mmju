package com.solt.jdc.common;

import java.util.HashMap;
import java.util.Map;

import com.solt.jdc.client.CourseBroker;
import com.solt.jdc.client.JdcClassBroker;
import com.solt.jdc.client.StudentBroker;
import com.solt.jdc.client.TimeTableBroker;
import com.solt.jdc.client.TownshipBroker;

public class ApplicationContext {

	public enum CommonList {
		JdcClass, Course, TimeTable, Township, Student
	}

	private static Map<CommonList, Object> COMMON_LIST;

	static {
		COMMON_LIST = new HashMap<>();
		COMMON_LIST.put(CommonList.JdcClass, JdcClassBroker.getInstancce()
				.getAll());
		COMMON_LIST.put(CommonList.Course, CourseBroker.getInstance().getAll());
		COMMON_LIST.put(CommonList.TimeTable, TimeTableBroker.getInstance()
				.getAll());
		COMMON_LIST.put(CommonList.Township, TownshipBroker.getInstance().getAll());
		COMMON_LIST.put(CommonList.Student, StudentBroker.getInstance().getAll());
	}
	
	public static void put(CommonList key, Object value) {
		COMMON_LIST.put(key, value);
	}
	
	public static void remove(CommonList key) {
		COMMON_LIST.remove(key);
	}
	
	public static Object get(CommonList key) {
		return COMMON_LIST.get(key);
	}
}
