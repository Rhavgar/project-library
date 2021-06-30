package br.edu.iff.projectLibrary;

import br.edu.iff.projectLibrary.model.Alert;
import br.edu.iff.projectLibrary.model.Book;
import br.edu.iff.projectLibrary.model.Language;
import br.edu.iff.projectLibrary.model.LibMember;
import br.edu.iff.projectLibrary.model.Librarian;
import br.edu.iff.projectLibrary.repository.AlertRepository;
import br.edu.iff.projectLibrary.repository.BookRepository;
import br.edu.iff.projectLibrary.repository.LibMemberRepository;
import br.edu.iff.projectLibrary.repository.LibrarianRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectLibraryApplication implements CommandLineRunner
{
        @Autowired
        private LibrarianRepository librarianRepo;
        @Autowired
        private LibMemberRepository libMemberRepo;
        @Autowired
        private BookRepository bookRepo;
        @Autowired
        private AlertRepository alertRepo;
        
	public static void main(String[] args)
        {
		SpringApplication.run(ProjectLibraryApplication.class, args);
	}
        
        @Override
        public void run(String... args) throws Exception
        {
            //Librarian
            Librarian l1 = new Librarian();
            l1.setPersonName("Mathias");
            l1.setEmail("mathias@gmail.com");
            l1.setCpf("321.465.734-53");
            l1.setPassword("987654321");
            l1.setShift("Noturno");
            
            librarianRepo.save(l1);
            
            
            //LibMember
            LibMember lm1 = new LibMember();
            lm1.setPersonName("Sidney");
            lm1.setEmail("sidney@gmail.com");
            lm1.setCpf("153.577.257-85");
            lm1.setPassword("123456789");
            
            lm1.setAddress("Rua Alvarenga, 555");
            Calendar lm1bd = Calendar.getInstance();
            lm1bd.set(1993, 26, 10);
            lm1.setBirthday(lm1bd);
            
            libMemberRepo.save(lm1);
            
            //Book
            Book b1 = new Book();
            b1.setBookName("Golden Compass");
            b1.setBookAuthor("Philip Pullman");
            b1.setLang(Language.ENGLISH);
            b1.setBookAmount(5);
            
            bookRepo.save(b1);
            
            //Alert
            Alert a1 = new Alert();
            a1.setBook(b1);
            a1.setLibMember(lm1);
            a1.setIssueDate(Calendar.getInstance());
            
            Calendar returnDate = Calendar.getInstance();
            returnDate.set(2021, 7, 25, 23, 59, 30);
            a1.setReturnDate(returnDate);
            
            
            alertRepo.save(a1);
        }

}
