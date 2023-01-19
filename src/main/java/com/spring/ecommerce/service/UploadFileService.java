package com.spring.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
 private String folder="images//"; //almacenamos la ubicacion donde se van alamacenar las imagenes
 public String saveImage(MultipartFile file) throws IOException {//recibe el objeto de tipo MultipartFile en este se almacenara mi imagen
	 if(!file.isEmpty()) {//siempre y cuando file empty no sea vacio
		 byte [] bytes=file.getBytes();//pasamos la imagen a bytes
		 Path path = Paths.get(folder+file.getOriginalFilename());//pasamos por el path la ruta donde se almacenara la imagen
		 Files.write(path, bytes);//le enavamos la ruta y los bytes que a transformado mi imagen en bytes
		 return file.getOriginalFilename();//retorno el nombre de la imagen que subi
	 }
	return "default.jpg";//en caso de que la imagen no cargue se cargara una imagen por defecto
	 
 }
 //eliminar imagen cuando se elimine unproducto
 public void deleteImage(String nombre) {//recibo el nombre de la imagen
	 String ruta="images//";//guarda la ruta donde esta almacenada la imagen
     File file = new File(ruta+nombre);//creo un objeto de tipo file le paso la ruta de la imagen y el nombre de la imagen
     file.delete();//eliminamos la imagen
 }
}
