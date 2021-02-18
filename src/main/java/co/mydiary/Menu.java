package co.mydiary;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
	종료(0), 추가(1), 수정(2), 삭제(3), 전체조회(4), 날짜로검색(5), 내용검색(6);

	private int value;

	private Menu(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static Menu getMenu(int m) {
		Optional<Menu> menu = Arrays.stream(Menu.values())
				  .filter(p -> p.getValue() == m)
				  .findFirst();
		return menu.get();
		/*
		Menu em = null;
		for (Menu menu : Menu.values()) {
		    if (menu.getValue() == m) {
		        em = m;
		    }
		}
		return em;
		*/
	}
}