package cn.peopleinvest.model;

import cn.peopleinvest.exception.ContentException;

public interface Content {
	public boolean useTemplate();

	public boolean validate() throws ContentException;
}