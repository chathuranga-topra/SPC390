package org.topraworld.process;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.topraworld.model.MCPOSConfigiration;
import org.topraworld.model.TpUtility;

//org.topraworld.process.POSBpartner
public class POSBpartner extends SvrProcess{

	private Properties m_ctx;
	private TpUtility tpUtility;
	private int AD_Org_ID;
	@SuppressWarnings("unused")
	private int AD_Client_ID;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if(name.equalsIgnoreCase("AD_Client_ID"))
				AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if(name.equalsIgnoreCase("AD_Org_ID"))
				AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}
		
		m_ctx = Env.getCtx();
		tpUtility = new TpUtility();
	}

	@Override
	protected String doIt() throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		String namePfix = dateFormat.format(new Date());
		String sql = "";
		String message = "";
		
		sql = "SELECT COUNT(VALUE) FROM C_Bpartner where VALUE LIKE '%"+namePfix+"%'";
		int count = DB.getSQLValue(this.get_TrxName(), sql);
		
		//Duplicate same day
		if(count > 0)
			return "Process Terminated - No Business Partners are created.";
		
		MCPOSConfigiration posConfs = MCPOSConfigiration.getPOSConfigOrg(m_ctx, get_TrxName());
		
		if(posConfs == null || posConfs.get_ID() == -1)
			return "Process Terminated - Error on POS Configuration!";
	
		MOrg mOrg = new MOrg(m_ctx, AD_Org_ID, this.get_TrxName());
		MOrgInfo orgInfo = mOrg.getInfo();
		
		MBPartnerLocation bpLocation = null;
		
		//Create new Bpartner for each category
		MBPartner bpartner = new MBPartner(m_ctx, 0, this.get_TrxName());//General
		bpartner.setName("General");
		bpartner.setValue("General " + namePfix);
		bpartner.setC_BP_Group_ID(tpUtility.getGeneralC_BPGroup_ID());
		bpartner.setAD_Org_ID(AD_Org_ID);
		bpartner.save();
		//set location
		bpLocation = new MBPartnerLocation(m_ctx, 0, this.get_TrxName());
		bpLocation.setC_Location_ID(orgInfo.getC_Location_ID());
		bpLocation.setName(mOrg.getName());
		bpLocation.setC_BPartner_ID(bpartner.get_ID());
		bpLocation.save();
		
		//UPDATE C_POSCOnfiguration TABLE GENARAL
		posConfs.setC_BpGeneral_ID(bpartner.get_ID());
		
		bpartner = new MBPartner(m_ctx, 0, this.get_TrxName());//Senior
		bpartner.setName("Senior");
		bpartner.setValue("Senior " + namePfix);
		bpartner.setC_BP_Group_ID(tpUtility.getSeniorC_BPGroup_ID());
		bpartner.setAD_Org_ID(AD_Org_ID);
		bpartner.save();
		//set location
		bpLocation = new MBPartnerLocation(m_ctx, 0, this.get_TrxName());
		bpLocation.setC_Location_ID(orgInfo.getC_Location_ID());
		bpLocation.setName(mOrg.getName());
		bpLocation.setC_BPartner_ID(bpartner.get_ID());
		bpLocation.save();
		//UPDATE C_POSCOnfiguration TABLE Senior
		posConfs.setC_BpSenior_ID(bpartner.get_ID());
		
		bpartner = new MBPartner(m_ctx, 0, this.get_TrxName());//Child
		bpartner.setName("Child ");
		bpartner.setValue("Child " + namePfix);
		bpartner.setC_BP_Group_ID(tpUtility.getChildC_BPGroup_ID());
		bpartner.setAD_Org_ID(AD_Org_ID);
		bpartner.save();
		//set location
		bpLocation = new MBPartnerLocation(m_ctx, 0, this.get_TrxName());
		bpLocation.setC_Location_ID(orgInfo.getC_Location_ID());
		bpLocation.setName(mOrg.getName());
		bpLocation.setC_BPartner_ID(bpartner.get_ID());
		bpLocation.save();
		//UPDATE C_POS TABLE Child
		posConfs.setC_BpChild_ID(bpartner.get_ID());
		
		bpartner = new MBPartner(m_ctx, 0, this.get_TrxName());//Child
		bpartner.setName("Pregnant");
		bpartner.setValue("Pregnant " + namePfix);
		bpartner.setAD_Org_ID(AD_Org_ID);
		bpartner.setC_BP_Group_ID(tpUtility.getPregnantC_BpGroup_ID());
		bpartner.save();
		//set location
		bpLocation = new MBPartnerLocation(m_ctx, 0, this.get_TrxName());
		bpLocation.setC_Location_ID(orgInfo.getC_Location_ID());
		bpLocation.setName(mOrg.getName());
		bpLocation.setC_BPartner_ID(bpartner.get_ID());
		bpLocation.save();
		//UPDATE C_POS TABLE Pregnant
		posConfs.setC_BpPregnant_ID(bpartner.get_ID());
		
		bpartner = new MBPartner(m_ctx, 0, this.get_TrxName());//Child
		bpartner.setName("Special");
		bpartner.setValue("Special " + namePfix);
		bpartner.setAD_Org_ID(AD_Org_ID);
		bpartner.setC_BP_Group_ID(tpUtility.getSpCusC_BPGroup_ID());
		bpartner.save();
		//set location
		bpLocation = new MBPartnerLocation(m_ctx, 0, this.get_TrxName());
		bpLocation.setC_Location_ID(orgInfo.getC_Location_ID());
		bpLocation.setName(mOrg.getName());
		bpLocation.setC_BPartner_ID(bpartner.get_ID());
		bpLocation.save();

		//UPDATE C_POS TABLE Special
		posConfs.setC_BpSpecial_ID(bpartner.get_ID());
		//set business partners to POS all user wise pos terminel
		
		posConfs.save(get_TrxName());
		message = "Process completed sucessfully!";
		return message;
	}
}
