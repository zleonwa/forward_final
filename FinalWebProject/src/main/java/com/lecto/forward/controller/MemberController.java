package com.lecto.forward.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lecto.forward.dto.MemberDTO;
import com.lecto.forward.service.MemberService;
import com.lecto.forward.vo.ManagerVO;
import com.lecto.forward.vo.MemberArticleVO;

@Controller
public class MemberController{
private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	/** 회원 - 메인에서 마이페이지로 */
	@RequestMapping(value="m_mypage", method=RequestMethod.GET)
	public String mainToMyPage(Model model, HttpSession session) {
		
		String sessionId = "aaa";
		//String sessionId = (String) session.getAttribute("login");
		MemberArticleVO[] list = memberService.searchMemberInfo(sessionId);
		model.addAttribute("memberArticleList", list);
		if(list != null) {
			model.addAttribute("cnt", list.length);
			System.out.println(list[0].getArticleTitle());
		}
		return "/m_mypage";
	}
	
	/** 회원 - 마이페이지에서 회원정보수정으로  */
	@RequestMapping(value="m_modify", method=RequestMethod.GET)
	public String updateMyInfoGET(Model model, HttpSession session) {
		
		String sessionId = "aaa";
		//String sessionId = (String) session.getAttribute("login");
		MemberDTO dto = memberService.searchMember(sessionId);
		
		model.addAttribute("memberDTO",dto);
		return "/m_modify";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** 관리자 - 회원 추가로 가기 */
	@RequestMapping(value="ad_addmember", method=RequestMethod.GET)
	public String addMemberGET(HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			return "/ad_addmember";
		}
		return "/ad_memberlist";
	}
	
	/** 관리자 - 회원 추가 */
	@RequestMapping(value="ad_addmember", method=RequestMethod.POST)
	public String addMemberPOST(MemberDTO memberDTO, @RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			RedirectAttributes rda, HttpSession session) throws Exception {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			
			memberDTO.setMemberPhone("010"+tel2+tel3);
			if(memberService.addMember(memberDTO)){
				rda.addFlashAttribute("msg", "SUCCESS");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SUCCESS");
			} else {
				rda.addFlashAttribute("msg", "FAIL");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FAIL");
			}
		} else{
			rda.addFlashAttribute("msg", "FAIL");
		}
		return "redirect:/ad_memberlist";
	}
	
	/** 관리자 - 회원 정보 수정으로 가기 */
	@RequestMapping(value="ad_editmember", method=RequestMethod.GET)
	public String updateMemberGET(@RequestParam("memId")String memId, Model model, HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			MemberDTO member = memberService.searchMember(memId);
			model.addAttribute("member", member);
			model.addAttribute("tel2", member.getMemberPhone().substring(3,7));
			model.addAttribute("tel3", member.getMemberPhone().substring(7));
		}
		return "/ad_editmember";
	}
	
	/** 관리자 - 회원 정보 수정 */
	@RequestMapping(value="/ad_editmember", method=RequestMethod.POST)
	public String updateMemberPOST(MemberDTO memberDTO, @RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			RedirectAttributes rda, HttpSession session) throws Exception {
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			memberDTO.setMemberPhone("010"+tel2+tel3);
			System.out.println(memberDTO.getMemberId());
			if(memberService.updateMember(memberDTO)){
				rda.addFlashAttribute("msg", "SUCCESS");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SUCCESS");
			} else {
				rda.addFlashAttribute("msg", "FAIL");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FAIL");
			}
		} else{
			rda.addFlashAttribute("msg", "FAIL");
		}
		return "redirect:/ad_memberlist";
	}

	
	/** 회원 목록 조회 */
	@RequestMapping(value="ad_memberlist", method=RequestMethod.GET)
	public String memberListGET(Model model, HttpSession session){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");

		if(sessionId.equals("admin")){
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList); // 빼서 쓰는 걸로
		}
		return "ad_memberlist";
	}
	
	/** 특정 게시판 회원 목록 조회 */
	@RequestMapping(value="ad_memberlist/board", method=RequestMethod.POST)
	public String memberListBoardGET(@RequestParam("boardName") String boardName, Model model, HttpSession session, HttpServletRequest request){
		// 
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");

		if(sessionId.equals("admin")){
			Object memberList;
			if(request.getAttribute("memberList")!=null) {
				//memberList = request.getAttribute("membeList");
				//model.addAttribute("memberList", memberList);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
				//if(memberList != null)
					//System.out.println(memberList.toString());
			}else {
				memberList = memberService.searchBoardMember(boardName);
				model.addAttribute("memberList", memberList);
				System.out.println("??????????????????????");
			}
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList); // 빼서 쓰는 걸로
			model.addAttribute("boardName",boardName);
		}
		return "/ad_memberlist";
	}
	
	/** 회원 키워드 조회  */
	@RequestMapping(value="/ad_memberlist/search", method=RequestMethod.POST)
	public String searchMemberPOST(@RequestParam("boardName")String boardName, 
			@RequestParam("searchWay")String searchWay, @RequestParam("keyword")String keyword,
			Model model, HttpSession session, HttpServletRequest request) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			Object[] memberList = new Object[1];
			memberList = memberService.searchBoardMember(boardName, searchWay, keyword);
			if(memberList !=null && memberList.length != 0)
				System.out.println(memberList[0].toString());
			model.addAttribute("memberList", memberList);
			//model.addAttribute("boardName", boardName);
			model.addAttribute("msg", "SUCCESS");
			request.setAttribute("memberList", memberList);
			return memberListBoardGET(boardName, model, session, request);
		} else {
			model.addAttribute("msg", "FAIL");
			return "/ad_memberlist";
		}
	}
	
	/** 관리자 - 회원 삭제 */
	@RequestMapping(value="/ad_memberlist/delete", method=RequestMethod.POST)
	public String deleteMemberPOST(@RequestParam("chk") String[] memberIds, Model model, HttpSession session){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");
		
		if(sessionId.equals("admin")){
			//String[] memberIds = request.getParameterValues("chk");
			if(!memberService.deleteMember(memberIds)){
				model.addAttribute("msg", "FAIL");
				System.out.println("삭제 실패인데");
			} else{
				model.addAttribute("msg", "SUCCESS");
				System.out.println("삭제 성공인데");
			}
		} else {
			model.addAttribute("msg", "FAIL");
		}
		return "redirect:/ad_memberlist";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** 관리자 - 운영자 추가  */
	@RequestMapping(value="/ad_addmanager", method=RequestMethod.GET)
	public String addManagerGET(Model model, HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList);
		}
		return "/ad_addmanager";
	}
	
	/** 관리자 - 운영자 추가  */
	@RequestMapping(value="/ad_addmanager", method=RequestMethod.POST)
	public String addManagerPOST(MemberDTO memberDTO, @RequestParam("boardName")String boardName,
			@RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			Model model, HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			
			memberDTO.setMemberPhone("010"+tel2+tel3);
			if(memberService.addMember(memberDTO, boardName)){
				model.addAttribute("msg", "SUCCESS");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SUCCESS");
			} else {
				model.addAttribute("msg", "FAIL");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FAIL");
			}
		} else{
			model.addAttribute("msg", "FAIL");
		}
		return "redirect:/ad_managerlist";
	}
	
	/** 관리자 - 운영자 정보 수정으로 가기 */
	@RequestMapping(value="/ad_editmanager", method=RequestMethod.GET)
	public String updateManagerGET(@RequestParam("memberId")String memberId,
			Model model, HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			ManagerVO vo = (ManagerVO) memberService.searchManager("아이디", memberId)[0];
			MemberDTO member = memberService.searchMember(memberId);
			model.addAttribute("member", member);
			model.addAttribute("manager", vo);
			
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList);
			
			model.addAttribute("tel2", member.getMemberPhone().substring(3,7));
			model.addAttribute("tel3", member.getMemberPhone().substring(7));
		}
		return "/ad_editmanager";
	}
	
	/** 관리자 - 운영자 수정  */
	@RequestMapping(value="/ad_editmanager/update", method=RequestMethod.POST)
	public String updateMemberPOST(MemberDTO memberDTO, @RequestParam("boardName")String boardName,
			@RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			Model model, HttpSession session) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");
		if(sessionId.equals("admin")){
			
			memberDTO.setMemberPhone("010"+tel2+tel3);
			if(memberService.updateMember(memberDTO, boardName)){
				model.addAttribute("msg", "SUCCESS");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SUCCESS");
			} else {
				model.addAttribute("msg", "FAIL");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FAIL");
			}
		} else{
			model.addAttribute("msg", "FAIL");
		}
		return "redirect:/ad_managerlist";
	}
	
	/** 운영자 목록 조회 */
	@RequestMapping(value="/ad_managerlist", method=RequestMethod.GET)
	public String managerListGET(Model model, HttpSession session, HttpServletRequest request){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");
		
		if(sessionId.equals("admin")){
			Object memberList;
			if(request.getAttribute("membeList")!=null) {
				memberList = (Object) request.getAttribute("membeList");
				model.addAttribute("memberList", memberList);
			}else {
				memberList = memberService.searchManager();
				System.out.println("이거야??");
				model.addAttribute("memberList", memberList);
			}
		}
		return "/ad_managerlist";
	}
	
	
	/** 운영자 키워드 조회  */
	@RequestMapping(value="/ad_managerlist/search", method=RequestMethod.POST)
	public String searchManagerPOST(@RequestParam("searchWay")String searchWay, @RequestParam("keyword")String keyword,
			Model mv, HttpSession session, HttpServletRequest request) {
		
		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			Object[] memberList = memberService.searchManager(searchWay, keyword);
			if(memberList != null && memberList.length == 0) {
				System.out.println(memberList[0].toString());
			}
			mv.addAttribute("membeList", memberList);
			mv.addAttribute("msg", "SUCCESS");
			request.setAttribute("membeList", memberList);
			return managerListGET(mv, session, request);
		} else {
			mv.addAttribute("msg", "FAIL");
			return "/ad_managerlist";
		}
	}
	
	/** 관리자 - 운영자 삭제 */
	@RequestMapping(value="/ad_managerlist/delete", method=RequestMethod.POST)
	public String adminManagerDelete(@RequestParam("chk") String[] memberIds, Model model, HttpSession session){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("memberId");
		
		if(sessionId.equals("admin")){
			//String[] memberIds = request.getParameterValues("chk");
			if(!memberService.deleteMember(memberIds)){
				model.addAttribute("msg", "FAIL");
				System.out.println("삭제 실패인데");
			} else{
				model.addAttribute("msg", "SUCCESS");
				System.out.println("삭제 성공인데");
			}
		} else {
			model.addAttribute("msg", "FAIL");
		}
		return "redirect:/ad_managerlist";
	}
	
	/** 관리자 정보 수정 */
	@RequestMapping(value="/ad_admininfo", method=RequestMethod.GET)
	public String updateAdminPOST(Model model, HttpSession session){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			
		}
		return "/ad_admininfo";
	}
	
	/** 관리자 정보 수정 */
	@RequestMapping(value="/ad_admininfo", method=RequestMethod.POST)
	public String updateAdminPOST(@RequestParam("newMemberPwd")String newMemberPwd, Model model, HttpSession session){

		String sessionId = "admin";
		//String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			memberService.updateMemberPwd(sessionId, newMemberPwd);
			model.addAttribute("msg", "SUCCESS");
		} else {
			model.addAttribute("msg", "FAIL");
		}
		return "/ad_admininfo";
	}
	
}
