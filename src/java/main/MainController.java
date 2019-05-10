package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
    //u web xml u je podeseno:
    //stavljen je url patern /app/* znaci samo tada se pali dispecar
    //i za pocetnu stranu stavljeno je app/
    //redirect ce poslati na app/index
    
      @RequestMapping("/pregled")
    public String pregled(ModelMap model) {
            List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
model.addAttribute("apoteke",apoteke);
        return "pregled";
    }
      @RequestMapping("/strana")
    public String strana(ModelMap model,
             @RequestParam (required = false) String apoteka
            ) {
           
  boolean izbor = apoteka!=null;
          List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
          model.addAttribute("apoteke",apoteke);
          model.addAttribute("izbor",izbor);
           if(apoteka!=null){
          
            List<Apoteka> trazene = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='"+apoteka+"'");
            Apoteka trazena = trazene.get(0);
             List<Zaposleni> zaposleni = trazena.getZaposleniList();
             model.addAttribute("zaposleni", zaposleni);
            model.addAttribute("trazena", trazena);
           
       }
          
        return "strana";
    }

    @RequestMapping(value = {"/", "/start"})
    public String redirect() {
        return "redirect";
    }

     @RequestMapping("/servletstavke")
    public String servletstavke() {
         

        return "forward:/servletstavke";
    }
      @RequestMapping("/servletok")
    public String servletok() {
         

        return "forward:/servletok";
    }
    

    @RequestMapping("/index")
    public String index(ModelMap model, HttpSession session) {
         boolean ulogovan = session.getAttribute("zaposleni")!=null;
      
        model.addAttribute("ulogovan",ulogovan);

        return "index";
    }

    @RequestMapping("/apoteke")
    public String apoteke(ModelMap model, HttpSession session) {
         List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
         List<Grad> gradovi = DB.query("SELECT g FROM Grad g");
          boolean ulogovan = session.getAttribute("zaposleni")!=null;
      
        model.addAttribute("ulogovan",ulogovan);
      model.addAttribute("gradovi", gradovi);
       model.addAttribute("apoteke", apoteke);

        return "apoteke";
    }

    @RequestMapping("/artikli")
    public String artikli(ModelMap model, HttpSession session) {
         List<Artikal> artikli = DB.query("SELECT a FROM Artikal a");
         List<Proizvodjac> proizvodjaci = DB.query("SELECT p FROM Proizvodjac p");
         List<Dobavljac> dobavljaci = DB.query("SELECT d FROM Dobavljac d");
         
       boolean ulogovan = session.getAttribute("zaposleni")!=null;
        model.addAttribute("ulogovan",ulogovan);
        
       model.addAttribute("artikli", artikli);
       model.addAttribute("proizvodjaci",proizvodjaci);
       model.addAttribute("dobavljaci", dobavljaci);
        return "artikli";
    }
     @RequestMapping("/dobavljaci")
    public String dobavljaci(ModelMap model, HttpSession session) {
         List<Dobavljac> dobavljaci = DB.query("SELECT d FROM Dobavljac d");
           List<Grad> gradovi = DB.query("SELECT g FROM Grad g");
          boolean ulogovan = session.getAttribute("zaposleni")!=null;
      
        model.addAttribute("ulogovan",ulogovan);
      model.addAttribute("gradovi", gradovi);
      
       model.addAttribute("dobavljaci", dobavljaci);

        return "dobavljaci";
    }
     @RequestMapping("/proizvodjaci")
    public String proizvodjaci(ModelMap model, HttpSession session) {
         List<Proizvodjac> proizvodjaci = DB.query("SELECT p FROM Proizvodjac p");
         
         List<Grad> gradovi = DB.query("SELECT g FROM Grad g");
          boolean ulogovan = session.getAttribute("zaposleni")!=null;
      
        model.addAttribute("ulogovan",ulogovan);
      model.addAttribute("gradovi", gradovi);
      
       model.addAttribute("proizvodjaci",proizvodjaci);

        return "proizvodjaci";
    }
     @RequestMapping("/kupci")
    public String kupci(ModelMap model, HttpSession session) {
         List<Kupac> kupci = DB.query("SELECT k FROM Kupac k");
         
           List<Grad> gradovi = DB.query("SELECT g FROM Grad g");
          boolean ulogovan = session.getAttribute("zaposleni")!=null;
      
        model.addAttribute("ulogovan",ulogovan);
      model.addAttribute("gradovi", gradovi);
      
       model.addAttribute("kupci",kupci);

        return "kupci";
    }
     @RequestMapping("/zaposleni")
    public String zaposleni(ModelMap model, HttpSession session) {
        //moram da nazovem zaposleni1 jer u sesiji vec imam zaposleni
        List<Zaposleni> zaposleni1 = DB.query("SELECT z FROM Zaposleni z");
       List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
         List<Grad> gradovi = DB.query("SELECT g FROM Grad g");
          boolean ulogovan = session.getAttribute("zaposleni")!=null;
          Zaposleni korisnik = (Zaposleni) session.getAttribute("zaposleni");
          
      // AKO NIJE LOPGOVAN ILI NIJE LOGOVAN KAO ADMINISTRATOR GRESKA!    
      if(session.getAttribute("zaposleni")==null||!"admin".equals(korisnik.getZapTip())){
String poruka = "Za ovu operaciju morate biti ulogovani kao administrator.";
model.addAttribute("poruka", poruka);
          return "greska";
      }
        model.addAttribute("ulogovan",ulogovan);
      model.addAttribute("gradovi", gradovi);
       model.addAttribute("apoteke", apoteke);
        
       model.addAttribute("zaposleni1",zaposleni1);
 
        return "zaposleni";
    }
     @RequestMapping("/kreirajracun")
    public String kreirajracun(HttpSession session, ModelMap model) {
        if(session.getAttribute("zaposleni")==null)
            return "login";
        
        List<Kupac> kupci = DB.query("SELECT k FROM Kupac k");
        List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
Calendar cal = Calendar.getInstance();
cal.add(Calendar.DATE, 0);
SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//System.out.println(cal.getTime());
// Output "Wed Sep 26 14:23:28 EST 2012"

String datum = format1.format(cal.getTime());
//System.out.println(formatted);
// Output "2012-09-26"
        model.addAttribute("datum", datum);
        model.addAttribute("kupci", kupci);
        model.addAttribute("apoteke", apoteke);
        

        return "kreirajracun";
    }
    
      @RequestMapping("/kreirajstavke")
    public String kreirajstavke(ModelMap model,HttpSession session,
            @RequestParam (required = false) String kupac,
             @RequestParam (required = false) String apoteka
    ) {
         List<Apoteka> trazena = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='"+apoteka+"'");
              Apoteka a = trazena.get(0);
             
            List<Kupac> trazeni = DB.query("SELECT k FROM Kupac k WHERE k.kupId ='"+kupac+"'");
            Kupac k = trazeni.get(0);
            
            Date d = new Date();
            //kreira se nov racun sa danasnjim datumom,pocetnom cenom 0.0 i izabranim kupcem
             Racun r = new Racun(d,0.0,k);
             DB.insert(r);
             //zelim i da posaljem stanja za izabranu apoteku kako ne bi bilo prodato sta nema na stanju
             List<Stanje> stanja = a.getStanjeList();
             
             model.addAttribute("k",k);
             model.addAttribute("a",a);
             model.addAttribute("r",r);
             model.addAttribute("stanja",stanja);

        return "kreirajstavke";
    }
    
    
    
      @RequestMapping("/proveristanje")
    public String proveristanje(ModelMap model,HttpSession session,
             @RequestParam (required = false) String apoteka
            ) {
        //ov stranica je samo za ulogovane
         boolean ulogovan = session.getAttribute("zaposleni")!=null;
         if(!ulogovan) return "login";
         
         
        boolean izbor = apoteka!=null;
          List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
          model.addAttribute("apoteke",apoteke);
          model.addAttribute("izbor",izbor);
          /*ako je izabrao apoteku onda cu izvuci listu stanja svih artikala
         za bast tu apoteku i poslacu tu listu kako bi je prikazao na stranici
          u okviru tabele
         */
       if(apoteka!=null){
          
            List<Apoteka> trazene = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='"+apoteka+"'");
            Apoteka trazena = trazene.get(0);
             List<Stanje> stanja = trazena.getStanjeList();
             model.addAttribute("stanja", stanja);
            model.addAttribute("trazena", trazena);
           
       }

        return "proveristanje";
    }
     @RequestMapping("/izmenistanje")
    public String izmenistanje(ModelMap model,
            @RequestParam (required = false) String apoX,
             @RequestParam (required = false) String artX,
              @RequestParam (required = false) String staX
    ){ 
        List<Apoteka> trazene = DB.query("SELECT a FROM Apoteka a WHERE a.apoId ='"+apoX+"'");
            Apoteka apoteka = trazene.get(0);
        List<Artikal> trazeni = DB.query("SELECT a FROM Artikal a WHERE a.artId ='"+artX+"'");
        Artikal artikal = trazeni.get(0);
       List<Stanje> trazeno = DB.query("SELECT s FROM Stanje s WHERE s.staId ='"+staX+"'");
         Stanje stanje = trazeno.get(0);
       model.addAttribute("apoteka", apoteka);
        model.addAttribute("artikal", artikal);
        model.addAttribute("stanje", stanje);

        return "izmenistanje";
    }
      @RequestMapping("/potvrdastanje")
    public String potvrdastanje(ModelMap model,
            @RequestParam (required = false) String apoteka,
            @RequestParam (required = false) String artikal,
            @RequestParam (required = false) String stanje,
            @RequestParam (required = false) String novoStanje
        
            ) {
 List<Stanje> trazeno = DB.query("SELECT s FROM Stanje s WHERE s.staId ='"+stanje+"'");
 Stanje s = trazeno.get(0);
s.setStaKolicina(Integer.parseInt(novoStanje));
DB.update(s);

String poruka = "Uspesno ste promenili stanje!";
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
      @RequestMapping("/arhiva")
    public String arhiva(HttpSession session, ModelMap model,
             @RequestParam (required = false) String racun) {
          if(session.getAttribute("zaposleni")==null)
            return "login";
           
           //STAVKE CU POSLATI SAMO AKO JE IZABRAN RACUN
           //I SAMO AKO JE IZABRAN TAMO CE SE PRIKAZIVATI U VIDU
           //UREDJENE LISTE
           boolean izbor = racun!=null;
           model.addAttribute("izbor", izbor);
           if(izbor){
               List<Racun> izabrani = DB.query("SELECT r FROM Racun r WHERE r.racId ='"+racun+"'");
               Racun izabran = izabrani.get(0);
               List<Stavka> stavke = izabran.getStavkaList();
               Kupac kk = izabran.getKupId();
               model.addAttribute("stavke", stavke);
               model.addAttribute("izabran", izabran);
               model.addAttribute("kk", kk);
               
               
           }
           
           List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
           List<Racun> racuni = DB.query("SELECT r FROM Racun r");
           model.addAttribute("apoteke", apoteke);
           model.addAttribute("racuni", racuni);

        return "arhiva";
    }
    
    
     @RequestMapping("/izmeniartikal")
    public String izmeniartikal(
    ModelMap model,
            @RequestParam (required = false) String id
    ) {
        List<Artikal> trazeni = DB.query("SELECT a FROM Artikal a WHERE a.artId ='"+id+"'");
        Artikal artikal = trazeni.get(0);
        model.addAttribute("artikal", artikal);

        return "izmeniartikal";
    }
     @RequestMapping("/potvrdaizmeniartikal")
    public String izmeniartikal(
    ModelMap model,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String tip,
            @RequestParam (required = false) String opis,
            @RequestParam (required = false) String cena,
            @RequestParam (required = false) String id
    ) {
        List<Artikal> trazeni = DB.query("SELECT a FROM Artikal a WHERE a.artId ='"+id+"'");
        Artikal artikal = trazeni.get(0);
        artikal.setArtNaziv(naziv);
        artikal.setArtOpis(opis);
        artikal.setArtTip(tip);
        artikal.setArtCena(Double.parseDouble(cena));
        DB.update(artikal);
        
String poruka = "Uspesno ste sacuvali izmene!";
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
      @RequestMapping("/obrisiartikal")
    public String obrisiartikal(
    ModelMap model,
            @RequestParam (required = false) String id
    ) {
        List<Artikal> trazeni = DB.query("SELECT a FROM Artikal a WHERE a.artId ='"+id+"'");
        Artikal artikal = trazeni.get(0);
        //prvo moram obrisatisva stanja tog artikla jer stanje prima kljuc od artikla
        List<Stanje> stanja = artikal.getStanjeList();
        for(int i = 0;i<stanja.size();i++){
            Stanje s = stanja.get(i);
            DB.delete(s);
        }
         String poruka = "Uspesno ste obrisali artikal!"+artikal.getArtNaziv();
       DB.delete(artikal);
       
        model.addAttribute("poruka", poruka);
        return "potvrda";
    }
    

   
     @RequestMapping("/potvrdaapoteka")
    public String potvrdaapoteka(ModelMap model,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String telefon,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String adresa,
            @RequestParam (required = false) String grad,
             @RequestParam (required = false) String query
            ) {
      
 List<Grad> gradovi = DB.query("SELECT g FROM Grad g WHERE g.graId='"+grad+"'");
 Grad g = gradovi.get(0);
 Apoteka a = new Apoteka(naziv,telefon,email,adresa,g);
 DB.insert(a);
/*EntityManager em = DB.emf.createEntityManager();
em.createNativeQuery('"'+query+'"').executeUpdate();*/
//"INSERT INTO testtable ('column1','column2') VALUES ('test1','test2')"
String poruka = "Uspesno ste dodali apoteku: "+naziv;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
    
     @RequestMapping("/potvrdaartikal")
    public String potvrdaartikal(ModelMap model,
            @RequestParam (required = false) String proizvodjac,
            @RequestParam (required = false) String dobavljac,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String tip,
            @RequestParam (required = false) String cena,
             @RequestParam (required = false) String opis,
             @RequestParam (required = false) String query
            ) {
      
 List<Proizvodjac> proizvodjaci = DB.query("SELECT p FROM Proizvodjac p WHERE p.proId='"+proizvodjac+"'");
 Proizvodjac p = proizvodjaci.get(0);
  List<Dobavljac> dobavljaci = DB.query("SELECT d FROM Dobavljac d WHERE d.dobId='"+dobavljac+"'");
 Dobavljac d = dobavljaci.get(0);
 
Double c = Double.parseDouble(cena);
 
 Artikal a = new Artikal(naziv, tip, opis, c, d, p);
 DB.insert(a);
 

 

 //novi artikal je odat ali nema stanja ni za jednu apoteku
  //lista stanja ima onoliko elemenata koliko ima apoteka i za svaki treba da bude 0 na pocetku
  
  //POCETAK
  List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a");
  for(int i =0;i<apoteke.size();i++){
      Stanje s = new Stanje();
      s.setApoId(apoteke.get(i));
      s.setArtId(a);
      s.setStaKolicina(0);

      DB.insert(s);
   
  }
//KRAJ
//---------------------------------------------------------------
 
String poruka = "Uspesno ste dodali artikal: "+naziv;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
         @RequestMapping("/potvrdadobavljac")
    public String potvrdadobavljac(ModelMap model,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String telefon,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String grad
             
            ) {
      
 List<Grad> gradovi = DB.query("SELECT g FROM Grad g WHERE g.graId='"+grad+"'");
 Grad g = gradovi.get(0);
 Dobavljac d = new Dobavljac(naziv,telefon,email,g);
 DB.insert(d);
String poruka = "Uspesno ste dodali dobavljaca: "+naziv;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
    
        @RequestMapping("/potvrdaproizvodjac")
    public String potvrdaproizvodjac(ModelMap model,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String telefon,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String grad
             
            ) {
      
 List<Grad> gradovi = DB.query("SELECT g FROM Grad g WHERE g.graId='"+grad+"'");
 Grad g = gradovi.get(0);
 Proizvodjac p = new Proizvodjac(naziv,telefon,email,g);
 DB.insert(p);
String poruka = "Uspesno ste dodali proizvodjaca: "+naziv;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
    
       @RequestMapping("/potvrdakupac")
    public String potvrdakupac(ModelMap model,
            @RequestParam (required = false) String naziv,
            @RequestParam (required = false) String telefon,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String grad
             
            ) {
      
 List<Grad> gradovi = DB.query("SELECT g FROM Grad g WHERE g.graId='"+grad+"'");
 Grad g = gradovi.get(0);
 Kupac k = new Kupac(naziv,telefon,email,g);
 DB.insert(k);
String poruka = "Uspesno ste dodali kupca: "+naziv;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }

       @RequestMapping("/potvrdazaposleni")
    public String potvrdazaposleni(ModelMap model,
            @RequestParam (required = false) String ime,
            @RequestParam (required = false) String prezime,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String lozinka,
            @RequestParam (required = false) String grad,
            @RequestParam (required = false) String apoteka,
            @RequestParam (required = false) String tip
             
            ) {
      
 List<Grad> gradovi = DB.query("SELECT g FROM Grad g WHERE g.graId='"+grad+"'");
 Grad g = gradovi.get(0);
 
 List<Apoteka> apoteke = DB.query("SELECT a FROM Apoteka a WHERE a.apoId='"+apoteka+"'");
 Apoteka a = apoteke.get(0);
 
 Zaposleni z = new Zaposleni(ime, prezime, email, lozinka,tip, a, g);
 DB.insert(z);
String poruka = "Uspesno ste dodali zaposlenog: "+ime+" "+prezime;
model.addAttribute("poruka", poruka);
        return "potvrda";
    }
    
      @RequestMapping("/izmenalozinke")
    public String izmenalozinke(ModelMap model,HttpSession session,
            @RequestParam (required = false) String trenutna,
            @RequestParam (required = false) String nova,
            @RequestParam (required = false) String nova1   
            ) {
 //trenutno logovan i njegova sifra:       
Zaposleni user = (Zaposleni) session.getAttribute("zaposleni");
String sifra = user.getZapLozinka();
//ako je dobro uneo trenutnu i ako je 2 puta dobro uneo novu:
if(trenutna.equals(sifra)&&nova.equals(nova1)){
    user.setZapLozinka(nova);
    DB.update(user);
    
  String poruka = "Uspesno izmenili vasu lozinku!";
  model.addAttribute("poruka", poruka);
        return "potvrda"; 
 //ako nije dobro uneo trenutnu i ako nije 2 puta dobro uneo novu:       
}else{
    String poruka = "Doslo je do greske,molimo pokusajte ponovo!";
model.addAttribute("poruka", poruka);
        return "greska";
}

    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //jedan nacin:
        //session.removeAttribute("korisnik");
        //drugi nacin:
        session.invalidate();
        return "redirect1";
        //mozda ovde da stavim login kad ga uredim
    }

 
    
      /*
    OVAJ LOGIN RADI 3 STVARI:
    1. AKO NEMA PARAMETARA STVARNO IDE NA JSP STRANU LOGIN
    2.AKO SU UNETI POGRESNI IDE NA GRESKU
    3.AKO SU UNETI TACNI PARAMETRI IDE NA INDEX
    */
     @RequestMapping("/login")
     //zahteva parametre email i pass i tacno se tako moraju zvati i u formi
     //onda cu ih ja dodati u model i saljem ih dalje na view(stranicu) koju vraca na return
    public String login(@RequestParam (required = false) String email,
            @RequestParam (required = false) String pass,
            ModelMap model,
            HttpSession session) {
        //1.
        if(email==null||pass==null)
            return "login";
        
        
        //da ne bi komplikovao sa navodnicima mogu ovaj query
      // List<Korisnik> res = DB.query("SELECT k FROM Korisnik k WHERE k.korEmail=?1",email);
      
      //a evo i standardnog(imam vise vrsta querija u DB)
      List<Zaposleni> res = DB.query("SELECT z FROM Zaposleni z WHERE z.zapEmail='"+email+"'");
    
      //2.
        if(res.size()==0||!pass.equals(res.get(0).getZapLozinka())){
            String poruka = "Niste uneli ispravne podatke!";
            model.addAttribute("poruka", poruka);
             return "greska";
        }
        //3.
        session.setAttribute("zaposleni", res.get(0));
        model.addAttribute("email", email);
        model.addAttribute("pass", pass);
        //redirect vraca na index, da bi se osvezila stranica
        return "redirect1";
    }
}
