package com.transform;

import com.common.SourceData;
import com.sink.Sink;
import com.sink.SinkImpl;


public class DataTranformerImpl implements DataTranformer {

	 Sink sink;
	
	@Override
	public void tranform(SourceData data) {
		System.out.println(" Transformation Starts...............");
		
		sink = new SinkImpl();
		sink.startSinking(data);
	}

}
