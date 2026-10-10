package Service;

import Repository.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public boolean register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }

        User usuarioSalvo = userRepository.save(new User(email, password));

        String accountNumber = String.valueOf(usuarioSalvo.getId());



        return true;
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email).filter(usuario -> usuario.getPassword().equals(password));
    }
}
