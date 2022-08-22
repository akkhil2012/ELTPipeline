package com.extract;

import com.common.SourceData;
import com.load.Loader;
import com.load.LoaderImpl;

public class ExtracterImpl implements Extracter{

	
	private Loader loader;
	
	@Override
	public void startEtractor(SourceData source) {
		System.out.println(" Start Extracter............");
		loader = new LoaderImpl();
		
		loader.load(source);
		
	}

}
