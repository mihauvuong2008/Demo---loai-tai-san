package View.Filter;

import DAO.TAISAN;

public class Filter_Column_Tree_MainTaisan {

	TAISAN t;
	int MA_TAISAN;

	public Filter_Column_Tree_MainTaisan(TAISAN t) {
		this.t = t;
		MA_TAISAN = Integer.valueOf(t.getMA_TAISAN());
	}

	public int getMA_TAISAN() {
		return MA_TAISAN;
	}

	public void setMA_TAISAN(int mA_TAISAN) {
		MA_TAISAN = mA_TAISAN;
	}

}
