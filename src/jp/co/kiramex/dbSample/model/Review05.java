package jp.co.kiramex.dbSample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Review05 {

    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost:3306/kadaidb"; // データベースURL
        String user = "root"; // MySQLのユーザー名
        String password = "xH9LC7czPgVU8"; // MySQLのパスワード

        // ユーザー入力を受け付ける
        Scanner scanner = new Scanner(System.in);
        System.out.print("検索キーワードを入力してください > ");
        String input = scanner.nextLine();

        try {
            // 入力値を数値に変換
            int id = Integer.parseInt(input);

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL文の準備（プレースホルダ ? を使用）
            String sql = "SELECT name, age FROM person WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id); // 入力されたidをセット

            // クエリの実行
            ResultSet rs = pstmt.executeQuery();

            // 結果の取得と表示
            if (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("age"));
            } else {
                System.out.println("該当するデータが見つかりませんでした。");
            }

            // リソースの解放
            rs.close();
            pstmt.close();
            conn.close();
        } catch (NumberFormatException e) {
            System.out.println("IDは数値で入力してください。");
        } catch (SQLException e) {
            System.out.println("データベース接続エラー: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }


}


