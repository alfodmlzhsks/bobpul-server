package bapul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class KakaoRestApiHelper {
	public enum HttpMethodType {
		POST, GET, DELETE
	}

	private static final String CID = "TC0ONETIME"; // CID ������ ��ȣ ����μ� TEST ID
	private static final String API_SERVER_HOST = "kapi.kakao.com"; // īī�� ���� API ȣ��Ʈ
	private static final String PAY_READY = "/v1/payment/ready";// ���� �غ� url
	private static final String PAY_APPROVE = "/v1/payment/approve"; // ���� ���� url
	private static final String PAY_STATUS = "/v1/payment/status"; // ����������ȸ url
	private static final String PAY_CANCEL = "/v1/payment/cancel"; // ���� ��� url
	private static final String PAY_REPORT = "/v1/payment/manage/report"; // ����������ȸ url

	private static final List<String> adminApiPaths = new ArrayList<String>();

	static {
		adminApiPaths.add(PAY_REPORT);
	}
	private String accessToken;
	private String adminKey;

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public void setAdminKey(final String adminKey) {
		this.adminKey = adminKey;
	}

	public String request(HttpMethodType httpMethod, final String apiPath, final String params) {
		String requestUrl = API_SERVER_HOST + apiPath;
		if (httpMethod == null) {
			httpMethod = HttpMethodType.POST;
		}
		if (params != null && params.length() > 0
				&& (httpMethod == HttpMethodType.POST || httpMethod == HttpMethodType.DELETE)) {
			requestUrl += params;
		}
		HttpURLConnection conn;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader isr = null;

		try {
			final URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(httpMethod.toString());

			if (adminApiPaths.contains(apiPath)) {
				conn.setRequestProperty("Authorization", "KakaoAK " + this.adminKey);
			} else {
				conn.setRequestProperty("Authorization", "Bearer " + this.accessToken);
			}

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
                conn.setDoOutput(true);
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(params);
                writer.flush();
            }
            
            final int responseCode = conn.getResponseCode();
            System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream());
            else
                isr = new InputStreamReader(conn.getErrorStream());
            
            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            System.out.println(buffer.toString());
            
            return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (Exception ignore) {
				}
			if (reader != null)
				try {
					reader.close();
				} catch (Exception ignore) {
				}
			if (isr != null)
				try {
					isr.close();
				} catch (Exception ignore) {
				}
		}

		return null;
	}
}
