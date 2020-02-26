package com.internousdev.bianco.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dao.DestinationInfoDAO;
import com.internousdev.bianco.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware {

	private String id;
	private Map<String, Object> session;
	private List<DestinationInfoDTO> destinationInfoDTOList;

	public String execute() {

		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined) ? 0 : Integer.parseInt(tmpLogined);
		if (logined != 1) {
			return "loginError";
		}

		String result = ERROR;
		//DestinationInfoDAOから削除メソッド取得
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		int count = destinationInfoDAO.deleteDestination(id);
		//DestinationInfoDAOから宛先情報メソッド取得
		if (count > 0) {
			destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(session.get("userId").toString());
			result = SUCCESS;
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
