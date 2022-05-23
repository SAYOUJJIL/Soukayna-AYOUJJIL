package ma.enset.gestionetudiant.web;


import lombok.AllArgsConstructor;
import ma.enset.gestionetudiant.entities.Etudiant;
import ma.enset.gestionetudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @GetMapping(path="/index")
    public String patientes(Model model ,
                           @RequestParam(name ="page" ,defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue ="") String keyword)

    {

        Page<Etudiant> pageEtudiants= etudiantRepository.findByNomContaining(keyword, PageRequest.of(page,size));
        model.addAttribute("PageEtudiant",pageEtudiants.getContent());
        model.addAttribute("nombrePages",new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "etudiants";
    }

    @GetMapping("/delete")
    public String delete(long id,String keyword,int page){
        etudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/Admin/etudiantForm")
    public String etudiantForm(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "etudiantForm";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/Admin/editEtudiant")
    public String editEtudiant(Model model , Long id , String keyword, int page){
        Etudiant etudiant= etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) new RuntimeException("Eudiat nxicte pas");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editEtudiant";
    }

    @GetMapping("/")
    public String Home(){
        return "index";
    }
}
