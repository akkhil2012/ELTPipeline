package com.load;

import com.common.SourceData;
import com.ibm.security.krb5.internal.crypto.t;
import com.transform.DataTranformer;
import com.transform.DataTranformerImpl;

public class LoaderImpl implements Loader{

	DataTranformer transformer;
	
	@Override
	public void load(SourceData data) {
		transformer = new DataTranformerImpl();
		
		transformer.tranform(data);
		
	}

	

}
