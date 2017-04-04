package View.Filter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import DAO.HOSO_ROW;
import DAO.NGUOIDUNG;
import DAO.TAISAN;

public class Sortter {

	public ArrayList<TAISAN> SortTaisan(ArrayList<TAISAN> tl, String Pattern) {
		ArrayList<Taisan_Filter> ftl = new ArrayList<>();
		for (TAISAN t : tl) {
			Taisan_Filter f = new Taisan_Filter(t, Pattern);
			ftl.add(f);
		}

		Collections.sort(ftl, new Comparator<Taisan_Filter>() {
			@Override
			public int compare(Taisan_Filter f1, Taisan_Filter f2) {
				return Double.compare(f2.getValue(), f1.getValue());
			}
		});
		ArrayList<TAISAN> result = new ArrayList<>();
		for (Taisan_Filter f : ftl) {
			result.add(f.t);
		}
		return result;
	}

	public ArrayList<TAISAN> SortPTGT(ArrayList<TAISAN> tl, boolean Tenphuongtien, boolean Bienso, boolean Dongxe,
			boolean Sokhung, boolean Somay, String Pattern, NGUOIDUNG user) throws SQLException {
		ArrayList<PTGT_Filter> ftl = new ArrayList<>();
		for (TAISAN t : tl) {
			PTGT_Filter f = new PTGT_Filter(t, Pattern, user, Tenphuongtien, Bienso, Dongxe, Sokhung, Somay);
			ftl.add(f);
		}

		Collections.sort(ftl, new Comparator<PTGT_Filter>() {
			@Override
			public int compare(PTGT_Filter f1, PTGT_Filter f2) {
				return Double.compare(f2.getValue(), f1.getValue());
			}
		});
		ArrayList<TAISAN> result = new ArrayList<>();
		for (PTGT_Filter f : ftl) {
			result.add(f.t);
		}
		return result;
	}

	public ArrayList<HOSO_ROW> SortHOSO_ROW(ArrayList<HOSO_ROW> tl, String Pattern) throws SQLException {
		ArrayList<HOSO_ROW_Filter> ftl = new ArrayList<>();
		for (HOSO_ROW t : tl) {
			HOSO_ROW_Filter f = new HOSO_ROW_Filter(t, Pattern);
			ftl.add(f);
		}

		Collections.sort(ftl, new Comparator<HOSO_ROW_Filter>() {
			@Override
			public int compare(HOSO_ROW_Filter f1, HOSO_ROW_Filter f2) {
				return Double.compare(f2.getValue(), f1.getValue());
			}
		});
		ArrayList<HOSO_ROW> result = new ArrayList<>();
		for (HOSO_ROW_Filter f : ftl) {
			result.add(f.getT());
		}
		return result;
	}

}
