package cn.com.bean;

import java.util.Map;

public class NoteProterties {

	private boolean noteUsed;// 短信开关

	private String noteAttemptTimes;// 短信重发的次数

	private Map<String, Object> map;// 机构对应短信发送机制的配置信息

	public boolean isNoteUsed() {
		return noteUsed;
	}

	public void setNoteUsed(boolean noteUsed) {
		this.noteUsed = noteUsed;
	}

	public String getNoteAttemptTimes() {
		return noteAttemptTimes;
	}

	public void setNoteAttemptTimes(String noteAttemptTimes) {
		this.noteAttemptTimes = noteAttemptTimes;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
