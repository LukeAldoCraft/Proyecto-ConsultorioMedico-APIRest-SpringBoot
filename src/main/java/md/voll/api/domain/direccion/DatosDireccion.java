package md.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;



public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento) {


        public DatosDireccion(String calle, String distrito, String ciudad, String numero, String complemento) {

                this.calle = calle;
                this.distrito = distrito;
                this.ciudad = ciudad;
                this.numero = numero;
                this.complemento = complemento;
        }


}
