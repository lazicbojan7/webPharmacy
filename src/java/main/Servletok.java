/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bojan
 */
@WebServlet(name = "servletok", urlPatterns = {"/servletok"})
public class Servletok extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int racun =Integer.parseInt(request.getParameter("racun"));
            int kupac =Integer.parseInt(request.getParameter("kupac"));
            int apoteka =Integer.parseInt(request.getParameter("apoteka"));
              List<Kupac> kupci = DB.query("SELECT k FROM Kupac k WHERE k.kupId ='" + kupac + "'");
            List<Racun> racuni = DB.query("SELECT r FROM Racun r WHERE r.racId ='" + racun + "'");
            List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='" + apoteka + "'");
             Kupac k = kupci.get(0);
              Apoteka a = apoteke.get(0);
            Racun r = racuni.get(0);
             //STAVKE OVOG RACUNA SU:
            List<Stavka> stavke = r.getStavkaList();
            
            
               //TREBA PROCI KROZ FOR PETNJU DA BIH SVAKU KOLICINU IZ SVAKE STAVKE VRATIO NA STANJE,
            //OBRISAO SVAKU STAVKU I NA KRAJU DA SE OBRISE CEO TAJ RACUN posle petlje
            for(int i = 0;i<stavke.size();i++){
                Stavka stavka = stavke.get(i);
                int kolStavka = stavka.getStaKolicina();
                int artikal= stavka.getArtId().getArtId();
            //zelim naci stanje bas ovog artikla za bas ovu apoteku sto je poslata da bih ga povecao
           //PAZNJA s.apoId.apoId zato sto apoId je objekat Apoteka i nema metodu equals da bi njih poredio
            //isto vazi i za artikal id koji sam dobio preko stavke 
            List<Stanje> stanja = DB.query("SELECT s FROM Stanje s WHERE s.apoId.apoId ='" + apoteka + "' AND s.artId.artId='"+artikal+"'"); 
            Stanje stanje = stanja.get(0);
            //ovo stanje bas tog artikla u bas toj apoteci treba uvecati za kolicinu iz stavke "kol"
            int staraKolicina = stanje.getStaKolicina();
            int novaKolicina = staraKolicina + kolStavka;
            stanje.setStaKolicina(novaKolicina);
            //obavezno snimanje
            DB.update(stanje);
            //sada nam stavka vise nije potrebna, stanje smo povecali, stavku brisemo
            DB.delete(stavka);
            }
  //kada smo prosli kroz petlju i obrisali sve stavke, treba obrisati i racun
   DB.delete(r);
   out.println("Racun je obrisan a artikli su vraceni na stanje u apoteku "+ a.getApoNaziv());         

        }
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
