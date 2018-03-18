/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raul
 */
@WebServlet(urlPatterns = {"","/adicionar","/apagar","/editar"})
public class Cadastro extends HttpServlet {
    private ArrayList agenda = new ArrayList<Contato>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String rota = request.getServletPath();
        
        switch(rota) {
            case "/adicionar":
                String adicionarHTML = "<html>\n" +
"    <head>\n" +
"        <title>TODO supply a title</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h1>Cadastro</h1>\n" +
"        <hr>\n" +
"        <form action=\"CadastroServlet\">\n" +
"            Nome:<input type=\"text\" name=\"nome\"/><br>\n" +
"            Email: <input type=\"text\" name=\"email\"/><br>\n" +
"            Telefone: <input type=\"text\" name=\"telefone\"/><br>\n" +
"            Rua: <input type=\"text\" name=\"rua\"/><br>\n" +
"            Bairro: <input type=\"text\" name=\"bairro\"/><br>\n" +
"            Cidade: <input type=\"text\" name=\"cidade\"/><br>\n" +
"            Estado: <input type=\"text\" name=\"estado\"/><br>\n" +
"            <input type=\"submit\" value=\"Enviar\">\n" +
"        </form>\n" +
"        <a href=\"index.html\">Voltar</a>\n" +
"    </body>\n" +
"</html>";
                break;
            case "/remover":
                this.remover(request, response);
                break;
            case "/editar":
                String editarHTML = "<html>\n" +
"    <head>\n" +
"        <title>TODO supply a title</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h1>Cadastro</h1>\n" +
"        <hr>\n" +
"        <form action=\"Digite o nome do contato\">\n" +
"            Nome:<input type=\"text\" name=\"nomeContato\"/><br>\n" +
"            <input type=\"submit\" value=\"Enviar\">\n" +
"        </form>\n" +
"        <a href=\"index.html\">Voltar</a>\n" +
"    </body>\n" +
"</html>";
                this.editar(request, response);
                break;
            default: 
                request.setAttribute("contatos", this.agenda);
                request.getRequestDispatcher("index.html").forward(request,response);
        }
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String rota = request.getServletPath();
        
        switch(rota) {
            case "/adicionar":
                this.adicionar(request,response);
                break;
            case "/editar":
               this.editar(request,response);
               break;
        }
    }
    
    public void adicionar(HttpServletRequest request, HttpServletResponse response ) 
             throws ServletException, IOException {
         
         String nome = request.getParameter("nome");
         String email = request.getParameter("email");
         String telefone = request.getParameter("telefone");
         String rua = request.getParameter("rua");
         String bairro = request.getParameter("bairro");
         String cidade = request.getParameter("cidade");
         String estado = request.getParameter("estado");
         
         Contato contato = new Contato();
         
         contato.setNome(nome);
         contato.setEmail(email);
         contato.setTelefone(telefone);
         contato.setRua(rua);
         contato.setBairro(bairro);
         contato.setCidade(cidade);
         contato.setEstado(estado);
         
         this.agenda.add(contato);  
         
         response.sendRedirect("/index.html");
         
     }
    
    public void editar(HttpServletRequest request, HttpServletResponse response ) throws IOException {
         
         String nomeContato = request.getParameter("nomeContato");
         String nome = request.getParameter("nome");
         
        for(int i = 0; i < agenda.size(); i++){
            if(nomeContato == nome){
                String email = request.getParameter("email");
                String telefone = request.getParameter("telefone");
                String rua = request.getParameter("rua");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String estado = request.getParameter("estado");
                Contato contato = (Contato) this.agenda.get(i);
                contato.setNome(nome);
                contato.setEmail(email);
                contato.setTelefone(telefone);
                contato.setRua(rua);
                contato.setBairro(bairro);
                contato.setCidade(cidade);
                contato.setEstado(estado);   
         }
        } 
         response.sendRedirect("/index.html");
     }
    
    public void remover(HttpServletRequest request, HttpServletResponse response ) 
             throws IOException {
         String nomeContato = request.getParameter("nomeContato");
         String nome = request.getParameter("nome");
         
         for(int i = 0; i < agenda.size(); i++){
            if(nomeContato == nome){
                this.agenda.remove(i);
            }
         }
    response.sendRedirect("/index.html");
    
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
