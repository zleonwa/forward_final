package com.lecto.forward.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lecto.forward.dto.MemberDTO;
import com.lecto.forward.service.MemberService;
import com.lecto.forward.vo.ManagerVO;

@Controller
public class LoginController {

	@Inject
	private MemberService memberService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "/login";
	}

	/** �α���(������,��� / ȸ��) */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO member, Model model, HttpSession session) {
		session.removeAttribute("login");

		try {
			System.out.println("a");
			MemberDTO memberDTO = memberService.login(member.getMemberId());
			if (memberDTO != null) {
				if (memberDTO.getMemberPwd().equals(member.getMemberPwd())) {
					session.setAttribute("login", member.getMemberId());
					// ��� view���� ���
					System.out.println("b");

					// Object�� ��ã�ƿ´�.
					Object[] managers = memberService.searchManager();
					List<String> managerIds = new ArrayList<String>();
					if (managers != null) {
						
						for (int i = 0; i < managers.length; i++) {
							managerIds.add(((ManagerVO) managers[i]).getMemberId());

						}
					}
					System.out.println("c");

					// �������� ���
					if (member.getMemberId().equals("admin")) {
						System.out.println("d");
						return "/a_main";
					}
					// ����� ���
					else if (managerIds.contains((member.getMemberId()))) {
						System.out.println("e");
						return "/a_main";
					}
					// ������� ���
					else {
						System.out.println("f");
						return "/index";
					}

				} else {
					model.addAttribute("error", "wrong_memberPwd");
					return "/login";
				}
			} else {
				model.addAttribute("error", "wrong_memberId");
				return "/login";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/** �α׾ƿ� ���� ���� ���� */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.removeAttribute("login");

		return "redirect:/index";
	}

	/** ���̵�,��й�ȣã��(���ο��� ���̵�/��� ã�� �������� �̵�) */
	@RequestMapping(value = "/find_id_pwd", method = RequestMethod.GET)
	public String findIdPwdGET() {

		return "/find_id_pwd";
	}

}
