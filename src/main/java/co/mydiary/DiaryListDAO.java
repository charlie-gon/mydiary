package co.mydiary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DiaryListDAO implements DAO {

	List<DiaryVO> list;
	String filename = "diary.dat";
	String filepath ;

	public DiaryListDAO() {
		filepath = System.getProperty("user.home") +"/"+filename;
		readFile(); // 파일 읽어서 리스트 초기화
	}

	// 파일확인
	public boolean fileCheck() {
		System.out.println(filepath+"====");
		boolean fileyn = true;
		File file = new File(filepath);
		fileyn = file.exists();
		
		// 파일이 있더라도 파일 크기가 0이면 읽을 내용이 없으므로
		if (file.length() == 0)
			fileyn = false;
		
		return fileyn;
	}

	// 파일 읽어서 리스트에 저장
	@SuppressWarnings("unchecked")
	public void readFile() {
		try {
			// 파일이 없으면 빈 리스트 생성
			if (!fileCheck()) {
				list = new ArrayList<DiaryVO>();
				System.out.println("파일이 없음");
			} else { // 파일이 있으면 파일정보를 읽어서 리스트 생성
				FileInputStream fis = new FileInputStream(filepath);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream in = new ObjectInputStream(bis);
				list = (List<DiaryVO>) in.readObject();
				System.out.println((list != null ? list.size() : 0) + "건이 read됨");
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// 리스트를 파일에 저장
	public void writeFile() {
		try {
			//저장할 내용이 있다면
			if (list.size() > 0) {
				// 파일 생성할 때 없으면 새로 만들고 있으면 덮어쓰게 된다
				FileOutputStream fos = new FileOutputStream(filepath);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream out = new ObjectOutputStream(bos);
				//리스트를 파일에 저장
				out.writeObject(list);
				out.close();
			}
			System.out.println(list.size() + "건이 저장됨");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public int insert(DiaryVO vo) {
		int idx = list.size();
		// 정렬해서 입력
		for (; idx > 0; idx--) {
			if (list.get(idx - 1).getWdate().equals(vo.getWdate())) {
				return 0;
			}
			if (list.get(idx - 1).getWdate().compareTo(vo.getWdate()) < 0) {
				break;
			}
		}
		list.add(idx, vo);
		//변경될때마다 파일에 저장
		writeFile();
		return 1;
	}

	//수정
	public void update(DiaryVO vo) {
		//to do : 수정
	}

	//해당 날짜의 내용을 삭제하고 삭제가 되었으면 1을 리턴
	public int delete(String date) {
		int result = 0;
		//to do : 삭제
		
		return result;
	}

	//날짜로 검색
	public DiaryVO selectDate(String date) {
		DiaryVO vo = null;
		int size = list.size();
		for (int idx = 0; idx < size; idx++) {
			if (list.get(idx).getWdate().equals(date)) {
				vo = list.get(idx);
				break;
			}
		}
		return vo;
	}

	//내용으로 검색
	public List<DiaryVO> selectContent(String content) {
		List<DiaryVO> searchlist = new ArrayList<>();
		//to do : 내용으로 검색
		
		return searchlist;
	}

	public List<DiaryVO> selectAll() {
		return list;
	}
}
