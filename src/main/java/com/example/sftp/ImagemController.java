package com.example.sftp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImagemController {

    @PostMapping("/imagem")
    public void armazenarImagemApenasSftp(Imagem imagem) throws IOException {
        InputStream image = imagem.getFile().getInputStream();

        try{
            SftpConfig.salvarImagemSftp(image, imagem.getFile().getOriginalFilename() );
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new IOException("Erro ao salvar imagem no cdn");
        }
    }

    @PostMapping("/local")
    public void armazenarImagemPastaLocalSftp(Imagem imagem) throws IOException {
        InputStream image = imagem.getFile().getInputStream();
        String path = "C:\\Users\\Dell\\Desktop\\testeImagem" + imagem.getFile().getOriginalFilename();
        try{
            image.transferTo(new FileOutputStream(path));
            SftpConfig.salvarImagemLocalSftp(path,imagem.getFile().getOriginalFilename());
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new IOException("Erro ao salvar imagem no cdn");
        }
    }

    @GetMapping("/imagem")
    public void buscarImagemSftp() throws IOException {

        try{
            SftpConfig.buscarImagemSftp("tcc.png");
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new IOException("Erro ao salvar imagem no cdn");
        }
    }

    @DeleteMapping("/imagem")
    public void deletarImagemSftp() throws IOException {

        try{
            SftpConfig.deletarImagemSftp("tcc.png");
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new IOException("Erro ao salvar imagem no cdn");
        }
    }
}
