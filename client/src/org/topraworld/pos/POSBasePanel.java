package org.topraworld.pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.JFrame;

import org.compiere.apps.ADialog;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MUser;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;

//org.topraworld.pos.POSBasePanel
public class POSBasePanel implements FormPanel{

	private int         	m_WindowNo = 0;
	private CLogger			log = CLogger.getCLogger(getClass());
	
	private int				m_SalesRep_ID = 0;
	private CFrame 		m_frame;
	private Properties		m_ctx = Env.getCtx();
	private MUser user;
	private Trx trx;
	
	public Properties getM_ctx() {
		return m_ctx;
	}

	public Trx getTrx() {
		return trx;
	}

	
	
	@Override
	public void init(int WindowNo, FormFrame frame) {
		
		m_frame = frame.getCFrame();
		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		m_WindowNo = WindowNo;
		
		m_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		m_frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		m_frame.setTitle("SPC Point of Sale - Adempiere");
		m_frame.setJMenuBar(null);
		//open new transaction
		trx = Trx.get(Trx.createTrxName("POS"), true);
		user = new MUser(m_ctx ,m_SalesRep_ID ,trx.getTrxName());
		
		if(isSalesRep()){
			new POSPanel(this).init(WindowNo, frame);
		}else{
			ADialog.error(WindowNo, frame.getCFrame(), "User is no more sales represantative!");
		}
	}

	@Override
	public void dispose() {

		
		trx.close(); trx = null;
		m_frame.dispose();
	}
	
	private boolean isSalesRep()
	{	
		if(m_SalesRep_ID == 100){ //super user
			m_frame.setTitle(m_frame.getTitle() + " " + "SuperUser");
			return true;
		}
		
		m_frame.setTitle(m_frame.getTitle() + " User : " + user.getName());
		return user.getC_BPartner().isSalesRep();
	}	//	setMPOS
}
