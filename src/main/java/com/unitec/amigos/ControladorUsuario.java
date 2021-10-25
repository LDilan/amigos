package com.unitec.amigos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorUsuario {
/*aqui va un metodo que representa cada uno de los estados que
vamos a transferir, es decir va un get, put y delete como minimo.
 */

//uso de la invercion del control
@Autowired RepositorioUsuario repositorioUsuario;

//guardar usuario en mongo db

@PostMapping("/usuario")
public Estatus guardar (@RequestBody String json) throws  Exception{
//primero se lee y se convierte el objeto json a objeto java
ObjectMapper mapper= new ObjectMapper();
Usuario u=mapper.readValue(json,Usuario.class);
//usuarion en formato json se guarda en mongodb
repositorioUsuario.save(u);
//se crea el objeto tipo Estatus y se retorna al cliente este objeto
Estatus estatus = new Estatus();
estatus.setSuccess(true);
estatus.setMensaje("El usuario se guardo con exito!!");
return estatus;
}
@GetMapping("/usuario/{id}")
public Usuario obtenerporId(@PathVariable String id){
/*
//generar usuario fake
Usuario u=new Usuario();
u.setEdad(23);
u.setEmail("dil12@gmail.com");
u.setNombre("LDilan");*/
//se lee un usuario con el metodo findbyid pasandole como argumento el id
Usuario U = repositorioUsuario.findById(id).get();
return U;
}
//
@GetMapping("/usuario")
public List<Usuario> buscarTodo(){
return repositorioUsuario.findAll();
}
//actualizar
@PutMapping("/usuario")
public Estatus actualizar(@RequestBody String json) throws  Exception{
// se verifica que exista, primero se busca
ObjectMapper mapper=new ObjectMapper();
Usuario u=mapper.readValue(json, Usuario.class);
Estatus e=new Estatus();
if(repositorioUsuario.findById(u.getEmail()).isPresent()){
//lo guardamos nuevamente
repositorioUsuario.save(u);
e.setMensaje("USUARIO ACTULAZADO EXITOSAMENTE");
e.setSuccess(true);
}//----<if
else {
e.setMensaje("NO EXISTE EL USUARIO,NO SE PUEDE ACTUALIZAR");
e.setSuccess(false);
}//-->else

return e;
}

}
