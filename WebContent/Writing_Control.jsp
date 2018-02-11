<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bapul.*" import="java.sql.*"
	import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="info" scope="page" class="bapul.DO" />
<jsp:useBean id="dao" scope="page" class="bapul.DAO" />
<jsp:setProperty name="info" property="*" />

<%
	String action = request.getParameter("action");

	if (action.equals("search")) { // 조회
		System.out.println("아이디 : " + info.getStrUserID());
		out.print(dao.boardList(info));
	}

	if (action.equals("update")) { // 수정

	}
	if (action.equals("delete")) { // 삭제
		boolean isChk = dao.deleteBoard(info);
		if (isChk) {
			System.out.println("게시글 삭제완료!");
			out.clear();
			out.print("1");
		} else {
			System.err.println("삭제실패!");
			out.clear();
			out.print("0");
		}
	}

	if (action.equals("addOn")) { // 추가
		System.out.println(
				info.getStrUserID() + " " + info.getStrBoardTitle() + "\n" + info.getStrBoardContent()+"\n"+info.getStrMeetName());
		boolean isChk = dao.addBoard(info);
		if (isChk) {
			System.out.println("게시글 등록성공!");
			out.clear();
			out.print("1");
		} else {
			System.err.println("등록실패!");
			out.clear();
			out.print("0");
		}
	}
%>
