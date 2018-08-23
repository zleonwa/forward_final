package com.lecto.forward.persistence;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.dto.MemberGradesDTO;

public interface MemberGradesMapper {
	/**MemberGrades �ϳ��� �߰�*/
	public void addMemberGradesDTO(MemberGradesDTO membreGradesDTO) throws Exception;
	/**MemberGrades ������ �߰�*/
	public void addMemberGrades(List<MemberGradesDTO> memberGradeses) throws Exception;
	/**MemberGrades �Ѱ� ����*/
	public void updateMemberGradesDTO(MemberGradesDTO memberGradesDTO) throws Exception;
	/**MemberGrades ������ ����*/
	public void updateMemberGrades(List<MemberGradesDTO> memberGradeses) throws Exception;
	/*��� memberGradesDTO ã��*/
	public List<MemberGradesDTO> searchMembarGradesAll() throws Exception;
	/**boardCode�� memberGradesDTO ������ ã��*/
	public List<MemberGradesDTO> searchToBoardCode(String boardCode) throws Exception;
	/**memberId�� memberGradesDTO ������ ã��*/
	public  List<MemberGradesDTO> searchToMemberId(String memberId) throws Exception;
	/**�ش� �Խ����� ȸ�����̵�� ����Ǿ� �ִ� memberGradesDTO ã�� (����: ȸ����� ã��)*/
	public MemberGradesDTO searchBoardGrades(@Param("boardCode")String boardCode,@Param("memberId") String memberId) throws Exception;
	/**�ش� �Խ����� �Ķ���ͷ� �ִ� ������� memberGreadesDTO ã��	(����: �ش� �Խ����� ��� ���� �� ������ �ִ� ����� �ٲٱ�����)*/
	public  List<MemberGradesDTO> searchMemberIdGrades(@Param("boardCode")String boardCode,@Param("gradeNum") int gradeNum) throws Exception;
	/**memberGrades Ű������ ã�� �޼ҵ�*/
	public MemberGradesDTO searchBoardMemberGrades(String boardMemberGrade) throws Exception;	
	/**ȸ��Ż��� �ش� ȸ������ ��ϵ� memberGradesDTO ����*/
	public void deleteMemberId(String memberId) throws Exception;
	/**�Խ��� ���� �� �ش� �Խ������� ��ϵ� memberGradesDTO ����*/
	public void deleteBoardCode(String boardCode) throws Exception;
}
