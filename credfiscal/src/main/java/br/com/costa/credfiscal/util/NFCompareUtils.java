package br.com.costa.credfiscal.util;

import br.com.costa.fiscalcred.model.NCM;
import br.com.costa.fiscalcred.model.nfe.Det;

public class NFCompareUtils {

	public static boolean compareNFDescription(NCM ncm, Det det){
		if(det.getProd().getxProd().equals(ncm.getDescricao())){
			return true;
		}
		return false;
	}
	
}
