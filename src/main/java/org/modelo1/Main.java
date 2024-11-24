package org.modelo1;

import java.util.*;
import org.modelo1.entidades.Categoria;
import org.modelo1.entidades.ArticuloInsumo;
import org.modelo1.entidades.ArticuloManufacturado;
import org.modelo1.entidades.UnidadMedida;
import org.modelo1.entidades.ImagenArticulo;
import org.modelo1.entidades.ArticuloManufacturadoDetalle;
import org.modelo1.repositorios.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        // Inicializar repositorios
        var categoriaRepo = new InMemoryRepository<Categoria>();
        var insumoRepo = new InMemoryRepository<ArticuloInsumo>();
        var manufacturadoRepo = new InMemoryRepository<ArticuloManufacturado>();
        var unidadMedidaRepo = new InMemoryRepository<UnidadMedida>();
        
        // Crear categorías
        var categorias = List.of(
                Categoria.builder().denominacion("Pizzas").esInsumo(false).build(),
                Categoria.builder().denominacion("Sandwiches").esInsumo(false).build(),
                Categoria.builder().denominacion("Bebidas").esInsumo(false).build(),
                Categoria.builder().denominacion("Insumos").esInsumo(true).build()
        );
        categorias.forEach(categoriaRepo::save);
        
        // Crear unidades de medida
        var unidades = List.of(
                UnidadMedida.builder().denominacion("Kg").build(),
                UnidadMedida.builder().denominacion("Litro").build(),
                UnidadMedida.builder().denominacion("Gramos").build()
        );
        unidades.forEach(unidadMedidaRepo::save);
        
        // Crear artículos insumos
        var insumos = List.of(
                ArticuloInsumo.builder()
                        .denominacion("Sal")
                        .precioCompra(1.0)
                        .stockActual(100)
                        .stockMinimo(10)
                        .stockMaximo(200)
                        .esParaElaborar(true)
                        .unidadMedida(unidades.get(2))
                        .categoria(categorias.get(3))
                        .build(),
                ArticuloInsumo.builder()
                        .denominacion("Harina")
                        .precioCompra(0.5)
                        .stockActual(50)
                        .stockMinimo(5)
                        .stockMaximo(100)
                        .esParaElaborar(true)
                        .unidadMedida(unidades.get(0))
                        .categoria(categorias.get(3))
                        .build(),
                ArticuloInsumo.builder()
                        .denominacion("Aceite")
                        .precioCompra(3.0)
                        .stockActual(30)
                        .stockMinimo(3)
                        .stockMaximo(60)
                        .esParaElaborar(true)
                        .unidadMedida(unidades.get(1))
                        .categoria(categorias.get(3))
                        .build(),
                ArticuloInsumo.builder()
                        .denominacion("Carne")
                        .precioCompra(5.0)
                        .stockActual(20)
                        .stockMinimo(2)
                        .stockMaximo(40)
                        .esParaElaborar(true)
                        .unidadMedida(unidades.get(0))
                        .categoria(categorias.get(3))
                        .build()
        );
        insumos.forEach(insumoRepo::save);
        
        // Crear imágenes para los artículos
        var imagenes = Set.of(
                ImagenArticulo.builder().name("Pizza1 Hawaiana").url("pizza1").build(),
                ImagenArticulo.builder().name("Pizza2 Hawaiana").url("pizza2").build(),
                ImagenArticulo.builder().name("Pizza3 Hawaiana").url("pizza3").build(),
                ImagenArticulo.builder().name("Lomo 1 Completo").url("lomo1").build(),
                ImagenArticulo.builder().name("Lomo 2 Completo").url("lomo2").build(),
                ImagenArticulo.builder().name("Lomo 3 Completo").url("lomo3").build()
        );
        
        // Crear detalles de artículos manufacturados
        var detallesPizzaHawaina = Set.of(
                ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(insumos.get(0)).build(),
                ArticuloManufacturadoDetalle.builder().cantidad(2).articuloInsumo(insumos.get(1)).build(),
                ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(insumos.get(2)).build()
        );
        
        var detallesLomoCompleto = Set.of(
                ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(insumos.get(0)).build(),
                ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(insumos.get(2)).build(),
                ArticuloManufacturadoDetalle.builder().cantidad(2).articuloInsumo(insumos.get(3)).build()
        );
        
        // Crear artículos manufacturados
        var manufacturados = List.of(
                ArticuloManufacturado.builder()
                        .denominacion("Pizza Hawaina")
                        .precioVenta(12.0)
                        .descripcion("Pizza con piña y jamón")
                        .tiempoEstimadoMinutos(20)
                        .preparacion("Hornear por 20 minutos")
                        .categoria(categorias.get(0))
                        .unidadMedida(unidades.get(0))
                        .imagenes(new HashSet<>(imagenes))
                        .articuloManufacturadoDetalles(detallesPizzaHawaina)
                        .build(),
                ArticuloManufacturado.builder()
                        .denominacion("Lomo Completo")
                        .precioVenta(15.0)
                        .descripcion("Lomo completo con todos los ingredientes")
                        .tiempoEstimadoMinutos(25)
                        .preparacion("Cocinar a la parrilla por 25 minutos")
                        .categoria(categorias.get(1))
                        .unidadMedida(unidades.get(0))
                        .imagenes(new HashSet<>(imagenes))
                        .articuloManufacturadoDetalles(detallesLomoCompleto)
                        .build()
        );
        manufacturados.forEach(manufacturadoRepo::save);
        
        // Mostrar todas las categorías
        System.out.println("Todas las categorías:");
        categoriaRepo.findAll().forEach(System.out::println);
        
        // Mostrar todos los artículos insumos
        System.out.println("Todos los artículos insumos:");
        insumoRepo.findAll().forEach(System.out::println);
        
        // Mostrar todos los artículos manufacturados
        System.out.println("Todos los artículos manufacturados:");
        manufacturadoRepo.findAll().forEach(System.out::println);
    }
}
