package com.lecto.forward.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lecto.forward.dto.BoardDTO;
import com.lecto.forward.dto.MemberDTO;
import com.lecto.forward.dto.MemberGradesDTO;
import com.lecto.forward.persistence.BoardMapper;
import com.lecto.forward.persistence.BoardMemberListViewMapper;
import com.lecto.forward.persistence.ManagerViewMapper;
import com.lecto.forward.persistence.MemberArticleViewMapper;
import com.lecto.forward.persistence.MemberGradesMapper;
import com.lecto.forward.persistence.MemberMapper;
import com.lecto.forward.vo.BoardMemberListVO;
import com.lecto.forward.vo.ManagerVO;
import com.lecto.forward.vo.MemberArticleVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberGradesMapper memberGradesMapper;
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardMemberListViewMapper bmlViewMapper;
	@Autowired
	private ManagerViewMapper managerViewMapper;
	@Autowired
	private MemberArticleViewMapper memberArticleViewMapper;
	
	public boolean addMember(MemberDTO memberDTO) {
		// ��� ���̿� ���� ��� �߰�
		if( !nullCheck(new String[]{memberDTO.getMemberId(), memberDTO.getMemberPwd(), memberDTO.getMemberName(), memberDTO.getMemberNickname(),
				memberDTO.getMemberBirth(), memberDTO.getMemberMail(), memberDTO.getMemberPhone(),
				memberDTO.getMemberAddress()})){
			return false;
		}
		boolean flag = false;
		try{
			memberMapper.addMember(memberDTO);
		
			List<BoardDTO> list = boardMapper.searchBoardAll();
			for(BoardDTO dto: list){
				if(!dto.getBoardName().equals("�����Խ���")){
					// ���⼭ �ڵ� ���� ������� boardService.generateBoardCode();
					try {
						MemberGradesDTO mg = new MemberGradesDTO(boardService.generateBoardMemberGrade(),dto.getBoardCode(),memberDTO.getMemberId(),1);
						memberGradesMapper.addMemberGradesDTO(mg);
						flag = true;
					} catch(Exception e) {
						e.printStackTrace();
						flag = false;
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			flag = false;
		}
		// �׷��̵��� ���� �Խ��Ǻ� ��� �߰�
		return flag;
		
	}
	//////// 2018-08-04 08:54 ����
	public boolean addMember(MemberDTO memberDTO, String boardName) {
		if( !nullCheck(new String[]{memberDTO.getMemberId(), memberDTO.getMemberPwd(), memberDTO.getMemberName(), memberDTO.getMemberNickname(),
				memberDTO.getMemberBirth(), memberDTO.getMemberMail(), memberDTO.getMemberPhone(),
				memberDTO.getMemberAddress()})){
			return false;
		}
		
		if(boardName == null) return false;
		boolean flag = false;
		try{
			memberMapper.addMember(memberDTO);
		
			List<BoardDTO> list =  boardMapper.searchBoardAll();
			List<BoardDTO> blist = new ArrayList<BoardDTO>();
			for(BoardDTO obj: list){
				blist.add(obj);
			}
			// String boardMemberGrade, String boardCode, String memberId, int gradeNum
			for(BoardDTO dto: blist){
				if(dto.getBoardName().equals(boardName)){
					try {
						MemberGradesDTO mg = new MemberGradesDTO(boardService.generateBoardMemberGrade(),dto.getBoardCode(), memberDTO.getMemberId(),-1);
						memberGradesMapper.addMemberGradesDTO(mg);
						flag = true;
					} catch(Exception e) {
						e.printStackTrace();
						flag = false;
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		// �׷��̵��� ���� �Խ��Ǻ� ��� �߰�
		return flag;
	}
	// ��� ����
	public boolean updateMember(MemberDTO memberDTO) {
		// ��� ���̿� ���� ��� �߰�
		if( !nullCheck(new String[]{memberDTO.getMemberId(), memberDTO.getMemberPwd(), memberDTO.getMemberName(), memberDTO.getMemberNickname(),
				memberDTO.getMemberBirth(), memberDTO.getMemberMail(), memberDTO.getMemberPhone(),
				memberDTO.getMemberAddress()})){
			return false;
		}
		try {		
			memberMapper.updateMember(memberDTO);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	// ������ ��� ����
	public boolean updateMemberPwd(String memberId, String newPwd) { 
		if( !nullCheck(new String[]{memberId, newPwd}))
			return false;
		try {
			memberMapper.updatePwd(memberId, newPwd);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	public boolean updateMember(MemberDTO memberDTO, String newBoardName) {
		if( !nullCheck(new String[]{newBoardName}))
			return false;
		
		boolean flag = false;
		if(updateMember(memberDTO)){
			try {
				memberGradesMapper.deleteMemberId(memberDTO.getMemberId());

				List<BoardDTO> list =  boardMapper.searchBoardAll();
				List<BoardDTO> blist = new ArrayList<BoardDTO>();
				for(BoardDTO obj: list){
					blist.add(obj);
				}
				for(BoardDTO dto: blist){
					if(dto.getBoardName().equals(newBoardName)){
						try {
							// ���⼭ �ڵ� ���� ������� boardService.generateBoardCode();
							MemberGradesDTO mg = new MemberGradesDTO(boardService.generateBoardMemberGrade(),dto.getBoardCode(), memberDTO.getMemberId(), -1);
							memberGradesMapper.addMemberGradesDTO(mg);
							flag = true;
						}catch(Exception e) {
							e.printStackTrace();
							flag = false;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
	public boolean deleteMember(String memberId) {
		// ��� ���̿� ���� ��� �߰�
		if( !nullCheck(new String[]{memberId})){
			return false;
		}
		try {
			memberMapper.deleteMember(memberId);
			try {
				memberGradesMapper.deleteMemberId(memberId);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	///////////////////////
	public boolean deleteMember(String[] memberIds) {
		for(String id:memberIds){
			if(!deleteMember(id)){
				return false;
			}
		}
		return true;
	}
	
	public MemberDTO searchMember(String memberId, String memberPwd) {
		if( !nullCheck(new String[]{memberId, memberPwd})){
			return null;
		}
		MemberDTO dto = null;
		try {
			dto = memberMapper.login(memberId, memberPwd);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dto != null){
			return dto;
		}
		return null;
	}
	
	public MemberDTO searchMember(MemberDTO memberDTO) {
		if( !nullCheck(new String[]{memberDTO.getMemberId(), memberDTO.getMemberPwd(), memberDTO.getMemberName(), memberDTO.getMemberNickname(),
				memberDTO.getMemberBirth(), memberDTO.getMemberMail(), memberDTO.getMemberPhone(),
				memberDTO.getMemberAddress(), memberDTO.getRegisterDate()})){
			return null;
		}
		MemberDTO dto = null;
		try {
			dto = memberMapper.searchMember(memberDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dto != null){
			return dto;
		}
		return null;
	}
	// ȸ���� �� ȸ�� ���� ��ȸ (�Խñ� ��)
	public MemberArticleVO[] searchMemberInfo(String memberId) {
		if( !nullCheck(new String[]{memberId})){
			return null;
		}
		MemberArticleVO[] arr = null;
		List<MemberArticleVO> list = null;
		try {
			list = memberArticleViewMapper.searchMemberIdArticle(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list != null && !list.isEmpty()) {
			arr = new MemberArticleVO[list.size()];
			for(int i=0; i<list.size(); i++)
				arr[i] = list.get(i);
		}
		return arr;
	}
	
	public MemberDTO searchMember(String memberId) {
		if( !nullCheck(new String[]{memberId})){
			return null;
		}
		
		MemberDTO dto = null;
		try {
			dto = memberMapper.searchId(memberId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public String searchMemberId(String memberName, String memberPhone, String memberMail) {
		if( !nullCheck(new String[]{memberName, memberPhone, memberMail}))
			return null;
		
		MemberDTO dto = null;
		try {
			dto = memberMapper.searchFindId(memberName, memberPhone, memberMail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dto != null)
			return dto.getMemberId();
		return null;
	}
	
	public String searchMemberPwd(String memberId, String memberPhone, String memberMail) {
		if( !nullCheck(new String[]{memberId, memberPhone, memberMail}))
			return null;
		
		MemberDTO dto = null;
		try {
			dto = memberMapper.searchFindPwd(memberId, memberPhone, memberMail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dto != null)
			return dto.getMemberPwd();
		return null;
	}
	
	public Object[] searchBoardMember(String boardKey) {
		Object[] list = null;
		List<BoardMemberListVO> vos = null;
		if( !nullCheck(new String[]{boardKey}))
			return null;
		
		try{
			if(boardKey.substring(0, 2).equals("bo")){
				System.out.println(boardKey);
				vos = bmlViewMapper.searchBoardCode(boardKey);
			}
			else{
				vos = bmlViewMapper.searchBoardName(boardKey);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(vos != null && !vos.isEmpty()) {
			list = new Object[vos.size()];
			for(int i=0;i<vos.size();i++)
				list[i] = vos.get(i);
		}
		return list;
	}
	
	public Object[] searchBoardMember(String boardName, String searchWay, String keyword) {
		if( !nullCheck(new String[]{boardName,searchWay, keyword}))
			return null;
		
		Object[] obj = null;
		List<BoardMemberListVO> list = new ArrayList<BoardMemberListVO>();
		try {
			switch(searchWay){
			case "���̵�":
				list = bmlViewMapper.searchBNMemberId(boardName, keyword);
				break;
			case "�г���":
				list = bmlViewMapper.searchBNMemberNickname(boardName, keyword);
				break;
			case "��޸�":
				obj = searchBoardGradeMebers(boardName, keyword);
				break;
			}
			if(list != null && !list.isEmpty()) {
				obj = new Object[list.size()];
				for(int i=0;i<list.size();i++)
					obj[i] = list.get(i);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public Object[] searchBoardGradeMebers(String boardName, String gradeName) {
		if( !nullCheck(new String[]{boardName, gradeName}))
			return null;
		Object[] objs = null;
		List<BoardMemberListVO> list = null;
		try {
			list = bmlViewMapper.searchBNGradeName(boardName, gradeName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list != null && !list.isEmpty()) {
			objs = new Object[list.size()];
			for(int i=0;i<list.size();i++)
				objs[i] = list.get(i);
		}
		return objs;
	}
	
	public Object[] searchManager(String searchWay, String keyword) {
		if( !nullCheck(new String[]{searchWay, keyword}))
			return null;
		Object[] objs = null;
		List<Object> list = new ArrayList<Object>();
		
		try {
			switch(searchWay){
			case "���̵�":
				list.add(managerViewMapper.searchMemberId(keyword));
				break;
			case "�̸�":
				for(Object o:managerViewMapper.searchMemberName(keyword)){
					list.add(o);
				}
				break;
			case "�Խ��Ǹ�":
				list.add(managerViewMapper.searchBoardName(keyword));
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		objs = new Object[list.size()];
		for(int i=0; i<list.size(); i++){
			objs[i] = list.get(i);
		}
		return objs;
	}
	
	public Object[] searchManager() {
		Object[] arr = null;
		System.out.println("������� ��ġ�Ŵ���");
		List<ManagerVO> list = null;
		
		try {
			list = managerViewMapper.searchManagerViewAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arr = new Object[list.size()];
		for(int i=0; i<list.size(); i++){
			arr[i] = list.get(i);
		}
		return arr;
	}
	// ��� ���� ã��
	public Object[] searchBoard(){
		Object[] arr = null;
		List<BoardDTO> list = null;
		try {
			list = boardMapper.searchBoardAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		arr = new Object[list.size()];
		for(int i=0; i<list.size(); i++){
			arr[i] = list.get(i);
		}
		return arr;
	}
	
	public boolean isManager(String memberId){
		if(memberId == null) return false;
		
		ManagerVO vo = null;
		try {
			vo = managerViewMapper.searchMemberId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(vo != null)
			return true;
		else return false;
	}

	/*���̵� �ߺ��˻�*/
	public boolean searchId(String memberId){
		boolean flag = false;
		MemberDTO memberDTO = null;
		try {
			memberDTO = memberMapper.searchId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO == null){
			//ȸ���� ������ true��ȯ
			flag = true;
		}
		return flag;
	}
	
	/*�г��� �ߺ��˻�*/
	public boolean searchNickname(String memberNickname){
		boolean flag = false;
		MemberDTO memberDTO = null;
		try {
			memberDTO = memberMapper.searchNickname(memberNickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO == null){
			flag = true;
		}
		
		return flag;
	}
	
	/*�̸��� �ߺ��˻�*/
	public boolean searchMail(String memberMail){
		boolean flag = false;
		MemberDTO memberDTO = null;
		try {
			memberDTO = memberMapper.searchMail(memberMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO == null){
			flag = true;
		}
		return flag;
	}
	
	/*����ȣ �ߺ��˻�*/
	public boolean searchPhone(String memberPhone){
		boolean flag = false;
		MemberDTO memberDTO = null;
		try {
			memberDTO = memberMapper.searchPhone(memberPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(memberDTO == null){
			flag = true;
		}
		return flag;
	}

	@Override
	public int idCheck(String memberId) throws Exception {
		return memberMapper.idCheck(memberId);
	}
	
	@Override
	public int emailCheck(String memberMail) throws Exception{
		return memberMapper.emailCheck(memberMail);
	}
	
	@Override
	public int phoneCheck(String memberPhone) throws Exception{
		return memberMapper.phoneCheck(memberPhone);
		
	}
	public boolean nullCheck(String[] str)
	{
		if(str == null){return false;}
		for(int i=0;i<str.length;i++)
		{
			if(str[i] == null )
			{
				return false;
			}
		}
		return true;
	}
}
