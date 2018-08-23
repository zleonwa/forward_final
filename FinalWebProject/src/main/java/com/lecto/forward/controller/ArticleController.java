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
	
	/*boardCode를 이용해 게시판별 게시글목록을 가져옴*/
/*	@RequestMapping(value="/m_board", method=RequestMethod.GET)
	public String articleListMem(String boardCode, Model model) {
		System.out.println("boardCode야 : "+boardCode);
		model.addAttribute("articles", articleService.searchArticle(boardCode));
		return "/m_board";
	}
	*/
	@RequestMapping(value="/m_board", method=RequestMethod.POST)
	public String articleListMemPost(String boardCode, Model model) {
		System.out.println("boardCode야 : "+boardCode);
		model.addAttribute("articles", articleService.searchArticle(boardCode));
		return "/m_board";
	}
	
	
	/*boardCode, searchWay, keyword를 가지고 게시글을 검색*/
//	@RequestMapping(value="/m_board", method=RequestMethod.GET)
//	public String searchArticleMem(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/m_board";
//	}
	
	/*boardCode, searchWay, keyword를 가지고 게시글을 검색*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.GET)
//	public String searchArticleAdmin(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {		
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/a_articlelist";
//	}
	
	/*boardCode, searchWay, keyword를 가지고 게시글을 검색*/
//	@RequestMapping(value="/m_board", method=RequestMethod.GET)
//	public String searchArticleMem(@RequestParam("boardCode") String boardCode, @RequestParam("searchWay") String searchWay, @RequestParam("keyword") String keyword, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode, searchWay, keyword));
//		return "/m_board";
//	}
	
	/*boardCode를 이용해 게시판별 게시글목록을 가져옴*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.GET)
//	public String articleListAdmin(@RequestParam("boardCode") String boardCode, Model model) {
//		model.addAttribute("list", articleService.searchArticle(boardCode));
//		return "/a_articlelist";
//		
//	}
	

//	
//	/*articleCode를 이용해 게시글 클릭후 상세보기에 사용할 데이터를 가져옴*/
//	@RequestMapping(value="/a_detailarticle", method=RequestMethod.GET)
//	public String readArticleAdmin(@RequestParam("articleCode") String articleCode, Model model) {
//		model.addAttribute("detail", articleService.searchArticle(articleCode, 1));
//		return "/a_detailarticle";
//	}
//	
//	/*articleCode를 이용해 게시글 클릭후 상세보기에 사용할 데이터를 가져옴*/
//	@RequestMapping(value="/m_detailarticle", method=RequestMethod.GET)
//	public String readArticleMem(@RequestParam("articleCode") String articleCode, Model model) {
//		model.addAttribute("detail", articleService.searchArticle(articleCode, 1));
//		return "/m_detailarticle";	
//	}
//	
//	/*게시글 수정버튼 클릭시 페이지 이동*/
//	@RequestMapping(value="/a_editarticle", method=RequestMethod.GET)
//	public String updateArticleAdminGet() {
//		return "/a_editarticle";
//	}
//	
//	/*게시글 수정버튼 클릭시 페이지 이동*/
//	@RequestMapping(value="/m_editarticle", method=RequestMethod.GET)
//	public String updateArticleMemGet() {
//		return "/m_editarticle";
//		
//	}
//	
//	/*게시글 수정*/
//	
//
//	@RequestMapping(value="/a_editarticle", method=RequestMethod.POST)
//	public String updateArticleAdminPOST(ArticleDTO articleDTO) {
//		
//		return "/a_detailarticle";
//	}
//	
//	/*게시글 수정*/
//	@RequestMapping(value="/m_editarticle", method=RequestMethod.POST)
//	public String updateArticleMemPOST() {
//		return "/m_detailarticle";
//	}
//	
//	/*게시글 삭제*/
//	@RequestMapping(value="/a_articlelist", method=RequestMethod.POST)
//	public String deleteArticleAdmin(@RequestParam("articleCode") String articleCode, RedirectAttributes rda) {
//		return "redirect:/a_articlelist";
//	}
//	
//	/*게시글 삭제*/
//	@RequestMapping(value="/m_detailarticle", method=RequestMethod.POST)
//	public String deleteArticleMem() {
//		return "redirect:/m_board";
//	}
//	
//	/*운영자/관리자페이지에서 게시글 추가*/
//	@RequestMapping(value="/register", method=RequestMethod.POST)
//	public String addArticleAdmin() {
//		return "/a_detailarticle";
//	}
//	
//	/*회원페이지에서 게시글 추가*/
//	@RequestMapping(value="/a_addarticle", method=RequestMethod.POST)
//	public String addArticleMem() {
//		return "/m_detailarticle";
//	}
//	
//	/*관리자 - 메인화면에서 공지 목록, 운영자목록 출력
//	운영자 - 메인화면에서 공지 목록, 최신목록 출력*/
//	@RequestMapping(value="/m_writearticle", method=RequestMethod.POST)
//	public String adminMain() {
//		return "/a_main";
//	}
}
