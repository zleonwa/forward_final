package com.lecto.forward.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lecto.forward.dto.BoardDTO;
import com.lecto.forward.dto.GradeDTO;
import com.lecto.forward.dto.MemberDTO;
import com.lecto.forward.dto.MemberGradesDTO;
import com.lecto.forward.persistence.BoardMapper;
import com.lecto.forward.persistence.BoardSearchViewMapper;
import com.lecto.forward.persistence.GradeMapper;
import com.lecto.forward.persistence.MemberArticleViewMapper;
import com.lecto.forward.persistence.MemberGradesMapper;
import com.lecto.forward.persistence.MemberMapper;
import com.lecto.forward.vo.BoardSearchVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	GradeMapper gradeMapper;
	@Autowired
	MemberGradesMapper memberGradesMapper;
	@Autowired
	MemberArticleViewMapper memberArticleViewMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	BoardSearchViewMapper boardSearchViewMapper;
	
	public boolean addBoard(BoardDTO boardDTO) throws Exception{
		if(boardDTO==null) {
			return false;
		}else {
			try {
				boardDTO.setBoardCode(generateBoardCode());
				boardMapper.addBoardDTO(boardDTO);
			} catch (Exception ex) {
				System.out.println("addBoard ����");

				return false;
			}
			return true;
		}
	}
	
	public boolean addGrade(List<GradeDTO> grades) throws Exception{
		if(grades.size()==0) {
			return false;
		}else {
			try {
				gradeMapper.addGradeParams(grades);
			} catch (Exception ex) {
				System.out.println("addGrade ����");
				return false;
			}
			return true;
		}
	}
	
	public boolean addMemberGrades(MemberGradesDTO memberGradesDTO) throws Exception{
		if(memberGradesDTO==null) {
			return false;
		}else {
			try {
				memberGradesMapper.addMemberGradesDTO(memberGradesDTO);
			} catch (Exception e) {
				System.out.println("addMemberGrades ����");
				return false;
			}
			return true;
		}
	}
	
	public boolean updateBoard(BoardDTO boardDTO, List<GradeDTO> grades) throws Exception{
		if(boardDTO==null || grades.size()==0) {
			return false;
		}else {
			try {
				boardMapper.updateBoard(boardDTO);
				for (int i = 0; i < grades.size(); i++) {
					gradeMapper.updateGrade(grades.get(i));
				}
				List<MemberDTO> memberIds=memberMapper.searchIds();
				MemberGradesDTO memberGradesDTO;
				int memberArticleNum;
				int changeGradeNum=0;
				int successCnt=0;
				for(int i=0;i<memberIds.size();i++){
//					memberLevel=memberGradesDAO.searchBoardGrades(boardDTO.getBoardCode(), (String)memberIds[i]);
//					memberGradesDTO =(MemberGradesDTO)memberLevel[i];
					memberArticleNum=Integer.parseInt(memberArticleViewMapper.searchArticleCnt(boardDTO.getBoardName(), memberIds.get(i).getMemberId()));
					for(int j=0;j<grades.size();i++){
						if(memberArticleNum<grades.get(j).getArticleCount())
						{
							if(j==0){
								changeGradeNum=grades.get(j).getGradeNum();
							}else{
								changeGradeNum=grades.get(j-1).getGradeNum();	
							}
						}
						else if(grades.get(i).getGradeNum()==grades.size()){
							changeGradeNum=grades.get(i).getGradeNum();
						}
					}
					memberGradesDTO = new MemberGradesDTO(generateBoardMemberGrade(),boardDTO.getBoardCode(), memberIds.get(i).getMemberId(), changeGradeNum);
					memberGradesMapper.updateMemberGradesDTO(memberGradesDTO);
					successCnt++;
				}
				if(successCnt!=0){
					return true;
				}
				else{
					return false;
				}
			} catch (Exception e) {
				System.out.println("updateBoard ����");
				return false;
			}
		}
	}
	
	public boolean updateBoard(String boardCode, String themeCode) throws Exception{
		if(boardCode==null || themeCode==null) {
			try {
				boardMapper.updateThemeCode(boardCode, themeCode);
			} catch (Exception e) {
				System.out.println("updateBoard ����");
				return false;
			}
			return true;
		}else {
			return true;
		}
	}
	
	/*�߰���*/
	public boolean updateGrade(String boardCode, List<GradeDTO> grades) throws Exception{
		if( boardCode ==null || grades ==null) {
			return false;
		}else {
			try {
				for(GradeDTO gradeDTO : grades) {
					gradeMapper.updateGrade(gradeDTO);
				
					}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e +" updateGrade ����");
				return false;
			} 
			return true;
		}
	}
	
	public List<BoardSearchVO> searchBoard() throws Exception{
		try {
			List<BoardSearchVO> returnVal = boardSearchViewMapper.searchBoardSearchViewAll();
			if(returnVal==null) {
				return null;
			}else {
				return returnVal;
			}
		} catch (Exception e) { 
			System.out.println("search() ����");
			return null;
		}
	}
	
	public List<BoardSearchVO> searchBoard(String searchWay, String keyword) throws Exception{
		if(searchWay==null || keyword==null) {
			return null;
		}
		List<BoardSearchVO> list = new ArrayList<BoardSearchVO>();
		try {
			switch (searchWay) {
			case "��ھ��̵�":
				System.out.println("��� ���̤��� ����"+keyword);
				list.add(boardSearchViewMapper.searchMemberId(keyword));
				break;
			case "�Խ��Ǹ�":
				list.add(boardSearchViewMapper.searchBoardName(keyword));
				break;
			case "�׸���":
				System.out.println(keyword);
				list = boardSearchViewMapper.searchThemeName(keyword);
				break;
			}
		} catch (Exception e) {
			System.out.println("search ����123");
		}
		return list;
	}
	
	public BoardDTO searchBoardName(String boardName) throws Exception{
		if(boardName==null) {
			return null;
		}else {
			try {
				return boardMapper.searchBoardName(boardName);
			} catch (Exception e) {
				System.out.println("search ����");
				return null;
			}
		}
	}
	
	public BoardDTO searchBoardCode(String boardCode) throws Exception{
		if(boardCode==null) {
			return null;
		}else {
			try {
				
				BoardDTO bt = boardMapper.searchBoardCode(boardCode);
				System.out.println(bt);
				return bt;
			} catch (Exception e) {
				System.out.println("search ����");
				return null;
			}
		}
	}
	/*�߰���*/
	public List<GradeDTO> searchGrade(String boardCode) throws Exception{
		if(boardCode==null) {
			return null;
		}else {
			try {
				List<GradeDTO> grades = gradeMapper.searchUseCode(boardCode);
				for(int i=0;i<grades.size();i++) {
					System.out.println(grades.get(i));
				}
				return grades;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("searchGrade ����");
				return null;
			}
		}
	}
	
	public boolean deleteBoard(String boardCode) throws Exception{
		if(boardCode==null) {
			return false;
		}else {
			try {
				if(boardMapper.deleteBoard(boardCode)==0) {
					return false;
				}
			}catch(Exception e) {
				System.out.println("delete ����");
				return false;
			}
			return true;
		}
	}
	
	public boolean deleteBoard(List<String> boardCode) throws Exception{
		if(boardCode.size()==0) {
			return false;
		}else {
			try {
				if(boardMapper.deleteBoards(boardCode)==0) { // ���� ����ؾߵ�
					return false;
				}
			} catch (Exception e) {
				System.out.println("delete ����");
				return false;
			}
			return true;
		}
	}
	
	public boolean deleteBoardName(List<String> boardName) throws Exception{
		if(boardName.size()==0) {
			return false;
		}else {
			try {
				if(boardMapper.deleteBoardName(boardName)==0) {
					return false;
				}					
			} catch (Exception e) {
				System.out.println("delete ����");
				return false;
			}
			return true;
		}
	}
	
	public String generateBoardCode() throws Exception{
		Map<Integer,BoardDTO> sortMap = new TreeMap<Integer, BoardDTO>();
		List<BoardDTO> list = null;
		String code = null;
		try {
			list = boardMapper.searchBoardAll();
		
			if(list.size() == 0 || list.isEmpty() || list == null) {
				code = "bo1";
			} else{
				for(BoardDTO dto:list){
			        int num = Integer.parseInt(dto.getBoardCode().substring(2));
					sortMap.put(num, dto);
				}
				int last = ((TreeMap<Integer, BoardDTO>) sortMap).lastKey();	
	
				code = "bo" + String.valueOf(last+1);
			}
		} catch (Exception e) {
			System.out.println("�����ڵ� ���� ����");
			e.printStackTrace();
		}
		System.out.println(code+"�����ڵ�");
		return code;
	}
	
	public String generateBoardMemberGrade() throws Exception{
		Map<Integer,MemberGradesDTO> sortMap = new TreeMap<Integer, MemberGradesDTO>();
		List<MemberGradesDTO> list = null;
		String code = null;
		try {
			list = memberGradesMapper.searchMembarGradesAll();
		
			if(list.size() == 0 || list.isEmpty() || list == null) {
				code = "mg1";
			} else{
				for(MemberGradesDTO dto:list){
			        int num = Integer.parseInt(dto.getBoardMemberGrade().substring(2));
					sortMap.put(num, dto);
				}
				int last = ((TreeMap<Integer, MemberGradesDTO>) sortMap).lastKey();	
	
				code = "mg" + String.valueOf(last+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
}
