package org.example.gestionjoueur.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gestionjoueur.repository.JoueurRepository;
import org.example.gestionjoueur.security.entities.Joueur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class JoueurController {

    private JoueurRepository joueurRepository;

    @GetMapping("/user/index")
    public String index(Model model , @RequestParam(name = "page" , defaultValue = "0")int p ,
                        @RequestParam(name = "size" ,defaultValue = "4") int s ,
                        @RequestParam(name = "keyword" ,defaultValue = "") String keyword){
        Page<Joueur> pageJoueurs=joueurRepository.findByNomContains(keyword, PageRequest.of(p,s));

        model.addAttribute("listJoueurs",pageJoueurs.getContent());
        model.addAttribute("pages",new int [pageJoueurs.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",keyword);
        return "Joueurs";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(Long id , String keyword , int page){
        joueurRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/user/")
    public String home(){
        return "index";
    }

    @GetMapping("/admin/formJoueurs")
    @PreAuthorize("hasRole('ADMIN')")
    public String formJoueur(Model model) {
        model.addAttribute("joueur", new Joueur()); // Ajoute un patient vide pour le binding du formulaire
        return "FormJoueurs";
    }


    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(Model model , @Valid Joueur joueur , BindingResult bindingResult, @RequestParam(defaultValue = "") String keyword , @RequestParam(defaultValue = "0") int page){
        if (bindingResult.hasErrors()) return "FormJoueurs";
        joueurRepository.save(joueur);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editJoueur")
    @PreAuthorize("hasRole('ADMIN')")
    public String editJoueur(Model model, Long id , String keyword , int page) {
        Joueur joueur=joueurRepository.findById(id).get();
        if (joueur==null) throw  new RuntimeException("joueur introuvable");
        model.addAttribute("joueur", joueur);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "EditJoueur";
    }
}
