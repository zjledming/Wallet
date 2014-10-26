package com.cts.elt.designpattern.flyweight;

import java.util.List;

public interface Menu {

	public void setPersonMenu(String person, List list);

	public List findPersonMenu(String person, List list);
}
