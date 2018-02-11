<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="info" scope="page" class="bapul.DO" />
<jsp:setProperty name="info" property="*" />
<jsp:useBean id="dao" scope="page" class="bapul.DAO" />

<%
	String action = request.getParameter("action");

	if (action.equals("login")) {
		System.out.println("접속 사용자 : " + info.getStrUserID() + " " + info.getStrUserPW());
		boolean isChk = dao.login(info);
		if (isChk) {
			System.out.println("로그인 성공");
			out.println("1");
		} else {
			System.out.println("로그인 실패");
			out.println("0");
		}
	}

	if (action.equals("addMem")) {
		System.out.println("새로운 유저가 가입했습니다");
		System.out.println("사용자 > " + info.getStrUserID() + " 비밀번호 > " + info.getStrUserPW());
		boolean isChk = dao.addMem(info);
		if (isChk) {
			System.out.println("회원가입 성공");
			out.clear();
			out.println("1");
		} else {
			System.out.println("회원가입 거절");
			out.clear();
			out.println("0");
		}
	}
%>

