package com.lecto.forward.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lecto.forward.dto.Criteria;
import com.lecto.forward.dto.MemberDTO;
import com.lecto.forward.dto.PageMaker;
import com.lecto.forward.service.MemberService;
import com.lecto.forward.vo.ManagerVO;
import com.lecto.forward.vo.MemberArticleVO;

@Controller
public class MemberController{
private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/m_signup", method=RequestMethod.POST)
	public String registerMemberPost(Model model, HttpServletRequest req) {
		
		String memberMail = req.getParameter("memberMail1")+"@"+req.getParameter("memberMail2");
		MemberDTO memberDTO = new MemberDTO(req.getParameter("memberId"),req.getParameter("memberPwd")
				,req.getParameter("memberName"),req.getParameter("memberNickname"),req.getParameter("memberBirth"),memberMail
				,req.getParameter("memberPhone"),req.getParameter("memberAddress"),req.getParameter("registerDate"));
		try {
			memberService.addMember(memberDTO);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/login";
	}
	
	/**JOIN��ư Ŭ���� ȸ������â���� �̵�*/
	@RequestMapping(value="/m_signup", method=RequestMethod.GET)
	public String registerMemberPost() {
		return "/m_signup";
	}
	
	/**���̵� ã�� �˾�â���� �α���ȭ������*/
	@RequestMapping(value="/find_id_popup", method=RequestMethod.GET)
	public String findGET() {
		return "/login";
	}
	
	@RequestMapping(value="/find_pwd_popup", method=RequestMethod.POST)
	public String findPwdPOST(HttpSession session,HttpServletRequest req) {
		System.out.println("���ã�� ��޿��� �Ѿ�Դ�.");
		String memberId = (String)session.getAttribute("memberId");
		String newPwd = req.getParameter("changePwd");
		System.out.println(memberId+","+newPwd);
		memberService.updateMemberPwd(memberId, newPwd);
		session.removeAttribute("memberId");
		
		return "/login";
	}
	
	@RequestMapping(value="/find_pwd_popup/cancel", method=RequestMethod.GET)
	public String findPwdCancelGET() {
		return "redirect:/find_id_pwd";
	}
	
	@RequestMapping(value="/find_pwd_popup", method=RequestMethod.GET)
	public String findIdPwdGET() {
		return "/find_id_pwd";
	}

	/**���̵� �ߺ� üũ*/
	@RequestMapping("/checkid.do")
	@ResponseBody
	public Map<Object,Object> checkId(@RequestBody String memberId){
		int count=0;
		Map<Object, Object> map = new HashMap<Object,Object>();
		try {
			count = memberService.idCheck(memberId);
			map.put("cnt", count);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**ȸ�����Խ� �г��� �ߺ�Ȯ��(�񵿱�)*/
	@RequestMapping("/checknickname.do")
	@ResponseBody
	public Map<Object,Object> checkNickname(@RequestBody String nickNameParam){
		int count=0;
		System.out.println(nickNameParam);
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			count = memberService.nicknameCheck(nickNameParam);
			map.put("cnt", count);
			System.out.println(count);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	/**����ȣ �ߺ� üũ*/
	@RequestMapping("/checkphone.do")
	@ResponseBody
	public Map<Object,Object> checkphone(@RequestBody String memberPhone){
		int count =0;
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			count = memberService.phoneCheck(memberPhone);
			map.put("cnt", count);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**�̸��� �ߺ� üũ*/
	@RequestMapping("/checkmail.do")
	@ResponseBody
	public Map<Object,Object> checkmail(@RequestBody String memberMail){
		int count=0;
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			count = memberService.emailCheck(memberMail);
			map.put("cnt", count);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**���̵� ã�� �˾�â(�񵿱�)*/
	@RequestMapping("/findid.do")
	@ResponseBody
	public Map<Object,Object> findId(@RequestBody Map<Object, Object> map){
		
		int count=0;
		String memberName=(String) map.get("memberName");
		String memberPhone=(String) map.get("memberPhone");
		String memberMail=(String) map.get("memberMail");
		System.out.println(memberName+","+memberPhone+","+memberMail);
		String memberId=null;
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		try {
			count = memberService.findId(memberName,memberPhone,memberMail);
			System.out.println(count);
			memberId = memberService.searchMemberId(memberName, memberPhone, memberMail);
			resultMap.put("cnt", count);
			resultMap.put("memberId", memberId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**��й�ȣ ã�� �˾�â(�񵿱�)*/
	@RequestMapping("/findpwd.do")
	@ResponseBody
	public Map<Object,Object> findPwd(@RequestBody Map<Object, Object> map,HttpSession session){
		System.out.println("�Դ�.");
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		String memberId = (String)map.get("memberId");
		String memberPhone = (String)map.get("memberPhone");
		String memberMail = (String)map.get("memberMail");
		System.out.println(memberId+","+memberPhone+","+memberMail);
		try {
			int count = memberService.findPwd(memberId,memberPhone,memberMail);
			System.out.println(count);
			if(count>0) {
				session.setAttribute("memberId", memberId);
			}
			resultMap.put("cnt", count);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/** ȸ�� - ���ο��� ������������ */
	@RequestMapping(value="m_mypage", method=RequestMethod.GET)
	public String mainToMyPage(Model model, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
		MemberArticleVO[] list = memberService.searchMemberInfo(sessionId);
		model.addAttribute("memberArticleList", list);
		if(list != null) {
			model.addAttribute("cnt", list.length);
			System.out.println(list[0].getArticleTitle());
		}
		return "/m_mypage";
	}
	
	/** ȸ�� - �������������� ȸ��������������  */
	@RequestMapping(value="/m_modify", method=RequestMethod.GET)
	public String updateMemberGet(Model model, HttpSession session) {
	
		String memberId = (String)session.getAttribute("login");
		
		MemberDTO findMember = memberService.searchMember(memberId);
		String mail = findMember.getMemberMail();
		int idx = mail.indexOf("@");
		
		String memberMail1 = mail.substring(0,idx);
		String memberMail2 = mail.substring(idx+1);
		
		model.addAttribute("findMember", findMember);
		model.addAttribute("memberMail1", memberMail1);
		model.addAttribute("memberMail2", memberMail2);
	
		return "/m_modify";
	}
	
	@RequestMapping(value="/m_modify", method=RequestMethod.POST)
	public String updateMemeberPost(Model model, HttpServletRequest req, HttpSession session) {
		
		String memberId = (String)session.getAttribute("login");
		System.out.println("aa"+memberId);
		String memberMail = req.getParameter("memberMail1")+"@"+req.getParameter("memberMail2");
		MemberDTO memberDTO = new MemberDTO(memberId,req.getParameter("memberPwd"),req.getParameter("memberName"),req.getParameter("memberNickname"),req.getParameter("memberBirth"),memberMail
				,req.getParameter("memberPhone"),req.getParameter("memberAddress"),"2019-08-24");
		try {
			memberService.updateMember(memberDTO);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return "/m_mypage";
	}
	
	@RequestMapping(value="/m_modify/cancel", method=RequestMethod.GET)
	public String cancelUpdateMember() {
		
		return "redirect:/m_mypage";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** ������ - ȸ�� �߰��� ���� */
	@RequestMapping(value="ad_addmember", method=RequestMethod.GET)
	public String addMemberGET(HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			return "/ad_addmember";
		}
		return "/ad_memberlist";
	}
	
	/** ������ - ȸ�� �߰� */
	@RequestMapping(value="ad_addmember", method=RequestMethod.POST)
	public String addMemberPOST(MemberDTO memberDTO, @RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			RedirectAttributes rda, HttpSession session) throws Exception {
		

		String sessionId = (String) session.getAttribute("login");
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

	/** ������ - ȸ�� ���� �������� ���� */
	@RequestMapping(value="ad_editmember", method=RequestMethod.GET)
	public String updateMemberGET(@RequestParam("memId")String memId, @ModelAttribute("cri")Criteria cri, Model model, HttpSession session) {
		
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			MemberDTO member = memberService.searchMember(memId);
			model.addAttribute("member", member);
			model.addAttribute("tel2", member.getMemberPhone().substring(3,7));
			model.addAttribute("tel3", member.getMemberPhone().substring(7));
		}
		return "/ad_editmember";
	}
	
	/** ������ - ȸ�� ���� ���� */
	@RequestMapping(value="/ad_editmember", method=RequestMethod.POST)
	public String updateMemberPOST(MemberDTO memberDTO, @RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			Criteria cri, RedirectAttributes rda, HttpSession session) throws Exception {
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			memberDTO.setMemberPhone("010"+tel2+tel3);
			rda.addAttribute("page", cri.getPage());
			rda.addAttribute("perPageNum", cri.getPerPageNum());
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

	
	/** ȸ�� ��� ��ȸ */
	@RequestMapping(value="ad_memberlist", method=RequestMethod.GET)
	public String memberListGET(Model model, HttpSession session){

		String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList); // ���� ���� �ɷ�
		}
		return "ad_memberlist";
	}
	
	/** Ư�� �Խ��� ȸ�� ��� ��ȸ */
	@RequestMapping(value="ad_memberlist/board", method=RequestMethod.POST)
	public String memberListBoardGET(@RequestParam("boardName") String boardName, Criteria cri, Model model, HttpSession session, HttpServletRequest request){

		String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			Object[] memberList;
		    PageMaker pageMaker = new PageMaker();
		    pageMaker.setCri(cri);
			if(request.getAttribute("memberList")!=null) {
				pageMaker.setTotalCount(1);
			}else {
				memberList = memberService.searchBoardMember(boardName, cri);
				model.addAttribute("memberList", memberList);
				if(memberList != null && memberList.length != 0)
					pageMaker.setTotalCount(memberList.length);
			}
		    model.addAttribute("pageMaker", pageMaker);
		    
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList); // ���� ���� �ɷ�
			model.addAttribute("boardName",boardName);
		}
		return "/ad_memberlist";
	}
	
	/** ȸ�� Ű���� ��ȸ  */
	@RequestMapping(value="/ad_memberlist/search", method=RequestMethod.POST)
	public String searchMemberPOST(@RequestParam("boardName")String boardName, 
			@RequestParam("searchWay")String searchWay, @RequestParam("keyword")String keyword, Criteria cri,
			Model model, HttpSession session, HttpServletRequest request) {
		
		String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			Object[] memberList = new Object[1];
			memberList = memberService.searchBoardMember(boardName, searchWay, keyword, cri);
			if(memberList !=null && memberList.length != 0)
				System.out.println(memberList[0].toString());
			model.addAttribute("memberList", memberList);
			request.setAttribute("memberList", memberList);
			return memberListBoardGET(boardName, cri, model, session, request);
		} else {
			model.addAttribute("msg", "FAIL");
			return "/ad_memberlist";
		}
	}
	
	/** ������ - ȸ�� ���� */
	@RequestMapping(value="/ad_memberlist/delete", method=RequestMethod.POST)
	public String deleteMemberPOST(@RequestParam("chk") String[] memberIds, Criteria cri, RedirectAttributes rda, HttpSession session){

		String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			//String[] memberIds = request.getParameterValues("chk");
			if(!memberService.deleteMember(memberIds)){
				rda.addFlashAttribute("msg", "FAIL");
				System.out.println("���� �����ε�");
			} else{
				rda.addFlashAttribute("msg", "SUCCESS");
				System.out.println("���� �����ε�");
			}
		} else {
			rda.addFlashAttribute("msg", "FAIL");
		}
		rda.addAttribute("page", cri.getPage());
		rda.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/ad_memberlist";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** ������ - ��� �߰�  */
	@RequestMapping(value="/ad_addmanager", method=RequestMethod.GET)
	public String addManagerGET(Model model, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			Object[] boardList = memberService.searchBoard();
			model.addAttribute("boardList", boardList);
		}
		return "/ad_addmanager";
	}
	
	/** ������ - ��� �߰�  */
	@RequestMapping(value="/ad_addmanager", method=RequestMethod.POST)
	public String addManagerPOST(MemberDTO memberDTO, @RequestParam("boardName")String boardName,
			@RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			Model model, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
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
	
	/** ������ - ��� ���� �������� ���� */
	@RequestMapping(value="/ad_editmanager", method=RequestMethod.GET)
	public String updateManagerGET(@RequestParam("memberId")String memberId,
			Model model, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			ManagerVO vo = (ManagerVO) memberService.searchManager("���̵�", memberId)[0];
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
	
	/** ������ - ��� ����  */
	@RequestMapping(value="/ad_editmanager/update", method=RequestMethod.POST)
	public String updateMemberPOST(MemberDTO memberDTO, @RequestParam("boardName")String boardName,
			@RequestParam("tel2")String tel2, @RequestParam("tel3") String tel3,
			RedirectAttributes rda, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("login");
		if(sessionId.equals("admin")){
			
			memberDTO.setMemberPhone("010"+tel2+tel3);
			if(memberService.updateMember(memberDTO, boardName)){
				rda.addFlashAttribute("msg", "SUCCESS");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SUCCESS");
			} else {
				rda.addFlashAttribute("msg", "FAIL");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FAIL");
			}
		} else{
			rda.addFlashAttribute("msg", "FAIL");
		}
		return "redirect:/ad_managerlist";
	}
	
	/** ��� ��� ��ȸ */
	@RequestMapping(value="/ad_managerlist", method=RequestMethod.GET)
	public String managerListGET(Model model, HttpSession session, HttpServletRequest request){

		String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			Object memberList;
			if(request.getAttribute("membeList")!=null) {
				memberList = (Object) request.getAttribute("membeList");
				model.addAttribute("memberList", memberList);
			}else {
				memberList = memberService.searchManager();
				System.out.println("�̰ž�??");
				model.addAttribute("memberList", memberList);
			}
		}
		return "/ad_managerlist";
	}
	
	
	/** ��� Ű���� ��ȸ  */
	@RequestMapping(value="/ad_managerlist/search", method=RequestMethod.POST)
	public String searchManagerPOST(@RequestParam("searchWay")String searchWay, @RequestParam("keyword")String keyword,
			Model mv, HttpSession session, HttpServletRequest request) {
		
		String sessionId = (String) session.getAttribute("login");
		
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
	
	/** ������ - ��� ���� */
	@RequestMapping(value="/ad_managerlist/delete", method=RequestMethod.POST)
	public String adminManagerDelete(@RequestParam("chk") String[] memberIds, Model model, HttpSession session){

		String sessionId = (String) session.getAttribute("login");
		
		if(sessionId.equals("admin")){
			//String[] memberIds = request.getParameterValues("chk");
			if(!memberService.deleteMember(memberIds)){
				model.addAttribute("msg", "FAIL");
				System.out.println("���� �����ε�");
			} else{
				model.addAttribute("msg", "SUCCESS");
				System.out.println("���� �����ε�");
			}
		} else {
			model.addAttribute("msg", "FAIL");
		}
		return "redirect:/ad_managerlist";
	}
	
	/** ������ ���� ���� */
	@RequestMapping(value="/ad_admininfo", method=RequestMethod.GET)
	public String updateAdminPOST(Model model, HttpSession session){

		String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			
		}
		return "/ad_admininfo";
	}
	
	/** ������ ���� ���� */
	@RequestMapping(value="/ad_admininfo", method=RequestMethod.POST)
	public String updateAdminPOST(@RequestParam("newMemberPwd")String newMemberPwd, Model model, HttpSession session){

		String sessionId = (String) session.getAttribute("login");

		if(sessionId.equals("admin")){
			memberService.updateMemberPwd(sessionId, newMemberPwd);
			model.addAttribute("msg", "SUCCESS");
		} else {
			model.addAttribute("msg", "FAIL");
		}
		return "/ad_admininfo";
	}
	
}
