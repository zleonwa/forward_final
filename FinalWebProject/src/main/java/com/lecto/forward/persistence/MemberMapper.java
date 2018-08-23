package com.lecto.forward.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lecto.forward.dto.MemberDTO;

public interface MemberMapper {
	/** ȸ�� �߰� */
	public void addMember(MemberDTO memberDTO) throws Exception;
	/** ȸ�� ������ �߰� */
	public void addMembers(List<MemberDTO> members) throws Exception;
	/** ���̵� ���� ȸ�� ���� ���� */
	public void updateMember(MemberDTO memberDTO) throws Exception;
	/** ���̵� �´� ȸ���� ��� ���� */
	public void updatePwd(@Param("memberId")String memberId, @Param("memberPwd")String memberPwd) throws Exception;
	/** ���̵� �´� ȸ���� �г��� ���� */
	public void updateNickname(@Param("memberId")String memberId, @Param("memberNickname")String memberNickname) throws Exception;
	/** ���̵� �´� ȸ���� �̸� ���� */
	public void updateName(@Param("memberId")String memberId, @Param("memberName")String memberName) throws Exception;
	/** ���̵� �´� ȸ���� ������� ���� */
	public void updateBirth(@Param("memberId")String memberId, @Param("memberBirth")String memberBirth) throws Exception;
	/** ���̵� �´� ȸ���� ���� ���� */
	public void updateMail(@Param("memberId")String memberId, @Param("memberMail")String memberMail) throws Exception;
	/** ���̵� �´� ȸ���� ���� ���� */
	public void updatePhone(@Param("memberId")String memberId, @Param("memberPhone")String memberPhone) throws Exception;
	/** ���̵� �´� ȸ���� �ּ� ���� */
	public void updateAddress(@Param("memberId")String memberId, @Param("memberAddress")String memberAddress) throws Exception;
	/** ���̵� �´� ȸ���� ���� ���� */
	public int deleteMember(String memberId) throws Exception;	
	/** ���̵� �´� ȸ���� ������ ���� */
	public int deleteMembers(ArrayList<String> memberIds) throws Exception;	
	/** ���̵�� ��й�ȣ ��ġ�ϸ� ���̵� ���� */
	public MemberDTO login(@Param("memberId")String memberId, @Param("memberPwd")String memberPwd) throws Exception;
	/** ���̵� ��ġ�ϴ� ���̵� ���� */
	public MemberDTO searchId(String memberId) throws Exception;
	/** ��� ȸ�� ���� */
	public List<MemberDTO> searchIds() throws Exception;
	/** ���̵� ã�� ��, �̸�, ����, ���� ��ġ�ϸ� MemebrDTO ���� */
	public MemberDTO searchFindId(@Param("memberName")String memberName, @Param("memberPhone")String memberPhone, @Param("memberMail")String memberMail) throws Exception;
	/** ��� ã�� ��, ���̵�, ����, ���� ��ġ�ϸ� MemebrDTO ���� */
	public MemberDTO searchFindPwd(@Param("memberId")String memberId, @Param("memberPhone")String memberPhone, @Param("memberMail")String memberMail) throws Exception;
	/** �д��� ��ġ�ϴ� MemebrDTO ���� */
	public MemberDTO searchNickname(String memberNickname) throws Exception;
	/** ���� ��ġ�ϴ� MemebrDTO ���� */
	public MemberDTO searchPhone(String memberPhone) throws Exception;
	/** ���� ��ġ�ϴ� MemebrDTO ���� */
	public MemberDTO searchMail(String memberMail) throws Exception;
	/** MemebrDTO�� ���� ��ġ�ϴ� MemebrDTO ���� */
	public MemberDTO searchMember(MemberDTO memberDTO) throws Exception;
	/** MemebrDTO��� ���� ��ġ�ϴ� MemebrDTO�� ���� */
	public List<MemberDTO> searchMembers(ArrayList<MemberDTO> members) throws Exception;
	/** ID�ߺ��˻�(�񵿱�)*/
	public int idCheck(String memberId) throws Exception;
	/** �̸����ߺ��˻�(�񵿱�)*/
	public int emailCheck(String memberMail)throws Exception;
	/** ����ȣ�ߺ��˻�(�񵿱�)*/
	public int phoneCheck(String memberPhone)throws Exception;
}
