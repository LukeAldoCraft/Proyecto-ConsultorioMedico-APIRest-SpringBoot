package md.voll.api.domain.medico;

import md.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, Especialidad especialidad, DatosDireccion direccion) {
}
