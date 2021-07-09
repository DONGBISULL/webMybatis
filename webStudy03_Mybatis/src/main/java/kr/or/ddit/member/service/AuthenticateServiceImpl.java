package kr.or.ddit.member.service;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	private MemberDaoImpl memberDAO = MemberDaoImpl.getInstance();
	
	@Override
	public Object authenticate(MemberVO param) {
		Object resultValue = null;
		MemberVO savedMember =memberDAO.selectMemberById(param.getMemId());
		if(savedMember==null) {
			throw new UserNotFoundException(String.format("%s 회원은 없음 ", param.getMemId()));
		}
		
		//String deleteMem = savedMember.getMemDelete();
		String savedPass = savedMember.getMemPass();
		String inputPass = param.getMemPass();
		boolean valid = savedPass.equals(inputPass);
		if(valid) {
		 
				//throw new DataNotFoundException("탈퇴 처리된 회원입니다");
				resultValue = savedMember;
		
		}else{
			resultValue = ServiceResult.INVALIDPASSWORD;
		}
		return resultValue;
	}

}
