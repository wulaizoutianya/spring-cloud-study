package commonserver.utils;

public enum UserSex {
	
	man("男"),woman("女"),other("其它");
	
	String value;
	
	private UserSex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
