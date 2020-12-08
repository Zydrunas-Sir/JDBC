import java.sql.*;
import java.util.ArrayList;

public class AirportDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db";

    public static void create(Airport airport) {
        String query = "INSERT INTO airports(biz_name, address, city) VALUES(?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.executeUpdate();
            System.out.println("Pavyko iterpite nauja irasa");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("ivyko klaida kuriant nauja irasa. Placiau : " + e.getMessage());
        }
    }

    public static ArrayList<Airport> searchByName(String name) {
        String query = "Select * From airports WHERE biz_name = '" + name + "'";
        ArrayList<Airport> airports = new ArrayList<Airport>();
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("biz_id");
                String name2 = resultSet.getString("biz_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                airports.add(new Airport(id, name2, address, city));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieska pagal oro uosto varda");
        } catch (SQLException e) {
            System.out.println("ivyko klaida atliekant paieska. Placiau : " + e.getMessage());
        }
        return airports;
    }
    public static void updateById(Airport airport){
        String query = "UPDATE airports SET biz_name = ?, address = ?, city = ? WHERE biz_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setInt(4, airport.getId());
            preparedStatement.executeUpdate();
            System.out.println("Pavyko redaguoti esama irasa");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("ivyko klaida redaguojant nauja irasa. Placiau : " + e.getMessage());
        }
    }
    public static void deleteById(int id){
        String query = "DELETE FROM airports WHERE biz_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Pavyko istrinti esama irasa");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("ivyko klaida istrinat nauja irasa. Placiau : " + e.getMessage());
        }
    }


    public static Airport searchByID(int id) {
        String query = "Select * From airports WHERE biz_id = '" + id + "'";
        ArrayList<Airport> airports = new ArrayList<Airport>();
        Airport airport = null;
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id2 = resultSet.getInt("biz_id");
                String name2 = resultSet.getString("biz_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                airport = new Airport(id2, name2, address, city);
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieska pagal id");
        } catch (SQLException e) {
            System.out.println("ivyko klaida atliekant paieska pagal id. Placiau : " + e.getMessage());
        }
        return airport;
    }
}
