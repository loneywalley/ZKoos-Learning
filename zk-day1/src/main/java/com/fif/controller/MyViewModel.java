package com.fif;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import java.util.Locale;

public class MyViewModel {

	private int count;
	private String name;
	private double count2;

	@Init
	public void init() {
		count = 100;
		name = "Bramasta";
		count2 = 10.2;
	}

	@Command
	@NotifyChange("count")
	public void cmd() {
		++count;
		++count2;
	}

	@Command
	@NotifyChange("name")
	public void click() {
		name = "Dewa";
	}
	public String getName(){
		return name;
	}
	public int getCount() {
		return count;
	}

	public double getCount2() {
		return count2;
	}
}

