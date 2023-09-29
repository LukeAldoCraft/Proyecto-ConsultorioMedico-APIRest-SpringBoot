package md.voll.api.domain.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import md.voll.api.domain.direccion.Direccion;

@Table(name="medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {

        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo = false;
    }

   

    public String getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
