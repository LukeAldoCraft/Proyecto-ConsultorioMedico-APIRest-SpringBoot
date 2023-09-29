package md.voll.api.controller;


import jakarta.validation.Valid;
import md.voll.api.domain.ususarios.DatosAutenticacionUsuario;
import md.voll.api.domain.ususarios.Usuario;
import md.voll.api.infra.security.DatosJWTtoken;
import md.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticacionUsurio(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication autenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(autenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));

    }

}
