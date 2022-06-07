package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.model.BancoBalcao;
import br.unisinos.edu.engine.domain.model.FilaBalcao;
import br.unisinos.edu.engine.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EngineService {
    private EngineRepository engineRepository;
    public void simulate(){
        BancoBalcao bancoBalcao = new BancoBalcao("balcao", 1, 6);
        FilaBalcao filaBalcao = new FilaBalcao("filaBalcao", 100);
    }
}
