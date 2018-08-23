package com.lecto.forward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lecto.forward.service.ArticleService;


@Controller
public class ArticleController{
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String memberMain() {
		return "index";
	}
	
	/*boardCode�� �̿��� �Խ��Ǻ� �Խñ۸���� ������*/
/*	@RequestMapping(value="/m_board", method=RequestMethod.GET)
	public String articleListMem(String boardCode, Model model) {
		System.out.println("boardCode�� : "+boardCode);
		model.addAttribute("articles", articleService.searchArticle(boardCode));
		return "/m_board";
	}
	*/
	@RequestMapping(value="/m_board", method=RequestMethod.POST)
	public String articleListMemPost(String boardCode, Model model) {
		System.out.println("boardCode�� : "+boardCode);
		model.addAttribute("articles", articleService.searchArticle(boardCode));
		return "/m_board";
	}
	
	
	/*boardCode, searchWay, keyword�� ������ �Խñ��� �˻�*/
//	@RequestMapping(value="/m_board", method=RequestMethod.GET)
//	public String searchArticleMem(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/m_board";
//	}
	
	/*boardCode, searchWay, keyword�� ������ �Խñ��� �˻�*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.GET)
//	public String searchArticleAdmin(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {		
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/a_articlelist";
//	}
	
	/*boardCode, searchWay, keyword�� ������ �Խñ��� �˻�*/
//	@RequestMapping(value="/m_board", method=RequestMethod.GET)
//	public String searchArticleMem(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/m_board";
//	}
	
	/*boardCode�� �̿��� �Խ��Ǻ� �Խñ۸���� ������*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.GET)
//	public String articleListAdmin(@RequestParam("boardCode") String boardCode, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode));
//		return "/a_articlelist";
//		
//	}
	

//	
//	/*articleCode�� �̿��� �Խñ� Ŭ���� �󼼺��⿡ ����� �����͸� ������*/
//	@RequestMapping(value="/a_detailarticle", method=RequestMethod.GET)
//	public String readArticleAdmin(@RequestParam("articleCode") String articleCode, Model model) {
//		model.addAttribute("detail", articleService.searchArticle(articleCode, 1));
//		return "/a_detailarticle";
//	}
//	
//	/*articleCode�� �̿��� �Խñ� Ŭ���� �󼼺��⿡ ����� �����͸� ������*/
//	@RequestMapping(value="/m_detailarticle", method=RequestMethod.GET)
//	public String readArticleMem(@RequestParam("articleCode") String articleCode, Model model) {
//		model.addAttribute("detail", articleService.searchArticle(articleCode, 1));
//		return "/m_detailarticle";	
//	}
//	
//	/*�Խñ� ������ư Ŭ���� ������ �̵�*/
//	@RequestMapping(value="/a_editarticle", method=RequestMethod.GET)
//	public String updateArticleAdminGet() {
//		return "/a_editarticle";
//	}
//	
//	/*�Խñ� ������ư Ŭ���� ������ �̵�*/
//	@RequestMapping(value="/m_editarticle", method=RequestMethod.GET)
//	public String updateArticleMemGet() {
//		return "/m_editarticle";
//		
//	}
//	
//	/*�Խñ� ����*/
//	
//
//	@RequestMapping(value="/a_editarticle", method=RequestMethod.POST)
//	public String updateArticleAdminPOST(ArticleDTO articleDTO) {
//		
//		return "/a_detailarticle";
//	}
//	
//	/*�Խñ� ����*/
//	@RequestMapping(value="/m_editarticle", method=RequestMethod.POST)
//	public String updateArticleMemPOST() {
//		return "/m_detailarticle";
//	}
//	
//	/*�Խñ� ����*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.POST)
//	public String deleteArticleAdmin(@RequestParam("articleCode") String articleCode, RedirectAttributes rda) {
//		return "redirect:/a_articlelist";
//	}
//	
//	/*�Խñ� ����*/
//	@RequestMapping(value="/m_detailarticle", method=RequestMethod.POST)
//	public String deleteArticleMem() {
//		return "redirect:/m_board";
//	}
//	
//	/*���/���������������� �Խñ� �߰�*/
//	@RequestMapping(value="/register", method=RequestMethod.POST)
//	public String addArticleAdmin() {
//		return "/a_detailarticle";
//	}
//	
//	/*ȸ������������ �Խñ� �߰�*/
//	@RequestMapping(value="/a_addarticle", method=RequestMethod.POST)
//	public String addArticleMem() {
//		return "/m_detailarticle";
//	}
//	
//	/*������ - ����ȭ�鿡�� ���� ���, ��ڸ�� ���
//	��� - ����ȭ�鿡�� ���� ���, �ֽŸ�� ���*/
//	@RequestMapping(value="/m_writearticle", method=RequestMethod.POST)
//	public String adminMain() {
//		return "/a_main";
//	}
}
