package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;
import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresaForm;
import br.com.alura.gerenciador.acao.RemoveEmpresa;


//@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		/*
		//Verifica se o usuário está logado.
		HttpSession sessao = request.getSession();	
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm")) ;
		
		if(usuarioNaoEstaLogado && ehAcaoProtegida) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		} */
		
		//Procura a acao dentro do pacote onde estão as classes de acoes.
		
		String nomeDaClasse="br.com.alura.gerenciador.acao."+paramAcao;
		
		//Cria uma instancia de "Acao" e chama o metodo executa.
		String nome = null;
		try {
			Class classe = Class.forName(nomeDaClasse);
			Object obj = classe.newInstance();
			Acao acao = (Acao) obj;
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] tipoEEndereco = nome.split(":");
		
	
		if(tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);   
			rd.forward(request, response);			
		} else {
			response.sendRedirect(tipoEEndereco[1]);
		}
		
		//String nome = null;
		/* if(paramAcao.equals("ListaEmpresas")) {
			ListaEmpresas acao = new ListaEmpresas();
			nome = acao.executa(request, response);
			
		} else if(paramAcao.equals("RemoveEmpresa")) {	
			RemoveEmpresa acao = new RemoveEmpresa();
			nome = acao.executa(request, response);
		
		} else if(paramAcao.equals("MostraEmpresa")) {
			MostraEmpresa acao = new MostraEmpresa();
			nome = acao.executa(request, response);
			
		} else if(paramAcao.equals("AlteraEmpresa")) {
			AlteraEmpresa acao = new AlteraEmpresa();
			nome = acao.executa(request, response);
			
		} else if(paramAcao.equals("NovaEmpresa")) {
			NovaEmpresa acao = new NovaEmpresa();
			nome = acao.executa(request, response);
		} else if(paramAcao.equals("NovaEmpresaForm")) {
			NovaEmpresaForm acao = new NovaEmpresaForm();
			nome = acao.executa(request, response);
		}
		*/
		 
		
	}

}
