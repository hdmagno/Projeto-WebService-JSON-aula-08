package service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import entity.Pessoa;
import persistence.PessoaDao;

@Path("/pessoa")
public class ServicePessoa {

	@GET
	public String buscarPessoa() {
		try {
			return new Gson().toJson(new PessoaDao().buscarTodos());
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Error", "Dados não encontrados");
			return new Gson().toJson(map);
		}
	}

	@GET
	@Path(value = "/{id}")
	public String buscarPorCodigo(@PathParam("id") String id) {
		try {
			return new Gson().toJson(new PessoaDao().buscarPorId(new Integer(id)));
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Error", e.getMessage());
			return new Gson().toJson(map);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String gravar(String pessoa) {
		try {
			Pessoa resp = new Gson().fromJson(pessoa, Pessoa.class);
			new PessoaDao().inserir(resp);
			return new Gson().toJson(resp);
		}catch(Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Error", e.getMessage());
			return new Gson().toJson(map);
		}
	}
	
	@DELETE
	@Path(value="/{id}")
	public String excluir(@PathParam("id") String id) {
		try {
			return new Gson().toJson(new PessoaDao().excluir(new Integer(id)));
		}catch(Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Error", e.getMessage());
			return new Gson().toJson(map);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value="/{id}")
	public String alterar(@PathParam("id") String id, String pessoa) {
		try {
			Pessoa resp = new Gson().fromJson(pessoa, Pessoa.class);
			return new Gson().toJson(new PessoaDao().alterar(resp, new Integer(id)));
		} catch (Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Error", e.getMessage());
			return new Gson().toJson(map);
		}
	}
}
