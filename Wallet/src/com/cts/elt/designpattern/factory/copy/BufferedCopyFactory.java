package com.cts.elt.designpattern.factory.copy;

public class BufferedCopyFactory extends FileCopyFactory {

	@Override
	public FileCopy getCopySub() {
		return new BufferedCopyImpl();
	}

}
