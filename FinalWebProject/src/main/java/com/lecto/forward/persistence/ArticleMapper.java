package com.lecto.forward.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.dto.ArticleDTO;

public interface ArticleMapper {
	public void addArticle(ArticleDTO articleDTO) throws Exception;
	public void updateArticleParams(@Param("articleCode")String articleCode,@Param("articleHits")int articleHits,@Param("articleTitle") String articleTitle, @Param("articleContent")String articleContent,@Param("notice") boolean notice, @Param("articleDate")String articleDate, @Param("boardCode")String boardCode, @Param("memberId")String memberId)throws Exception;
	public void updateArticleParam(ArticleDTO articleDTO)throws Exception;
	public void updateTitle(@Param("articleCode")String articleCode, @Param("articleDate")String articleDate, @Param("articleTitle")String articleTitle)throws Exception;
	public void updateContent(@Param("articleCode")String articleCode, @Param("articleDate")String articleDate, @Param("newContent")String newContent)throws Exception;
	public void updateHits(@Param("articleCode")String articleCode,@Param("articleHits")int articleHits)throws Exception;
	public void updateNotice(@Param("articleCode")String articleCode, @Param("articleDate")String articleDate, @Param("notice")boolean notice)throws Exception;
	public void updateDate(@Param("articleCode")String articleCode, @Param("articleDate")String articleDate)throws Exception;
	public int deleteArticleCode(@Param("articleCode")String articleCode)throws Exception;
	public int deleteArticleMemberId(@Param("memberId")String memberId)throws Exception;
	public int deleteArticleParam(ArticleDTO articleDTO)throws Exception;	//deleteArticleParam
	public int deleteArticleParams(List<ArticleDTO> articles)throws Exception;	//deleteArticleParams///////////
	/*코드 여러개를 받아 article여러개 삭제*/
	public int deleteArticleCodes(List<String> articles) throws Exception;
	public List<ArticleDTO> searchTitle(@Param("articleTitle")String articleTitle)throws Exception;
	public List<ArticleDTO> searchKeyword(@Param("searchMethod")String searchMethod, @Param("keyword")String keyword)throws Exception; //제목 //내용 //닉네임
	public List<ArticleDTO> searchDate(@Param("startDate")String startDate,@Param("endDate")String endDate)throws Exception;
	public List<ArticleDTO> searchId(@Param("memberId")String memberId)throws Exception;
	public List<ArticleDTO> searchBoardCode(@Param("boardCode")String boardCode)throws Exception;
	public ArticleDTO searchArticleCode(@Param("articleCode")String articleCode)throws Exception;
	public ArticleDTO searchArticleParam(ArticleDTO articleDTO)throws Exception;		//searchArticleParam
	public List<ArticleDTO> searchArticleParams(List<ArticleDTO> articles)throws Exception;//searchArticleParams/////////
	public List<ArticleDTO> searchAllArticle() throws Exception;
}
