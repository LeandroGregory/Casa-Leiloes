
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
        
        return listagem;
    }
    
    
    
        
}

