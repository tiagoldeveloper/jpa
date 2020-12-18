package br.com.jpa.temp;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


//@Tag("branch-20")
public class ExemplosTests {


    @DisplayName("Esse teste só é executado para o sistema operacional linux")
    @Test
    @EnabledOnOs({OS.LINUX})
    public void executandoLinux(){
        System.out.println("Executando com  LINUX");
    }

    @DisplayName("Desabilitado para não ser executado para o sistema operacional linux")
    @Test
    @DisabledOnOs(OS.LINUX)
    public void desabilitadoParaLinux(){
        System.out.println("Desabilitado para Linux");
    }

    @DisplayName("Esse test é executado somente para os sistemas operacionais: Windows ou Linux ou Mac")
    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX, OS.MAC})
    public void ehWindowsOrLinuxOrMac(){
        System.out.println("Executando com WINDOWS, LINUX e MAC");
    }

}
