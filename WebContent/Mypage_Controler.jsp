<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bapul.*" import="java.util.*"
	import="java.sql.*"%>
<jsp:useBean id="info" scope="page" class="bapul.DO" />
<jsp:useBean id="dao" scope="page" class="bapul.DAO" />
<jsp:setProperty name="info" property="*" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String action = request.getParameter("action");

	if (action.equals("update")) {

	}
	if (action.equals("delete")) {

	}
	if (action.equals("addOn")) {

	}
	if (action.equals("serch_point")) {
		System.out.println("포인트 조회로 넘어옴");
		int iPassData = dao.getPoint(info);
		if (iPassData >= 0) {
			System.out.println("[보내려 하는 값!] > " + iPassData + " / [사용자!] > " + info.getStrUserID());
			out.clear();
			out.print(iPassData);
		}
	}
	if (action.equals("serch_money")) {
		System.out.println("잔액 조회로 넘어옴");
		int iPassData = dao.getMoney_B(info);
		if (iPassData >= 0) {
			System.out.println("[보내려 하는 값!] > " + iPassData + " / [사용자!] > " + info.getStrUserID());
			out.clear();
			out.print(iPassData);
		}
	}
	if (action.equals("change_pw")) {
		System.out.println("비밀번호 변경으로 넘어옴");
		boolean isChk = dao.chgPsWd(info);
		if (isChk) {
			System.out.println("비밀번호 변경완료! " + "[변경된 값] > " + info.getStrChgPw());
			out.clear();
			out.print("1");
		} else {
			System.err.println("비밀번호 변경 실패!");
			out.clear();
			out.print("0");
		}
	}

	if (action.equals("crt_pw")) {
		System.out.println("새로운 임시 비밀번호를 생성합니다\n반드시 로그인후 변경해주세요");
		boolean isChk = dao.sendMailSMTP(info);
		if (isChk) {
			System.out.println("메일 전송완료!");
			out.clear();
			out.print("1");
		} else {
			System.out.println("메일 전송실패\nSMTP서버를 확인하세요");
			out.clear()	;
			out.print("0");
		}
	}
%>