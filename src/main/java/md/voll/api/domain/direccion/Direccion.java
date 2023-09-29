package md.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;

    private String complemento;



    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
      }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        return this;
    }

    public String getCalle() {
        return calle;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}