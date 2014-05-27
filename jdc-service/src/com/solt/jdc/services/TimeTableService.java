package com.solt.jdc.services;

import javax.ws.rs.Path;

import com.solt.jdc.entity.TimeTable;

@Path("/timetable")
public class TimeTableService extends AbstractService<TimeTable> {
	

	@Override
	public void setClass() {
		super.entity = TimeTable.class;
	}

	@Override
	public TimeTable update(TimeTable t) {
		TimeTable c = find(t.getId());
		c.setDays(t.getDays());
		c.setDescription(t.getDescription());
		c.setTimeFrom(t.getTimeFrom());
		c.setTimeTo(t.getTimeTo());
		return this.dao.update(c);
	}

}
