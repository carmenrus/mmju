package com.mmju.hhk.ep4.freeq;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FreqString {

	private String myStr;
	private List<FreqChar> carList;

	public FreqString(byte[] bytes) {
		this.myStr = new String(bytes);
		this.myStr = this.myStr.toLowerCase();
		this.carList = new ArrayList<>();
		List<Character> tmpList = new ArrayList<>();

		for (int i = 0; i < this.myStr.length(); i++) {
			Character c = this.myStr.charAt(i);
			if (Character.isLetter(c) && !tmpList.contains(c)) {
				tmpList.add(myStr.charAt(i));
				this.carList.add(new FreqChar(this.myStr.charAt(i),
						getCountOfChar(this.myStr.charAt(i))));
			}
		}

		Collections.sort(carList, new Comparator<FreqChar>() {
			@Override
			public int compare(FreqChar o1, FreqChar o2) {
				return o2.getCount() - o1.getCount();
			}
		});
	}

	private int getCountOfChar(char c) {
		int ret = 0;
		for (int i = 0; i < this.myStr.length(); i++) {
			if (c == this.myStr.charAt(i))
				ret++;
		}
		return ret;
	}

	public int length() {
		return this.myStr.length();
	}

	public String toString() {
		return this.myStr;
	}

	public String[] getRowData(FreqChar fc) {
		String[] rowData = null;
		if (null != fc) {
			rowData = new String[3];
			rowData[0] = String.valueOf(fc.getC());
			rowData[1] = String.valueOf(fc.getCount());
			rowData[2] = new BigDecimal(fc.getCount())
					.multiply(new BigDecimal(100))
					.divide(new BigDecimal(this.myStr.length()), 4, RoundingMode.DOWN)
					.toPlainString();
		}
		return rowData;
	}

	public List<FreqChar> getCharList() {
		return this.carList;
	}

	public Object[][] getRowDatas() {
		Object[][] rows = new Object[this.carList.size()][];
		for (int i = 0; i < this.carList.size(); i++) {
			rows[i] = getRowData(this.carList.get(i));
		}
		return rows;
	}
	
	public String cryptString(String target, char from, char to) {
		StringBuilder sb = new StringBuilder();
		int count = this.myStr.length();
		if(count != target.length())
			return target;
		for(int i=0; i<count; i++) {
			char tmp = this.myStr.charAt(i);
			if(tmp == from) {
				sb.append(to);
			} else {
				sb.append(target.charAt(i));
			}
		}
		return sb.toString();
	}
}
