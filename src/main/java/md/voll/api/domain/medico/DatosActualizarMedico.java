package md.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import md.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}
