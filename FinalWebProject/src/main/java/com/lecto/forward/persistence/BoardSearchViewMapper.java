package com.lecto.forward.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.vo.BoardSearchVO;

/** �Խ��� ���� ����� ���̵�, �Խ��Ǹ�, �׸��� �����ִ� view */
public interface BoardSearchViewMapper {
	/** �Խ��Ǹ����� ã�� */
	public BoardSearchVO searchBoardName(@Param("boardName")String boardName) throws Exception;
	/** BoardSearchVO ��ü�� ã�� */
	public BoardSearchVO searchBoardSearchView(BoardSearchVO boardSearchVO) throws Exception;
	/** BoardSearchVO ����Ʈ�� ã�� */
	public List<BoardSearchVO> searchBoardSearchViews(List<BoardSearchVO> boardSearches) throws Exception;
	/** ���� ����� ���̵�� ã�� */
	public BoardSearchVO searchMemberId(@Param("memberId")String memberId) throws Exception;
	/** �Խ��� ����� ��� ã�� */
	public List<BoardSearchVO> searchBoardSearchViewAll() throws Exception;
	/** �׸������� ã��*/
	public List<BoardSearchVO> searchThemeName(@Param("themeName")String themeName) throws Exception;	//�߰���
}
