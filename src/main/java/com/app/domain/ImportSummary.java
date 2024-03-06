package com.app.domain;

public class ImportSummary {

	private String name;
	private long total;
	private long success;
	private long failed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSuccess() {
		return success;
	}

	public void setSuccess(long success) {
		this.success = success;
	}

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}

	@Override
	public String toString() {
		return "ImportSummary [name=" + name + ", total=" + total + ", success=" + success + ", failed=" + failed + "]";
	}
}
