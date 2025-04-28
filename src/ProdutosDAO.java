
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try{
            conn = new conectaDAO().connectDB();
            
            
            String sql = "INSERT INTO produtos(nome,valor,status) VALUES (?, ?, ?)";
            prep = this.conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());  
            
            prep.execute();
            
            JOptionPane.showMessageDialog(null, " O Produto foi cadastrado com sucesso! ");
           
        }       
        
        catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto");
        }      
                
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){        
        
        try {
            
            String sql = "SELECT * FROM produtos";
        
            conn = new conectaDAO().connectDB();
            
                    prep = this.conn.prepareStatement(sql);                                   
                    
                    ResultSet rs = prep.executeQuery();        
                   
                    while (rs.next()) {
                        ProdutosDTO produtos = new ProdutosDTO();
                                                
                      produtos.setId(rs.getInt("id"));
                      produtos.setNome(rs.getString("nome"));
                      produtos.setValor(rs.getDouble("valor"));
                      produtos.setStatus(rs.getString("status"));                                       
                      
                        
                        listagem.add(produtos);
                    }
                    return listagem;
                                      
                }     
        catch (SQLException e) {
                    return null;
                }
        
       
    }
        
}

