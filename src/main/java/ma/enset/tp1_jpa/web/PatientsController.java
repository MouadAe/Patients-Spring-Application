package ma.enset.tp1_jpa.web;

import ma.enset.tp1_jpa.entities.Patient;
import ma.enset.tp1_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PatientsController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/patients")
    public String list(Model model,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size",defaultValue = "5") int size,
                       @RequestParam(name = "keyword",defaultValue = "") String mc)
    {
        Page<Patient> pagePatients = patientRepository.findByNomContains(mc,PageRequest.of(page,size));
        model.addAttribute("patients",pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",mc);
        return "patients";
    }
    @GetMapping(path = "/deletePatient")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path = "/formPatient")
    public String formPatient(Model model){
        model.addAttribute("pageOption","addNewPatient");
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping(path = "/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/patients";
    }
    @GetMapping(path = "/editPatient")
    public String editPatient(Model model,Long id){
        model.addAttribute("patient",patientRepository.findById(id).get());
        model.addAttribute("pageOption","editPatient");
        return "formPatient";
    }

}
//    @GetMapping("/patients")
//    public List<Patient> getPatients(){
//        return patientRepository.findAll();
//    }
//    @GetMapping("/patients/{id}")
//    public Patient getPatientById(@PathVariable Long id){
//        return patientRepository.findById(id).get();
//    }
