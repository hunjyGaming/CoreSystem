package de.hunjy.utils;

public class PrefixBuilder {
	private String name;

	public PrefixBuilder(String name) {
		this.name = name;
	}

	 public String build()
    {
        return "§7•§8● " + name + " §8┃ §7";
    }
}

