package com.lecto.forward.service;

import java.util.List;

import com.lecto.forward.dto.ArticleDTO;
import com.lecto.forward.vo.ArticleVO;

public interface ArticleService {
	public void addArticle(ArticleDTO articleDTO);
	public void updateArticle(ArticleDTO articleDTO);
	public void deleteArticle(String articleCode);
	public void deleteArticles(String[] articleCodes);
	public List<ArticleVO> searchArticle(String boardCode, String searchWay, String keyword);
	public List<ArticleVO> searchArticle(String boardCode);
	public ArticleVO searchArticle(String articleCode, int flag);
	//public ArticleDTO searchArticle(String articleCode);
	public String generateCode();
}
