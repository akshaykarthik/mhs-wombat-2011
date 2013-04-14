package edu.mhs.wombat.utils.data;

public class Option {
	public String type;
	public String value;

	public Option(String value) {
		this(value, "string");
	}

	public Option(String value, String type) {
		this.type = type;
		this.value = value;
	}
}