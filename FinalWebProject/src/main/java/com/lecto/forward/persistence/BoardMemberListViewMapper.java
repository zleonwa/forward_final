package com.lecto.forward.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.vo.BoardMemberListVO;

public interface BoardMemberListViewMapper {
	/** ���� �ڵ忡 �ش��ϴ� �Խ����� ��� ���� �������� */
	public List<BoardMemberListVO> searchBoardCode(String boardCode) throws Exception;
	/** ���� �̸��� �ش��ϴ� �Խ����� ��� ���� �������� */
	public List<BoardMemberListVO> searchBoardName(String boardName) throws Exception;
	/** ���� �ڵ忡 �ش��ϴ� �Խ��ǿ��� ������̵�� ��ġ�ϴ� ��� ���� �������� */
	public List<BoardMemberListVO> searchBCMemberId(@Param("boardCode")String boardCode, @Param("memberId")String memberId) throws Exception;
	/** ���� �̸��� �ش��ϴ� �Խ��ǿ��� ������̵�� ��ġ�ϴ� ��� ���� �������� */
	public List<BoardMemberListVO> searchBNMemberId(@Param("boardName")String boardName, @Param("memberId")String memberId) throws Exception;
	/** ���� �ڵ忡 �ش��ϴ� �Խ��ǿ��� ����г��Ӱ� ��ġ�ϴ� ��� ���� �������� */
	public List<BoardMemberListVO> searchBCMemberNickname(@Param("boardCode")String boardCode, @Param("memberNickname")String memberNickname) throws Exception;
	/** ���� �̸��� �ش��ϴ� �Խ��ǿ��� ����г��Ӱ� ��ġ�ϴ� ��� ���� �������� */
	public List<BoardMemberListVO> searchBNMemberNickname(@Param("boardName")String boardName, @Param("memberNickname")String memberNickname) throws Exception;
	/** ���� �ڵ忡 �ش��ϴ� �Խ��ǿ��� ��޸�� ��ġ�ϴ� ��� ������ �������� */
	public List<BoardMemberListVO> searchBCGradeName(@Param("boardCode")String boardCode, @Param("gradeName")String gradeName) throws Exception;
	/** ���� �̸��� �ش��ϴ� �Խ��ǿ��� ��޸�� ��ġ�ϴ� ��� ������ �������� */
	public List<BoardMemberListVO> searchBNGradeName(@Param("boardName")String boardName, @Param("gradeName")String gradeName) throws Exception;
}
