import java.sql.*;
import java.util.Scanner;

public class Main {

    public static Connection con;
    public static Statement stm;

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/db_java";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver"); // Menggunakan driver yang benar
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            System.out.println("===== CRUD SEDERHANA JAVA =====");

            Scanner scanner = new Scanner(System.in);

            // Menampilkan menu
            System.out.println("1. Read");
            System.out.println("2. Insert");
            System.out.println("3. Update");
            System.out.println("4. Delete \n");

            System.out.print("Pilih aksi: ");
            int pilihan = scanner.nextInt();
            System.out.println("-------------------");

            switch (pilihan) {
                case 1:
                	
                	 // Query SELECT
                	String selectQuery = "SELECT * FROM tbl_pasien"; 
                	PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
                	ResultSet resultSet = preparedStatement.executeQuery();

                	// Mengambil data dari ResultSet
                	while (resultSet.next()) {
                	    int id = resultSet.getInt("id"); 
                	    String nama = resultSet.getString("nama"); 
                	    int umur = resultSet.getInt("umur"); 
                	    String alamat = resultSet.getString("alamat"); 
                	    String penyakit = resultSet.getString("penyakit"); 

                	    System.out.println("ID: " + id);
                	    System.out.println("Nama: " + nama);
                	    System.out.println("Umur: " + umur);
                	    System.out.println("Alamat: " + alamat);
                	    System.out.println("Penyakit: " + penyakit);
                	    System.out.println("-------------------");
                	}

                    break;
                    
                case 2:
                	
                    // Insert data
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.next();
                    
                    System.out.print("Masukkan umur: ");                   
                    int umur = scanner.nextInt();
                    
                    System.out.print("Masukkan alamat: ");                
                    String alamat = scanner.next();
                    
                    System.out.print("Masukkan penyakit: ");                
                    String penyakit = scanner.next();

                    String insertQuery = "INSERT INTO tbl_pasien (nama, umur, alamat, penyakit) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                    insertStatement.setString(1, nama);
                    insertStatement.setInt(2, umur);
                    insertStatement.setString(3, alamat);
                    insertStatement.setString(4, penyakit);

                    int rowsInserted = insertStatement.executeUpdate();
                    
                    if (rowsInserted > 0) {
                        System.out.println("Data berhasil di insert.");
                        
                    }else {
                    	
                    	System.out.println("Data gagal di insert.");
                    }
                    
                    break;
                    
                case 3:
                	
                    // Update data
                    System.out.print("Masukkan ID yang akan di update: ");
                    int idToUpdate = scanner.nextInt();
                    
                    System.out.print("Masukkan nama baru: ");
                    String newNama = scanner.next();
                    
                    System.out.print("Masukkan umur baru: ");
                    int newUmur = scanner.nextInt();
                    
                    System.out.print("Masukkan alamat baru: ");
                    String newAlamat = scanner.next();
                    
                    System.out.print("Masukkan penyakit baru: ");
                    String newPenyakit = scanner.next();

                    String updateQuery = "UPDATE tbl_pasien SET nama = ?, umur = ?, alamat = ?, penyakit = ? WHERE id = ?"; 
                    PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                    updateStatement.setString(1, newNama);
                    updateStatement.setInt(2, newUmur);
                    updateStatement.setString(3, newAlamat);
                    updateStatement.setString(4, newPenyakit);
                    updateStatement.setInt(5, idToUpdate);

                    int rowsUpdated = updateStatement.executeUpdate();
                    
                    if (rowsUpdated > 0) {
                    	
                        System.out.println("Data berhasil di update.");
                        
                    }else {
                    	
                    	System.out.println("Data gagal di update.");
                    	
                    }
                    
                    break;
                    
                case 4:
                	
                    // Delete data berdasarkan ID
                    System.out.print("Masukkan ID yang akan dihapus: ");
                    int idToDelete = scanner.nextInt();

                    String deleteQuery = "DELETE FROM tbl_pasien WHERE id = ?"; 
                    PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, idToDelete);

                    int rowsDeleted = deleteStatement.executeUpdate();
                    
                    if (rowsDeleted > 0) {
                    	
                        System.out.println("Data berhasil di hapus.");
                        
                    }else {
                    	
                    	System.out.println("Data gagal di hapus.");
                    }
                    
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            // Tutup koneksi
            stm.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Terjadi error : " + e.getMessage());
        }
    }
}
