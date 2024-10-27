/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
      conn = new conectaDAO().connectDB(); 
      String sql = "INSERT into produtos (nome, valor, status) values (?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());  
            stmt.execute();
        }catch(Exception e){
             System.out.println("Erro ao inserir produto/leião: " + e.getMessage());
        }      
    }
    
    
    public List<ProdutosDTO> listarProdutos(){
         String sql = "SELECT * FROM produtos";
         conn = new conectaDAO().connectDB();         
        try{  
            PreparedStatement stmt = conn.prepareStatement(sql);     
            ResultSet rs = stmt.executeQuery();        
           List <ProdutosDTO> listaProdutos = new ArrayList<>();          
           
             while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();            
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));                       
                listaProdutos.add(produto); 
                }
             return listaProdutos;
             
             
           }catch(Exception e){
            return null;
            }
                
        }
}
     