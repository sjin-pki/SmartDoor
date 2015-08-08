package org.zero.servlet.web.sms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.json.JSONObject;
import org.zero.db.entity.sms.SdValidation;
import org.zero.db.entity.sms.SdValidationDAO;

public class SendCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendCode() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String cell = request.getParameter("cell");

		JSONObject json = new JSONObject();

		if (cell != null && !cell.isEmpty()) {
			SdValidationDAO dao = new SdValidationDAO();
			SdValidation validation = generateValidation(cell);
			Transaction transaction = dao.getSession().beginTransaction();
			dao.save(validation);
			transaction.commit();
			String message = "您的验证码是" + validation.getSdValidationCode()
					+ ",本次识别码为(" + validation.getSdValidationTag()
					+ "),30分钟内有效。[国展中心]";
			System.out.println(message);
			// TODO: 调用发送短信模块
			
			json.put("status", "1");
			json.put("id", validation.getSdValidationId());
			json.put("tag", "(" + validation.getSdValidationTag() + ")");
		} else {
			json.put("status", "0");
			json.put("data", "请输入正确的手机号码");
		}

		out.print(json);
		out.flush();
		out.close();
	}

	private SdValidation generateValidation(String cell) {
		Random r = new Random(System.currentTimeMillis());
		String code = (int) (r.nextFloat() * 1000000) + "";
		String tag = (int) (r.nextFloat() * 100) + "";
		return new SdValidation(cell, code, tag, new Timestamp(System
				.currentTimeMillis()));
	}
}
