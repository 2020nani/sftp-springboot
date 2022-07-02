package com.example.sftp;

import com.jcraft.jsch.*;

import java.io.InputStream;


public class SftpConfig {

    /* criar canal para acessar servidor sftp */
    public static ChannelSftp setupJsch() throws JSchException {

        Session jschSession = null;

        Channel sftp = null;

        JSch jsch = new JSch();
        JSch.setConfig("StrictHostKeyChecking", "no");
        //jsch.setKnownHosts("/home/remote_user/.ssh/known_hosts");
        jschSession = jsch.getSession("remote_user", "localhost", 58898);

        // authenticate using private key
        // jsch.addIdentity(<destino da chave ssh>);

        // authenticate using password
        jschSession.setPassword("password1234");

        // 10 seconds session timeout
        jschSession.connect(100000);

        sftp = jschSession.openChannel("sftp");

        return  (ChannelSftp) sftp;

    }

    /* armazenar imagem servidor SFTP */
    public static void salvarImagemSftp(InputStream storedImage, String originalFilename) throws SftpException, JSchException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.put(storedImage,channelSftp.getHome() + "/" + originalFilename );

        channelSftp.exit();

        System.out.println("Done");

    }

    /* armazenar imagem servidor SFTP e local*/
    public static void salvarImagemLocalSftp(String pathLocal, String originalFilename) throws SftpException, JSchException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.put(pathLocal,channelSftp.getHome() + "/" + originalFilename );

        channelSftp.exit();

        System.out.println("Done");

    }

    /* buscar imagem servidor sftp e transferir para pasta local */
    public static void buscarImagemSftp(String originalFilename) throws SftpException, JSchException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.get(channelSftp.getHome() + "/" + originalFilename, "C:\\Users\\Dell\\Desktop\\testeImagem" );

        channelSftp.exit();

        System.out.println("Done");

    }

    /* deletar imagem servidor sftp */
    public static void deletarImagemSftp(String nomeImagem) throws SftpException, JSchException {

        Session jschSession = null;

        Channel sftp = null;
        // transfer file from local to remote server
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect(50000);

        channelSftp.rm(channelSftp.getHome() + "/" + nomeImagem);

        channelSftp.exit();

        System.out.println("Done");

    }
}
