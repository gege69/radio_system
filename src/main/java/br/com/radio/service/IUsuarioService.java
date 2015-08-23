package br.com.radio.service;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.UserDTO;
import br.com.radio.model.Usuario;

public interface IUsuarioService {

	Usuario registerNewUserAccount(UserDTO accountDto);

    Usuario findUserByEmail(String email);
    
    Usuario findUserByEmailOrLogin(String email, String login);

    Usuario getUserByID(long id);

    void changeUserPassword(String user, AlterarSenhaDTO alterarSenhaDTO );

    boolean checkIfValidOldPassword(Usuario user, String password);
	
}
