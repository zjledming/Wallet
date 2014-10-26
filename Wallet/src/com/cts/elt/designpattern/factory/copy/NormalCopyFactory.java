package com.cts.elt.designpattern.factory.copy;

public class NormalCopyFactory extends FileCopyFactory {

	@Override
	public FileCopy getCopySub() {
		return new NormalCopyImpl();
	}

}
