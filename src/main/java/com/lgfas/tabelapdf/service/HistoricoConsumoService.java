package com.lgfas.tabelapdf.service;

import com.lgfas.tabelapdf.model.HistoricoConsumoDemanda;
import com.lgfas.tabelapdf.repository.HistoricoConsumoDemandaRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoConsumoService {

    @Autowired
    private HistoricoConsumoDemandaRepository repository;

    public void processarPdf(String caminhoPdf, Long clienteId) throws Exception {
        try (PDDocument document = PDDocument.load(new File(caminhoPdf))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            List<HistoricoConsumoDemanda> historicos = extrairDadosDoTexto(text, clienteId);
            repository.saveAll(historicos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao processar PDF: " + e.getMessage(), e);
        }
    }

    private List<HistoricoConsumoDemanda> extrairDadosDoTexto(String texto, Long clienteId) {
        List<HistoricoConsumoDemanda> historicos = new ArrayList<>();
        String[] linhas = texto.split("\n");
        boolean dentroDaTabela = false;

        for (String linha : linhas) {
            // Detecta o início da seção da tabela
            if (linha.contains("Histórico dos últimos meses")) {
                dentroDaTabela = true;
                continue;  // pula para a próxima linha
            }

            // Detecta o final da tabela se estiver dentro dela
            if (dentroDaTabela && linha.trim().isEmpty()) {
                dentroDaTabela = false;
                break;  // Sai do loop ao encontrar uma linha em branco
            }

            // Processa apenas as linhas dentro da tabela com formato correto
            if (dentroDaTabela && linha.matches("^[A-Z]{3}\\s+([0-9,.]+\\s+){8}[0-9,.]+$")) {
                String[] colunas = linha.trim().split("\\s+");

                try {
                    if (colunas.length >= 9) {
                        HistoricoConsumoDemanda historico = new HistoricoConsumoDemanda();
                        historico.setClienteId(clienteId);
                        historico.setMes(colunas[0]);
                        historico.setDemandaMedidaPonta(Double.parseDouble(colunas[1].replace(",", ".")));
                        historico.setDemandaMedidaForaPonta(Double.parseDouble(colunas[2].replace(",", ".")));
                        historico.setDemandaMedidaReativoExcedente(Double.parseDouble(colunas[3].replace(",", ".")));
                        historico.setConsumoFaturadoPontaTot(Double.parseDouble(colunas[4].replace(",", ".")));
                        historico.setConsumoFaturadoForaPonta(Double.parseDouble(colunas[5].replace(",", ".")));
                        historico.setConsumoFaturadoReativoExcedente(Double.parseDouble(colunas[6].replace(",", ".")));
                        historico.setHorarioReservadoConsumo(Double.parseDouble(colunas[7].replace(",", ".")));
                        historico.setHorarioReservadoReativoExcedente(Double.parseDouble(colunas[8].replace(",", ".")));

                        historicos.add(historico);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter dados da linha: " + linha);
                    e.printStackTrace();  // Exibe o stack trace para diagnóstico
                }
            }
        }
        return historicos;
    }


}
