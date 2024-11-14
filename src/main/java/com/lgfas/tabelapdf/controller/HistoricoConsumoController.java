package com.lgfas.tabelapdf.controller;

import com.lgfas.tabelapdf.service.HistoricoConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/consumo")
public class HistoricoConsumoController {

    @Autowired
    private HistoricoConsumoService historicoConsumoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file,
                                            @RequestParam("clienteId") Long clienteId) {
        try {
            // Salva o arquivo PDF temporariamente
            File tempFile = File.createTempFile("fatura-", ".pdf");
            file.transferTo(tempFile);

            // Processa o PDF e salva os dados
            historicoConsumoService.processarPdf(tempFile.getAbsolutePath(), clienteId);

            // Deleta o arquivo temporário
            tempFile.delete();

            return ResponseEntity.ok("Dados processados e salvos com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();  // Exibe o stack trace completo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar PDF: " + e.getMessage());
        }
    }
}
