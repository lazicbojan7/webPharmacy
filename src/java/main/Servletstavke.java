
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
@WebServlet(name = "servletstavke", urlPatterns = {"/servletstavke"})
public class Servletstavke extends HttpServlet {

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
            //UCITAVANJE SVIH PARAMETARA I NALAZANJE TIH OBJEKATA U BAZI:
            int kolicina = Integer.parseInt(request.getParameter("kolicina"));
            int kupac = Integer.parseInt(request.getParameter("kupac"));
            int racun = Integer.parseInt(request.getParameter("racun"));
            int apoteka = Integer.parseInt(request.getParameter("apoteka"));
            int stanje = Integer.parseInt(request.getParameter("stanje"));

            List<Kupac> kupci = DB.query("SELECT k FROM Kupac k WHERE k.kupId ='" + kupac + "'");
            List<Racun> racuni = DB.query("SELECT r FROM Racun r WHERE r.racId ='" + racun + "'");
            List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='" + apoteka + "'");
            List<Stanje> stanja = DB.query("SELECT s FROM Stanje s WHERE s.staId ='" + stanje + "'");

            Kupac k = kupci.get(0);
            Racun r = racuni.get(0);
            Apoteka a = apoteke.get(0);
            Stanje s = stanja.get(0);
            
            //AKO ZELI KOLICINU VISE NEGO STO IMA NA STANJU TO SE NE MOZE IZVRSITI!!!
            if(kolicina>s.getStaKolicina()){
                out.println("Kolicina mora biti manja od trenutnog stanja"
                        + " - stavka nije dodata. Trenutni iznos racuna je "+r.getRacIznos()+" Pokusajte ponovo!");
                return;
            }
            
            

            //PROMENE U BAZI:------------------------ 
            
          
           //nova stavka dobija cenu artikla u tom trenutku, tog dana, ako se cena artikla promeni kasnije
           //to nece uticati na stavke u racunu i na iznos racuna
           //na osnovu izabranog stanja odredjuje se koji je artikal i koja cena,
           //takodje poslata je i kolicina i racun koji je kreiran
            //konstruktor Stavka(Integer staKolicina, Double staCena, Artikal artId, Racun racId)
            Stavka stavka = new Stavka(kolicina,s.getArtId().getArtCena(),s.getArtId(),r);
            DB.insert(stavka);
            
            //racunanje novog iznosa racuna i snimanje
            double stariIznos = r.getRacIznos();
            double noviIznos = stariIznos+kolicina*s.getArtId().getArtCena();
            r.setRacIznos(noviIznos);
            DB.update(r);
            
            //skidanje sa stanja u odgovarajucoj apoteci
            int staraKolicina = s.getStaKolicina();
            int novaKolicina = staraKolicina-kolicina;
            s.setStaKolicina(novaKolicina);
            DB.update(s);




            //PORUKA KOJA SE VRACA U ALERT:
            out.println("Uspesno ste dodali "+kolicina+" komada artikla "+s.getArtId().getArtNaziv()
            + " na racun kupca "+k.getKupNaziv()+". Artikli su skinuti sa stanja u apoteci: "+a.getApoNaziv()
            +" trenutni iznos racuna je "+r.getRacIznos());
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
