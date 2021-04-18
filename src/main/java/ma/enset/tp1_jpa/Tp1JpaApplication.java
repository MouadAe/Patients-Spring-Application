package ma.enset.tp1_jpa;

import ma.enset.tp1_jpa.entities.Patient;
import ma.enset.tp1_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

@SpringBootApplication
public class Tp1JpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Tp1JpaApplication.class, args);
    }

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Mouad",new Date(),2300,false));
        patientRepository.save(new Patient(null,"Oussama",new Date(),3000,false));
        patientRepository.save(new Patient(null,"Ahmed",new Date(),2000,true));
        patientRepository.save(new Patient(null,"Mohamed",new Date(),6300,false));
        patientRepository.save(new Patient(null,"Khaled",new Date(),9000,false));
        patientRepository.save(new Patient(null,"Youssef",new Date(),3000,true));
        patientRepository.save(new Patient(null,"Aziz",new Date(),5300,true));
        patientRepository.save(new Patient(null,"Kamal",new Date(),1300,true));
        patientRepository.save(new Patient(null,"Ibrahim",new Date(),300,true));
        patientRepository.save(new Patient(null,"Marwane",new Date(),7700,true));
        patientRepository.save(new Patient(null,"Ismail",new Date(),2100,true));

//        System.out.println("*********************************************************************");
//            for (Patient p : patientRepository.findAll()) {
//                System.out.println(p);
//            }
//        System.out.println("*********************************************************************");
//            System.out.println(patientRepository.findById(2L).get().getNom());
//        System.out.println("*********************************************************************");
//            for(Patient p : patientRepository.findByNomContains("m",PageRequest.of(0,2))){
//                System.out.println(p);
//            }
//        System.out.println("*********************************************************************");
//            for(Patient p : patientRepository.findByMalade(true)){
//                System.out.println(p);
//            }
//        System.out.println("*********************************************************************");
////         patientRepository.deleteById(2L);
//        System.out.println("*********************************************************************");
////        Pagination pour le cas de beaucoup de donnees :
//        for (Patient p : patientRepository.findAll(PageRequest.of(1,2))) {
//            System.out.println(p);
//        }
    }
}
