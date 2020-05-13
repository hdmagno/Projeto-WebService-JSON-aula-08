package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Pessoa;

public class PessoaDao extends Dao {
	
	public void inserir(Pessoa p) throws Exception {
		conectar();
		String sql = "insert into pessoa values(null, ?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, p.getNome());
		stmt.execute();
		desconectar();
	}

	public List<Pessoa> buscarTodos() throws Exception {
		conectar();
		String sql = "select * from pessoa";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		List<Pessoa> pessoas = new ArrayList<>();
		while(rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoas.add(pessoa);
		}
		desconectar();
		return pessoas;		
	}
	
	public Pessoa buscarPorId(Integer id) throws Exception {
		conectar();
		String sql = "select * from pessoa where id = ?";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		Pessoa pessoa = null;
		if(rs.next()) {
			pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
		}
		desconectar();
		if(pessoa == null) {
			System.out.println("Id não encontrado...");
		}
		return pessoa;
	}
	
	public Boolean excluir(Integer id) throws Exception {
		conectar();
		String sql = "delete from pessoa where id = ?";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		desconectar();
		return true;
	}
	
	public Boolean alterar(Pessoa p, Integer id) throws Exception {
		conectar();
		String sql = "update pessoa set nome = ? where id = ?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, p.getNome());
		stmt.setInt(2, id);
		stmt.execute();
		desconectar();
		return true;
	}
}
