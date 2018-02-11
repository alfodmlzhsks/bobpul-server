<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bapul.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 테스트</title>
</head>
<body>
	<center>
		<form name="loginform" method="post" action="Login.jsp">
			<fieldset>
				<legend>
					<h2>SQL AddMem TEST</h2>
				</legend>
				<p>
					ID<input type="text" name="strUserID">
				</p>
				<p>
					PW<input type="password" name="strUserPW" maxlength="20"><br>
				</p>
				<p>
					Birth<input type="text" name="strBirth">
				</p>
				<p>
					Telnum<input type="text" name="strTel">
				</p>
				<p>
					Address<input type="text" name="strAddr">
				</p>
				<p>
					<input type="hidden" name="action" value="addMem"> <input
						type="submit" name="addMem" value="회원가입">
				</p>
			</fieldset>
		</form>
	</center>
</body>
</html>