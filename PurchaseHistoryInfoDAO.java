package com.internousdev.bianco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.bianco.dto.PurchaseHistoryInfoDTO;
import com.internousdev.bianco.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String userId) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		/* DBから商品購入情報を取得 */
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();
		String sql = "SELECT " + "phi.id, " /* ID */
				+ "phi.user_id, " /* ユーザーID */
				+ "phi.product_count, " /* 個数 */
				+ "pi.product_id, " /* 商品ID */
				+ "pi.product_name, " /* 商品名 */
				+ "pi.product_name_kana, " /* 商品名かな */
				+ "pi.product_description, " /* 商品詳細 */
				+ "pi.category_id, " /* カテゴリID */
				+ "pi.image_file_name, " /* 画像ファイル名 */
				+ "pi.image_file_path, " /* 画像ファイルパス */
				+ "pi.release_company, " /* 販売会社名 */
				+ "pi.release_date, " /* 販売年月日 */
				+ "phi.price, " /* 値段 */
				+ "phi.price * phi.product_count totalprice, " /* 合計金額 */
				+ "phi.regist_date, " /* 登録日 */
				+ "di.family_name, " /* 姓 */
				+ "di.first_name, " /* 名 */
				+ "di.user_address " /* 住所 */
				+ "FROM purchase_history_info phi " + "LEFT JOIN product_info pi "
				+ "ON phi.product_id = pi.product_id " + "LEFT JOIN destination_info di "
				+ "ON phi.destination_id = di.id " + "WHERE phi.user_id = ? " + "ORDER BY regist_date DESC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			/* DBから取得した商品購入情報を格納 */
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PurchaseHistoryInfoDTO purchaseHistoryInfoDTO = new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDTO.setId(rs.getInt("id"));
				purchaseHistoryInfoDTO.setUserId(rs.getString("user_id"));
				purchaseHistoryInfoDTO.setProductId(rs.getInt("product_id"));
				purchaseHistoryInfoDTO.setProductName(rs.getString("product_name"));
				purchaseHistoryInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				purchaseHistoryInfoDTO.setImageFileName(rs.getString("image_file_name"));
				purchaseHistoryInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				purchaseHistoryInfoDTO.setReleaseCompany(rs.getString("release_company"));
				purchaseHistoryInfoDTO.setReleaseDate(rs.getDate("release_date"));
				purchaseHistoryInfoDTO.setPrice(rs.getInt("price"));
				purchaseHistoryInfoDTO.setProductCount(rs.getInt("product_count"));
				purchaseHistoryInfoDTO.setTotalPrice(rs.getInt("totalprice"));
				purchaseHistoryInfoDTO.setFamilyName(rs.getString("family_name"));
				purchaseHistoryInfoDTO.setFirstName(rs.getString("first_name"));
				purchaseHistoryInfoDTO.setUserAddress(rs.getString("user_address"));
				purchaseHistoryInfoDTOList.add(purchaseHistoryInfoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return purchaseHistoryInfoDTOList;
	}

	public int regist(String userId, int productId, int productCount, int destinationId, int price) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "INSERT INTO purchase_history_info"
				+ "(user_id, product_id, product_count, price, destination_id, regist_date, update_date) "
				+ "VALUES(?, ?, ?, ?, ?, now(), now())";
		int count = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, price);
			ps.setInt(5, destinationId);
			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/* 商品購入履歴情報削除機能 */
	public int deleteAll(String userId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "DELETE FROM purchase_history_info WHERE user_id = ?";
		int count = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return count;
	}
}
