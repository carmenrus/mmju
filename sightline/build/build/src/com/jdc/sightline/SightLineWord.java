package com.jdc.sightline;

public class SightLineWord {
	
	private String product;
	private String english;
	private String japanese;
	
	public SightLineWord(String line) {
		String [] data = line.split("\t");
		this.english = data[0].trim();
		this.japanese = data[1].trim();
		if(data.length > 2)
			this.product = data[2].trim();
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getJapanese() {
		return japanese;
	}

	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((english == null) ? 0 : english.hashCode());
		result = prime * result
				+ ((japanese == null) ? 0 : japanese.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SightLineWord other = (SightLineWord) obj;
		if (english == null) {
			if (other.english != null)
				return false;
		} else if (!english.equals(other.english))
			return false;
		if (japanese == null) {
			if (other.japanese != null)
				return false;
		} else if (!japanese.equals(other.japanese))
			return false;
		return true;
	}
	
	

}
