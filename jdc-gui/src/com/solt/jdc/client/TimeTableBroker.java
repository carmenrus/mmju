package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.TimeTable;

public class TimeTableBroker extends AbstractBroker<TimeTable> {

	private static TimeTableBroker broker;
	
	private TimeTableBroker() {
		super(TimeTable.class);
	}
	
	public synchronized static TimeTableBroker getInstance() {
		if(null == broker) {
			broker = new TimeTableBroker();
		}
		return broker;
	}

	@Override
	public List<TimeTable> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<TimeTable>>(){});
	}

}
