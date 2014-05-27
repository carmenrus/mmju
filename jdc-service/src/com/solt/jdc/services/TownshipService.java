package com.solt.jdc.services;

import com.solt.jdc.entity.Township;

public class TownshipService extends AbstractService<Township> {

	@Override
	public void setClass() {
		super.entity = Township.class;
	}

	@Override
	public Township update(Township t) {
		Township c = find(t.getId());
		c.setName(t.getName());
		return dao.update(c);
	}

}
