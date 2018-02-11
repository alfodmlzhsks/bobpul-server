package bapul;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;

	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://noring.iptime.org:7011/bapul";

	// DB����
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url, "coin", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DB��������
	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// �ű� ȸ������(��Ǯ���� ȸ������)
	public boolean addMem(DO do1) {
		connect();
		String sql = "insert into member (userid, userpw, birth, address, telnum) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			pstmt.setString(2, do1.getStrUserPW());
			pstmt.setString(3, do1.getStrBirth());
			pstmt.setString(4, do1.getStrAddr());
			pstmt.setString(5, do1.getStrTel());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		} finally {
			disconnect();
		}
		return true;
	}

	// ����� �α���
	public boolean login(DO do1) {
		connect();

		String sql = "select * from member where userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("[Test]");
				if (do1.getStrUserPW().equals(rs.getString("userpw"))) {
					return true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return false;
	}

	// ��й�ȣ ����
	public boolean chgPsWd(DO do1) {
		connect();
		String sql = "update member set userpw=? where userid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrChgPw());
			pstmt.setString(2, do1.getStrUserID());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		} finally {
			disconnect();
		}
		return true;
	}

	// �ӽú�й�ȣ �߱�
	public String randomPsWd(DO do1) {
		connect();
		String sql = "update member set userpw=? where userid=?";

		String newPW = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer sc = new StringBuffer("!@+#~&");

		// �빮�� 4���� ���� �߻�
		sb.append((char) ((Math.random() * 26) + 65)); // ù���ڴ� �빮��, ù���ں��� Ư������
														// ������ �� �̻�
		for (int i = 0; i < 3; i++) {
			sb.append((char) ((Math.random() * 26) + 65)); // �ƽ�Ű��ȣ 65(A) ����
															// 26���� �߿��� ����
		}

		// �ҹ��� 4���� ���ǹ߻�
		for (int i = 0; i < 4; i++) {
			sb.append((char) ((Math.random() * 26) + 97)); // �ƽ�Ű��ȣ 97(a) ����
															// 26���� �߿��� ����
		}

		// ���� 2���� ���� �߻�
		for (int i = 0; i < 2; i++) {
			sb.append((char) ((Math.random() * 10) + 48)); // �ƽ�Ű��ȣ 48(1) ����
															// 10���� �߿��� ����
		}

		// Ư�����ڸ� �ΰ� �߻����� �����ϰ� �߰��� ���� �ִ´�
		sb.setCharAt(((int) (Math.random() * 3) + 1), sc.charAt((int) (Math.random() * sc.length() - 1))); // �빮��3����
																											// �ϳ�
		sb.setCharAt(((int) (Math.random() * 4) + 4), sc.charAt((int) (Math.random() * sc.length() - 1))); // �ҹ���4����
																											// �ϳ�
		do1.setStrTempPw(sb.toString());

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrTempPw());
			pstmt.setString(2, do1.getStrUserID());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return do1.getStrTempPw();
	}

	// ����Ʈ ��ȸ
	public int getPoint(DO do1) {
		connect();
		int iUserPoint = 0;
		String sql = "select * from member where userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				do1 = new DO();
				do1.setiPoint(rs.getInt("point"));
			}
			rs.close();
			iUserPoint = do1.getiPoint();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return iUserPoint;
	}

	// �ܾ� ��ȸ
	public int getMoney_B(DO do1) {
		connect();
		int iMoney_B = 0;
		String sql = "select * from member where userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				do1 = new DO();
				do1.setIB_Money(rs.getInt("balance"));
			}
			rs.close();
			iMoney_B = do1.getIB_Money();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return iMoney_B;
	}

	// �Խñ� �ۼ�
	public boolean addBoard(DO do1) {
		connect();
		String sql = "insert into board_data (userid,b_title,b_content,b_join,b_minPP,b_maxPP,b_opprice,b_finfo,b_openM,b_openD) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			pstmt.setString(2, do1.getStrBoardTitle());
			pstmt.setString(3, do1.getStrBoardContent());
			pstmt.setString(4, do1.getStrMeetName());
			pstmt.setString(5, do1.getStrMinP());
			pstmt.setString(6, do1.getStrMaxP());
			pstmt.setInt(7, do1.getiOpPrice());
			pstmt.setString(8, do1.getStrRestInfo());
			pstmt.setString(9, do1.getStrOpMonth());
			pstmt.setString(10, do1.getStrOpDate());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	// �Խñ� ����
	public boolean deleteBoard(DO do1) {
		connect();
		String sql = "delete from board_data where b_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, do1.getiB_Num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	// �Խñ� ��ȸ
	public String boardList(DO do1) {
		connect();
		String sql = "select * from board_data where userid=?";

		JSONArray list = new JSONArray();
		JSONObject OBJ = new JSONObject();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, do1.getStrUserID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("number", rs.getInt("b_num"));
				obj.put("userid", rs.getString("userid"));
				obj.put("food_info", rs.getString("b_finfo"));
				obj.put("owner_tag", rs.getString("b_opentag"));
				obj.put("price", rs.getInt("b_opprice"));
				obj.put("min", rs.getInt("b_minPP"));
				obj.put("max", rs.getInt("b_maxPP"));
				obj.put("title", rs.getString("b_title"));
				obj.put("content", rs.getString("b_content"));

				list.add(obj);
			}
			OBJ.put("board_Data", list);

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			disconnect();
		}
		return OBJ.toJSONString();
	}

	public boolean sendMailSMTP(DO do1) {
		final String toMail = do1.getStrMail();
		final String fromMail = "bapul_management@co.kr";
		final String fromMailUser = "��Ǯ ������";
		final String title = do1.getStrUserID() + "���� �ӽú�й�ȣ�� �߱޵Ǿ����ϴ�";
		final String content = "�ӽ� ��й�ȣ�� " + randomPsWd(do1) + " �Դϴ�\n�ݵ�� ������������ ���� ��й�ȣ ������ ���ֽñ� �ٶ��ϴ�.";

		// ���� ���� ��� SSL
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		SMTPAuthenticator smtp = new SMTPAuthenticator();

		Session session = Session.getInstance(props, smtp);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail, fromMailUser));

			msg.setSubject(title, "UTF-8");

			msg.setSentDate(new Date());
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(content);
			mp.addBodyPart(mbp1);

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);

			msg.setContent(mp);
			Transport.send(msg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}
}
