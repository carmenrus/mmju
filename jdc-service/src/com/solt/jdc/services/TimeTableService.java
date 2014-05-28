package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.TimeTable;

@Path("/timetable")
public class TimeTableService extends AbstractService<TimeTable> {
	

	@Override
	public void setClass() {
		super.entity = TimeTable.class;
	}

}
