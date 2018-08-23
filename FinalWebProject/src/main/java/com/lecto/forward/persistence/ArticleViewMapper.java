package com.lecto.forward.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.vo.ArticleVO;

/** ��� �Խ��� �� �Խñ� ��ȸ �� �� ���� ���... �ۼ��� �г���, ����, ����, ����, �Խñ� �ڵ�, �ۼ�����, ��ȸ��, ��� ��, �Խ����ڵ� */
public interface ArticleViewMapper {
	/** �Խ��Ǹ����� �Խ��Ǻ� �Խñ� ��� �޾ƿ���  */
	public List<ArticleVO> searchArticle(String boardCode) throws Exception;
	/** �Խ��Ǻ� �Խñ� �˻����(�г���, ����, ����) ���ؼ� �˻���� �κ� �˻��ϱ� */
	public List<ArticleVO> searchArticleKeyword(@Param("boardCode")String boardCode, @Param("searchWayCode")String searchWayCode, @Param("keyword")String keyword) throws Exception;  //@Param("boardCode")
	/** ���� �Խñ۸� �������� */
	public List<ArticleVO> searchNotice(boolean notice) throws Exception;
	/** �� ��ü�� �˻��ؼ� ������ �������� */
	public ArticleVO searchArticleView(ArticleVO articleVO) throws Exception;
	/** �������� �˻��ؼ� ������ �������� */
	public List<ArticleVO> searchArticleViews(List<ArticleVO> articles) throws Exception;
	/** articleCode�� �Խñ� ã��*/
	public ArticleVO searchArticleCode(String articleCode) throws Exception;
}
