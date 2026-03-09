/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alfirkti.sigcilat.Ciudadano;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author WOPS
 */
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {

    @Query("select coalesce(max(c.claveConsecutiva), 0) from Ciudadano c")
    long maxClaveConsecutiva();

    boolean existsByCurp(String curp);

    List<Ciudadano> findAllByOrderByClaveConsecutivaDesc();

    List<Ciudadano> findByActivoTrueOrderByClaveConsecutivaDesc();

    // Para localizar un ciudadano activo por id (útil para evitar bajas dobles)
    Optional<Ciudadano> findByIdAndActivoTrue(Long id);

    List<Ciudadano> findByActivoFalseOrderByClaveConsecutivaDesc();

    // para editar: "existe alguien con esta CURP pero con id diferente"
    boolean existsByCurpAndIdNot(String curp, Long id);

    long countByActivoTrue();

    long countByActivoFalse();

    List<Ciudadano> findByActivoTrueOrderByIdDesc();

    List<Ciudadano> findByActivoFalseOrderByIdDesc();

    @Query("""
        select c
        from Ciudadano c
        where c.activo = true and (
              upper(c.curp) like upper(concat('%', :q, '%'))
           or upper(c.nombre) like upper(concat('%', :q, '%'))
           or upper(c.apellidoPaterno) like upper(concat('%', :q, '%'))
           or upper(c.apellidoMaterno) like upper(concat('%', :q, '%'))
           or upper(concat(c.nombre,' ',c.apellidoPaterno,' ',c.apellidoMaterno)) like upper(concat('%', :q, '%'))
        )
        order by c.id desc
    """)
    List<Ciudadano> buscarActivos(@Param("q") String q);

    @Query("""
        select c
        from Ciudadano c
        where c.activo = false and (
              upper(c.curp) like upper(concat('%', :q, '%'))
           or upper(c.nombre) like upper(concat('%', :q, '%'))
           or upper(c.apellidoPaterno) like upper(concat('%', :q, '%'))
           or upper(c.apellidoMaterno) like upper(concat('%', :q, '%'))
           or upper(concat(c.nombre,' ',c.apellidoPaterno,' ',c.apellidoMaterno)) like upper(concat('%', :q, '%'))
        )
        order by c.id desc
    """)
    List<Ciudadano> buscarInactivos(@Param("q") String q);
}
