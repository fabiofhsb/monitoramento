package com.api.projeto.integrador.controllers;

//import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.projeto.integrador.models.ControleDeGado;
import com.api.projeto.integrador.services.ControleDeGadoService;
import com.api.projeto.integrador.services.EmailService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class ControleDeGadoController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-alerta")
    public ResponseEntity<?> enviarAlerta(@RequestBody AlertaDTO alerta) {
        emailService.enviarAlerta(alerta.getEmail(), alerta.getTipo(), alerta.getValor());
        return ResponseEntity.ok().build();
    }

    @Autowired
    private ControleDeGadoService controleDeGadoService;

    /*
     * @PostMapping
     * public ResponseEntity<ControleDeGado> salvar(@RequestBody ControleDeGado
     * controleDeGado) {
     * ControleDeGado controleDeGadoSalvo =
     * controleDeGadoService.salvar(controleDeGado);
     * return ResponseEntity.ok(controleDeGadoSalvo);
     * }
     */

    @GetMapping("/dados")
    public ResponseEntity<List<ControleDeGado>> obterDadosAnimais() {
        List<ControleDeGado> registros = controleDeGadoService.buscarTodos();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/medias")
    public ResponseEntity<Map<String, Object>> obterMediasPorNumeroProdutor() {
        Map<String, Object> medias = controleDeGadoService.calcularMediaTemperaturaPorData();
        return ResponseEntity.ok(medias);
    }

    @GetMapping("/temperatura/media")
    public ResponseEntity<Double> obterMediaTemperatura() {
        double media = controleDeGadoService.calcularMediaTemperatura();
        return ResponseEntity.ok(media);
    }

    @GetMapping("/temperatura/maxima")
    public ResponseEntity<Double> obterTemperaturaMaxima() {
        double temperaturaMaxima = controleDeGadoService.calcularTemperaturaMaxima();
        return ResponseEntity.ok(temperaturaMaxima);
    }

    @GetMapping("/temperatura/minima")
    public ResponseEntity<Double> obterTemperaturaMinima() {
        double temperaturaMinima = controleDeGadoService.calcularTemperaturaMinima();
        return ResponseEntity.ok(temperaturaMinima);
    }

    @GetMapping("/umidade/media")
    public ResponseEntity<Double> obterMediaUmidade() {
        double mediaUmidade = controleDeGadoService.calcularMediaUmidade();
        return ResponseEntity.ok(mediaUmidade);
    }

    @GetMapping("/umidade/maxima")
    public ResponseEntity<Double> obterUmidadeMaxima() {
        double umidadeMaxima = controleDeGadoService.calcularUmidadeMaxima();
        return ResponseEntity.ok(umidadeMaxima);
    }

    @GetMapping("/umidade/minima")
    public ResponseEntity<Double> obterUmidadeMinima() {
        double umidadeMinima = controleDeGadoService.calcularUmidadeMinima();
        return ResponseEntity.ok(umidadeMinima);
    }

    /*
     * 
     * @GetMapping("/classificacao/{classificacao}")
     * public ResponseEntity<Integer> contarPorClassificacao(@PathVariable String
     * classificacao) {
     * int quantidade = controleDeGadoService.contarPorClassificacao(classificacao);
     * return ResponseEntity.ok(quantidade);
     * }
     * 
     * 
     * @GetMapping("/disponiveis/{data}")
     * 
     * public ResponseEntity<Integer> calcularCabeçasDisponiveis(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int cabeçasDisponiveis =
     * controleDeGadoService.calcularCabeçasDisponiveis(calendar.getTime());
     * return ResponseEntity.ok(cabeçasDisponiveis);
     * }
     * /*
     * 
     * @GetMapping("/vitelos/{data}")
     * 
     * public ResponseEntity<Integer> calcularVitelos(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int vitelosDisponiveis =
     * controleDeGadoService.calcularVitelos(calendar.getTime());
     * return ResponseEntity.ok(vitelosDisponiveis);
     * }
     * 
     * @GetMapping("/superPrecoce/{data}")
     * 
     * public ResponseEntity<Integer> calcularNovilhosSuperPrecoce(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int NovilhosSuperPrecoceDisponiveis =
     * controleDeGadoService.calcularNovilhosSuperPrecoce(calendar.getTime());
     * return ResponseEntity.ok(NovilhosSuperPrecoceDisponiveis);
     * }
     * 
     * @GetMapping("/precoce/{data}")
     * 
     * public ResponseEntity<Integer> calcularNovilhosPrecoce(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int NovilhosPrecoceDisponiveis =
     * controleDeGadoService.calcularNovilhosPrecoce(calendar.getTime());
     * return ResponseEntity.ok(NovilhosPrecoceDisponiveis);
     * }
     * 
     * @GetMapping("/novilhos/{data}")
     * 
     * public ResponseEntity<Integer> calcularNovilhos(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int NovilhosDisponiveis =
     * controleDeGadoService.calcularNovilhos(calendar.getTime());
     * return ResponseEntity.ok(NovilhosDisponiveis);
     * }
     * 
     * @GetMapping("/bois/{data}")
     * 
     * public ResponseEntity<Integer> calcularBois(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int boisDisponiveis = controleDeGadoService.calcularBois(calendar.getTime());
     * return ResponseEntity.ok(boisDisponiveis);
     * }
     * 
     * @GetMapping("/touros/{data}")
     * 
     * public ResponseEntity<Integer> calcularTouros(
     * 
     * @PathVariable @DateTimeFormat(iso = ISO.DATE) Date data,
     * 
     * @RequestParam(value = "timeZone", defaultValue = "UTC") String timeZoneId) {
     * TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
     * Calendar calendar = Calendar.getInstance(timeZone);
     * calendar.setTime(data);
     * int tourosDisponiveis =
     * controleDeGadoService.calcularTouros(calendar.getTime());
     * return ResponseEntity.ok(tourosDisponiveis);
     * }
     */

    @GetMapping("/cabeças-de-gado")
    public Page<ControleDeGado> listarCabeçasDeGadoPaginado(@RequestParam(defaultValue = "0") int page) {
        return controleDeGadoService.listarCabeçasDeGadoPaginado(page, 500);
    }

    /*
     * @DeleteMapping("/delete/{id}")
     * public void deleteById(@PathVariable("id") UUID id) {
     * controleDeGadoService.deleteById(id);
     * }
     * 
     * /*
     * 
     * @PutMapping("/editar/{numeroIdentificacao}")
     * public ResponseEntity<String>
     * atualizarAnimal(@PathVariable("numeroIdentificacao") String
     * numeroIdentificacao, @RequestBody ControleDeGadoDTO controleDeGadoDTO) {
     * // Aqui você pode implementar a lógica para atualizar o animal com base nos
     * dados recebidos
     * try {
     * List<ControleDeGado> animal =
     * controleDeGadoService.buscarPorNumeroIdentificacao(numeroIdentificacao);
     * if (animal != null) {
     * // Atualize os campos relevantes do animal com base nos dados recebidos em
     * animalDTO
     * animal.setNumeroIdentificacao(controleDeGadoDTO.getNumeroIdentificacao());
     * animal.setNumeroProdutor(controleDeGadoDTO.getNumeroProdutor());
     * animal.setDataNascimento(controleDeGadoDTO.getDataNascimento());
     * 
     * 
     * // Atualize outros campos, se necessário
     * 
     * controleDeGadoService.salvar(animal); // Salva as alterações no banco de
     * dados
     * 
     * return new ResponseEntity<>("Animal atualizado com sucesso", HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>("Animal não encontrado", HttpStatus.NOT_FOUND);
     * }
     * } catch (Exception e) {
     * return new ResponseEntity<>("Erro ao atualizar animal: " + e.getMessage(),
     * HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     */
}