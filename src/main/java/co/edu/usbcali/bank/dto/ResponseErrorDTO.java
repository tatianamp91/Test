package co.edu.usbcali.bank.dto;

public class ResponseErrorDTO {

	public String code;
	public String msg;
	
	public ResponseErrorDTO() {
		super();
	}
	public ResponseErrorDTO(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
