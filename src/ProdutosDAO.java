
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


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
    
    public void venderProduto(int idProduto) {
    try {
        conn = new conectaDAO().connectDB();
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        prep = this.conn.prepareStatement(sql);
        
        prep.setString(1, "Vendido");
        prep.setInt(2, idProduto);       
        
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto: " + e.getMessage());
        }
    }
    
       
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            conn = new conectaDAO().connectDB();
            
            prep = this.conn.prepareStatement(sql);
            
            
            ResultSet rs = prep.executeQuery();

            ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getDouble("valor"));
                produtos.setStatus(rs.getString("status"));

                listaProdutos.add(produtos);
            }
            return listaProdutos;

        } catch (Exception e) {

            return null;
        }
    }
    
    public void produtoVendido(int id) {        

        try {
            
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = this.conn.prepareStatement(sql);

            prep.setInt(1, id);
            prep.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto: " + e.getMessage());
        }
    }

        
    
}

