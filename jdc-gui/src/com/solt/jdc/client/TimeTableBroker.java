package com.solt.jdc.client;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.solt.jdc.entity.TimeTable;

public class TimeTableBroker extends AbstractBroker<TimeTable> {

	public TimeTableBroker() {
		super(TimeTable.class);
	}

	@Override
	public List<TimeTable> getAll() {
		return baseTarget().request(MediaType.APPLICATION_JSON).buildGet()
				.invoke().readEntity(new GenericType<List<TimeTable>>(){});
	}

}
