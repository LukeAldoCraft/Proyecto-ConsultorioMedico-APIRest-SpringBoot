package md.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import md.voll.api.domain.direccion.DatosDireccion;
import md.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
         Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
         DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                 medico.getNombre(),
                 medico.getEmail(),
                 medico.getTelefono(),
                 medico.getDocumento(),
                 medico.getEspecialidad(),
                 new DatosDireccion(medico.getDireccion().getCalle(),
                         medico.getDireccion().getDistrito(),
                         medico.getDireccion().getCiudad(),
                         medico.getDireccion().getNumero(),
                         medico.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

         return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10, sort = "documento") Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

   @PutMapping
   @Transactional
   public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
       Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
       medico.actualizarDatos(datosActualizarMedico);
       return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
               medico.getNombre(),
               medico.getEmail(),
               medico.getTelefono(),
               medico.getDocumento(),
               medico.getEspecialidad(),
               new DatosDireccion(medico.getDireccion().getCalle(),
                       medico.getDireccion().getDistrito(),
                       medico.getDireccion().getCiudad(),
                       medico.getDireccion().getNumero(),
                       medico.getDireccion().getComplemento())));
   }

//Metodo para hacer delete Logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

   // Metodo para borrar de la base de datos
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id) {
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> datosDetalladosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
    }
}
