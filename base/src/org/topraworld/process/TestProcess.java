package org.topraworld.process;

import org.adempiere.server.rpl.imp.TopicImportProcessor;
import org.compiere.process.SvrProcess;
import org.compiere.server.ReplicationProcessor;

//org.topraworld.process.TestProcess
public class TestProcess extends SvrProcess{

	@Override
	protected void prepare() {
		
		/*ReplicationProcessor replicationProcessor = getMImportProcessor();
		
		
		TopicImportProcessor timp = new TopicImportProcessor();
		timp.process(getCtx(), replicationProcessor, trxName);*/
		
	}

	@Override
	protected String doIt() throws Exception {

		System.out.println("HeloTestProcess");
		
		return null;
	}

}
