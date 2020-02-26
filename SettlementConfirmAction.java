package com.internousdev.bianco.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dao.DestinationInfoDAO;
import com.internousdev.bianco.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {
	public List<DestinationInfoDTO> destinationInfoDTOList;
	public Map<String, Object> session;

	public String execute() {

		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined) ? 0 : Integer.parseInt(tmpLogined);
		if (logined != 1) {
			return "loginError";
		}

		String user_id = session.get("userId").toString();

		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(user_id);

		return SUCCESS;
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
