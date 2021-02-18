package co.mydiary;

import java.util.List;

public class DiaryApp {

	DAO dao;
	
	//프로그램 시작
	public void start() {
		
		//저장된 내용 읽어서 리스트에 담기
		dao = new DiaryListDAO();	
		
		int menunum;
		
		do {
			// 메뉴 출력
			menuPrint();

			// 메뉴 선택
			menunum = menuChoose();

			// 메뉴별 실행
			process(menunum);

		} while (menunum != 0);

	}

	
	// 메뉴 출력
	public void menuPrint() {
		Menu[] arr = Menu.values();
		int i = 0;
		for (Menu rb : arr) {
			System.out.print(String.format("%d:%s ", i, rb));
			i++;
		}
		System.out.println();
	}

	// 메뉴 선택
	public int menuChoose() {
		int menusize = Menu.values().length;
		int menunum = StdInputUtil.readInt();
		do {
			if (menunum <= menusize) {
				break;
			}
			System.out.println(menusize + "까지만 입력가능합니다.");
		} while (true);
		return menunum;
	}

	// 메뉴별 실행
	public void process(int menunum) {
		Menu menu = Menu.getMenu(menunum);
		switch (menu) {
		case 종료: end();	break;
		case 추가: insert();	break;
		case 수정: insert();	break;
		case 삭제: delete();	break;
		case 전체조회:   selectAll(); break;
		case 날짜로검색: selectDate(); break;
		case 내용검색:   selectContent();break;
		}
	}

	public void end() {
		System.out.println("종료");
	}

	// 등록 : 날짜와 내용 입력하면 리스트에 추가
	public void insert() {
		String wdate = "";
		String contents = "";
		//입력
		System.out.println("날짜:");
		wdate = StdInputUtil.readDate();

		System.out.println("내용:");
		contents = StdInputUtil.readMultiLine();

		//vo 담기
		DiaryVO vo = new DiaryVO();
		vo.setWdate(wdate);
		vo.setContents(contents);
		
		//리스트에 저장
		dao.insert(vo);
		System.out.println(vo);
	}

	// 수정
	public void update() {
		//to do : 수정
		//날짜 입력
		
		//내용 입력
		
		//리스트에서 수정
		System.out.println("수정");
	}

	// 삭제
	public void delete() {
		//to do : 삭제
		//날짜 입력
		
		//리스트에서 삭제
		
		System.out.println("삭제");
	}

	// 날짜검색
	public void selectDate() {
		System.out.println("날짜검색");
		String wdate = StdInputUtil.readDate();
		DiaryVO vo = dao.selectDate(wdate);
		print(vo); 
	}

	// 내용검색
	public void selectContent() {
		System.out.println("내용검색");
		String contents = StdInputUtil.readMultiLine();
		List<DiaryVO> list = dao.selectContent(contents);
		list.stream().forEach(this::print);
	}

	// 전체조회
	public void selectAll() {
		System.out.println("전체조회");
		List<DiaryVO> list = dao.selectAll();
//1. 확장 for문으로 출력		
//		for(DiaryVO vo : list) {
//			System.out.println(vo.getWdate());
//			System.out.println("========");
//			System.out.println(vo.getContents());
//			System.out.println("========");
//		}

//2. stream과 람다식을 이용하여 출력
//		Stream<DiaryVO> stream = list.stream();
//		stream.forEach(vo -> {
//			print(vo);
//		});

//3. stream, 람다식,  Method reference 를 이용하여 출력		
		list.stream().forEach(this::print);
	
	}
	
	public void print(DiaryVO vo) {
		System.out.println(vo.getWdate());
		System.out.println("========");
		System.out.println(vo.getContents());
		System.out.println("========");
	}
}
