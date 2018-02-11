<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQL 쿼리문 전송 테스트</title>
</head>
<body>
	<center>
		<form name="loginform" method="post" action="Login.jsp">
			<fieldset>
				<legend>
					<h2>SQL Login TEST</h2>
				</legend>
				<p>
					ID<input type="text" name="strUserID">
				</p>
				<p>
					PW<input type="password" name="strUserPW" maxlength="20"><br>
				</p>
				<p>
					<input type="hidden" name="action" value="login"> <input
						type="submit" name="login" value="login">
				</p>
			</fieldset>
		</form>
	</center>
	<hr>
	<center>
		<form name="boardform" method="post" action="Writing_Control.jsp">
			<fieldset>
				<legend>
					<h2>SQL Memo TEST</h2>
				</legend>
				<p>
					ID<input type="text" name="strUserID">
				</p>
				<p>
					Meet Name<input type="text" name="strMeetName" maxlength="20"><br>
				</p>
				<p>
					Memo Title<input type="text" name="strBoardTitle" maxlength="20"><br>
				</p>
				<div>
					<h4>Memo Content</h4>
					<textarea rows="10" cols="40" name="strBoardContent"></textarea>
				</div>
				<p>
					<input type="hidden" name="action" value="addOn"> <input
						type="submit" name="addOn" value="send">
				</p>
			</fieldset>
		</form>
	</center>
	<hr>
	<center>
		<form name="boardform" method="post" action="Writing_Control.jsp">
			<fieldset>
				<legend>
					<h2>SQL Memo TEST</h2>
				</legend>
				<p>
					ID<input type="text" name="strUserID">
				</p>
				<p>
					<input type="hidden" name="action" value=search> <input
						type="submit" name="search" value="send">
				</p>
			</fieldset>
		</form>
	</center>
	<hr>
	<center>
		<form name="privateform" method="post" action="Mypage_Controler.jsp">
			<fieldset>
				<legend>
					<h2>SQL Mypage TEST</h2>
				</legend>
				<div>
					사용자 아이디 <input type="text" name="strUserID"> <br> <input
						type="hidden" name="action" value="serch_point"> <input
						type="submit" value="포인트 조회" name="serch_point"><br>
				</div>
			</fieldset>
		</form>
	</center>
	<center>
		<form name="privateform" method="post" action="Mypage_Controler.jsp">
			<fieldset>
				<legend>
					<h2>SQL Mypage TEST</h2>
				</legend>
				<div>
					사용자 아이디 <input type="text" name="strUserID"> <br> <input
						type="hidden" name="action" value="serch_money"> <input
						type="submit" value="잔액 조회" name="serch_money"><br>
				</div>
			</fieldset>
		</form>
	</center>
	<center>
		<form name="privateform" method="post" action="Mypage_Controler.jsp">
			<fieldset>
				<legend>
					<h2>SQL Mypage TEST</h2>
				</legend>
				<div>
					사용자 아이디 <input type="text" name="strUserID"> <br> 사용자
					비밀번호<input type="password" name="strUserPW"> <br>
					변경하고자 하는 비밀번호<input type="password" name="strChgPw"> <input
						type="hidden" name="action" value="change_pw"> <input
						type="submit" value="비밀번호 변경!" name="change_pw"><br>
				</div>
			</fieldset>
		</form>
	</center>
	<center>
		<form name="privateform" method="post" action="Mypage_Controler.jsp">
			<fieldset>
				<legend>
				
					<h2>SQL Mypage TEST</h2>
				</legend>
				<div>
					사용자 아이디<input type="text" name="strUserID">
					발송 원하는 이메일<input type="text" name="strMail">
					<input type="hidden" name="action" value="crt_pw"> <input
						type="submit" value="임시 비밀번호 발급요청!" name="crt_pw"><br>
				</div>
			</fieldset>
		</form>
	</center>
</body>
</html>