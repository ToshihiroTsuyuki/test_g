package com.example.test;

//記事1件の内容を保持するクラス
public class TestItem {

	private String title;
	private String category;
	private String description;
	private String link;

	// コンストラクタ
	public TestItem(String title, String category,String description,String link) {
		super();
		this.title = title;
		this.category = category;
		this.description = description;
		this.link = link;
	}

	// 記事の見出しを返すメソッド
	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}
	public String getDescription() {
		return description;
	}
	public String getLink() {
		return link;
	}

	// 文字列表現を返すメソッド
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ZisinItem={");
		sb.append("title=" + title);
		sb.append(",category=" + category);
		sb.append(",description=" + description);
		sb.append("link=" + link);
		sb.append("}");
		return sb.toString();
	}
}
