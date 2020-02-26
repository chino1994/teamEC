package com.internousdev.bianco.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dao.CartInfoDAO;
import com.internousdev.bianco.dao.PurchaseHistoryInfoDAO;
import com.internousdev.bianco.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware {
	private String id;
	private Map<String, Object> session;

	public String execute() throws SQLException {

		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined) ? 0 : Integer.parseInt(tmpLogined);
		if (logined != 1) {
			return "loginError";
		}
		String result = ERROR;

		String user_id = session.get("userId").toString();

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = cartInfoDAO.getCartInfo(user_id);

		//purchaseHistoryInfoDAOからメソッド取得
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = 0;
		for (CartInfoDTO dto : cartInfoDTOList) {
			count = purchaseHistoryInfoDAO.regist(
					user_id,
					dto.getProductId(),
					dto.getProductCount(),
					Integer.parseInt(id),
					dto.getPrice());
		}
		//CartInfoDAOから削除メソッド取得
		if (count > 0) {
			count = cartInfoDAO.SettlementDeleteAll(String.valueOf(session.get("userId")));
			if (count > 0) {
				result = SUCCESS;
			}
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
